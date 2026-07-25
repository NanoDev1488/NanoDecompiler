// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Type.Builtin
package com.kenai.jffi;

import com.kenai.jffi.Foreign;
import com.kenai.jffi.NativeType;
import com.kenai.jffi.Type;
import com.kenai.jffi.Type_Anon1;
import com.kenai.jffi.Type_TypeInfo;

final class Type_Builtin extends Type {

    // ---- поля ----
  private final NativeType nativeType;
  private Type_TypeInfo typeInfo;

  private Type_Builtin(NativeType arg0) { // было: <init>
        super();
        nativeType = arg0;
    }

   Type_TypeInfo getTypeInfo() {
        return typeInfo == null ? lookupTypeInfo() : typeInfo;
    }

  private Type_TypeInfo lookupTypeInfo() {
        Type_TypeInfo __stk1;
        try {
            Foreign var1 = Foreign.getInstance();
            long var2 = var1.lookupBuiltinType(nativeType.ffiType);
            if (var2 != 0L) {
                typeInfo = new Type_TypeInfo(var2, var1.getTypeType(var2), var1.getTypeSize(var2), var1.getTypeAlign(var2));
                __stk1 = new Type_TypeInfo(var2, var1.getTypeType(var2), var1.getTypeSize(var2), var1.getTypeAlign(var2));
            } else {
                throw new NullPointerException(new StringBuilder().append("invalid handle for native type ").append(nativeType).toString());
            }
        } catch (Throwable e1) {
            Throwable var1 = e1;
            throw new UnsatisfiedLinkError(new StringBuilder().append("could not get native definition for type `").append(nativeType).append("`, original error message follows: ").append(var1.getLocalizedMessage()).toString());
        }
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 == null) {
                return false;
            } else {
                if (getClass() == arg0.getClass()) {
                    if (super.equals(arg0)) {
                        Type_Builtin var2 = ((Type_Builtin) arg0);
                        if (nativeType == var2.nativeType) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return true;
        }
    }

  public int hashCode() {
        int var1 = super.hashCode();
        var1 = 31 * var1 + nativeType.hashCode();
        return var1;
    }

   Type_Builtin(NativeType arg0, Type_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}