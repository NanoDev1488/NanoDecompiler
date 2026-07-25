// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.NativeSelectableChannel
package jnr.enxio.channels;

import java.nio.channels.Channel;

public interface NativeSelectableChannel extends Channel {

  public abstract int getFD();

}