// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.MethodTuple
package org.freedesktop.dbus;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MethodTuple {

    // ---- поля ----
  private final Logger logger;
  private final String name;
  private final String sig;

  public MethodTuple(String arg0, String arg1) { // было: <init>
        super();
        logger = LoggerFactory.getLogger(getClass());
        name = arg0;
        sig = null == arg1 ? "" : arg1;
        logger.trace("new MethodTuple({}, {})", name, sig);
    }

  public int hashCode() {
        return Objects.hash(new Object[]{name, sig});
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 instanceof MethodTuple) {
                MethodTuple var2 = ((MethodTuple) arg0);
                return !Objects.equals(name, var2.name) ? 0 : Objects.equals(sig, var2.sig);
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

  public Logger getLogger() {
        return logger;
    }

  public String getName() {
        return name;
    }

  public String getSig() {
        return sig;
    }

}