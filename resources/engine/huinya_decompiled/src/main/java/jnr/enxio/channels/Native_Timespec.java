// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.Native.Timespec
package jnr.enxio.channels;

import jnr.enxio.channels.Native;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_SignedLong;

public final class Native_Timespec extends Struct {

    // ---- поля ----
  public final Struct_SignedLong tv_sec;
  public final Struct_SignedLong tv_nsec;

  public Native_Timespec() { // было: <init>
        super(Native.getRuntime());
        tv_sec = new Struct_SignedLong(this);
        tv_nsec = new Struct_SignedLong(this);
    }

  public Native_Timespec(Runtime arg0) { // было: <init>
        super(arg0);
        tv_sec = new Struct_SignedLong(this);
        tv_nsec = new Struct_SignedLong(this);
    }

  public Native_Timespec(long arg0, long arg1) { // было: <init>
        super(Native.getRuntime());
        tv_sec = new Struct_SignedLong(this);
        tv_nsec = new Struct_SignedLong(this);
        tv_sec.set(arg0);
        tv_nsec.set(arg1);
    }

}