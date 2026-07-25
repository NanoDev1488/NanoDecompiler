// исходный (обфусцированный) внутренний класс: jnr.posix.POSIX
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
import jnr.ffi.annotations.Out;
import jnr.posix.FileStat;
import jnr.posix.Group;
import jnr.posix.LibC;
import jnr.posix.MsgHdr;
import jnr.posix.Passwd;
import jnr.posix.RLimit;
import jnr.posix.SignalHandler;
import jnr.posix.Times;
import jnr.posix.Timeval;
import jnr.posix.util.ProcessMaker;

public interface POSIX {

  public abstract CharSequence crypt(CharSequence arg0, CharSequence arg1);

  public abstract byte[] crypt(byte[] arg0, byte[] arg1);

  public abstract FileStat allocateStat();

  public abstract int chmod(String arg0, int arg1);

  public abstract int fchmod(int arg0, int arg1);

  public abstract int chown(String arg0, int arg1, int arg2);

  public abstract int fchown(int arg0, int arg1, int arg2);

  public abstract int exec(String arg0, String[] arg1);

  public abstract int exec(String arg0, String[] arg1, String[] arg2);

  public abstract int execv(String arg0, String[] arg1);

  public abstract int execve(String arg0, String[] arg1, String[] arg2);

  public abstract int fork();

  public abstract FileStat fstat(FileDescriptor arg0);

  public abstract FileStat fstat(int arg0);

  public abstract int fstat(FileDescriptor arg0, FileStat arg1);

  public abstract int fstat(int arg0, FileStat arg1);

  public abstract Pointer environ();

  public abstract String getenv(String arg0);

  public abstract int getegid();

  public abstract int geteuid();

  public abstract int seteuid(int arg0);

  public abstract int getgid();

  public abstract int getdtablesize();

  public abstract String getlogin();

  public abstract int getpgid();

  public abstract int getpgid(int arg0);

  public abstract int getpgrp();

  public abstract int getpid();

  public abstract int getppid();

  public abstract int getpriority(int arg0, int arg1);

  public abstract Passwd getpwent();

  public abstract Passwd getpwuid(int arg0);

  public abstract Passwd getpwnam(String arg0);

  public abstract Group getgrgid(int arg0);

  public abstract Group getgrnam(String arg0);

  public abstract Group getgrent();

  public abstract int endgrent();

  public abstract int setgrent();

  public abstract int endpwent();

  public abstract int setpwent();

  public abstract int getuid();

  public abstract int getrlimit(int arg0, RLimit arg1);

  public abstract int getrlimit(int arg0, Pointer arg1);

  public abstract RLimit getrlimit(int arg0);

  public abstract int setrlimit(int arg0, RLimit arg1);

  public abstract int setrlimit(int arg0, Pointer arg1);

  public abstract int setrlimit(int arg0, long arg1, long arg2);

  public abstract boolean isatty(FileDescriptor arg0);

  public abstract int isatty(int arg0);

  public abstract int kill(int arg0, int arg1);

  public abstract int kill(long arg0, int arg1);

  public abstract SignalHandler signal(Signal arg0, SignalHandler arg1);

  public abstract int raise(int arg0);

  public abstract int lchmod(String arg0, int arg1);

  public abstract int lchown(String arg0, int arg1, int arg2);

  public abstract int link(String arg0, String arg1);

  public abstract FileStat lstat(String arg0);

  public abstract int lstat(String arg0, FileStat arg1);

  public abstract int mkdir(String arg0, int arg1);

  public abstract String readlink(String arg0);

  public abstract int readlink(CharSequence arg0, byte[] arg1, int arg2);

  public abstract int readlink(CharSequence arg0, ByteBuffer arg1, int arg2);

  public abstract int readlink(CharSequence arg0, Pointer arg1, int arg2);

  public abstract int rmdir(String arg0);

  public abstract int setenv(String arg0, String arg1, int arg2);

  public abstract int setsid();

  public abstract int setgid(int arg0);

  public abstract int setegid(int arg0);

  public abstract int setpgid(int arg0, int arg1);

  public abstract int setpgrp(int arg0, int arg1);

  public abstract int setpriority(int arg0, int arg1, int arg2);

  public abstract int setuid(int arg0);

  public abstract FileStat stat(String arg0);

  public abstract int stat(String arg0, FileStat arg1);

  public abstract int symlink(String arg0, String arg1);

  public abstract int umask(int arg0);

  public abstract int unsetenv(String arg0);

  public abstract int utimes(String arg0, long[] arg1, long[] arg2);

  public abstract int utimes(String arg0, Pointer arg1);

  public abstract int futimes(int arg0, long[] arg1, long[] arg2);

  public abstract int lutimes(String arg0, long[] arg1, long[] arg2);

  public abstract int utimensat(int arg0, String arg1, long[] arg2, long[] arg3, int arg4);

  public abstract int utimensat(int arg0, String arg1, Pointer arg2, int arg3);

  public abstract int futimens(int arg0, long[] arg1, long[] arg2);

  public abstract int futimens(int arg0, Pointer arg1);

  public abstract int waitpid(int arg0, int[] arg1, int arg2);

  public abstract int waitpid(long arg0, int[] arg1, int arg2);

  public abstract int wait(int[] arg0);

  public abstract int errno();

  public abstract void errno(int arg0);

  public abstract String strerror(int arg0);

  public abstract int chdir(String arg0);

  public abstract boolean isNative();

  public abstract LibC libc();

  public abstract ProcessMaker newProcessMaker(String[] arg0);

  public abstract ProcessMaker newProcessMaker();

  public abstract long sysconf(Sysconf arg0);

  public abstract int confstr(Confstr arg0, @Out ByteBuffer arg1, int arg2);

  public abstract int fpathconf(int arg0, Pathconf arg1);

  public abstract Times times();

  public abstract long posix_spawnp(String arg0, Collection arg1, Collection arg2, Collection arg3);

  public abstract long posix_spawnp(String arg0, Collection arg1, Collection arg2, Collection arg3, Collection arg4);

  public abstract int flock(int arg0, int arg1);

  public abstract int dup(int arg0);

  public abstract int dup2(int arg0, int arg1);

  public abstract int fcntlInt(int arg0, Fcntl arg1, int arg2);

  public abstract int fcntl(int arg0, Fcntl arg1, int arg2);

  public abstract int fcntl(int arg0, Fcntl arg1);

  public abstract int access(CharSequence arg0, int arg1);

  public abstract int close(int arg0);

  public abstract int unlink(CharSequence arg0);

  public abstract int open(CharSequence arg0, int arg1, int arg2);

  public abstract long read(int arg0, byte[] arg1, long arg2);

  public abstract long write(int arg0, byte[] arg1, long arg2);

  public abstract long read(int arg0, ByteBuffer arg1, long arg2);

  public abstract long write(int arg0, ByteBuffer arg1, long arg2);

  public abstract long pread(int arg0, byte[] arg1, long arg2, long arg3);

  public abstract long pwrite(int arg0, byte[] arg1, long arg2, long arg3);

  public abstract long pread(int arg0, ByteBuffer arg1, long arg2, long arg3);

  public abstract long pwrite(int arg0, ByteBuffer arg1, long arg2, long arg3);

  public abstract int read(int arg0, byte[] arg1, int arg2);

  public abstract int write(int arg0, byte[] arg1, int arg2);

  public abstract int read(int arg0, ByteBuffer arg1, int arg2);

  public abstract int write(int arg0, ByteBuffer arg1, int arg2);

  public abstract int pread(int arg0, byte[] arg1, int arg2, int arg3);

  public abstract int pwrite(int arg0, byte[] arg1, int arg2, int arg3);

  public abstract int pread(int arg0, ByteBuffer arg1, int arg2, int arg3);

  public abstract int pwrite(int arg0, ByteBuffer arg1, int arg2, int arg3);

  public abstract int lseek(int arg0, long arg1, int arg2);

  public abstract long lseekLong(int arg0, long arg1, int arg2);

  public abstract int pipe(int[] arg0);

  public abstract int truncate(CharSequence arg0, long arg1);

  public abstract int ftruncate(int arg0, long arg1);

  public abstract int rename(CharSequence arg0, CharSequence arg1);

  public abstract String getcwd();

  public abstract String gethostname();

  public abstract int socketpair(int arg0, int arg1, int arg2, int[] arg3);

  public abstract int sendmsg(int arg0, MsgHdr arg1, int arg2);

  public abstract int recvmsg(int arg0, MsgHdr arg1, int arg2);

  public abstract MsgHdr allocateMsgHdr();

    @Deprecated
  public abstract int fcntl(int arg0, Fcntl arg1, int[] arg2);

  public abstract int fsync(int arg0);

  public abstract int fdatasync(int arg0);

  public abstract int mkfifo(String arg0, int arg1);

  public abstract int daemon(int arg0, int arg1);

  public abstract long[] getgroups();

  public abstract int getgroups(int arg0, int[] arg1);

  public abstract String nl_langinfo(int arg0);

  public abstract String setlocale(int arg0, String arg1);

  public abstract Timeval allocateTimeval();

  public abstract int gettimeofday(Timeval arg0);

}