// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.loongarch64.RLIMIT.StringTable
package jnr.constants.platform.linux.loongarch64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.loongarch64.RLIMIT;

final class RLIMIT_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   RLIMIT_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(RLIMIT.class);
        var0.put(RLIMIT.RLIMIT_AS, "RLIMIT_AS");
        var0.put(RLIMIT.RLIMIT_CORE, "RLIMIT_CORE");
        var0.put(RLIMIT.RLIMIT_CPU, "RLIMIT_CPU");
        var0.put(RLIMIT.RLIMIT_DATA, "RLIMIT_DATA");
        var0.put(RLIMIT.RLIMIT_FSIZE, "RLIMIT_FSIZE");
        var0.put(RLIMIT.RLIMIT_LOCKS, "RLIMIT_LOCKS");
        var0.put(RLIMIT.RLIMIT_MEMLOCK, "RLIMIT_MEMLOCK");
        var0.put(RLIMIT.RLIMIT_MSGQUEUE, "RLIMIT_MSGQUEUE");
        var0.put(RLIMIT.RLIMIT_NICE, "RLIMIT_NICE");
        var0.put(RLIMIT.RLIMIT_NLIMITS, "RLIMIT_NLIMITS");
        var0.put(RLIMIT.RLIMIT_NOFILE, "RLIMIT_NOFILE");
        var0.put(RLIMIT.RLIMIT_NPROC, "RLIMIT_NPROC");
        var0.put(RLIMIT.RLIMIT_OFILE, "RLIMIT_OFILE");
        var0.put(RLIMIT.RLIMIT_RSS, "RLIMIT_RSS");
        var0.put(RLIMIT.RLIMIT_RTPRIO, "RLIMIT_RTPRIO");
        var0.put(RLIMIT.RLIMIT_RTTIME, "RLIMIT_RTTIME");
        var0.put(RLIMIT.RLIMIT_SIGPENDING, "RLIMIT_SIGPENDING");
        var0.put(RLIMIT.RLIMIT_STACK, "RLIMIT_STACK");
        return var0;
    }

}