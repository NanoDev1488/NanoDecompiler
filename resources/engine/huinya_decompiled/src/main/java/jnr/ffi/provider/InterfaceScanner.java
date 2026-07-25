// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.InterfaceScanner
package jnr.ffi.provider;

import java.lang.reflect.Method;
import java.util.Collection;
import jnr.ffi.CallingConvention;
import jnr.ffi.mapper.SignatureTypeMapper;
import jnr.ffi.provider.InterfaceScanner_Anon1;
import jnr.ffi.provider.InterfaceScanner_Anon2;

public class InterfaceScanner {

    // ---- поля ----
  private final Class interfaceClass;
  private final SignatureTypeMapper typeMapper;
  private final CallingConvention callingConvention;
  private final Method[] methods;
  private static final Method methodIsDefault;

    static {
        Object var0 = null;
        try {
            var0 = Method.class.getMethod("isDefault", null);
        } catch (NoSuchMethodException var1) {
        }
        methodIsDefault = ((Method) var0);
    }

  public InterfaceScanner(Class arg0, SignatureTypeMapper arg1, CallingConvention arg2) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokespecial  #28 // java.lang.Object.<init>:()V
        //      4: aload_0
        //      5: aload_1
        //      6: putfield  #20 // jnr.ffi.provider.InterfaceScanner.interfaceClass:Ljava/lang/Class;
        //      9: aload_0
        //     10: aload_2
        //     11: putfield  #23 // jnr.ffi.provider.InterfaceScanner.typeMapper:Ljnr/ffi/mapper/SignatureTypeMapper;
        //     14: aload_0
        //     15: aload_1
        //     16: invokevirtual  #26 // java.lang.Class.getMethods:()[Ljava/lang/reflect/Method;
        //     19: putfield  #22 // jnr.ffi.provider.InterfaceScanner.methods:[Ljava/lang/reflect/Method;
        //     22: aload_0
        //     23: aload_1
        //     24: ldc  #11 // jnr.ffi.annotations.StdCall
        //     26: invokevirtual  #27 // java.lang.Class.isAnnotationPresent:(Ljava/lang/Class;)Z
        //     29: ifeq  38 (offset +9)
        //     32: getstatic  #18 // jnr.ffi.CallingConvention.STDCALL:Ljnr/ffi/CallingConvention;
        //     35: goto  39 (offset +4)
        //     38: aload_3
        //     39: putfield  #19 // jnr.ffi.provider.InterfaceScanner.callingConvention:Ljnr/ffi/CallingConvention;
        //     42: return
    }

  public Collection functions() {
        return new InterfaceScanner_Anon1(this);
    }

  public Collection variables() {
        return new InterfaceScanner_Anon2(this);
    }

  private static boolean isDefault(Method arg0) {
        boolean __stk1;
        if (methodIsDefault == null) {
            return false;
        }
        try {
            __stk1 = Boolean.TRUE.equals(methodIsDefault.invoke(arg0, new Object[0]));
        } catch (Exception var1) {
            throw new RuntimeException("Unexpected error attempting to call isDefault method", var1);
        }
    }

  static Method[] access$000(InterfaceScanner arg0) {
        return arg0.methods;
    }

  static boolean access$300(Method arg0) {
        return isDefault(arg0);
    }

  static CallingConvention access$400(InterfaceScanner arg0) {
        return arg0.callingConvention;
    }

}