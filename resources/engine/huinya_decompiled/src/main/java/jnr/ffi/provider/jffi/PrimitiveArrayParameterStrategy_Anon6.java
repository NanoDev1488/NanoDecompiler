// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.PrimitiveArrayParameterStrategy$6
package jnr.ffi.provider.jffi;

import com.kenai.jffi.ObjectParameterType_ComponentType;
import jnr.ffi.provider.jffi.PrimitiveArrayParameterStrategy;

class PrimitiveArrayParameterStrategy_Anon6 extends PrimitiveArrayParameterStrategy {

   PrimitiveArrayParameterStrategy_Anon6(ObjectParameterType_ComponentType arg0) { // было: <init>
        super(arg0);
    }

  public int length(Object arg0) {
        return (((float[]) arg0)).length;
    }

}