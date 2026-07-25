// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.WinLibCAdapter.LibMSVCRT
package jnr.enxio.channels;

import java.nio.ByteBuffer;
import jnr.ffi.annotations.IgnoreError;
import jnr.ffi.annotations.In;
import jnr.ffi.annotations.Out;
import jnr.ffi.types.size_t;
import jnr.ffi.types.ssize_t;

public interface WinLibCAdapter_LibMSVCRT {

  public abstract int _close(int arg0);

    @ssize_t
  public abstract int _read(int arg0, @Out ByteBuffer arg1, @size_t long arg2);

    @ssize_t
  public abstract int _read(int arg0, @Out byte[] arg1, @size_t long arg2);

    @ssize_t
  public abstract int _write(int arg0, @In ByteBuffer arg1, @size_t long arg2);

    @ssize_t
  public abstract int _write(int arg0, @In byte[] arg1, @size_t long arg2);

  public abstract int _pipe(@Out int[] arg0);

    @IgnoreError
  public abstract String _strerror(int arg0);

}