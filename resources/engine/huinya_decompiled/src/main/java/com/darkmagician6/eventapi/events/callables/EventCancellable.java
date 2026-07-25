// исходный (обфусцированный) внутренний класс: com.darkmagician6.eventapi.events.callables.EventCancellable
package com.darkmagician6.eventapi.events.callables;

import com.darkmagician6.eventapi.events.Cancellable;
import com.darkmagician6.eventapi.events.Event;

public abstract class EventCancellable implements Cancellable, Event {

    // ---- поля ----
  private boolean cancelled;

  protected EventCancellable() { // было: <init>
        super();
    }

  public boolean isCancelled() {
        return cancelled;
    }

  public void setCancelled(boolean arg0) {
        cancelled = arg0;
    }

}