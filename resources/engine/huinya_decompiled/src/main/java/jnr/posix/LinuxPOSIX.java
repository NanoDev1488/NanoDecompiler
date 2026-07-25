// исходный (обфусцированный) внутренний класс: jnr.posix.LinuxPOSIX
package jnr.posix;

import java.io.FileDescriptor;
import java.nio.ByteBuffer;
import jnr.constants.platform.Confstr;
import jnr.constants.platform.Errno;
import jnr.constants.platform.Pathconf;
import jnr.constants.platform.PosixFadvise;
import jnr.constants.platform.Sysconf;
import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import jnr.posix.BaseNativePOSIX;
import jnr.posix.BaseNativePOSIX_PointerConverter;
import jnr.posix.FileStat;
import jnr.posix.JavaLibCHelper;
import jnr.posix.LibC;
import jnr.posix.LibCProvider;
import jnr.posix.Linux;
import jnr.posix.LinuxFileStat32;
import jnr.posix.LinuxFileStat64;
import jnr.posix.LinuxFileStatAARCH64;
import jnr.posix.LinuxFileStatLOONGARCH64;
import jnr.posix.LinuxFileStatMIPS64;
import jnr.posix.LinuxFileStatSPARCV9;
import jnr.posix.LinuxLibC;
import jnr.posix.LinuxMsgHdr;
import jnr.posix.LinuxPOSIX_Anon1;
import jnr.posix.LinuxPOSIX_Syscall;
import jnr.posix.LinuxPOSIX_Syscall_ABI;
import jnr.posix.LinuxSocketMacros;
import jnr.posix.MsgHdr;
import jnr.posix.NativeTimes;
import jnr.posix.POSIXHandler;
import jnr.posix.SocketMacros;
import jnr.posix.Times;
import jnr.posix.util.Platform;

final class LinuxPOSIX extends BaseNativePOSIX implements Linux {

    // ---- поля ----
  private volatile boolean use_fxstat64;
  private volatile boolean use_lxstat64;
  private volatile boolean use_xstat64;
  private final int statVersion;
  public static final BaseNativePOSIX_PointerConverter PASSWD;

    static {
        PASSWD = new LinuxPOSIX_Anon1();
    }

   LinuxPOSIX(LibCProvider arg0, POSIXHandler arg1) { // было: <init>
        super(arg0, arg1);
        use_fxstat64 = true;
        use_lxstat64 = true;
        use_xstat64 = true;
        if (Platform.IS_32_BIT) {
            statVersion = 3;
        } else {
            if ("sparcv9".equals(Platform.ARCH)) {
                statVersion = 3;
            } else {
                if (!Platform.ARCH.contains("mips64")) {
                    FileStat var3 = allocateStat();
                    statVersion = (((LinuxLibC) libc())).__xstat64(0, "/dev/null", var3) < 0;
                } else {
                    statVersion = 3;
                }
            }
        }
    }

  public FileStat allocateStat() {
        if (!Platform.IS_32_BIT) {
            if (!"aarch64".equals(Platform.ARCH)) {
                if (!"sparcv9".equals(Platform.ARCH)) {
                    if (!"loongarch64".equals(Platform.ARCH)) {
                        if (!Platform.ARCH.contains("mips64")) {
                            return new LinuxFileStat64(this);
                        } else {
                            return new LinuxFileStatMIPS64(this);
                        }
                    } else {
                        return new LinuxFileStatLOONGARCH64(this);
                    }
                } else {
                    return new LinuxFileStatSPARCV9(this);
                }
            } else {
                return new LinuxFileStatAARCH64(this);
            }
        } else {
            return new LinuxFileStat32(this);
        }
    }

  public MsgHdr allocateMsgHdr() {
        return new LinuxMsgHdr(this);
    }

  public Pointer allocatePosixSpawnFileActions() {
        return Memory.allocateDirect(getRuntime(), 80);
    }

  public Pointer allocatePosixSpawnattr() {
        return Memory.allocateDirect(getRuntime(), 336);
    }

  public SocketMacros socketMacros() {
        return LinuxSocketMacros.INSTANCE;
    }

  private int old_fstat(int arg0, FileStat arg1) {
        int __stk1;
        try {
            __stk1 = super.fstat(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            handler.unimplementedError("fstat");
            return -1;
        }
    }

  public int fstat(int arg0, FileStat arg1) {
        int __stk1;
        if (!use_fxstat64) {
            return old_fstat(arg0, arg1);
        }
        try {
            int var3 = (((LinuxLibC) libc())).__fxstat64(statVersion, arg0, arg1);
            if (var3 >= 0) {
                __stk1 = var3;
            } else {
                handler.error(Errno.valueOf(((long) errno())), "fstat", Integer.toString(arg0));
                __stk1 = var3;
            }
        } catch (UnsatisfiedLinkError var4) {
            use_fxstat64 = false;
            return old_fstat(arg0, arg1);
        }
    }

  public FileStat fstat(int arg0) {
        FileStat var2 = allocateStat();
        int var3 = fstat(arg0, var2);
        if (var3 < 0) {
            handler.error(Errno.valueOf(((long) errno())), "fstat", Integer.toString(arg0));
        }
        return var2;
    }

  public int fstat(FileDescriptor arg0, FileStat arg1) {
        return fstat(helper.getfd(arg0), arg1);
    }

  public FileStat fstat(FileDescriptor arg0) {
        FileStat var2 = allocateStat();
        int var3 = helper.getfd(arg0);
        int var4 = fstat(var3, var2);
        if (var4 < 0) {
            handler.error(Errno.valueOf(((long) errno())), "fstat", Integer.toString(var3));
        }
        return var2;
    }

  private final int old_lstat(String arg0, FileStat arg1) {
        int __stk1;
        try {
            __stk1 = super.lstat(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            handler.unimplementedError("lstat");
            return -1;
        }
    }

  public int lstat(String arg0, FileStat arg1) {
        int __stk1;
        if (!use_lxstat64) {
            return old_lstat(arg0, arg1);
        }
        try {
            __stk1 = (((LinuxLibC) libc())).__lxstat64(statVersion, arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            use_lxstat64 = false;
            return old_lstat(arg0, arg1);
        }
    }

  public FileStat lstat(String arg0) {
        FileStat var2 = allocateStat();
        int var3 = lstat(arg0, var2);
        if (var3 < 0) {
            handler.error(Errno.valueOf(((long) errno())), "lstat", arg0);
        }
        return var2;
    }

  private final int old_stat(String arg0, FileStat arg1) {
        int __stk1;
        try {
            __stk1 = super.stat(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            handler.unimplementedError("stat");
            return -1;
        }
    }

  public int stat(String arg0, FileStat arg1) {
        int __stk1;
        if (!use_xstat64) {
            return old_stat(arg0, arg1);
        }
        try {
            __stk1 = (((LinuxLibC) libc())).__xstat64(statVersion, arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            use_xstat64 = false;
            return old_stat(arg0, arg1);
        }
    }

  public FileStat stat(String arg0) {
        FileStat var2 = allocateStat();
        int var3 = stat(arg0, var2);
        if (var3 < 0) {
            handler.error(Errno.valueOf(((long) errno())), "stat", arg0);
        }
        return var2;
    }

  public long sysconf(Sysconf arg0) {
        return libc().sysconf(arg0);
    }

  public int confstr(Confstr arg0, ByteBuffer arg1, int arg2) {
        return libc().confstr(arg0, arg1, arg2);
    }

  public int fpathconf(int arg0, Pathconf arg1) {
        return libc().fpathconf(arg0, arg1);
    }

  public Times times() {
        return NativeTimes.times(this);
    }

  public int ioprio_get(int arg0, int arg1) {
        LinuxPOSIX_Syscall_ABI var3 = LinuxPOSIX_Syscall.abi();
        if (var3 != null) {
            return libc().syscall(var3.__NR_ioprio_get(), arg0, arg1);
        } else {
            handler.unimplementedError("ioprio_get");
            return -1;
        }
    }

  public int ioprio_set(int arg0, int arg1, int arg2) {
        LinuxPOSIX_Syscall_ABI var4 = LinuxPOSIX_Syscall.abi();
        if (var4 != null) {
            return libc().syscall(var4.__NR_ioprio_set(), arg0, arg1, arg2);
        } else {
            handler.unimplementedError("ioprio_set");
            return -1;
        }
    }

  public int posix_fadvise(int arg0, long arg1, long arg2, PosixFadvise arg3) {
        return (((LinuxLibC) libc())).posix_fadvise(arg0, arg1, arg2, arg3.intValue());
    }

}