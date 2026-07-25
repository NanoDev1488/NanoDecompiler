// исходный (обфусцированный) внутренний класс: jnr.unixsocket.Ucred
package jnr.unixsocket;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_gid_t;
import jnr.ffi.Struct_pid_t;
import jnr.ffi.Struct_uid_t;

final class Ucred extends Struct {

    // ---- поля ----
  final Struct_pid_t pid;
  final Struct_uid_t uid;
  final Struct_gid_t gid;

  public Ucred() { // было: <init>
        super(Runtime.getSystemRuntime());
        pid = new Struct_pid_t(this);
        uid = new Struct_uid_t(this);
        gid = new Struct_gid_t(this);
    }

   Struct_pid_t getPidField() {
        return pid;
    }

   Struct_uid_t getUidField() {
        return uid;
    }

   Struct_gid_t getGidField() {
        return gid;
    }

}