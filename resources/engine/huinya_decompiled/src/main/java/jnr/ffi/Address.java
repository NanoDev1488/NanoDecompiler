// исходный (обфусцированный) внутренний класс: jnr.ffi.Address
package jnr.ffi;

public final class Address extends Number implements Comparable {

    // ---- поля ----
  private static final Address NULL;
  private final long address;

    static {
        NULL = new Address(0L);
    }

  private Address(long arg0) { // было: <init>
        super();
        address = arg0;
    }

  public Address(Address arg0) { // было: <init>
        super();
        address = arg0.address;
    }

  public final long address() {
        return address;
    }

  public final int intValue() {
        return ((int) address);
    }

  public final long longValue() {
        return address;
    }

  public final float floatValue() {
        return ((float) address);
    }

  public final double doubleValue() {
        return ((double) address);
    }

  public final long nativeAddress() {
        return address;
    }

  public final int hashCode() {
        return ((int) (address ^ address >>> 32));
    }

  public final boolean equals(Object arg0) {
        return !(arg0 instanceof Address) ? arg0 != null ? 0 : address == 0L : address == (((Address) arg0)).address ? 1 : arg0 != null ? 0 : address == 0L;
    }

  public final String toString() {
        return Long.toString(address, 10);
    }

  public final String toHexString() {
        return Long.toString(address, 16);
    }

  public final int compareTo(Address arg0) {
        return address >= arg0.address ? address > arg0.address : -1;
    }

  public final boolean isNull() {
        return address == 0L;
    }

  public static Address valueOf(long arg0) {
        return arg0 != 0L ? new Address(arg0) : NULL;
    }

  public static Address valueOf(int arg0) {
        return arg0 != 0 ? new Address(((long) arg0) & 4294967295L) : NULL;
    }

  public int compareTo(Object arg0) {
        return compareTo(((Address) arg0));
    }

}