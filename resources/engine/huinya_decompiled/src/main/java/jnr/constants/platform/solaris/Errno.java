// исходный (обфусцированный) внутренний класс: jnr.constants.platform.solaris.Errno
package jnr.constants.platform.solaris;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.solaris.Errno_StringTable;

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
    EINPROGRESS(150L),
    EALREADY(149L),
    ENOTSOCK(95L),
    EDESTADDRREQ(96L),
    EMSGSIZE(97L),
    EPROTOTYPE(98L),
    ENOPROTOOPT(99L),
    EPROTONOSUPPORT(120L),
    ESOCKTNOSUPPORT(121L),
    EOPNOTSUPP(122L),
    EPFNOSUPPORT(123L),
    EAFNOSUPPORT(124L),
    EADDRINUSE(125L),
    EADDRNOTAVAIL(126L),
    ENETDOWN(127L),
    ENETUNREACH(128L),
    ENETRESET(129L),
    ECONNABORTED(130L),
    ECONNRESET(131L),
    ENOBUFS(132L),
    EISCONN(133L),
    ENOTCONN(134L),
    ESHUTDOWN(143L),
    ETOOMANYREFS(144L),
    ETIMEDOUT(145L),
    ECONNREFUSED(146L),
    ELOOP(90L),
    ENAMETOOLONG(78L),
    EHOSTDOWN(147L),
    EHOSTUNREACH(148L),
    ENOTEMPTY(93L),
    EUSERS(94L),
    EDQUOT(49L),
    ESTALE(151L),
    EREMOTE(66L),
    ENOLCK(46L),
    ENOSYS(89L),
    EOVERFLOW(79L),
    EIDRM(36L),
    ENOMSG(35L),
    EILSEQ(88L),
    EBADMSG(77L),
    EMULTIHOP(74L),
    ENODATA(61L),
    ENOLINK(67L),
    ENOSR(63L),
    ENOSTR(60L),
    EPROTO(71L),
    ETIME(62L),
    ECHRNG(37L),
    EL2NSYNC(38L),
    EL3HLT(39L),
    EL3RST(40L),
    ELNRNG(41L),
    EUNATCH(42L),
    ENOCSI(43L),
    EL2HLT(44L),
    EBADE(50L),
    EBADR(51L),
    EXFULL(52L),
    ENOANO(53L),
    EBADRQC(54L),
    EBADSLT(55L),
    EDEADLOCK(56L),
    EBFONT(57L),
    ENONET(64L),
    ENOPKG(65L),
    EADV(68L),
    ESRMNT(69L),
    ECOMM(70L),
    ENOTUNIQ(80L),
    EBADFD(81L),
    EREMCHG(82L),
    ELIBACC(83L),
    ELIBBAD(84L),
    ELIBSCN(85L),
    ELIBMAX(86L),
    ELIBEXEC(87L),
    ERESTART(91L),
    ESTRPIPE(92L),
    ECANCELED(47L),
    ENOTRECOVERABLE(59L),
    EOWNERDEAD(58L),
    ENOTSUP(48L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 151L;

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