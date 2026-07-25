// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.powerpc64.Confstr.StringTable
package jnr.constants.platform.linux.powerpc64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.linux.powerpc64.Confstr;

final class Confstr_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   Confstr_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(Confstr.class);
        var0.put(Confstr._CS_PATH, "_CS_PATH");
        var0.put(Confstr._CS_POSIX_V7_ILP32_OFF32_CFLAGS, "_CS_POSIX_V7_ILP32_OFF32_CFLAGS");
        var0.put(Confstr._CS_POSIX_V7_ILP32_OFF32_LDFLAGS, "_CS_POSIX_V7_ILP32_OFF32_LDFLAGS");
        var0.put(Confstr._CS_POSIX_V7_ILP32_OFF32_LIBS, "_CS_POSIX_V7_ILP32_OFF32_LIBS");
        var0.put(Confstr._CS_POSIX_V7_ILP32_OFFBIG_CFLAGS, "_CS_POSIX_V7_ILP32_OFFBIG_CFLAGS");
        var0.put(Confstr._CS_POSIX_V7_ILP32_OFFBIG_LDFLAGS, "_CS_POSIX_V7_ILP32_OFFBIG_LDFLAGS");
        var0.put(Confstr._CS_POSIX_V7_ILP32_OFFBIG_LIBS, "_CS_POSIX_V7_ILP32_OFFBIG_LIBS");
        var0.put(Confstr._CS_POSIX_V7_LP64_OFF64_CFLAGS, "_CS_POSIX_V7_LP64_OFF64_CFLAGS");
        var0.put(Confstr._CS_POSIX_V7_LP64_OFF64_LDFLAGS, "_CS_POSIX_V7_LP64_OFF64_LDFLAGS");
        var0.put(Confstr._CS_POSIX_V7_LP64_OFF64_LIBS, "_CS_POSIX_V7_LP64_OFF64_LIBS");
        var0.put(Confstr._CS_POSIX_V7_LPBIG_OFFBIG_CFLAGS, "_CS_POSIX_V7_LPBIG_OFFBIG_CFLAGS");
        var0.put(Confstr._CS_POSIX_V7_LPBIG_OFFBIG_LDFLAGS, "_CS_POSIX_V7_LPBIG_OFFBIG_LDFLAGS");
        var0.put(Confstr._CS_POSIX_V7_LPBIG_OFFBIG_LIBS, "_CS_POSIX_V7_LPBIG_OFFBIG_LIBS");
        var0.put(Confstr._CS_POSIX_V7_WIDTH_RESTRICTED_ENVS, "_CS_POSIX_V7_WIDTH_RESTRICTED_ENVS");
        var0.put(Confstr._CS_V7_ENV, "_CS_V7_ENV");
        var0.put(Confstr._CS_POSIX_V6_ILP32_OFF32_CFLAGS, "_CS_POSIX_V6_ILP32_OFF32_CFLAGS");
        var0.put(Confstr._CS_POSIX_V6_ILP32_OFF32_LDFLAGS, "_CS_POSIX_V6_ILP32_OFF32_LDFLAGS");
        var0.put(Confstr._CS_POSIX_V6_ILP32_OFF32_LIBS, "_CS_POSIX_V6_ILP32_OFF32_LIBS");
        var0.put(Confstr._CS_POSIX_V6_ILP32_OFFBIG_CFLAGS, "_CS_POSIX_V6_ILP32_OFFBIG_CFLAGS");
        var0.put(Confstr._CS_POSIX_V6_ILP32_OFFBIG_LDFLAGS, "_CS_POSIX_V6_ILP32_OFFBIG_LDFLAGS");
        var0.put(Confstr._CS_POSIX_V6_ILP32_OFFBIG_LIBS, "_CS_POSIX_V6_ILP32_OFFBIG_LIBS");
        var0.put(Confstr._CS_POSIX_V6_LP64_OFF64_CFLAGS, "_CS_POSIX_V6_LP64_OFF64_CFLAGS");
        var0.put(Confstr._CS_POSIX_V6_LP64_OFF64_LDFLAGS, "_CS_POSIX_V6_LP64_OFF64_LDFLAGS");
        var0.put(Confstr._CS_POSIX_V6_LP64_OFF64_LIBS, "_CS_POSIX_V6_LP64_OFF64_LIBS");
        var0.put(Confstr._CS_POSIX_V6_LPBIG_OFFBIG_CFLAGS, "_CS_POSIX_V6_LPBIG_OFFBIG_CFLAGS");
        var0.put(Confstr._CS_POSIX_V6_LPBIG_OFFBIG_LDFLAGS, "_CS_POSIX_V6_LPBIG_OFFBIG_LDFLAGS");
        var0.put(Confstr._CS_POSIX_V6_LPBIG_OFFBIG_LIBS, "_CS_POSIX_V6_LPBIG_OFFBIG_LIBS");
        var0.put(Confstr._CS_POSIX_V6_WIDTH_RESTRICTED_ENVS, "_CS_POSIX_V6_WIDTH_RESTRICTED_ENVS");
        var0.put(Confstr._CS_V6_ENV, "_CS_V6_ENV");
        var0.put(Confstr._CS_GNU_LIBC_VERSION, "_CS_GNU_LIBC_VERSION");
        var0.put(Confstr._CS_GNU_LIBPTHREAD_VERSION, "_CS_GNU_LIBPTHREAD_VERSION");
        return var0;
    }

}