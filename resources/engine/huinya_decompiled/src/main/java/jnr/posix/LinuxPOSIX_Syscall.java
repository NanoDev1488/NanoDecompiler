// исходный (обфусцированный) внутренний класс: jnr.posix.LinuxPOSIX.Syscall
package jnr.posix;

import jnr.posix.LinuxPOSIX_Syscall_ABI;
import jnr.posix.LinuxPOSIX_Syscall_ABI_AARCH64;
import jnr.posix.LinuxPOSIX_Syscall_ABI_LOONGARCH64;
import jnr.posix.LinuxPOSIX_Syscall_ABI_MIPS64;
import jnr.posix.LinuxPOSIX_Syscall_ABI_PPC64;
import jnr.posix.LinuxPOSIX_Syscall_ABI_SPARCV9;
import jnr.posix.LinuxPOSIX_Syscall_ABI_X86_32;
import jnr.posix.LinuxPOSIX_Syscall_ABI_X86_64;
import jnr.posix.util.Platform;

public final class LinuxPOSIX_Syscall {

    // ---- поля ----
  static final LinuxPOSIX_Syscall_ABI _ABI_X86_32;
  static final LinuxPOSIX_Syscall_ABI _ABI_X86_64;
  static final LinuxPOSIX_Syscall_ABI _ABI_AARCH64;
  static final LinuxPOSIX_Syscall_ABI _ABI_SPARCV9;
  static final LinuxPOSIX_Syscall_ABI _ABI_PPC64;
  static final LinuxPOSIX_Syscall_ABI _ABI_MIPS64;
  static final LinuxPOSIX_Syscall_ABI _ABI_LOONGARCH64;

    static {
        _ABI_X86_32 = new LinuxPOSIX_Syscall_ABI_X86_32();
        _ABI_X86_64 = new LinuxPOSIX_Syscall_ABI_X86_64();
        _ABI_AARCH64 = new LinuxPOSIX_Syscall_ABI_AARCH64();
        _ABI_SPARCV9 = new LinuxPOSIX_Syscall_ABI_SPARCV9();
        _ABI_PPC64 = new LinuxPOSIX_Syscall_ABI_PPC64();
        _ABI_MIPS64 = new LinuxPOSIX_Syscall_ABI_MIPS64();
        _ABI_LOONGARCH64 = new LinuxPOSIX_Syscall_ABI_LOONGARCH64();
    }

  public LinuxPOSIX_Syscall() { // было: <init>
        super();
    }

  public static LinuxPOSIX_Syscall_ABI abi() {
        if (!"x86_64".equals(Platform.ARCH)) {
            if (!"i386".equals(Platform.ARCH)) {
                if (!"aarch64".equals(Platform.ARCH)) {
                    if (!"sparcv9".equals(Platform.ARCH)) {
                        if (!Platform.ARCH.contains("ppc64")) {
                            if (!Platform.ARCH.contains("mips64")) {
                                if (!Platform.ARCH.contains("loongarch64")) {
                                    return null;
                                } else {
                                    return _ABI_LOONGARCH64;
                                }
                            } else {
                                return _ABI_MIPS64;
                            }
                        } else {
                            return _ABI_PPC64;
                        }
                    } else {
                        return _ABI_SPARCV9;
                    }
                } else {
                    return _ABI_AARCH64;
                }
            } else {
                return _ABI_X86_32;
            }
        } else {
            if (!Platform.IS_64_BIT) {
                return null;
            } else {
                return _ABI_X86_64;
            }
        }
    }

}