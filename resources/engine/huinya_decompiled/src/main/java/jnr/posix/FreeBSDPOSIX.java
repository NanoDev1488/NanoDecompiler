// исходный (обфусцированный) внутренний класс: jnr.posix.FreeBSDPOSIX
package jnr.posix;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.text.NumberFormat;
import java.text.ParsePosition;
import jnr.constants.platform.Confstr;
import jnr.constants.platform.Pathconf;
import jnr.constants.platform.Sysconf;
import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import jnr.posix.BaseNativePOSIX;
import jnr.posix.BaseNativePOSIX_PointerConverter;
import jnr.posix.FileStat;
import jnr.posix.FreeBSDFileStat;
import jnr.posix.FreeBSDFileStat12;
import jnr.posix.FreeBSDMsgHdr;
import jnr.posix.FreeBSDPOSIX_Anon1;
import jnr.posix.FreeBSDSocketMacros;
import jnr.posix.LibC;
import jnr.posix.LibCProvider;
import jnr.posix.MsgHdr;
import jnr.posix.NativeTimes;
import jnr.posix.POSIXHandler;
import jnr.posix.SocketMacros;
import jnr.posix.Times;

final class FreeBSDPOSIX extends BaseNativePOSIX {

    // ---- поля ----
  private final int freebsdVersion;
  public static final BaseNativePOSIX_PointerConverter PASSWD;

    static {
        PASSWD = new FreeBSDPOSIX_Anon1();
    }

   FreeBSDPOSIX(LibCProvider arg0, POSIXHandler arg1) { // было: <init>
        super(arg0, arg1);
        int var3 = 0;
        try {
            Process var4 = Runtime.getRuntime().exec("/bin/freebsd-version -u");
            String var5 = new BufferedReader(new InputStreamReader(var4.getInputStream())).readLine();
            if (var4.waitFor() == 0) {
                if (var5 != null) {
                    NumberFormat var6 = NumberFormat.getIntegerInstance();
                    var6.setGroupingUsed(false);
                    var3 = var6.parse(var5, new ParsePosition(0)).intValue();
                }
            }
            freebsdVersion = var3;
            return;
        } catch (Exception e1) {
            Throwable var4 = e1;
        }
        freebsdVersion = var3;
    }

  public FileStat allocateStat() {
        if (freebsdVersion < 12) {
            return new FreeBSDFileStat(this);
        } else {
            return new FreeBSDFileStat12(this);
        }
    }

  public MsgHdr allocateMsgHdr() {
        return new FreeBSDMsgHdr(this);
    }

  public SocketMacros socketMacros() {
        return FreeBSDSocketMacros.INSTANCE;
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