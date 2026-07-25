// исходный (обфусцированный) внутренний класс: jnr.constants.platform.Fcntl
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum Fcntl implements Constant {

    FAPPEND,
    FREAD,
    FWRITE,
    FASYNC,
    FFSYNC,
    FNONBLOCK,
    FNDELAY,
    F_DUPFD,
    F_GETFD,
    F_SETFD,
    F_GETFL,
    F_SETFL,
    F_GETOWN,
    F_SETOWN,
    F_GETLK,
    F_SETLK,
    F_SETLKW,
    F_CHKCLEAN,
    F_PREALLOCATE,
    F_SETSIZE,
    F_RDADVISE,
    F_RDAHEAD,
    F_READBOOTSTRAP,
    F_WRITEBOOTSTRAP,
    F_NOCACHE,
    F_LOG2PHYS,
    F_GETPATH,
    F_FULLFSYNC,
    F_PATHPKG_CHECK,
    F_FREEZE_FS,
    F_THAW_FS,
    F_GLOBAL_NOCACHE,
    F_ADDSIGS,
    F_MARKDEPENDENCY,
    F_RDLCK,
    F_UNLCK,
    F_WRLCK,
    F_ALLOCATECONTIG,
    F_ALLOCATEALL,
    F_GETPIPE_SZ,
    F_SETPIPE_SZ,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(Fcntl.class, 20000, 20999);
    }

  private Fcntl() { // было: <init>
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

  public static Fcntl valueOf(long arg0) {
        return ((Fcntl) resolver.valueOf(arg0));
    }

}