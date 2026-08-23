// cfg.hpp - порт resources/engine/cfg.py (v2.0, HANDOFF_28, модуль 5).
// Построение CFG (базовые блоки, рёбра), дерево доминаторов (итеративный
// Cooper/Harvey/Kennedy), естественные циклы, постдоминаторы.
#pragma once

#include <map>
#include <optional>
#include <set>
#include <string>
#include <tuple>
#include <vector>

#include "classfile.hpp"
#include "ir.hpp"

namespace nd {

struct Block {
    int64_t start = 0;
    int64_t end = 0;                 // pc ПОСЛЕ последней инструкции блока (exclusive)
    std::vector<Instruction> instrs;
    std::vector<int64_t> succs;      // порядок ВАЖЕН - зеркалит Python list, не set
    std::vector<int64_t> preds;      // порядок ВАЖЕН
    std::optional<int64_t> idom;
    // если это начало exception-handler'а: [(catch_type, ExceptionEntry), ...]
    // ВНИМАНИЕ: как и в оригинале, если try-диапазон охватывает НЕСКОЛЬКО
    // блоков, сюда добавляется ПО ОДНОЙ (дублирующейся) записи НА КАЖДЫЙ
    // покрывающий блок - см. примечание "найденная особенность" в HANDOFF_28.
    std::vector<std::pair<std::optional<std::string>, ExceptionEntry>> handler_types;
};

class CFG {
public:
    CFG(const std::map<size_t, Instruction>& instrs, const std::vector<size_t>& order,
        const std::vector<ExceptionEntry>& exceptions);

    std::map<int64_t, Block> blocks;
    std::optional<int64_t> entry;

    bool dominates(int64_t a, int64_t b) const;
    // (header, body, tails) - body/tails как множества (порядок печати при
    // сверке - сортированный, сами по себе это Python `set`, порядок не
    // семантически значим); порядок ЭЛЕМЕНТОВ САМОГО СПИСКА result -
    // значим и воспроизведён точно (см. .cpp).
    std::vector<std::tuple<int64_t, std::set<int64_t>, std::set<int64_t>>> natural_loops() const;
    std::vector<int64_t> reverse_postorder_list() const;
    // ipdom[b] = ближайший постдоминатор b, либо nullopt (нет общей точки
    // схождения - все пути из b завершаются return/throw).
    std::map<int64_t, std::optional<int64_t>> compute_postdominators();

private:
    std::map<int64_t, Instruction> instrs_;
    std::vector<int64_t> order_;
    std::vector<ExceptionEntry> exceptions_;
    std::map<int64_t, int64_t> idom_;  // внутренняя рабочая карта _compute_dominators (только присутствующие ключи)

    std::set<int64_t> leaders() const;
    void build();
    void compute_dominators();
    std::vector<int64_t> reverse_postorder() const;
};

}  // namespace nd
