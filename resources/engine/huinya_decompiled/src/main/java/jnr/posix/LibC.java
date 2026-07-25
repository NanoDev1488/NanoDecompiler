// исходный (обфусцированный) внутренний класс: jnr.posix.LibC
package jnr.posix;

import java.nio.ByteBuffer;
import jnr.constants.platform.Confstr;
import jnr.constants.platform.Pathconf;
import jnr.constants.platform.Sysconf;
import jnr.ffi.Pointer;
import jnr.ffi.Variable;
import jnr.ffi.annotations.Direct;
import jnr.ffi.annotations.IgnoreError;
import jnr.ffi.annotations.In;
import jnr.ffi.annotations.Out;
import jnr.ffi.annotations.Transient;
import jnr.ffi.annotations.Variadic;
import jnr.ffi.byref.IntByReference;
import jnr.ffi.types.clock_t;
import jnr.ffi.types.intptr_t;
import jnr.ffi.types.off_t;
import jnr.ffi.types.size_t;
import jnr.ffi.types.ssize_t;
import jnr.ffi.types.u_int32_t;
import jnr.ffi.types.u_int64_t;
import jnr.posix.FileStat;
import jnr.posix.Flock;
import jnr.posix.LibC_LibCSignalHandler;
import jnr.posix.MsgHdr;
import jnr.posix.NativeGroup;
import jnr.posix.NativePasswd;
import jnr.posix.NativeTimes;
import jnr.posix.RLimit;
import jnr.posix.Timespec;
import jnr.posix.Timeval;

public interface LibC {

  public abstract int chmod(CharSequence arg0, int arg1);

  public abstract int fchmod(int arg0, int arg1);

  public abstract int chown(CharSequence arg0, int arg1, int arg2);

  public abstract int fchown(int arg0, int arg1, int arg2);

  public abstract int fstat(int arg0, @Out @Transient FileStat arg1);

  public abstract int fstat64(int arg0, @Out @Transient FileStat arg1);

  public abstract String getenv(CharSequence arg0);

    @IgnoreError
  public abstract int getegid();

  public abstract int setegid(int arg0);

    @IgnoreError
  public abstract int geteuid();

  public abstract int seteuid(int arg0);

    @IgnoreError
  public abstract int getgid();

  public abstract String getlogin();

  public abstract int setgid(int arg0);

  public abstract int getpgid();

  public abstract int getpgid(int arg0);

  public abstract int setpgid(int arg0, int arg1);

  public abstract int getpgrp();

  public abstract int setpgrp(int arg0, int arg1);

    @IgnoreError
  public abstract int getppid();

    @IgnoreError
  public abstract int getpid();

  public abstract NativePasswd getpwent();

  public abstract NativePasswd getpwuid(int arg0);

  public abstract NativePasswd getpwnam(CharSequence arg0);

  public abstract NativeGroup getgrent();

  public abstract NativeGroup getgrgid(int arg0);

  public abstract NativeGroup getgrnam(CharSequence arg0);

  public abstract int setpwent();

  public abstract int endpwent();

  public abstract int setgrent();

  public abstract int endgrent();

    @IgnoreError
  public abstract int getuid();

  public abstract int setsid();

  public abstract int setuid(int arg0);

  public abstract int getrlimit(int arg0, @Out RLimit arg1);

  public abstract int getrlimit(int arg0, Pointer arg1);

  public abstract int setrlimit(int arg0, @In RLimit arg1);

  public abstract int setrlimit(int arg0, Pointer arg1);

  public abstract int kill(int arg0, int arg1);

  public abstract int kill(long arg0, int arg1);

  public abstract int dup(int arg0);

  public abstract int dup2(int arg0, int arg1);

    @Variadic(fixedCount = 2)
  public abstract int fcntl(int arg0, int arg1, Flock arg2);

    @Variadic(fixedCount = 2)
  public abstract int fcntl(int arg0, int arg1, Pointer arg2);

    @Variadic(fixedCount = 2)
  public abstract int fcntl(int arg0, int arg1);

    @Variadic(fixedCount = 2)
  public abstract int fcntl(int arg0, int arg1, @u_int64_t int arg2);

    @Deprecated
  public abstract int fcntl(int arg0, int arg1, int[] arg2);

  public abstract int access(CharSequence arg0, int arg1);

  public abstract int getdtablesize();

    @intptr_t
  public abstract long signal(int arg0, LibC_LibCSignalHandler arg1);

  public abstract int raise(int arg0);

  public abstract int lchmod(CharSequence arg0, int arg1);

  public abstract int lchown(CharSequence arg0, int arg1, int arg2);

  public abstract int link(CharSequence arg0, CharSequence arg1);

  public abstract int lstat(CharSequence arg0, @Out @Transient FileStat arg1);

  public abstract int lstat64(CharSequence arg0, @Out @Transient FileStat arg1);

  public abstract int mkdir(CharSequence arg0, int arg1);

  public abstract int rmdir(CharSequence arg0);

  public abstract int stat(CharSequence arg0, @Out @Transient FileStat arg1);

  public abstract int stat64(CharSequence arg0, @Out @Transient FileStat arg1);

  public abstract int symlink(CharSequence arg0, CharSequence arg1);

  public abstract int readlink(CharSequence arg0, @Out ByteBuffer arg1, int arg2);

  public abstract int readlink(CharSequence arg0, @Out byte[] arg1, int arg2);

  public abstract int readlink(CharSequence arg0, Pointer arg1, int arg2);

  public abstract int setenv(CharSequence arg0, CharSequence arg1, int arg2);

    @IgnoreError
  public abstract int umask(int arg0);

  public abstract int unsetenv(CharSequence arg0);

  public abstract int utimes(CharSequence arg0, @In Timeval[] arg1);

  public abstract int utimes(String arg0, @In Pointer arg1);

  public abstract int futimes(int arg0, @In Timeval[] arg1);

  public abstract int lutimes(CharSequence arg0, @In Timeval[] arg1);

  public abstract int utimensat(int arg0, String arg1, Timespec[] arg2, int arg3);

  public abstract int utimensat(int arg0, String arg1, @In Pointer arg2, int arg3);

  public abstract int futimens(int arg0, Timespec[] arg1);

  public abstract int futimens(int arg0, @In Pointer arg1);

  public abstract int fork();

  public abstract int waitpid(long arg0, @Out int[] arg1, int arg2);

  public abstract int wait(@Out int[] arg0);

  public abstract int getpriority(int arg0, int arg1);

  public abstract int setpriority(int arg0, int arg1, int arg2);

    @IgnoreError
  public abstract int isatty(int arg0);

    @ssize_t
  public abstract long read(int arg0, @Out byte[] arg1, @size_t long arg2);

    @ssize_t
  public abstract long write(int arg0, @In byte[] arg1, @size_t long arg2);

    @ssize_t
  public abstract long read(int arg0, @Out ByteBuffer arg1, @size_t long arg2);

    @ssize_t
  public abstract long write(int arg0, @In ByteBuffer arg1, @size_t long arg2);

    @ssize_t
  public abstract long pread(int arg0, @Out byte[] arg1, @size_t long arg2, @off_t long arg3);

    @ssize_t
  public abstract long pwrite(int arg0, @In byte[] arg1, @size_t long arg2, @off_t long arg3);

    @ssize_t
  public abstract long pread(int arg0, @Out ByteBuffer arg1, @size_t long arg2, @off_t long arg3);

    @ssize_t
  public abstract long pwrite(int arg0, @In ByteBuffer arg1, @size_t long arg2, @off_t long arg3);

  public abstract int read(int arg0, @Out byte[] arg1, int arg2);

  public abstract int write(int arg0, @In byte[] arg1, int arg2);

  public abstract int read(int arg0, @Out ByteBuffer arg1, int arg2);

  public abstract int write(int arg0, @In ByteBuffer arg1, int arg2);

  public abstract int pread(int arg0, @Out byte[] arg1, int arg2, int arg3);

  public abstract int pwrite(int arg0, @In byte[] arg1, int arg2, int arg3);

  public abstract int pread(int arg0, @Out ByteBuffer arg1, int arg2, int arg3);

  public abstract int pwrite(int arg0, @In ByteBuffer arg1, int arg2, int arg3);

  public abstract long lseek(int arg0, long arg1, int arg2);

  public abstract int close(int arg0);

  public abstract int execv(CharSequence arg0, @In CharSequence[] arg1);

  public abstract int execve(CharSequence arg0, @In CharSequence[] arg1, @In CharSequence[] arg2);

  public abstract int chdir(CharSequence arg0);

  public abstract long sysconf(Sysconf arg0);

  public abstract int confstr(Confstr arg0, @Out ByteBuffer arg1, int arg2);

  public abstract int fpathconf(int arg0, Pathconf arg1);

    @clock_t
  public abstract long times(@Out @Transient NativeTimes arg0);

  public abstract int flock(int arg0, int arg1);

  public abstract int unlink(CharSequence arg0);

    @Variadic(fixedCount = 2)
  public abstract int open(CharSequence arg0, int arg1, @u_int32_t int arg2);

  public abstract int pipe(@Out int[] arg0);

  public abstract int truncate(CharSequence arg0, long arg1);

  public abstract int ftruncate(int arg0, long arg1);

  public abstract int rename(CharSequence arg0, CharSequence arg1);

  public abstract long getcwd(byte[] arg0, int arg1);

  public abstract int gethostname(@Out ByteBuffer arg0, int arg1);

  public abstract int fsync(int arg0);

  public abstract int fdatasync(int arg0);

  public abstract int socketpair(int arg0, int arg1, int arg2, @Out int[] arg3);

  public abstract int sendmsg(int arg0, @In MsgHdr arg1, int arg2);

  public abstract int recvmsg(int arg0, @Direct MsgHdr arg1, int arg2);

  public abstract int setsockopt(int arg0, int arg1, int arg2, @In ByteBuffer arg3, int arg4);

  public abstract int getsockopt(int arg0, int arg1, int arg2, @Out ByteBuffer arg3, @In @Out IntByReference arg4);

  public abstract Variable environ();

  public abstract int syscall(int arg0);

  public abstract int syscall(int arg0, int arg1);

  public abstract int syscall(int arg0, int arg1, int arg2);

  public abstract int syscall(int arg0, int arg1, int arg2, int arg3);

  public abstract int daemon(int arg0, int arg1);

  public abstract int getgroups(int arg0, int[] arg1);

  public abstract String nl_langinfo(int arg0);

  public abstract String setlocale(int arg0, String arg1);

  public abstract String strerror(int arg0);

  public abstract int gettimeofday(Timeval arg0, long arg1);

}