// исходный (обфусцированный) внутренний класс: jnr.posix.WindowsChildRecord
package jnr.posix;

import jnr.posix.HANDLE;

public class WindowsChildRecord {

    // ---- поля ----
  private final HANDLE process;
  private final int pid;

  public WindowsChildRecord(HANDLE arg0, int arg1) { // было: <init>
        super();
        process = arg0;
        pid = arg1;
    }

  public HANDLE getProcess() {
        return process;
    }

  public int getPid() {
        return pid;
    }

}