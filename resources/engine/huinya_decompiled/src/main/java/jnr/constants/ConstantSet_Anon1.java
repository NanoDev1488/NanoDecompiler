// исходный (обфусцированный) внутренний класс: jnr.constants.ConstantSet$1
package jnr.constants;

import java.net.URL;
import java.security.PrivilegedAction;
import jnr.constants.ConstantSet;

class ConstantSet_Anon1 implements PrivilegedAction {

   ConstantSet_Anon1() { // было: <init>
        super();
    }

  public URL run() {
        return ConstantSet.access$000().getResource("jnr/constants/ConstantSet.class");
    }

  public Object run() {
        return run();
    }

}