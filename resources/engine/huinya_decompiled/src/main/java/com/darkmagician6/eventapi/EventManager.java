// исходный (обфусцированный) внутренний класс: com.darkmagician6.eventapi.EventManager
package com.darkmagician6.eventapi;

import com.darkmagician6.eventapi.EventManager_MethodData;
import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.EventStoppable;
import com.darkmagician6.eventapi.types.Priority;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public final class EventManager {

    // ---- поля ----
  private static final Map REGISTRY_MAP;

    static {
        REGISTRY_MAP = new HashMap();
    }

  private EventManager() { // было: <init>
        super();
    }

  public static void register(Object arg0) {
        Method[] var1 = arg0.getClass().getDeclaredMethods();
        int var2 = var1.length;
        int var3 = 0;
        while (var3 < var2) {
            Object var4 = var1[var3];
            if (!isMethodBad(((Method) var4))) {
                register(((Method) var4), arg0);
            }
            ++var3;
            continue;
        }
    }

  public static void register(Object arg0, Class arg1) {
        Method[] var2 = arg0.getClass().getDeclaredMethods();
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            Object var5 = var2[var4];
            if (!isMethodBad(((Method) var5), arg1)) {
                register(((Method) var5), arg0);
            }
            ++var4;
            continue;
        }
    }

  public static void unregister(Object arg0) {
        Iterator var1 = REGISTRY_MAP.values().iterator();
        while (var1.hasNext()) {
            List var2 = ((List) var1.next());
            var2.removeIf(lp0 -> lambda$unregister$0(arg0, ((EventManager_MethodData) lp0)));
            continue;
        }
        cleanMap(true);
    }

  public static void unregister(Object arg0, Class arg1) {
        List var2 = ((List) REGISTRY_MAP.get(arg1));
        if (var2 != null) {
            var2.removeIf(lp0 -> lambda$unregister$1(arg0, ((EventManager_MethodData) lp0)));
            cleanMap(true);
        }
    }

  private static void register(Method arg0, Object arg1) {
        if (arg0.getParameterCount() == 1) {
            Object var2 = arg0.getParameterTypes()[0];
            if (Event.class.isAssignableFrom(((Class) var2))) {
                Object var3 = var2;
                EventTarget var4 = ((EventTarget) arg0.getAnnotation(EventTarget.class));
                if (var4 != null) {
                    EventManager_MethodData var5 = new EventManager_MethodData(arg1, arg0, var4.value());
                    if (!var5.getTarget().isAccessible()) {
                        var5.getTarget().setAccessible(true);
                    }
                    (((List) REGISTRY_MAP.computeIfAbsent(var3, lp0 -> lambda$register$2(((Class) lp0))))).add(var5);
                    sortListValue(((Class) var3));
                    return;
                } else {
                    return;
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }

  public static void removeEntry(Class arg0) {
        REGISTRY_MAP.remove(arg0);
    }

  public static void cleanMap(boolean arg0) {
        if (!arg0) {
            REGISTRY_MAP.clear();
        } else {
            REGISTRY_MAP.entrySet().removeIf(lp0 -> lambda$cleanMap$3(((Entry) lp0)));
        }
    }

  private static void sortListValue(Class arg0) {
        List var1 = ((List) REGISTRY_MAP.get(arg0));
        if (var1 == null) {
            return;
        }
        CopyOnWriteArrayList var2;
        int var4;
        int var5;
        if (!var1.isEmpty()) {
            var2 = new CopyOnWriteArrayList();
            byte[] var3 = Priority.VALUE_ARRAY;
            var4 = var3.length;
            var5 = 0;
        } else {
            return;
        }
        while (var5 < var4) {
            byte var6 = var3[var5];
            Iterator var7 = var1.iterator();
            while (var7.hasNext()) {
                EventManager_MethodData var8 = ((EventManager_MethodData) var7.next());
                if (var8.getPriority() == var6) {
                    var2.add(var8);
                }
                continue;
            }
            ++var5;
            continue;
        }
        REGISTRY_MAP.put(arg0, var2);
    }

  private static boolean isMethodBad(Method arg0) {
        return arg0.getParameterCount() != 1 ? 1 : !Event.class.isAssignableFrom(((Class) arg0.getParameterTypes()[0])) ? 1 : !arg0.isAnnotationPresent(EventTarget.class);
    }

  private static boolean isMethodBad(Method arg0, Class arg1) {
        return isMethodBad(arg0) ? 1 : !arg0.getParameterTypes()[0].equals(arg1);
    }

  public static Event call(Event arg0) {
        List var1 = ((List) REGISTRY_MAP.get(arg0.getClass()));
        if (var1 != null) {
            if (!(arg0 instanceof EventStoppable)) {
                Iterator var3 = var1.iterator();
                while (var3.hasNext()) {
                    EventManager_MethodData var4 = ((EventManager_MethodData) var3.next());
                    invoke(var4, arg0);
                    continue;
                }
            } else {
                EventStoppable var2 = ((EventStoppable) arg0);
                Iterator var3 = var1.iterator();
                while (var3.hasNext()) {
                    EventManager_MethodData var4 = ((EventManager_MethodData) var3.next());
                    invoke(var4, arg0);
                    if (!var2.isStopped()) {
                        continue;
                    } else {
                        break;
                    }
                }
            }
        } else {
            return arg0;
        }
        return arg0;
    }

  private static void invoke(EventManager_MethodData arg0, Event arg1) {
        try {
            arg0.getTarget().invoke(arg0.getSource(), new Object[]{arg1});
        } catch (IllegalAccessException var2) {
        }
    }

  private static boolean lambda$cleanMap$3(Entry arg0) {
        return (((List) arg0.getValue())).isEmpty();
    }

  private static List lambda$register$2(Class arg0) {
        return new CopyOnWriteArrayList();
    }

  private static boolean lambda$unregister$1(Object arg0, EventManager_MethodData arg1) {
        return arg1.getSource().equals(arg0);
    }

  private static boolean lambda$unregister$0(Object arg0, EventManager_MethodData arg1) {
        return arg1.getSource().equals(arg0);
    }

}