// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct
package jnr.ffi;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.nio.charset.Charset;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct_Address;
import jnr.ffi.Struct_Alignment;
import jnr.ffi.Struct_Double;
import jnr.ffi.Struct_Enum;
import jnr.ffi.Struct_Enum16;
import jnr.ffi.Struct_Enum32;
import jnr.ffi.Struct_Enum64;
import jnr.ffi.Struct_Enum8;
import jnr.ffi.Struct_Float;
import jnr.ffi.Struct_Function;
import jnr.ffi.Struct_Info;
import jnr.ffi.Struct_Member;
import jnr.ffi.Struct_Pointer;
import jnr.ffi.Struct_Signed16;
import jnr.ffi.Struct_Signed32;
import jnr.ffi.Struct_Signed64;
import jnr.ffi.Struct_Signed8;
import jnr.ffi.Struct_SignedLong;
import jnr.ffi.Struct_UTF8String;
import jnr.ffi.Struct_Unsigned16;
import jnr.ffi.Struct_Unsigned32;
import jnr.ffi.Struct_Unsigned64;
import jnr.ffi.Struct_Unsigned8;
import jnr.ffi.Struct_UnsignedLong;
import jnr.ffi.provider.MemoryManager;

public abstract class Struct {

    // ---- поля ----
  static final Charset ASCII;
  static final Charset UTF8;
  final Struct_Info __info;

    static {
        ASCII = Charset.forName("ASCII");
        UTF8 = Charset.forName("UTF-8");
    }

  protected Struct(Runtime arg0) { // было: <init>
        super();
        __info = new Struct_Info(arg0);
    }

  protected Struct(Runtime arg0, Struct_Alignment arg1) { // было: <init>
        this(arg0);
        __info.alignment = arg1;
    }

  protected Struct(Runtime arg0, Struct arg1) { // было: <init>
        this(arg0);
        __info.alignment = arg1.__info.alignment;
    }

  protected Struct(Runtime arg0, boolean arg1) { // было: <init>
        this(arg0);
        __info.resetIndex = arg1;
        __info.isUnion = arg1;
    }

  public final Runtime getRuntime() {
        return Struct_Info.access$100(__info);
    }

  public final void useMemory(Pointer arg0) {
        __info.useMemory(arg0);
    }

  public static Pointer getMemory(Struct arg0) {
        return arg0.__info.getMemory(0);
    }

  public static Pointer getMemory(Struct arg0, int arg1) {
        return arg0.__info.getMemory(arg1);
    }

  public static int size(Struct arg0) {
        return arg0.__info.size();
    }

  public static int size(Class arg0, Runtime arg1) {
        int __stk3;
        try {
            Constructor var2 = arg0.getDeclaredConstructor(new Class[]{Runtime.class});
            Object[] __obj2 = new Object[1];
            __obj2[0] = arg1;
            Struct var3 = ((Struct) var2.newInstance(__obj2));
            __stk3 = size(var3);
        } catch (NoSuchMethodException e1) {
            Throwable var2 = e1;
            throw new RuntimeException(new StringBuilder().append("Could not create an instance of ").append(arg0.getName()).append("\nBecause could not find the public constructor with a Runtime argument, it should look like:\npublic ").append(arg0.getSimpleName()).append("(Runtime runtime) {super(runtime);}").toString(), var2);
        } catch (Exception e2) {
            Throwable var2 = e2;
            throw new RuntimeException(var2);
        }
    }

  public static int size(Class arg0) {
        return size(arg0, Runtime.getSystemRuntime());
    }

  public static int alignment(Struct arg0) {
        return arg0.__info.getMinimumAlignment();
    }

  public static boolean isDirect(Struct arg0) {
        return arg0.__info.isDirect();
    }

  private static int align(int arg0, int arg1) {
        return arg0 + arg1 - 1 & (arg1 - 1 ^ -1);
    }

  public static Struct[] arrayOf(Runtime arg0, Class arg1, int arg2) {
        Struct[] __stk3;
        try {
            Struct[] var3 = ((Struct[]) Array.newInstance(arg1, arg2));
            Constructor var4 = arg1.getConstructor(new Class[]{Runtime.class});
            int var5 = 0;
            while (var5 < arg2) {
                var3[var5] = ((Struct) var4.newInstance(new Object[]{arg0}));
                ++var5;
                continue;
            }
            if (var3.length <= 0) {
                __stk3 = var3;
            }
            var5 = align(size(((Struct) var3[0])), alignment(((Struct) var3[0])));
            Pointer var6 = arg0.getMemoryManager().allocateDirect(var5 * arg2);
            int var7 = 0;
            while (var7 < var3.length) {
                var3[var7].useMemory(var6.slice(((long) (var5 * var7)), ((long) var5)));
                ++var7;
                continue;
            }
            __stk3 = var3;
        } catch (RuntimeException e1) {
            Throwable var3 = e1;
            throw var3;
        } catch (Exception e2) {
            Throwable var3 = e2;
            throw new RuntimeException(var3);
        }
    }

  public String toString() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #22 // java.lang.StringBuilder
        //      3: dup
        //      4: invokespecial  #141 // java.lang.StringBuilder.<init>:()V
        //      7: astore_1
        //      8: aload_0
        //      9: invokevirtual  #137 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     12: invokevirtual  #130 // java.lang.Class.getDeclaredFields:()[Ljava/lang/reflect/Field;
        //     15: astore_2
        //     16: aload_1
        //     17: aload_0
        //     18: invokevirtual  #137 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     21: invokevirtual  #133 // java.lang.Class.getSimpleName:()Ljava/lang/String;
        //     24: invokevirtual  #142 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     27: ldc  #5 // ' { \n'
        //     29: invokevirtual  #142 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     32: pop
        //     33: ldc  #3 // '    '
        //     35: astore_3
        //     36: aload_2
        //     37: astore  4
        //     39: aload  4
        //     41: arraylength
        //     42: istore  5
        //     44: iconst_0
        //     45: istore  6
        //     47: iload  6
        //     49: iload  5
        //     51: if_icmpge  149 (offset +98)
        //     54: aload  4
        //     56: iload  6
        //     58: aaload
        //     59: astore  7
        //     61: aload_1
        //     62: ldc  #3 // '    '
        //     64: invokevirtual  #142 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     67: pop
        //     68: aload_1
        //     69: aload  7
        //     71: invokevirtual  #147 // java.lang.reflect.Field.getName:()Ljava/lang/String;
        //     74: invokevirtual  #142 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     77: ldc  #4 // ' = '
        //     79: invokevirtual  #142 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     82: pop
        //     83: aload_1
        //     84: aload  7
        //     86: aload_0
        //     87: invokevirtual  #146 // java.lang.reflect.Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //     90: invokevirtual  #138 // java.lang.Object.toString:()Ljava/lang/String;
        //     93: invokevirtual  #142 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     96: pop
        //     97: goto  121 (offset +24)
        //    100: astore  8
        //    102: aload_1
        //    103: ldc  #8 // '- null -'
        //    105: invokevirtual  #142 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    108: pop
        //    109: goto  121 (offset +12)
        //    112: astore  8
        //    114: aload_1
        //    115: ldc  #7 // '- IllegalAccessException -'
        //    117: invokevirtual  #142 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    120: pop
        //    121: aload_1
        //    122: ldc  #1 // '\n'
        //    124: invokevirtual  #142 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    127: pop
        //    128: goto  143 (offset +15)
        //    131: astore  8
        //    133: new  #21 // java.lang.RuntimeException
        //    136: dup
        //    137: aload  8
        //    139: invokespecial  #140 // java.lang.RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //    142: athrow
        //    143: iinc  6, 1
        //    146: goto  47 (offset -99)
        //    149: aload_1
        //    150: ldc  #12 // '}\n'
        //    152: invokevirtual  #142 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    155: pop
        //    156: aload_1
        //    157: invokevirtual  #143 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    160: areturn
        //       Exception table:
        //         from 83 to 97 target 100 type java.lang.NullPointerException
        //         from 83 to 97 target 112 type java.lang.IllegalAccessException
        //         from 61 to 128 target 131 type java.lang.Throwable
    }

  protected final void arrayBegin() {
        __info.resetIndex = false;
    }

  protected final void arrayEnd() {
        __info.resetIndex = __info.isUnion;
    }

  protected Struct_Member[] array(Struct_Member[] arg0) {
        arrayBegin();
        try {
            Class var2 = arg0.getClass().getComponentType();
            Constructor var3 = var2.getDeclaredConstructor(new Class[]{var2.getEnclosingClass()});
            Object[] __obj2 = new Object[1];
            __obj2[0] = this;
            Object[] var4 = __obj2;
            int var5 = 0;
            while (var5 < arg0.length) {
                arg0[var5] = ((Struct_Member) var3.newInstance(var4));
                ++var5;
                continue;
            }
        } catch (Exception e1) {
            Throwable var2 = e1;
            throw new RuntimeException(var2);
        }
    }

  protected Struct_Enum8[] array(Struct_Enum8[] arg0, Class arg1) {
        arrayBegin();
        int var3 = 0;
        while (var3 < arg0.length) {
            arg0[var3] = new Struct_Enum8(this, arg1);
            ++var3;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected Struct_Enum16[] array(Struct_Enum16[] arg0, Class arg1) {
        arrayBegin();
        int var3 = 0;
        while (var3 < arg0.length) {
            arg0[var3] = new Struct_Enum16(this, arg1);
            ++var3;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected Struct_Enum32[] array(Struct_Enum32[] arg0, Class arg1) {
        arrayBegin();
        int var3 = 0;
        while (var3 < arg0.length) {
            arg0[var3] = new Struct_Enum32(this, arg1);
            ++var3;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected Struct_Enum64[] array(Struct_Enum64[] arg0, Class arg1) {
        arrayBegin();
        int var3 = 0;
        while (var3 < arg0.length) {
            arg0[var3] = new Struct_Enum64(this, arg1);
            ++var3;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected Struct_Enum[] array(Struct_Enum[] arg0, Class arg1) {
        arrayBegin();
        int var3 = 0;
        while (var3 < arg0.length) {
            arg0[var3] = new Struct_Enum(this, arg1);
            ++var3;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected Struct[] array(Struct[] arg0) {
        arrayBegin();
        try {
            Class var2 = arg0.getClass().getComponentType();
            Constructor var3 = var2.getConstructor(new Class[]{Runtime.class});
            int var4 = 0;
            while (var4 < arg0.length) {
                arg0[var4] = inner(((Struct) var3.newInstance(new Object[]{getRuntime()})));
                ++var4;
                continue;
            }
        } catch (Exception e1) {
            Throwable var2 = e1;
            throw new RuntimeException(var2);
        }
    }

  protected final Struct_Signed8[] array(Struct_Signed8[] arg0) {
        arrayBegin();
        int var2 = 0;
        while (var2 < arg0.length) {
            arg0[var2] = new Struct_Signed8(this);
            ++var2;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected final Struct_Unsigned8[] array(Struct_Unsigned8[] arg0) {
        arrayBegin();
        int var2 = 0;
        while (var2 < arg0.length) {
            arg0[var2] = new Struct_Unsigned8(this);
            ++var2;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected final Struct_Signed16[] array(Struct_Signed16[] arg0) {
        arrayBegin();
        int var2 = 0;
        while (var2 < arg0.length) {
            arg0[var2] = new Struct_Signed16(this);
            ++var2;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected final Struct_Unsigned16[] array(Struct_Unsigned16[] arg0) {
        arrayBegin();
        int var2 = 0;
        while (var2 < arg0.length) {
            arg0[var2] = new Struct_Unsigned16(this);
            ++var2;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected final Struct_Signed32[] array(Struct_Signed32[] arg0) {
        arrayBegin();
        int var2 = 0;
        while (var2 < arg0.length) {
            arg0[var2] = new Struct_Signed32(this);
            ++var2;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected final Struct_Unsigned32[] array(Struct_Unsigned32[] arg0) {
        arrayBegin();
        int var2 = 0;
        while (var2 < arg0.length) {
            arg0[var2] = new Struct_Unsigned32(this);
            ++var2;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected final Struct_Signed64[] array(Struct_Signed64[] arg0) {
        arrayBegin();
        int var2 = 0;
        while (var2 < arg0.length) {
            arg0[var2] = new Struct_Signed64(this);
            ++var2;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected final Struct_Unsigned64[] array(Struct_Unsigned64[] arg0) {
        arrayBegin();
        int var2 = 0;
        while (var2 < arg0.length) {
            arg0[var2] = new Struct_Unsigned64(this);
            ++var2;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected final Struct_SignedLong[] array(Struct_SignedLong[] arg0) {
        arrayBegin();
        int var2 = 0;
        while (var2 < arg0.length) {
            arg0[var2] = new Struct_SignedLong(this);
            ++var2;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected final Struct_UnsignedLong[] array(Struct_UnsignedLong[] arg0) {
        arrayBegin();
        int var2 = 0;
        while (var2 < arg0.length) {
            arg0[var2] = new Struct_UnsignedLong(this);
            ++var2;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected final Struct_Float[] array(Struct_Float[] arg0) {
        arrayBegin();
        int var2 = 0;
        while (var2 < arg0.length) {
            arg0[var2] = new Struct_Float(this);
            ++var2;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected final Struct_Double[] array(Struct_Double[] arg0) {
        arrayBegin();
        int var2 = 0;
        while (var2 < arg0.length) {
            arg0[var2] = new Struct_Double(this);
            ++var2;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected final Struct_Address[] array(Struct_Address[] arg0) {
        arrayBegin();
        int var2 = 0;
        while (var2 < arg0.length) {
            arg0[var2] = new Struct_Address(this);
            ++var2;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected final Struct_Pointer[] array(Struct_Pointer[] arg0) {
        arrayBegin();
        int var2 = 0;
        while (var2 < arg0.length) {
            arg0[var2] = new Struct_Pointer(this);
            ++var2;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected Struct_UTF8String[] array(Struct_UTF8String[] arg0, int arg1) {
        arrayBegin();
        int var3 = 0;
        while (var3 < arg0.length) {
            arg0[var3] = new Struct_UTF8String(this, arg1);
            ++var3;
            continue;
        }
        arrayEnd();
        return arg0;
    }

  protected final Struct inner(Struct arg0) {
        int __stk1;
        int __stk2;
        __stk1 = __info.alignment.intValue() <= 0 ? arg0.__info.getMinimumAlignment() : Math.min(__info.alignment.intValue(), arg0.__info.getMinimumAlignment());
        int var2 = __stk1;
        __stk2 = !__info.resetIndex ? align(__info.size, ((Integer) var2)) : 0;
        int var3 = __stk2;
        arg0.__info.enclosing = this;
        arg0.__info.offset = ((Integer) var3);
        __info.size = Math.max(__info.size, var3 + arg0.__info.size);
        return arg0;
    }

  protected final Struct inner(Class arg0) {
        Struct __stk3;
        try {
            Constructor var2 = arg0.getDeclaredConstructor(new Class[]{Runtime.class});
            Object[] __obj2 = new Object[1];
            __obj2[0] = getRuntime();
            Struct var3 = ((Struct) var2.newInstance(__obj2));
            __stk3 = inner(var3);
        } catch (NoSuchMethodException e1) {
            Throwable var2 = e1;
            throw new RuntimeException(new StringBuilder().append("Could not create an instance of ").append(arg0.getName()).append("\nBecause could not find the public constructor with a Runtime argument, it should look like:\npublic ").append(arg0.getSimpleName()).append("(Runtime runtime) {super(runtime);}").toString(), var2);
        } catch (Exception e2) {
            Throwable var2 = e2;
            throw new RuntimeException(var2);
        }
    }

  protected final Struct_Function function(Class arg0) {
        return new Struct_Function(this, arg0);
    }

  static int access$000(int arg0, int arg1) {
        return align(arg0, arg1);
    }

}