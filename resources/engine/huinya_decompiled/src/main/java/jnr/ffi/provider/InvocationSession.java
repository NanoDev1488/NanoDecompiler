// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.InvocationSession
package jnr.ffi.provider;

import java.util.ArrayList;
import java.util.Iterator;
import jnr.ffi.provider.InvocationSession_PostInvoke;

public class InvocationSession {

    // ---- поля ----
  private ArrayList list;
  private ArrayList liveObjects;

  public InvocationSession() { // было: <init>
        super();
    }

  public void finish() {
        if (list != null) {
            Iterator var1 = list.iterator();
            while (var1.hasNext()) {
                InvocationSession_PostInvoke var2 = ((InvocationSession_PostInvoke) var1.next());
                try {
                    var2.postInvoke();
                } catch (Throwable var3) {
                }
                continue;
            }
        }
    }

  public void addPostInvoke(InvocationSession_PostInvoke arg0) {
        if (list == null) {
            list = new ArrayList();
        }
        list.add(arg0);
    }

  public void keepAlive(Object arg0) {
        if (liveObjects == null) {
            liveObjects = new ArrayList();
        }
        liveObjects.add(arg0);
    }

}