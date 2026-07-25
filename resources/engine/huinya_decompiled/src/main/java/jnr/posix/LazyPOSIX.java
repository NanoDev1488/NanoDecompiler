// исходный (обфусцированный) внутренний класс: jnr.posix.LazyPOSIX
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
import jnr.posix.POSIXFactory;
import jnr.posix.POSIXHandler;
import jnr.posix.Passwd;
import jnr.posix.RLimit;
import jnr.posix.SignalHandler;
import jnr.posix.Times;
import jnr.posix.Timeval;
import jnr.posix.util.ProcessMaker;

final class LazyPOSIX implements POSIX {

    // ---- поля ----
  private final POSIXHandler handler;
  private final boolean useNativePosix;
  private volatile POSIX posix;

   LazyPOSIX(POSIXHandler arg0, boolean arg1) { // было: <init>
        super();
        handler = arg0;
        useNativePosix = arg1;
    }

  private final POSIX posix() {
        return posix == null ? loadPOSIX() : posix;
    }

  private final synchronized POSIX loadPOSIX() {
        POSIX __stk1;
        if (posix == null) {
            posix = POSIXFactory.loadPOSIX(handler, useNativePosix);
            __stk1 = POSIXFactory.loadPOSIX(handler, useNativePosix);
        } else {
            __stk1 = posix;
        }
        return __stk1;
    }

  public ProcessMaker newProcessMaker(String[] arg0) {
        return posix().newProcessMaker(arg0);
    }

  public ProcessMaker newProcessMaker() {
        return posix().newProcessMaker();
    }

  public FileStat allocateStat() {
        return posix().allocateStat();
    }

  public MsgHdr allocateMsgHdr() {
        return posix().allocateMsgHdr();
    }

  public int chdir(String arg0) {
        return posix().chdir(arg0);
    }

  public int chmod(String arg0, int arg1) {
        return posix().chmod(arg0, arg1);
    }

  public int fchmod(int arg0, int arg1) {
        return posix().fchmod(arg0, arg1);
    }

  public int chown(String arg0, int arg1, int arg2) {
        return posix().chown(arg0, arg1, arg2);
    }

  public CharSequence crypt(CharSequence arg0, CharSequence arg1) {
        return posix().crypt(arg0, arg1);
    }

  public byte[] crypt(byte[] arg0, byte[] arg1) {
        return posix().crypt(arg0, arg1);
    }

  public int fchown(int arg0, int arg1, int arg2) {
        return posix().fchown(arg0, arg1, arg2);
    }

  public int endgrent() {
        return posix().endgrent();
    }

  public int endpwent() {
        return posix().endpwent();
    }

  public int errno() {
        return posix().errno();
    }

  public void errno(int arg0) {
        posix().errno(arg0);
    }

  public int exec(String arg0, String[] arg1) {
        return posix().exec(arg0, arg1);
    }

  public int exec(String arg0, String[] arg1, String[] arg2) {
        return posix().exec(arg0, arg1, arg2);
    }

  public int execv(String arg0, String[] arg1) {
        return posix().execv(arg0, arg1);
    }

  public int execve(String arg0, String[] arg1, String[] arg2) {
        return posix().execve(arg0, arg1, arg2);
    }

  public int fork() {
        return posix().fork();
    }

  public FileStat fstat(int arg0) {
        return posix().fstat(arg0);
    }

  public int fstat(int arg0, FileStat arg1) {
        return posix().fstat(arg0, arg1);
    }

  public FileStat fstat(FileDescriptor arg0) {
        return posix().fstat(arg0);
    }

  public int fstat(FileDescriptor arg0, FileStat arg1) {
        return posix().fstat(arg0, arg1);
    }

  public int getegid() {
        return posix().getegid();
    }

  public int geteuid() {
        return posix().geteuid();
    }

  public int getgid() {
        return posix().getgid();
    }

  public int getdtablesize() {
        return posix().getdtablesize();
    }

  public Group getgrent() {
        return posix().getgrent();
    }

  public Group getgrgid(int arg0) {
        return posix().getgrgid(arg0);
    }

  public Group getgrnam(String arg0) {
        return posix().getgrnam(arg0);
    }

  public String getlogin() {
        return posix().getlogin();
    }

  public int getpgid() {
        return posix().getpgid();
    }

  public int getpgid(int arg0) {
        return posix().getpgid(arg0);
    }

  public int getpgrp() {
        return posix().getpgrp();
    }

  public int getpid() {
        return posix().getpid();
    }

  public int getppid() {
        return posix().getppid();
    }

  public int getpriority(int arg0, int arg1) {
        return posix().getpriority(arg0, arg1);
    }

  public Passwd getpwent() {
        return posix().getpwent();
    }

  public Passwd getpwnam(String arg0) {
        return posix().getpwnam(arg0);
    }

  public Passwd getpwuid(int arg0) {
        return posix().getpwuid(arg0);
    }

  public int getuid() {
        return posix().getuid();
    }

  public int getrlimit(int arg0, RLimit arg1) {
        return posix().getrlimit(arg0, arg1);
    }

  public int getrlimit(int arg0, Pointer arg1) {
        return posix().getrlimit(arg0, arg1);
    }

  public RLimit getrlimit(int arg0) {
        return posix().getrlimit(arg0);
    }

  public int setrlimit(int arg0, RLimit arg1) {
        return posix().setrlimit(arg0, arg1);
    }

  public int setrlimit(int arg0, Pointer arg1) {
        return posix().setrlimit(arg0, arg1);
    }

  public int setrlimit(int arg0, long arg1, long arg2) {
        return posix().setrlimit(arg0, arg1, arg2);
    }

  public boolean isatty(FileDescriptor arg0) {
        return posix().isatty(arg0);
    }

  public int isatty(int arg0) {
        return posix().isatty(arg0);
    }

  public int kill(int arg0, int arg1) {
        return kill(((long) arg0), arg1);
    }

  public int kill(long arg0, int arg1) {
        return posix().kill(arg0, arg1);
    }

  public SignalHandler signal(Signal arg0, SignalHandler arg1) {
        return posix().signal(arg0, arg1);
    }

  public int raise(int arg0) {
        return posix().raise(arg0);
    }

  public int lchmod(String arg0, int arg1) {
        return posix().lchmod(arg0, arg1);
    }

  public int lchown(String arg0, int arg1, int arg2) {
        return posix().lchown(arg0, arg1, arg2);
    }

  public int link(String arg0, String arg1) {
        return posix().link(arg0, arg1);
    }

  public FileStat lstat(String arg0) {
        return posix().lstat(arg0);
    }

  public int lstat(String arg0, FileStat arg1) {
        return posix().lstat(arg0, arg1);
    }

  public int mkdir(String arg0, int arg1) {
        return posix().mkdir(arg0, arg1);
    }

  public String readlink(String arg0) {
        return posix().readlink(arg0);
    }

  public int readlink(CharSequence arg0, byte[] arg1, int arg2) {
        return posix().readlink(arg0, arg1, arg2);
    }

  public int readlink(CharSequence arg0, ByteBuffer arg1, int arg2) {
        return posix().readlink(arg0, arg1, arg2);
    }

  public int readlink(CharSequence arg0, Pointer arg1, int arg2) {
        return posix().readlink(arg0, arg1, arg2);
    }

  public int rmdir(String arg0) {
        return posix().rmdir(arg0);
    }

  public int setegid(int arg0) {
        return posix().setegid(arg0);
    }

  public int seteuid(int arg0) {
        return posix().seteuid(arg0);
    }

  public int setgid(int arg0) {
        return posix().setgid(arg0);
    }

  public int setgrent() {
        return posix().setgrent();
    }

  public int setpgid(int arg0, int arg1) {
        return posix().setpgid(arg0, arg1);
    }

  public int setpgrp(int arg0, int arg1) {
        return posix().setpgrp(arg0, arg1);
    }

  public int setpriority(int arg0, int arg1, int arg2) {
        return posix().setpriority(arg0, arg1, arg2);
    }

  public int setpwent() {
        return posix().setpwent();
    }

  public int setsid() {
        return posix().setsid();
    }

  public int setuid(int arg0) {
        return posix().setuid(arg0);
    }

  public FileStat stat(String arg0) {
        return posix().stat(arg0);
    }

  public int stat(String arg0, FileStat arg1) {
        return posix().stat(arg0, arg1);
    }

  public int symlink(String arg0, String arg1) {
        return posix().symlink(arg0, arg1);
    }

  public int umask(int arg0) {
        return posix().umask(arg0);
    }

  public int utimes(String arg0, long[] arg1, long[] arg2) {
        return posix().utimes(arg0, arg1, arg2);
    }

  public int utimes(String arg0, Pointer arg1) {
        return posix().utimes(arg0, arg1);
    }

  public int futimes(int arg0, long[] arg1, long[] arg2) {
        return posix().futimes(arg0, arg1, arg2);
    }

  public int lutimes(String arg0, long[] arg1, long[] arg2) {
        return posix().lutimes(arg0, arg1, arg2);
    }

  public int utimensat(int arg0, String arg1, long[] arg2, long[] arg3, int arg4) {
        return posix().utimensat(arg0, arg1, arg2, arg3, arg4);
    }

  public int utimensat(int arg0, String arg1, Pointer arg2, int arg3) {
        return posix().utimensat(arg0, arg1, arg2, arg3);
    }

  public int futimens(int arg0, long[] arg1, long[] arg2) {
        return posix().futimens(arg0, arg1, arg2);
    }

  public int futimens(int arg0, Pointer arg1) {
        return posix().futimens(arg0, arg1);
    }

  public int wait(int[] arg0) {
        return posix().wait(arg0);
    }

  public int waitpid(int arg0, int[] arg1, int arg2) {
        return waitpid(((long) arg0), arg1, arg2);
    }

  public int waitpid(long arg0, int[] arg1, int arg2) {
        return posix().waitpid(arg0, arg1, arg2);
    }

  public boolean isNative() {
        return posix().isNative();
    }

  public LibC libc() {
        return posix().libc();
    }

  public Pointer environ() {
        return posix().environ();
    }

  public String getenv(String arg0) {
        return posix().getenv(arg0);
    }

  public int setenv(String arg0, String arg1, int arg2) {
        return posix().setenv(arg0, arg1, arg2);
    }

  public int unsetenv(String arg0) {
        return posix().unsetenv(arg0);
    }

  public long posix_spawnp(String arg0, Collection arg1, Collection arg2, Collection arg3) {
        return posix().posix_spawnp(arg0, arg1, arg2, arg3);
    }

  public long posix_spawnp(String arg0, Collection arg1, Collection arg2, Collection arg3, Collection arg4) {
        return posix().posix_spawnp(arg0, arg1, arg2, arg3, arg4);
    }

  public long sysconf(Sysconf arg0) {
        return posix().sysconf(arg0);
    }

  public int confstr(Confstr arg0, ByteBuffer arg1, int arg2) {
        return posix().confstr(arg0, arg1, arg2);
    }

  public int fpathconf(int arg0, Pathconf arg1) {
        return posix().fpathconf(arg0, arg1);
    }

  public Times times() {
        return posix().times();
    }

  public int flock(int arg0, int arg1) {
        return posix().flock(arg0, arg1);
    }

  public int dup(int arg0) {
        return posix().dup(arg0);
    }

  public int dup2(int arg0, int arg1) {
        return posix().dup2(arg0, arg1);
    }

  public int fcntlInt(int arg0, Fcntl arg1, int arg2) {
        return posix().fcntlInt(arg0, arg1, arg2);
    }

  public int fcntl(int arg0, Fcntl arg1) {
        return posix().fcntl(arg0, arg1);
    }

  public int fcntl(int arg0, Fcntl arg1, int arg2) {
        return posix().fcntl(arg0, arg1, arg2);
    }

    @Deprecated
  public int fcntl(int arg0, Fcntl arg1, int[] arg2) {
        return posix().fcntl(arg0, arg1);
    }

  public int access(CharSequence arg0, int arg1) {
        return posix().access(arg0, arg1);
    }

  public int close(int arg0) {
        return posix().close(arg0);
    }

  public int unlink(CharSequence arg0) {
        return posix().unlink(arg0);
    }

  public int open(CharSequence arg0, int arg1, int arg2) {
        return posix().open(arg0, arg1, arg2);
    }

  public long read(int arg0, byte[] arg1, long arg2) {
        return posix().read(arg0, arg1, arg2);
    }

  public long write(int arg0, byte[] arg1, long arg2) {
        return posix().write(arg0, arg1, arg2);
    }

  public long read(int arg0, ByteBuffer arg1, long arg2) {
        return posix().read(arg0, arg1, arg2);
    }

  public long write(int arg0, ByteBuffer arg1, long arg2) {
        return posix().write(arg0, arg1, arg2);
    }

  public long pread(int arg0, byte[] arg1, long arg2, long arg3) {
        return posix().pread(arg0, arg1, arg2, arg3);
    }

  public long pwrite(int arg0, byte[] arg1, long arg2, long arg3) {
        return posix().pwrite(arg0, arg1, arg2, arg3);
    }

  public long pread(int arg0, ByteBuffer arg1, long arg2, long arg3) {
        return posix().pread(arg0, arg1, arg2, arg3);
    }

  public long pwrite(int arg0, ByteBuffer arg1, long arg2, long arg3) {
        return posix().pwrite(arg0, arg1, arg2, arg3);
    }

  public int read(int arg0, byte[] arg1, int arg2) {
        return posix().read(arg0, arg1, arg2);
    }

  public int write(int arg0, byte[] arg1, int arg2) {
        return posix().write(arg0, arg1, arg2);
    }

  public int read(int arg0, ByteBuffer arg1, int arg2) {
        return posix().read(arg0, arg1, arg2);
    }

  public int write(int arg0, ByteBuffer arg1, int arg2) {
        return posix().write(arg0, arg1, arg2);
    }

  public int pread(int arg0, byte[] arg1, int arg2, int arg3) {
        return posix().pread(arg0, arg1, arg2, arg3);
    }

  public int pwrite(int arg0, byte[] arg1, int arg2, int arg3) {
        return posix().pwrite(arg0, arg1, arg2, arg3);
    }

  public int pread(int arg0, ByteBuffer arg1, int arg2, int arg3) {
        return posix().pread(arg0, arg1, arg2, arg3);
    }

  public int pwrite(int arg0, ByteBuffer arg1, int arg2, int arg3) {
        return posix().pwrite(arg0, arg1, arg2, arg3);
    }

  public int lseek(int arg0, long arg1, int arg2) {
        return posix().lseek(arg0, arg1, arg2);
    }

  public long lseekLong(int arg0, long arg1, int arg2) {
        return posix().lseekLong(arg0, arg1, arg2);
    }

  public int pipe(int[] arg0) {
        return posix().pipe(arg0);
    }

  public int socketpair(int arg0, int arg1, int arg2, int[] arg3) {
        return posix().socketpair(arg0, arg1, arg2, arg3);
    }

  public int sendmsg(int arg0, MsgHdr arg1, int arg2) {
        return posix().sendmsg(arg0, arg1, arg2);
    }

  public int recvmsg(int arg0, MsgHdr arg1, int arg2) {
        return posix().recvmsg(arg0, arg1, arg2);
    }

  public int truncate(CharSequence arg0, long arg1) {
        return posix().truncate(arg0, arg1);
    }

  public int ftruncate(int arg0, long arg1) {
        return posix().ftruncate(arg0, arg1);
    }

  public int rename(CharSequence arg0, CharSequence arg1) {
        return posix().rename(arg0, arg1);
    }

  public String getcwd() {
        return posix().getcwd();
    }

  public int fsync(int arg0) {
        return posix().fsync(arg0);
    }

  public int fdatasync(int arg0) {
        return posix().fdatasync(arg0);
    }

  public int mkfifo(String arg0, int arg1) {
        return posix().mkfifo(arg0, arg1);
    }

  public String gethostname() {
        return posix().gethostname();
    }

  public int daemon(int arg0, int arg1) {
        return posix().daemon(arg0, arg1);
    }

  public long[] getgroups() {
        return posix().getgroups();
    }

  public int getgroups(int arg0, int[] arg1) {
        return posix().getgroups(arg0, arg1);
    }

  public String nl_langinfo(int arg0) {
        return posix().nl_langinfo(arg0);
    }

  public String setlocale(int arg0, String arg1) {
        return posix().setlocale(arg0, arg1);
    }

  public String strerror(int arg0) {
        return posix().strerror(arg0);
    }

  public Timeval allocateTimeval() {
        return posix().allocateTimeval();
    }

  public int gettimeofday(Timeval arg0) {
        return posix().gettimeofday(arg0);
    }

}