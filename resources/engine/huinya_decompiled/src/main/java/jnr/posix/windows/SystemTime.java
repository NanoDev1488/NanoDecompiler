// исходный (обфусцированный) внутренний класс: jnr.posix.windows.SystemTime
package jnr.posix.windows;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_Unsigned16;

public class SystemTime extends Struct {

    // ---- поля ----
   Struct_Unsigned16 wYear;
   Struct_Unsigned16 wMonth;
   Struct_Unsigned16 wDayOfWeek;
   Struct_Unsigned16 wDay;
   Struct_Unsigned16 wHour;
   Struct_Unsigned16 wMinute;
   Struct_Unsigned16 wSecond;
   Struct_Unsigned16 wMilliseconds;

  public SystemTime(Runtime arg0) { // было: <init>
        super(arg0);
        wYear = new Struct_Unsigned16(this);
        wMonth = new Struct_Unsigned16(this);
        wDayOfWeek = new Struct_Unsigned16(this);
        wDay = new Struct_Unsigned16(this);
        wHour = new Struct_Unsigned16(this);
        wMinute = new Struct_Unsigned16(this);
        wSecond = new Struct_Unsigned16(this);
        wMilliseconds = new Struct_Unsigned16(this);
    }

  public String toString() {
        return new StringBuilder().append("").append(wYear).append("/").append(wMonth).append("/").append(wDay).append(" ").append(wHour).append(":").append(wMinute).append(":").append(wSecond).toString();
    }

}