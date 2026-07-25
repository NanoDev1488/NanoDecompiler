// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.PrimitiveArrayParameterStrategy$3
package jnr.ffi.provider.jffi;

import com.kenai.jffi.ObjectParameterType_ComponentType;
import jnr.ffi.provider.jffi.PrimitiveArrayParameterStrategy;

class PrimitiveArrayParameterStrategy_Anon3 extends PrimitiveArrayParameterStrategy {

   PrimitiveArrayParameterStrategy_Anon3(ObjectParameterType_ComponentType arg0) { // было: <init>
        super(arg0);
    }

  public int length(Object arg0) {
        return (((char[]) arg0)).length;
    }

}