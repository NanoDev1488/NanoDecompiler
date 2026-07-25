// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.messages.MethodReturn
package org.freedesktop.dbus.messages;

import java.util.ArrayList;
import java.util.List;
import org.freedesktop.dbus.messages.MethodBase;
import org.freedesktop.dbus.messages.MethodCall;

public class MethodReturn extends MethodBase {

    // ---- поля ----
  private MethodCall call;

   MethodReturn() { // было: <init>
        super();
    }

  protected MethodReturn(byte arg0, String arg1, long arg2, String arg3, Object[] arg4) { // было: <init>
        this(arg0, null, arg1, arg2, arg3, arg4);
    }

  protected MethodReturn(byte arg0, String arg1, String arg2, long arg3, String arg4, Object[] arg5) { // было: <init>
        super(arg0, 2, 0);
        ArrayList var8 = new ArrayList();
        var8.add(createHeaderArgs(5, "u", Long.valueOf(arg3)));
        if (null != arg1) {
            var8.add(createHeaderArgs(7, "s", arg1));
        }
        if (null != arg2) {
            var8.add(createHeaderArgs(6, "s", arg2));
        }
        if (null != arg4) {
            var8.add(createHeaderArgs(8, "g", arg4));
            setArgs(arg5);
        }
        appendFileDescriptors(var8, arg5);
        padAndMarshall(var8, getSerial(), arg4, arg5);
    }

  protected MethodReturn(MethodCall arg0, String arg1, Object[] arg2) { // было: <init>
        this(null, arg0, arg1, arg2);
    }

  protected MethodReturn(String arg0, MethodCall arg1, String arg2, Object[] arg3) { // было: <init>
        this(arg1.getEndianess(), arg0, arg1.getSource(), arg1.getSerial(), arg2, arg3);
        call = arg1;
    }

  public MethodCall getCall() {
        return call;
    }

  public void setCall(MethodCall arg0) {
        call = arg0;
    }

}