// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.StubCompiler
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Function;
import com.kenai.jffi.Internals;
import com.kenai.jffi.PageManager;
import com.kenai.jffi.Platform;
import com.kenai.jffi.Platform_OS;
import jnr.a64asm.Assembler_A64;
import jnr.a64asm.CPU_A64;
import jnr.ffi.CallingConvention;
import jnr.ffi.Runtime;
import jnr.ffi.provider.ParameterType;
import jnr.ffi.provider.ResultType;
import jnr.ffi.provider.jffi.ARM_64StubCompiler;
import jnr.ffi.provider.jffi.StubCompiler_DummyStubCompiler;
import jnr.ffi.provider.jffi.X86_32StubCompiler;
import jnr.ffi.provider.jffi.X86_64StubCompiler;
import jnr.x86asm.Assembler;
import jnr.x86asm.CPU;

abstract class StubCompiler {

    // ---- поля ----
  static final long errnoFunctionAddress;
  static final boolean hasPageManager;
  static final boolean hasAssembler;

    static {
        errnoFunctionAddress = getErrnoSaveFunction();
        hasPageManager = hasPageManager();
        hasAssembler = hasAssembler();
    }

   StubCompiler() { // было: <init>
        super();
    }

  public static StubCompiler newCompiler(Runtime arg0) {
        if (errnoFunctionAddress == 0L) {
            return new StubCompiler_DummyStubCompiler();
        }
        if (!hasPageManager) {
            return new StubCompiler_DummyStubCompiler();
        }
        if (!hasAssembler) {
            return new StubCompiler_DummyStubCompiler();
        }
        switch (Platform.getPlatform().getCPU()) {
            case I386:
                if (Platform.getPlatform().getOS() == Platform_OS.WINDOWS) {
                    return new StubCompiler_DummyStubCompiler();
                } else {
                    return new X86_32StubCompiler(arg0);
                }
            case X86_64:
                if (Platform.getPlatform().getOS() == Platform_OS.WINDOWS) {
                    return new StubCompiler_DummyStubCompiler();
                } else {
                    return new X86_64StubCompiler(arg0);
                }
            case AARCH64:
                if (Platform.getPlatform().getOS() != Platform_OS.WINDOWS) {
                    return new ARM_64StubCompiler(arg0);
                }
            default:
                return new StubCompiler_DummyStubCompiler();
        }
    }

  abstract boolean canCompile(ResultType arg0, ParameterType[] arg1, CallingConvention arg2);

  abstract void compile(Function arg0, String arg1, ResultType arg2, ParameterType[] arg3, Class arg4, Class[] arg5, CallingConvention arg6, boolean arg7);

  abstract void attach(Class arg0);

  private static long getErrnoSaveFunction() {
        long __stk1;
        try {
            __stk1 = Internals.getErrnoSaveFunction();
        } catch (Throwable var0) {
            return 0L;
        }
    }

  private static boolean hasPageManager() {
        int __stk1;
        try {
            long var0 = PageManager.getInstance().allocatePages(1, 3);
            PageManager.getInstance().freePages(var0, 1);
            __stk1 = 1;
        } catch (Throwable e1) {
            Throwable var0 = e1;
            return false;
        }
    }

  private static boolean hasAssembler() {
        int __stk1;
        int __stk2;
        int __stk3;
        int __stk4;
        try {
            switch (Platform.getPlatform().getCPU()) {
                case I386:
                    new Assembler(CPU.X86_32);
                    __stk1 = 1;
                case X86_64:
                    try {
                        new Assembler(CPU.X86_64);
                        __stk2 = 1;
                    } catch (Throwable var0) {
                        return false;
                    }
                case AARCH64:
                    try {
                        new Assembler_A64(CPU_A64.A64);
                        __stk3 = 1;
                    } catch (Throwable var0) {
                        return false;
                    }
                default:
                    try {
                        __stk4 = 0;
                    } catch (Throwable var0) {
                        return false;
                    }
            }
        } catch (Throwable var0) {
            return false;
        }
    }

}