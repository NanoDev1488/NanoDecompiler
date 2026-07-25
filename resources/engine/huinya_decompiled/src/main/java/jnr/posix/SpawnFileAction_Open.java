// исходный (обфусцированный) внутренний класс: jnr.posix.SpawnFileAction.Open
package jnr.posix;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import jnr.ffi.Pointer;
import jnr.posix.POSIX;
import jnr.posix.SpawnFileAction;
import jnr.posix.UnixLibC;

final class SpawnFileAction_Open extends SpawnFileAction {

    // ---- поля ----
  final String path;
  final int fd;
  final int flags;
  final int mode;
  final ByteBuffer nativePath;

  public SpawnFileAction_Open(String arg0, int arg1, int arg2, int arg3) { // было: <init>
        super();
        path = arg0;
        fd = arg1;
        flags = arg2;
        mode = arg3;
        nativePath = defensiveCopy(arg0);
    }

  private ByteBuffer defensiveCopy(String arg0) {
        CharsetEncoder var2 = Charset.defaultCharset().newEncoder();
        int var3 = ((int) var2.maxBytesPerChar());
        int var4 = (arg0.length() + 1) * var3;
        ByteBuffer var5 = ByteBuffer.allocateDirect(var4);
        var2.encode(CharBuffer.wrap(arg0), var5, true);
        var5.flip();
        var5.limit(var5.limit() + var3);
        return var5;
    }

  final boolean act(POSIX arg0, Pointer arg1) {
        return (((UnixLibC) arg0.libc())).posix_spawn_file_actions_addopen(arg1, fd, nativePath, flags, mode) == 0;
    }

  public String toString() {
        return new StringBuilder().append("SpawnFileAction::Open(path = '").append(path).append("', fd = ").append(fd).append(", flags = ").append(Integer.toHexString(flags)).append(", mode = ").append(Integer.toHexString(mode)).append(")").toString();
    }

}