// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.WinLibCAdapter
package jnr.enxio.channels;

import java.nio.ByteBuffer;
import jnr.enxio.channels.Native_LibC;
import jnr.enxio.channels.Native_Timespec;
import jnr.enxio.channels.WinLibCAdapter_LibMSVCRT;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.LoadedLibrary;

public final class WinLibCAdapter implements Native_LibC, LoadedLibrary {

    // ---- поля ----
  private WinLibCAdapter_LibMSVCRT win;

  public WinLibCAdapter(WinLibCAdapter_LibMSVCRT arg0) { // было: <init>
        super();
        win = arg0;
    }

  public int close(int arg0) {
        return win._close(arg0);
    }

  public int read(int arg0, ByteBuffer arg1, long arg2) {
        return win._read(arg0, arg1, arg2);
    }

  public int read(int arg0, byte[] arg1, long arg2) {
        return win._read(arg0, arg1, arg2);
    }

  public int write(int arg0, ByteBuffer arg1, long arg2) {
        return win._write(arg0, arg1, arg2);
    }

  public int write(int arg0, byte[] arg1, long arg2) {
        return win._write(arg0, arg1, arg2);
    }

  public int pipe(int[] arg0) {
        return win._pipe(arg0);
    }

  public String strerror(int arg0) {
        return win._strerror(arg0);
    }

  public Runtime getRuntime() {
        return Runtime.getRuntime(win);
    }

  public int fcntl(int arg0, int arg1, int arg2) {
        throw new UnsupportedOperationException("fcntl isn't supported on Windows");
    }

  public int poll(ByteBuffer arg0, int arg1, int arg2) {
        throw new UnsupportedOperationException("poll isn't supported on Windows");
    }

  public int poll(Pointer arg0, int arg1, int arg2) {
        throw new UnsupportedOperationException("poll isn't supported on Windows");
    }

  public int kqueue() {
        throw new UnsupportedOperationException("kqueue isn't supported on Windows");
    }

  public int kevent(int arg0, ByteBuffer arg1, int arg2, ByteBuffer arg3, int arg4, Native_Timespec arg5) {
        throw new UnsupportedOperationException("kevent isn't supported on Windows");
    }

  public int kevent(int arg0, Pointer arg1, int arg2, Pointer arg3, int arg4, Native_Timespec arg5) {
        throw new UnsupportedOperationException("kevent isn't supported on Windows");
    }

  public int shutdown(int arg0, int arg1) {
        throw new UnsupportedOperationException("shutdown isn't supported on Windows");
    }

}