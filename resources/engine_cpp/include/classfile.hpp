// classfile.hpp
// Порт resources/engine/classfile.py на C++ (v2.0, HANDOFF_24, модуль 1/N).
// Ручной парсер .class файлов JVM: constant pool, поля, методы, атрибуты Code.
// Семантика ДОЛЖНА совпадать 1:1 с Python-оригиналом - при любом расхождении
// поведения это баг порта, а не "улучшение". См. HANDOFF_24_CPP_PORT_MODULE1_CLASSFILE.md.
#pragma once

#include <cstdint>
#include <map>
#include <memory>
#include <optional>
#include <stdexcept>
#include <string>
#include <variant>
#include <vector>

namespace nd {

// ---------- бинарный ридер (аналог Reader в classfile.py) ----------

class ClassFormatError : public std::runtime_error {
public:
    explicit ClassFormatError(const std::string& msg) : std::runtime_error(msg) {}
};

class Reader {
public:
    explicit Reader(const std::vector<uint8_t>& data, size_t start = 0)
        : data_(data), pos_(start) {}

    uint8_t u1();
    uint16_t u2();
    uint32_t u4();
    int8_t s1();
    int16_t s2();
    int32_t s4();
    std::vector<uint8_t> bytes(size_t n);
    void skip(size_t n);
    size_t pos() const { return pos_; }
    size_t size() const { return data_.size(); }

private:
    const std::vector<uint8_t>& data_;
    size_t pos_;
};

// ---------- constant pool ----------

enum class CpTag {
    Utf8, Integer, Float, Long, Double, Class, String, Fieldref, Methodref,
    InterfaceMethodref, NameAndType, MethodHandle, MethodType, Dynamic,
    InvokeDynamic, Module, Package, Unknown
};

std::string cp_tag_name(CpTag t);

// Одна запись constant pool. Поля используются по-разному в зависимости от tag,
// зеркалит tuple-представление в Python (("Tag", a, b, ...)).
struct CpEntry {
    CpTag tag = CpTag::Unknown;
    // Utf8
    std::string utf8_value;
    // Integer/Long (Long хранится как int64_t)
    int64_t int_value = 0;
    // Float/Double
    double float_value = 0.0;
    // Class: name_idx ; String: string_idx ; MethodType: desc_idx ; Module/Package: name_idx
    uint16_t idx1 = 0;
    // Fieldref/Methodref/InterfaceMethodref: class_idx, nt_idx
    // NameAndType: name_idx, type_idx
    // MethodHandle: (kind stored in idx1 as u1 widened), ref_idx in idx2
    // Dynamic/InvokeDynamic: bsm_idx, nt_idx
    uint16_t idx2 = 0;
};

// ---------- element_value для аннотаций (JVM spec 4.7.16.1) ----------
// Python использует "естественные" значения (bool/int/float/str/dict/list/None).
// Здесь - размеченный вариант, structurally эквивалентный.

struct AnnotationValue;
using AnnotationValuePtr = std::shared_ptr<AnnotationValue>;

struct Annotation {
    std::string type;  // field_descriptor, напр. "Lorg/jetbrains/annotations/NotNull;"
    std::vector<std::pair<std::string, AnnotationValuePtr>> args;  // сохраняем порядок, как Python dict (3.7+)
};

struct AnnotationValue {
    enum class Kind { None, Bool, Int, Char, Float, Double, Long, Str, Annotation, Array } kind = Kind::None;
    bool bool_v = false;
    int64_t int_v = 0;
    double dbl_v = 0.0;
    std::string str_v;
    std::shared_ptr<Annotation> ann_v;
    std::vector<AnnotationValuePtr> arr_v;
};

// ---------- Field / Method / ExceptionEntry ----------

struct ExceptionEntry {
    uint16_t start_pc = 0, end_pc = 0, handler_pc = 0;
    std::optional<std::string> catch_type;  // None == catch-all (finally)
};

struct LocalVarEntry {
    uint16_t start_pc = 0, length = 0, slot = 0;
    std::string name;
    std::string descriptor;
};

struct Field {
    uint16_t access = 0;
    std::string name;
    std::string descriptor;
    std::optional<CpEntry> constant_value;  // ConstantValue attr, если есть
    std::vector<Annotation> annotations;
    std::optional<std::string> signature;
};

struct Method {
    uint16_t access = 0;
    std::string name;
    std::string descriptor;
    bool has_code = false;
    std::vector<uint8_t> code;
    uint16_t max_stack = 0, max_locals = 0;
    std::vector<ExceptionEntry> exceptions;
    std::vector<LocalVarEntry> local_var_table;
    std::vector<Annotation> annotations;
    std::vector<std::vector<Annotation>> param_annotations;
    std::optional<std::string> signature;
};

struct InnerClassEntry {
    std::optional<std::string> inner;
    std::optional<std::string> outer;
    std::optional<std::string> inner_name;
    uint16_t access = 0;
};

// HANDOFF_50: JVM spec 4.7.30 (JDK 16+, атрибут Record класса) - компонент
// record'а в порядке объявления. Используется render_class.cpp, чтобы
// печатать `record Name(Type comp1, Type comp2) { }` вместо
// `class Name extends Record { ... }`.
struct RecordComponent {
    std::string name;
    std::string descriptor;
};

struct BootstrapMethod {
    uint16_t method_handle_idx = 0;
    std::vector<uint16_t> args;
};

// ---------- ClassFile ----------

class ClassFile {
public:
    explicit ClassFile(const std::vector<uint8_t>& data);

    // constant pool resolution helpers (зеркалят одноимённые методы Python)
    const std::string* utf8(uint16_t idx) const;
    std::optional<std::string> class_name(uint16_t idx) const;
    std::optional<std::pair<std::string, std::string>> name_and_type(uint16_t idx) const;
    // (owner_internal, name, desc)
    std::optional<std::tuple<std::string, std::string, std::string>> ref_string(uint16_t idx) const;
    std::string describe_cp(uint16_t idx) const;

    // MethodHandle cp entry -> (kind, owner_internal, name, desc).
    std::optional<std::tuple<int, std::string, std::string, std::string>> method_handle_ref(uint16_t mh_cp_index) const;

    uint16_t minor = 0, major = 0;
    uint16_t access = 0;
    std::string this_class_name;
    std::optional<std::string> super_class_name;
    std::vector<std::optional<std::string>> interfaces;
    std::vector<Field> fields;
    std::vector<Method> methods;
    std::optional<std::string> source_file;
    std::vector<Annotation> annotations;
    std::optional<std::string> signature;
    std::vector<BootstrapMethod> bootstrap_methods;
    std::vector<InnerClassEntry> inner_classes;
    // Непусто <=> класс - настоящий record (JDK 16+) - см. RecordComponent.
    std::vector<RecordComponent> record_components;

    std::map<uint16_t, CpEntry> pool;

private:
    void parse(const std::vector<uint8_t>& data);
    void parse_code(Method& m, const std::vector<uint8_t>& a_data);
    Annotation parse_annotation(Reader& r);
    AnnotationValuePtr parse_element_value(Reader& r);
    std::vector<Annotation> parse_annotations_attr(const std::vector<uint8_t>& a_data);
    std::vector<std::vector<Annotation>> parse_param_annotations_attr(const std::vector<uint8_t>& a_data);
};

std::string access_str(uint16_t flags, const std::string& kind = "class");

}  // namespace nd
