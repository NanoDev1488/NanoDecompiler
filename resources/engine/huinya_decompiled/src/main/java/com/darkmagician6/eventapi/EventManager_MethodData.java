// исходный (обфусцированный) внутренний класс: com.darkmagician6.eventapi.EventManager.MethodData
package com.darkmagician6.eventapi;

import java.lang.reflect.Method;

final class EventManager_MethodData {

    // ---- поля ----
  private final Object source;
  private final Method target;
  private final byte priority;

  public EventManager_MethodData(Object arg0, Method arg1, byte arg2) { // было: <init>
        super();
        source = arg0;
        target = arg1;
        priority = arg2;
    }

  public Object getSource() {
        return source;
    }

  public Method getTarget() {
        return target;
    }

  public byte getPriority() {
        return priority;
    }

}