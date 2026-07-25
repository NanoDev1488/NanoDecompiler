// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout
package jnr.ffi;

import java.lang.reflect.Constructor;
import java.nio.charset.Charset;
import jnr.ffi.NativeType;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout_Field;
import jnr.ffi.StructLayout_Function;
import jnr.ffi.StructLayout_Offset;
import jnr.ffi.Type;

public class StructLayout extends Type {

    // ---- поля ----
  static final Charset ASCII;
  static final Charset UTF8;
  private final Runtime runtime;
  private final boolean isUnion = false;
  private boolean resetIndex;
   StructLayout enclosing;
   int offset;
   int size;
   int alignment;
   int paddedSize;

    static {
        ASCII = Charset.forName("ASCII");
        UTF8 = Charset.forName("UTF-8");
    }

  protected StructLayout(Runtime arg0) { // было: <init>
        super();
        isUnion = false;
        resetIndex = false;
        enclosing = null;
        offset = 0;
        size = 0;
        alignment = 1;
        paddedSize = 0;
        runtime = arg0;
    }

  protected StructLayout(Runtime arg0, int arg1) { // было: <init>
        super();
        isUnion = false;
        resetIndex = false;
        enclosing = null;
        offset = 0;
        size = 0;
        alignment = 1;
        paddedSize = 0;
        runtime = arg0;
        paddedSize = arg1;
        size = arg1;
    }

  public final Runtime getRuntime() {
        return runtime;
    }

  public final int size() {
        return paddedSize;
    }

  public final int alignment() {
        return alignment;
    }

  public final int offset() {
        return offset;
    }

  public NativeType getNativeType() {
        return NativeType.STRUCT;
    }

  public String toString() {
        StringBuilder var1 = new StringBuilder();
        Field[] var2 = getClass().getDeclaredFields();
        var1.append(getClass().getSimpleName()).append(" { \n");
        String var3 = "    ";
        Field[] var4 = var2;
        int var5 = var4.length;
        int var6 = 0;
        while (true) {
            if (var6 >= var5) {
                var1.append("}\n");
                return var1.toString();
            } else {
                Object var7 = var4[var6];
            }
            try {
                var1.append("    ").append('\n');
            } catch (Throwable e1) {
            }
        }
        Throwable var8 = __caught__;
        throw new RuntimeException(var8);
    }

  private static int align(int arg0, int arg1) {
        return arg0 + arg1 - 1 & (arg1 - 1 ^ -1);
    }

  protected final int addField(int arg0, int arg1) {
        int __stk1;
        __stk1 = !resetIndex ? align(size, arg1) : 0;
        int var3 = __stk1;
        size = Math.max(size, var3 + arg0);
        alignment = Math.max(alignment, arg1);
        paddedSize = align(size, alignment);
        return ((Integer) var3);
    }

  protected final int addField(int arg0, int arg1, StructLayout_Offset arg2) {
        size = Math.max(size, arg2.intValue() + arg0);
        alignment = Math.max(alignment, arg1);
        paddedSize = align(size, alignment);
        return arg2.intValue();
    }

  protected final int addField(Type arg0) {
        return addField(arg0.size(), arg0.alignment());
    }

  protected final int addField(Type arg0, StructLayout_Offset arg1) {
        return addField(arg0.size(), arg0.alignment(), arg1);
    }

  protected final StructLayout_Offset at(int arg0) {
        return new StructLayout_Offset(arg0);
    }

  protected final void arrayBegin() {
        resetIndex = false;
    }

  protected final void arrayEnd() {
        resetIndex = false;
    }

  protected StructLayout_Field[] array(StructLayout_Field[] arg0) {
        arrayBegin();
        try {
            Class var2 = arg0.getClass().getComponentType();
            Constructor var3 = var2.getDeclaredConstructor(new Class[]{var2.getEnclosingClass()});
            Object[] __obj2 = new Object[1];
            __obj2[0] = this;
            Object[] var4 = __obj2;
            int var5 = 0;
            while (var5 < arg0.length) {
                arg0[var5] = ((StructLayout_Field) var3.newInstance(var4));
                ++var5;
                continue;
            }
        } catch (Exception e1) {
            Throwable var2 = e1;
            throw new RuntimeException(var2);
        }
    }

  protected final StructLayout inner(StructLayout arg0) {
        arg0.enclosing = this;
        arg0.offset = align(size, arg0.alignment);
        size = arg0.offset + arg0.size;
        paddedSize = align(size, alignment());
        return arg0;
    }

  protected final StructLayout_Function function(Class arg0) {
        return new StructLayout_Function(this, arg0);
    }

  protected final StructLayout_Function function(Class arg0, StructLayout_Offset arg1) {
        return new StructLayout_Function(this, arg0, arg1);
    }

}