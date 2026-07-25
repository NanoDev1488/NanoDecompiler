// исходный (обфусцированный) внутренний класс: jnr.posix.util.ExecIt
package jnr.posix.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import jnr.posix.POSIXHandler;
import jnr.posix.util.ExecIt_StreamPumper;

public class ExecIt {

    // ---- поля ----
  protected final POSIXHandler handler;

  public ExecIt(POSIXHandler arg0) { // было: <init>
        super();
        handler = arg0;
    }

  public int runAndWait(String[] arg0) {
        return runAndWait(handler.getOutputStream(), arg0);
    }

  public int runAndWait(OutputStream arg0, String[] arg1) {
        return runAndWait(arg0, handler.getErrorStream(), arg1);
    }

  public int runAndWait(OutputStream arg0, OutputStream arg1, String[] arg2) {
        Process var4 = run(arg2);
        handleStreams(var4, handler.getInputStream(), arg0, arg1);
        return var4.waitFor();
    }

  public Process run(String[] arg0) {
        File var2 = handler.getCurrentWorkingDirectory();
        return Runtime.getRuntime().exec(arg0, handler.getEnv(), var2);
    }

  private void handleStreams(Process arg0, InputStream arg1, OutputStream arg2, OutputStream arg3) {
        InputStream var5 = arg0.getInputStream();
        InputStream var6 = arg0.getErrorStream();
        OutputStream var7 = arg0.getOutputStream();
        ExecIt_StreamPumper var8 = new ExecIt_StreamPumper(var5, arg2, false);
        ExecIt_StreamPumper var9 = new ExecIt_StreamPumper(var6, arg3, false);
        ExecIt_StreamPumper var10 = new ExecIt_StreamPumper(arg1, var7, true);
        var8.start();
        var9.start();
        var10.start();
        try {
            var8.join();
        } catch (InterruptedException var11) {
        }
        try {
            var9.join();
        } catch (InterruptedException e2) {
            Throwable var11 = e2;
        }
        var10.quit();
        try {
            arg3.flush();
        } catch (IOException e3) {
            Throwable var11 = e3;
        }
        try {
            arg2.flush();
        } catch (IOException e4) {
            Throwable var11 = e4;
        }
        try {
            var7.close();
        } catch (IOException e5) {
            Throwable var11 = e5;
        }
        try {
            var5.close();
        } catch (IOException e6) {
            Throwable var11 = e6;
        }
        try {
            var6.close();
        } catch (IOException e7) {
            Throwable var11 = e7;
        }
    }

}