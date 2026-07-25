// исходный (обфусцированный) внутренний класс: jnr.posix.SolarisPOSIX
package jnr.posix;

import java.nio.ByteBuffer;
import jnr.constants.platform.Confstr;
import jnr.constants.platform.Pathconf;
import jnr.constants.platform.Sysconf;
import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.posix.BaseNativePOSIX;
import jnr.posix.BaseNativePOSIX_PointerConverter;
import jnr.posix.FileStat;
import jnr.posix.LibC;
import jnr.posix.LibCProvider;
import jnr.posix.MsgHdr;
import jnr.posix.NativeTimes;
import jnr.posix.POSIXHandler;
import jnr.posix.SocketMacros;
import jnr.posix.SolarisFileStat32;
import jnr.posix.SolarisFileStat64;
import jnr.posix.SolarisPOSIX_Anon1;
import jnr.posix.SolarisPOSIX_Layout;
import jnr.posix.Times;
import jnr.posix.util.MethodName;
import jnr.posix.util.Platform;

final class SolarisPOSIX extends BaseNativePOSIX {

    // ---- поля ----
  public static final int LOCK_SH = 1;
  public static final int LOCK_EX = 2;
  public static final int LOCK_NB = 4;
  public static final int LOCK_UN = 8;
  public static final int SEEK_SET = 0;
  private static final SolarisPOSIX_Layout FLOCK_LAYOUT;
  public static final BaseNativePOSIX_PointerConverter PASSWD;

    static {
        FLOCK_LAYOUT = new SolarisPOSIX_Layout(Runtime.getSystemRuntime());
        PASSWD = new SolarisPOSIX_Anon1();
    }

   SolarisPOSIX(LibCProvider arg0, POSIXHandler arg1) { // было: <init>
        super(arg0, arg1);
    }

  public FileStat allocateStat() {
        return !Platform.IS_32_BIT ? new SolarisFileStat64(this) : new SolarisFileStat32(this);
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

  public int flock(int arg0, int arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #52 // jnr.posix.SolarisPOSIX.getRuntime:()Ljnr/ffi/Runtime;
        //      4: invokevirtual  #43 // jnr.ffi.Runtime.getMemoryManager:()Ljnr/ffi/provider/MemoryManager;
        //      7: getstatic  #32 // jnr.posix.SolarisPOSIX.FLOCK_LAYOUT:Ljnr/posix/SolarisPOSIX$Layout;
        //     10: invokevirtual  #56 // jnr.posix.SolarisPOSIX$Layout.size:()I
        //     13: iconst_1
        //     14: invokeinterface  #58 // jnr.ffi.provider.MemoryManager.allocateTemporary:(IZ)Ljnr/ffi/Pointer;, count 3
        //     19: astore_3
        //     20: iload_2
        //     21: bipush  -5
        //     23: iand
        //     24: lookupswitch  default->123, 1->60, 2->81, 8->102
        //     60: getstatic  #32 // jnr.posix.SolarisPOSIX.FLOCK_LAYOUT:Ljnr/posix/SolarisPOSIX$Layout;
        //     63: getfield  #37 // jnr.posix.SolarisPOSIX$Layout.l_type:Ljnr/ffi/StructLayout$int16_t;
        //     66: aload_3
        //     67: getstatic  #27 // jnr.constants.platform.Fcntl.F_RDLCK:Ljnr/constants/platform/Fcntl;
        //     70: invokevirtual  #41 // jnr.constants.platform.Fcntl.intValue:()I
        //     73: i2s
        //     74: i2l
        //     75: invokevirtual  #45 // jnr.ffi.StructLayout$int16_t.set:(Ljnr/ffi/Pointer;J)V
        //     78: goto  135 (offset +57)
        //     81: getstatic  #32 // jnr.posix.SolarisPOSIX.FLOCK_LAYOUT:Ljnr/posix/SolarisPOSIX$Layout;
        //     84: getfield  #37 // jnr.posix.SolarisPOSIX$Layout.l_type:Ljnr/ffi/StructLayout$int16_t;
        //     87: aload_3
        //     88: getstatic  #31 // jnr.constants.platform.Fcntl.F_WRLCK:Ljnr/constants/platform/Fcntl;
        //     91: invokevirtual  #41 // jnr.constants.platform.Fcntl.intValue:()I
        //     94: i2s
        //     95: i2l
        //     96: invokevirtual  #45 // jnr.ffi.StructLayout$int16_t.set:(Ljnr/ffi/Pointer;J)V
        //     99: goto  135 (offset +36)
        //    102: getstatic  #32 // jnr.posix.SolarisPOSIX.FLOCK_LAYOUT:Ljnr/posix/SolarisPOSIX$Layout;
        //    105: getfield  #37 // jnr.posix.SolarisPOSIX$Layout.l_type:Ljnr/ffi/StructLayout$int16_t;
        //    108: aload_3
        //    109: getstatic  #30 // jnr.constants.platform.Fcntl.F_UNLCK:Ljnr/constants/platform/Fcntl;
        //    112: invokevirtual  #41 // jnr.constants.platform.Fcntl.intValue:()I
        //    115: i2s
        //    116: i2l
        //    117: invokevirtual  #45 // jnr.ffi.StructLayout$int16_t.set:(Ljnr/ffi/Pointer;J)V
        //    120: goto  135 (offset +15)
        //    123: aload_0
        //    124: getstatic  #26 // jnr.constants.platform.Errno.EINVAL:Ljnr/constants/platform/Errno;
        //    127: invokevirtual  #40 // jnr.constants.platform.Errno.intValue:()I
        //    130: invokevirtual  #51 // jnr.posix.SolarisPOSIX.errno:(I)V
        //    133: iconst_m1
        //    134: ireturn
        //    135: getstatic  #32 // jnr.posix.SolarisPOSIX.FLOCK_LAYOUT:Ljnr/posix/SolarisPOSIX$Layout;
        //    138: getfield  #38 // jnr.posix.SolarisPOSIX$Layout.l_whence:Ljnr/ffi/StructLayout$int16_t;
        //    141: aload_3
        //    142: lconst_0
        //    143: invokevirtual  #45 // jnr.ffi.StructLayout$int16_t.set:(Ljnr/ffi/Pointer;J)V
        //    146: getstatic  #32 // jnr.posix.SolarisPOSIX.FLOCK_LAYOUT:Ljnr/posix/SolarisPOSIX$Layout;
        //    149: getfield  #36 // jnr.posix.SolarisPOSIX$Layout.l_start:Ljnr/ffi/StructLayout$off_t;
        //    152: aload_3
        //    153: lconst_0
        //    154: invokevirtual  #46 // jnr.ffi.StructLayout$off_t.set:(Ljnr/ffi/Pointer;J)V
        //    157: getstatic  #32 // jnr.posix.SolarisPOSIX.FLOCK_LAYOUT:Ljnr/posix/SolarisPOSIX$Layout;
        //    160: getfield  #35 // jnr.posix.SolarisPOSIX$Layout.l_len:Ljnr/ffi/StructLayout$off_t;
        //    163: aload_3
        //    164: lconst_0
        //    165: invokevirtual  #46 // jnr.ffi.StructLayout$off_t.set:(Ljnr/ffi/Pointer;J)V
        //    168: aload_0
        //    169: invokevirtual  #53 // jnr.posix.SolarisPOSIX.libc:()Ljnr/posix/LibC;
        //    172: iload_1
        //    173: iload_2
        //    174: iconst_4
        //    175: iand
        //    176: ifeq  188 (offset +12)
        //    179: getstatic  #28 // jnr.constants.platform.Fcntl.F_SETLK:Ljnr/constants/platform/Fcntl;
        //    182: invokevirtual  #41 // jnr.constants.platform.Fcntl.intValue:()I
        //    185: goto  194 (offset +9)
        //    188: getstatic  #29 // jnr.constants.platform.Fcntl.F_SETLKW:Ljnr/constants/platform/Fcntl;
        //    191: invokevirtual  #41 // jnr.constants.platform.Fcntl.intValue:()I
        //    194: aload_3
        //    195: invokeinterface  #60 // jnr.posix.LibC.fcntl:(IILjnr/ffi/Pointer;)I, count 4
        //    200: ireturn
    }

  public Pointer allocatePosixSpawnFileActions() {
        return Memory.allocateDirect(getRuntime(), 8);
    }

  public Pointer allocatePosixSpawnattr() {
        return Memory.allocateDirect(getRuntime(), 8);
    }

}