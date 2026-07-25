// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.types.UInt64
package org.freedesktop.dbus.types;

import java.math.BigInteger;

public class UInt64 extends Number implements Comparable {

    // ---- поля ----
  public static final long MAX_LONG_VALUE = 9223372036854775807L;
  public static final BigInteger MAX_BIG_VALUE;
  public static final long MIN_VALUE = 0L;
  private static final String BOUNDS = "4294967295";
  private static final String ERROR_MSG = "%s is not between %s and %s.";
  private final BigInteger value;
  private final long top;
  private final long bottom;

    static {
        MAX_BIG_VALUE = new BigInteger("18446744073709551615");
    }

  public UInt64(long arg0) { // было: <init>
        super();
        if (arg0 < 0L) {
            throw new NumberFormatException(String.format("%s is not between %s and %s.", new Object[]{Long.valueOf(arg0), Long.valueOf(0L), Long.valueOf(9223372036854775807L)}));
        } else {
            if (arg0 <= 9223372036854775807L) {
                value = BigInteger.valueOf(arg0);
                top = value.shiftRight(32).and(new BigInteger("4294967295")).longValue();
                bottom = value.and(new BigInteger("4294967295")).longValue();
                return;
            } else {
                Object[] __obj1 = new Object[]{Long.valueOf(arg0), Long.valueOf(0L), Long.valueOf(9223372036854775807L)};
                __obj1[0] = Long.valueOf(arg0);
                __obj1[1] = Long.valueOf(0L);
                __obj1[2] = Long.valueOf(9223372036854775807L);
                throw new NumberFormatException(String.format("%s is not between %s and %s.", new Object[]{Long.valueOf(arg0), Long.valueOf(0L), Long.valueOf(9223372036854775807L)}));
            }
        }
    }

  public UInt64(long arg0, long arg1) { // было: <init>
        super();
        BigInteger var5 = BigInteger.valueOf(arg0);
        var5 = var5.shiftLeft(32);
        var5 = var5.add(BigInteger.valueOf(arg1));
        if (0 <= var5.compareTo(BigInteger.ZERO)) {
            if (0 >= var5.compareTo(MAX_BIG_VALUE)) {
                value = var5;
                top = arg0;
                bottom = arg1;
                return;
            } else {
                throw new NumberFormatException(String.format("%s is not between %s and %s.", new Object[]{var5, Long.valueOf(0L), MAX_BIG_VALUE}));
            }
        } else {
            throw new NumberFormatException(String.format("%s is not between %s and %s.", new Object[]{var5, Long.valueOf(0L), MAX_BIG_VALUE}));
        }
    }

  public UInt64(BigInteger arg0) { // было: <init>
        super();
        if (null == arg0) {
            throw new NumberFormatException(String.format("%s is not between %s and %s.", new Object[]{arg0, Long.valueOf(0L), MAX_BIG_VALUE}));
        } else {
            if (0 > arg0.compareTo(BigInteger.ZERO)) {
                Object[] __obj1 = new Object[]{arg0, Long.valueOf(0L), MAX_BIG_VALUE};
                __obj1[0] = arg0;
                __obj1[1] = Long.valueOf(0L);
                __obj1[2] = MAX_BIG_VALUE;
                throw new NumberFormatException(String.format("%s is not between %s and %s.", new Object[]{arg0, Long.valueOf(0L), MAX_BIG_VALUE}));
            } else {
                if (0 >= arg0.compareTo(MAX_BIG_VALUE)) {
                    value = arg0;
                    top = value.shiftRight(32).and(new BigInteger("4294967295")).longValue();
                    bottom = value.and(new BigInteger("4294967295")).longValue();
                    return;
                } else {
                    Object[] __obj1 = new Object[]{arg0, Long.valueOf(0L), MAX_BIG_VALUE};
                    __obj1[0] = arg0;
                    __obj1[1] = Long.valueOf(0L);
                    __obj1[2] = MAX_BIG_VALUE;
                    throw new NumberFormatException(String.format("%s is not between %s and %s.", new Object[]{arg0, Long.valueOf(0L), MAX_BIG_VALUE}));
                }
            }
        }
    }

  public UInt64(String arg0) { // было: <init>
        super();
        if (null != arg0) {
            BigInteger var2 = new BigInteger(arg0);
            if (0 > var2.compareTo(BigInteger.ZERO)) {
                throw new NumberFormatException(String.format("%s is not between %s and %s.", new Object[]{arg0, Long.valueOf(0L), MAX_BIG_VALUE}));
            } else {
                if (0 >= var2.compareTo(MAX_BIG_VALUE)) {
                    value = var2;
                    top = value.shiftRight(32).and(new BigInteger("4294967295")).longValue();
                    bottom = value.and(new BigInteger("4294967295")).longValue();
                    return;
                } else {
                    Object[] __obj2 = new Object[]{arg0, Long.valueOf(0L), MAX_BIG_VALUE};
                    __obj2[0] = arg0;
                    __obj2[1] = Long.valueOf(0L);
                    __obj2[2] = MAX_BIG_VALUE;
                    throw new NumberFormatException(String.format("%s is not between %s and %s.", new Object[]{arg0, Long.valueOf(0L), MAX_BIG_VALUE}));
                }
            }
        } else {
            throw new NumberFormatException(String.format("%s is not between %s and %s.", new Object[]{arg0, Long.valueOf(0L), MAX_BIG_VALUE}));
        }
    }

  public BigInteger value() {
        return value;
    }

  public byte byteValue() {
        return value.byteValue();
    }

  public double doubleValue() {
        return value.doubleValue();
    }

  public float floatValue() {
        return value.floatValue();
    }

  public int intValue() {
        return value.intValue();
    }

  public long longValue() {
        return value.longValue();
    }

  public short shortValue() {
        return value.shortValue();
    }

  public boolean equals(Object arg0) {
        int __stk1;
        if (!(arg0 instanceof UInt64)) {
            __stk1 = 0;
        } else {
            UInt64 var2 = ((UInt64) arg0);
            __stk1 = value.equals(var2.value);
        }
        return __stk1;
    }

  public int hashCode() {
        return value.hashCode();
    }

  public int compareTo(UInt64 arg0) {
        return value.compareTo(arg0.value);
    }

  public String toString() {
        return value.toString();
    }

  public long top() {
        return top;
    }

  public long bottom() {
        return bottom;
    }

  public int compareTo(Object arg0) {
        return compareTo(((UInt64) arg0));
    }

}