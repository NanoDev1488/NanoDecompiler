// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.StructHelper
package org.freedesktop.dbus;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.DBusStructType;
import org.freedesktop.dbus.types.Variant;

public final class StructHelper {

  private StructHelper() { // было: <init>
        super();
    }

  public static List convertToStructList(List arg0, Class arg1) {
        ArrayList var2 = new ArrayList();
        convertToStructCollection(arg0, arg1, var2);
        return var2;
    }

  public static Set convertToStructSet(Set arg0, Class arg1) {
        LinkedHashSet var2 = new LinkedHashSet();
        convertToStructCollection(arg0, arg1, var2);
        return var2;
    }

  public static void convertToStructCollection(Collection arg0, Class arg1, Collection arg2) {
        Objects.requireNonNull(arg1, "Struct class required");
        Objects.requireNonNull(arg2, "Collection for result storage required");
        Objects.requireNonNull(arg0, "Input data required");
        Class[] var3 = ((Class[]) Arrays.stream(arg1.getDeclaredFields()).filter(lp0 -> lambda$convertToStructCollection$0(((Field) lp0))).sorted((lp0, lp1) -> lambda$convertToStructCollection$1(((Field) lp0), ((Field) lp1))).map(lp0 -> (((Field) lp0)).getType()).toArray(lp0 -> lambda$convertToStructCollection$2(lp0)));
        Iterator var4 = arg0.iterator();
        while (true) {
            if (!var4.hasNext()) {
                return;
            }
            Object[] var5 = ((Object[]) var4.next());
            if (var3.length != var5.length) {
                break;
            }
            Struct var6 = createStruct(var3, var5, arg1);
            arg2.add(var6);
            continue;
        }
        throw new IllegalArgumentException("Struct length does not match argument length");
    }

  public static Struct createStructFromVariant(Variant arg0, Class arg1) {
        if (arg0 == null) {
            return null;
        } else {
            if (arg1 != null) {
                if (!(arg0.getType() instanceof DBusStructType)) {
                    return null;
                } else {
                    if (!(arg0.getValue() instanceof Object[])) {
                        return null;
                    } else {
                        Class[] var2 = ((Class[]) Arrays.stream(((Object[]) arg0.getValue())).map(lp0 -> lp0.getClass()).toArray(lp0 -> lambda$createStructFromVariant$3(lp0)));
                        return createStruct(var2, arg0.getValue(), arg1);
                    }
                }
            } else {
                return null;
            }
        }
    }

  public static Struct createStruct(Class[] arg0, Object arg1, Class arg2) {
        Struct __stk2;
        Struct __stk3;
        if (arg0 == null) {
            return null;
        }
        if (arg2 == null) {
            return null;
        }
        if (arg1 == null) {
            return null;
        }
        try {
            Constructor var3 = arg2.getDeclaredConstructor(arg0);
            var3.setAccessible(true);
            if (arg1 instanceof Object[]) {
                Object[] var4 = ((Object[]) arg1);
                __stk2 = ((Struct) var3.newInstance(var4));
            }
            try {
                __stk3 = ((Struct) var3.newInstance(new Object[]{arg1}));
            } catch (NoSuchMethodException e1) {
                var3 = e1;
                int var4 = 0;
            }
        } catch (NoSuchMethodException e2) {
            Throwable var3 = e1;
            int var4 = 0;
        }
    }

  private static Class[] lambda$createStructFromVariant$3(int arg0) {
        return new Class[arg0];
    }

  private static Class[] lambda$convertToStructCollection$2(int arg0) {
        return new Class[arg0];
    }

  private static int lambda$convertToStructCollection$1(Field arg0, Field arg1) {
        return Integer.compare((((Position) arg0.getAnnotation(Position.class))).value(), (((Position) arg1.getAnnotation(Position.class))).value());
    }

  private static boolean lambda$convertToStructCollection$0(Field arg0) {
        return arg0.isAnnotationPresent(Position.class);
    }

}