// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.StubCompiler.DummyStubCompiler
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Function;
import jnr.ffi.CallingConvention;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.ResultType;
import jnr.ffi.provider.jffi.StubCompiler;

final class StubCompiler_DummyStubCompiler extends StubCompiler {

   StubCompiler_DummyStubCompiler() { // было: <init>
        super();
    }

   boolean canCompile(ResultType arg0, ParameterType[] arg1, CallingConvention arg2) {
        return false;
    }

   void compile(Function arg0, String arg1, ResultType arg2, ParameterType[] arg3, Class arg4, Class[] arg5, CallingConvention arg6, boolean arg7) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   void attach(Class arg0) {
        // (пустое тело)
    }

}