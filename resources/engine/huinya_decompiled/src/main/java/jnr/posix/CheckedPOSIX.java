// исходный (обфусцированный) внутренний класс: jnr.posix.CheckedPOSIX
package jnr.posix;

import java.io.FileDescriptor;
import java.nio.ByteBuffer;
import java.util.Collection;
import jnr.constants.platform.Confstr;
import jnr.constants.platform.Fcntl;
import jnr.constants.platform.Pathconf;
import jnr.constants.platform.Signal;
import jnr.constants.platform.Sysconf;
import jnr.ffi.Pointer;
import jnr.posix.FileStat;
import jnr.posix.Group;
import jnr.posix.LibC;
import jnr.posix.MsgHdr;
import jnr.posix.POSIX;
import jnr.posix.POSIXHandler;
import jnr.posix.Passwd;
import jnr.posix.RLimit;
import jnr.posix.SignalHandler;
import jnr.posix.Times;
import jnr.posix.Timeval;
import jnr.posix.util.MethodName;
import jnr.posix.util.ProcessMaker;

final class CheckedPOSIX implements POSIX {

    // ---- поля ----
  private final POSIX posix;
  private final POSIXHandler handler;

   CheckedPOSIX(POSIX arg0, POSIXHandler arg1) { // было: <init>
        super();
        posix = arg0;
        handler = arg1;
    }

  private Object unimplementedNull() {
        handler.unimplementedError(MethodName.getCallerMethodName());
        return null;
    }

  private int unimplementedInt() {
        handler.unimplementedError(MethodName.getCallerMethodName());
        return -1;
    }

  private boolean unimplementedBool() {
        handler.unimplementedError(MethodName.getCallerMethodName());
        return false;
    }

  private String unimplementedString() {
        handler.unimplementedError(MethodName.getCallerMethodName());
        return null;
    }

  public ProcessMaker newProcessMaker(String[] arg0) {
        ProcessMaker __stk1;
        try {
            __stk1 = posix.newProcessMaker(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return ((ProcessMaker) unimplementedNull());
        }
    }

  public ProcessMaker newProcessMaker() {
        ProcessMaker __stk1;
        try {
            __stk1 = posix.newProcessMaker();
        } catch (UnsatisfiedLinkError var1) {
            return ((ProcessMaker) unimplementedNull());
        }
    }

  public FileStat allocateStat() {
        FileStat __stk1;
        try {
            __stk1 = posix.allocateStat();
        } catch (UnsatisfiedLinkError var1) {
            return ((FileStat) unimplementedNull());
        }
    }

  public MsgHdr allocateMsgHdr() {
        MsgHdr __stk1;
        try {
            __stk1 = posix.allocateMsgHdr();
        } catch (UnsatisfiedLinkError var1) {
            return ((MsgHdr) unimplementedNull());
        }
    }

  public int chdir(String arg0) {
        int __stk1;
        try {
            __stk1 = posix.chdir(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public int chmod(String arg0, int arg1) {
        int __stk1;
        try {
            __stk1 = posix.chmod(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int fchmod(int arg0, int arg1) {
        int __stk1;
        try {
            __stk1 = posix.fchmod(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int chown(String arg0, int arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.chown(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public CharSequence crypt(CharSequence arg0, CharSequence arg1) {
        CharSequence __stk1;
        try {
            __stk1 = posix.crypt(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return ((CharSequence) unimplementedNull());
        }
    }

  public byte[] crypt(byte[] arg0, byte[] arg1) {
        byte[] __stk1;
        try {
            __stk1 = posix.crypt(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return ((byte[]) unimplementedNull());
        }
    }

  public int fchown(int arg0, int arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.fchown(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int endgrent() {
        int __stk1;
        try {
            __stk1 = posix.endgrent();
        } catch (UnsatisfiedLinkError var1) {
            return unimplementedInt();
        }
    }

  public int endpwent() {
        int __stk1;
        try {
            __stk1 = posix.endpwent();
        } catch (UnsatisfiedLinkError var1) {
            return unimplementedInt();
        }
    }

  public int errno() {
        return posix.errno();
    }

  public void errno(int arg0) {
        posix.errno(arg0);
    }

  public int exec(String arg0, String[] arg1) {
        int __stk1;
        try {
            __stk1 = posix.exec(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int exec(String arg0, String[] arg1, String[] arg2) {
        int __stk1;
        try {
            __stk1 = posix.exec(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int execv(String arg0, String[] arg1) {
        int __stk1;
        try {
            __stk1 = posix.execv(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int execve(String arg0, String[] arg1, String[] arg2) {
        int __stk1;
        try {
            __stk1 = posix.execve(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int fork() {
        int __stk1;
        try {
            __stk1 = posix.fork();
        } catch (UnsatisfiedLinkError var1) {
            return unimplementedInt();
        }
    }

  public FileStat fstat(int arg0) {
        FileStat __stk1;
        try {
            __stk1 = posix.fstat(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return ((FileStat) unimplementedNull());
        }
    }

  public int fstat(int arg0, FileStat arg1) {
        int __stk1;
        try {
            __stk1 = posix.fstat(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public FileStat fstat(FileDescriptor arg0) {
        FileStat __stk1;
        try {
            __stk1 = posix.fstat(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return ((FileStat) unimplementedNull());
        }
    }

  public int fstat(FileDescriptor arg0, FileStat arg1) {
        int __stk1;
        try {
            __stk1 = posix.fstat(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int getegid() {
        int __stk1;
        try {
            __stk1 = posix.getegid();
        } catch (UnsatisfiedLinkError var1) {
            return unimplementedInt();
        }
    }

  public int geteuid() {
        int __stk1;
        try {
            __stk1 = posix.geteuid();
        } catch (UnsatisfiedLinkError var1) {
            return unimplementedInt();
        }
    }

  public int getgid() {
        int __stk1;
        try {
            __stk1 = posix.getgid();
        } catch (UnsatisfiedLinkError var1) {
            return unimplementedInt();
        }
    }

  public int getdtablesize() {
        int __stk1;
        try {
            __stk1 = posix.getdtablesize();
        } catch (UnsatisfiedLinkError var1) {
            return unimplementedInt();
        }
    }

  public Group getgrent() {
        Group __stk1;
        try {
            __stk1 = posix.getgrent();
        } catch (UnsatisfiedLinkError var1) {
            return ((Group) unimplementedNull());
        }
    }

  public Group getgrgid(int arg0) {
        Group __stk1;
        try {
            __stk1 = posix.getgrgid(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return ((Group) unimplementedNull());
        }
    }

  public Group getgrnam(String arg0) {
        Group __stk1;
        try {
            __stk1 = posix.getgrnam(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return ((Group) unimplementedNull());
        }
    }

  public String getlogin() {
        String __stk1;
        try {
            __stk1 = posix.getlogin();
        } catch (UnsatisfiedLinkError var1) {
            return ((String) unimplementedNull());
        }
    }

  public int getpgid() {
        int __stk1;
        try {
            __stk1 = posix.getpgid();
        } catch (UnsatisfiedLinkError var1) {
            return unimplementedInt();
        }
    }

  public int getpgid(int arg0) {
        int __stk1;
        try {
            __stk1 = posix.getpgid(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public int getpgrp() {
        int __stk1;
        try {
            __stk1 = posix.getpgrp();
        } catch (UnsatisfiedLinkError var1) {
            return unimplementedInt();
        }
    }

  public int getpid() {
        int __stk1;
        try {
            __stk1 = posix.getpid();
        } catch (UnsatisfiedLinkError var1) {
            return unimplementedInt();
        }
    }

  public int getppid() {
        int __stk1;
        try {
            __stk1 = posix.getppid();
        } catch (UnsatisfiedLinkError var1) {
            return unimplementedInt();
        }
    }

  public int getpriority(int arg0, int arg1) {
        int __stk1;
        try {
            __stk1 = posix.getpriority(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public Passwd getpwent() {
        Passwd __stk1;
        try {
            __stk1 = posix.getpwent();
        } catch (UnsatisfiedLinkError var1) {
            return ((Passwd) unimplementedNull());
        }
    }

  public Passwd getpwnam(String arg0) {
        Passwd __stk1;
        try {
            __stk1 = posix.getpwnam(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return ((Passwd) unimplementedNull());
        }
    }

  public Passwd getpwuid(int arg0) {
        Passwd __stk1;
        try {
            __stk1 = posix.getpwuid(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return ((Passwd) unimplementedNull());
        }
    }

  public int getuid() {
        int __stk1;
        try {
            __stk1 = posix.getuid();
        } catch (UnsatisfiedLinkError var1) {
            return unimplementedInt();
        }
    }

  public int getrlimit(int arg0, RLimit arg1) {
        int __stk1;
        try {
            __stk1 = posix.getrlimit(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int getrlimit(int arg0, Pointer arg1) {
        int __stk1;
        try {
            __stk1 = posix.getrlimit(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public RLimit getrlimit(int arg0) {
        RLimit __stk1;
        try {
            __stk1 = posix.getrlimit(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return ((RLimit) unimplementedNull());
        }
    }

  public int setrlimit(int arg0, RLimit arg1) {
        int __stk1;
        try {
            __stk1 = posix.setrlimit(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int setrlimit(int arg0, Pointer arg1) {
        int __stk1;
        try {
            __stk1 = posix.setrlimit(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int setrlimit(int arg0, long arg1, long arg2) {
        int __stk1;
        try {
            __stk1 = posix.setrlimit(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var6) {
            return unimplementedInt();
        }
    }

  public boolean isatty(FileDescriptor arg0) {
        boolean __stk1;
        try {
            __stk1 = posix.isatty(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedBool();
        }
    }

  public int isatty(int arg0) {
        int __stk1;
        try {
            __stk1 = posix.isatty(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public int kill(int arg0, int arg1) {
        return kill(((long) arg0), arg1);
    }

  public int kill(long arg0, int arg1) {
        int __stk1;
        try {
            __stk1 = posix.kill(arg0, arg1);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public SignalHandler signal(Signal arg0, SignalHandler arg1) {
        SignalHandler __stk1;
        try {
            __stk1 = posix.signal(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return ((SignalHandler) unimplementedNull());
        }
    }

  public int raise(int arg0) {
        int __stk1;
        try {
            __stk1 = posix.raise(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public int lchmod(String arg0, int arg1) {
        int __stk1;
        try {
            __stk1 = posix.lchmod(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int lchown(String arg0, int arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.lchown(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int link(String arg0, String arg1) {
        int __stk1;
        try {
            __stk1 = posix.link(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public FileStat lstat(String arg0) {
        FileStat __stk1;
        try {
            __stk1 = posix.lstat(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return ((FileStat) unimplementedNull());
        }
    }

  public int lstat(String arg0, FileStat arg1) {
        int __stk1;
        try {
            __stk1 = posix.lstat(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int mkdir(String arg0, int arg1) {
        int __stk1;
        try {
            __stk1 = posix.mkdir(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public String readlink(String arg0) {
        String __stk1;
        try {
            __stk1 = posix.readlink(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return ((String) unimplementedNull());
        }
    }

  public int readlink(CharSequence arg0, byte[] arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.readlink(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int readlink(CharSequence arg0, ByteBuffer arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.readlink(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int readlink(CharSequence arg0, Pointer arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.readlink(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int rmdir(String arg0) {
        int __stk1;
        try {
            __stk1 = posix.rmdir(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public int setegid(int arg0) {
        int __stk1;
        try {
            __stk1 = posix.setegid(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public int seteuid(int arg0) {
        int __stk1;
        try {
            __stk1 = posix.seteuid(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public int setgid(int arg0) {
        int __stk1;
        try {
            __stk1 = posix.setgid(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public int setgrent() {
        int __stk1;
        try {
            __stk1 = posix.setgrent();
        } catch (UnsatisfiedLinkError var1) {
            return unimplementedInt();
        }
    }

  public int setpgid(int arg0, int arg1) {
        int __stk1;
        try {
            __stk1 = posix.setpgid(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int setpgrp(int arg0, int arg1) {
        int __stk1;
        try {
            __stk1 = posix.setpgrp(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int setpriority(int arg0, int arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.setpriority(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int setpwent() {
        int __stk1;
        try {
            __stk1 = posix.setpwent();
        } catch (UnsatisfiedLinkError var1) {
            return unimplementedInt();
        }
    }

  public int setsid() {
        int __stk1;
        try {
            __stk1 = posix.setsid();
        } catch (UnsatisfiedLinkError var1) {
            return unimplementedInt();
        }
    }

  public int setuid(int arg0) {
        int __stk1;
        try {
            __stk1 = posix.setuid(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public FileStat stat(String arg0) {
        FileStat __stk1;
        try {
            __stk1 = posix.stat(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return ((FileStat) unimplementedNull());
        }
    }

  public int stat(String arg0, FileStat arg1) {
        int __stk1;
        try {
            __stk1 = posix.stat(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int symlink(String arg0, String arg1) {
        int __stk1;
        try {
            __stk1 = posix.symlink(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int umask(int arg0) {
        int __stk1;
        try {
            __stk1 = posix.umask(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public int utimes(String arg0, long[] arg1, long[] arg2) {
        int __stk1;
        try {
            __stk1 = posix.utimes(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int utimes(String arg0, Pointer arg1) {
        int __stk1;
        try {
            __stk1 = posix.utimes(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int futimes(int arg0, long[] arg1, long[] arg2) {
        int __stk1;
        try {
            __stk1 = posix.futimes(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int lutimes(String arg0, long[] arg1, long[] arg2) {
        int __stk1;
        try {
            __stk1 = posix.lutimes(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int utimensat(int arg0, String arg1, long[] arg2, long[] arg3, int arg4) {
        int __stk1;
        try {
            __stk1 = posix.utimensat(arg0, arg1, arg2, arg3, arg4);
        } catch (UnsatisfiedLinkError var6) {
            return unimplementedInt();
        }
    }

  public int utimensat(int arg0, String arg1, Pointer arg2, int arg3) {
        int __stk1;
        try {
            __stk1 = posix.utimensat(arg0, arg1, arg2, arg3);
        } catch (UnsatisfiedLinkError var5) {
            return unimplementedInt();
        }
    }

  public int futimens(int arg0, long[] arg1, long[] arg2) {
        int __stk1;
        try {
            __stk1 = posix.futimens(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int futimens(int arg0, Pointer arg1) {
        int __stk1;
        try {
            __stk1 = posix.futimens(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int wait(int[] arg0) {
        int __stk1;
        try {
            __stk1 = posix.wait(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public int waitpid(int arg0, int[] arg1, int arg2) {
        return waitpid(((long) arg0), arg1, arg2);
    }

  public int waitpid(long arg0, int[] arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.waitpid(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var5) {
            return unimplementedInt();
        }
    }

  public boolean isNative() {
        return posix.isNative();
    }

  public LibC libc() {
        return posix.libc();
    }

  public Pointer environ() {
        Pointer __stk1;
        try {
            __stk1 = posix.environ();
        } catch (UnsatisfiedLinkError var1) {
            return ((Pointer) unimplementedNull());
        }
    }

  public String getenv(String arg0) {
        String __stk1;
        try {
            __stk1 = posix.getenv(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return ((String) unimplementedNull());
        }
    }

  public int setenv(String arg0, String arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.setenv(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int unsetenv(String arg0) {
        int __stk1;
        try {
            __stk1 = posix.unsetenv(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public long posix_spawnp(String arg0, Collection arg1, Collection arg2, Collection arg3) {
        long __stk1;
        try {
            __stk1 = posix.posix_spawnp(arg0, arg1, arg2, arg3);
        } catch (UnsatisfiedLinkError var5) {
            return ((long) unimplementedInt());
        }
    }

  public long posix_spawnp(String arg0, Collection arg1, Collection arg2, Collection arg3, Collection arg4) {
        long __stk1;
        try {
            __stk1 = posix.posix_spawnp(arg0, arg1, arg2, arg3, arg4);
        } catch (UnsatisfiedLinkError var6) {
            return ((long) unimplementedInt());
        }
    }

  public long sysconf(Sysconf arg0) {
        long __stk1;
        try {
            __stk1 = posix.sysconf(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return ((long) unimplementedInt());
        }
    }

  public int confstr(Confstr arg0, ByteBuffer arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.confstr(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int fpathconf(int arg0, Pathconf arg1) {
        int __stk1;
        try {
            __stk1 = posix.fpathconf(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public Times times() {
        Times __stk1;
        try {
            __stk1 = posix.times();
        } catch (UnsatisfiedLinkError var1) {
            return ((Times) unimplementedNull());
        }
    }

  public int flock(int arg0, int arg1) {
        return posix.flock(arg0, arg1);
    }

  public int dup(int arg0) {
        int __stk1;
        try {
            __stk1 = posix.dup(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public int dup2(int arg0, int arg1) {
        int __stk1;
        try {
            __stk1 = posix.dup2(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int fcntlInt(int arg0, Fcntl arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.fcntlInt(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int fcntl(int arg0, Fcntl arg1) {
        int __stk1;
        try {
            __stk1 = posix.fcntl(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int fcntl(int arg0, Fcntl arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.fcntl(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

    @Deprecated
  public int fcntl(int arg0, Fcntl arg1, int[] arg2) {
        int __stk1;
        try {
            __stk1 = posix.fcntl(arg0, arg1);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int access(CharSequence arg0, int arg1) {
        int __stk1;
        try {
            __stk1 = posix.access(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int close(int arg0) {
        int __stk1;
        try {
            __stk1 = posix.close(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public int unlink(CharSequence arg0) {
        int __stk1;
        try {
            __stk1 = posix.unlink(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public int open(CharSequence arg0, int arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.open(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public long read(int arg0, byte[] arg1, long arg2) {
        long __stk1;
        try {
            __stk1 = posix.read(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var5) {
            return ((long) unimplementedInt());
        }
    }

  public long write(int arg0, byte[] arg1, long arg2) {
        long __stk1;
        try {
            __stk1 = posix.write(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var5) {
            return ((long) unimplementedInt());
        }
    }

  public long read(int arg0, ByteBuffer arg1, long arg2) {
        long __stk1;
        try {
            __stk1 = posix.read(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var5) {
            return ((long) unimplementedInt());
        }
    }

  public long write(int arg0, ByteBuffer arg1, long arg2) {
        long __stk1;
        try {
            __stk1 = posix.write(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var5) {
            return ((long) unimplementedInt());
        }
    }

  public long pread(int arg0, byte[] arg1, long arg2, long arg3) {
        long __stk1;
        try {
            __stk1 = posix.pread(arg0, arg1, arg2, arg3);
        } catch (UnsatisfiedLinkError var7) {
            return ((long) unimplementedInt());
        }
    }

  public long pwrite(int arg0, byte[] arg1, long arg2, long arg3) {
        long __stk1;
        try {
            __stk1 = posix.pwrite(arg0, arg1, arg2, arg3);
        } catch (UnsatisfiedLinkError var7) {
            return ((long) unimplementedInt());
        }
    }

  public long pread(int arg0, ByteBuffer arg1, long arg2, long arg3) {
        long __stk1;
        try {
            __stk1 = posix.pread(arg0, arg1, arg2, arg3);
        } catch (UnsatisfiedLinkError var7) {
            return ((long) unimplementedInt());
        }
    }

  public long pwrite(int arg0, ByteBuffer arg1, long arg2, long arg3) {
        long __stk1;
        try {
            __stk1 = posix.pwrite(arg0, arg1, arg2, arg3);
        } catch (UnsatisfiedLinkError var7) {
            return ((long) unimplementedInt());
        }
    }

  public int read(int arg0, byte[] arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.read(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int write(int arg0, byte[] arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.write(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int read(int arg0, ByteBuffer arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.read(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int write(int arg0, ByteBuffer arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.write(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int pread(int arg0, byte[] arg1, int arg2, int arg3) {
        int __stk1;
        try {
            __stk1 = posix.pread(arg0, arg1, arg2, arg3);
        } catch (UnsatisfiedLinkError var5) {
            return unimplementedInt();
        }
    }

  public int pwrite(int arg0, byte[] arg1, int arg2, int arg3) {
        int __stk1;
        try {
            __stk1 = posix.pwrite(arg0, arg1, arg2, arg3);
        } catch (UnsatisfiedLinkError var5) {
            return unimplementedInt();
        }
    }

  public int pread(int arg0, ByteBuffer arg1, int arg2, int arg3) {
        int __stk1;
        try {
            __stk1 = posix.pread(arg0, arg1, arg2, arg3);
        } catch (UnsatisfiedLinkError var5) {
            return unimplementedInt();
        }
    }

  public int pwrite(int arg0, ByteBuffer arg1, int arg2, int arg3) {
        int __stk1;
        try {
            __stk1 = posix.pwrite(arg0, arg1, arg2, arg3);
        } catch (UnsatisfiedLinkError var5) {
            return unimplementedInt();
        }
    }

  public int lseek(int arg0, long arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.lseek(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var5) {
            return unimplementedInt();
        }
    }

  public long lseekLong(int arg0, long arg1, int arg2) {
        long __stk1;
        try {
            __stk1 = posix.lseekLong(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var5) {
            return ((long) unimplementedInt());
        }
    }

  public int pipe(int[] arg0) {
        int __stk1;
        try {
            __stk1 = posix.pipe(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public int socketpair(int arg0, int arg1, int arg2, int[] arg3) {
        int __stk1;
        try {
            __stk1 = posix.socketpair(arg0, arg1, arg2, arg3);
        } catch (UnsatisfiedLinkError var5) {
            return unimplementedInt();
        }
    }

  public int sendmsg(int arg0, MsgHdr arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.sendmsg(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int recvmsg(int arg0, MsgHdr arg1, int arg2) {
        int __stk1;
        try {
            __stk1 = posix.recvmsg(arg0, arg1, arg2);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int truncate(CharSequence arg0, long arg1) {
        int __stk1;
        try {
            __stk1 = posix.truncate(arg0, arg1);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int ftruncate(int arg0, long arg1) {
        int __stk1;
        try {
            __stk1 = posix.ftruncate(arg0, arg1);
        } catch (UnsatisfiedLinkError var4) {
            return unimplementedInt();
        }
    }

  public int rename(CharSequence arg0, CharSequence arg1) {
        int __stk1;
        try {
            __stk1 = posix.rename(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public String getcwd() {
        String __stk1;
        try {
            __stk1 = posix.getcwd();
        } catch (UnsatisfiedLinkError var1) {
            return unimplementedString();
        }
    }

  public int fsync(int arg0) {
        int __stk1;
        try {
            __stk1 = posix.fsync(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public int fdatasync(int arg0) {
        int __stk1;
        try {
            __stk1 = posix.fsync(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public int mkfifo(String arg0, int arg1) {
        int __stk1;
        try {
            __stk1 = posix.mkfifo(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public int daemon(int arg0, int arg1) {
        int __stk1;
        try {
            __stk1 = posix.daemon(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public long[] getgroups() {
        long[] __stk1;
        try {
            __stk1 = posix.getgroups();
        } catch (UnsatisfiedLinkError var1) {
            return null;
        }
    }

  public int getgroups(int arg0, int[] arg1) {
        int __stk1;
        try {
            __stk1 = posix.getgroups(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedInt();
        }
    }

  public String nl_langinfo(int arg0) {
        String __stk1;
        try {
            __stk1 = posix.nl_langinfo(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedString();
        }
    }

  public String setlocale(int arg0, String arg1) {
        String __stk1;
        try {
            __stk1 = posix.setlocale(arg0, arg1);
        } catch (UnsatisfiedLinkError var3) {
            return unimplementedString();
        }
    }

  public String strerror(int arg0) {
        String __stk1;
        try {
            __stk1 = posix.strerror(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedString();
        }
    }

  public Timeval allocateTimeval() {
        Timeval __stk1;
        try {
            __stk1 = posix.allocateTimeval();
        } catch (UnsatisfiedLinkError var1) {
            return ((Timeval) unimplementedNull());
        }
    }

  public int gettimeofday(Timeval arg0) {
        int __stk1;
        try {
            __stk1 = posix.gettimeofday(arg0);
        } catch (UnsatisfiedLinkError var2) {
            return unimplementedInt();
        }
    }

  public String gethostname() {
        String __stk1;
        try {
            __stk1 = posix.gethostname();
        } catch (UnsatisfiedLinkError var1) {
            return ((String) unimplementedNull());
        }
    }

}