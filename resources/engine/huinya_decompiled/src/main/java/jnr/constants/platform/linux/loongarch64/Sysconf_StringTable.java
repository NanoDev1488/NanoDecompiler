// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.loongarch64.Sysconf.StringTable
package jnr.constants.platform.linux.loongarch64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.loongarch64.Sysconf;

final class Sysconf_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   Sysconf_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(Sysconf.class);
        var0.put(Sysconf._SC_ARG_MAX, "_SC_ARG_MAX");
        var0.put(Sysconf._SC_CHILD_MAX, "_SC_CHILD_MAX");
        var0.put(Sysconf._SC_CLK_TCK, "_SC_CLK_TCK");
        var0.put(Sysconf._SC_NGROUPS_MAX, "_SC_NGROUPS_MAX");
        var0.put(Sysconf._SC_OPEN_MAX, "_SC_OPEN_MAX");
        var0.put(Sysconf._SC_JOB_CONTROL, "_SC_JOB_CONTROL");
        var0.put(Sysconf._SC_SAVED_IDS, "_SC_SAVED_IDS");
        var0.put(Sysconf._SC_VERSION, "_SC_VERSION");
        var0.put(Sysconf._SC_BC_BASE_MAX, "_SC_BC_BASE_MAX");
        var0.put(Sysconf._SC_BC_DIM_MAX, "_SC_BC_DIM_MAX");
        var0.put(Sysconf._SC_BC_SCALE_MAX, "_SC_BC_SCALE_MAX");
        var0.put(Sysconf._SC_BC_STRING_MAX, "_SC_BC_STRING_MAX");
        var0.put(Sysconf._SC_COLL_WEIGHTS_MAX, "_SC_COLL_WEIGHTS_MAX");
        var0.put(Sysconf._SC_EXPR_NEST_MAX, "_SC_EXPR_NEST_MAX");
        var0.put(Sysconf._SC_LINE_MAX, "_SC_LINE_MAX");
        var0.put(Sysconf._SC_RE_DUP_MAX, "_SC_RE_DUP_MAX");
        var0.put(Sysconf._SC_2_VERSION, "_SC_2_VERSION");
        var0.put(Sysconf._SC_2_C_BIND, "_SC_2_C_BIND");
        var0.put(Sysconf._SC_2_C_DEV, "_SC_2_C_DEV");
        var0.put(Sysconf._SC_2_CHAR_TERM, "_SC_2_CHAR_TERM");
        var0.put(Sysconf._SC_2_FORT_DEV, "_SC_2_FORT_DEV");
        var0.put(Sysconf._SC_2_FORT_RUN, "_SC_2_FORT_RUN");
        var0.put(Sysconf._SC_2_LOCALEDEF, "_SC_2_LOCALEDEF");
        var0.put(Sysconf._SC_2_SW_DEV, "_SC_2_SW_DEV");
        var0.put(Sysconf._SC_2_UPE, "_SC_2_UPE");
        var0.put(Sysconf._SC_STREAM_MAX, "_SC_STREAM_MAX");
        var0.put(Sysconf._SC_TZNAME_MAX, "_SC_TZNAME_MAX");
        var0.put(Sysconf._SC_ASYNCHRONOUS_IO, "_SC_ASYNCHRONOUS_IO");
        var0.put(Sysconf._SC_PAGESIZE, "_SC_PAGESIZE");
        var0.put(Sysconf._SC_MEMLOCK, "_SC_MEMLOCK");
        var0.put(Sysconf._SC_MEMLOCK_RANGE, "_SC_MEMLOCK_RANGE");
        var0.put(Sysconf._SC_MEMORY_PROTECTION, "_SC_MEMORY_PROTECTION");
        var0.put(Sysconf._SC_MESSAGE_PASSING, "_SC_MESSAGE_PASSING");
        var0.put(Sysconf._SC_PRIORITIZED_IO, "_SC_PRIORITIZED_IO");
        var0.put(Sysconf._SC_PRIORITY_SCHEDULING, "_SC_PRIORITY_SCHEDULING");
        var0.put(Sysconf._SC_REALTIME_SIGNALS, "_SC_REALTIME_SIGNALS");
        var0.put(Sysconf._SC_SEMAPHORES, "_SC_SEMAPHORES");
        var0.put(Sysconf._SC_FSYNC, "_SC_FSYNC");
        var0.put(Sysconf._SC_SHARED_MEMORY_OBJECTS, "_SC_SHARED_MEMORY_OBJECTS");
        var0.put(Sysconf._SC_SYNCHRONIZED_IO, "_SC_SYNCHRONIZED_IO");
        var0.put(Sysconf._SC_TIMERS, "_SC_TIMERS");
        var0.put(Sysconf._SC_AIO_LISTIO_MAX, "_SC_AIO_LISTIO_MAX");
        var0.put(Sysconf._SC_AIO_MAX, "_SC_AIO_MAX");
        var0.put(Sysconf._SC_AIO_PRIO_DELTA_MAX, "_SC_AIO_PRIO_DELTA_MAX");
        var0.put(Sysconf._SC_DELAYTIMER_MAX, "_SC_DELAYTIMER_MAX");
        var0.put(Sysconf._SC_MQ_OPEN_MAX, "_SC_MQ_OPEN_MAX");
        var0.put(Sysconf._SC_MAPPED_FILES, "_SC_MAPPED_FILES");
        var0.put(Sysconf._SC_RTSIG_MAX, "_SC_RTSIG_MAX");
        var0.put(Sysconf._SC_SEM_NSEMS_MAX, "_SC_SEM_NSEMS_MAX");
        var0.put(Sysconf._SC_SEM_VALUE_MAX, "_SC_SEM_VALUE_MAX");
        var0.put(Sysconf._SC_SIGQUEUE_MAX, "_SC_SIGQUEUE_MAX");
        var0.put(Sysconf._SC_TIMER_MAX, "_SC_TIMER_MAX");
        var0.put(Sysconf._SC_NPROCESSORS_CONF, "_SC_NPROCESSORS_CONF");
        var0.put(Sysconf._SC_NPROCESSORS_ONLN, "_SC_NPROCESSORS_ONLN");
        var0.put(Sysconf._SC_2_PBS, "_SC_2_PBS");
        var0.put(Sysconf._SC_2_PBS_ACCOUNTING, "_SC_2_PBS_ACCOUNTING");
        var0.put(Sysconf._SC_2_PBS_CHECKPOINT, "_SC_2_PBS_CHECKPOINT");
        var0.put(Sysconf._SC_2_PBS_LOCATE, "_SC_2_PBS_LOCATE");
        var0.put(Sysconf._SC_2_PBS_MESSAGE, "_SC_2_PBS_MESSAGE");
        var0.put(Sysconf._SC_2_PBS_TRACK, "_SC_2_PBS_TRACK");
        var0.put(Sysconf._SC_ADVISORY_INFO, "_SC_ADVISORY_INFO");
        var0.put(Sysconf._SC_BARRIERS, "_SC_BARRIERS");
        var0.put(Sysconf._SC_CLOCK_SELECTION, "_SC_CLOCK_SELECTION");
        var0.put(Sysconf._SC_CPUTIME, "_SC_CPUTIME");
        var0.put(Sysconf._SC_FILE_LOCKING, "_SC_FILE_LOCKING");
        var0.put(Sysconf._SC_GETGR_R_SIZE_MAX, "_SC_GETGR_R_SIZE_MAX");
        var0.put(Sysconf._SC_GETPW_R_SIZE_MAX, "_SC_GETPW_R_SIZE_MAX");
        var0.put(Sysconf._SC_HOST_NAME_MAX, "_SC_HOST_NAME_MAX");
        var0.put(Sysconf._SC_LOGIN_NAME_MAX, "_SC_LOGIN_NAME_MAX");
        var0.put(Sysconf._SC_MONOTONIC_CLOCK, "_SC_MONOTONIC_CLOCK");
        var0.put(Sysconf._SC_MQ_PRIO_MAX, "_SC_MQ_PRIO_MAX");
        var0.put(Sysconf._SC_READER_WRITER_LOCKS, "_SC_READER_WRITER_LOCKS");
        var0.put(Sysconf._SC_REGEXP, "_SC_REGEXP");
        var0.put(Sysconf._SC_SHELL, "_SC_SHELL");
        var0.put(Sysconf._SC_SPAWN, "_SC_SPAWN");
        var0.put(Sysconf._SC_SPIN_LOCKS, "_SC_SPIN_LOCKS");
        var0.put(Sysconf._SC_SPORADIC_SERVER, "_SC_SPORADIC_SERVER");
        var0.put(Sysconf._SC_THREAD_ATTR_STACKADDR, "_SC_THREAD_ATTR_STACKADDR");
        var0.put(Sysconf._SC_THREAD_ATTR_STACKSIZE, "_SC_THREAD_ATTR_STACKSIZE");
        var0.put(Sysconf._SC_THREAD_CPUTIME, "_SC_THREAD_CPUTIME");
        var0.put(Sysconf._SC_THREAD_DESTRUCTOR_ITERATIONS, "_SC_THREAD_DESTRUCTOR_ITERATIONS");
        var0.put(Sysconf._SC_THREAD_KEYS_MAX, "_SC_THREAD_KEYS_MAX");
        var0.put(Sysconf._SC_THREAD_PRIO_INHERIT, "_SC_THREAD_PRIO_INHERIT");
        var0.put(Sysconf._SC_THREAD_PRIO_PROTECT, "_SC_THREAD_PRIO_PROTECT");
        var0.put(Sysconf._SC_THREAD_PRIORITY_SCHEDULING, "_SC_THREAD_PRIORITY_SCHEDULING");
        var0.put(Sysconf._SC_THREAD_PROCESS_SHARED, "_SC_THREAD_PROCESS_SHARED");
        var0.put(Sysconf._SC_THREAD_SAFE_FUNCTIONS, "_SC_THREAD_SAFE_FUNCTIONS");
        var0.put(Sysconf._SC_THREAD_SPORADIC_SERVER, "_SC_THREAD_SPORADIC_SERVER");
        var0.put(Sysconf._SC_THREAD_STACK_MIN, "_SC_THREAD_STACK_MIN");
        var0.put(Sysconf._SC_THREAD_THREADS_MAX, "_SC_THREAD_THREADS_MAX");
        var0.put(Sysconf._SC_TIMEOUTS, "_SC_TIMEOUTS");
        var0.put(Sysconf._SC_THREADS, "_SC_THREADS");
        var0.put(Sysconf._SC_TRACE, "_SC_TRACE");
        var0.put(Sysconf._SC_TRACE_EVENT_FILTER, "_SC_TRACE_EVENT_FILTER");
        var0.put(Sysconf._SC_TRACE_INHERIT, "_SC_TRACE_INHERIT");
        var0.put(Sysconf._SC_TRACE_LOG, "_SC_TRACE_LOG");
        var0.put(Sysconf._SC_TTY_NAME_MAX, "_SC_TTY_NAME_MAX");
        var0.put(Sysconf._SC_TYPED_MEMORY_OBJECTS, "_SC_TYPED_MEMORY_OBJECTS");
        var0.put(Sysconf._SC_V6_ILP32_OFF32, "_SC_V6_ILP32_OFF32");
        var0.put(Sysconf._SC_V6_ILP32_OFFBIG, "_SC_V6_ILP32_OFFBIG");
        var0.put(Sysconf._SC_V6_LP64_OFF64, "_SC_V6_LP64_OFF64");
        var0.put(Sysconf._SC_V6_LPBIG_OFFBIG, "_SC_V6_LPBIG_OFFBIG");
        var0.put(Sysconf._SC_IPV6, "_SC_IPV6");
        var0.put(Sysconf._SC_RAW_SOCKETS, "_SC_RAW_SOCKETS");
        var0.put(Sysconf._SC_SYMLOOP_MAX, "_SC_SYMLOOP_MAX");
        var0.put(Sysconf._SC_ATEXIT_MAX, "_SC_ATEXIT_MAX");
        var0.put(Sysconf._SC_IOV_MAX, "_SC_IOV_MAX");
        var0.put(Sysconf._SC_PAGE_SIZE, "_SC_PAGE_SIZE");
        var0.put(Sysconf._SC_XOPEN_CRYPT, "_SC_XOPEN_CRYPT");
        var0.put(Sysconf._SC_XOPEN_ENH_I18N, "_SC_XOPEN_ENH_I18N");
        var0.put(Sysconf._SC_XOPEN_LEGACY, "_SC_XOPEN_LEGACY");
        var0.put(Sysconf._SC_XOPEN_REALTIME, "_SC_XOPEN_REALTIME");
        var0.put(Sysconf._SC_XOPEN_REALTIME_THREADS, "_SC_XOPEN_REALTIME_THREADS");
        var0.put(Sysconf._SC_XOPEN_SHM, "_SC_XOPEN_SHM");
        var0.put(Sysconf._SC_XOPEN_STREAMS, "_SC_XOPEN_STREAMS");
        var0.put(Sysconf._SC_XOPEN_UNIX, "_SC_XOPEN_UNIX");
        var0.put(Sysconf._SC_XOPEN_VERSION, "_SC_XOPEN_VERSION");
        var0.put(Sysconf._SC_XOPEN_XCU_VERSION, "_SC_XOPEN_XCU_VERSION");
        var0.put(Sysconf._SC_XBS5_ILP32_OFF32, "_SC_XBS5_ILP32_OFF32");
        var0.put(Sysconf._SC_XBS5_ILP32_OFFBIG, "_SC_XBS5_ILP32_OFFBIG");
        var0.put(Sysconf._SC_XBS5_LP64_OFF64, "_SC_XBS5_LP64_OFF64");
        var0.put(Sysconf._SC_XBS5_LPBIG_OFFBIG, "_SC_XBS5_LPBIG_OFFBIG");
        var0.put(Sysconf._SC_SS_REPL_MAX, "_SC_SS_REPL_MAX");
        var0.put(Sysconf._SC_TRACE_EVENT_NAME_MAX, "_SC_TRACE_EVENT_NAME_MAX");
        var0.put(Sysconf._SC_TRACE_NAME_MAX, "_SC_TRACE_NAME_MAX");
        var0.put(Sysconf._SC_TRACE_SYS_MAX, "_SC_TRACE_SYS_MAX");
        var0.put(Sysconf._SC_TRACE_USER_EVENT_MAX, "_SC_TRACE_USER_EVENT_MAX");
        var0.put(Sysconf._SC_PASS_MAX, "_SC_PASS_MAX");
        return var0;
    }

}