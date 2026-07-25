// исходный (обфусцированный) внутренний класс: jnr.ffi.Union
package jnr.ffi;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;

public abstract class Union extends Struct {

  protected Union(Runtime arg0) { // было: <init>
        super(arg0, true);
    }

}