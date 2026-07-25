// исходный (обфусцированный) внутренний класс: jnr.posix.windows.WindowsFindData
package jnr.posix.windows;

import jnr.ffi.NativeType;
import jnr.ffi.Runtime;
import jnr.ffi.Struct_Padding;
import jnr.ffi.Struct_UnsignedLong;
import jnr.posix.windows.CommonFileInformation;
import jnr.posix.windows.CommonFileInformation_HackyFileTime;

public class WindowsFindData extends CommonFileInformation {

    // ---- поля ----
  public static final int MAX_PATH = 260;
  final Struct_UnsignedLong dwFileAttributes;
  final Struct_UnsignedLong chigh;
  final Struct_UnsignedLong clow;
  final Struct_UnsignedLong ahigh;
  final Struct_UnsignedLong alow;
  final Struct_UnsignedLong uhigh;
  final Struct_UnsignedLong ulow;
  final Struct_UnsignedLong nFileSizeHigh;
  final Struct_UnsignedLong nFileSizeLow;
  final Struct_UnsignedLong dwReserved0;
  final Struct_UnsignedLong dwReserved1;
  final Struct_Padding cFileName;
  final Struct_Padding cAlternateFileName;

  public WindowsFindData(Runtime arg0) { // было: <init>
        super(arg0);
        dwFileAttributes = new Struct_UnsignedLong(this);
        clow = new Struct_UnsignedLong(this);
        chigh = new Struct_UnsignedLong(this);
        alow = new Struct_UnsignedLong(this);
        ahigh = new Struct_UnsignedLong(this);
        ulow = new Struct_UnsignedLong(this);
        uhigh = new Struct_UnsignedLong(this);
        nFileSizeHigh = new Struct_UnsignedLong(this);
        nFileSizeLow = new Struct_UnsignedLong(this);
        dwReserved0 = new Struct_UnsignedLong(this);
        dwReserved1 = new Struct_UnsignedLong(this);
        cFileName = new Struct_Padding(this, NativeType.USHORT, 32767);
        cAlternateFileName = new Struct_Padding(this, NativeType.USHORT, 14);
    }

  public CommonFileInformation_HackyFileTime getCreationTime() {
        return new CommonFileInformation_HackyFileTime(this, chigh, clow);
    }

  public CommonFileInformation_HackyFileTime getLastAccessTime() {
        return new CommonFileInformation_HackyFileTime(this, ahigh, alow);
    }

  public CommonFileInformation_HackyFileTime getLastWriteTime() {
        return new CommonFileInformation_HackyFileTime(this, uhigh, ulow);
    }

  public int getFileAttributes() {
        return dwFileAttributes.intValue();
    }

  public long getFileSizeHigh() {
        return nFileSizeHigh.longValue();
    }

  public long getFileSizeLow() {
        return nFileSizeLow.longValue();
    }

}