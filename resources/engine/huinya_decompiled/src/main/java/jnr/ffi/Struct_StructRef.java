// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.StructRef
package jnr.ffi;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_Offset;
import jnr.ffi.Struct_PointerField;

public class Struct_StructRef extends Struct_PointerField {

    // ---- поля ----
  private final Constructor structConstructor;
  private final Class structType;
  private final int size;
  final Struct this$0;

  public Struct_StructRef(Struct arg0, Class arg1) { // было: <init>
        super(arg0);
        this$0 = arg0;
        structType = arg1;
        try {
            structConstructor = arg1.getDeclaredConstructor(new Class[]{Runtime.class});
            Object[] __obj2 = new Object[1];
            __obj2[0] = arg0.getRuntime();
            size = Struct.size(((Struct) structConstructor.newInstance(__obj2)));
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

  public Struct_StructRef(Struct arg0, Class arg1, int arg2) { // было: <init>
        this(arg0, arg1);
        set(Memory.allocateDirect(arg0.getRuntime(), size * arg2));
    }

  public Struct_StructRef(Struct arg0, Struct_Offset arg1, Class arg2) { // было: <init>
        super(arg0, arg1);
        this$0 = arg0;
        structType = arg2;
        try {
            structConstructor = arg2.getDeclaredConstructor(new Class[]{Runtime.class});
            Object[] __obj2 = new Object[1];
            __obj2[0] = arg0.getRuntime();
            size = Struct.size(((Struct) structConstructor.newInstance(__obj2)));
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

  public Struct_StructRef(Struct arg0, Struct_Offset arg1, Class arg2, int arg3) { // было: <init>
        this(arg0, arg1, arg2);
        set(Memory.allocateDirect(arg0.getRuntime(), size * arg3));
    }

  public final void set(Struct arg0) {
        Pointer var2 = Struct.getMemory(arg0);
        set(var2);
    }

  public final void set(Struct[] arg0) {
        Pointer var2;
        int var4;
        if (arg0.length != 0) {
            var2 = Memory.allocateDirect(this$0.getRuntime(), size * arg0.length);
            byte[] var3 = new byte[size];
            var4 = 0;
        } else {
            set(Memory.allocateDirect(this$0.getRuntime(), 0));
            return;
        }
        while (var4 < arg0.length) {
            Struct.getMemory(((Struct) arg0[var4])).get(0L, var3, 0, size);
            var2.put(((long) (size * var4)), var3, 0, size);
            ++var4;
            continue;
        }
        set(var2);
    }

  public final Struct get() {
        try {
            Struct var1 = ((Struct) structConstructor.newInstance(new Object[]{this$0.getRuntime()}));
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

  public final Struct[] get(int arg0) {
        Struct[] __stk2;
        try {
            Struct[] var2 = ((Struct[]) Array.newInstance(structType, arg0));
            int var3 = 0;
            while (var3 < arg0) {
                var2[var3] = ((Struct) structConstructor.newInstance(new Object[]{this$0.getRuntime()}));
                var2[var3].useMemory(getPointer().slice(((long) (Struct.size(((Struct) var2[var3])) * var3))));
                ++var3;
                continue;
            }
            __stk2 = var2;
        } catch (Exception e1) {
            Throwable var2 = e1;
            throw new RuntimeException(var2);
        }
    }

  public String toString() {
        return new StringBuilder().append("struct @ ").append(super.toString()).append('\n').append(get()).toString();
    }

}