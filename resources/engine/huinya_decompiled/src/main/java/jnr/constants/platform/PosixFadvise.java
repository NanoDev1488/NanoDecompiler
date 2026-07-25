// исходный (обфусцированный) внутренний класс: jnr.constants.platform.PosixFadvise
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum PosixFadvise implements Constant {

    POSIX_FADV_NORMAL,
    POSIX_FADV_SEQUENTIAL,
    POSIX_FADV_RANDOM,
    POSIX_FADV_NOREUSE,
    POSIX_FADV_WILLNEED,
    POSIX_FADV_DONTNEED,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(PosixFadvise.class, 20000, 29999);
    }

  private PosixFadvise() { // было: <init>
        // (пустое тело)
    }

  public final int value() {
        return ((int) resolver.longValue(this));
    }

  public final int intValue() {
        return ((int) resolver.longValue(this));
    }

  public final long longValue() {
        return resolver.longValue(this);
    }

  public final String description() {
        return resolver.description(this);
    }

  public final boolean defined() {
        return resolver.defined(this);
    }

  public final String toString() {
        return description();
    }

  public static PosixFadvise valueOf(long arg0) {
        return ((PosixFadvise) resolver.valueOf(arg0));
    }

}