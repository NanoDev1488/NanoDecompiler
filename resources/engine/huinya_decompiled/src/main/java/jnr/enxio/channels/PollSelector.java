// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.PollSelector
package jnr.enxio.channels;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import jnr.enxio.channels.Native;
import jnr.enxio.channels.NativeSelectableChannel;
import jnr.enxio.channels.Native_LibC;
import jnr.enxio.channels.PollSelectionKey;

class PollSelector extends AbstractSelector {

    // ---- поля ----
  private static final int POLLFD_SIZE = 8;
  private static final int FD_OFFSET = 0;
  private static final int EVENTS_OFFSET = 4;
  private static final int REVENTS_OFFSET = 6;
  static final int POLLIN = 1;
  static final int POLLOUT = 4;
  static final int POLLERR = 8;
  static final int POLLHUP = 16;
  private PollSelectionKey[] keyArray;
  private ByteBuffer pollData;
  private int nfds;
  private final int[] pipefd;
  private final Object regLock;
  private final Map keys;
  private final Set selected;

  public PollSelector(SelectorProvider arg0) { // было: <init>
        super(arg0);
        keyArray = new PollSelectionKey[0];
        pollData = null;
        pipefd = new int[]{-1, -1};
        regLock = new Object();
        keys = new ConcurrentHashMap();
        selected = new HashSet();
        Native.libc().pipe(pipefd);
        pollData = ByteBuffer.allocateDirect(8).order(ByteOrder.nativeOrder());
        putPollFD(0, pipefd[0]);
        putPollEvents(0, 1);
        nfds = 1;
        keyArray = new PollSelectionKey[1];
    }

  private void putPollFD(int arg0, int arg1) {
        pollData.putInt(arg0 * 8 + 0, arg1);
    }

  private void putPollEvents(int arg0, int arg1) {
        pollData.putShort(arg0 * 8 + 4, ((short) arg1));
    }

  private int getPollFD(int arg0) {
        return pollData.getInt(arg0 * 8 + 0);
    }

  private short getPollEvents(int arg0) {
        return pollData.getShort(arg0 * 8 + 4);
    }

  private short getPollRevents(int arg0) {
        return pollData.getShort(arg0 * 8 + 6);
    }

  private void putPollRevents(int arg0, int arg1) {
        pollData.putShort(arg0 * 8 + 6, ((short) arg1));
    }

  protected void implCloseSelector() {
        if (pipefd[0] != -1) {
            Native.close(pipefd[0]);
        }
        if (pipefd[1] != -1) {
            Native.close(pipefd[1]);
        }
        Iterator var1 = keys.keySet().iterator();
        while (var1.hasNext()) {
            SelectionKey var2 = ((SelectionKey) var1.next());
            remove(((PollSelectionKey) var2));
            continue;
        }
    }

  protected SelectionKey register(AbstractSelectableChannel arg0, int arg1, Object arg2) {
        PollSelectionKey var4 = new PollSelectionKey(this, ((NativeSelectableChannel) arg0));
        add(var4);
        var4.attach(arg2);
        var4.interestOps(arg1);
        return var4;
    }

  public Set keys() {
        return new HashSet(Arrays.asList(keyArray).subList(1, nfds));
    }

  public Set selectedKeys() {
        return selected;
    }

   void interestOps(PollSelectionKey arg0, int arg1) {
        int var3 = 0;
        if ((arg1 & 17) != 0) {
            var3 = ((short) (var3 | 1));
        }
        if ((arg1 & 12) != 0) {
            var3 = ((short) (var3 | 4));
        }
        putPollEvents(arg0.getIndex(), var3);
    }

  private void add(PollSelectionKey arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #38 // jnr.enxio.channels.PollSelector.regLock:Ljava/lang/Object;
        //      4: dup
        //      5: astore_2
        //      6: monitorenter
        //      7: aload_0
        //      8: dup
        //      9: getfield  #35 // jnr.enxio.channels.PollSelector.nfds:I
        //     12: iconst_1
        //     13: iadd
        //     14: putfield  #35 // jnr.enxio.channels.PollSelector.nfds:I
        //     17: aload_0
        //     18: getfield  #33 // jnr.enxio.channels.PollSelector.keyArray:[Ljnr/enxio/channels/PollSelectionKey;
        //     21: arraylength
        //     22: aload_0
        //     23: getfield  #35 // jnr.enxio.channels.PollSelector.nfds:I
        //     26: if_icmpge  111 (offset +85)
        //     29: aload_0
        //     30: getfield  #35 // jnr.enxio.channels.PollSelector.nfds:I
        //     33: aload_0
        //     34: getfield  #35 // jnr.enxio.channels.PollSelector.nfds:I
        //     37: iconst_2
        //     38: idiv
        //     39: iadd
        //     40: anewarray  #27 // jnr.enxio.channels.PollSelectionKey
        //     43: astore_3
        //     44: aload_0
        //     45: getfield  #33 // jnr.enxio.channels.PollSelector.keyArray:[Ljnr/enxio/channels/PollSelectionKey;
        //     48: iconst_0
        //     49: aload_3
        //     50: iconst_0
        //     51: aload_0
        //     52: getfield  #35 // jnr.enxio.channels.PollSelector.nfds:I
        //     55: iconst_1
        //     56: isub
        //     57: invokestatic  #43 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //     60: aload_0
        //     61: aload_3
        //     62: putfield  #33 // jnr.enxio.channels.PollSelector.keyArray:[Ljnr/enxio/channels/PollSelectionKey;
        //     65: aload_3
        //     66: arraylength
        //     67: bipush  8
        //     69: imul
        //     70: invokestatic  #45 // java.nio.ByteBuffer.allocateDirect:(I)Ljava/nio/ByteBuffer;
        //     73: astore  4
        //     75: aload_0
        //     76: getfield  #37 // jnr.enxio.channels.PollSelector.pollData:Ljava/nio/ByteBuffer;
        //     79: ifnull  92 (offset +13)
        //     82: aload  4
        //     84: aload_0
        //     85: getfield  #37 // jnr.enxio.channels.PollSelector.pollData:Ljava/nio/ByteBuffer;
        //     88: invokevirtual  #50 // java.nio.ByteBuffer.put:(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;
        //     91: pop
        //     92: aload  4
        //     94: iconst_0
        //     95: invokevirtual  #49 // java.nio.ByteBuffer.position:(I)Ljava/nio/Buffer;
        //     98: pop
        //     99: aload_0
        //    100: aload  4
        //    102: invokestatic  #53 // java.nio.ByteOrder.nativeOrder:()Ljava/nio/ByteOrder;
        //    105: invokevirtual  #48 // java.nio.ByteBuffer.order:(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;
        //    108: putfield  #37 // jnr.enxio.channels.PollSelector.pollData:Ljava/nio/ByteBuffer;
        //    111: aload_1
        //    112: aload_0
        //    113: getfield  #35 // jnr.enxio.channels.PollSelector.nfds:I
        //    116: iconst_1
        //    117: isub
        //    118: invokevirtual  #73 // jnr.enxio.channels.PollSelectionKey.setIndex:(I)V
        //    121: aload_0
        //    122: getfield  #33 // jnr.enxio.channels.PollSelector.keyArray:[Ljnr/enxio/channels/PollSelectionKey;
        //    125: aload_0
        //    126: getfield  #35 // jnr.enxio.channels.PollSelector.nfds:I
        //    129: iconst_1
        //    130: isub
        //    131: aload_1
        //    132: aastore
        //    133: aload_0
        //    134: aload_1
        //    135: invokevirtual  #70 // jnr.enxio.channels.PollSelectionKey.getIndex:()I
        //    138: aload_1
        //    139: invokevirtual  #69 // jnr.enxio.channels.PollSelectionKey.getFD:()I
        //    142: invokespecial  #84 // jnr.enxio.channels.PollSelector.putPollFD:(II)V
        //    145: aload_0
        //    146: aload_1
        //    147: invokevirtual  #70 // jnr.enxio.channels.PollSelectionKey.getIndex:()I
        //    150: iconst_0
        //    151: invokespecial  #83 // jnr.enxio.channels.PollSelector.putPollEvents:(II)V
        //    154: aload_0
        //    155: getfield  #34 // jnr.enxio.channels.PollSelector.keys:Ljava/util/Map;
        //    158: aload_1
        //    159: iconst_1
        //    160: invokestatic  #40 // java.lang.Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    163: invokeinterface  #93 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    168: pop
        //    169: aload_2
        //    170: monitorexit
        //    171: goto  181 (offset +10)
        //    174: astore  5
        //    176: aload_2
        //    177: monitorexit
        //    178: aload  5
        //    180: athrow
        //    181: return
        //       Exception table:
        //         from 7 to 171 target 174 type any
        //         from 174 to 178 target 174 type any
    }

  private void remove(PollSelectionKey arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: invokevirtual  #70 // jnr.enxio.channels.PollSelectionKey.getIndex:()I
        //      4: istore_2
        //      5: aload_0
        //      6: getfield  #38 // jnr.enxio.channels.PollSelector.regLock:Ljava/lang/Object;
        //      9: dup
        //     10: astore_3
        //     11: monitorenter
        //     12: iload_2
        //     13: aload_0
        //     14: getfield  #35 // jnr.enxio.channels.PollSelector.nfds:I
        //     17: iconst_1
        //     18: isub
        //     19: if_icmpge  80 (offset +61)
        //     22: aload_0
        //     23: getfield  #33 // jnr.enxio.channels.PollSelector.keyArray:[Ljnr/enxio/channels/PollSelectionKey;
        //     26: aload_0
        //     27: getfield  #35 // jnr.enxio.channels.PollSelector.nfds:I
        //     30: iconst_1
        //     31: isub
        //     32: aaload
        //     33: astore  4
        //     35: aload_0
        //     36: getfield  #33 // jnr.enxio.channels.PollSelector.keyArray:[Ljnr/enxio/channels/PollSelectionKey;
        //     39: iload_2
        //     40: aload  4
        //     42: aastore
        //     43: aload_0
        //     44: iload_2
        //     45: aload_0
        //     46: aload  4
        //     48: invokevirtual  #70 // jnr.enxio.channels.PollSelectionKey.getIndex:()I
        //     51: invokespecial  #80 // jnr.enxio.channels.PollSelector.getPollFD:(I)I
        //     54: invokespecial  #84 // jnr.enxio.channels.PollSelector.putPollFD:(II)V
        //     57: aload_0
        //     58: iload_2
        //     59: aload_0
        //     60: aload  4
        //     62: invokevirtual  #70 // jnr.enxio.channels.PollSelectionKey.getIndex:()I
        //     65: invokespecial  #79 // jnr.enxio.channels.PollSelector.getPollEvents:(I)S
        //     68: invokespecial  #83 // jnr.enxio.channels.PollSelector.putPollEvents:(II)V
        //     71: aload  4
        //     73: iload_2
        //     74: invokevirtual  #73 // jnr.enxio.channels.PollSelectionKey.setIndex:(I)V
        //     77: goto  92 (offset +15)
        //     80: aload_0
        //     81: iload_2
        //     82: iconst_m1
        //     83: invokespecial  #84 // jnr.enxio.channels.PollSelector.putPollFD:(II)V
        //     86: aload_0
        //     87: iload_2
        //     88: iconst_0
        //     89: invokespecial  #83 // jnr.enxio.channels.PollSelector.putPollEvents:(II)V
        //     92: aload_0
        //     93: getfield  #33 // jnr.enxio.channels.PollSelector.keyArray:[Ljnr/enxio/channels/PollSelectionKey;
        //     96: aload_0
        //     97: getfield  #35 // jnr.enxio.channels.PollSelector.nfds:I
        //    100: iconst_1
        //    101: isub
        //    102: aconst_null
        //    103: aastore
        //    104: aload_0
        //    105: dup
        //    106: getfield  #35 // jnr.enxio.channels.PollSelector.nfds:I
        //    109: iconst_1
        //    110: isub
        //    111: putfield  #35 // jnr.enxio.channels.PollSelector.nfds:I
        //    114: aload_0
        //    115: getfield  #39 // jnr.enxio.channels.PollSelector.selected:Ljava/util/Set;
        //    118: dup
        //    119: astore  4
        //    121: monitorenter
        //    122: aload_0
        //    123: getfield  #39 // jnr.enxio.channels.PollSelector.selected:Ljava/util/Set;
        //    126: aload_1
        //    127: invokeinterface  #99 // java.util.Set.remove:(Ljava/lang/Object;)Z, count 2
        //    132: pop
        //    133: aload  4
        //    135: monitorexit
        //    136: goto  147 (offset +11)
        //    139: astore  5
        //    141: aload  4
        //    143: monitorexit
        //    144: aload  5
        //    146: athrow
        //    147: aload_0
        //    148: getfield  #34 // jnr.enxio.channels.PollSelector.keys:Ljava/util/Map;
        //    151: aload_1
        //    152: invokeinterface  #94 // java.util.Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    157: pop
        //    158: aload_3
        //    159: monitorexit
        //    160: goto  170 (offset +10)
        //    163: astore  6
        //    165: aload_3
        //    166: monitorexit
        //    167: aload  6
        //    169: athrow
        //    170: aload_0
        //    171: aload_1
        //    172: invokevirtual  #77 // jnr.enxio.channels.PollSelector.deregister:(Ljava/nio/channels/spi/AbstractSelectionKey;)V
        //    175: return
        //       Exception table:
        //         from 122 to 136 target 139 type any
        //         from 139 to 144 target 139 type any
        //         from 12 to 160 target 163 type any
        //         from 163 to 167 target 163 type any
    }

  public int selectNow() {
        return poll(0L);
    }

  public int select(long arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: lload_1
        //      2: lconst_0
        //      3: lcmp
        //      4: ifle  11 (offset +7)
        //      7: lload_1
        //      8: goto  14 (offset +6)
        //     11: ldc2_w  #30 // -1L
        //     14: invokespecial  #82 // jnr.enxio.channels.PollSelector.poll:(J)I
        //     17: ireturn
    }

  public int select() {
        return poll(-1L);
    }

  private int poll(long arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #76 // jnr.enxio.channels.PollSelector.cancelledKeys:()Ljava/util/Set;
        //      4: astore_3
        //      5: aload_3
        //      6: dup
        //      7: astore  4
        //      9: monitorenter
        //     10: aload_3
        //     11: invokeinterface  #98 // java.util.Set.iterator:()Ljava/util/Iterator;, count 1
        //     16: astore  5
        //     18: aload  5
        //     20: invokeinterface  #89 // java.util.Iterator.hasNext:()Z, count 1
        //     25: ifeq  52 (offset +27)
        //     28: aload  5
        //     30: invokeinterface  #90 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     35: checkcast  #14 // java.nio.channels.SelectionKey
        //     38: astore  6
        //     40: aload_0
        //     41: aload  6
        //     43: checkcast  #27 // jnr.enxio.channels.PollSelectionKey
        //     46: invokespecial  #86 // jnr.enxio.channels.PollSelector.remove:(Ljnr/enxio/channels/PollSelectionKey;)V
        //     49: goto  18 (offset -31)
        //     52: aload_3
        //     53: invokeinterface  #96 // java.util.Set.clear:()V, count 1
        //     58: aload  4
        //     60: monitorexit
        //     61: goto  72 (offset +11)
        //     64: astore  7
        //     66: aload  4
        //     68: monitorexit
        //     69: aload  7
        //     71: athrow
        //     72: iconst_0
        //     73: istore  4
        //     75: aload_0
        //     76: invokevirtual  #75 // jnr.enxio.channels.PollSelector.begin:()V
        //     79: invokestatic  #64 // jnr.enxio.channels.Native.libc:()Ljnr/enxio/channels/Native$LibC;
        //     82: aload_0
        //     83: getfield  #37 // jnr.enxio.channels.PollSelector.pollData:Ljava/nio/ByteBuffer;
        //     86: aload_0
        //     87: getfield  #35 // jnr.enxio.channels.PollSelector.nfds:I
        //     90: lload_1
        //     91: l2i
        //     92: invokeinterface  #101 // jnr.enxio.channels.Native$LibC.poll:(Ljava/nio/ByteBuffer;II)I, count 4
        //     97: istore  4
        //     99: iload  4
        //    101: ifge  123 (offset +22)
        //    104: getstatic  #32 // jnr.constants.platform.Errno.EINTR:Ljnr/constants/platform/Errno;
        //    107: invokestatic  #63 // jnr.enxio.channels.Native.getRuntime:()Ljnr/ffi/Runtime;
        //    110: invokevirtual  #88 // jnr.ffi.Runtime.getLastError:()I
        //    113: i2l
        //    114: invokestatic  #61 // jnr.constants.platform.Errno.valueOf:(J)Ljnr/constants/platform/Errno;
        //    117: invokevirtual  #60 // jnr.constants.platform.Errno.equals:(Ljava/lang/Object;)Z
        //    120: ifne  79 (offset -41)
        //    123: aload_0
        //    124: invokevirtual  #78 // jnr.enxio.channels.PollSelector.end:()V
        //    127: goto  139 (offset +12)
        //    130: astore  8
        //    132: aload_0
        //    133: invokevirtual  #78 // jnr.enxio.channels.PollSelector.end:()V
        //    136: aload  8
        //    138: athrow
        //    139: iload  4
        //    141: iconst_1
        //    142: if_icmpge  148 (offset +6)
        //    145: iload  4
        //    147: ireturn
        //    148: aload_0
        //    149: iconst_0
        //    150: invokespecial  #81 // jnr.enxio.channels.PollSelector.getPollRevents:(I)S
        //    153: iconst_1
        //    154: iand
        //    155: ifeq  162 (offset +7)
        //    158: aload_0
        //    159: invokespecial  #87 // jnr.enxio.channels.PollSelector.wakeupReceived:()V
        //    162: iconst_0
        //    163: istore  5
        //    165: aload_0
        //    166: getfield  #34 // jnr.enxio.channels.PollSelector.keys:Ljava/util/Map;
        //    169: invokeinterface  #92 // java.util.Map.keySet:()Ljava/util/Set;, count 1
        //    174: invokeinterface  #98 // java.util.Set.iterator:()Ljava/util/Iterator;, count 1
        //    179: astore  6
        //    181: aload  6
        //    183: invokeinterface  #89 // java.util.Iterator.hasNext:()Z, count 1
        //    188: ifeq  334 (offset +146)
        //    191: aload  6
        //    193: invokeinterface  #90 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    198: checkcast  #14 // java.nio.channels.SelectionKey
        //    201: astore  7
        //    203: aload  7
        //    205: checkcast  #27 // jnr.enxio.channels.PollSelectionKey
        //    208: astore  8
        //    210: aload_0
        //    211: aload  8
        //    213: invokevirtual  #70 // jnr.enxio.channels.PollSelectionKey.getIndex:()I
        //    216: invokespecial  #81 // jnr.enxio.channels.PollSelector.getPollRevents:(I)S
        //    219: istore  9
        //    221: iload  9
        //    223: ifeq  331 (offset +108)
        //    226: aload_0
        //    227: aload  8
        //    229: invokevirtual  #70 // jnr.enxio.channels.PollSelectionKey.getIndex:()I
        //    232: iconst_0
        //    233: invokespecial  #85 // jnr.enxio.channels.PollSelector.putPollRevents:(II)V
        //    236: aload  7
        //    238: invokevirtual  #54 // java.nio.channels.SelectionKey.interestOps:()I
        //    241: istore  10
        //    243: iconst_0
        //    244: istore  11
        //    246: iload  9
        //    248: iconst_1
        //    249: iand
        //    250: ifeq  263 (offset +13)
        //    253: iload  11
        //    255: iload  10
        //    257: bipush  17
        //    259: iand
        //    260: ior
        //    261: istore  11
        //    263: iload  9
        //    265: iconst_4
        //    266: iand
        //    267: ifeq  280 (offset +13)
        //    270: iload  11
        //    272: iload  10
        //    274: bipush  12
        //    276: iand
        //    277: ior
        //    278: istore  11
        //    280: iload  9
        //    282: bipush  24
        //    284: iand
        //    285: ifeq  292 (offset +7)
        //    288: iload  10
        //    290: istore  11
        //    292: aload  7
        //    294: checkcast  #27 // jnr.enxio.channels.PollSelectionKey
        //    297: iload  11
        //    299: invokevirtual  #72 // jnr.enxio.channels.PollSelectionKey.readyOps:(I)V
        //    302: iinc  5, 1
        //    305: aload_0
        //    306: getfield  #39 // jnr.enxio.channels.PollSelector.selected:Ljava/util/Set;
        //    309: aload  7
        //    311: invokeinterface  #97 // java.util.Set.contains:(Ljava/lang/Object;)Z, count 2
        //    316: ifne  331 (offset +15)
        //    319: aload_0
        //    320: getfield  #39 // jnr.enxio.channels.PollSelector.selected:Ljava/util/Set;
        //    323: aload  7
        //    325: invokeinterface  #95 // java.util.Set.add:(Ljava/lang/Object;)Z, count 2
        //    330: pop
        //    331: goto  181 (offset -150)
        //    334: iload  5
        //    336: ireturn
        //       Exception table:
        //         from 10 to 61 target 64 type any
        //         from 64 to 69 target 64 type any
        //         from 75 to 123 target 130 type any
        //         from 130 to 132 target 130 type any
    }

  private void wakeupReceived() {
        Native.read(pipefd[0], ByteBuffer.allocate(1));
    }

  public Selector wakeup() {
        try {
            Native.write(pipefd[1], ByteBuffer.allocate(1));
        } catch (IOException var1) {
            throw new RuntimeException(var1);
        }
    }

}