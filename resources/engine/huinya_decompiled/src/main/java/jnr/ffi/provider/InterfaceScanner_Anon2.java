// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.InterfaceScanner$2
package jnr.ffi.provider;

import java.util.AbstractCollection;
import java.util.Iterator;
import jnr.ffi.provider.InterfaceScanner;
import jnr.ffi.provider.InterfaceScanner_VariablesIterator;

class InterfaceScanner_Anon2 extends AbstractCollection {

    // ---- поля ----
  final InterfaceScanner this$0;

   InterfaceScanner_Anon2(InterfaceScanner arg0) { // было: <init>
        super();
        this$0 = arg0;
    }

  public Iterator iterator() {
        return new InterfaceScanner_VariablesIterator(this$0, InterfaceScanner.access$000(this$0), null);
    }

  public int size() {
        return 0;
    }

}