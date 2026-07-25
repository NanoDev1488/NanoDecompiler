// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.PollSelectionKey
package jnr.enxio.channels;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.AbstractSelectionKey;
import jnr.enxio.channels.NativeSelectableChannel;
import jnr.enxio.channels.PollSelector;

class PollSelectionKey extends AbstractSelectionKey {

    // ---- поля ----
  private final PollSelector selector;
  private final NativeSelectableChannel channel;
  private int interestOps;
  private int readyOps;
  private int index;

  public PollSelectionKey(PollSelector arg0, NativeSelectableChannel arg1) { // было: <init>
        super();
        interestOps = 0;
        readyOps = 0;
        index = -1;
        selector = arg0;
        channel = arg1;
    }

   void setIndex(int arg0) {
        index = arg0;
    }

   int getIndex() {
        return index;
    }

   int getFD() {
        return channel.getFD();
    }

  public SelectableChannel channel() {
        return ((SelectableChannel) channel);
    }

  public Selector selector() {
        return selector;
    }

  public int interestOps() {
        return interestOps;
    }

  public SelectionKey interestOps(int arg0) {
        interestOps = arg0;
        selector.interestOps(this, arg0);
        return this;
    }

  public int readyOps() {
        return readyOps;
    }

   void readyOps(int arg0) {
        readyOps = arg0;
    }

}