// исходный (обфусцированный) внутренний класс: jnr.posix.AixFlock
package jnr.posix;

import jnr.ffi.Runtime;
import jnr.ffi.Struct_Signed16;
import jnr.ffi.Struct_Signed32;
import jnr.ffi.Struct_SignedLong;
import jnr.ffi.Struct_Unsigned32;
import jnr.posix.Flock;

public final class AixFlock extends Flock {

    // ---- поля ----
  public final Struct_Signed16 l_type;
  public final Struct_Signed16 l_whence;
  public final Struct_Unsigned32 l_sysid;
  public final Struct_Signed32 l_pid;
  public final Struct_Signed32 l_vfs;
  public final Struct_SignedLong l_start;
  public final Struct_SignedLong l_len;

  public AixFlock(Runtime arg0) { // было: <init>
        super(arg0);
        l_type = new Struct_Signed16(this);
        l_whence = new Struct_Signed16(this);
        l_sysid = new Struct_Unsigned32(this);
        l_pid = new Struct_Signed32(this);
        l_vfs = new Struct_Signed32(this);
        l_start = new Struct_SignedLong(this);
        l_len = new Struct_SignedLong(this);
    }

  public void type(short arg0) {
        l_type.set(arg0);
    }

  public void whence(short arg0) {
        l_whence.set(arg0);
    }

  public void start(long arg0) {
        l_start.set(arg0);
    }

  public void len(long arg0) {
        l_len.set(arg0);
    }

  public void pid(int arg0) {
        l_pid.set(arg0);
    }

  public short type() {
        return l_type.get();
    }

  public short whence() {
        return l_whence.get();
    }

  public long start() {
        return l_start.get();
    }

  public long len() {
        return l_len.get();
    }

  public int pid() {
        return l_pid.get();
    }

}