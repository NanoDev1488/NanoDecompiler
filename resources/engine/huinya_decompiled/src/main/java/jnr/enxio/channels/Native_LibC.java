// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.Native.LibC
package jnr.enxio.channels;

import java.nio.ByteBuffer;
import jnr.constants.platform.Fcntl;
import jnr.constants.platform.OpenFlags;
import jnr.enxio.channels.Native_Timespec;
import jnr.ffi.Pointer;
import jnr.ffi.annotations.IgnoreError;
import jnr.ffi.annotations.In;
import jnr.ffi.annotations.Out;
import jnr.ffi.annotations.Transient;
import jnr.ffi.types.size_t;
import jnr.ffi.types.ssize_t;

public interface Native_LibC {

    // ---- поля ----
  public static final int F_GETFL = Fcntl.F_GETFL.intValue();
  public static final int F_SETFL = Fcntl.F_SETFL.intValue();
  public static final int O_NONBLOCK = OpenFlags.O_NONBLOCK.intValue();

  public abstract int close(int arg0);

    @ssize_t
  public abstract int read(int arg0, @Out ByteBuffer arg1, @size_t long arg2);

    @ssize_t
  public abstract int read(int arg0, @Out byte[] arg1, @size_t long arg2);

    @ssize_t
  public abstract int write(int arg0, @In ByteBuffer arg1, @size_t long arg2);

    @ssize_t
  public abstract int write(int arg0, @In byte[] arg1, @size_t long arg2);

  public abstract int fcntl(int arg0, int arg1, int arg2);

  public abstract int poll(@In @Out ByteBuffer arg0, int arg1, int arg2);

  public abstract int poll(@In @Out Pointer arg0, int arg1, int arg2);

  public abstract int kqueue();

  public abstract int kevent(int arg0, @In ByteBuffer arg1, int arg2, @Out ByteBuffer arg3, int arg4, @In @Transient Native_Timespec arg5);

  public abstract int kevent(int arg0, @In Pointer arg1, int arg2, @Out Pointer arg3, int arg4, @In @Transient Native_Timespec arg5);

  public abstract int pipe(@Out int[] arg0);

  public abstract int shutdown(int arg0, int arg1);

    @IgnoreError
  public abstract String strerror(int arg0);

}