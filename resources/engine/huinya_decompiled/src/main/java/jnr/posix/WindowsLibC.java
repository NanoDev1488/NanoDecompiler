// исходный (обфусцированный) внутренний класс: jnr.posix.WindowsLibC
package jnr.posix;

import java.nio.ByteBuffer;
import jnr.ffi.Pointer;
import jnr.ffi.Variable;
import jnr.ffi.annotations.In;
import jnr.ffi.annotations.Out;
import jnr.ffi.annotations.StdCall;
import jnr.ffi.annotations.Transient;
import jnr.ffi.byref.IntByReference;
import jnr.posix.FileStat;
import jnr.posix.FileTime;
import jnr.posix.HANDLE;
import jnr.posix.LibC;
import jnr.posix.WString;
import jnr.posix.WindowsProcessInformation;
import jnr.posix.WindowsSecurityAttributes;
import jnr.posix.WindowsStartupInfo;
import jnr.posix.windows.SystemTime;
import jnr.posix.windows.WindowsByHandleFileInformation;
import jnr.posix.windows.WindowsFileInformation;
import jnr.posix.windows.WindowsFindData;

public interface WindowsLibC extends LibC {

    // ---- поля ----
  public static final int STD_INPUT_HANDLE = -10;
  public static final int STD_OUTPUT_HANDLE = -11;
  public static final int STD_ERROR_HANDLE = -12;
  public static final int NORMAL_PRIORITY_CLASS = 32;
  public static final int CREATE_UNICODE_ENVIRONMENT = 1024;
  public static final int INFINITE = -1;
  public static final int FILE_TYPE_DISK = 1;
  public static final int FILE_TYPE_CHAR = 2;
  public static final int FILE_TYPE_PIPE = 3;
  public static final int FILE_TYPE_REMOTE = 32768;
  public static final int FILE_TYPE_UNKNOWN = 0;
  public static final int PROCESS_QUERY_INFORMATION = 1024;

  public abstract int _open_osfhandle(HANDLE arg0, int arg1);

  public abstract HANDLE _get_osfhandle(int arg0);

  public abstract int _close(int arg0);

  public abstract int _getpid();

  public abstract int _stat64(CharSequence arg0, @Out @Transient FileStat arg1);

  public abstract int _umask(int arg0);

  public abstract int _wmkdir(@In WString arg0);

  public abstract boolean RemoveDirectoryW(@In WString arg0);

  public abstract int _wchmod(@In WString arg0, int arg1);

  public abstract int _wchdir(@In WString arg0);

  public abstract int _wstat64(@In WString arg0, @Out @Transient FileStat arg1);

  public abstract int _wstat64(@In byte[] arg0, @Out @Transient FileStat arg1);

  public abstract int _pipe(int[] arg0, int arg1, int arg2);

    @StdCall
  public abstract boolean CreateProcessW(byte[] arg0, @In @Out ByteBuffer arg1, WindowsSecurityAttributes arg2, WindowsSecurityAttributes arg3, int arg4, int arg5, @In Pointer arg6, @In byte[] arg7, WindowsStartupInfo arg8, WindowsProcessInformation arg9);

  public abstract HANDLE OpenProcess(@In int arg0, @In int arg1, @In int arg2);

  public abstract int FileTimeToSystemTime(@In FileTime arg0, @Out @Transient SystemTime arg1);

  public abstract int GetFileAttributesW(@In WString arg0);

  public abstract int GetFileAttributesExW(@In WString arg0, @In int arg1, @Out @Transient WindowsFileInformation arg2);

  public abstract int GetFileAttributesExW(@In byte[] arg0, @In int arg1, @Out @Transient WindowsFileInformation arg2);

  public abstract int SetFileAttributesW(@In WString arg0, int arg1);

  public abstract int GetFileInformationByHandle(@In HANDLE arg0, @Out @Transient WindowsByHandleFileInformation arg1);

  public abstract int FindClose(HANDLE arg0);

  public abstract HANDLE FindFirstFileW(@In WString arg0, @Out WindowsFindData arg1);

  public abstract HANDLE FindFirstFileW(@In byte[] arg0, @Out WindowsFindData arg1);

    @StdCall
  public abstract boolean GetExitCodeProcess(HANDLE arg0, @Out Pointer arg1);

    @StdCall
  public abstract boolean GetExitCodeProcess(HANDLE arg0, @Out IntByReference arg1);

    @StdCall
  public abstract int GetFileType(HANDLE arg0);

    @StdCall
  public abstract int GetFileSize(HANDLE arg0, @Out IntByReference arg1);

    @StdCall
  public abstract HANDLE GetStdHandle(int arg0);

    @StdCall
  public abstract boolean CreateHardLinkW(@In WString arg0, @In WString arg1, @In WString arg2);

    @StdCall
  public abstract HANDLE CreateFileW(byte[] arg0, int arg1, int arg2, Pointer arg3, int arg4, int arg5, int arg6);

    @StdCall
  public abstract boolean SetEnvironmentVariableW(@In WString arg0, @In WString arg1);

    @StdCall
  public abstract boolean GetComputerNameW(@Out ByteBuffer arg0, IntByReference arg1);

    @StdCall
  public abstract boolean SetFileTime(HANDLE arg0, FileTime arg1, FileTime arg2, FileTime arg3);

    @StdCall
  public abstract boolean CloseHandle(HANDLE arg0);

    @StdCall
  public abstract int WaitForSingleObject(HANDLE arg0, int arg1);

  public abstract Variable _environ();

}