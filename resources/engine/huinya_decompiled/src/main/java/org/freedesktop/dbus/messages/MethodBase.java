// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.messages.MethodBase
package org.freedesktop.dbus.messages;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import org.freedesktop.dbus.FileDescriptor;
import org.freedesktop.dbus.messages.Message;
import org.freedesktop.dbus.types.UInt32;

public abstract class MethodBase extends Message {

   MethodBase() { // было: <init>
        super();
    }

  protected MethodBase(byte arg0, byte arg1, byte arg2) { // было: <init>
        super(arg0, arg1, arg2);
    }

   void appendFileDescriptors(List arg0, Object[] arg1) {
        long __stk1;
        Objects.requireNonNull(arg0);
        if (arg1 != null) {
            Objects.requireNonNull(FileDescriptor.class);
            __stk1 = Arrays.stream(arg1).filter(lp0 -> FileDescriptor.class.isInstance(lp0)).count();
        } else {
            __stk1 = 0L;
        }
        long var3 = __stk1;
        if (var3 > 0L) {
            arg0.add(createHeaderArgs(9, "u", new UInt32(((Long) var3))));
        }
    }

}