// исходный (обфусцированный) внутренний класс: jnr.posix.SpawnFileAction
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.posix.POSIX;
import jnr.posix.SpawnFileAction_Close;
import jnr.posix.SpawnFileAction_Dup;
import jnr.posix.SpawnFileAction_Open;

public abstract class SpawnFileAction {

  public SpawnFileAction() { // было: <init>
        super();
    }

  abstract boolean act(POSIX arg0, Pointer arg1);

  public static SpawnFileAction dup(int arg0, int arg1) {
        return new SpawnFileAction_Dup(arg0, arg1);
    }

  public static SpawnFileAction open(String arg0, int arg1, int arg2, int arg3) {
        return new SpawnFileAction_Open(arg0, arg1, arg2, arg3);
    }

  public static SpawnFileAction close(int arg0) {
        return new SpawnFileAction_Close(arg0);
    }

}