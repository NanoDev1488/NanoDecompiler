// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.HeapBufferParameterStrategy
package jnr.ffi.provider.jffi;

import com.kenai.jffi.ObjectParameterType;
import com.kenai.jffi.ObjectParameterType_ComponentType;
import java.nio.Buffer;
import java.util.EnumSet;
import java.util.Iterator;
import jnr.ffi.provider.jffi.ParameterStrategy;

final class HeapBufferParameterStrategy extends ParameterStrategy {

    // ---- поля ----
  private static final HeapBufferParameterStrategy[] heapBufferStrategies;

    static {
        EnumSet var0 = EnumSet.allOf(ObjectParameterType_ComponentType.class);
        heapBufferStrategies = new HeapBufferParameterStrategy[var0.size()];
        Iterator var1 = var0.iterator();
        while (var1.hasNext()) {
            ObjectParameterType_ComponentType var2 = ((ObjectParameterType_ComponentType) var1.next());
            heapBufferStrategies[var2.ordinal()] = new HeapBufferParameterStrategy(var2);
            continue;
        }
    }

  public HeapBufferParameterStrategy(ObjectParameterType_ComponentType arg0) { // было: <init>
        super(HEAP, ObjectParameterType.create(ObjectParameterType.ARRAY, arg0));
    }

  public long address(Object arg0) {
        return 0L;
    }

  public Object object(Object arg0) {
        return (((Buffer) arg0)).array();
    }

  public int offset(Object arg0) {
        Buffer var2 = ((Buffer) arg0);
        return var2.arrayOffset() + var2.position();
    }

  public int length(Object arg0) {
        return (((Buffer) arg0)).remaining();
    }

  static HeapBufferParameterStrategy get(ObjectParameterType_ComponentType arg0) {
        return ((HeapBufferParameterStrategy) heapBufferStrategies[arg0.ordinal()]);
    }

}