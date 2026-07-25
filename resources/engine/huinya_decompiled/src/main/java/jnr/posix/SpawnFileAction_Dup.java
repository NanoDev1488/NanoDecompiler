// исходный (обфусцированный) внутренний класс: jnr.posix.SpawnFileAction.Dup
package jnr.posix;

import jnr.ffi.Pointer;
import jnr.posix.POSIX;
import jnr.posix.SpawnFileAction;
import jnr.posix.UnixLibC;

final class SpawnFileAction_Dup extends SpawnFileAction {

    // ---- поля ----
  final int fd;
  final int newfd;

  public SpawnFileAction_Dup(int arg0, int arg1) { // было: <init>
        super();
        fd = arg0;
        newfd = arg1;
    }

  final boolean act(POSIX arg0, Pointer arg1) {
        return (((UnixLibC) arg0.libc())).posix_spawn_file_actions_adddup2(arg1, fd, newfd) == 0;
    }

  public String toString() {
        return new StringBuilder().append("SpawnFileAction::Dup(old = ").append(fd).append(", new = ").append(newfd).append(")").toString();
    }

}