// исходный (обфусцированный) внутренний класс: com.darkmagician6.eventapi.events.Cancellable
package com.darkmagician6.eventapi.events;

public interface Cancellable {

  public abstract boolean isCancelled();

  public abstract void setCancelled(boolean arg0);

}