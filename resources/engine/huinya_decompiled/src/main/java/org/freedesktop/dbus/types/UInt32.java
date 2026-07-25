// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.types.UInt32
package org.freedesktop.dbus.types;

public class UInt32 extends Number implements Comparable {

    // ---- поля ----
  public static final long MAX_VALUE = 4294967295L;
  public static final long MIN_VALUE = 0L;
  private final long value;

  public UInt32(long arg0) { // было: <init>
        super();
        if (arg0 < 0L) {
            throw new NumberFormatException(String.format("%s is not between %s and %s.", new Object[]{Long.valueOf(arg0), Long.valueOf(0L), Long.valueOf(4294967295L)}));
        } else {
            if (arg0 <= 4294967295L) {
                value = arg0;
                return;
            } else {
                Object[] __obj1 = new Object[]{Long.valueOf(arg0), Long.valueOf(0L), Long.valueOf(4294967295L)};
                __obj1[0] = Long.valueOf(arg0);
                __obj1[1] = Long.valueOf(0L);
                __obj1[2] = Long.valueOf(4294967295L);
                throw new NumberFormatException(String.format("%s is not between %s and %s.", new Object[]{Long.valueOf(arg0), Long.valueOf(0L), Long.valueOf(4294967295L)}));
            }
        }
    }

  public UInt32(String arg0) { // было: <init>
        this(Long.parseLong(arg0));
    }

  public byte byteValue() {
        return ((byte) ((int) value));
    }

  public double doubleValue() {
        return ((double) value);
    }

  public float floatValue() {
        return ((float) value);
    }

  public int intValue() {
        return ((int) value);
    }

  public long longValue() {
        return value;
    }

  public short shortValue() {
        return ((short) ((int) value));
    }

  public boolean equals(Object arg0) {
        int __stk1;
        if (!(arg0 instanceof UInt32)) {
            __stk1 = 0;
        } else {
            UInt32 var2 = ((UInt32) arg0);
            __stk1 = var2.value == value;
        }
        return __stk1;
    }

  public int hashCode() {
        return ((int) value);
    }

  public int compareTo(UInt32 arg0) {
        return Long.compare(value, arg0.value);
    }

  public String toString() {
        return String.valueOf(value);
    }

  public int compareTo(Object arg0) {
        return compareTo(((UInt32) arg0));
    }

}