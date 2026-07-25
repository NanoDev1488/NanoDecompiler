// исходный (обфусцированный) внутренний класс: jnr.posix.WindowsPOSIX
package jnr.posix;

import java.io.FileDescriptor;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import jnr.constants.platform.Errno;
import jnr.constants.platform.Fcntl;
import jnr.constants.platform.OpenFlags;
import jnr.constants.platform.WaitFlags;
import jnr.constants.platform.windows.LastError;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct_Unsigned32;
import jnr.ffi.Variable;
import jnr.ffi.byref.IntByReference;
import jnr.ffi.provider.MemoryManager;
import jnr.posix.BaseNativePOSIX;
import jnr.posix.BaseNativePOSIX_PointerConverter;
import jnr.posix.DefaultNativeTimeval;
import jnr.posix.FileStat;
import jnr.posix.FileTime;
import jnr.posix.Group;
import jnr.posix.HANDLE;
import jnr.posix.JavaLibCHelper;
import jnr.posix.LibC;
import jnr.posix.LibCProvider;
import jnr.posix.MsgHdr;
import jnr.posix.POSIXHandler;
import jnr.posix.Passwd;
import jnr.posix.SocketMacros;
import jnr.posix.Timeval;
import jnr.posix.WString;
import jnr.posix.WindowsChildRecord;
import jnr.posix.WindowsFileStat;
import jnr.posix.WindowsLibC;
import jnr.posix.WindowsPOSIX_Anon1;
import jnr.posix.WindowsRawFileStat;
import jnr.posix.WindowsSecurityAttributes;
import jnr.posix.util.MethodName;
import jnr.posix.util.WindowsHelpers;
import jnr.posix.windows.WindowsByHandleFileInformation;
import jnr.posix.windows.WindowsFindData;

public final class WindowsPOSIX extends BaseNativePOSIX {

    // ---- поля ----
  private static final int FILE_TYPE_CHAR = 2;
  private static final Map errorToErrnoMapper;
  private final FileStat checkFdStat;
  private static final int GENERIC_ALL = 268435456;
  private static final int GENERIC_READ = -2147483648;
  private static final int GENERIC_WRITE = 1073741824;
  private static final int GENERIC_EXECUTE = 33554432;
  private static final int FILE_SHARE_DELETE = 4;
  private static final int FILE_SHARE_READ = 1;
  private static final int FILE_SHARE_WRITE = 2;
  private static final int CREATE_ALWAYS = 2;
  private static final int CREATE_NEW = 1;
  private static final int OPEN_ALWAYS = 4;
  private static final int OPEN_EXISTING = 3;
  private static final int TRUNCATE_EXISTING = 5;
  public static final int FILE_FLAG_BACKUP_SEMANTICS = 33554432;
  static final int FILE_ATTRIBUTE_READONLY = 1;
  static final int INVALID_FILE_ATTRIBUTES = -1;
  private static final int STARTF_USESTDHANDLES = 256;
  public static final BaseNativePOSIX_PointerConverter PASSWD;

    static {
        errorToErrnoMapper = new HashMap();
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_INVALID_FUNCTION.value()), Errno.EINVAL);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_FILE_NOT_FOUND.value()), Errno.ENOENT);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_PATH_NOT_FOUND.value()), Errno.ENOENT);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_TOO_MANY_OPEN_FILES.value()), Errno.EMFILE);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_ACCESS_DENIED.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_INVALID_HANDLE.value()), Errno.EBADF);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_ARENA_TRASHED.value()), Errno.ENOMEM);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_NOT_ENOUGH_MEMORY.value()), Errno.ENOMEM);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_INVALID_BLOCK.value()), Errno.ENOMEM);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_BAD_ENVIRONMENT.value()), Errno.E2BIG);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_BAD_FORMAT.value()), Errno.ENOEXEC);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_INVALID_ACCESS.value()), Errno.EINVAL);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_INVALID_DATA.value()), Errno.EINVAL);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_INVALID_DRIVE.value()), Errno.ENOENT);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_CURRENT_DIRECTORY.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_NOT_SAME_DEVICE.value()), Errno.EXDEV);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_NO_MORE_FILES.value()), Errno.ENOENT);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_WRITE_PROTECT.value()), Errno.EROFS);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_BAD_UNIT.value()), Errno.ENODEV);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_NOT_READY.value()), Errno.ENXIO);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_BAD_COMMAND.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_CRC.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_BAD_LENGTH.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_SEEK.value()), Errno.EIO);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_NOT_DOS_DISK.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_SECTOR_NOT_FOUND.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_OUT_OF_PAPER.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_WRITE_FAULT.value()), Errno.EIO);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_READ_FAULT.value()), Errno.EIO);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_GEN_FAILURE.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_LOCK_VIOLATION.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_SHARING_VIOLATION.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_WRONG_DISK.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_SHARING_BUFFER_EXCEEDED.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_BAD_NETPATH.value()), Errno.ENOENT);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_NETWORK_ACCESS_DENIED.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_BAD_NET_NAME.value()), Errno.ENOENT);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_FILE_EXISTS.value()), Errno.EEXIST);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_CANNOT_MAKE.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_FAIL_I24.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_INVALID_PARAMETER.value()), Errno.EINVAL);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_NO_PROC_SLOTS.value()), Errno.EAGAIN);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_DRIVE_LOCKED.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_BROKEN_PIPE.value()), Errno.EPIPE);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_DISK_FULL.value()), Errno.ENOSPC);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_INVALID_TARGET_HANDLE.value()), Errno.EBADF);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_INVALID_HANDLE.value()), Errno.EINVAL);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_WAIT_NO_CHILDREN.value()), Errno.ECHILD);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_CHILD_NOT_COMPLETE.value()), Errno.ECHILD);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_DIRECT_ACCESS_HANDLE.value()), Errno.EBADF);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_NEGATIVE_SEEK.value()), Errno.EINVAL);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_SEEK_ON_DEVICE.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_DIR_NOT_EMPTY.value()), Errno.ENOTEMPTY);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_DIRECTORY.value()), Errno.ENOTDIR);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_NOT_LOCKED.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_BAD_PATHNAME.value()), Errno.ENOENT);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_MAX_THRDS_REACHED.value()), Errno.EAGAIN);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_LOCK_FAILED.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_ALREADY_EXISTS.value()), Errno.EEXIST);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_INVALID_STARTING_CODESEG.value()), Errno.ENOEXEC);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_INVALID_STACKSEG.value()), Errno.ENOEXEC);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_INVALID_MODULETYPE.value()), Errno.ENOEXEC);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_INVALID_EXE_SIGNATURE.value()), Errno.ENOEXEC);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_EXE_MARKED_INVALID.value()), Errno.ENOEXEC);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_BAD_EXE_FORMAT.value()), Errno.ENOEXEC);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_ITERATED_DATA_EXCEEDS_64k.value()), Errno.ENOEXEC);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_INVALID_MINALLOCSIZE.value()), Errno.ENOEXEC);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_DYNLINK_FROM_INVALID_RING.value()), Errno.ENOEXEC);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_IOPL_NOT_ENABLED.value()), Errno.ENOEXEC);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_INVALID_SEGDPL.value()), Errno.ENOEXEC);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_AUTODATASEG_EXCEEDS_64k.value()), Errno.ENOEXEC);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_RING2SEG_MUST_BE_MOVABLE.value()), Errno.ENOEXEC);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_RELOC_CHAIN_XEEDS_SEGLIM.value()), Errno.ENOEXEC);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_INFLOOP_IN_RELOC_CHAIN.value()), Errno.ENOEXEC);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_FILENAME_EXCED_RANGE.value()), Errno.ENOENT);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_NESTING_NOT_ALLOWED.value()), Errno.EAGAIN);
        errorToErrnoMapper.put(Integer.valueOf(229), Errno.EPIPE);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_BAD_PIPE.value()), Errno.EPIPE);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_PIPE_BUSY.value()), Errno.EAGAIN);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_NO_DATA.value()), Errno.EPIPE);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_PIPE_NOT_CONNECTED.value()), Errno.EPIPE);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_OPERATION_ABORTED.value()), Errno.EINTR);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_NOT_ENOUGH_QUOTA.value()), Errno.ENOMEM);
        errorToErrnoMapper.put(Integer.valueOf(LastError.ERROR_MOD_NOT_FOUND.value()), Errno.ENOENT);
        errorToErrnoMapper.put(Integer.valueOf(LastError.WSAENAMETOOLONG.value()), Errno.ENAMETOOLONG);
        errorToErrnoMapper.put(Integer.valueOf(LastError.WSAENOTEMPTY.value()), Errno.ENOTEMPTY);
        errorToErrnoMapper.put(Integer.valueOf(LastError.WSAEINTR.value()), Errno.EINTR);
        errorToErrnoMapper.put(Integer.valueOf(LastError.WSAEBADF.value()), Errno.EBADF);
        errorToErrnoMapper.put(Integer.valueOf(LastError.WSAEACCES.value()), Errno.EACCES);
        errorToErrnoMapper.put(Integer.valueOf(LastError.WSAEFAULT.value()), Errno.EFAULT);
        errorToErrnoMapper.put(Integer.valueOf(LastError.WSAEINVAL.value()), Errno.EINVAL);
        errorToErrnoMapper.put(Integer.valueOf(LastError.WSAEMFILE.value()), Errno.EMFILE);
        PASSWD = new WindowsPOSIX_Anon1();
    }

   WindowsPOSIX(LibCProvider arg0, POSIXHandler arg1) { // было: <init>
        super(arg0, arg1);
        checkFdStat = new WindowsFileStat(this);
    }

  public FileStat allocateStat() {
        return new WindowsRawFileStat(this, handler);
    }

  public MsgHdr allocateMsgHdr() {
        handler.unimplementedError(MethodName.getCallerMethodName());
        return null;
    }

  public SocketMacros socketMacros() {
        handler.unimplementedError(MethodName.getCallerMethodName());
        return null;
    }

  public int kill(int arg0, int arg1) {
        handler.unimplementedError("kill");
        return -1;
    }

  public int kill(long arg0, int arg1) {
        handler.unimplementedError("kill");
        return -1;
    }

  public int chmod(String arg0, int arg1) {
        return wlibc()._wchmod(WString.path(arg0), arg1);
    }

  public int chdir(String arg0) {
        return wlibc()._wchdir(WString.path(arg0));
    }

  public int chown(String arg0, int arg1, int arg2) {
        return 0;
    }

  public int exec(String arg0, String[] arg1) {
        if (arg1.length != 1) {
            return aspawn(true, null, arg1, arg0, null);
        } else {
            return spawn(true, ((String) arg1[0]), null, arg0, null);
        }
    }

  public CharSequence crypt(CharSequence arg0, CharSequence arg1) {
        return JavaLibCHelper.crypt(arg0, arg1);
    }

  public byte[] crypt(byte[] arg0, byte[] arg1) {
        return JavaLibCHelper.crypt(arg0, arg1);
    }

  public int exec(String arg0, String[] arg1, String[] arg2) {
        if (arg1.length != 1) {
            return aspawn(true, null, arg1, arg0, arg2);
        } else {
            return spawn(true, ((String) arg1[0]), null, arg0, arg2);
        }
    }

  public int execv(String arg0, String[] arg1) {
        handler.unimplementedError("egid");
        return -1;
    }

  public int getegid() {
        handler.unimplementedError("egid");
        return -1;
    }

  public int setegid(int arg0) {
        handler.unimplementedError("setegid");
        return -1;
    }

  public int geteuid() {
        return 0;
    }

  public int seteuid(int arg0) {
        handler.unimplementedError("seteuid");
        return -1;
    }

  public int getuid() {
        return 0;
    }

  public int setuid(int arg0) {
        handler.unimplementedError("setuid");
        return -1;
    }

  public int getgid() {
        return 0;
    }

  public int setgid(int arg0) {
        handler.unimplementedError("setgid");
        return -1;
    }

  public int getpgid(int arg0) {
        handler.unimplementedError("getpgid");
        return -1;
    }

  public int getpgid() {
        handler.unimplementedError("getpgid");
        return -1;
    }

  public int setpgid(int arg0, int arg1) {
        handler.unimplementedError("setpgid");
        return -1;
    }

  public int getpriority(int arg0, int arg1) {
        handler.unimplementedError("getpriority");
        return -1;
    }

  public int setpriority(int arg0, int arg1, int arg2) {
        handler.unimplementedError("setpriority");
        return -1;
    }

  public int getpid() {
        return wlibc()._getpid();
    }

  public int getppid() {
        return 0;
    }

  public int lchmod(String arg0, int arg1) {
        handler.unimplementedError("lchmod");
        return -1;
    }

  public int lchown(String arg0, int arg1, int arg2) {
        handler.unimplementedError("lchown");
        return -1;
    }

  public String gethostname() {
        ByteBuffer var1 = ByteBuffer.allocate(64);
        IntByReference var2 = new IntByReference(var1.capacity() - 1);
        if (wlibc().GetComputerNameW(var1, var2)) {
            var1.limit(var2.intValue() * 2);
            return Charset.forName("UTF-16LE").decode(var1).toString();
        } else {
            return helper.gethostname();
        }
    }

  public FileStat fstat(int arg0) {
        WindowsFileStat var2 = new WindowsFileStat(this);
        if (fstat(arg0, var2) < 0) {
            handler.error(Errno.valueOf(((long) errno())), "fstat", new StringBuilder().append("").append(arg0).toString());
        }
        return var2;
    }

  public int fstat(FileDescriptor arg0, FileStat arg1) {
        WindowsByHandleFileInformation var3 = new WindowsByHandleFileInformation(getRuntime());
        if (wlibc().GetFileInformationByHandle(JavaLibCHelper.gethandle(arg0), var3) != 0) {
            (((WindowsRawFileStat) arg1)).setup(var3);
            return 0;
        } else {
            return -1;
        }
    }

  public FileStat lstat(String arg0) {
        return stat(arg0);
    }

  public int lstat(String arg0, FileStat arg1) {
        return stat(arg0, arg1);
    }

  public int stat(String arg0, FileStat arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #90 // jnr.posix.windows.WindowsFileInformation
        //      3: dup
        //      4: aload_0
        //      5: invokevirtual  #294 // jnr.posix.WindowsPOSIX.getRuntime:()Ljnr/ffi/Runtime;
        //      8: invokespecial  #328 // jnr.posix.windows.WindowsFileInformation.<init>:(Ljnr/ffi/Runtime;)V
        //     11: astore_3
        //     12: aload_1
        //     13: iconst_1
        //     14: invokestatic  #281 // jnr.posix.WString.path:(Ljava/lang/String;Z)[B
        //     17: astore  4
        //     19: aload_0
        //     20: invokespecial  #305 // jnr.posix.WindowsPOSIX.wlibc:()Ljnr/posix/WindowsLibC;
        //     23: aload  4
        //     25: iconst_0
        //     26: aload_3
        //     27: invokeinterface  #349 // jnr.posix.WindowsLibC.GetFileAttributesExW:([BILjnr/posix/windows/WindowsFileInformation;)I, count 4
        //     32: ifeq  47 (offset +15)
        //     35: aload_2
        //     36: checkcast  #84 // jnr.posix.WindowsRawFileStat
        //     39: aload_1
        //     40: aload_3
        //     41: invokevirtual  #312 // jnr.posix.WindowsRawFileStat.setup:(Ljava/lang/String;Ljnr/posix/windows/CommonFileInformation;)V
        //     44: goto  95 (offset +51)
        //     47: aload_0
        //     48: invokevirtual  #291 // jnr.posix.WindowsPOSIX.errno:()I
        //     51: istore  5
        //     53: iload  5
        //     55: getstatic  #164 // jnr.constants.platform.windows.LastError.ERROR_FILE_NOT_FOUND:Ljnr/constants/platform/windows/LastError;
        //     58: invokevirtual  #257 // jnr.constants.platform.windows.LastError.intValue:()I
        //     61: if_icmpeq  86 (offset +25)
        //     64: iload  5
        //     66: getstatic  #201 // jnr.constants.platform.windows.LastError.ERROR_PATH_NOT_FOUND:Ljnr/constants/platform/windows/LastError;
        //     69: invokevirtual  #257 // jnr.constants.platform.windows.LastError.intValue:()I
        //     72: if_icmpeq  86 (offset +14)
        //     75: iload  5
        //     77: getstatic  #144 // jnr.constants.platform.windows.LastError.ERROR_BAD_NETPATH:Ljnr/constants/platform/windows/LastError;
        //     80: invokevirtual  #257 // jnr.constants.platform.windows.LastError.intValue:()I
        //     83: if_icmpne  88 (offset +5)
        //     86: iconst_m1
        //     87: ireturn
        //     88: aload_0
        //     89: aload_1
        //     90: aload_2
        //     91: invokevirtual  #292 // jnr.posix.WindowsPOSIX.findFirstFile:(Ljava/lang/String;Ljnr/posix/FileStat;)I
        //     94: ireturn
        //     95: iconst_0
        //     96: ireturn
    }

  public int findFirstFile(String arg0, FileStat arg1) {
        byte[] var3 = WString.path(arg0, true);
        WindowsFindData var4 = new WindowsFindData(getRuntime());
        HANDLE var5 = wlibc().FindFirstFileW(var3, var4);
        if (var5.isValid()) {
            wlibc().FindClose(var5);
            (((WindowsRawFileStat) arg1)).setup(arg0, var4);
            return 0;
        } else {
            return -1;
        }
    }

  public String readlink(String arg0) {
        handler.unimplementedError("readlink");
        return null;
    }

  public Pointer environ() {
        return getRuntime().getMemoryManager().newPointer((((Long) wlibc()._environ().get())).longValue());
    }

  public int setenv(String arg0, String arg1, int arg2) {
        if (!arg0.contains("=")) {
            if (wlibc().SetEnvironmentVariableW(new WString(arg0), new WString(arg1))) {
                return 0;
            } else {
                handler.error(Errno.EINVAL, "setenv", arg0);
                return -1;
            }
        } else {
            handler.error(Errno.EINVAL, "setenv", arg0);
            return -1;
        }
    }

  public int umask(int arg0) {
        return wlibc()._umask(arg0);
    }

  public int unsetenv(String arg0) {
        if (wlibc().SetEnvironmentVariableW(new WString(arg0), null)) {
            return 0;
        } else {
            handler.error(Errno.EINVAL, "unsetenv", arg0);
            return -1;
        }
    }

  public int utimes(String arg0, long[] arg1, long[] arg2) {
        FileTime var4 = timevalToFileTime(arg1);
        FileTime var5 = timevalToFileTime(arg2);
        return setFileTime(arg0, var4, var5);
    }

  public int utimensat(int arg0, String arg1, long[] arg2, long[] arg3, int arg4) {
        FileTime var6 = timespecToFileTime(arg2);
        FileTime var7 = timespecToFileTime(arg3);
        return setFileTime(arg1, var6, var7);
    }

  private FileTime timevalToFileTime(long[] arg0) {
        if (arg0 != null) {
            long var2 = arg0[0] * 10000000L + arg0[1] * 10L;
            return unixTimeToFileTime(var2);
        } else {
            return currentFileTime();
        }
    }

  private FileTime timespecToFileTime(long[] arg0) {
        if (arg0 != null) {
            long var2 = arg0[0] * 10000000L + arg0[1] / 100L;
            return unixTimeToFileTime(var2);
        } else {
            return currentFileTime();
        }
    }

  private int setFileTime(String arg0, FileTime arg1, FileTime arg2) {
        byte[] var4 = WindowsHelpers.toWPath(arg0);
        HANDLE var5 = wlibc().CreateFileW(var4, 1073741824, 3, null, 3, 33554432, 0);
        if (var5.isValid()) {
            boolean var6 = wlibc().SetFileTime(var5, null, arg1, arg2);
            wlibc().CloseHandle(var5);
            return !var6 ? -1 : 0;
        } else {
            return -1;
        }
    }

  private FileTime unixTimeToFileTime(long arg0) {
        long var3 = 116444736000000000L + arg0;
        FileTime var5 = new FileTime(getRuntime());
        var5.dwLowDateTime.set(var3 & 4294967295L);
        var5.dwHighDateTime.set(var3 >> 32 & 4294967295L);
        return var5;
    }

  private FileTime nullFileTime() {
        FileTime var1 = new FileTime(getRuntime());
        var1.dwLowDateTime.set(0L);
        var1.dwHighDateTime.set(0L);
        return var1;
    }

  private FileTime currentFileTime() {
        return unixTimeToFileTime(System.currentTimeMillis() * 10000L);
    }

  public int wait(int[] arg0) {
        handler.unimplementedError("wait");
        return -1;
    }

  public int waitpid(int arg0, int[] arg1, int arg2) {
        if (arg0 <= 0) {
            handler.unimplementedError("waitpid");
        }
        HANDLE var4 = wlibc().OpenProcess(1024, 0, arg0);
        if (var4 != null) {
            if ((arg2 & WaitFlags.WNOHANG.intValue()) != 0) {
                wlibc().WaitForSingleObject(var4, -1);
            }
            IntByReference var5 = new IntByReference();
            wlibc().GetExitCodeProcess(var4, var5);
            wlibc().CloseHandle(var4);
            int var6 = (((Integer) var5.getValue())).intValue();
            if (var6 != 259) {
                arg1[0] = var6;
                return arg0;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }

  public int waitpid(long arg0, int[] arg1, int arg2) {
        if (arg0 <= 2147483647L) {
            return waitpid(((int) arg0), arg1, arg2);
        } else {
            throw new IllegalArgumentException("waitpid");
        }
    }

  public String getlogin() {
        return helper.getlogin();
    }

  public int endgrent() {
        return 0;
    }

  public int endpwent() {
        return helper.endpwent();
    }

  public Group getgrent() {
        return null;
    }

  public Passwd getpwent() {
        return null;
    }

  public Group getgrgid(int arg0) {
        return null;
    }

  public Passwd getpwnam(String arg0) {
        return null;
    }

  public Group getgrnam(String arg0) {
        return null;
    }

  public int setgrent() {
        return 0;
    }

  public int setpwent() {
        return helper.setpwent();
    }

  public Passwd getpwuid(int arg0) {
        return null;
    }

  public boolean isatty(FileDescriptor arg0) {
        HANDLE var2 = JavaLibCHelper.gethandle(arg0);
        int var3 = wlibc().GetFileType(var2);
        return var3 == 2;
    }

  public int isatty(int arg0) {
        HANDLE var2 = JavaLibCHelper.gethandle(((long) arg0));
        int var3 = wlibc().GetFileType(var2);
        return var3 == 2;
    }

  public int mkdir(String arg0, int arg1) {
        WString var3 = WString.path(arg0);
        int var4 = -1;
        if (wlibc()._wmkdir(var3) == 0) {
            var4 = wlibc()._wchmod(var3, arg1);
        }
        if (var4 < 0) {
            int var5 = errno();
            handler.error(Errno.valueOf(((long) var5)), "mkdir", arg0);
        }
        return var4;
    }

  public int rmdir(String arg0) {
        int __stk1;
        WString var2 = WString.path(arg0);
        int var3 = wlibc().GetFileAttributesW(var2);
        __stk1 = var3 == -1 ? 0 : (var3 & 1) != 0;
        int var4 = __stk1;
        if (var4 != 0) {
            wlibc().SetFileAttributesW(var2, var3 & -2);
        }
        if (wlibc().RemoveDirectoryW(var2)) {
            return 0;
        } else {
            int var5 = errno();
            if (var4 != 0) {
                wlibc().SetFileAttributesW(var2, var3 & 1);
            }
            handler.error(mapErrorToErrno(var5), "rmdir", arg0);
            return -1;
        }
    }

  public int link(String arg0, String arg1) {
        boolean var3 = wlibc().CreateHardLinkW(WString.path(arg1), WString.path(arg0), null);
        if (var3) {
            return 0;
        } else {
            int var4 = errno();
            handler.error(mapErrorToErrno(var4), "link", new StringBuilder().append(arg0).append(" or ").append(arg1).toString());
            return var4;
        }
    }

  public int aspawn(boolean arg0, String arg1, String[] arg2, String arg3, String[] arg4) {
        int __stk1;
        int __stk2;
        try {
            if (arg2.length == 0) {
                __stk1 = -1;
            }
            try {
                String[] var6 = WindowsHelpers.processCommandArgs(this, arg1, arg2, arg3);
                __stk2 = childResult(createProcess("aspawn", ((String) var6[0]), ((String) var6[1]), null, null, null, null, arg4), arg0);
            } catch (Exception e1) {
                Throwable var6 = e1;
                return -1;
            }
        } catch (Exception e2) {
            Throwable var6 = e1;
            return -1;
        }
    }

  public int pipe(int[] arg0) {
        return (((WindowsLibC) libc()))._pipe(arg0, 512, 0);
    }

  public int truncate(CharSequence arg0, long arg1) {
        int var4 = libc().open(arg0, OpenFlags.O_WRONLY.intValue(), 0);
        if (var4 != -1) {
            if (libc().ftruncate(var4, arg1) != -1) {
                if (libc().close(var4) != -1) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

  public int fcntlInt(int arg0, Fcntl arg1, int arg2) {
        switch (arg1) {
            case F_GETFD:
                if (checkFd(arg0) != -1) {
                    return 0;
                } else {
                    return -1;
                }
            case F_SETFD:
                if (checkFd(arg0) != -1) {
                    return 0;
                } else {
                    return -1;
                }
            case F_GETFL:
                if (checkFd(arg0) != -1) {
                    return OpenFlags.O_RDWR.intValue();
                } else {
                    return -1;
                }
            default:
                handler.unimplementedError("fcntl");
                return -1;
        }
    }

  private WindowsLibC wlibc() {
        return ((WindowsLibC) libc());
    }

  public int spawn(boolean arg0, String arg1, String arg2, String arg3, String[] arg4) {
        if (arg1 != null) {
            String[] var6 = WindowsHelpers.processCommandLine(this, arg1, arg2, arg3);
            return childResult(createProcess("spawn", ((String) var6[0]), ((String) var6[1]), null, null, null, null, arg4), arg0);
        } else {
            return -1;
        }
    }

  private int childResult(WindowsChildRecord arg0, boolean arg1) {
        if (arg0 != null) {
            if (arg1) {
                IntByReference var3 = new IntByReference();
                WindowsLibC var4 = ((WindowsLibC) libc());
                HANDLE var5 = arg0.getProcess();
                var4.WaitForSingleObject(var5, -1);
                var4.GetExitCodeProcess(var5, var3);
                var4.CloseHandle(var5);
                System.exit((((Integer) var3.getValue())).intValue());
            }
            return arg0.getPid();
        } else {
            return -1;
        }
    }

  private static Errno mapErrorToErrno(int arg0) {
        Errno var1 = ((Errno) errorToErrnoMapper.get(Integer.valueOf(arg0)));
        if (var1 == null) {
            var1 = Errno.__UNKNOWN_CONSTANT__;
        }
        return var1;
    }

  private WindowsChildRecord createProcess(String arg0, String arg1, String arg2, WindowsSecurityAttributes arg3, HANDLE arg4, HANDLE arg5, HANDLE arg6, String[] arg7) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_2
        //      1: ifnonnull  25 (offset +24)
        //      4: aload_3
        //      5: ifnonnull  25 (offset +20)
        //      8: aload_0
        //      9: getfield  #230 // jnr.posix.WindowsPOSIX.handler:Ljnr/posix/POSIXHandler;
        //     12: getstatic  #114 // jnr.constants.platform.Errno.EFAULT:Ljnr/constants/platform/Errno;
        //     15: aload_1
        //     16: ldc  #29 // 'no command or program specified'
        //     18: invokeinterface  #338 // jnr.posix.POSIXHandler.error:(Ljnr/constants/platform/Errno;Ljava/lang/String;Ljava/lang/String;)V, count 4
        //     23: aconst_null
        //     24: areturn
        //     25: aload  4
        //     27: ifnonnull  43 (offset +16)
        //     30: new  #85 // jnr.posix.WindowsSecurityAttributes
        //     33: dup
        //     34: aload_0
        //     35: invokevirtual  #294 // jnr.posix.WindowsPOSIX.getRuntime:()Ljnr/ffi/Runtime;
        //     38: invokespecial  #314 // jnr.posix.WindowsSecurityAttributes.<init>:(Ljnr/ffi/Runtime;)V
        //     41: astore  4
        //     43: new  #86 // jnr.posix.WindowsStartupInfo
        //     46: dup
        //     47: aload_0
        //     48: invokevirtual  #294 // jnr.posix.WindowsPOSIX.getRuntime:()Ljnr/ffi/Runtime;
        //     51: invokespecial  #316 // jnr.posix.WindowsStartupInfo.<init>:(Ljnr/ffi/Runtime;)V
        //     54: astore  9
        //     56: aload  9
        //     58: sipush  256
        //     61: invokevirtual  #317 // jnr.posix.WindowsStartupInfo.setFlags:(I)V
        //     64: aload  9
        //     66: aload  5
        //     68: ifnull  76 (offset +8)
        //     71: aload  5
        //     73: goto  87 (offset +14)
        //     76: aload_0
        //     77: invokespecial  #305 // jnr.posix.WindowsPOSIX.wlibc:()Ljnr/posix/WindowsLibC;
        //     80: bipush  -10
        //     82: invokeinterface  #353 // jnr.posix.WindowsLibC.GetStdHandle:(I)Ljnr/posix/HANDLE;, count 2
        //     87: invokevirtual  #319 // jnr.posix.WindowsStartupInfo.setStandardInput:(Ljnr/posix/HANDLE;)V
        //     90: aload  9
        //     92: aload  6
        //     94: ifnull  102 (offset +8)
        //     97: aload  6
        //     99: goto  113 (offset +14)
        //    102: aload_0
        //    103: invokespecial  #305 // jnr.posix.WindowsPOSIX.wlibc:()Ljnr/posix/WindowsLibC;
        //    106: bipush  -11
        //    108: invokeinterface  #353 // jnr.posix.WindowsLibC.GetStdHandle:(I)Ljnr/posix/HANDLE;, count 2
        //    113: invokevirtual  #320 // jnr.posix.WindowsStartupInfo.setStandardOutput:(Ljnr/posix/HANDLE;)V
        //    116: aload  9
        //    118: aload  7
        //    120: ifnull  128 (offset +8)
        //    123: aload  5
        //    125: goto  139 (offset +14)
        //    128: aload_0
        //    129: invokespecial  #305 // jnr.posix.WindowsPOSIX.wlibc:()Ljnr/posix/WindowsLibC;
        //    132: bipush  -12
        //    134: invokeinterface  #353 // jnr.posix.WindowsLibC.GetStdHandle:(I)Ljnr/posix/HANDLE;, count 2
        //    139: invokevirtual  #318 // jnr.posix.WindowsStartupInfo.setStandardError:(Ljnr/posix/HANDLE;)V
        //    142: sipush  1056
        //    145: istore  10
        //    147: new  #83 // jnr.posix.WindowsProcessInformation
        //    150: dup
        //    151: aload_0
        //    152: invokevirtual  #294 // jnr.posix.WindowsPOSIX.getRuntime:()Ljnr/ffi/Runtime;
        //    155: invokespecial  #307 // jnr.posix.WindowsProcessInformation.<init>:(Ljnr/ffi/Runtime;)V
        //    158: astore  11
        //    160: aconst_null
        //    161: astore  12
        //    163: aload_3
        //    164: invokestatic  #326 // jnr.posix.util.WindowsHelpers.toWString:(Ljava/lang/String;)[B
        //    167: astore  13
        //    169: new  #49 // java.lang.StringBuilder
        //    172: dup
        //    173: invokespecial  #239 // java.lang.StringBuilder.<init>:()V
        //    176: aload_0
        //    177: getfield  #230 // jnr.posix.WindowsPOSIX.handler:Ljnr/posix/POSIXHandler;
        //    180: invokeinterface  #339 // jnr.posix.POSIXHandler.getCurrentWorkingDirectory:()Ljava/io/File;, count 1
        //    185: invokevirtual  #233 // java.io.File.toString:()Ljava/lang/String;
        //    188: invokestatic  #322 // jnr.posix.util.WindowsHelpers.escapePath:(Ljava/lang/String;)Ljava/lang/String;
        //    191: invokevirtual  #241 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    194: ldc  #16 // '\\'
        //    196: invokevirtual  #241 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    199: invokevirtual  #242 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    202: invokestatic  #326 // jnr.posix.util.WindowsHelpers.toWString:(Ljava/lang/String;)[B
        //    205: astore  14
        //    207: aload_2
        //    208: invokestatic  #326 // jnr.posix.util.WindowsHelpers.toWString:(Ljava/lang/String;)[B
        //    211: invokestatic  #248 // java.nio.ByteBuffer.wrap:([B)Ljava/nio/ByteBuffer;
        //    214: astore  15
        //    216: aload_0
        //    217: invokespecial  #305 // jnr.posix.WindowsPOSIX.wlibc:()Ljnr/posix/WindowsLibC;
        //    220: aload  13
        //    222: aload  15
        //    224: aload  4
        //    226: aload  4
        //    228: aload  4
        //    230: invokevirtual  #315 // jnr.posix.WindowsSecurityAttributes.getInheritHandle:()Z
        //    233: ifeq  240 (offset +7)
        //    236: iconst_1
        //    237: goto  241 (offset +4)
        //    240: iconst_0
        //    241: iload  10
        //    243: aload  12
        //    245: aload  14
        //    247: aload  9
        //    249: aload  11
        //    251: invokeinterface  #344 // jnr.posix.WindowsLibC.CreateProcessW:([BLjava/nio/ByteBuffer;Ljnr/posix/WindowsSecurityAttributes;Ljnr/posix/WindowsSecurityAttributes;IILjnr/ffi/Pointer;[BLjnr/posix/WindowsStartupInfo;Ljnr/posix/WindowsProcessInformation;)Z, count 11
        //    256: istore  16
        //    258: iload  16
        //    260: ifne  265 (offset +5)
        //    263: aconst_null
        //    264: areturn
        //    265: aload_0
        //    266: invokespecial  #305 // jnr.posix.WindowsPOSIX.wlibc:()Ljnr/posix/WindowsLibC;
        //    269: aload  11
        //    271: invokevirtual  #310 // jnr.posix.WindowsProcessInformation.getThread:()Ljnr/posix/HANDLE;
        //    274: invokeinterface  #341 // jnr.posix.WindowsLibC.CloseHandle:(Ljnr/posix/HANDLE;)Z, count 2
        //    279: pop
        //    280: new  #77 // jnr.posix.WindowsChildRecord
        //    283: dup
        //    284: aload  11
        //    286: invokevirtual  #309 // jnr.posix.WindowsProcessInformation.getProcess:()Ljnr/posix/HANDLE;
        //    289: aload  11
        //    291: invokevirtual  #308 // jnr.posix.WindowsProcessInformation.getPid:()I
        //    294: invokespecial  #282 // jnr.posix.WindowsChildRecord.<init>:(Ljnr/posix/HANDLE;I)V
        //    297: areturn
    }

  private int checkFd(int arg0) {
        return libc().fstat(arg0, checkFdStat);
    }

  public int mkfifo(String arg0, int arg1) {
        handler.unimplementedError("mkfifo");
        return -1;
    }

  public Timeval allocateTimeval() {
        return new DefaultNativeTimeval(getRuntime());
    }

  public int gettimeofday(Timeval arg0) {
        long var2 = System.currentTimeMillis();
        arg0.sec(var2 / 1000L);
        arg0.usec(var2 % 1000L * 1000L);
        return 0;
    }

}