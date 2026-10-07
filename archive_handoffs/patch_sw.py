import re

with open('resources/engine_cpp/src/stackvm.cpp', 'r', encoding='utf-8') as f:
    code = f.read()

# We want to replace the body of 	ry_collapse_string_switch up to the end of the function.
# Let's find the start of ool try_collapse_string_switch and the end.

pattern = re.compile(r'bool try_collapse_string_switch\(std::vector<StmtPtr>& stmts, size_t i\) \{.*?return true;\n\}', re.DOTALL)

new_func = '''bool try_collapse_string_switch(std::vector<StmtPtr>& stmts, size_t i) {
    if (i + 1 >= stmts.size() || stmts[i + 1]->kind != StmtKind::SwitchStmt) return false;
    auto* sw2 = static_cast<SwitchStmt*>(stmts[i + 1].get());
    if (!sw2->selector || sw2->selector->kind != ExprKind::Local) return false;
    std::string index_var = static_cast<Local*>(sw2->selector.get())->name;

    struct Sw1Candidate {
        SwitchStmt* sw;
        std::vector<StmtPtr>* container;
        size_t pos;
    };
    std::vector<Sw1Candidate> candidates;

    std::function<void(std::vector<StmtPtr>&, size_t)> find_sw1 = [&](std::vector<StmtPtr>& blk, size_t pos) {
        if (pos >= blk.size()) return;
        if (blk[pos]->kind == StmtKind::SwitchStmt) {
            candidates.push_back({static_cast<SwitchStmt*>(blk[pos].get()), &blk, pos});
            auto* sw = static_cast<SwitchStmt*>(blk[pos].get());
            for (auto& c : sw->cases) {
                if (!c.body.empty()) find_sw1(c.body, c.body.size() - 1);
            }
        } else if (blk[pos]->kind == StmtKind::IfStmt) {
            auto* ifs = static_cast<IfStmt*>(blk[pos].get());
            if (!ifs->then_body.empty()) find_sw1(ifs->then_body, ifs->then_body.size() - 1);
            if (ifs->else_body.has_value() && !ifs->else_body->empty()) find_sw1(*ifs->else_body, ifs->else_body->size() - 1);
        }
    };

    find_sw1(stmts, i);

    bool any_collapsed = false;

    for (auto& cand : candidates) {
        SwitchStmt* sw1 = cand.sw;
        if (!sw1->selector || sw1->selector->kind != ExprKind::MethodCall) continue;
        auto* hc = static_cast<MethodCall*>(sw1->selector.get());
        if (hc->name != "hashCode" || !hc->args.empty() || !hc->target || hc->target->kind != ExprKind::Local) continue;
        std::string var_name = static_cast<Local*>(hc->target.get())->name;

        std::string detected_index_var;
        std::map<std::string, std::string> index_to_literal;
        bool valid = true;
        for (auto& c : sw1->cases) {
            if (c.is_default) {
                for (auto& st : c.body) {
                    if (st->kind != StmtKind::BreakStmt) { valid = false; break; }
                }
                continue;
            }
            if (c.values.size() != 1) { valid = false; break; }
            auto info = match_hash_case_body(c.body, var_name, detected_index_var);
            if (!info.has_value()) { valid = false; break; }
            if (index_to_literal.count(info->index_value)) { valid = false; break; }
            index_to_literal[info->index_value] = info->literal;
        }
        if (!valid || detected_index_var.empty() || detected_index_var != index_var || index_to_literal.empty()) continue;

        std::vector<SwitchCase> new_cases;
        bool cases_valid = true;
        for (auto& c : sw2->cases) {
            if (c.is_default) {
                new_cases.push_back(c);
                continue;
            }
            std::vector<std::string> new_values;
            for (auto& v : c.values) {
                auto it = index_to_literal.find(v);
                if (it == index_to_literal.end()) { cases_valid = false; break; }
                new_values.push_back(java_string_literal(it->second));
            }
            if (!cases_valid) break;
            SwitchCase nc;
            nc.values = new_values;
            nc.body = c.body; // Share pointers
            nc.is_default = false;
            new_cases.push_back(nc);
        }
        if (!cases_valid) continue;

        auto new_selector = std::make_shared<Local>(var_name, "String");
        (*cand.container)[cand.pos] = std::make_shared<SwitchStmt>(std::move(new_selector), std::move(new_cases), sw2->label);
        any_collapsed = true;
    }

    if (any_collapsed) {
        stmts.erase(stmts.begin() + static_cast<long>(i) + 1);
        return true;
    }
    return false;
}'''

new_code = pattern.sub(new_func, code)
with open('resources/engine_cpp/src/stackvm.cpp', 'w', encoding='utf-8') as f:
    f.write(new_code)
