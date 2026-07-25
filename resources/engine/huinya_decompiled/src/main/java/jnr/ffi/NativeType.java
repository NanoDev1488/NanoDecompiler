// исходный (обфусцированный) внутренний класс: jnr.ffi.NativeType
package jnr.ffi;

public enum NativeType {

    VOID,
    SCHAR,
    UCHAR,
    SSHORT,
    USHORT,
    SINT,
    UINT,
    SLONG,
    ULONG,
    SLONGLONG,
    ULONGLONG,
    FLOAT,
    DOUBLE,
    STRUCT,
    ADDRESS;

  private NativeType() { // было: <init>
        // (пустое тело)
    }

}