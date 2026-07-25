// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.MethodGenerator
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Function;
import jnr.ffi.CallingConvention;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.ResultType;
import jnr.ffi.provider.jffi.AsmBuilder;

public interface MethodGenerator {

  public abstract boolean isSupported(ResultType arg0, ParameterType[] arg1, CallingConvention arg2);

  public abstract void generate(AsmBuilder arg0, String arg1, Function arg2, ResultType arg3, ParameterType[] arg4, boolean arg5);

}