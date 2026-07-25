// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.loongarch64.Errno
package jnr.constants.platform.linux.loongarch64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.loongarch64.Errno_StringTable;

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
    EDEADLK(35L),
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
    EINPROGRESS(115L),
    EALREADY(114L),
    ENOTSOCK(88L),
    EDESTADDRREQ(89L),
    EMSGSIZE(90L),
    EPROTOTYPE(91L),
    ENOPROTOOPT(92L),
    EPROTONOSUPPORT(93L),
    ESOCKTNOSUPPORT(94L),
    EOPNOTSUPP(95L),
    EPFNOSUPPORT(96L),
    EAFNOSUPPORT(97L),
    EADDRINUSE(98L),
    EADDRNOTAVAIL(99L),
    ENETDOWN(100L),
    ENETUNREACH(101L),
    ENETRESET(102L),
    ECONNABORTED(103L),
    ECONNRESET(104L),
    ENOBUFS(105L),
    EISCONN(106L),
    ENOTCONN(107L),
    ESHUTDOWN(108L),
    ETOOMANYREFS(109L),
    ETIMEDOUT(110L),
    ECONNREFUSED(111L),
    ELOOP(40L),
    ENAMETOOLONG(36L),
    EHOSTDOWN(112L),
    EHOSTUNREACH(113L),
    ENOTEMPTY(39L),
    EUSERS(87L),
    EDQUOT(122L),
    ESTALE(116L),
    EREMOTE(66L),
    ENOLCK(37L),
    ENOSYS(38L),
    EOVERFLOW(75L),
    EIDRM(43L),
    ENOMSG(42L),
    EILSEQ(84L),
    EBADMSG(74L),
    EMULTIHOP(72L),
    ENODATA(61L),
    ENOLINK(67L),
    ENOSR(63L),
    ENOSTR(60L),
    EPROTO(71L),
    ETIME(62L),
    ECHRNG(44L),
    EL2NSYNC(45L),
    EL3HLT(46L),
    EL3RST(47L),
    ELNRNG(48L),
    EUNATCH(49L),
    ENOCSI(50L),
    EL2HLT(51L),
    EBADE(52L),
    EBADR(53L),
    EXFULL(54L),
    ENOANO(55L),
    EBADRQC(56L),
    EBADSLT(57L),
    EDEADLOCK(35L),
    EBFONT(59L),
    ENONET(64L),
    ENOPKG(65L),
    EADV(68L),
    ESRMNT(69L),
    ECOMM(70L),
    EDOTDOT(73L),
    ENOTUNIQ(76L),
    EBADFD(77L),
    EREMCHG(78L),
    ELIBACC(79L),
    ELIBBAD(80L),
    ELIBSCN(81L),
    ELIBMAX(82L),
    ELIBEXEC(83L),
    ERESTART(85L),
    ESTRPIPE(86L),
    EUCLEAN(117L),
    ENOTNAM(118L),
    ENAVAIL(119L),
    EISNAM(120L),
    EREMOTEIO(121L),
    ECANCELED(125L),
    EKEYEXPIRED(127L),
    EKEYREJECTED(129L),
    EKEYREVOKED(128L),
    EMEDIUMTYPE(124L),
    ENOKEY(126L),
    ENOMEDIUM(123L),
    ENOTRECOVERABLE(131L),
    EOWNERDEAD(130L),
    ERFKILL(132L),
    ENOTSUP(95L),
    EHWPOISON(133L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 133L;

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