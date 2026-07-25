// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.NativeException
package jnr.enxio.channels;

import java.io.IOException;
import jnr.constants.platform.Errno;

public class NativeException extends IOException {

    // ---- поля ----
  private final Errno errno;

  public NativeException(String arg0, Errno arg1) { // было: <init>
        super(arg0);
        errno = arg1;
    }

  public Errno getErrno() {
        return errno;
    }

}