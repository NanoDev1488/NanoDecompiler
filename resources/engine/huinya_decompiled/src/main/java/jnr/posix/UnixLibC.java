// исходный (обфусцированный) внутренний класс: jnr.posix.UnixLibC
package jnr.posix;

import java.nio.ByteBuffer;
import jnr.ffi.Pointer;
import jnr.ffi.annotations.Direct;
import jnr.ffi.annotations.In;
import jnr.ffi.annotations.Out;
import jnr.ffi.byref.ByReference;
import jnr.ffi.byref.NumberByReference;
import jnr.ffi.byref.ShortByReference;
import jnr.ffi.types.pid_t;
import jnr.posix.LibC;

public interface UnixLibC extends LibC {

  public abstract int posix_spawn(@Out ByReference arg0, @In CharSequence arg1, @In Pointer arg2, @In Pointer arg3, @In CharSequence[] arg4, @In CharSequence[] arg5);

  public abstract int posix_spawnp(@Out ByReference arg0, @In CharSequence arg1, @In Pointer arg2, @In Pointer arg3, @In CharSequence[] arg4, @In CharSequence[] arg5);

  public abstract int posix_spawn_file_actions_init(Pointer arg0);

  public abstract int posix_spawn_file_actions_destroy(Pointer arg0);

  public abstract int posix_spawn_file_actions_addclose(Pointer arg0, int arg1);

    @Deprecated
  public abstract int posix_spawn_file_actions_addopen(Pointer arg0, int arg1, CharSequence arg2, int arg3, int arg4);

  public abstract int posix_spawn_file_actions_addopen(Pointer arg0, int arg1, @Direct ByteBuffer arg2, int arg3, int arg4);

  public abstract int posix_spawn_file_actions_adddup2(Pointer arg0, int arg1, int arg2);

  public abstract int posix_spawnattr_init(Pointer arg0);

  public abstract int posix_spawnattr_destroy(Pointer arg0);

  public abstract int posix_spawnattr_setflags(Pointer arg0, short arg1);

  public abstract int posix_spawnattr_getflags(Pointer arg0, ShortByReference arg1);

  public abstract int posix_spawnattr_setpgroup(Pointer arg0, @pid_t long arg1);

  public abstract int posix_spawnattr_getpgroup(Pointer arg0, NumberByReference arg1);

  public abstract int posix_spawnattr_setsigmask(Pointer arg0, Pointer arg1);

  public abstract int posix_spawnattr_getsigmask(Pointer arg0, Pointer arg1);

  public abstract int posix_spawnattr_setsigdefault(Pointer arg0, Pointer arg1);

  public abstract int posix_spawnattr_getsigdefault(Pointer arg0, Pointer arg1);

  public abstract int sigprocmask(int arg0, Pointer arg1, Pointer arg2);

  public abstract int mkfifo(CharSequence arg0, int arg1);

}