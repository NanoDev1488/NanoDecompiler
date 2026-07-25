// исходный (обфусцированный) внутренний класс: jnr.posix.JavaLibCHelper.PosixExec
package jnr.posix;

import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicReference;
import jnr.constants.platform.Errno;
import jnr.posix.JavaLibCHelper;
import jnr.posix.JavaLibCHelper_ErrnoParsingOutputStream;
import jnr.posix.POSIXHandler;
import jnr.posix.util.ExecIt;

class JavaLibCHelper_PosixExec extends ExecIt {

    // ---- поля ----
  private final AtomicReference errno;
  private final JavaLibCHelper_ErrnoParsingOutputStream errorStream;

  public JavaLibCHelper_PosixExec(POSIXHandler arg0) { // было: <init>
        super(arg0);
        errno = new AtomicReference(Errno.EINVAL);
        errorStream = new JavaLibCHelper_ErrnoParsingOutputStream(errno, null);
    }

  private int parseResult(int arg0) {
        if (arg0 != 0) {
            JavaLibCHelper.errno(((Errno) errno.get()));
            return -1;
        } else {
            return arg0;
        }
    }

  public int runAndWait(String[] arg0) {
        return runAndWait(handler.getOutputStream(), errorStream, arg0);
    }

  public int runAndWait(OutputStream arg0, String[] arg1) {
        return runAndWait(arg0, errorStream, arg1);
    }

  public int runAndWait(OutputStream arg0, OutputStream arg1, String[] arg2) {
        return parseResult(super.runAndWait(arg0, arg1, arg2));
    }

}