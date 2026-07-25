// исходный (обфусцированный) внутренний класс: com.kenai.jnr.x86asm.Util
package com.kenai.jnr.x86asm;

@Deprecated
public final class Util {

  private Util() { // было: <init>
        super();
    }

  static final boolean isInt8(long arg0) {
        return arg0 < -128L ? 0 : arg0 <= 127L;
    }

  static final boolean isUInt8(long arg0) {
        return arg0 < 0L ? 0 : arg0 <= 255L;
    }

  static final boolean isInt16(long arg0) {
        return arg0 < -32768L ? 0 : arg0 <= 32767L;
    }

  static final boolean isUInt16(long arg0) {
        return arg0 < 0L ? 0 : arg0 <= 65535L;
    }

  static final boolean isInt32(long arg0) {
        return arg0 < -2147483648L ? 0 : arg0 <= 2147483647L;
    }

  static final boolean isUInt32(long arg0) {
        return arg0 < 0L ? 0 : arg0 <= 4294967295L;
    }

}