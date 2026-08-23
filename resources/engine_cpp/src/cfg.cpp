// cfg.cpp - см. cfg.hpp. 1:1 порт cfg.py (включая воспроизведённые
// особенности оригинала - см. HANDOFF_28).
#include "cfg.hpp"

#include <algorithm>
#include <functional>

namespace nd {

namespace {
constexpr int64_t EXIT_MARKER = -1;  // аналог строкового сентинела "__EXIT__" в Python
}

CFG::CFG(const std::map<size_t, Instruction>& instrs, const std::vector<size_t>& order,
         const std::vector<ExceptionEntry>& exceptions) {
    for (auto& [pc, ins] : instrs) instrs_[static_cast<int64_t>(pc)] = ins;
    for (size_t pc : order) order_.push_back(static_cast<int64_t>(pc));
    exceptions_ = exceptions;
    entry = order_.empty() ? std::nullopt : std::optional<int64_t>(order_.front());
    build();
    if (entry.has_value()) compute_dominators();
}

std::set<int64_t> CFG::leaders() const {
    std::set<int64_t> ldrs;
    if (!order_.empty()) ldrs.insert(order_.front());
    for (int64_t pc : order_) {
        const Instruction& ins = instrs_.at(pc);
        if (ins.is_branch()) {
            if (ins.target.has_value()) ldrs.insert(*ins.target);
            ldrs.insert(static_cast<int64_t>(ins.next_pc));
        } else if (ins.is_switch()) {
            if (ins.targets.has_value()) {
                for (auto& [v, t] : *ins.targets) ldrs.insert(t);
            }
            ldrs.insert(static_cast<int64_t>(ins.next_pc));
        } else if (ins.is_unconditional()) {
            ldrs.insert(static_cast<int64_t>(ins.next_pc));
        }
    }
    for (auto& e : exceptions_) {
        ldrs.insert(e.start_pc);
        ldrs.insert(e.end_pc);
        ldrs.insert(e.handler_pc);
    }
    std::set<int64_t> out;
    for (int64_t pc : ldrs) {
        if (instrs_.count(pc)) out.insert(pc);
    }
    return out;
}

void CFG::build() {
    std::set<int64_t> leaders_set = leaders();
    if (leaders_set.empty()) return;
    std::vector<int64_t> leaders_vec(leaders_set.begin(), leaders_set.end());  // std::set уже отсортирован

    for (size_t i = 0; i < leaders_vec.size(); ++i) {
        int64_t start = leaders_vec[i];
        Block b;
        b.start = start;
        int64_t end;
        if (i + 1 < leaders_vec.size()) {
            end = leaders_vec[i + 1];
        } else {
            // Зеркалит Python: `self.order[-1] and self.instrs[self.order[-1]].next_pc`
            // - "and"-трюк даёт 0 (а НЕ next_pc), если order[-1] == 0 (метод из
            // ОДНОЙ инструкции на pc=0). Это особенность/баг оригинала -
            // воспроизведена намеренно, не исправлена. См. HANDOFF_28.
            int64_t last = order_.back();
            end = (last == 0) ? 0 : static_cast<int64_t>(instrs_.at(last).next_pc);
        }
        int64_t pc = start;
        while (pc < end && instrs_.count(pc)) {
            b.instrs.push_back(instrs_.at(pc));
            pc = static_cast<int64_t>(instrs_.at(pc).next_pc);
        }
        b.end = pc;
        blocks[start] = std::move(b);
    }

    std::vector<int64_t> starts;
    for (auto& [s, _] : blocks) starts.push_back(s);  // std::map -> уже отсортировано

    for (size_t i = 0; i < starts.size(); ++i) {
        int64_t start = starts[i];
        Block& b = blocks[start];
        if (b.instrs.empty()) continue;
        const Instruction& last = b.instrs.back();
        if (last.is_switch()) {
            if (last.targets.has_value()) {
                for (auto& [v, t] : *last.targets) {
                    if (blocks.count(t)) b.succs.push_back(t);
                }
            }
        } else if (last.mnemonic == "goto" || last.mnemonic == "goto_w") {
            if (last.target.has_value() && blocks.count(*last.target)) b.succs.push_back(*last.target);
        } else if (last.is_conditional()) {
            if (last.target.has_value() && blocks.count(*last.target)) b.succs.push_back(*last.target);
            if (blocks.count(static_cast<int64_t>(last.next_pc))) b.succs.push_back(static_cast<int64_t>(last.next_pc));
        } else if (last.is_return() || last.mnemonic == "athrow") {
            // нет fallthrough-преемника
        } else {
            if (i + 1 < starts.size()) b.succs.push_back(starts[i + 1]);
        }
    }

    // Рёбра исключений: см. предупреждение в cfg.hpp - дублирующиеся записи
    // handler_types воспроизведены намеренно точно как в оригинале.
    for (auto& e : exceptions_) {
        if (!blocks.count(e.handler_pc)) continue;
        for (int64_t start : starts) {
            if (e.start_pc <= start && start < e.end_pc) {
                blocks[start].succs.push_back(e.handler_pc);
                Block& hb = blocks[e.handler_pc];
                hb.handler_types.emplace_back(e.catch_type, e);
            }
        }
    }

    for (auto& [start, b] : blocks) {
        for (int64_t s : b.succs) {
            auto& target_preds = blocks[s].preds;
            if (std::find(target_preds.begin(), target_preds.end(), start) == target_preds.end()) {
                target_preds.push_back(start);
            }
        }
    }
}

// ---------------- dominators ----------------

void CFG::compute_dominators() {
    std::vector<int64_t> rpo = reverse_postorder();
    std::map<int64_t, size_t> rpo_index;
    for (size_t i = 0; i < rpo.size(); ++i) rpo_index[rpo[i]] = i;

    std::map<int64_t, int64_t> idom;
    idom[*entry] = *entry;
    bool changed = true;
    while (changed) {
        changed = false;
        for (int64_t b : rpo) {
            if (b == *entry) continue;
            std::vector<int64_t> preds;
            for (int64_t p : blocks[b].preds) {
                if (idom.count(p)) preds.push_back(p);
            }
            if (preds.empty()) continue;
            int64_t new_idom = preds[0];
            for (size_t k = 1; k < preds.size(); ++k) {
                int64_t a = new_idom, c = preds[k];
                while (a != c) {
                    while (rpo_index[a] > rpo_index[c]) a = idom[a];
                    while (rpo_index[c] > rpo_index[a]) c = idom[c];
                }
                new_idom = a;
            }
            auto it = idom.find(b);
            if (it == idom.end() || it->second != new_idom) {
                idom[b] = new_idom;
                changed = true;
            }
        }
    }
    for (auto& [b, blk] : blocks) {
        auto it = idom.find(b);
        blk.idom = (it != idom.end()) ? std::optional<int64_t>(it->second) : std::nullopt;
    }
    idom_ = std::move(idom);
}

std::vector<int64_t> CFG::reverse_postorder() const {
    std::set<int64_t> visited;
    std::vector<int64_t> order;
    std::function<void(int64_t)> dfs = [&](int64_t n) {
        visited.insert(n);
        auto it = blocks.find(n);
        if (it != blocks.end()) {
            for (int64_t s : it->second.succs) {
                if (!visited.count(s) && blocks.count(s)) dfs(s);
            }
        }
        order.push_back(n);
    };
    if (entry.has_value() && blocks.count(*entry)) dfs(*entry);
    std::reverse(order.begin(), order.end());
    for (auto& [s, _] : blocks) {  // std::map -> сортированный порядок, как sorted(self.blocks)
        if (!visited.count(s)) {
            order.push_back(s);
            visited.insert(s);
        }
    }
    return order;
}

std::vector<int64_t> CFG::reverse_postorder_list() const { return reverse_postorder(); }

bool CFG::dominates(int64_t a, int64_t b) const {
    int64_t n = b;
    size_t seen = 0;
    while (true) {
        if (n == a) return true;
        auto it = idom_.find(n);
        if (it == idom_.end()) return n == a;
        if (it->second == n) return n == a;
        if (seen > blocks.size() + 2) return false;
        n = it->second;
        seen += 1;
    }
}

std::vector<std::tuple<int64_t, std::set<int64_t>, std::set<int64_t>>> CFG::natural_loops() const {
    // Порядок заголовков (header) в результирующем списке ДОЛЖЕН совпадать
    // с порядком их ПЕРВОГО обнаружения (как insertion-order Python dict) -
    // воспроизведено явным вектором first-seen порядка.
    std::vector<int64_t> header_order;
    std::map<int64_t, std::set<int64_t>> loops;
    for (auto& [start, b] : blocks) {  // sorted(self.blocks.items()) по построению std::map
        for (int64_t s : b.succs) {
            if (blocks.count(s) && dominates(s, start)) {
                auto [it, inserted] = loops.try_emplace(s);
                if (inserted) header_order.push_back(s);
                it->second.insert(start);
            }
        }
    }
    std::vector<std::tuple<int64_t, std::set<int64_t>, std::set<int64_t>>> result;
    for (int64_t header : header_order) {
        const std::set<int64_t>& tails = loops[header];
        std::set<int64_t> body;
        body.insert(header);
        std::vector<int64_t> stack(tails.begin(), tails.end());  // порядок стека не влияет на итоговое множество body
        while (!stack.empty()) {
            int64_t n = stack.back();
            stack.pop_back();
            if (!body.count(n)) {
                body.insert(n);
                auto it = blocks.find(n);
                if (it != blocks.end()) {
                    for (int64_t p : it->second.preds) {
                        if (!body.count(p)) stack.push_back(p);
                    }
                }
            }
        }
        result.emplace_back(header, std::move(body), tails);
    }
    return result;
}

// ---------------- post-dominators ----------------

std::map<int64_t, std::optional<int64_t>> CFG::compute_postdominators() {
    std::map<int64_t, std::vector<int64_t>> preds_rev;  // succs of b (используется как "предки" в развёрнутом графе)
    std::map<int64_t, std::vector<int64_t>> succs_rev;  // preds of b
    for (auto& [b, blk] : blocks) {
        preds_rev[b] = blk.succs;
        succs_rev[b] = blk.preds;
    }
    std::vector<int64_t> exit_preds;
    for (auto& [b, blk] : blocks) {
        if (blk.succs.empty()) exit_preds.push_back(b);
    }

    std::set<int64_t> visited;
    std::vector<int64_t> order;
    std::function<void(int64_t)> dfs = [&](int64_t n) {
        visited.insert(n);
        const std::vector<int64_t>& nxt = (n == EXIT_MARKER) ? exit_preds : succs_rev[n];
        for (int64_t s : nxt) {
            if (!visited.count(s)) dfs(s);
        }
        order.push_back(n);
    };
    dfs(EXIT_MARKER);
    for (auto& [b, _] : blocks) {
        if (!visited.count(b)) {
            order.push_back(b);
            visited.insert(b);
        }
    }
    std::reverse(order.begin(), order.end());
    std::map<int64_t, size_t> rpo_index;
    for (size_t i = 0; i < order.size(); ++i) rpo_index[order[i]] = i;

    std::map<int64_t, int64_t> idom;
    idom[EXIT_MARKER] = EXIT_MARKER;
    bool changed = true;
    while (changed) {
        changed = false;
        for (int64_t b : order) {
            if (b == EXIT_MARKER) continue;
            std::vector<int64_t> ps = preds_rev.count(b) ? preds_rev[b] : std::vector<int64_t>{};
            if (std::find(exit_preds.begin(), exit_preds.end(), b) != exit_preds.end()) ps.push_back(EXIT_MARKER);
            std::vector<int64_t> ps_f;
            for (int64_t p : ps) if (idom.count(p)) ps_f.push_back(p);
            if (ps_f.empty()) continue;
            int64_t new_idom = ps_f[0];
            for (size_t k = 1; k < ps_f.size(); ++k) {
                int64_t a = new_idom, c = ps_f[k];
                while (a != c) {
                    while (rpo_index[a] > rpo_index[c]) a = idom[a];
                    while (rpo_index[c] > rpo_index[a]) c = idom[c];
                }
                new_idom = a;
            }
            auto it = idom.find(b);
            if (it == idom.end() || it->second != new_idom) {
                idom[b] = new_idom;
                changed = true;
            }
        }
    }
    std::map<int64_t, std::optional<int64_t>> result;
    for (auto& [b, _] : blocks) {
        auto it = idom.find(b);
        if (it == idom.end() || it->second == EXIT_MARKER) {
            result[b] = std::nullopt;
        } else {
            result[b] = it->second;
        }
    }
    return result;
}

}  // namespace nd
