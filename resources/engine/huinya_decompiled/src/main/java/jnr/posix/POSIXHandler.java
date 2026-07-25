// исходный (обфусцированный) внутренний класс: jnr.posix.POSIXHandler
package jnr.posix;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import jnr.constants.platform.Errno;
import jnr.posix.POSIXHandler_WARNING_ID;

public interface POSIXHandler {

  public abstract void error(Errno arg0, String arg1);

  public abstract void error(Errno arg0, String arg1, String arg2);

  public abstract void unimplementedError(String arg0);

  public abstract void warn(POSIXHandler_WARNING_ID arg0, String arg1, Object[] arg2);

  public abstract boolean isVerbose();

  public abstract File getCurrentWorkingDirectory();

  public abstract String[] getEnv();

  public abstract InputStream getInputStream();

  public abstract PrintStream getOutputStream();

  public abstract int getPID();

  public abstract PrintStream getErrorStream();

}