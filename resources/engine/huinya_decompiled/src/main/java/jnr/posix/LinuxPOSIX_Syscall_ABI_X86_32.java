// исходный (обфусцированный) внутренний класс: jnr.posix.LinuxPOSIX.Syscall.ABI_X86_32
package jnr.posix;

import jnr.posix.LinuxPOSIX_Syscall_ABI;

final class LinuxPOSIX_Syscall_ABI_X86_32 implements LinuxPOSIX_Syscall_ABI {

   LinuxPOSIX_Syscall_ABI_X86_32() { // было: <init>
        super();
    }

  public int __NR_ioprio_set() {
        return 289;
    }

  public int __NR_ioprio_get() {
        return 290;
    }

}