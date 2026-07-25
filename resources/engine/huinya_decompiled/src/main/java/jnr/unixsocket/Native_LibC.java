// исходный (обфусцированный) внутренний класс: jnr.unixsocket.Native.LibC
package jnr.unixsocket;

import java.nio.ByteBuffer;
import jnr.constants.platform.Fcntl;
import jnr.constants.platform.OpenFlags;
import jnr.ffi.annotations.In;
import jnr.ffi.annotations.Out;
import jnr.ffi.annotations.Transient;
import jnr.ffi.byref.IntByReference;
import jnr.ffi.types.size_t;
import jnr.ffi.types.ssize_t;
import jnr.posix.Timeval;
import jnr.unixsocket.SockAddrUnix;

public interface Native_LibC {

    // ---- поля ----
  public static final int F_GETFL = Fcntl.F_GETFL.intValue();
  public static final int F_SETFL = Fcntl.F_SETFL.intValue();
  public static final int O_NONBLOCK = OpenFlags.O_NONBLOCK.intValue();

  public abstract int socket(int arg0, int arg1, int arg2);

  public abstract int listen(int arg0, int arg1);

  public abstract int bind(int arg0, @In @Out @Transient SockAddrUnix arg1, int arg2);

  public abstract int accept(int arg0, @Out SockAddrUnix arg1, @In @Out IntByReference arg2);

  public abstract int connect(int arg0, @In @Transient SockAddrUnix arg1, int arg2);

  public abstract int getsockname(int arg0, @Out SockAddrUnix arg1, @In @Out IntByReference arg2);

  public abstract int getpeername(int arg0, @Out SockAddrUnix arg1, @In @Out IntByReference arg2);

  public abstract int socketpair(int arg0, int arg1, int arg2, @Out int[] arg3);

  public abstract int fcntl(int arg0, int arg1, int arg2);

  public abstract int getsockopt(int arg0, int arg1, int arg2, @Out ByteBuffer arg3, @In @Out IntByReference arg4);

  public abstract int getsockopt(int arg0, int arg1, int arg2, @Out Timeval arg3, @In @Out IntByReference arg4);

  public abstract int setsockopt(int arg0, int arg1, int arg2, @In ByteBuffer arg3, int arg4);

  public abstract int setsockopt(int arg0, int arg1, int arg2, @In Timeval arg3, int arg4);

  public abstract String strerror(int arg0);

    @ssize_t
  public abstract int sendto(int arg0, @In ByteBuffer arg1, @size_t long arg2, int arg3, @In @Transient SockAddrUnix arg4, int arg5);

    @ssize_t
  public abstract int recvfrom(int arg0, @Out ByteBuffer arg1, @size_t long arg2, int arg3, @Out SockAddrUnix arg4, @In @Out IntByReference arg5);

}