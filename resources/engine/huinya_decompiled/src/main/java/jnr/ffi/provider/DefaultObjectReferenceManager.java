// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.DefaultObjectReferenceManager
package jnr.ffi.provider;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import jnr.ffi.ObjectReferenceManager;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.DefaultObjectReferenceManager_ObjectReference;

public final class DefaultObjectReferenceManager extends ObjectReferenceManager {

    // ---- поля ----
  private final Runtime runtime;
  private final ConcurrentMap references;

  public DefaultObjectReferenceManager(Runtime arg0) { // было: <init>
        super();
        references = new ConcurrentHashMap();
        runtime = arg0;
    }

  public Pointer add(Object arg0) {
        if (arg0 != null) {
            long var2 = id(arg0);
        } else {
            throw new IllegalArgumentException("reference to null value not allowed");
        }
        DefaultObjectReferenceManager_ObjectReference var4;
        while (true) {
            var4 = new DefaultObjectReferenceManager_ObjectReference(runtime, var2, arg0);
            if (references.putIfAbsent(Long.valueOf(var2), var4) == null) {
                break;
            }
            long var2 = var2 + 1L;
            continue;
        }
        return var4;
    }

  public boolean remove(Pointer arg0) {
        DefaultObjectReferenceManager_ObjectReference var2 = ((DefaultObjectReferenceManager_ObjectReference) references.remove(Long.valueOf(arg0.address())));
        return var2 != null;
    }

  public Object get(Pointer arg0) {
        DefaultObjectReferenceManager_ObjectReference var2 = ((DefaultObjectReferenceManager_ObjectReference) references.get(Long.valueOf(arg0.address())));
        return var2 == null ? null : DefaultObjectReferenceManager_ObjectReference.access$000(var2);
    }

  private long id(Object arg0) {
        return (-3819410108757049344L | ((long) System.identityHashCode(arg0)) & 4294967295L) & runtime.addressMask();
    }

}