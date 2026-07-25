// исходный (обфусцированный) внутренний класс: jnr.posix.MacOSPOSIX
package jnr.posix;

import java.nio.ByteBuffer;
import jnr.constants.platform.Confstr;
import jnr.constants.platform.Pathconf;
import jnr.constants.platform.Sysconf;
import jnr.ffi.LibraryLoader;
import jnr.ffi.Memory;
import jnr.ffi.Platform;
import jnr.ffi.Platform_CPU;
import jnr.ffi.Pointer;
import jnr.posix.BaseNativePOSIX;
import jnr.posix.BaseNativePOSIX_PointerConverter;
import jnr.posix.FileStat;
import jnr.posix.LibC;
import jnr.posix.LibCProvider;
import jnr.posix.MacOSFileStat;
import jnr.posix.MacOSFileStat64Inode;
import jnr.posix.MacOSMsgHdr;
import jnr.posix.MacOSPOSIX_Anon1;
import jnr.posix.MacOSSocketMacros;
import jnr.posix.MsgHdr;
import jnr.posix.NSGetEnviron;
import jnr.posix.NativeTimes;
import jnr.posix.POSIXHandler;
import jnr.posix.SocketMacros;
import jnr.posix.Times;

final class MacOSPOSIX extends BaseNativePOSIX {

    // ---- поля ----
  private final NSGetEnviron environ;
  public static final BaseNativePOSIX_PointerConverter PASSWD;

    static {
        PASSWD = new MacOSPOSIX_Anon1();
    }

   MacOSPOSIX(LibCProvider arg0, POSIXHandler arg1) { // было: <init>
        super(arg0, arg1);
        LibraryLoader var3 = LibraryLoader.create(NSGetEnviron.class);
        var3.library("libSystem.B.dylib");
        environ = ((NSGetEnviron) var3.load());
    }

  public FileStat allocateStat() {
        if (Platform.getNativePlatform().getCPU() != Platform_CPU.AARCH64) {
            return new MacOSFileStat(this);
        } else {
            return new MacOSFileStat64Inode(this);
        }
    }

  public MsgHdr allocateMsgHdr() {
        return new MacOSMsgHdr(this);
    }

  public Pointer allocatePosixSpawnFileActions() {
        return Memory.allocateDirect(getRuntime(), 8);
    }

  public Pointer allocatePosixSpawnattr() {
        return Memory.allocateDirect(getRuntime(), 8);
    }

  public SocketMacros socketMacros() {
        return MacOSSocketMacros.INSTANCE;
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

  public Pointer environ() {
        return environ._NSGetEnviron().getPointer(0L);
    }

}