// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.types.UInt16
package org.freedesktop.dbus.types;

public class UInt16 extends Number implements Comparable {

    // ---- поля ----
  public static final int MAX_VALUE = 65535;
  public static final int MIN_VALUE = 0;
  private final int value;

  public UInt16(int arg0) { // было: <init>
        super();
        if (arg0 < 0) {
            throw new NumberFormatException(String.format("%s is not between %s and %s.", new Object[]{Integer.valueOf(arg0), Integer.valueOf(0), Integer.valueOf(65535)}));
        } else {
            if (arg0 <= 65535) {
                value = arg0;
                return;
            } else {
                Object[] __obj1 = new Object[]{Integer.valueOf(arg0), Integer.valueOf(0), Integer.valueOf(65535)};
                __obj1[0] = Integer.valueOf(arg0);
                __obj1[1] = Integer.valueOf(0);
                __obj1[2] = Integer.valueOf(65535);
                throw new NumberFormatException(String.format("%s is not between %s and %s.", new Object[]{Integer.valueOf(arg0), Integer.valueOf(0), Integer.valueOf(65535)}));
            }
        }
    }

  public UInt16(String arg0) { // было: <init>
        this(Integer.parseInt(arg0));
    }

  public byte byteValue() {
        return ((byte) value);
    }

  public double doubleValue() {
        return ((double) value);
    }

  public float floatValue() {
        return ((float) value);
    }

  public int intValue() {
        return value;
    }

  public long longValue() {
        return ((long) value);
    }

  public short shortValue() {
        return ((short) value);
    }

  public boolean equals(Object arg0) {
        int __stk1;
        if (!(arg0 instanceof UInt16)) {
            __stk1 = 0;
        } else {
            UInt16 var2 = ((UInt16) arg0);
            __stk1 = var2.value == value;
        }
        return __stk1;
    }

  public int hashCode() {
        return value;
    }

  public int compareTo(UInt16 arg0) {
        return Integer.compare(value, arg0.value);
    }

  public String toString() {
        return String.valueOf(value);
    }

  public int compareTo(Object arg0) {
        return compareTo(((UInt16) arg0));
    }

}