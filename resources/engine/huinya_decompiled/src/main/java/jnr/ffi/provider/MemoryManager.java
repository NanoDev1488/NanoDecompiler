// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.MemoryManager
package jnr.ffi.provider;

import java.nio.ByteBuffer;
import jnr.ffi.Pointer;

public interface MemoryManager {

  public abstract Pointer allocate(int arg0);

  public abstract Pointer allocateDirect(int arg0);

  public abstract Pointer allocateDirect(long arg0);

  public abstract Pointer allocateDirect(int arg0, boolean arg1);

  public abstract Pointer allocateDirect(long arg0, boolean arg1);

  public abstract Pointer allocateTemporary(int arg0, boolean arg1);

  public abstract Pointer newPointer(ByteBuffer arg0);

  public abstract Pointer newPointer(long arg0);

  public abstract Pointer newPointer(long arg0, long arg1);

  public abstract Pointer newOpaquePointer(long arg0);

}