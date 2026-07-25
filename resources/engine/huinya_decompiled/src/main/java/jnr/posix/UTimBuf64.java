// исходный (обфусцированный) внутренний класс: jnr.posix.UTimBuf64
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_Signed64;

public final class UTimBuf64 extends Struct {

    // ---- поля ----
  public final Struct_Signed64 actime;
  public final Struct_Signed64 modtime;

  public UTimBuf64(Runtime arg0, long arg1, long arg2) { // было: <init>
        super(arg0);
        actime = new Struct_Signed64(this);
        modtime = new Struct_Signed64(this);
        actime.set(arg1);
        modtime.set(arg2);
    }

}