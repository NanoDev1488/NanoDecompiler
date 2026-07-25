// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NativeMemoryManager
package jnr.ffi.provider.jffi;

import java.nio.ByteBuffer;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.BoundedMemoryIO;
import jnr.ffi.provider.IntPointer;
import jnr.ffi.provider.MemoryManager;
import jnr.ffi.provider.jffi.ArrayMemoryIO;
import jnr.ffi.provider.jffi.ByteBufferMemoryIO;
import jnr.ffi.provider.jffi.DirectMemoryIO;
import jnr.ffi.provider.jffi.NativeRuntime;
import jnr.ffi.provider.jffi.TransientNativeMemory;

public class NativeMemoryManager implements MemoryManager {

    // ---- поля ----
  private final Runtime runtime;
  private final long addressMask;

  public NativeMemoryManager(NativeRuntime arg0) { // было: <init>
        super();
        runtime = arg0;
        addressMask = arg0.addressMask();
    }

  public Pointer allocate(int arg0) {
        return new ArrayMemoryIO(runtime, arg0);
    }

  public Pointer allocateDirect(int arg0) {
        return new BoundedMemoryIO(TransientNativeMemory.allocate(runtime, arg0, 8, true), 0L, ((long) arg0));
    }

  public Pointer allocateDirect(long arg0) {
        return new BoundedMemoryIO(TransientNativeMemory.allocate(runtime, arg0, 8, true), 0L, arg0);
    }

  public Pointer allocateDirect(int arg0, boolean arg1) {
        return new BoundedMemoryIO(TransientNativeMemory.allocate(runtime, arg0, 8, arg1), 0L, ((long) arg0));
    }

  public Pointer allocateDirect(long arg0, boolean arg1) {
        return new BoundedMemoryIO(TransientNativeMemory.allocate(runtime, arg0, 8, arg1), 0L, arg0);
    }

  public Pointer allocateTemporary(int arg0) {
        return new BoundedMemoryIO(TransientNativeMemory.allocate(runtime, arg0, 8, true), 0L, ((long) arg0));
    }

  public Pointer allocateTemporary(int arg0, boolean arg1) {
        return new BoundedMemoryIO(TransientNativeMemory.allocate(runtime, arg0, 8, arg1), 0L, ((long) arg0));
    }

  public Pointer newPointer(ByteBuffer arg0) {
        return new ByteBufferMemoryIO(runtime, arg0);
    }

  public Pointer newPointer(long arg0) {
        return new DirectMemoryIO(runtime, arg0 & addressMask);
    }

  public Pointer newPointer(long arg0, long arg1) {
        return new BoundedMemoryIO(new DirectMemoryIO(runtime, arg0 & addressMask), 0L, arg1);
    }

  public Pointer newOpaquePointer(long arg0) {
        return new IntPointer(runtime, arg0);
    }

}