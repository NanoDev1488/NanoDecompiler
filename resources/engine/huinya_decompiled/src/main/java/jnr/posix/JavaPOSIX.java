// исходный (обфусцированный) внутренний класс: jnr.posix.JavaPOSIX
package jnr.posix;

import java.io.File;
import java.io.FileDescriptor;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;
import jnr.constants.platform.Confstr;
import jnr.constants.platform.Errno;
import jnr.constants.platform.Fcntl;
import jnr.constants.platform.Pathconf;
import jnr.constants.platform.Signal;
import jnr.constants.platform.Sysconf;
import jnr.ffi.Pointer;
import jnr.posix.FileStat;
import jnr.posix.Group;
import jnr.posix.JavaFileStat;
import jnr.posix.JavaLibCHelper;
import jnr.posix.JavaPOSIX_LoginInfo;
import jnr.posix.JavaTimes;
import jnr.posix.LibC;
import jnr.posix.MsgHdr;
import jnr.posix.POSIX;
import jnr.posix.POSIXHandler;
import jnr.posix.Passwd;
import jnr.posix.RLimit;
import jnr.posix.SignalHandler;
import jnr.posix.SocketMacros;
import jnr.posix.Times;
import jnr.posix.Timeval;
import jnr.posix.util.Java5ProcessMaker;
import jnr.posix.util.MethodName;
import jnr.posix.util.ProcessMaker;
import jnr.posix.util.SunMiscSignal;

final class JavaPOSIX implements POSIX {

    // ---- поля ----
  private final POSIXHandler handler;
  private final JavaLibCHelper helper;
  static final boolean $assertionsDisabled;

    static {
        boolean __stk1;
        __stk1 = !JavaPOSIX.class.desiredAssertionStatus();
        $assertionsDisabled = __stk1;
    }

   JavaPOSIX(POSIXHandler arg0) { // было: <init>
        super();
        handler = arg0;
        helper = new JavaLibCHelper(arg0);
    }

  public ProcessMaker newProcessMaker(String[] arg0) {
        return new Java5ProcessMaker(handler, arg0);
    }

  public ProcessMaker newProcessMaker() {
        return new Java5ProcessMaker(handler);
    }

  public FileStat allocateStat() {
        return new JavaFileStat(this, handler);
    }

  public MsgHdr allocateMsgHdr() {
        handler.unimplementedError(MethodName.getCallerMethodName());
        return null;
    }

  public SocketMacros socketMacros() {
        handler.unimplementedError(MethodName.getCallerMethodName());
        return null;
    }

  public int chmod(String arg0, int arg1) {
        return helper.chmod(arg0, arg1);
    }

  public int fchmod(int arg0, int arg1) {
        handler.unimplementedError("No fchmod in Java (yet)");
        return -1;
    }

  public int chown(String arg0, int arg1, int arg2) {
        return helper.chown(arg0, arg1, arg2);
    }

  public int fchown(int arg0, int arg1, int arg2) {
        handler.unimplementedError("No fchown in Java (yet)");
        return -1;
    }

  public CharSequence crypt(CharSequence arg0, CharSequence arg1) {
        return JavaLibCHelper.crypt(arg0, arg1);
    }

  public byte[] crypt(byte[] arg0, byte[] arg1) {
        return JavaLibCHelper.crypt(arg0, arg1);
    }

  public int exec(String arg0, String[] arg1) {
        handler.unimplementedError("No exec in Java (yet)");
        return -1;
    }

  public int exec(String arg0, String[] arg1, String[] arg2) {
        handler.unimplementedError("No exec in Java (yet)");
        return -1;
    }

  public int execv(String arg0, String[] arg1) {
        handler.unimplementedError("No execv in Java (yet)");
        return -1;
    }

  public int execve(String arg0, String[] arg1, String[] arg2) {
        handler.unimplementedError("No execve in Java (yet)");
        return -1;
    }

  public FileStat fstat(FileDescriptor arg0) {
        handler.unimplementedError("fstat unimplemented");
        return null;
    }

  public FileStat fstat(int arg0) {
        handler.unimplementedError("fstat unimplemented");
        return null;
    }

  public int fstat(int arg0, FileStat arg1) {
        handler.unimplementedError("fstat unimplemented");
        return -1;
    }

  public int fstat(FileDescriptor arg0, FileStat arg1) {
        handler.unimplementedError("fstat unimplemented");
        return -1;
    }

  public int getegid() {
        return JavaPOSIX_LoginInfo.GID;
    }

  public int geteuid() {
        return JavaPOSIX_LoginInfo.UID;
    }

  public int getgid() {
        return JavaPOSIX_LoginInfo.GID;
    }

  public int getdtablesize() {
        handler.unimplementedError("getdtablesize unimplemented");
        return -1;
    }

  public String getlogin() {
        return helper.getlogin();
    }

  public int getpgid() {
        return unimplementedInt("getpgid");
    }

  public int getpgrp() {
        return unimplementedInt("getpgrp");
    }

  public int getpid() {
        return helper.getpid();
    }

  public int getppid() {
        return unimplementedInt("getppid");
    }

  public Passwd getpwent() {
        return helper.getpwent();
    }

  public Passwd getpwuid(int arg0) {
        return helper.getpwuid(arg0);
    }

  public Group getgrgid(int arg0) {
        handler.unimplementedError("getgrgid unimplemented");
        return null;
    }

  public Passwd getpwnam(String arg0) {
        handler.unimplementedError("getpwnam unimplemented");
        return null;
    }

  public Group getgrnam(String arg0) {
        handler.unimplementedError("getgrnam unimplemented");
        return null;
    }

  public Group getgrent() {
        handler.unimplementedError("getgrent unimplemented");
        return null;
    }

  public int setpwent() {
        return helper.setpwent();
    }

  public int endpwent() {
        return helper.endpwent();
    }

  public int setgrent() {
        return unimplementedInt("setgrent");
    }

  public int endgrent() {
        return unimplementedInt("endgrent");
    }

  public Pointer environ() {
        handler.unimplementedError("environ");
        return null;
    }

  public String getenv(String arg0) {
        return ((String) helper.getEnv().get(arg0));
    }

  public int getuid() {
        return JavaPOSIX_LoginInfo.UID;
    }

  public int getrlimit(int arg0, RLimit arg1) {
        return unimplementedInt("getrlimit");
    }

  public int getrlimit(int arg0, Pointer arg1) {
        return unimplementedInt("getrlimit");
    }

  public RLimit getrlimit(int arg0) {
        handler.unimplementedError("getrlimit");
        return null;
    }

  public int setrlimit(int arg0, RLimit arg1) {
        return unimplementedInt("setrlimit");
    }

  public int setrlimit(int arg0, Pointer arg1) {
        return unimplementedInt("setrlimit");
    }

  public int setrlimit(int arg0, long arg1, long arg2) {
        return unimplementedInt("setrlimit");
    }

  public int fork() {
        return -1;
    }

  public boolean isatty(FileDescriptor arg0) {
        return arg0 == FileDescriptor.in ? 1 : arg0 == FileDescriptor.out ? 1 : arg0 == FileDescriptor.err;
    }

  public int isatty(int arg0) {
        return arg0 == 0 ? 1 : arg0 == 1 ? 1 : arg0 == 2;
    }

  public int kill(int arg0, int arg1) {
        return unimplementedInt("kill");
    }

  public int kill(long arg0, int arg1) {
        return unimplementedInt("kill");
    }

  public SignalHandler signal(Signal arg0, SignalHandler arg1) {
        return SunMiscSignal.signal(arg0, arg1);
    }

  public int raise(int arg0) {
        return unimplementedInt("raise");
    }

  public int lchmod(String arg0, int arg1) {
        return unimplementedInt("lchmod");
    }

  public int lchown(String arg0, int arg1, int arg2) {
        return unimplementedInt("lchown");
    }

  public int link(String arg0, String arg1) {
        return helper.link(arg0, arg1);
    }

  public FileStat lstat(String arg0) {
        FileStat var2 = allocateStat();
        if (lstat(arg0, var2) < 0) {
            handler.error(Errno.ENOENT, "lstat", arg0);
        }
        return var2;
    }

  public int lstat(String arg0, FileStat arg1) {
        return helper.lstat(arg0, arg1);
    }

  public int mkdir(String arg0, int arg1) {
        return helper.mkdir(arg0, arg1);
    }

  public int rmdir(String arg0) {
        return helper.rmdir(arg0);
    }

  public String readlink(String arg0) {
        ByteBuffer var2 = ByteBuffer.allocateDirect(256);
        int var3 = helper.readlink(arg0, var2, var2.capacity());
        if (var3 != -1) {
            var2.position(0);
            var2.limit(var3);
            return Charset.forName("ASCII").decode(var2).toString();
        } else {
            return null;
        }
    }

  public int readlink(CharSequence arg0, byte[] arg1, int arg2) {
        handler.unimplementedError("readlink");
        return -1;
    }

  public int readlink(CharSequence arg0, ByteBuffer arg1, int arg2) {
        handler.unimplementedError("readlink");
        return -1;
    }

  public int readlink(CharSequence arg0, Pointer arg1, int arg2) {
        handler.unimplementedError("readlink");
        return -1;
    }

  public int setenv(String arg0, String arg1, int arg2) {
        Map var4 = helper.getEnv();
        if (!arg0.contains("=")) {
            if (arg2 != 0) {
                var4.put(arg0, arg1);
                return 0;
            } else {
                if (!var4.containsKey(arg0)) {
                    var4.put(arg0, arg1);
                    return 0;
                } else {
                    return 0;
                }
            }
        } else {
            handler.error(Errno.EINVAL, "setenv", arg0);
            return -1;
        }
    }

  public FileStat stat(String arg0) {
        FileStat var2 = allocateStat();
        if (helper.stat(arg0, var2) < 0) {
            handler.error(Errno.ENOENT, "stat", arg0);
        }
        return var2;
    }

  public int stat(String arg0, FileStat arg1) {
        return helper.stat(arg0, arg1);
    }

  public int symlink(String arg0, String arg1) {
        return helper.symlink(arg0, arg1);
    }

  public int setegid(int arg0) {
        return unimplementedInt("setegid");
    }

  public int seteuid(int arg0) {
        return unimplementedInt("seteuid");
    }

  public int setgid(int arg0) {
        return unimplementedInt("setgid");
    }

  public int getpgid(int arg0) {
        return unimplementedInt("getpgid");
    }

  public int setpgid(int arg0, int arg1) {
        return unimplementedInt("setpgid");
    }

  public int setpgrp(int arg0, int arg1) {
        return unimplementedInt("setpgrp");
    }

  public int setsid() {
        return unimplementedInt("setsid");
    }

  public int setuid(int arg0) {
        return unimplementedInt("setuid");
    }

  public int umask(int arg0) {
        return 0;
    }

  public int unsetenv(String arg0) {
        if (helper.getEnv().remove(arg0) != null) {
            return 0;
        } else {
            handler.error(Errno.EINVAL, "unsetenv", arg0);
            return -1;
        }
    }

  public int utimes(String arg0, long[] arg1, long[] arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_3
        //      1: ifnull  44 (offset +43)
        //      4: getstatic  #122 // jnr.posix.JavaPOSIX.$assertionsDisabled:Z
        //      7: ifne  24 (offset +17)
        //     10: aload_3
        //     11: arraylength
        //     12: iconst_2
        //     13: if_icmpeq  24 (offset +11)
        //     16: new  #85 // java.lang.AssertionError
        //     19: dup
        //     20: invokespecial  #131 // java.lang.AssertionError.<init>:()V
        //     23: athrow
        //     24: aload_3
        //     25: iconst_0
        //     26: laload
        //     27: ldc2_w  #112 // 1000L
        //     30: lmul
        //     31: aload_3
        //     32: iconst_1
        //     33: laload
        //     34: ldc2_w  #112 // 1000L
        //     37: ldiv
        //     38: ladd
        //     39: lstore  4
        //     41: goto  49 (offset +8)
        //     44: invokestatic  #135 // java.lang.System.currentTimeMillis:()J
        //     47: lstore  4
        //     49: new  #83 // java.io.File
        //     52: dup
        //     53: aload_1
        //     54: invokespecial  #128 // java.io.File.<init>:(Ljava/lang/String;)V
        //     57: lload  4
        //     59: invokevirtual  #130 // java.io.File.setLastModified:(J)Z
        //     62: pop
        //     63: iconst_0
        //     64: ireturn
    }

  public int utimes(String arg0, Pointer arg1) {
        return unimplementedInt("utimes");
    }

  public int futimes(int arg0, long[] arg1, long[] arg2) {
        handler.unimplementedError("futimes");
        return unimplementedInt("futimes");
    }

  public int lutimes(String arg0, long[] arg1, long[] arg2) {
        handler.unimplementedError("lutimes");
        return unimplementedInt("lutimes");
    }

  public int utimensat(int arg0, String arg1, long[] arg2, long[] arg3, int arg4) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload  4
        //      2: ifnull  48 (offset +46)
        //      5: getstatic  #122 // jnr.posix.JavaPOSIX.$assertionsDisabled:Z
        //      8: ifne  26 (offset +18)
        //     11: aload  4
        //     13: arraylength
        //     14: iconst_2
        //     15: if_icmpeq  26 (offset +11)
        //     18: new  #85 // java.lang.AssertionError
        //     21: dup
        //     22: invokespecial  #131 // java.lang.AssertionError.<init>:()V
        //     25: athrow
        //     26: aload  4
        //     28: iconst_0
        //     29: laload
        //     30: ldc2_w  #112 // 1000L
        //     33: lmul
        //     34: aload  4
        //     36: iconst_1
        //     37: laload
        //     38: ldc2_w  #114 // 1000000L
        //     41: ldiv
        //     42: ladd
        //     43: lstore  6
        //     45: goto  53 (offset +8)
        //     48: invokestatic  #135 // java.lang.System.currentTimeMillis:()J
        //     51: lstore  6
        //     53: new  #83 // java.io.File
        //     56: dup
        //     57: aload_2
        //     58: invokespecial  #128 // java.io.File.<init>:(Ljava/lang/String;)V
        //     61: lload  6
        //     63: invokevirtual  #130 // java.io.File.setLastModified:(J)Z
        //     66: pop
        //     67: iconst_0
        //     68: ireturn
    }

  public int utimensat(int arg0, String arg1, Pointer arg2, int arg3) {
        return unimplementedInt("utimensat");
    }

  public int futimens(int arg0, long[] arg1, long[] arg2) {
        handler.unimplementedError("futimens");
        return unimplementedInt("futimens");
    }

  public int futimens(int arg0, Pointer arg1) {
        handler.unimplementedError("futimens");
        return unimplementedInt("futimens");
    }

  public int wait(int[] arg0) {
        return unimplementedInt("wait");
    }

  public int waitpid(int arg0, int[] arg1, int arg2) {
        return unimplementedInt("waitpid");
    }

  public int waitpid(long arg0, int[] arg1, int arg2) {
        return unimplementedInt("waitpid");
    }

  public int getpriority(int arg0, int arg1) {
        return unimplementedInt("getpriority");
    }

  public int setpriority(int arg0, int arg1, int arg2) {
        return unimplementedInt("setpriority");
    }

  public long posix_spawnp(String arg0, Collection arg1, Collection arg2, Collection arg3) {
        return ((long) unimplementedInt("posix_spawnp"));
    }

  public long posix_spawnp(String arg0, Collection arg1, Collection arg2, Collection arg3, Collection arg4) {
        return ((long) unimplementedInt("posix_spawnp"));
    }

  public int errno() {
        return JavaLibCHelper.errno();
    }

  public void errno(int arg0) {
        JavaLibCHelper.errno(arg0);
    }

  public int chdir(String arg0) {
        return JavaLibCHelper.chdir(arg0);
    }

  public boolean isNative() {
        return false;
    }

  public LibC libc() {
        return null;
    }

  private int unimplementedInt(String arg0) {
        handler.unimplementedError(arg0);
        return -1;
    }

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

  public int flock(int arg0, int arg1) {
        return unimplementedInt("flock");
    }

  public int dup(int arg0) {
        return unimplementedInt("dup");
    }

  public int dup2(int arg0, int arg1) {
        return unimplementedInt("dup2");
    }

  public int fcntlInt(int arg0, Fcntl arg1, int arg2) {
        return unimplementedInt("fcntl");
    }

  public int fcntl(int arg0, Fcntl arg1) {
        return unimplementedInt("fcntl");
    }

  public int fcntl(int arg0, Fcntl arg1, int arg2) {
        return unimplementedInt("fcntl");
    }

    @Deprecated
  public int fcntl(int arg0, Fcntl arg1, int[] arg2) {
        return unimplementedInt("fcntl");
    }

  public int access(CharSequence arg0, int arg1) {
        handler.unimplementedError("access");
        return -1;
    }

  public int close(int arg0) {
        return unimplementedInt("close");
    }

  public int unlink(CharSequence arg0) {
        handler.unimplementedError("unlink");
        return -1;
    }

  public int open(CharSequence arg0, int arg1, int arg2) {
        handler.unimplementedError("open");
        return -1;
    }

  public long read(int arg0, byte[] arg1, long arg2) {
        handler.unimplementedError("read");
        return -1L;
    }

  public long write(int arg0, byte[] arg1, long arg2) {
        handler.unimplementedError("write");
        return -1L;
    }

  public long read(int arg0, ByteBuffer arg1, long arg2) {
        handler.unimplementedError("read");
        return -1L;
    }

  public long write(int arg0, ByteBuffer arg1, long arg2) {
        handler.unimplementedError("write");
        return -1L;
    }

  public long pread(int arg0, byte[] arg1, long arg2, long arg3) {
        handler.unimplementedError("pread");
        return -1L;
    }

  public long pwrite(int arg0, byte[] arg1, long arg2, long arg3) {
        handler.unimplementedError("pwrite");
        return -1L;
    }

  public long pread(int arg0, ByteBuffer arg1, long arg2, long arg3) {
        handler.unimplementedError("pread");
        return -1L;
    }

  public long pwrite(int arg0, ByteBuffer arg1, long arg2, long arg3) {
        handler.unimplementedError("pwrite");
        return -1L;
    }

  public int read(int arg0, byte[] arg1, int arg2) {
        handler.unimplementedError("read");
        return -1;
    }

  public int write(int arg0, byte[] arg1, int arg2) {
        handler.unimplementedError("write");
        return -1;
    }

  public int read(int arg0, ByteBuffer arg1, int arg2) {
        handler.unimplementedError("read");
        return -1;
    }

  public int write(int arg0, ByteBuffer arg1, int arg2) {
        handler.unimplementedError("write");
        return -1;
    }

  public int pread(int arg0, byte[] arg1, int arg2, int arg3) {
        handler.unimplementedError("pread");
        return -1;
    }

  public int pwrite(int arg0, byte[] arg1, int arg2, int arg3) {
        handler.unimplementedError("pwrite");
        return -1;
    }

  public int pread(int arg0, ByteBuffer arg1, int arg2, int arg3) {
        handler.unimplementedError("pread");
        return -1;
    }

  public int pwrite(int arg0, ByteBuffer arg1, int arg2, int arg3) {
        handler.unimplementedError("pwrite");
        return -1;
    }

  public int lseek(int arg0, long arg1, int arg2) {
        handler.unimplementedError("lseek");
        return -1;
    }

  public long lseekLong(int arg0, long arg1, int arg2) {
        handler.unimplementedError("lseek");
        return -1L;
    }

  public int pipe(int[] arg0) {
        handler.unimplementedError("pipe");
        return -1;
    }

  public int socketpair(int arg0, int arg1, int arg2, int[] arg3) {
        handler.unimplementedError("socketpair");
        return -1;
    }

  public int sendmsg(int arg0, MsgHdr arg1, int arg2) {
        handler.unimplementedError("sendmsg");
        return -1;
    }

  public int recvmsg(int arg0, MsgHdr arg1, int arg2) {
        handler.unimplementedError("recvmsg");
        return -1;
    }

  public int truncate(CharSequence arg0, long arg1) {
        handler.unimplementedError("truncate");
        return -1;
    }

  public int ftruncate(int arg0, long arg1) {
        handler.unimplementedError("ftruncate");
        return -1;
    }

  public int rename(CharSequence arg0, CharSequence arg1) {
        File var3 = new File(arg0.toString());
        File var4 = new File(arg1.toString());
        if (!var3.renameTo(var4)) {
            return -1;
        } else {
            return 0;
        }
    }

  public String getcwd() {
        return System.getProperty("user.dir");
    }

  public int fsync(int arg0) {
        handler.unimplementedError("fsync");
        return unimplementedInt("fsync not available for Java");
    }

  public int fdatasync(int arg0) {
        handler.unimplementedError("fdatasync");
        return unimplementedInt("fdatasync not available for Java");
    }

  public int mkfifo(String arg0, int arg1) {
        handler.unimplementedError("mkfifo");
        return unimplementedInt("mkfifo not available for Java");
    }

  public int daemon(int arg0, int arg1) {
        handler.unimplementedError("daemon");
        return unimplementedInt("daemon not available for Java");
    }

  public long[] getgroups() {
        handler.unimplementedError("getgroups");
        return null;
    }

  public int getgroups(int arg0, int[] arg1) {
        handler.unimplementedError("getgroups");
        return unimplementedInt("getgroups not available for Java");
    }

  public String nl_langinfo(int arg0) {
        handler.unimplementedError("nl_langinfo");
        return null;
    }

  public String setlocale(int arg0, String arg1) {
        handler.unimplementedError("setlocale");
        return null;
    }

  public String strerror(int arg0) {
        handler.unimplementedError("strerror");
        return null;
    }

  public Timeval allocateTimeval() {
        handler.unimplementedError("allocateTimeval");
        return null;
    }

  public int gettimeofday(Timeval arg0) {
        handler.unimplementedError("gettimeofday");
        return -1;
    }

  public String gethostname() {
        return helper.gethostname();
    }

}