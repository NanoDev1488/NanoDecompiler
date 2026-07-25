// исходный (обфусцированный) внутренний класс: com.darkmagician6.eventapi.events.EventStoppable
package com.darkmagician6.eventapi.events;

import com.darkmagician6.eventapi.events.Event;

public abstract class EventStoppable implements Event {

    // ---- поля ----
  private boolean stopped;

  protected EventStoppable() { // было: <init>
        super();
    }

  public void stop() {
        stopped = true;
    }

  public boolean isStopped() {
        return stopped;
    }

}