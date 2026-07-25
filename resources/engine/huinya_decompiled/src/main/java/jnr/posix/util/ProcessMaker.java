// исходный (обфусцированный) внутренний класс: jnr.posix.util.ProcessMaker
package jnr.posix.util;

import java.io.File;
import java.util.List;
import java.util.Map;
import jnr.posix.util.ProcessMaker_Redirect;

public interface ProcessMaker {

  public abstract List command();

  public abstract ProcessMaker command(List arg0);

  public abstract ProcessMaker command(String[] arg0);

  public abstract File directory();

  public abstract ProcessMaker directory(File arg0);

  public abstract Map environment();

  public abstract ProcessMaker environment(String[] arg0);

  public abstract ProcessMaker inheritIO();

  public abstract ProcessMaker_Redirect redirectError();

  public abstract ProcessMaker redirectError(File arg0);

  public abstract ProcessMaker redirectError(ProcessMaker_Redirect arg0);

  public abstract boolean redirectErrorStream();

  public abstract ProcessMaker redirectErrorStream(boolean arg0);

  public abstract ProcessMaker_Redirect redirectInput();

  public abstract ProcessMaker redirectInput(File arg0);

  public abstract ProcessMaker redirectInput(ProcessMaker_Redirect arg0);

  public abstract ProcessMaker_Redirect redirectOutput();

  public abstract ProcessMaker redirectOutput(File arg0);

  public abstract ProcessMaker redirectOutput(ProcessMaker_Redirect arg0);

  public abstract Process start();

}