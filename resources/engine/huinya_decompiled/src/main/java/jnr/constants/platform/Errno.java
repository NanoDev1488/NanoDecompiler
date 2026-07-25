// исходный (обфусцированный) внутренний класс: jnr.constants.platform.Errno
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum Errno implements Constant {

    EPERM,
    ENOENT,
    ESRCH,
    EINTR,
    EIO,
    ENXIO,
    E2BIG,
    ENOEXEC,
    EBADF,
    ECHILD,
    EDEADLK,
    ENOMEM,
    EACCES,
    EFAULT,
    ENOTBLK,
    EBUSY,
    EEXIST,
    EXDEV,
    ENODEV,
    ENOTDIR,
    EISDIR,
    EINVAL,
    ENFILE,
    EMFILE,
    ENOTTY,
    ETXTBSY,
    EFBIG,
    ENOSPC,
    ESPIPE,
    EROFS,
    EMLINK,
    EPIPE,
    EDOM,
    ERANGE,
    EWOULDBLOCK,
    EAGAIN,
    EINPROGRESS,
    EALREADY,
    ENOTSOCK,
    EDESTADDRREQ,
    EMSGSIZE,
    EPROTOTYPE,
    ENOPROTOOPT,
    EPROTONOSUPPORT,
    ESOCKTNOSUPPORT,
    EOPNOTSUPP,
    EPFNOSUPPORT,
    EAFNOSUPPORT,
    EADDRINUSE,
    EADDRNOTAVAIL,
    ENETDOWN,
    ENETUNREACH,
    ENETRESET,
    ECONNABORTED,
    ECONNRESET,
    ENOBUFS,
    EISCONN,
    ENOTCONN,
    ESHUTDOWN,
    ETOOMANYREFS,
    ETIMEDOUT,
    ECONNREFUSED,
    ELOOP,
    ENAMETOOLONG,
    EHOSTDOWN,
    EHOSTUNREACH,
    ENOTEMPTY,
    EUSERS,
    EDQUOT,
    ESTALE,
    EREMOTE,
    ENOLCK,
    ENOSYS,
    EOVERFLOW,
    EIDRM,
    ENOMSG,
    EILSEQ,
    EBADMSG,
    EMULTIHOP,
    ENODATA,
    ENOLINK,
    ENOSR,
    ENOSTR,
    EPROTO,
    ETIME,
    ECHRNG,
    EL2NSYNC,
    EL3HLT,
    EL3RST,
    ELNRNG,
    EUNATCH,
    ENOCSI,
    EL2HLT,
    EBADE,
    EBADR,
    EXFULL,
    ENOANO,
    EBADRQC,
    EBADSLT,
    EDEADLOCK,
    EBFONT,
    ENONET,
    ENOPKG,
    EADV,
    ESRMNT,
    ECOMM,
    EDOTDOT,
    ENOTUNIQ,
    EBADFD,
    EREMCHG,
    ELIBACC,
    ELIBBAD,
    ELIBSCN,
    ELIBMAX,
    ELIBEXEC,
    ERESTART,
    ESTRPIPE,
    EUCLEAN,
    ENOTNAM,
    ENAVAIL,
    EISNAM,
    EREMOTEIO,
    ECANCELED,
    EKEYEXPIRED,
    EKEYREJECTED,
    EKEYREVOKED,
    EMEDIUMTYPE,
    ENOKEY,
    ENOMEDIUM,
    ENOTRECOVERABLE,
    EOWNERDEAD,
    ERFKILL,
    EAUTH,
    EBADRPC,
    EDOOFUS,
    EFTYPE,
    ENEEDAUTH,
    ENOATTR,
    ENOTSUP,
    EPROCLIM,
    EPROCUNAVAIL,
    EPROGMISMATCH,
    EPROGUNAVAIL,
    ERPCMISMATCH,
    EIPSEC,
    EHWPOISON,
    ECAPMODE,
    ENOTCAPABLE,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(Errno.class, 20000, 20999);
    }

  private Errno() { // было: <init>
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

  public static Errno valueOf(long arg0) {
        return ((Errno) resolver.valueOf(arg0));
    }

}