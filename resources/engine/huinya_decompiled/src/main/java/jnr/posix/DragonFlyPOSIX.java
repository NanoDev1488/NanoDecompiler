// исходный (обфусцированный) внутренний класс: jnr.posix.DragonFlyPOSIX
package jnr.posix;

import java.nio.ByteBuffer;
import jnr.constants.platform.Confstr;
import jnr.constants.platform.Pathconf;
import jnr.constants.platform.Sysconf;
import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import jnr.posix.BaseNativePOSIX;
import jnr.posix.BaseNativePOSIX_PointerConverter;
import jnr.posix.DragonFlyFileStat;
import jnr.posix.DragonFlyPOSIX_Anon1;
import jnr.posix.FileStat;
import jnr.posix.LibC;
import jnr.posix.LibCProvider;
import jnr.posix.MsgHdr;
import jnr.posix.NativeTimes;
import jnr.posix.POSIXHandler;
import jnr.posix.SocketMacros;
import jnr.posix.Times;
import jnr.posix.util.MethodName;

final class DragonFlyPOSIX extends BaseNativePOSIX {

    // ---- поля ----
  public static final BaseNativePOSIX_PointerConverter PASSWD;

    static {
        PASSWD = new DragonFlyPOSIX_Anon1();
    }

   DragonFlyPOSIX(LibCProvider arg0, POSIXHandler arg1) { // было: <init>
        super(arg0, arg1);
    }

  public FileStat allocateStat() {
        return new DragonFlyFileStat(this);
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
        return Memory.allocateDirect(getRuntime(), 8);
    }

  public Pointer allocatePosixSpawnattr() {
        return Memory.allocateDirect(getRuntime(), 8);
    }

}