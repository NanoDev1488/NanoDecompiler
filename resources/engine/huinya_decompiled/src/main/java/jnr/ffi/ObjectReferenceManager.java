// исходный (обфусцированный) внутренний класс: jnr.ffi.ObjectReferenceManager
package jnr.ffi;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;

public abstract class ObjectReferenceManager {

  public ObjectReferenceManager() { // было: <init>
        super();
    }

  public static ObjectReferenceManager newInstance(Runtime arg0) {
        return arg0.newObjectReferenceManager();
    }

    @Deprecated
  public Pointer newReference(Object arg0) {
        return add(arg0);
    }

    @Deprecated
  public void freeReference(Pointer arg0) {
        remove(arg0);
    }

    @Deprecated
  public Object getObject(Pointer arg0) {
        return get(arg0);
    }

  public abstract Pointer add(Object arg0);

  public abstract boolean remove(Pointer arg0);

  public abstract Object get(Pointer arg0);

}