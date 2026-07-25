// исходный (обфусцированный) внутренний класс: jnr.constants.platform.openbsd.Errno
package jnr.constants.platform.openbsd;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.openbsd.Errno_StringTable;

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
    EDEADLK(11L),
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
    EWOULDBLOCK(35L),
    EAGAIN(35L),
    EINPROGRESS(36L),
    EALREADY(37L),
    ENOTSOCK(38L),
    EDESTADDRREQ(39L),
    EMSGSIZE(40L),
    EPROTOTYPE(41L),
    ENOPROTOOPT(42L),
    EPROTONOSUPPORT(43L),
    ESOCKTNOSUPPORT(44L),
    EOPNOTSUPP(45L),
    EPFNOSUPPORT(46L),
    EAFNOSUPPORT(47L),
    EADDRINUSE(48L),
    EADDRNOTAVAIL(49L),
    ENETDOWN(50L),
    ENETUNREACH(51L),
    ENETRESET(52L),
    ECONNABORTED(53L),
    ECONNRESET(54L),
    ENOBUFS(55L),
    EISCONN(56L),
    ENOTCONN(57L),
    ESHUTDOWN(58L),
    ETOOMANYREFS(59L),
    ETIMEDOUT(60L),
    ECONNREFUSED(61L),
    ELOOP(62L),
    ENAMETOOLONG(63L),
    EHOSTDOWN(64L),
    EHOSTUNREACH(65L),
    ENOTEMPTY(66L),
    EUSERS(68L),
    EDQUOT(69L),
    ESTALE(70L),
    EREMOTE(71L),
    ENOLCK(77L),
    ENOSYS(78L),
    EOVERFLOW(87L),
    EIDRM(89L),
    ENOMSG(90L),
    EILSEQ(84L),
    EBADMSG(92L),
    EPROTO(95L),
    ECANCELED(88L),
    EMEDIUMTYPE(86L),
    ENOMEDIUM(85L),
    ENOTRECOVERABLE(93L),
    EOWNERDEAD(94L),
    EAUTH(80L),
    EBADRPC(72L),
    EFTYPE(79L),
    ENEEDAUTH(81L),
    ENOATTR(83L),
    ENOTSUP(91L),
    EPROCLIM(67L),
    EPROCUNAVAIL(76L),
    EPROGMISMATCH(75L),
    EPROGUNAVAIL(74L),
    ERPCMISMATCH(73L),
    EIPSEC(82L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 95L;

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