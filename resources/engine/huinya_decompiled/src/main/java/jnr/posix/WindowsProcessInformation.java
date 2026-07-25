// исходный (обфусцированный) внутренний класс: jnr.posix.WindowsProcessInformation
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_Pointer;
import jnr.ffi.Struct_Unsigned32;
import jnr.posix.HANDLE;

public class WindowsProcessInformation extends Struct {

    // ---- поля ----
  final Struct_Pointer hProcess;
  final Struct_Pointer hThread;
  final Struct_Unsigned32 dwProcessId;
  final Struct_Unsigned32 dwThreadId;

  public WindowsProcessInformation(Runtime arg0) { // было: <init>
        super(arg0);
        hProcess = new Struct_Pointer(this);
        hThread = new Struct_Pointer(this);
        dwProcessId = new Struct_Unsigned32(this);
        dwThreadId = new Struct_Unsigned32(this);
    }

  public HANDLE getThread() {
        return new HANDLE(hThread.get());
    }

  public HANDLE getProcess() {
        return new HANDLE(hProcess.get());
    }

  public int getPid() {
        return dwProcessId.intValue();
    }

}