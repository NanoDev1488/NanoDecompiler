// исходный (обфусцированный) внутренний класс: jnr.posix.LinuxPOSIX.Syscall.ABI_PPC64
package jnr.posix;

import jnr.posix.LinuxPOSIX_Syscall_ABI;

final class LinuxPOSIX_Syscall_ABI_PPC64 implements LinuxPOSIX_Syscall_ABI {

   LinuxPOSIX_Syscall_ABI_PPC64() { // было: <init>
        super();
    }

  public int __NR_ioprio_set() {
        return 273;
    }

  public int __NR_ioprio_get() {
        return 274;
    }

}