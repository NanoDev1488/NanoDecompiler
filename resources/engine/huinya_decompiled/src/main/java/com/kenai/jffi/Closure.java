// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Closure
package com.kenai.jffi;

import com.kenai.jffi.Closure_Buffer;

public interface Closure {

  public abstract void invoke(Closure_Buffer arg0);

}