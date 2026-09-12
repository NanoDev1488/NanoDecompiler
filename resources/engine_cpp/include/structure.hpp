// structure.hpp - порт resources/engine/structure.py (v2.0, HANDOFF_38).
// Структуризация: убирает goto/переходы по адресам и строит вложенный AST
// (if/else, while/do-while/for, switch, try/catch, break/continue) поверх CFG.
#pragma once

#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
#include <map>
#include <memory>
#include <optional>
#include <set>
#include <string>
#include <vector>

#include "ast_nodes.hpp"
#include "cfg.hpp"
#include "stackvm.hpp"

namespace nd {

class Structurer {
public:
    Structurer(CFG& cfg, const std::map<int64_t, BlockResult>& block_results,
               const std::vector<ExceptionEntry>& exceptions, MethodCtx& ctx);

    std::vector<StmtPtr> build(int64_t entry_pc);

private:
    struct StackEntry {
        std::optional<int64_t> header;  // присутствует только для записей циклов
        std::optional<int64_t> exit;
        std::optional<std::string> label;
    };
    using StackEntryPtr = std::shared_ptr<StackEntry>;

    struct MergedExc {
        int64_t start_pc, end_pc;
        std::optional<std::string> catch_type;
        int64_t handler_pc;
    };

    CFG& cfg_;
    const std::map<int64_t, BlockResult>& results_;
    std::vector<ExceptionEntry> exceptions_;
    MethodCtx& ctx_;
    std::map<int64_t, std::optional<int64_t>> ipdom_;

    // --- циклы ---
    std::map<int64_t, std::pair<std::set<int64_t>, std::optional<int64_t>>> loop_headers_;  // header -> (body, exit)
    std::set<int64_t> consumed_loop_;

    // --- try/catch ---
    // ключ (start,end) -> список (catch_type, handler_pc) В ПОРЯДКЕ ВСТАВКИ
    std::vector<std::pair<std::pair<int64_t, int64_t>, std::vector<std::pair<std::optional<std::string>, int64_t>>>> try_by_key_order_;
    std::map<std::pair<int64_t, int64_t>, std::vector<std::pair<std::optional<std::string>, int64_t>>> try_by_key_;
    std::map<int64_t, std::vector<std::pair<int64_t, int64_t>>> try_by_start_;  // start -> [(start,end), ...] порядок вставки
    std::set<int64_t> consumed_try_;

    std::vector<StackEntryPtr> loop_stack_;
    std::vector<StackEntryPtr> breakable_stack_;
    int label_ctr_ = 0;
    int64_t guard_ = 0;
    int if_chain_depth_ = 0;
    std::map<int64_t, bool> terminates_cache_;
    int catch_var_ctr_ = 0;
    std::optional<int64_t> last_try_merge_pc_;
    std::set<int64_t> all_consumed_;

    void prepare_loops();
    std::vector<MergedExc> merge_split_exception_ranges(const std::vector<ExceptionEntry>& exceptions) const;
    void prepare_try();
    void check_full_coverage(int64_t entry_pc);

    std::vector<StmtPtr> region(std::optional<int64_t> pc, const std::set<int64_t>& stop_addrs);

    // _resolve_jump_stmt: nullptr=_NO_STMT(остановиться без statement'а),
    // sentinel CONTINUE_LINEARLY возвращается отдельным флагом через out-параметр.
    enum class JumpKind { Stmt, NoStmt, ContinueLinearly };
    JumpKind resolve_jump_stmt(int64_t target, const std::set<int64_t>& stop_addrs, StmtPtr& out_stmt);
    StmtPtr try_resolve_special_target(int64_t target);
    std::string new_label();

    bool is_terminating(int64_t pc, int depth, std::set<int64_t> seen);
    std::optional<int64_t> find_forward_merge(int64_t true_t, int64_t false_t, const std::set<int64_t>& stop_addrs,
                                               bool exclude_starts = false);

    std::pair<StmtPtr, std::optional<int64_t>> build_if(int64_t pc, ExprPtr cond, int64_t true_t, int64_t false_t,
                                                          const std::set<int64_t>& stop_addrs);
    std::pair<StmtPtr, std::optional<int64_t>> build_if_inner(int64_t pc, ExprPtr cond, int64_t true_t, int64_t false_t,
                                                                const std::set<int64_t>& stop_addrs);
    std::pair<StmtPtr, std::optional<int64_t>> build_loop(int64_t header_pc, const std::set<int64_t>& stop_addrs);
    std::pair<StmtPtr, std::optional<int64_t>> build_switch(int64_t pc, ExprPtr selector, const Block& block,
                                                              const std::set<int64_t>& stop_addrs);
    std::pair<StmtPtr, std::optional<int64_t>> build_try(int64_t pc, const std::set<int64_t>& stop_addrs);
};

// ---------------- свободные функции (постобработка AST) ----------------

ExprPtr negate(const ExprPtr& cond);

// Рекурсивно упрощает: while(true)+break -> while/do-while/for, схлопывает
// временные __stkN, сворачивает if/else материализацию булевых значений в
// тернарник и т.д. - применяется к результату Structurer::build().
std::vector<StmtPtr> simplify_stmts(const std::vector<StmtPtr>& stmts);

}  // namespace nd
