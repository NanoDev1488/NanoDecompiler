// исходный (обфусцированный) внутренний класс: jnr.posix.SpawnFileAction.Close
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.posix.POSIX;
import jnr.posix.SpawnFileAction;
import jnr.posix.UnixLibC;

final class SpawnFileAction_Close extends SpawnFileAction {

    // ---- поля ----
  final int fd;

  public SpawnFileAction_Close(int arg0) { // было: <init>
        super();
        fd = arg0;
    }

  final boolean act(POSIX arg0, Pointer arg1) {
        return (((UnixLibC) arg0.libc())).posix_spawn_file_actions_addclose(arg1, fd) == 0;
    }

  public String toString() {
        return new StringBuilder().append("SpawnFileAction::Close(fd = ").append(fd).append(")").toString();
    }

}