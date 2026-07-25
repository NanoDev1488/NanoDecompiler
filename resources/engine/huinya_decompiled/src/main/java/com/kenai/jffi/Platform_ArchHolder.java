// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Platform.ArchHolder
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.Platform;
import com.kenai.jffi.Platform_CPU;
import com.kenai.jffi.Util;

final class Platform_ArchHolder {

    // ---- поля ----
  public static final Platform_CPU cpu;

    static {
        cpu = determineCPU();
    }

  private Platform_ArchHolder() { // было: <init>
        super();
    }

  private static Platform_CPU determineCPU() {
        Object var0 = null;
        try {
            var0 = Foreign.getInstance().getArch();
        } catch (UnsatisfiedLinkError var1) {
        }
        if (var0 == null) {
            var0 = System.getProperty("os.arch", "unknown");
        } else {
            if ("unknown".equals(var0)) {
                var0 = System.getProperty("os.arch", "unknown");
            }
        }
        if (Util.equalsIgnoreCase("x86", ((String) var0), Platform.access$000())) {
            return Platform_CPU.I386;
        }
        if (Util.equalsIgnoreCase("i386", ((String) var0), Platform.access$000())) {
            return Platform_CPU.I386;
        }
        if (Util.equalsIgnoreCase("i86pc", ((String) var0), Platform.access$000())) {
            return Platform_CPU.I386;
        }
        if (Util.equalsIgnoreCase("x86_64", ((String) var0), Platform.access$000())) {
            return Platform_CPU.X86_64;
        }
        if (Util.equalsIgnoreCase("amd64", ((String) var0), Platform.access$000())) {
            return Platform_CPU.X86_64;
        }
        if (Util.equalsIgnoreCase("ppc", ((String) var0), Platform.access$000())) {
            return Platform_CPU.PPC;
        }
        if (Util.equalsIgnoreCase("powerpc", ((String) var0), Platform.access$000())) {
            return Platform_CPU.PPC;
        }
        if (Util.equalsIgnoreCase("ppc64", ((String) var0), Platform.access$000())) {
            return Platform_CPU.PPC64;
        }
        if (Util.equalsIgnoreCase("powerpc64", ((String) var0), Platform.access$000())) {
            return Platform_CPU.PPC64;
        }
        if (Util.equalsIgnoreCase("ppc64le", ((String) var0), Platform.access$000())) {
            return Platform_CPU.PPC64LE;
        }
        if (Util.equalsIgnoreCase("powerpc64le", ((String) var0), Platform.access$000())) {
            return Platform_CPU.PPC64LE;
        }
        if (Util.equalsIgnoreCase("s390", ((String) var0), Platform.access$000())) {
            return Platform_CPU.S390X;
        }
        if (Util.equalsIgnoreCase("s390x", ((String) var0), Platform.access$000())) {
            return Platform_CPU.S390X;
        }
        if (Util.equalsIgnoreCase("arm", ((String) var0), Platform.access$000())) {
            return Platform_CPU.ARM;
        }
        if (Util.equalsIgnoreCase("armv7l", ((String) var0), Platform.access$000())) {
            return Platform_CPU.ARM;
        }
        if (Util.equalsIgnoreCase("aarch64", ((String) var0), Platform.access$000())) {
            return Platform_CPU.AARCH64;
        }
        if (Util.equalsIgnoreCase("loongarch64", ((String) var0), Platform.access$000())) {
            return Platform_CPU.LOONGARCH64;
        }
        if (Util.equalsIgnoreCase("mipsel", ((String) var0), Platform.access$000())) {
            return Platform_CPU.MIPSEL;
        }
        if (Util.equalsIgnoreCase("mips64", ((String) var0), Platform.access$000())) {
            return Platform_CPU.MIPS64EL;
        }
        if (Util.equalsIgnoreCase("mips64el", ((String) var0), Platform.access$000())) {
            return Platform_CPU.MIPS64EL;
        }
        if (!Util.equalsIgnoreCase("riscv64", ((String) var0), Platform.access$000())) {
            Platform_CPU[] var1 = Platform_CPU.values();
            int var2 = var1.length;
            int var3 = 0;
        } else {
            return Platform_CPU.RISCV64;
        }
        Object var4;
        while (true) {
            if (var3 >= var2) {
                return Platform_CPU.UNKNOWN;
            }
            var4 = var1[var3];
            if (var4.name().equalsIgnoreCase(((String) var0))) {
                break;
            }
            ++var3;
            continue;
        }
        return ((CPU) var4);
    }

}