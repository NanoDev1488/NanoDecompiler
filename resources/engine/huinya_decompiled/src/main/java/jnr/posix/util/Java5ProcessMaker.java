// исходный (обфусцированный) внутренний класс: jnr.posix.util.Java5ProcessMaker
package jnr.posix.util;

import java.io.File;
import java.util.List;
import java.util.Map;
import jnr.posix.POSIXHandler;
import jnr.posix.util.ProcessMaker;
import jnr.posix.util.ProcessMaker_Redirect;

public class Java5ProcessMaker implements ProcessMaker {

    // ---- поля ----
  private final ProcessBuilder builder;
  private final POSIXHandler handler;

  public Java5ProcessMaker(POSIXHandler arg0, String[] arg1) { // было: <init>
        super();
        handler = arg0;
        builder = new ProcessBuilder(arg1);
    }

  public Java5ProcessMaker(POSIXHandler arg0) { // было: <init>
        super();
        handler = arg0;
        builder = new ProcessBuilder(new String[0]);
    }

  public List command() {
        return builder.command();
    }

  public ProcessMaker command(List arg0) {
        builder.command(arg0);
        return this;
    }

  public ProcessMaker command(String[] arg0) {
        builder.command(arg0);
        return this;
    }

  public File directory() {
        return builder.directory();
    }

  public ProcessMaker directory(File arg0) {
        builder.directory(arg0);
        return this;
    }

  public Map environment() {
        return builder.environment();
    }

  public ProcessMaker environment(String[] arg0) {
        envIntoProcessBuilder(builder, arg0);
        return this;
    }

  public ProcessMaker inheritIO() {
        handler.unimplementedError("inheritIO");
        return this;
    }

  public ProcessMaker_Redirect redirectError() {
        return ProcessMaker_Redirect.PIPE;
    }

  public ProcessMaker redirectError(File arg0) {
        handler.unimplementedError("redirectError");
        return this;
    }

  public ProcessMaker redirectError(ProcessMaker_Redirect arg0) {
        handler.unimplementedError("redirectError");
        return this;
    }

  public boolean redirectErrorStream() {
        return false;
    }

  public ProcessMaker redirectErrorStream(boolean arg0) {
        handler.unimplementedError("redirectErrorStream");
        return this;
    }

  public ProcessMaker_Redirect redirectInput() {
        return ProcessMaker_Redirect.PIPE;
    }

  public ProcessMaker redirectInput(File arg0) {
        handler.unimplementedError("redirectInput");
        return this;
    }

  public ProcessMaker redirectInput(ProcessMaker_Redirect arg0) {
        handler.unimplementedError("redirectInput");
        return this;
    }

  public ProcessMaker_Redirect redirectOutput() {
        return ProcessMaker_Redirect.PIPE;
    }

  public ProcessMaker redirectOutput(File arg0) {
        handler.unimplementedError("redirectOutput");
        return this;
    }

  public ProcessMaker redirectOutput(ProcessMaker_Redirect arg0) {
        handler.unimplementedError("redirectOutput");
        return this;
    }

  public Process start() {
        return builder.start();
    }

  private static void envIntoProcessBuilder(ProcessBuilder arg0, String[] arg1) {
        int var3;
        int var4;
        if (arg1 != null) {
            arg0.environment().clear();
            String[] var2 = arg1;
            var3 = var2.length;
            var4 = 0;
        } else {
            return;
        }
        while (var4 < var3) {
            Object var5 = var2[var4];
            if (var5.indexOf(0) != -1) {
                var5 = var5.replaceFirst("��.*", "");
            }
            int var6 = var5.indexOf(61);
            if (var6 != -1) {
                arg0.environment().put(var5.substring(0, var6), var5.substring(var6 + 1));
            }
            ++var4;
            continue;
        }
    }

}