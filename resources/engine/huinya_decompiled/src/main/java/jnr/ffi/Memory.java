// исходный (обфусцированный) внутренний класс: jnr.ffi.Memory
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Type;
import jnr.ffi.TypeAlias;
import jnr.ffi.provider.MemoryManager;

public final class Memory {

  private Memory() { // было: <init>
        super();
    }

  public static Pointer allocate(Runtime arg0, int arg1) {
        return arg0.getMemoryManager().allocate(arg1);
    }

  public static Pointer allocate(Runtime arg0, NativeType arg1) {
        return arg0.getMemoryManager().allocate(arg0.findType(arg1).size());
    }

  public static Pointer allocate(Runtime arg0, Type arg1) {
        return arg0.getMemoryManager().allocate(arg1.size());
    }

  public static Pointer allocate(Runtime arg0, TypeAlias arg1) {
        return arg0.getMemoryManager().allocate(arg0.findType(arg1).size());
    }

  public static Pointer allocateDirect(Runtime arg0, int arg1) {
        return arg0.getMemoryManager().allocateDirect(arg1);
    }

  public static Pointer allocateDirect(Runtime arg0, long arg1) {
        return arg0.getMemoryManager().allocateDirect(arg1);
    }

  public static Pointer allocateDirect(Runtime arg0, NativeType arg1) {
        return arg0.getMemoryManager().allocateDirect(arg0.findType(arg1).size());
    }

  public static Pointer allocateDirect(Runtime arg0, TypeAlias arg1) {
        return arg0.getMemoryManager().allocateDirect(arg0.findType(arg1).size());
    }

  public static Pointer allocateDirect(Runtime arg0, int arg1, boolean arg2) {
        return arg0.getMemoryManager().allocateDirect(arg1, arg2);
    }

  public static Pointer allocateDirect(Runtime arg0, long arg1, boolean arg2) {
        return arg0.getMemoryManager().allocateDirect(arg1, arg2);
    }

  public static Pointer allocateTemporary(Runtime arg0, NativeType arg1) {
        return arg0.getMemoryManager().allocateTemporary(arg0.findType(arg1).size(), true);
    }

  public static Pointer allocateTemporary(Runtime arg0, TypeAlias arg1) {
        return arg0.getMemoryManager().allocateTemporary(arg0.findType(arg1).size(), true);
    }

  public static Pointer allocateTemporary(Runtime arg0, NativeType arg1, boolean arg2) {
        return arg0.getMemoryManager().allocateTemporary(arg0.findType(arg1).size(), arg2);
    }

}