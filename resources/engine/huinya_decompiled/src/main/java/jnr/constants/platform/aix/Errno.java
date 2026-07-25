// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.Errno
package jnr.constants.platform.aix;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.aix.Errno_StringTable;

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
    EDEADLK(45L),
    ENOMEM(12L),
    EACCES(13L),
    EFAULT(14L),
    ENOTBLK(15L),
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
    ETXTBSY(26L),
    EFBIG(27L),
    ENOSPC(28L),
    ESPIPE(29L),
    EROFS(30L),
    EMLINK(31L),
    EPIPE(32L),
    EDOM(33L),
    ERANGE(34L),
    EWOULDBLOCK(11L),
    EAGAIN(11L),
    EINPROGRESS(55L),
    EALREADY(56L),
    ENOTSOCK(57L),
    EDESTADDRREQ(58L),
    EMSGSIZE(59L),
    EPROTOTYPE(60L),
    ENOPROTOOPT(61L),
    EPROTONOSUPPORT(62L),
    ESOCKTNOSUPPORT(63L),
    EOPNOTSUPP(64L),
    EPFNOSUPPORT(65L),
    EAFNOSUPPORT(66L),
    EADDRINUSE(67L),
    EADDRNOTAVAIL(68L),
    ENETDOWN(69L),
    ENETUNREACH(70L),
    ENETRESET(71L),
    ECONNABORTED(72L),
    ECONNRESET(73L),
    ENOBUFS(74L),
    EISCONN(75L),
    ENOTCONN(76L),
    ESHUTDOWN(77L),
    ETOOMANYREFS(115L),
    ETIMEDOUT(78L),
    ECONNREFUSED(79L),
    ELOOP(85L),
    ENAMETOOLONG(86L),
    EHOSTDOWN(80L),
    EHOSTUNREACH(81L),
    ENOTEMPTY(17L),
    EUSERS(84L),
    EDQUOT(88L),
    ESTALE(52L),
    EREMOTE(93L),
    ENOLCK(49L),
    ENOSYS(109L),
    EOVERFLOW(127L),
    EIDRM(36L),
    ENOMSG(35L),
    EILSEQ(116L),
    EBADMSG(120L),
    EMULTIHOP(125L),
    ENODATA(122L),
    ENOLINK(126L),
    ENOSR(118L),
    ENOSTR(123L),
    EPROTO(121L),
    ETIME(119L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 127L;

  private Errno(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) Errno_StringTable.descriptions.get(this));
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