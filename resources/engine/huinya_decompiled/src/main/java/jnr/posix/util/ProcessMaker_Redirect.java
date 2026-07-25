// исходный (обфусцированный) внутренний класс: jnr.posix.util.ProcessMaker.Redirect
package jnr.posix.util;

import java.io.File;
import jnr.posix.util.ProcessMaker_Redirect_Type;

public class ProcessMaker_Redirect {

    // ---- поля ----
  public static final ProcessMaker_Redirect INHERIT;
  public static final ProcessMaker_Redirect PIPE;
  private final ProcessMaker_Redirect_Type type;
  private final File file;

    static {
        INHERIT = new ProcessMaker_Redirect(ProcessMaker_Redirect_Type.INHERIT);
        PIPE = new ProcessMaker_Redirect(ProcessMaker_Redirect_Type.PIPE);
    }

  private ProcessMaker_Redirect(ProcessMaker_Redirect_Type arg0) { // было: <init>
        this(arg0, null);
    }

  private ProcessMaker_Redirect(ProcessMaker_Redirect_Type arg0, File arg1) { // было: <init>
        super();
        type = arg0;
        file = arg1;
    }

  public static ProcessMaker_Redirect appendTo(File arg0) {
        return new ProcessMaker_Redirect(ProcessMaker_Redirect_Type.APPEND, arg0);
    }

  public static ProcessMaker_Redirect from(File arg0) {
        return new ProcessMaker_Redirect(ProcessMaker_Redirect_Type.READ, arg0);
    }

  public static ProcessMaker_Redirect to(File arg0) {
        return new ProcessMaker_Redirect(ProcessMaker_Redirect_Type.WRITE, arg0);
    }

  public File file() {
        return file;
    }

  public ProcessMaker_Redirect_Type type() {
        return type;
    }

}