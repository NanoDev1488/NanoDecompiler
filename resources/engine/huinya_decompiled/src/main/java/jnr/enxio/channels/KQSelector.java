// исходный (обфусцированный) внутренний класс: jnr.enxio.channels.KQSelector
package jnr.enxio.channels;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.AbstractSelector;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import jnr.constants.platform.Errno;
import jnr.enxio.channels.KQSelectionKey;
import jnr.enxio.channels.KQSelector_Descriptor;
import jnr.enxio.channels.KQSelector_EventIO;
import jnr.enxio.channels.Native;
import jnr.enxio.channels.NativeSelectorProvider;
import jnr.enxio.channels.Native_LibC;
import jnr.enxio.channels.Native_Timespec;
import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.jffi.NativeRuntime;

class KQSelector extends AbstractSelector {

    // ---- поля ----
  private static final boolean DEBUG = false;
  private static final int MAX_EVENTS = 100;
  private static final int EVFILT_READ = -1;
  private static final int EVFILT_WRITE = -2;
  private static final int EV_ADD = 1;
  private static final int EV_DELETE = 2;
  private static final int EV_ENABLE = 4;
  private static final int EV_DISABLE = 8;
  private static final int EV_CLEAR = 32;
  private int kqfd;
  private final Runtime runtime;
  private final Pointer changebuf;
  private final Pointer eventbuf;
  private final KQSelector_EventIO io;
  private final int[] pipefd;
  private final Object regLock;
  private final Map descriptors;
  private final Set selected;
  private final Native_Timespec ZERO_TIMESPEC;

  public KQSelector(NativeSelectorProvider arg0) { // было: <init>
        super(arg0);
        kqfd = -1;
        runtime = NativeRuntime.getSystemRuntime();
        io = KQSelector_EventIO.getInstance();
        pipefd = new int[]{-1, -1};
        regLock = new Object();
        descriptors = new ConcurrentHashMap();
        selected = new LinkedHashSet();
        ZERO_TIMESPEC = new Native_Timespec(0L, 0L);
        changebuf = Memory.allocateDirect(runtime, 100 * io.size());
        eventbuf = Memory.allocateDirect(runtime, 100 * io.size());
        Native.libc().pipe(pipefd);
        kqfd = Native.libc().kqueue();
        io.put(changebuf, 0, pipefd[0], -1, 1);
        Native.libc().kevent(kqfd, changebuf, 1, null, 0, ZERO_TIMESPEC);
    }

  protected void implCloseSelector() {
        if (kqfd != -1) {
            Native.close(kqfd);
        }
        if (pipefd[0] != -1) {
            Native.close(pipefd[0]);
        }
        if (pipefd[1] != -1) {
            Native.close(pipefd[1]);
        }
        kqfd = -1;
        pipefd[1] = -1;
        pipefd[0] = -1;
        Iterator var1 = descriptors.entrySet().iterator();
        while (var1.hasNext()) {
            Entry var2 = ((Entry) var1.next());
            Iterator var3 = KQSelector_Descriptor.access$000(((KQSelector_Descriptor) var2.getValue())).iterator();
            while (var3.hasNext()) {
                KQSelectionKey var4 = ((KQSelectionKey) var3.next());
                deregister(var4);
                continue;
            }
            continue;
        }
    }

  protected SelectionKey register(AbstractSelectableChannel arg0, int arg1, Object arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #25 // jnr.enxio.channels.KQSelectionKey
        //      3: dup
        //      4: aload_0
        //      5: aload_1
        //      6: checkcast  #36 // jnr.enxio.channels.NativeSelectableChannel
        //      9: iload_2
        //     10: invokespecial  #68 // jnr.enxio.channels.KQSelectionKey.<init>:(Ljnr/enxio/channels/KQSelector;Ljnr/enxio/channels/NativeSelectableChannel;I)V
        //     13: astore  4
        //     15: aload_0
        //     16: getfield  #53 // jnr.enxio.channels.KQSelector.regLock:Ljava/lang/Object;
        //     19: dup
        //     20: astore  5
        //     22: monitorenter
        //     23: new  #28 // jnr.enxio.channels.KQSelector$Descriptor
        //     26: dup
        //     27: aload  4
        //     29: invokevirtual  #70 // jnr.enxio.channels.KQSelectionKey.getFD:()I
        //     32: invokespecial  #81 // jnr.enxio.channels.KQSelector$Descriptor.<init>:(I)V
        //     35: astore  6
        //     37: aload_0
        //     38: getfield  #48 // jnr.enxio.channels.KQSelector.descriptors:Ljava/util/Map;
        //     41: aload  4
        //     43: invokevirtual  #70 // jnr.enxio.channels.KQSelectionKey.getFD:()I
        //     46: invokestatic  #57 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //     49: aload  6
        //     51: invokeinterface  #105 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //     56: pop
        //     57: aload  6
        //     59: invokestatic  #82 // jnr.enxio.channels.KQSelector$Descriptor.access$000:(Ljnr/enxio/channels/KQSelector$Descriptor;)Ljava/util/Set;
        //     62: aload  4
        //     64: invokeinterface  #109 // java.util.Set.add:(Ljava/lang/Object;)Z, count 2
        //     69: pop
        //     70: aload_0
        //     71: aload  6
        //     73: invokespecial  #78 // jnr.enxio.channels.KQSelector.handleChangedKey:(Ljnr/enxio/channels/KQSelector$Descriptor;)V
        //     76: aload  5
        //     78: monitorexit
        //     79: goto  90 (offset +11)
        //     82: astore  7
        //     84: aload  5
        //     86: monitorexit
        //     87: aload  7
        //     89: athrow
        //     90: aload  4
        //     92: aload_3
        //     93: invokevirtual  #69 // jnr.enxio.channels.KQSelectionKey.attach:(Ljava/lang/Object;)Ljava/lang/Object;
        //     96: pop
        //     97: aload  4
        //     99: areturn
        //       Exception table:
        //         from 23 to 79 target 82 type any
        //         from 82 to 87 target 82 type any
    }

  public Set keys() {
        HashSet var1 = new HashSet();
        Iterator var2 = descriptors.values().iterator();
        while (var2.hasNext()) {
            KQSelector_Descriptor var3 = ((KQSelector_Descriptor) var2.next());
            var1.addAll(KQSelector_Descriptor.access$000(var3));
            continue;
        }
        return Collections.unmodifiableSet(var1);
    }

  public Set selectedKeys() {
        return selected;
    }

  public int selectNow() {
        return poll(0L);
    }

  public int select(long arg0) {
        return poll(arg0);
    }

  public int select() {
        return poll(-1L);
    }

  private int poll(long arg0) {
        int var3 = handleCancelledKeys();
        Object var4 = null;
        if (arg0 >= 0L) {
            long var5 = TimeUnit.MILLISECONDS.toSeconds(arg0);
            long var7 = TimeUnit.MILLISECONDS.toNanos(arg0 % 1000L);
            var4 = new Native_Timespec(var5, var7);
        }
        int var5 = 0;
        try {
            begin();
            do {
                var5 = Native.libc().kevent(kqfd, changebuf, var3, eventbuf, 100, ((Native_Timespec) var4));
                if (var5 >= 0) {
                    break;
                }
            } while (Errno.EINTR.equals(Errno.valueOf(((long) Native.getRuntime().getLastError()))));
        } catch (Throwable e2) {
            try {
                while (true) {
                    Throwable var9 = e2;
                }
            } catch (Throwable var9) {
            }
        }
    }

  private int handleCancelledKeys() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #74 // jnr.enxio.channels.KQSelector.cancelledKeys:()Ljava/util/Set;
        //      4: astore_1
        //      5: aload_1
        //      6: dup
        //      7: astore_2
        //      8: monitorenter
        //      9: iconst_0
        //     10: istore_3
        //     11: aload_0
        //     12: getfield  #53 // jnr.enxio.channels.KQSelector.regLock:Ljava/lang/Object;
        //     15: dup
        //     16: astore  4
        //     18: monitorenter
        //     19: aload_1
        //     20: invokeinterface  #114 // java.util.Set.iterator:()Ljava/util/Iterator;, count 1
        //     25: astore  5
        //     27: aload  5
        //     29: invokeinterface  #101 // java.util.Iterator.hasNext:()Z, count 1
        //     34: ifeq  252 (offset +218)
        //     37: aload  5
        //     39: invokeinterface  #102 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     44: checkcast  #12 // java.nio.channels.SelectionKey
        //     47: astore  6
        //     49: aload  6
        //     51: checkcast  #25 // jnr.enxio.channels.KQSelectionKey
        //     54: astore  7
        //     56: aload_0
        //     57: aload  7
        //     59: invokevirtual  #75 // jnr.enxio.channels.KQSelector.deregister:(Ljava/nio/channels/spi/AbstractSelectionKey;)V
        //     62: aload_0
        //     63: getfield  #55 // jnr.enxio.channels.KQSelector.selected:Ljava/util/Set;
        //     66: dup
        //     67: astore  8
        //     69: monitorenter
        //     70: aload_0
        //     71: getfield  #55 // jnr.enxio.channels.KQSelector.selected:Ljava/util/Set;
        //     74: aload  7
        //     76: invokeinterface  #115 // java.util.Set.remove:(Ljava/lang/Object;)Z, count 2
        //     81: pop
        //     82: aload  8
        //     84: monitorexit
        //     85: goto  96 (offset +11)
        //     88: astore  9
        //     90: aload  8
        //     92: monitorexit
        //     93: aload  9
        //     95: athrow
        //     96: aload_0
        //     97: getfield  #48 // jnr.enxio.channels.KQSelector.descriptors:Ljava/util/Map;
        //    100: aload  7
        //    102: invokevirtual  #70 // jnr.enxio.channels.KQSelectionKey.getFD:()I
        //    105: invokestatic  #57 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    108: invokeinterface  #104 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    113: checkcast  #28 // jnr.enxio.channels.KQSelector$Descriptor
        //    116: astore  8
        //    118: aload  8
        //    120: ifnull  136 (offset +16)
        //    123: aload  8
        //    125: invokestatic  #82 // jnr.enxio.channels.KQSelector$Descriptor.access$000:(Ljnr/enxio/channels/KQSelector$Descriptor;)Ljava/util/Set;
        //    128: aload  7
        //    130: invokeinterface  #115 // java.util.Set.remove:(Ljava/lang/Object;)Z, count 2
        //    135: pop
        //    136: aload  8
        //    138: ifnull  154 (offset +16)
        //    141: aload  8
        //    143: invokestatic  #82 // jnr.enxio.channels.KQSelector$Descriptor.access$000:(Ljnr/enxio/channels/KQSelector$Descriptor;)Ljava/util/Set;
        //    146: invokeinterface  #113 // java.util.Set.isEmpty:()Z, count 1
        //    151: ifeq  217 (offset +66)
        //    154: aload_0
        //    155: getfield  #50 // jnr.enxio.channels.KQSelector.io:Ljnr/enxio/channels/KQSelector$EventIO;
        //    158: aload_0
        //    159: getfield  #47 // jnr.enxio.channels.KQSelector.changebuf:Ljnr/ffi/Pointer;
        //    162: iload_3
        //    163: iinc  3, 1
        //    166: aload  7
        //    168: invokevirtual  #70 // jnr.enxio.channels.KQSelectionKey.getFD:()I
        //    171: iconst_m1
        //    172: iconst_2
        //    173: invokevirtual  #91 // jnr.enxio.channels.KQSelector$EventIO.put:(Ljnr/ffi/Pointer;IIII)V
        //    176: aload_0
        //    177: getfield  #50 // jnr.enxio.channels.KQSelector.io:Ljnr/enxio/channels/KQSelector$EventIO;
        //    180: aload_0
        //    181: getfield  #47 // jnr.enxio.channels.KQSelector.changebuf:Ljnr/ffi/Pointer;
        //    184: iload_3
        //    185: iinc  3, 1
        //    188: aload  7
        //    190: invokevirtual  #70 // jnr.enxio.channels.KQSelectionKey.getFD:()I
        //    193: bipush  -2
        //    195: iconst_2
        //    196: invokevirtual  #91 // jnr.enxio.channels.KQSelector$EventIO.put:(Ljnr/ffi/Pointer;IIII)V
        //    199: aload_0
        //    200: getfield  #48 // jnr.enxio.channels.KQSelector.descriptors:Ljava/util/Map;
        //    203: aload  7
        //    205: invokevirtual  #70 // jnr.enxio.channels.KQSelectionKey.getFD:()I
        //    208: invokestatic  #57 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    211: invokeinterface  #106 // java.util.Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    216: pop
        //    217: iload_3
        //    218: bipush  100
        //    220: if_icmplt  249 (offset +29)
        //    223: invokestatic  #95 // jnr.enxio.channels.Native.libc:()Ljnr/enxio/channels/Native$LibC;
        //    226: aload_0
        //    227: getfield  #51 // jnr.enxio.channels.KQSelector.kqfd:I
        //    230: aload_0
        //    231: getfield  #47 // jnr.enxio.channels.KQSelector.changebuf:Ljnr/ffi/Pointer;
        //    234: iload_3
        //    235: aconst_null
        //    236: iconst_0
        //    237: aload_0
        //    238: getfield  #46 // jnr.enxio.channels.KQSelector.ZERO_TIMESPEC:Ljnr/enxio/channels/Native$Timespec;
        //    241: invokeinterface  #116 // jnr.enxio.channels.Native$LibC.kevent:(ILjnr/ffi/Pointer;ILjnr/ffi/Pointer;ILjnr/enxio/channels/Native$Timespec;)I, count 7
        //    246: pop
        //    247: iconst_0
        //    248: istore_3
        //    249: goto  27 (offset -222)
        //    252: aload  4
        //    254: monitorexit
        //    255: goto  266 (offset +11)
        //    258: astore  10
        //    260: aload  4
        //    262: monitorexit
        //    263: aload  10
        //    265: athrow
        //    266: aload_1
        //    267: invokeinterface  #111 // java.util.Set.clear:()V, count 1
        //    272: iload_3
        //    273: aload_2
        //    274: monitorexit
        //    275: ireturn
        //    276: astore  11
        //    278: aload_2
        //    279: monitorexit
        //    280: aload  11
        //    282: athrow
        //       Exception table:
        //         from 70 to 85 target 88 type any
        //         from 88 to 93 target 88 type any
        //         from 19 to 255 target 258 type any
        //         from 258 to 263 target 258 type any
        //         from 9 to 275 target 276 type any
        //         from 276 to 280 target 276 type any
    }

  private void handleChangedKey(KQSelector_Descriptor arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #53 // jnr.enxio.channels.KQSelector.regLock:Ljava/lang/Object;
        //      4: dup
        //      5: astore_2
        //      6: monitorenter
        //      7: iconst_0
        //      8: istore_3
        //      9: iconst_0
        //     10: istore  4
        //     12: iconst_0
        //     13: istore  5
        //     15: aload_1
        //     16: invokestatic  #82 // jnr.enxio.channels.KQSelector$Descriptor.access$000:(Ljnr/enxio/channels/KQSelector$Descriptor;)Ljava/util/Set;
        //     19: invokeinterface  #114 // java.util.Set.iterator:()Ljava/util/Iterator;, count 1
        //     24: astore  6
        //     26: aload  6
        //     28: invokeinterface  #101 // java.util.Iterator.hasNext:()Z, count 1
        //     33: ifeq  79 (offset +46)
        //     36: aload  6
        //     38: invokeinterface  #102 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //     43: checkcast  #25 // jnr.enxio.channels.KQSelectionKey
        //     46: astore  7
        //     48: aload  7
        //     50: invokevirtual  #71 // jnr.enxio.channels.KQSelectionKey.interestOps:()I
        //     53: bipush  17
        //     55: iand
        //     56: ifeq  62 (offset +6)
        //     59: iinc  5, 1
        //     62: aload  7
        //     64: invokevirtual  #71 // jnr.enxio.channels.KQSelectionKey.interestOps:()I
        //     67: bipush  12
        //     69: iand
        //     70: ifeq  76 (offset +6)
        //     73: iinc  4, 1
        //     76: goto  26 (offset -50)
        //     79: iconst_2
        //     80: anewarray  #10 // java.lang.Integer
        //     83: dup
        //     84: iconst_0
        //     85: iconst_m1
        //     86: invokestatic  #57 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //     89: aastore
        //     90: dup
        //     91: iconst_1
        //     92: bipush  -2
        //     94: invokestatic  #57 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //     97: aastore
        //     98: astore  6
        //    100: aload  6
        //    102: arraylength
        //    103: istore  7
        //    105: iconst_0
        //    106: istore  8
        //    108: iload  8
        //    110: iload  7
        //    112: if_icmpge  275 (offset +163)
        //    115: aload  6
        //    117: iload  8
        //    119: aaload
        //    120: astore  9
        //    122: iconst_0
        //    123: istore  10
        //    125: aload  9
        //    127: invokevirtual  #56 // java.lang.Integer.intValue:()I
        //    130: iconst_m1
        //    131: if_icmpne  181 (offset +50)
        //    134: iload  5
        //    136: ifle  159 (offset +23)
        //    139: aload_1
        //    140: invokestatic  #83 // jnr.enxio.channels.KQSelector$Descriptor.access$100:(Ljnr/enxio/channels/KQSelector$Descriptor;)Z
        //    143: ifne  159 (offset +16)
        //    146: bipush  37
        //    148: istore  10
        //    150: aload_1
        //    151: iconst_1
        //    152: invokestatic  #84 // jnr.enxio.channels.KQSelector$Descriptor.access$102:(Ljnr/enxio/channels/KQSelector$Descriptor;Z)Z
        //    155: pop
        //    156: goto  181 (offset +25)
        //    159: iload  5
        //    161: ifne  181 (offset +20)
        //    164: aload_1
        //    165: invokestatic  #83 // jnr.enxio.channels.KQSelector$Descriptor.access$100:(Ljnr/enxio/channels/KQSelector$Descriptor;)Z
        //    168: ifeq  181 (offset +13)
        //    171: bipush  8
        //    173: istore  10
        //    175: aload_1
        //    176: iconst_0
        //    177: invokestatic  #84 // jnr.enxio.channels.KQSelector$Descriptor.access$102:(Ljnr/enxio/channels/KQSelector$Descriptor;Z)Z
        //    180: pop
        //    181: aload  9
        //    183: invokevirtual  #56 // java.lang.Integer.intValue:()I
        //    186: bipush  -2
        //    188: if_icmpne  238 (offset +50)
        //    191: iload  4
        //    193: ifle  216 (offset +23)
        //    196: aload_1
        //    197: invokestatic  #85 // jnr.enxio.channels.KQSelector$Descriptor.access$200:(Ljnr/enxio/channels/KQSelector$Descriptor;)Z
        //    200: ifne  216 (offset +16)
        //    203: bipush  37
        //    205: istore  10
        //    207: aload_1
        //    208: iconst_1
        //    209: invokestatic  #86 // jnr.enxio.channels.KQSelector$Descriptor.access$202:(Ljnr/enxio/channels/KQSelector$Descriptor;Z)Z
        //    212: pop
        //    213: goto  238 (offset +25)
        //    216: iload  4
        //    218: ifne  238 (offset +20)
        //    221: aload_1
        //    222: invokestatic  #85 // jnr.enxio.channels.KQSelector$Descriptor.access$200:(Ljnr/enxio/channels/KQSelector$Descriptor;)Z
        //    225: ifeq  238 (offset +13)
        //    228: bipush  8
        //    230: istore  10
        //    232: aload_1
        //    233: iconst_0
        //    234: invokestatic  #86 // jnr.enxio.channels.KQSelector$Descriptor.access$202:(Ljnr/enxio/channels/KQSelector$Descriptor;Z)Z
        //    237: pop
        //    238: iload  10
        //    240: ifeq  269 (offset +29)
        //    243: aload_0
        //    244: getfield  #50 // jnr.enxio.channels.KQSelector.io:Ljnr/enxio/channels/KQSelector$EventIO;
        //    247: aload_0
        //    248: getfield  #47 // jnr.enxio.channels.KQSelector.changebuf:Ljnr/ffi/Pointer;
        //    251: iload_3
        //    252: iinc  3, 1
        //    255: aload_1
        //    256: invokestatic  #87 // jnr.enxio.channels.KQSelector$Descriptor.access$300:(Ljnr/enxio/channels/KQSelector$Descriptor;)I
        //    259: aload  9
        //    261: invokevirtual  #56 // java.lang.Integer.intValue:()I
        //    264: iload  10
        //    266: invokevirtual  #91 // jnr.enxio.channels.KQSelector$EventIO.put:(Ljnr/ffi/Pointer;IIII)V
        //    269: iinc  8, 1
        //    272: goto  108 (offset -164)
        //    275: invokestatic  #95 // jnr.enxio.channels.Native.libc:()Ljnr/enxio/channels/Native$LibC;
        //    278: aload_0
        //    279: getfield  #51 // jnr.enxio.channels.KQSelector.kqfd:I
        //    282: aload_0
        //    283: getfield  #47 // jnr.enxio.channels.KQSelector.changebuf:Ljnr/ffi/Pointer;
        //    286: iload_3
        //    287: aconst_null
        //    288: iconst_0
        //    289: aload_0
        //    290: getfield  #46 // jnr.enxio.channels.KQSelector.ZERO_TIMESPEC:Ljnr/enxio/channels/Native$Timespec;
        //    293: invokeinterface  #116 // jnr.enxio.channels.Native$LibC.kevent:(ILjnr/ffi/Pointer;ILjnr/ffi/Pointer;ILjnr/enxio/channels/Native$Timespec;)I, count 7
        //    298: pop
        //    299: aload_2
        //    300: monitorexit
        //    301: goto  311 (offset +10)
        //    304: astore  11
        //    306: aload_2
        //    307: monitorexit
        //    308: aload  11
        //    310: athrow
        //    311: return
        //       Exception table:
        //         from 7 to 301 target 304 type any
        //         from 304 to 308 target 304 type any
    }

  private void wakeupReceived() {
        Native.libc().read(pipefd[0], new byte[1], 1L);
    }

  public Selector wakeup() {
        Native.libc().write(pipefd[1], new byte[1], 1L);
        return this;
    }

   void interestOps(KQSelectionKey arg0, int arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #53 // jnr.enxio.channels.KQSelector.regLock:Ljava/lang/Object;
        //      4: dup
        //      5: astore_3
        //      6: monitorenter
        //      7: aload_0
        //      8: aload_0
        //      9: getfield  #48 // jnr.enxio.channels.KQSelector.descriptors:Ljava/util/Map;
        //     12: aload_1
        //     13: invokevirtual  #70 // jnr.enxio.channels.KQSelectionKey.getFD:()I
        //     16: invokestatic  #57 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //     19: invokeinterface  #104 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     24: checkcast  #28 // jnr.enxio.channels.KQSelector$Descriptor
        //     27: invokespecial  #78 // jnr.enxio.channels.KQSelector.handleChangedKey:(Ljnr/enxio/channels/KQSelector$Descriptor;)V
        //     30: aload_3
        //     31: monitorexit
        //     32: goto  42 (offset +10)
        //     35: astore  4
        //     37: aload_3
        //     38: monitorexit
        //     39: aload  4
        //     41: athrow
        //     42: return
        //       Exception table:
        //         from 7 to 32 target 35 type any
        //         from 35 to 39 target 35 type any
    }

}