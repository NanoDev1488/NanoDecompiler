// исходный (обфусцированный) внутренний класс: com.darkmagician6.eventapi.events.callables.EventTyped
package com.darkmagician6.eventapi.events.callables;

import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.events.Typed;

public abstract class EventTyped implements Event, Typed {

    // ---- поля ----
  private final byte type;

  protected EventTyped(byte arg0) { // было: <init>
        super();
        type = arg0;
    }

  public byte getType() {
        return type;
    }

}