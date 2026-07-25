// исходный (обфусцированный) внутренний класс: jnr.posix.WindowsStartupInfo
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_Pointer;
import jnr.ffi.Struct_Unsigned16;
import jnr.ffi.Struct_Unsigned32;
import jnr.posix.HANDLE;

public class WindowsStartupInfo extends Struct {

    // ---- поля ----
  public final Struct_Unsigned32 cb;
  public final Struct_Pointer lpReserved;
  public final Struct_Pointer lpDesktop;
  public final Struct_Pointer lpTitle;
  public final Struct_Unsigned32 dwX;
  public final Struct_Unsigned32 dwY;
  public final Struct_Unsigned32 dwXSize;
  public final Struct_Unsigned32 dwYSize;
  public final Struct_Unsigned32 dwXCountChars;
  public final Struct_Unsigned32 dwYCountChars;
  public final Struct_Unsigned32 dwFillAttribute;
  public final Struct_Unsigned32 dwFlags;
  public final Struct_Unsigned16 wShowWindow;
  public final Struct_Unsigned16 cbReserved2;
  public final Struct_Pointer lpReserved2;
  public final Struct_Pointer standardInput;
  public final Struct_Pointer standardOutput;
  public final Struct_Pointer standardError;

  public WindowsStartupInfo(Runtime arg0) { // было: <init>
        super(arg0);
        cb = new Struct_Unsigned32(this);
        lpReserved = new Struct_Pointer(this);
        lpDesktop = new Struct_Pointer(this);
        lpTitle = new Struct_Pointer(this);
        dwX = new Struct_Unsigned32(this);
        dwY = new Struct_Unsigned32(this);
        dwXSize = new Struct_Unsigned32(this);
        dwYSize = new Struct_Unsigned32(this);
        dwXCountChars = new Struct_Unsigned32(this);
        dwYCountChars = new Struct_Unsigned32(this);
        dwFillAttribute = new Struct_Unsigned32(this);
        dwFlags = new Struct_Unsigned32(this);
        wShowWindow = new Struct_Unsigned16(this);
        cbReserved2 = new Struct_Unsigned16(this);
        lpReserved2 = new Struct_Pointer(this);
        standardInput = new Struct_Pointer(this);
        standardOutput = new Struct_Pointer(this);
        standardError = new Struct_Pointer(this);
    }

  public void setFlags(int arg0) {
        dwFlags.set(((long) arg0));
    }

  public void setStandardInput(HANDLE arg0) {
        standardInput.set(arg0.toPointer());
    }

  public void setStandardOutput(HANDLE arg0) {
        standardOutput.set(arg0.toPointer());
    }

  public void setStandardError(HANDLE arg0) {
        standardError.set(arg0.toPointer());
    }

}