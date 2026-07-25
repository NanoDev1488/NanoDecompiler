// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Info
package jnr.ffi;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_Alignment;
import jnr.ffi.Struct_Offset;
import jnr.ffi.provider.MemoryManager;
import jnr.ffi.provider.ParameterFlags;

final class Struct_Info {

    // ---- поля ----
  private final Runtime runtime;
  private Pointer memory;
   Struct enclosing;
   int offset;
   int size;
   int minAlign;
   boolean isUnion;
   boolean resetIndex;
   Struct_Alignment alignment;

  public Struct_Info(Runtime arg0) { // было: <init>
        super();
        memory = null;
        enclosing = null;
        offset = 0;
        size = 0;
        minAlign = 1;
        isUnion = false;
        resetIndex = false;
        alignment = new Struct_Alignment(0);
        runtime = arg0;
    }

  public final int getOffset() {
        return enclosing != null ? offset + enclosing.__info.getOffset() : 0;
    }

  public final Pointer getMemory(int arg0) {
        Pointer __stk1;
        if (enclosing == null) {
            if (memory == null) {
                memory = allocateMemory(arg0);
                __stk1 = allocateMemory(arg0);
            } else {
                __stk1 = memory;
            }
        } else {
            __stk1 = enclosing.__info.getMemory(arg0);
        }
        return __stk1;
    }

  public final Pointer getMemory() {
        return getMemory(16);
    }

  final boolean isDirect() {
        return enclosing == null ? memory == null ? 0 : memory.isDirect() : enclosing.__info.isDirect() ? 1 : memory == null ? 0 : memory.isDirect();
    }

  final int size() {
        return alignment.intValue() <= 0 ? size : size + (-size & minAlign - 1);
    }

  final int getMinimumAlignment() {
        return minAlign;
    }

  private Pointer allocateMemory(int arg0) {
        if (!ParameterFlags.isDirect(arg0)) {
            return runtime.getMemoryManager().allocate(size());
        } else {
            return runtime.getMemoryManager().allocateDirect(size(), true);
        }
    }

  public final void useMemory(Pointer arg0) {
        memory = arg0;
    }

  protected final int addField(int arg0, int arg1, Struct_Offset arg2) {
        size = Math.max(size, arg2.intValue() + (arg0 >> 3));
        minAlign = Math.max(minAlign, arg1 >> 3);
        return arg2.intValue();
    }

  protected final int addField(int arg0, int arg1) {
        int __stk1;
        int __stk2;
        __stk1 = alignment.intValue() <= 0 ? arg1 >> 3 : Math.min(alignment.intValue(), arg1 >> 3);
        int var3 = __stk1;
        __stk2 = !resetIndex ? Struct.access$000(size, ((Integer) var3)) : 0;
        int var4 = __stk2;
        size = Math.max(size, var4 + (arg0 >> 3));
        minAlign = Math.max(minAlign, ((Integer) var3));
        return ((Integer) var4);
    }

  static Runtime access$100(Struct_Info arg0) {
        return arg0.runtime;
    }

}