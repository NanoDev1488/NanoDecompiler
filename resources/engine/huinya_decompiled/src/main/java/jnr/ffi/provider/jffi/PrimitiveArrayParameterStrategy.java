// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.PrimitiveArrayParameterStrategy
package jnr.ffi.provider.jffi;

import com.kenai.jffi.ObjectParameterType;
import com.kenai.jffi.ObjectParameterType_ComponentType;
import com.kenai.jffi.ObjectParameterType_ObjectType;
import jnr.ffi.provider.jffi.ParameterStrategy;
import jnr.ffi.provider.jffi.PrimitiveArrayParameterStrategy_Anon1;
import jnr.ffi.provider.jffi.PrimitiveArrayParameterStrategy_Anon2;
import jnr.ffi.provider.jffi.PrimitiveArrayParameterStrategy_Anon3;
import jnr.ffi.provider.jffi.PrimitiveArrayParameterStrategy_Anon4;
import jnr.ffi.provider.jffi.PrimitiveArrayParameterStrategy_Anon5;
import jnr.ffi.provider.jffi.PrimitiveArrayParameterStrategy_Anon6;
import jnr.ffi.provider.jffi.PrimitiveArrayParameterStrategy_Anon7;
import jnr.ffi.provider.jffi.PrimitiveArrayParameterStrategy_Anon8;

public abstract class PrimitiveArrayParameterStrategy extends ParameterStrategy {

    // ---- поля ----
  static final PrimitiveArrayParameterStrategy BYTE;
  static final PrimitiveArrayParameterStrategy SHORT;
  static final PrimitiveArrayParameterStrategy CHAR;
  static final PrimitiveArrayParameterStrategy INT;
  static final PrimitiveArrayParameterStrategy LONG;
  static final PrimitiveArrayParameterStrategy FLOAT;
  static final PrimitiveArrayParameterStrategy DOUBLE;
  static final PrimitiveArrayParameterStrategy BOOLEAN;

    static {
        BYTE = new PrimitiveArrayParameterStrategy_Anon1(ObjectParameterType.BYTE);
        SHORT = new PrimitiveArrayParameterStrategy_Anon2(ObjectParameterType.SHORT);
        CHAR = new PrimitiveArrayParameterStrategy_Anon3(ObjectParameterType.CHAR);
        INT = new PrimitiveArrayParameterStrategy_Anon4(ObjectParameterType.INT);
        LONG = new PrimitiveArrayParameterStrategy_Anon5(ObjectParameterType.LONG);
        FLOAT = new PrimitiveArrayParameterStrategy_Anon6(ObjectParameterType.FLOAT);
        DOUBLE = new PrimitiveArrayParameterStrategy_Anon7(ObjectParameterType.DOUBLE);
        BOOLEAN = new PrimitiveArrayParameterStrategy_Anon8(ObjectParameterType.BOOLEAN);
    }

   PrimitiveArrayParameterStrategy(ObjectParameterType_ComponentType arg0) { // было: <init>
        super(HEAP, ObjectParameterType.create(ObjectParameterType_ObjectType.ARRAY, arg0));
    }

  public final long address(Object arg0) {
        return 0L;
    }

  public final Object object(Object arg0) {
        return arg0;
    }

  public final int offset(Object arg0) {
        return 0;
    }

}