// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.KQSelectionKey
package jnr.enxio.channels;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.AbstractSelectionKey;
import jnr.enxio.channels.KQSelector;
import jnr.enxio.channels.NativeSelectableChannel;

class KQSelectionKey extends AbstractSelectionKey {

    // ---- поля ----
  private final KQSelector selector;
  private final NativeSelectableChannel channel;
  private int interestOps;
  private int readyOps;

  public KQSelectionKey(KQSelector arg0, NativeSelectableChannel arg1, int arg2) { // было: <init>
        super();
        interestOps = 0;
        readyOps = 0;
        selector = arg0;
        channel = arg1;
        interestOps = arg2;
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