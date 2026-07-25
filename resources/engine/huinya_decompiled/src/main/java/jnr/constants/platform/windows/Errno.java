// исходный (обфусцированный) внутренний класс: jnr.constants.platform.windows.Errno
package jnr.constants.platform.windows;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.windows.Errno_StringTable;

public enum Errno implements Constant {

    EPERM(1L),
    ENOENT(2L),
    ESRCH(3L),
    EINTR(4L),
    EIO(5L),
    ENXIO(6L),
    E2BIG(7L),
    ENOEXEC(8L),
    EBADF(9L),
    ECHILD(10L),
    EDEADLK(36L),
    ENOMEM(12L),
    EACCES(13L),
    EFAULT(14L),
    EBUSY(16L),
    EEXIST(17L),
    EXDEV(18L),
    ENODEV(19L),
    ENOTDIR(20L),
    EISDIR(21L),
    EINVAL(22L),
    ENFILE(23L),
    EMFILE(24L),
    ENOTTY(25L),
    ETXTBSY(139L),
    EFBIG(27L),
    ENOSPC(28L),
    ESPIPE(29L),
    EROFS(30L),
    EMLINK(31L),
    EPIPE(32L),
    EDOM(33L),
    ERANGE(34L),
    EWOULDBLOCK(140L),
    EAGAIN(11L),
    EINPROGRESS(112L),
    EALREADY(103L),
    ENOTSOCK(128L),
    EDESTADDRREQ(109L),
    EMSGSIZE(115L),
    EPROTOTYPE(136L),
    ENOPROTOOPT(123L),
    EPROTONOSUPPORT(135L),
    EOPNOTSUPP(130L),
    EAFNOSUPPORT(102L),
    EADDRINUSE(100L),
    EADDRNOTAVAIL(101L),
    ENETDOWN(116L),
    ENETUNREACH(118L),
    ENETRESET(117L),
    ECONNABORTED(106L),
    ECONNRESET(108L),
    ENOBUFS(119L),
    EISCONN(113L),
    ENOTCONN(126L),
    ETIMEDOUT(138L),
    ECONNREFUSED(107L),
    ELOOP(114L),
    ENAMETOOLONG(38L),
    EHOSTUNREACH(110L),
    ENOTEMPTY(41L),
    ENOLCK(39L),
    ENOSYS(40L),
    EOVERFLOW(132L),
    EIDRM(111L),
    ENOMSG(122L),
    EILSEQ(42L),
    EBADMSG(104L),
    ENODATA(120L),
    ENOLINK(121L),
    ENOSR(124L),
    ENOSTR(125L),
    EPROTO(134L),
    ETIME(137L),
    EDEADLOCK(36L),
    ECANCELED(105L),
    ENOTRECOVERABLE(127L),
    EOWNERDEAD(133L),
    ENOTSUP(129L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 140L;

  private Errno(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) Errno_StringTable.descriptions.get(this));
    }

  public final int value() {
        return ((int) value);
    }

  public final int intValue() {
        return ((int) value);
    }

  public final long longValue() {
        return value;
    }

  public final boolean defined() {
        return true;
    }

}