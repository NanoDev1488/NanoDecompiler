// исходный (обфусцированный) внутренний класс: jnr.constants.platform.openbsd.Sysconf
package jnr.constants.platform.openbsd;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.openbsd.Sysconf_StringTable;

public enum Sysconf implements Constant {

    _SC_ARG_MAX(1L),
    _SC_CHILD_MAX(2L),
    _SC_CLK_TCK(3L),
    _SC_NGROUPS_MAX(4L),
    _SC_OPEN_MAX(5L),
    _SC_JOB_CONTROL(6L),
    _SC_SAVED_IDS(7L),
    _SC_VERSION(8L),
    _SC_BC_BASE_MAX(9L),
    _SC_BC_DIM_MAX(10L),
    _SC_BC_SCALE_MAX(11L),
    _SC_BC_STRING_MAX(12L),
    _SC_COLL_WEIGHTS_MAX(13L),
    _SC_EXPR_NEST_MAX(14L),
    _SC_LINE_MAX(15L),
    _SC_RE_DUP_MAX(16L),
    _SC_2_VERSION(17L),
    _SC_2_C_BIND(18L),
    _SC_2_C_DEV(19L),
    _SC_2_CHAR_TERM(20L),
    _SC_2_FORT_DEV(21L),
    _SC_2_FORT_RUN(22L),
    _SC_2_LOCALEDEF(23L),
    _SC_2_SW_DEV(24L),
    _SC_2_UPE(25L),
    _SC_STREAM_MAX(26L),
    _SC_TZNAME_MAX(27L),
    _SC_ASYNCHRONOUS_IO(45L),
    _SC_PAGESIZE(28L),
    _SC_MEMLOCK(54L),
    _SC_MEMLOCK_RANGE(55L),
    _SC_MEMORY_PROTECTION(56L),
    _SC_MESSAGE_PASSING(57L),
    _SC_PRIORITIZED_IO(60L),
    _SC_PRIORITY_SCHEDULING(61L),
    _SC_REALTIME_SIGNALS(64L),
    _SC_SEMAPHORES(67L),
    _SC_FSYNC(29L),
    _SC_SHARED_MEMORY_OBJECTS(68L),
    _SC_SYNCHRONIZED_IO(75L),
    _SC_TIMERS(94L),
    _SC_AIO_LISTIO_MAX(42L),
    _SC_AIO_MAX(43L),
    _SC_AIO_PRIO_DELTA_MAX(44L),
    _SC_DELAYTIMER_MAX(50L),
    _SC_MQ_OPEN_MAX(58L),
    _SC_MAPPED_FILES(53L),
    _SC_RTSIG_MAX(66L),
    _SC_SEM_NSEMS_MAX(31L),
    _SC_SEM_VALUE_MAX(32L),
    _SC_SIGQUEUE_MAX(70L),
    _SC_TIMER_MAX(93L),
    _SC_NPROCESSORS_CONF(502L),
    _SC_NPROCESSORS_ONLN(503L),
    _SC_2_PBS(35L),
    _SC_2_PBS_ACCOUNTING(36L),
    _SC_2_PBS_CHECKPOINT(37L),
    _SC_2_PBS_LOCATE(38L),
    _SC_2_PBS_MESSAGE(39L),
    _SC_2_PBS_TRACK(40L),
    _SC_ADVISORY_INFO(41L),
    _SC_BARRIERS(47L),
    _SC_CLOCK_SELECTION(48L),
    _SC_CPUTIME(49L),
    _SC_GETGR_R_SIZE_MAX(100L),
    _SC_GETPW_R_SIZE_MAX(101L),
    _SC_HOST_NAME_MAX(33L),
    _SC_LOGIN_NAME_MAX(102L),
    _SC_MONOTONIC_CLOCK(34L),
    _SC_MQ_PRIO_MAX(59L),
    _SC_READER_WRITER_LOCKS(63L),
    _SC_REGEXP(65L),
    _SC_SHELL(69L),
    _SC_SPAWN(71L),
    _SC_SPIN_LOCKS(72L),
    _SC_SPORADIC_SERVER(73L),
    _SC_THREAD_ATTR_STACKADDR(77L),
    _SC_THREAD_ATTR_STACKSIZE(78L),
    _SC_THREAD_CPUTIME(79L),
    _SC_THREAD_DESTRUCTOR_ITERATIONS(80L),
    _SC_THREAD_KEYS_MAX(81L),
    _SC_THREAD_PRIO_INHERIT(82L),
    _SC_THREAD_PRIO_PROTECT(83L),
    _SC_THREAD_PRIORITY_SCHEDULING(84L),
    _SC_THREAD_PROCESS_SHARED(85L),
    _SC_THREAD_SAFE_FUNCTIONS(103L),
    _SC_THREAD_SPORADIC_SERVER(88L),
    _SC_THREAD_STACK_MIN(89L),
    _SC_THREAD_THREADS_MAX(90L),
    _SC_TIMEOUTS(92L),
    _SC_THREADS(91L),
    _SC_TRACE(95L),
    _SC_TRACE_EVENT_FILTER(96L),
    _SC_TRACE_INHERIT(98L),
    _SC_TRACE_LOG(99L),
    _SC_TTY_NAME_MAX(107L),
    _SC_TYPED_MEMORY_OBJECTS(108L),
    _SC_V6_ILP32_OFF32(109L),
    _SC_V6_ILP32_OFFBIG(110L),
    _SC_V6_LP64_OFF64(111L),
    _SC_V6_LPBIG_OFFBIG(112L),
    _SC_IPV6(52L),
    _SC_RAW_SOCKETS(62L),
    _SC_SYMLOOP_MAX(76L),
    _SC_ATEXIT_MAX(46L),
    _SC_IOV_MAX(51L),
    _SC_PAGE_SIZE(28L),
    _SC_XOPEN_CRYPT(117L),
    _SC_XOPEN_ENH_I18N(118L),
    _SC_XOPEN_LEGACY(119L),
    _SC_XOPEN_REALTIME(120L),
    _SC_XOPEN_REALTIME_THREADS(121L),
    _SC_XOPEN_SHM(30L),
    _SC_XOPEN_STREAMS(122L),
    _SC_XOPEN_UNIX(123L),
    _SC_XOPEN_VERSION(125L),
    _SC_SS_REPL_MAX(74L),
    _SC_TRACE_EVENT_NAME_MAX(97L),
    _SC_TRACE_NAME_MAX(104L),
    _SC_TRACE_SYS_MAX(105L),
    _SC_TRACE_USER_EVENT_MAX(106L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 503L;

  private Sysconf(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) Sysconf_StringTable.descriptions.get(this));
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