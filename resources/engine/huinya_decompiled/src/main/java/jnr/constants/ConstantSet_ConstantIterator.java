// исходный (обфусцированный) внутренний класс: jnr.constants.ConstantSet.ConstantIterator
package jnr.constants;

import java.util.Collection;
import java.util.Iterator;
import jnr.constants.Constant;
import jnr.constants.ConstantSet;

final class ConstantSet_ConstantIterator implements Iterator {

    // ---- поля ----
  private final Iterator it;
  private Constant next;
  final ConstantSet this$0;

   ConstantSet_ConstantIterator(ConstantSet arg0, Collection arg1) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: putfield  #12 // jnr.constants.ConstantSet$ConstantIterator.this$0:Ljnr/constants/ConstantSet;
        //      5: aload_0
        //      6: invokespecial  #13 // java.lang.Object.<init>:()V
        //      9: aload_0
        //     10: aconst_null
        //     11: putfield  #11 // jnr.constants.ConstantSet$ConstantIterator.next:Ljnr/constants/Constant;
        //     14: aload_0
        //     15: aload_2
        //     16: invokeinterface  #17 // java.util.Collection.iterator:()Ljava/util/Iterator;, count 1
        //     21: putfield  #10 // jnr.constants.ConstantSet$ConstantIterator.it:Ljava/util/Iterator;
        //     24: aload_0
        //     25: aload_0
        //     26: getfield  #10 // jnr.constants.ConstantSet$ConstantIterator.it:Ljava/util/Iterator;
        //     29: invokeinterface  #18 // java.util.Iterator.hasNext:()Z, count 1
        //     34: ifeq  52 (offset +18)
        //     37: aload_0
        //     38: getfield  #10 // jnr.constants.ConstantSet$ConstantIterator.it:Ljava/util/Iterator;
        //     41: invokeinterface  #19 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     46: checkcast  #7 // jnr.constants.Constant
        //     49: goto  53 (offset +4)
        //     52: aconst_null
        //     53: putfield  #11 // jnr.constants.ConstantSet$ConstantIterator.next:Ljnr/constants/Constant;
        //     56: return
    }

  public boolean hasNext() {
        return next == null ? 0 : !next.name().equals("__UNKNOWN_CONSTANT__");
    }

  public void remove() {
        throw new UnsupportedOperationException();
    }

  public Constant next() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #11 // jnr.constants.ConstantSet$ConstantIterator.next:Ljnr/constants/Constant;
        //      4: astore_1
        //      5: aload_0
        //      6: aload_0
        //      7: getfield  #10 // jnr.constants.ConstantSet$ConstantIterator.it:Ljava/util/Iterator;
        //     10: invokeinterface  #18 // java.util.Iterator.hasNext:()Z, count 1
        //     15: ifeq  33 (offset +18)
        //     18: aload_0
        //     19: getfield  #10 // jnr.constants.ConstantSet$ConstantIterator.it:Ljava/util/Iterator;
        //     22: invokeinterface  #19 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     27: checkcast  #7 // jnr.constants.Constant
        //     30: goto  34 (offset +4)
        //     33: aconst_null
        //     34: putfield  #11 // jnr.constants.ConstantSet$ConstantIterator.next:Ljnr/constants/Constant;
        //     37: aload_1
        //     38: areturn
    }

  public Object next() {
        return next();
    }

}