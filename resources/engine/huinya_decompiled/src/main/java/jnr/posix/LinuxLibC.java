// исходный (обфусцированный) внутренний класс: jnr.posix.LinuxLibC
package jnr.posix;

import java.nio.ByteBuffer;
import jnr.ffi.annotations.In;
import jnr.ffi.annotations.NulTerminate;
import jnr.ffi.annotations.Out;
import jnr.ffi.annotations.Transient;
import jnr.ffi.types.off_t;
import jnr.posix.FileStat;
import jnr.posix.UnixLibC;

public interface LinuxLibC extends UnixLibC {

  public abstract int __fxstat(int arg0, int arg1, @Out @Transient FileStat arg2);

  public abstract int __lxstat(int arg0, CharSequence arg1, @Out @Transient FileStat arg2);

  public abstract int __lxstat(int arg0, @NulTerminate @In ByteBuffer arg1, @Out @Transient FileStat arg2);

  public abstract int __xstat(int arg0, CharSequence arg1, @Out @Transient FileStat arg2);

  public abstract int __xstat(int arg0, @NulTerminate @In ByteBuffer arg1, @Out @Transient FileStat arg2);

  public abstract int __fxstat64(int arg0, int arg1, @Out @Transient FileStat arg2);

  public abstract int __lxstat64(int arg0, CharSequence arg1, @Out @Transient FileStat arg2);

  public abstract int __lxstat64(int arg0, @NulTerminate @In ByteBuffer arg1, @Out @Transient FileStat arg2);

  public abstract int __xstat64(int arg0, CharSequence arg1, @Out @Transient FileStat arg2);

  public abstract int __xstat64(int arg0, @NulTerminate @In ByteBuffer arg1, @Out @Transient FileStat arg2);

  public abstract int posix_fadvise(int arg0, @off_t long arg1, @off_t long arg2, int arg3);

}