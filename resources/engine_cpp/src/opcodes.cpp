// opcodes.cpp - см. opcodes.hpp. 1:1 порт таблицы из opcodes.py.
#include "opcodes.hpp"

namespace nd {

using OK = OperandKind;

std::string operand_kind_name(OperandKind k) {
    switch (k) {
        case OK::None: return "none";
        case OK::Byte: return "byte";
        case OK::Short: return "short";
        case OK::UByteCp: return "ubyte_cp";
        case OK::UShortCp: return "ushort_cp";
        case OK::LocalUByte: return "local_ubyte";
        case OK::IInc: return "iinc";
        case OK::Branch2: return "branch2";
        case OK::Branch4: return "branch4";
        case OK::AType: return "atype";
        case OK::InvokeInterface: return "invokeinterface";
        case OK::InvokeDynamic: return "invokedynamic";
        case OK::MultiANewArray: return "multianewarray";
        case OK::TableSwitch: return "tableswitch";
        case OK::LookupSwitch: return "lookupswitch";
        case OK::Wide: return "wide";
    }
    return "none";
}

namespace {
// Строится через макрос-таблицу для наглядности 1:1 сверки с opcodes.py
// (тот же порядок 0x00..0xC9, тот же mnemonic/operand_kind на каждой строке).
constexpr std::array<OpcodeInfo, 256> build_table() {
    std::array<OpcodeInfo, 256> t{};
    for (auto& e : t) e = {nullptr, OK::None};
    t[0x00] = {"nop", OK::None};
    t[0x01] = {"aconst_null", OK::None};
    t[0x02] = {"iconst_m1", OK::None};
    t[0x03] = {"iconst_0", OK::None};
    t[0x04] = {"iconst_1", OK::None};
    t[0x05] = {"iconst_2", OK::None};
    t[0x06] = {"iconst_3", OK::None};
    t[0x07] = {"iconst_4", OK::None};
    t[0x08] = {"iconst_5", OK::None};
    t[0x09] = {"lconst_0", OK::None};
    t[0x0a] = {"lconst_1", OK::None};
    t[0x0b] = {"fconst_0", OK::None};
    t[0x0c] = {"fconst_1", OK::None};
    t[0x0d] = {"fconst_2", OK::None};
    t[0x0e] = {"dconst_0", OK::None};
    t[0x0f] = {"dconst_1", OK::None};
    t[0x10] = {"bipush", OK::Byte};
    t[0x11] = {"sipush", OK::Short};
    t[0x12] = {"ldc", OK::UByteCp};
    t[0x13] = {"ldc_w", OK::UShortCp};
    t[0x14] = {"ldc2_w", OK::UShortCp};
    t[0x15] = {"iload", OK::LocalUByte};
    t[0x16] = {"lload", OK::LocalUByte};
    t[0x17] = {"fload", OK::LocalUByte};
    t[0x18] = {"dload", OK::LocalUByte};
    t[0x19] = {"aload", OK::LocalUByte};
    t[0x1a] = {"iload_0", OK::None};
    t[0x1b] = {"iload_1", OK::None};
    t[0x1c] = {"iload_2", OK::None};
    t[0x1d] = {"iload_3", OK::None};
    t[0x1e] = {"lload_0", OK::None};
    t[0x1f] = {"lload_1", OK::None};
    t[0x20] = {"lload_2", OK::None};
    t[0x21] = {"lload_3", OK::None};
    t[0x22] = {"fload_0", OK::None};
    t[0x23] = {"fload_1", OK::None};
    t[0x24] = {"fload_2", OK::None};
    t[0x25] = {"fload_3", OK::None};
    t[0x26] = {"dload_0", OK::None};
    t[0x27] = {"dload_1", OK::None};
    t[0x28] = {"dload_2", OK::None};
    t[0x29] = {"dload_3", OK::None};
    t[0x2a] = {"aload_0", OK::None};
    t[0x2b] = {"aload_1", OK::None};
    t[0x2c] = {"aload_2", OK::None};
    t[0x2d] = {"aload_3", OK::None};
    t[0x2e] = {"iaload", OK::None};
    t[0x2f] = {"laload", OK::None};
    t[0x30] = {"faload", OK::None};
    t[0x31] = {"daload", OK::None};
    t[0x32] = {"aaload", OK::None};
    t[0x33] = {"baload", OK::None};
    t[0x34] = {"caload", OK::None};
    t[0x35] = {"saload", OK::None};
    t[0x36] = {"istore", OK::LocalUByte};
    t[0x37] = {"lstore", OK::LocalUByte};
    t[0x38] = {"fstore", OK::LocalUByte};
    t[0x39] = {"dstore", OK::LocalUByte};
    t[0x3a] = {"astore", OK::LocalUByte};
    t[0x3b] = {"istore_0", OK::None};
    t[0x3c] = {"istore_1", OK::None};
    t[0x3d] = {"istore_2", OK::None};
    t[0x3e] = {"istore_3", OK::None};
    t[0x3f] = {"lstore_0", OK::None};
    t[0x40] = {"lstore_1", OK::None};
    t[0x41] = {"lstore_2", OK::None};
    t[0x42] = {"lstore_3", OK::None};
    t[0x43] = {"fstore_0", OK::None};
    t[0x44] = {"fstore_1", OK::None};
    t[0x45] = {"fstore_2", OK::None};
    t[0x46] = {"fstore_3", OK::None};
    t[0x47] = {"dstore_0", OK::None};
    t[0x48] = {"dstore_1", OK::None};
    t[0x49] = {"dstore_2", OK::None};
    t[0x4a] = {"dstore_3", OK::None};
    t[0x4b] = {"astore_0", OK::None};
    t[0x4c] = {"astore_1", OK::None};
    t[0x4d] = {"astore_2", OK::None};
    t[0x4e] = {"astore_3", OK::None};
    t[0x4f] = {"iastore", OK::None};
    t[0x50] = {"lastore", OK::None};
    t[0x51] = {"fastore", OK::None};
    t[0x52] = {"dastore", OK::None};
    t[0x53] = {"aastore", OK::None};
    t[0x54] = {"bastore", OK::None};
    t[0x55] = {"castore", OK::None};
    t[0x56] = {"sastore", OK::None};
    t[0x57] = {"pop", OK::None};
    t[0x58] = {"pop2", OK::None};
    t[0x59] = {"dup", OK::None};
    t[0x5a] = {"dup_x1", OK::None};
    t[0x5b] = {"dup_x2", OK::None};
    t[0x5c] = {"dup2", OK::None};
    t[0x5d] = {"dup2_x1", OK::None};
    t[0x5e] = {"dup2_x2", OK::None};
    t[0x5f] = {"swap", OK::None};
    t[0x60] = {"iadd", OK::None};
    t[0x61] = {"ladd", OK::None};
    t[0x62] = {"fadd", OK::None};
    t[0x63] = {"dadd", OK::None};
    t[0x64] = {"isub", OK::None};
    t[0x65] = {"lsub", OK::None};
    t[0x66] = {"fsub", OK::None};
    t[0x67] = {"dsub", OK::None};
    t[0x68] = {"imul", OK::None};
    t[0x69] = {"lmul", OK::None};
    t[0x6a] = {"fmul", OK::None};
    t[0x6b] = {"dmul", OK::None};
    t[0x6c] = {"idiv", OK::None};
    t[0x6d] = {"ldiv", OK::None};
    t[0x6e] = {"fdiv", OK::None};
    t[0x6f] = {"ddiv", OK::None};
    t[0x70] = {"irem", OK::None};
    t[0x71] = {"lrem", OK::None};
    t[0x72] = {"frem", OK::None};
    t[0x73] = {"drem", OK::None};
    t[0x74] = {"ineg", OK::None};
    t[0x75] = {"lneg", OK::None};
    t[0x76] = {"fneg", OK::None};
    t[0x77] = {"dneg", OK::None};
    t[0x78] = {"ishl", OK::None};
    t[0x79] = {"lshl", OK::None};
    t[0x7a] = {"ishr", OK::None};
    t[0x7b] = {"lshr", OK::None};
    t[0x7c] = {"iushr", OK::None};
    t[0x7d] = {"lushr", OK::None};
    t[0x7e] = {"iand", OK::None};
    t[0x7f] = {"land", OK::None};
    t[0x80] = {"ior", OK::None};
    t[0x81] = {"lor", OK::None};
    t[0x82] = {"ixor", OK::None};
    t[0x83] = {"lxor", OK::None};
    t[0x84] = {"iinc", OK::IInc};
    t[0x85] = {"i2l", OK::None};
    t[0x86] = {"i2f", OK::None};
    t[0x87] = {"i2d", OK::None};
    t[0x88] = {"l2i", OK::None};
    t[0x89] = {"l2f", OK::None};
    t[0x8a] = {"l2d", OK::None};
    t[0x8b] = {"f2i", OK::None};
    t[0x8c] = {"f2l", OK::None};
    t[0x8d] = {"f2d", OK::None};
    t[0x8e] = {"d2i", OK::None};
    t[0x8f] = {"d2l", OK::None};
    t[0x90] = {"d2f", OK::None};
    t[0x91] = {"i2b", OK::None};
    t[0x92] = {"i2c", OK::None};
    t[0x93] = {"i2s", OK::None};
    t[0x94] = {"lcmp", OK::None};
    t[0x95] = {"fcmpl", OK::None};
    t[0x96] = {"fcmpg", OK::None};
    t[0x97] = {"dcmpl", OK::None};
    t[0x98] = {"dcmpg", OK::None};
    t[0x99] = {"ifeq", OK::Branch2};
    t[0x9a] = {"ifne", OK::Branch2};
    t[0x9b] = {"iflt", OK::Branch2};
    t[0x9c] = {"ifge", OK::Branch2};
    t[0x9d] = {"ifgt", OK::Branch2};
    t[0x9e] = {"ifle", OK::Branch2};
    t[0x9f] = {"if_icmpeq", OK::Branch2};
    t[0xa0] = {"if_icmpne", OK::Branch2};
    t[0xa1] = {"if_icmplt", OK::Branch2};
    t[0xa2] = {"if_icmpge", OK::Branch2};
    t[0xa3] = {"if_icmpgt", OK::Branch2};
    t[0xa4] = {"if_icmple", OK::Branch2};
    t[0xa5] = {"if_acmpeq", OK::Branch2};
    t[0xa6] = {"if_acmpne", OK::Branch2};
    t[0xa7] = {"goto", OK::Branch2};
    t[0xa8] = {"jsr", OK::Branch2};
    t[0xa9] = {"ret", OK::LocalUByte};
    t[0xaa] = {"tableswitch", OK::TableSwitch};
    t[0xab] = {"lookupswitch", OK::LookupSwitch};
    t[0xac] = {"ireturn", OK::None};
    t[0xad] = {"lreturn", OK::None};
    t[0xae] = {"freturn", OK::None};
    t[0xaf] = {"dreturn", OK::None};
    t[0xb0] = {"areturn", OK::None};
    t[0xb1] = {"return", OK::None};
    t[0xb2] = {"getstatic", OK::UShortCp};
    t[0xb3] = {"putstatic", OK::UShortCp};
    t[0xb4] = {"getfield", OK::UShortCp};
    t[0xb5] = {"putfield", OK::UShortCp};
    t[0xb6] = {"invokevirtual", OK::UShortCp};
    t[0xb7] = {"invokespecial", OK::UShortCp};
    t[0xb8] = {"invokestatic", OK::UShortCp};
    t[0xb9] = {"invokeinterface", OK::InvokeInterface};
    t[0xba] = {"invokedynamic", OK::InvokeDynamic};
    t[0xbb] = {"new", OK::UShortCp};
    t[0xbc] = {"newarray", OK::AType};
    t[0xbd] = {"anewarray", OK::UShortCp};
    t[0xbe] = {"arraylength", OK::None};
    t[0xbf] = {"athrow", OK::None};
    t[0xc0] = {"checkcast", OK::UShortCp};
    t[0xc1] = {"instanceof", OK::UShortCp};
    t[0xc2] = {"monitorenter", OK::None};
    t[0xc3] = {"monitorexit", OK::None};
    t[0xc4] = {"wide", OK::Wide};
    t[0xc5] = {"multianewarray", OK::MultiANewArray};
    t[0xc6] = {"ifnull", OK::Branch2};
    t[0xc7] = {"ifnonnull", OK::Branch2};
    t[0xc8] = {"goto_w", OK::Branch4};
    t[0xc9] = {"jsr_w", OK::Branch4};
    return t;
}
}  // namespace

const std::array<OpcodeInfo, 256> OPCODES = build_table();

const std::map<int, std::string> NEWARRAY_TYPES = {
    {4, "boolean"}, {5, "char"}, {6, "float"}, {7, "double"},
    {8, "byte"}, {9, "short"}, {10, "int"}, {11, "long"},
};

}  // namespace nd
