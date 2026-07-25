// исходный (обфусцированный) внутренний класс: jnr.posix.util.DefaultPOSIXHandler
package jnr.posix.util;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jnr.constants.platform.Errno;
import jnr.posix.POSIXHandler;
import jnr.posix.POSIXHandler_WARNING_ID;

public class DefaultPOSIXHandler implements POSIXHandler {

  public DefaultPOSIXHandler() { // было: <init>
        super();
    }

  public void error(Errno arg0, String arg1) {
        throw new RuntimeException(new StringBuilder().append("native error ").append(arg0.description()).append(" ").append(arg1).toString());
    }

  public void error(Errno arg0, String arg1, String arg2) {
        throw new RuntimeException(new StringBuilder().append("native error calling ").append(arg1).append(": ").append(arg0.description()).append(" ").append(arg2).toString());
    }

  public void unimplementedError(String arg0) {
        throw new IllegalStateException(new StringBuilder().append(arg0).append(" is not implemented in jnr-posix").toString());
    }

  public void warn(POSIXHandler_WARNING_ID arg0, String arg1, Object[] arg2) {
        String var4;
        try {
            var4 = String.format(arg1, arg2);
        } catch (IllegalFormatException var5) {
            var4 = new StringBuilder().append(arg1).append(" ").append(Arrays.toString(arg2)).toString();
        }
        Logger.getLogger("jnr-posix").log(Level.WARNING, var4);
    }

  public boolean isVerbose() {
        return false;
    }

  public File getCurrentWorkingDirectory() {
        return new File(".");
    }

  public String[] getEnv() {
        String[] var1 = new String[System.getenv().size()];
        int var2 = 0;
        Iterator var3 = System.getenv().entrySet().iterator();
        while (var3.hasNext()) {
            Entry var4 = ((Entry) var3.next());
            var1[var2++] = new StringBuilder(((String) var4.getKey())).append("=").append(((String) var4.getValue())).toString();
            continue;
        }
        return var1;
    }

  public InputStream getInputStream() {
        return System.in;
    }

  public PrintStream getOutputStream() {
        return System.out;
    }

  public int getPID() {
        return 0;
    }

  public PrintStream getErrorStream() {
        return System.err;
    }

}