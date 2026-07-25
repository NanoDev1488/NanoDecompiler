// исходный (обфусцированный) внутренний класс: jnr.posix.BaseNativePOSIX
package jnr.posix;

import java.io.FileDescriptor;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import jnr.constants.platform.Confstr;
import jnr.constants.platform.Errno;
import jnr.constants.platform.Fcntl;
import jnr.constants.platform.Pathconf;
import jnr.constants.platform.Signal;
import jnr.constants.platform.Sysconf;
import jnr.ffi.LastError;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.TypeAlias;
import jnr.ffi.Variable;
import jnr.ffi.byref.NumberByReference;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.provider.MemoryManager;
import jnr.posix.BaseNativePOSIX_Anon2;
import jnr.posix.BaseNativePOSIX_Anon3;
import jnr.posix.BaseNativePOSIX_Anon4;
import jnr.posix.BaseNativePOSIX_Anon5;
import jnr.posix.BaseNativePOSIX_Anon6;
import jnr.posix.BaseNativePOSIX_PointerConverter;
import jnr.posix.Crypt;
import jnr.posix.DefaultNativeRLimit;
import jnr.posix.DefaultNativeTimespec;
import jnr.posix.DefaultNativeTimeval;
import jnr.posix.FileStat;
import jnr.posix.Group;
import jnr.posix.JavaLibCHelper;
import jnr.posix.JavaTimes;
import jnr.posix.LibC;
import jnr.posix.LibCProvider;
import jnr.posix.MsgHdr;
import jnr.posix.NativePOSIX;
import jnr.posix.POSIX;
import jnr.posix.POSIXHandler;
import jnr.posix.Passwd;
import jnr.posix.RLimit;
import jnr.posix.SignalHandler;
import jnr.posix.SpawnAttribute;
import jnr.posix.SpawnFileAction;
import jnr.posix.Times;
import jnr.posix.Timespec;
import jnr.posix.Timeval;
import jnr.posix.UnixLibC;
import jnr.posix.util.Java5ProcessMaker;
import jnr.posix.util.MethodName;
import jnr.posix.util.ProcessMaker;

public abstract class BaseNativePOSIX extends NativePOSIX implements POSIX {

    // ---- поля ----
  private final LibC libc;
  private final Crypt crypt;
  protected final POSIXHandler handler;
  protected final JavaLibCHelper helper;
  protected final Map signalHandlers;
  public static final BaseNativePOSIX_PointerConverter GROUP;
  public static final ToNativeConverter FileStatConverter;
  public static final ToNativeConverter TimesConverter;
  public static final ToNativeConverter ConstantConverter;
  public static final ToNativeConverter MsgHdrConverter;

    static {
        GROUP = new BaseNativePOSIX_Anon2();
        FileStatConverter = new BaseNativePOSIX_Anon3();
        TimesConverter = new BaseNativePOSIX_Anon4();
        ConstantConverter = new BaseNativePOSIX_Anon5();
        MsgHdrConverter = new BaseNativePOSIX_Anon6();
    }

  protected BaseNativePOSIX(LibCProvider arg0, POSIXHandler arg1) { // было: <init>
        super();
        signalHandlers = new HashMap();
        handler = arg1;
        libc = arg0.getLibC();
        crypt = arg0.getCrypt();
        helper = new JavaLibCHelper(arg1);
    }

  public ProcessMaker newProcessMaker(String[] arg0) {
        return new Java5ProcessMaker(handler, arg0);
    }

  public ProcessMaker newProcessMaker() {
        return new Java5ProcessMaker(handler);
    }

  public final LibC libc() {
        return libc;
    }

  public final Crypt crypt() {
        return crypt;
    }

   POSIXHandler handler() {
        return handler;
    }

  protected Object unimplementedNull() {
        handler().unimplementedError(MethodName.getCallerMethodName());
        return null;
    }

  protected int unimplementedInt() {
        handler().unimplementedError(MethodName.getCallerMethodName());
        return -1;
    }

  public int chmod(String arg0, int arg1) {
        return libc().chmod(arg0, arg1);
    }

  public int fchmod(int arg0, int arg1) {
        return libc().fchmod(arg0, arg1);
    }

  public int chown(String arg0, int arg1, int arg2) {
        return libc().chown(arg0, arg1, arg2);
    }

  public int fchown(int arg0, int arg1, int arg2) {
        return libc().fchown(arg0, arg1, arg2);
    }

  public CharSequence crypt(CharSequence arg0, CharSequence arg1) {
        Crypt var3 = crypt();
        if (var3 != null) {
            return var3.crypt(arg0, arg1);
        } else {
            return JavaLibCHelper.crypt(arg0, arg1);
        }
    }

  public byte[] crypt(byte[] arg0, byte[] arg1) {
        Crypt var3 = crypt();
        if (var3 != null) {
            Pointer var4 = crypt().crypt(arg0, arg1);
            if (var4 != null) {
                int var5 = var4.indexOf(0L, 0);
                byte[] var6 = new byte[var5 + 1];
                var4.get(0L, var6, 0, var5);
                return var6;
            } else {
                return null;
            }
        } else {
            return JavaLibCHelper.crypt(arg0, arg1);
        }
    }

  public int exec(String arg0, String[] arg1) {
        handler.unimplementedError("exec unimplemented");
        return -1;
    }

  public int exec(String arg0, String[] arg1, String[] arg2) {
        handler.unimplementedError("exec unimplemented");
        return -1;
    }

  public int execv(String arg0, String[] arg1) {
        return libc().execv(arg0, arg1);
    }

  public int execve(String arg0, String[] arg1, String[] arg2) {
        return libc().execve(arg0, arg1, arg2);
    }

  public FileStat fstat(FileDescriptor arg0) {
        FileStat var2 = allocateStat();
        if (fstat(arg0, var2) < 0) {
            handler.error(Errno.valueOf(((long) errno())), "fstat", new StringBuilder().append("").append(helper.getfd(arg0)).toString());
        }
        return var2;
    }

  public FileStat fstat(int arg0) {
        FileStat var2 = allocateStat();
        if (fstat(arg0, var2) < 0) {
            handler.error(Errno.valueOf(((long) errno())), "fstat", new StringBuilder().append("").append(arg0).toString());
        }
        return var2;
    }

  public int fstat(FileDescriptor arg0, FileStat arg1) {
        int var3 = helper.getfd(arg0);
        return libc().fstat(var3, arg1);
    }

  public int fstat(int arg0, FileStat arg1) {
        return libc().fstat(arg0, arg1);
    }

  public Pointer environ() {
        return getRuntime().getMemoryManager().newPointer((((Long) libc().environ().get())).longValue());
    }

  public String getenv(String arg0) {
        return libc().getenv(arg0);
    }

  public int getegid() {
        return libc().getegid();
    }

  public int geteuid() {
        return libc().geteuid();
    }

  public int getgid() {
        return libc().getgid();
    }

  public int getdtablesize() {
        return libc().getdtablesize();
    }

  public String getlogin() {
        return libc().getlogin();
    }

  public int getpgid() {
        return libc().getpgid();
    }

  public int getpgrp() {
        return libc().getpgrp();
    }

  public int getpid() {
        return libc().getpid();
    }

  public int getppid() {
        return libc().getppid();
    }

  public Passwd getpwent() {
        return libc().getpwent();
    }

  public Passwd getpwuid(int arg0) {
        return libc().getpwuid(arg0);
    }

  public Passwd getpwnam(String arg0) {
        return libc().getpwnam(arg0);
    }

  public Group getgrent() {
        return libc().getgrent();
    }

  public Group getgrgid(int arg0) {
        return libc().getgrgid(arg0);
    }

  public Group getgrnam(String arg0) {
        return libc().getgrnam(arg0);
    }

  public int setpwent() {
        return libc().setpwent();
    }

  public int endpwent() {
        return libc().endpwent();
    }

  public int setgrent() {
        return libc().setgrent();
    }

  public int endgrent() {
        return libc().endgrent();
    }

  public int getuid() {
        return libc().getuid();
    }

  public int getrlimit(int arg0, RLimit arg1) {
        return libc().getrlimit(arg0, arg1);
    }

  public int getrlimit(int arg0, Pointer arg1) {
        return libc().getrlimit(arg0, arg1);
    }

  public RLimit getrlimit(int arg0) {
        DefaultNativeRLimit var2 = new DefaultNativeRLimit(getRuntime());
        if (getrlimit(arg0, var2) < 0) {
            handler.error(Errno.valueOf(((long) errno())), "rlim");
        }
        return var2;
    }

  public int setrlimit(int arg0, RLimit arg1) {
        return libc().setrlimit(arg0, arg1);
    }

  public int setrlimit(int arg0, Pointer arg1) {
        return libc().setrlimit(arg0, arg1);
    }

  public int setrlimit(int arg0, long arg1, long arg2) {
        DefaultNativeRLimit var6 = new DefaultNativeRLimit(getRuntime());
        var6.init(arg1, arg2);
        return libc().setrlimit(arg0, var6);
    }

  public int setegid(int arg0) {
        return libc().setegid(arg0);
    }

  public int seteuid(int arg0) {
        return libc().seteuid(arg0);
    }

  public int setgid(int arg0) {
        return libc().setgid(arg0);
    }

  public int getfd(FileDescriptor arg0) {
        return helper.getfd(arg0);
    }

  public int getpgid(int arg0) {
        return libc().getpgid(arg0);
    }

  public int setpgid(int arg0, int arg1) {
        return libc().setpgid(arg0, arg1);
    }

  public int setpgrp(int arg0, int arg1) {
        return libc().setpgrp(arg0, arg1);
    }

  public int setsid() {
        return libc().setsid();
    }

  public int setuid(int arg0) {
        return libc().setuid(arg0);
    }

  public int kill(int arg0, int arg1) {
        return kill(((long) arg0), arg1);
    }

  public int kill(long arg0, int arg1) {
        return libc().kill(arg0, arg1);
    }

  public SignalHandler signal(Signal arg0, SignalHandler arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #86 // jnr.posix.BaseNativePOSIX.signalHandlers:Ljava/util/Map;
        //      4: dup
        //      5: astore_3
        //      6: monitorenter
        //      7: aload_0
        //      8: getfield  #86 // jnr.posix.BaseNativePOSIX.signalHandlers:Ljava/util/Map;
        //     11: aload_1
        //     12: invokeinterface  #174 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     17: checkcast  #61 // jnr.posix.SignalHandler
        //     20: astore  4
        //     22: aload_0
        //     23: invokevirtual  #136 // jnr.posix.BaseNativePOSIX.libc:()Ljnr/posix/LibC;
        //     26: aload_1
        //     27: invokevirtual  #111 // jnr.constants.platform.Signal.intValue:()I
        //     30: new  #40 // jnr.posix.BaseNativePOSIX$1
        //     33: dup
        //     34: aload_0
        //     35: aload_2
        //     36: invokespecial  #145 // jnr.posix.BaseNativePOSIX$1.<init>:(Ljnr/posix/BaseNativePOSIX;Ljnr/posix/SignalHandler;)V
        //     39: invokeinterface  #277 // jnr.posix.LibC.signal:(ILjnr/posix/LibC$LibCSignalHandler;)J, count 3
        //     44: lstore  5
        //     46: lload  5
        //     48: ldc2_w  #69 // -1L
        //     51: lcmp
        //     52: ifeq  67 (offset +15)
        //     55: aload_0
        //     56: getfield  #86 // jnr.posix.BaseNativePOSIX.signalHandlers:Ljava/util/Map;
        //     59: aload_1
        //     60: aload_2
        //     61: invokeinterface  #175 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //     66: pop
        //     67: aload  4
        //     69: aload_3
        //     70: monitorexit
        //     71: areturn
        //     72: astore  7
        //     74: aload_3
        //     75: monitorexit
        //     76: aload  7
        //     78: athrow
        //       Exception table:
        //         from 7 to 71 target 72 type any
        //         from 72 to 76 target 72 type any
    }

  public int raise(int arg0) {
        return libc().raise(arg0);
    }

  public int lchmod(String arg0, int arg1) {
        int __stk1;
        try {
            __stk1 = libc().lchmod(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int lchown(String arg0, int arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = libc().lchown(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int link(String arg0, String arg1) {
        return libc().link(arg0, arg1);
    }

  public FileStat lstat(String arg0) {
        FileStat var2 = allocateStat();
        if (lstat(arg0, var2) < 0) {
            handler.error(Errno.valueOf(((long) errno())), "lstat", arg0);
        }
        return var2;
    }

  public int lstat(String arg0, FileStat arg1) {
        return libc().lstat(arg0, arg1);
    }

  public int mkdir(String arg0, int arg1) {
        int var3 = libc().mkdir(arg0, arg1);
        if (var3 < 0) {
            int var4 = errno();
            handler.error(Errno.valueOf(((long) var4)), "mkdir", arg0);
        }
        return var3;
    }

  public int rmdir(String arg0) {
        int var2 = libc().rmdir(arg0);
        if (var2 < 0) {
            handler.error(Errno.valueOf(((long) errno())), "rmdir", arg0);
        }
        return var2;
    }

  public int setenv(String arg0, String arg1, int arg2) {
        return libc().setenv(arg0, arg1, arg2);
    }

  public FileStat stat(String arg0) {
        FileStat var2 = allocateStat();
        if (stat(arg0, var2) < 0) {
            handler.error(Errno.valueOf(((long) errno())), "stat", arg0);
        }
        return var2;
    }

  public int stat(String arg0, FileStat arg1) {
        return libc().stat(arg0, arg1);
    }

  public int symlink(String arg0, String arg1) {
        return libc().symlink(arg0, arg1);
    }

  public String readlink(String arg0) {
        ByteBuffer var2 = ByteBuffer.allocate(1024);
        int var3 = libc().readlink(arg0, var2, var2.capacity());
        if (var3 != -1) {
            var2.position(0);
            var2.limit(var3);
            return Charset.defaultCharset().decode(var2).toString();
        } else {
            return null;
        }
    }

  public int readlink(CharSequence arg0, byte[] arg1, int arg2) {
        return libc().readlink(arg0, arg1, arg2);
    }

  public int readlink(CharSequence arg0, ByteBuffer arg1, int arg2) {
        return libc().readlink(arg0, arg1, arg2);
    }

  public int readlink(CharSequence arg0, Pointer arg1, int arg2) {
        return libc().readlink(arg0, arg1, arg2);
    }

  public int unsetenv(String arg0) {
        return libc().unsetenv(arg0);
    }

  public int umask(int arg0) {
        return libc().umask(arg0);
    }

  public int utimes(String arg0, long[] arg1, long[] arg2) {
        Object var4 = null;
        if (arg1 != null) {
            if (arg2 != null) {
                var4 = ((Timeval[]) Struct.arrayOf(getRuntime(), DefaultNativeTimeval.class, 2));
                var4[0].setTime(arg1);
                var4[1].setTime(arg2);
            }
        }
        return libc().utimes(arg0, ((Timeval[]) var4));
    }

  public int utimes(String arg0, Pointer arg1) {
        return libc().utimes(arg0, arg1);
    }

  public int futimes(int arg0, long[] arg1, long[] arg2) {
        Object var4 = null;
        if (arg1 != null) {
            if (arg2 != null) {
                var4 = ((Timeval[]) Struct.arrayOf(getRuntime(), DefaultNativeTimeval.class, 2));
                var4[0].setTime(arg1);
                var4[1].setTime(arg2);
            }
        }
        return libc().futimes(arg0, ((Timeval[]) var4));
    }

  public int lutimes(String arg0, long[] arg1, long[] arg2) {
        Object var4 = null;
        if (arg1 != null) {
            if (arg2 != null) {
                var4 = ((Timeval[]) Struct.arrayOf(getRuntime(), DefaultNativeTimeval.class, 2));
                var4[0].setTime(arg1);
                var4[1].setTime(arg2);
            }
        }
        return libc().lutimes(arg0, ((Timeval[]) var4));
    }

  public int utimensat(int arg0, String arg1, long[] arg2, long[] arg3, int arg4) {
        Object var6 = null;
        if (arg2 != null) {
            if (arg3 != null) {
                var6 = ((Timespec[]) Struct.arrayOf(getRuntime(), DefaultNativeTimespec.class, 2));
                var6[0].setTime(arg2);
                var6[1].setTime(arg3);
            }
        }
        return libc().utimensat(arg0, arg1, ((Timespec[]) var6), arg4);
    }

  public int utimensat(int arg0, String arg1, Pointer arg2, int arg3) {
        return libc().utimensat(arg0, arg1, arg2, arg3);
    }

  public int futimens(int arg0, long[] arg1, long[] arg2) {
        Object var4 = null;
        if (arg1 != null) {
            if (arg2 != null) {
                var4 = ((Timespec[]) Struct.arrayOf(getRuntime(), DefaultNativeTimespec.class, 2));
                var4[0].setTime(arg1);
                var4[1].setTime(arg2);
            }
        }
        return libc().futimens(arg0, ((Timespec[]) var4));
    }

  public int futimens(int arg0, Pointer arg1) {
        return libc().futimens(arg0, arg1);
    }

  public int fork() {
        return libc().fork();
    }

  public int waitpid(int arg0, int[] arg1, int arg2) {
        return waitpid(((long) arg0), arg1, arg2);
    }

  public int waitpid(long arg0, int[] arg1, int arg2) {
        return libc().waitpid(arg0, arg1, arg2);
    }

  public int wait(int[] arg0) {
        return libc().wait(arg0);
    }

  public int getpriority(int arg0, int arg1) {
        return libc().getpriority(arg0, arg1);
    }

  public int setpriority(int arg0, int arg1, int arg2) {
        return libc().setpriority(arg0, arg1, arg2);
    }

  public boolean isatty(FileDescriptor arg0) {
        return isatty(helper.getfd(arg0)) != 0;
    }

  public int isatty(int arg0) {
        return libc().isatty(arg0);
    }

  public int errno() {
        return LastError.getLastError(getRuntime());
    }

  public void errno(int arg0) {
        LastError.setLastError(getRuntime(), arg0);
    }

  public int chdir(String arg0) {
        return libc().chdir(arg0);
    }

  public boolean isNative() {
        return true;
    }

  public long posix_spawnp(String arg0, Collection arg1, CharSequence[] arg2, CharSequence[] arg3) {
        return posix_spawnp(arg0, arg1, null, arg2, arg3);
    }

  public long posix_spawnp(String arg0, Collection arg1, Collection arg2, Collection arg3) {
        return posix_spawnp(arg0, arg1, null, arg2, arg3);
    }

  public long posix_spawnp(String arg0, Collection arg1, Collection arg2, Collection arg3, Collection arg4) {
        CharSequence[] var6 = new CharSequence[arg3.size()];
        arg3.toArray(var6);
        CharSequence[] var7 = new CharSequence[arg4.size()];
        arg4.toArray(var7);
        return posix_spawnp(arg0, arg1, arg2, var6, var7);
    }

  public long posix_spawnp(String arg0, Collection arg1, Collection arg2, CharSequence[] arg3, CharSequence[] arg4) {
        Pointer __stk1;
        Pointer __stk2;
        NumberByReference var6 = new NumberByReference(TypeAlias.pid_t);
        __stk1 = arg1 == null ? null : arg1.isEmpty() ? null : nativeFileActions(arg1);
        Pointer var7 = __stk1;
        __stk2 = arg2 == null ? null : arg2.isEmpty() ? null : nativeSpawnAttributes(arg2);
        Pointer var8 = __stk2;
        try {
            long var9 = ((long) (((UnixLibC) libc())).posix_spawnp(var6, arg0, ((Pointer) var7), ((Pointer) var8), arg3, arg4));
        } catch (Throwable e2) {
            try {
                while (true) {
                    Throwable var11 = e2;
                }
            } catch (Throwable var11) {
            }
        }
    }

  public int flock(int arg0, int arg1) {
        return libc().flock(arg0, arg1);
    }

  public int dup(int arg0) {
        return libc().dup(arg0);
    }

  public int dup2(int arg0, int arg1) {
        return libc().dup2(arg0, arg1);
    }

  public int fcntlInt(int arg0, Fcntl arg1, int arg2) {
        return fcntl(arg0, arg1, arg2);
    }

  public int fcntl(int arg0, Fcntl arg1) {
        return libc().fcntl(arg0, arg1.intValue());
    }

  public int fcntl(int arg0, Fcntl arg1, int arg2) {
        return libc().fcntl(arg0, arg1.intValue(), arg2);
    }

    @Deprecated
  public int fcntl(int arg0, Fcntl arg1, int[] arg2) {
        if (arg2 == null) {
            throw new IllegalArgumentException("fcntl with variadic int args is unsupported");
        } else {
            if (arg2.length != 1) {
                throw new IllegalArgumentException("fcntl with variadic int args is unsupported");
            } else {
                return fcntl(arg0, arg1, arg2[0]);
            }
        }
    }

  public int access(CharSequence arg0, int arg1) {
        return libc().access(arg0, arg1);
    }

  public int close(int arg0) {
        return libc().close(arg0);
    }

  private Pointer nativeFileActions(Collection arg0) {
        Pointer var2 = allocatePosixSpawnFileActions();
        (((UnixLibC) libc())).posix_spawn_file_actions_init(var2);
        Iterator var3 = arg0.iterator();
        while (var3.hasNext()) {
            SpawnFileAction var4 = ((SpawnFileAction) var3.next());
            var4.act(this, var2);
            continue;
        }
        return var2;
    }

  private Pointer nativeSpawnAttributes(Collection arg0) {
        Pointer var2 = allocatePosixSpawnattr();
        (((UnixLibC) libc())).posix_spawnattr_init(var2);
        Iterator var3 = arg0.iterator();
        while (var3.hasNext()) {
            SpawnAttribute var4 = ((SpawnAttribute) var3.next());
            var4.set(this, var2);
            continue;
        }
        return var2;
    }

  public abstract FileStat allocateStat();

  public long sysconf(Sysconf arg0) {
        switch (arg0) {
            case _SC_CLK_TCK:
                return 1000L;
            default:
                errno(Errno.EOPNOTSUPP.intValue());
                return -1L;
        }
    }

  public int confstr(Confstr arg0, ByteBuffer arg1, int arg2) {
        errno(Errno.EOPNOTSUPP.intValue());
        return -1;
    }

  public int fpathconf(int arg0, Pathconf arg1) {
        errno(Errno.EOPNOTSUPP.intValue());
        return -1;
    }

  public Times times() {
        return new JavaTimes();
    }

  public int unlink(CharSequence arg0) {
        return libc().unlink(arg0);
    }

  public int open(CharSequence arg0, int arg1, int arg2) {
        return libc().open(arg0, arg1, arg2);
    }

  public long read(int arg0, byte[] arg1, long arg2) {
        return libc().read(arg0, arg1, arg2);
    }

  public long write(int arg0, byte[] arg1, long arg2) {
        return libc().write(arg0, arg1, arg2);
    }

  public long read(int arg0, ByteBuffer arg1, long arg2) {
        return libc().read(arg0, arg1, arg2);
    }

  public long write(int arg0, ByteBuffer arg1, long arg2) {
        return libc().write(arg0, arg1, arg2);
    }

  public long pread(int arg0, byte[] arg1, long arg2, long arg3) {
        return libc().pread(arg0, arg1, arg2, arg3);
    }

  public long pwrite(int arg0, byte[] arg1, long arg2, long arg3) {
        return libc().pwrite(arg0, arg1, arg2, arg3);
    }

  public long pread(int arg0, ByteBuffer arg1, long arg2, long arg3) {
        return libc().pread(arg0, arg1, arg2, arg3);
    }

  public long pwrite(int arg0, ByteBuffer arg1, long arg2, long arg3) {
        return libc().pwrite(arg0, arg1, arg2, arg3);
    }

  public int read(int arg0, byte[] arg1, int arg2) {
        return libc().read(arg0, arg1, arg2);
    }

  public int write(int arg0, byte[] arg1, int arg2) {
        return libc().write(arg0, arg1, arg2);
    }

  public int read(int arg0, ByteBuffer arg1, int arg2) {
        return libc().read(arg0, arg1, arg2);
    }

  public int write(int arg0, ByteBuffer arg1, int arg2) {
        return libc().write(arg0, arg1, arg2);
    }

  public int pread(int arg0, byte[] arg1, int arg2, int arg3) {
        return libc().pread(arg0, arg1, arg2, arg3);
    }

  public int pwrite(int arg0, byte[] arg1, int arg2, int arg3) {
        return libc().pwrite(arg0, arg1, arg2, arg3);
    }

  public int pread(int arg0, ByteBuffer arg1, int arg2, int arg3) {
        return libc().pread(arg0, arg1, arg2, arg3);
    }

  public int pwrite(int arg0, ByteBuffer arg1, int arg2, int arg3) {
        return libc().pwrite(arg0, arg1, arg2, arg3);
    }

  public int lseek(int arg0, long arg1, int arg2) {
        return ((int) libc().lseek(arg0, arg1, arg2));
    }

  public long lseekLong(int arg0, long arg1, int arg2) {
        return libc().lseek(arg0, arg1, arg2);
    }

  public int pipe(int[] arg0) {
        return libc().pipe(arg0);
    }

  public int socketpair(int arg0, int arg1, int arg2, int[] arg3) {
        return libc().socketpair(arg0, arg1, arg2, arg3);
    }

  public int sendmsg(int arg0, MsgHdr arg1, int arg2) {
        return libc().sendmsg(arg0, arg1, arg2);
    }

  public int recvmsg(int arg0, MsgHdr arg1, int arg2) {
        return libc().recvmsg(arg0, arg1, arg2);
    }

  public int truncate(CharSequence arg0, long arg1) {
        return libc().truncate(arg0, arg1);
    }

  public int ftruncate(int arg0, long arg1) {
        return libc().ftruncate(arg0, arg1);
    }

  public int rename(CharSequence arg0, CharSequence arg1) {
        return libc().rename(arg0, arg1);
    }

  public String gethostname() {
        ByteBuffer var1 = ByteBuffer.allocate(256);
        int var2;
        try {
            var2 = libc().gethostname(var1, var1.capacity() - 1);
        } catch (UnsatisfiedLinkError var3) {
            var2 = -1;
        }
        if (var2 != -1) {
            var1.position(0);
        } else {
            return helper.gethostname();
        }
        while (var1.hasRemaining()) {
            if (var1.get() == 0) {
                break;
            }
            continue;
        }
        var1.limit(var1.position() - 1);
        var1.position(0);
        return Charset.forName("US-ASCII").decode(var1).toString();
    }

  public String getcwd() {
        byte[] var1 = new byte[1024];
        long var2 = libc().getcwd(var1, 1024);
        int var4;
        if (var2 != -1L) {
            var4 = 0;
        } else {
            return null;
        }
        while (var4 < 1024) {
            if (var1[var4] != 0) {
                ++var4;
                continue;
            } else {
                break;
            }
        }
        return new String(var1, 0, var4);
    }

  public int fsync(int arg0) {
        return libc().fsync(arg0);
    }

  public int fdatasync(int arg0) {
        return libc().fdatasync(arg0);
    }

  public int mkfifo(String arg0, int arg1) {
        return (((UnixLibC) libc())).mkfifo(arg0, arg1);
    }

  public int daemon(int arg0, int arg1) {
        return libc().daemon(arg0, arg1);
    }

  public long[] getgroups() {
        int var1 = getgroups(0, null);
        int[] var2 = new int[var1];
        long[] var3 = new long[var1];
        int var4 = getgroups(var1, var2);
        int var5;
        if (var4 != -1) {
            var5 = 0;
        } else {
            return null;
        }
        while (var5 < var4) {
            var3[var5] = ((long) var2[var5]) & 4294967295L;
            ++var5;
            continue;
        }
        if (var4 >= var1) {
            return var3;
        } else {
            return Arrays.copyOfRange(var3, 0, var4);
        }
    }

  public int getgroups(int arg0, int[] arg1) {
        return libc().getgroups(arg0, arg1);
    }

  public String nl_langinfo(int arg0) {
        return libc().nl_langinfo(arg0);
    }

  public String setlocale(int arg0, String arg1) {
        return libc().setlocale(arg0, arg1);
    }

  public String strerror(int arg0) {
        return libc().strerror(arg0);
    }

  public Timeval allocateTimeval() {
        return new DefaultNativeTimeval(getRuntime());
    }

  public int gettimeofday(Timeval arg0) {
        return libc().gettimeofday(arg0, 0L);
    }

}