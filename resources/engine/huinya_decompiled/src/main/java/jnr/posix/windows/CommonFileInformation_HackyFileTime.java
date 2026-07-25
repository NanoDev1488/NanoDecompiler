// исходный (обфусцированный) внутренний класс: jnr.posix.windows.CommonFileInformation.HackyFileTime
package jnr.posix.windows;

import jnr.ffi.Struct_UnsignedLong;
import jnr.posix.windows.CommonFileInformation;

public class CommonFileInformation_HackyFileTime {

    // ---- поля ----
  private final Struct_UnsignedLong dwHighDateTime;
  private final Struct_UnsignedLong dwLowDateTime;
  final CommonFileInformation this$0;

  public CommonFileInformation_HackyFileTime(CommonFileInformation arg0, Struct_UnsignedLong arg1, Struct_UnsignedLong arg2) { // было: <init>
        super();
        this$0 = arg0;
        dwHighDateTime = arg1;
        dwLowDateTime = arg2;
    }

  public long getLowDateTime() {
        return dwLowDateTime.longValue();
    }

  public long getHighDateTime() {
        return dwHighDateTime.longValue();
    }

  public long getLongValue() {
        return (getHighDateTime() & 4294967295L) << 32 | getLowDateTime() & 4294967295L;
    }

}