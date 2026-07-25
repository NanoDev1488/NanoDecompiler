// исходный (обфусцированный) внутренний класс: jnr.ffi.NativeLong
package jnr.ffi;

import jnr.ffi.NativeLong_Cache;

public final class NativeLong extends Number implements Comparable {

    // ---- поля ----
  private static final NativeLong ZERO;
  private static final NativeLong ONE;
  private static final NativeLong MINUS_ONE;
  private final long value;

    static {
        ZERO = new NativeLong(0);
        ONE = new NativeLong(1);
        MINUS_ONE = new NativeLong(-1);
    }

  public NativeLong(long arg0) { // было: <init>
        super();
        value = arg0;
    }

  public NativeLong(int arg0) { // было: <init>
        super();
        value = ((long) arg0);
    }

  public final int intValue() {
        return ((int) value);
    }

  public final long longValue() {
        return value;
    }

  public final float floatValue() {
        return ((float) value);
    }

  public final double doubleValue() {
        return ((double) value);
    }

  public final int hashCode() {
        return ((int) (value ^ value >>> 32));
    }

  public final boolean equals(Object arg0) {
        return !(arg0 instanceof NativeLong) ? 0 : value == (((NativeLong) arg0)).value;
    }

  public String toString() {
        return String.valueOf(value);
    }

  public final int compareTo(NativeLong arg0) {
        return value >= arg0.value ? value > arg0.value : -1;
    }

  private static NativeLong _valueOf(long arg0) {
        NativeLong __stk1;
        __stk1 = arg0 < -128L ? new NativeLong(arg0) : arg0 > 127L ? new NativeLong(arg0) : NativeLong_Cache.cache[128 + ((int) arg0)];
        return ((NativeLong) __stk1);
    }

  private static NativeLong _valueOf(int arg0) {
        NativeLong __stk1;
        __stk1 = arg0 < -128 ? new NativeLong(arg0) : arg0 > 127 ? new NativeLong(arg0) : NativeLong_Cache.cache[128 + arg0];
        return ((NativeLong) __stk1);
    }

  public static NativeLong valueOf(long arg0) {
        return arg0 != 0L ? arg0 != 1L ? arg0 != -1L ? _valueOf(arg0) : MINUS_ONE : ONE : ZERO;
    }

  public static NativeLong valueOf(int arg0) {
        return arg0 != 0 ? arg0 != 1 ? arg0 != -1 ? _valueOf(arg0) : MINUS_ONE : ONE : ZERO;
    }

  public int compareTo(Object arg0) {
        return compareTo(((NativeLong) arg0));
    }

  static NativeLong access$000() {
        return ZERO;
    }

  static NativeLong access$100() {
        return ONE;
    }

  static NativeLong access$200() {
        return MINUS_ONE;
    }

}