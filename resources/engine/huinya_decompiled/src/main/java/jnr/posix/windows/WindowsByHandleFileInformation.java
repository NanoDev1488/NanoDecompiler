// исходный (обфусцированный) внутренний класс: jnr.posix.windows.WindowsByHandleFileInformation
package jnr.posix.windows;

import jnr.ffi.Runtime;
import jnr.ffi.Struct_Unsigned32;
import jnr.ffi.Struct_UnsignedLong;
import jnr.posix.windows.CommonFileInformation;
import jnr.posix.windows.CommonFileInformation_HackyFileTime;

public class WindowsByHandleFileInformation extends CommonFileInformation {

    // ---- поля ----
  final Struct_Unsigned32 dwFileAttributes;
  final Struct_UnsignedLong chigh;
  final Struct_UnsignedLong clow;
  final Struct_UnsignedLong ahigh;
  final Struct_UnsignedLong alow;
  final Struct_UnsignedLong uhigh;
  final Struct_UnsignedLong ulow;
  final Struct_Unsigned32 dwVolumeSerialNumber;
  final Struct_Unsigned32 nFileSizeHigh;
  final Struct_Unsigned32 nFileSizeLow;
  final Struct_Unsigned32 nNumberOfLinks;
  final Struct_Unsigned32 nFileIndexHigh;
  final Struct_Unsigned32 nFileIndexLow;

  public WindowsByHandleFileInformation(Runtime arg0) { // было: <init>
        super(arg0);
        dwFileAttributes = new Struct_Unsigned32(this);
        chigh = new Struct_UnsignedLong(this);
        clow = new Struct_UnsignedLong(this);
        ahigh = new Struct_UnsignedLong(this);
        alow = new Struct_UnsignedLong(this);
        uhigh = new Struct_UnsignedLong(this);
        ulow = new Struct_UnsignedLong(this);
        dwVolumeSerialNumber = new Struct_Unsigned32(this);
        nFileSizeHigh = new Struct_Unsigned32(this);
        nFileSizeLow = new Struct_Unsigned32(this);
        nNumberOfLinks = new Struct_Unsigned32(this);
        nFileIndexHigh = new Struct_Unsigned32(this);
        nFileIndexLow = new Struct_Unsigned32(this);
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
        return ((long) nFileSizeHigh.intValue());
    }

  public long getFileSizeLow() {
        return ((long) nFileSizeLow.intValue());
    }

}