// исходный (обфусцированный) внутренний класс: com.kenai.jffi.NativeType
package com.kenai.jffi;

public enum NativeType {

    VOID(0),
    FLOAT(2),
    DOUBLE(3),
    LONGDOUBLE(4),
    UINT8(5),
    SINT8(6),
    UINT16(7),
    SINT16(8),
    UINT32(9),
    SINT32(10),
    UINT64(11),
    SINT64(12),
    POINTER(14),
    UCHAR(101),
    SCHAR(102),
    USHORT(103),
    SSHORT(104),
    UINT(105),
    SINT(106),
    ULONG(107),
    SLONG(108),
    STRUCT(13);

    // ---- поля ----
  final int ffiType;

  private NativeType(int arg2) { // было: <init>
        ffiType = arg2;
    }

}