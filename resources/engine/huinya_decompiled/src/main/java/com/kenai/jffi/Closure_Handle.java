// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Closure.Handle
package com.kenai.jffi;

public interface Closure_Handle {

  public abstract long getAddress();

  public abstract void setAutoRelease(boolean arg0);

  public abstract void dispose();

    @Deprecated
  public abstract void free();

}