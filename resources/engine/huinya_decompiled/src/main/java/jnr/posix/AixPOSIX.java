// исходный (обфусцированный) внутренний класс: jnr.posix.AixPOSIX
package jnr.posix;

import java.nio.ByteBuffer;
import jnr.constants.platform.Confstr;
import jnr.constants.platform.Fcntl;
import jnr.constants.platform.Pathconf;
import jnr.constants.platform.Sysconf;
import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import jnr.posix.AixFileStat;
import jnr.posix.AixFlock;
import jnr.posix.AixPOSIX_Anon1;
import jnr.posix.AixPOSIX_FlockFlags;
import jnr.posix.AixTimeval;
import jnr.posix.BaseNativePOSIX;
import jnr.posix.BaseNativePOSIX_PointerConverter;
import jnr.posix.FileStat;
import jnr.posix.Flock;
import jnr.posix.LibC;
import jnr.posix.LibCProvider;
import jnr.posix.MsgHdr;
import jnr.posix.NativeTimes;
import jnr.posix.POSIXHandler;
import jnr.posix.SocketMacros;
import jnr.posix.Times;
import jnr.posix.Timeval;
import jnr.posix.util.MethodName;

final class AixPOSIX extends BaseNativePOSIX {

    // ---- поля ----
  public static final BaseNativePOSIX_PointerConverter PASSWD;

    static {
        PASSWD = new AixPOSIX_Anon1();
    }

   AixPOSIX(LibCProvider arg0, POSIXHandler arg1) { // было: <init>
        super(arg0, arg1);
    }

  public FileStat allocateStat() {
        return new AixFileStat(this);
    }

  public MsgHdr allocateMsgHdr() {
        handler.unimplementedError(MethodName.getCallerMethodName());
        return null;
    }

  public SocketMacros socketMacros() {
        handler.unimplementedError(MethodName.getCallerMethodName());
        return null;
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

  public Pointer allocatePosixSpawnFileActions() {
        return Memory.allocateDirect(getRuntime(), 4);
    }

  public Pointer allocatePosixSpawnattr() {
        return Memory.allocateDirect(getRuntime(), 60);
    }

  public int flock(int arg0, int arg1) {
        int var3 = Fcntl.F_SETLKW.intValue();
        int var4 = 0;
        if ((arg1 & AixPOSIX_FlockFlags.LOCK_SH.intValue()) == 0) {
            if ((arg1 & AixPOSIX_FlockFlags.LOCK_EX.intValue()) == 0) {
                if ((arg1 & AixPOSIX_FlockFlags.LOCK_UN.intValue()) != 0) {
                    var4 = ((short) Fcntl.F_UNLCK.intValue());
                }
            } else {
                var4 = ((short) Fcntl.F_WRLCK.intValue());
            }
        } else {
            var4 = ((short) Fcntl.F_RDLCK.intValue());
        }
        if ((arg1 & AixPOSIX_FlockFlags.LOCK_NB.intValue()) != 0) {
            var3 = Fcntl.F_SETLK.intValue();
        }
        Flock var5 = allocateFlock();
        var5.type(var4);
        var5.whence(0);
        var5.start(0L);
        var5.len(0L);
        return libc().fcntl(arg0, var3, var5);
    }

  public Timeval allocateTimeval() {
        return new AixTimeval(getRuntime());
    }

  public Flock allocateFlock() {
        return new AixFlock(getRuntime());
    }

}