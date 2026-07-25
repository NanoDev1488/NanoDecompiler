// исходный (обфусцированный) внутренний класс: jnr.constants.platform.ConstantResolver.UnknownConstant
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.PlatformConstants;

final class ConstantResolver_UnknownConstant implements Constant {

    // ---- поля ----
  private final long value;
  private final String name;

   ConstantResolver_UnknownConstant(long arg0, String arg1) { // было: <init>
        super();
        value = arg0;
        name = arg1;
    }

  public int value() {
        checkFake();
        return ((int) value);
    }

  public final int intValue() {
        checkFake();
        return ((int) value);
    }

  public final long longValue() {
        checkFake();
        return value;
    }

  public final String name() {
        return name;
    }

  public final boolean defined() {
        return false;
    }

  public final String toString() {
        return name;
    }

  private void checkFake() {
        if (PlatformConstants.FAKE) {
            return;
        } else {
            throw new AssertionError(new StringBuilder().append("Constant ").append(name).append(" is not defined on ").append(PlatformConstants.NAME).toString());
        }
    }

}