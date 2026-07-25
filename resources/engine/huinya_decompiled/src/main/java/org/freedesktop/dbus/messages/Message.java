// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.messages.Message
package org.freedesktop.dbus.messages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.freedesktop.dbus.ArrayFrob;
import org.freedesktop.dbus.Marshalling;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.exceptions.MarshallingException;
import org.freedesktop.dbus.messages.Error;
import org.freedesktop.dbus.messages.Message_ExtractMethod;
import org.freedesktop.dbus.types.Variant;
import org.freedesktop.dbus.utils.Hexdump;
import org.freedesktop.dbus.utils.LoggingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Message {

    // ---- поля ----
  public static final int MAXIMUM_ARRAY_LENGTH = 67108864;
  public static final int MAXIMUM_MESSAGE_LENGTH = 134217728;
  public static final int MAXIMUM_NUM_UNIX_FDS = 33554432;
  public static final byte PROTOCOL = 1;
  private static final int OFFSET_DATA = 1;
  private static final int OFFSET_SIG = 0;
  private static byte[][] padding;
  private static final int BUFFERINCREMENT = 20;
  private static final AtomicLong GLOBAL_SERIAL;
  protected final Logger logger;
  private final List filedescriptors;
  private final Object[] headers;
  private byte[][] wiredata;
  private long bytecounter;
  private long serial;
  private byte type;
  private byte flags;
  private byte protover;
  private boolean big;
  private Object[] args;
  private byte[] body;
  private long bodylen;
  private int preallocated;
  private int paofs;
  private byte[] pabuf;
  private int bufferuse;
  private boolean endianWasSet;

    static {
        padding = new byte[][]{null, new byte[1], new byte[2], new byte[3], new byte[4], new byte[5], new byte[6], new byte[7]};
        GLOBAL_SERIAL = new AtomicLong(0L);
    }

  protected Message(byte arg0, byte arg1, byte arg2) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokespecial  #242 // org.freedesktop.dbus.messages.Message.<init>:()V
        //      4: aload_0
        //      5: bipush  66
        //      7: iload_1
        //      8: if_icmpne  15 (offset +7)
        //     11: iconst_1
        //     12: goto  16 (offset +4)
        //     15: iconst_0
        //     16: putfield  #142 // org.freedesktop.dbus.messages.Message.big:Z
        //     19: aload_0
        //     20: getstatic  #140 // org.freedesktop.dbus.messages.Message.GLOBAL_SERIAL:Ljava/util/concurrent/atomic/AtomicLong;
        //     23: invokevirtual  #228 // java.util.concurrent.atomic.AtomicLong.incrementAndGet:()J
        //     26: invokevirtual  #293 // org.freedesktop.dbus.messages.Message.setSerial:(J)V
        //     29: aload_0
        //     30: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //     33: ldc  #20 // 'Creating message with serial {}'
        //     35: aload_0
        //     36: invokevirtual  #270 // org.freedesktop.dbus.messages.Message.getSerial:()J
        //     39: invokestatic  #180 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //     42: invokeinterface  #332 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //     47: aload_0
        //     48: iload_2
        //     49: putfield  #158 // org.freedesktop.dbus.messages.Message.type:B
        //     52: aload_0
        //     53: iload_3
        //     54: putfield  #149 // org.freedesktop.dbus.messages.Message.flags:B
        //     57: aload_0
        //     58: iconst_4
        //     59: invokevirtual  #290 // org.freedesktop.dbus.messages.Message.preallocate:(I)V
        //     62: aload_0
        //     63: iload_1
        //     64: ifeq  71 (offset +7)
        //     67: iconst_1
        //     68: goto  72 (offset +4)
        //     71: iconst_0
        //     72: putfield  #147 // org.freedesktop.dbus.messages.Message.endianWasSet:Z
        //     75: aload_0
        //     76: ldc  #63 // 'yyyy'
        //     78: iconst_4
        //     79: anewarray  #89 // java.lang.Object
        //     82: dup
        //     83: iconst_0
        //     84: iload_1
        //     85: invokestatic  #163 // java.lang.Byte.valueOf:(B)Ljava/lang/Byte;
        //     88: aastore
        //     89: dup
        //     90: iconst_1
        //     91: iload_2
        //     92: invokestatic  #163 // java.lang.Byte.valueOf:(B)Ljava/lang/Byte;
        //     95: aastore
        //     96: dup
        //     97: iconst_2
        //     98: iload_3
        //     99: invokestatic  #163 // java.lang.Byte.valueOf:(B)Ljava/lang/Byte;
        //    102: aastore
        //    103: dup
        //    104: iconst_3
        //    105: iconst_1
        //    106: invokestatic  #163 // java.lang.Byte.valueOf:(B)Ljava/lang/Byte;
        //    109: aastore
        //    110: invokevirtual  #244 // org.freedesktop.dbus.messages.Message.append:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    113: return
    }

  protected Message() { // было: <init>
        super();
        logger = LoggerFactory.getLogger(getClass());
        filedescriptors = new ArrayList();
        headers = new Object[10];
        wiredata = new byte[20][];
        bytecounter = 0L;
        bodylen = 0L;
        preallocated = 0;
        paofs = 0;
        bufferuse = 0;
    }

  public void updateEndianess(byte arg0) {
        if (!endianWasSet) {
            if (wiredata[0] == null) {
                wiredata[0] = new byte[]{arg0, 0, 0, 0};
            } else {
                wiredata[0][0] = arg0;
            }
            endianWasSet = true;
            return;
        } else {
            return;
        }
    }

   void populate(byte[] arg0, byte[] arg1, byte[] arg2, List arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: arraylength
        //      2: newarray  byte
        //      4: astore  5
        //      6: aload_1
        //      7: iconst_0
        //      8: aload  5
        //     10: iconst_0
        //     11: aload_1
        //     12: arraylength
        //     13: invokestatic  #208 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //     16: aload_2
        //     17: arraylength
        //     18: newarray  byte
        //     20: astore  6
        //     22: aload_2
        //     23: iconst_0
        //     24: aload  6
        //     26: iconst_0
        //     27: aload_2
        //     28: arraylength
        //     29: invokestatic  #208 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //     32: aload_3
        //     33: arraylength
        //     34: newarray  byte
        //     36: astore  7
        //     38: aload_3
        //     39: iconst_0
        //     40: aload  7
        //     42: iconst_0
        //     43: aload_3
        //     44: arraylength
        //     45: invokestatic  #208 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //     48: aload_0
        //     49: iconst_1
        //     50: putfield  #147 // org.freedesktop.dbus.messages.Message.endianWasSet:Z
        //     53: aload_0
        //     54: aload  5
        //     56: iconst_0
        //     57: baload
        //     58: bipush  66
        //     60: if_icmpne  67 (offset +7)
        //     63: iconst_1
        //     64: goto  68 (offset +4)
        //     67: iconst_0
        //     68: putfield  #142 // org.freedesktop.dbus.messages.Message.big:Z
        //     71: aload_0
        //     72: aload  5
        //     74: iconst_1
        //     75: baload
        //     76: putfield  #158 // org.freedesktop.dbus.messages.Message.type:B
        //     79: aload_0
        //     80: aload  5
        //     82: iconst_2
        //     83: baload
        //     84: putfield  #149 // org.freedesktop.dbus.messages.Message.flags:B
        //     87: aload_0
        //     88: aload  5
        //     90: iconst_3
        //     91: baload
        //     92: putfield  #156 // org.freedesktop.dbus.messages.Message.protover:B
        //     95: aload_0
        //     96: getfield  #159 // org.freedesktop.dbus.messages.Message.wiredata:[[B
        //     99: iconst_0
        //    100: aload  5
        //    102: aastore
        //    103: aload_0
        //    104: getfield  #159 // org.freedesktop.dbus.messages.Message.wiredata:[[B
        //    107: iconst_1
        //    108: aload  6
        //    110: aastore
        //    111: aload_0
        //    112: getfield  #159 // org.freedesktop.dbus.messages.Message.wiredata:[[B
        //    115: iconst_2
        //    116: aload  7
        //    118: aastore
        //    119: aload_0
        //    120: aload  7
        //    122: putfield  #143 // org.freedesktop.dbus.messages.Message.body:[B
        //    125: aload_0
        //    126: iconst_3
        //    127: putfield  #145 // org.freedesktop.dbus.messages.Message.bufferuse:I
        //    130: aload_0
        //    131: aload_0
        //    132: ldc  #61 // 'u'
        //    134: aload  5
        //    136: iconst_4
        //    137: invokevirtual  #255 // org.freedesktop.dbus.messages.Message.extract:(Ljava/lang/String;[BI)[Ljava/lang/Object;
        //    140: iconst_0
        //    141: aaload
        //    142: checkcast  #88 // java.lang.Number
        //    145: invokevirtual  #185 // java.lang.Number.longValue:()J
        //    148: putfield  #144 // org.freedesktop.dbus.messages.Message.bodylen:J
        //    151: aload_0
        //    152: ldc  #61 // 'u'
        //    154: aload  5
        //    156: bipush  8
        //    158: invokevirtual  #255 // org.freedesktop.dbus.messages.Message.extract:(Ljava/lang/String;[BI)[Ljava/lang/Object;
        //    161: iconst_0
        //    162: aaload
        //    163: checkcast  #88 // java.lang.Number
        //    166: invokevirtual  #185 // java.lang.Number.longValue:()J
        //    169: lstore  8
        //    171: aload_0
        //    172: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //    175: ldc  #39 // 'Received message of type {} with serial {}'
        //    177: aload_0
        //    178: getfield  #158 // org.freedesktop.dbus.messages.Message.type:B
        //    181: invokestatic  #163 // java.lang.Byte.valueOf:(B)Ljava/lang/Byte;
        //    184: lload  8
        //    186: invokestatic  #180 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //    189: invokeinterface  #333 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    194: aload_0
        //    195: lload  8
        //    197: invokevirtual  #293 // org.freedesktop.dbus.messages.Message.setSerial:(J)V
        //    200: aload_0
        //    201: aload  5
        //    203: arraylength
        //    204: i2l
        //    205: aload  6
        //    207: arraylength
        //    208: i2l
        //    209: ladd
        //    210: aload  7
        //    212: arraylength
        //    213: i2l
        //    214: ladd
        //    215: putfield  #146 // org.freedesktop.dbus.messages.Message.bytecounter:J
        //    218: aload_0
        //    219: getfield  #148 // org.freedesktop.dbus.messages.Message.filedescriptors:Ljava/util/List;
        //    222: invokeinterface  #314 // java.util.List.clear:()V, count 1
        //    227: aload  4
        //    229: ifnull  244 (offset +15)
        //    232: aload_0
        //    233: getfield  #148 // org.freedesktop.dbus.messages.Message.filedescriptors:Ljava/util/List;
        //    236: aload  4
        //    238: invokeinterface  #313 // java.util.List.addAll:(Ljava/util/Collection;)Z, count 2
        //    243: pop
        //    244: aload_0
        //    245: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //    248: invokeinterface  #336 // org.slf4j.Logger.isTraceEnabled:()Z, count 1
        //    253: aload_0
        //    254: aload  6
        //    256: invokedynamic  #341 // invokedynamic run:(Lorg/freedesktop/dbus/messages/Message;[B)Ljava/lang/Runnable;
        //    261: invokestatic  #308 // org.freedesktop.dbus.utils.LoggingHelper.logIf:(ZLjava/lang/Runnable;)V
        //    264: aload_0
        //    265: aload  6
        //    267: invokevirtual  #260 // org.freedesktop.dbus.messages.Message.extractHeader:([B)[Ljava/lang/Object;
        //    270: astore  10
        //    272: aload_0
        //    273: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //    276: invokeinterface  #336 // org.slf4j.Logger.isTraceEnabled:()Z, count 1
        //    281: aload_0
        //    282: aload  10
        //    284: invokedynamic  #342 // invokedynamic run:(Lorg/freedesktop/dbus/messages/Message;[Ljava/lang/Object;)Ljava/lang/Runnable;
        //    289: invokestatic  #308 // org.freedesktop.dbus.utils.LoggingHelper.logIf:(ZLjava/lang/Runnable;)V
        //    292: aload  10
        //    294: iconst_0
        //    295: aaload
        //    296: checkcast  #104 // java.util.List
        //    299: astore  11
        //    301: aload  11
        //    303: invokeinterface  #316 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    308: astore  12
        //    310: aload  12
        //    312: invokeinterface  #310 // java.util.Iterator.hasNext:()Z, count 1
        //    317: ifeq  362 (offset +45)
        //    320: aload  12
        //    322: invokeinterface  #311 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    327: astore  13
        //    329: aload  13
        //    331: checkcast  #72 // [Ljava.lang.Object;
        //    334: astore  14
        //    336: aload  14
        //    338: iconst_0
        //    339: aaload
        //    340: checkcast  #79 // java.lang.Byte
        //    343: invokevirtual  #162 // java.lang.Byte.byteValue:()B
        //    346: istore  15
        //    348: aload_0
        //    349: getfield  #150 // org.freedesktop.dbus.messages.Message.headers:[Ljava/lang/Object;
        //    352: iload  15
        //    354: aload  14
        //    356: iconst_1
        //    357: aaload
        //    358: aastore
        //    359: goto  310 (offset -49)
        //    362: return
    }

  protected Object[] getHeader() {
        return headers;
    }

  protected void setHeader(Object[] arg0) {
        if (arg0 != null) {
            if (arg0.length <= headers.length) {
                System.arraycopy(arg0, 0, headers, 0, arg0.length);
                return;
            } else {
                throw new IllegalArgumentException("Given header is larger (" + arg0.length + ") than allowed header size: " + headers.length);
            }
        } else {
            return;
        }
    }

  protected long getByteCounter() {
        return bytecounter;
    }

  protected void setByteCounter(long arg0) {
        bytecounter = arg0;
    }

  protected synchronized void setSerial(long arg0) {
        serial = arg0;
    }

  protected void setWireData(byte[][] arg0) {
        wiredata = arg0;
    }

   byte getProtover() {
        return protover;
    }

   long getBodylen() {
        return bodylen;
    }

  private void preallocate(int arg0) {
        preallocated = 0;
        pabuf = new byte[arg0];
        appendBytes(pabuf);
        preallocated = arg0;
        paofs = 0;
    }

  private void ensureBuffers(int arg0) {
        int var2 = arg0 - wiredata.length + bufferuse;
        if (var2 > 0) {
            if (var2 < 20) {
                var2 = 20;
            }
            logger.trace("Resizing {}", Integer.valueOf(bufferuse));
            byte[][] var3 = new byte[wiredata.length + var2][];
            System.arraycopy(wiredata, 0, var3, 0, wiredata.length);
            wiredata = var3;
        }
    }

  protected void appendBytes(byte[] arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aconst_null
        //      1: aload_1
        //      2: if_acmpne  6 (offset +4)
        //      5: return
        //      6: aload_0
        //      7: getfield  #155 // org.freedesktop.dbus.messages.Message.preallocated:I
        //     10: ifle  114 (offset +104)
        //     13: aload_0
        //     14: getfield  #154 // org.freedesktop.dbus.messages.Message.paofs:I
        //     17: aload_1
        //     18: arraylength
        //     19: iadd
        //     20: aload_0
        //     21: getfield  #152 // org.freedesktop.dbus.messages.Message.pabuf:[B
        //     24: arraylength
        //     25: if_icmple  74 (offset +49)
        //     28: new  #77 // java.lang.ArrayIndexOutOfBoundsException
        //     31: dup
        //     32: ldc  #18 // 'Array index out of bounds, paofs={0}, pabuf.length={1}, buf.length={2}.'
        //     34: iconst_3
        //     35: anewarray  #89 // java.lang.Object
        //     38: dup
        //     39: iconst_0
        //     40: aload_0
        //     41: getfield  #154 // org.freedesktop.dbus.messages.Message.paofs:I
        //     44: invokestatic  #179 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //     47: aastore
        //     48: dup
        //     49: iconst_1
        //     50: aload_0
        //     51: getfield  #152 // org.freedesktop.dbus.messages.Message.pabuf:[B
        //     54: arraylength
        //     55: invokestatic  #179 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //     58: aastore
        //     59: dup
        //     60: iconst_2
        //     61: aload_1
        //     62: arraylength
        //     63: invokestatic  #179 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //     66: aastore
        //     67: invokestatic  #215 // java.text.MessageFormat.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //     70: invokespecial  #160 // java.lang.ArrayIndexOutOfBoundsException.<init>:(Ljava/lang/String;)V
        //     73: athrow
        //     74: aload_1
        //     75: iconst_0
        //     76: aload_0
        //     77: getfield  #152 // org.freedesktop.dbus.messages.Message.pabuf:[B
        //     80: aload_0
        //     81: getfield  #154 // org.freedesktop.dbus.messages.Message.paofs:I
        //     84: aload_1
        //     85: arraylength
        //     86: invokestatic  #208 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //     89: aload_0
        //     90: dup
        //     91: getfield  #154 // org.freedesktop.dbus.messages.Message.paofs:I
        //     94: aload_1
        //     95: arraylength
        //     96: iadd
        //     97: putfield  #154 // org.freedesktop.dbus.messages.Message.paofs:I
        //    100: aload_0
        //    101: dup
        //    102: getfield  #155 // org.freedesktop.dbus.messages.Message.preallocated:I
        //    105: aload_1
        //    106: arraylength
        //    107: isub
        //    108: putfield  #155 // org.freedesktop.dbus.messages.Message.preallocated:I
        //    111: goto  205 (offset +94)
        //    114: aload_0
        //    115: getfield  #145 // org.freedesktop.dbus.messages.Message.bufferuse:I
        //    118: aload_0
        //    119: getfield  #159 // org.freedesktop.dbus.messages.Message.wiredata:[[B
        //    122: arraylength
        //    123: if_icmpne  176 (offset +53)
        //    126: aload_0
        //    127: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //    130: ldc  #41 // 'Resizing {}'
        //    132: aload_0
        //    133: getfield  #145 // org.freedesktop.dbus.messages.Message.bufferuse:I
        //    136: invokestatic  #179 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    139: invokeinterface  #338 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    144: aload_0
        //    145: getfield  #159 // org.freedesktop.dbus.messages.Message.wiredata:[[B
        //    148: arraylength
        //    149: bipush  20
        //    151: iadd
        //    152: anewarray  #67 // [B
        //    155: astore_2
        //    156: aload_0
        //    157: getfield  #159 // org.freedesktop.dbus.messages.Message.wiredata:[[B
        //    160: iconst_0
        //    161: aload_2
        //    162: iconst_0
        //    163: aload_0
        //    164: getfield  #159 // org.freedesktop.dbus.messages.Message.wiredata:[[B
        //    167: arraylength
        //    168: invokestatic  #208 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    171: aload_0
        //    172: aload_2
        //    173: putfield  #159 // org.freedesktop.dbus.messages.Message.wiredata:[[B
        //    176: aload_0
        //    177: getfield  #159 // org.freedesktop.dbus.messages.Message.wiredata:[[B
        //    180: aload_0
        //    181: dup
        //    182: getfield  #145 // org.freedesktop.dbus.messages.Message.bufferuse:I
        //    185: dup_x1
        //    186: iconst_1
        //    187: iadd
        //    188: putfield  #145 // org.freedesktop.dbus.messages.Message.bufferuse:I
        //    191: aload_1
        //    192: aastore
        //    193: aload_0
        //    194: dup
        //    195: getfield  #146 // org.freedesktop.dbus.messages.Message.bytecounter:J
        //    198: aload_1
        //    199: arraylength
        //    200: i2l
        //    201: ladd
        //    202: putfield  #146 // org.freedesktop.dbus.messages.Message.bytecounter:J
        //    205: return
    }

  protected void appendByte(byte arg0) {
        if (preallocated <= 0) {
            if (bufferuse == wiredata.length) {
                logger.trace("Resizing {}", Integer.valueOf(bufferuse));
                byte[][] var2 = new byte[wiredata.length + 20][];
                System.arraycopy(wiredata, 0, var2, 0, wiredata.length);
                wiredata = var2;
            }
            bufferuse = bufferuse + 1;
            wiredata[bufferuse] = new byte[]{arg0};
            bytecounter = bytecounter + 1L;
        } else {
            paofs = paofs + 1;
            pabuf[paofs] = arg0;
            preallocated = preallocated - 1;
        }
    }

  protected long demarshallint(byte[] arg0, int arg1, int arg2) {
        return !big ? demarshallintLittle(arg0, arg1, arg2) : demarshallintBig(arg0, arg1, arg2);
    }

  protected void appendint(long arg0, int arg1) {
        byte[] var4 = new byte[arg1];
        marshallint(arg0, var4, 0, arg1);
        appendBytes(var4);
    }

  protected void marshallint(long arg0, byte[] arg1, int arg2, int arg3) {
        if (!big) {
            marshallintLittle(arg0, arg1, arg2, arg3);
        } else {
            marshallintBig(arg0, arg1, arg2, arg3);
        }
        LoggingHelper.logIf(logger.isTraceEnabled(), () -> lambda$marshallint$2(arg0, arg1, arg2, arg3));
    }

  public byte[][] getWireData() {
        return wiredata;
    }

  public List getFiledescriptors() {
        return filedescriptors;
    }

  public String toString() {
        StringBuilder var1 = new StringBuilder();
        var1.append(getClass().getSimpleName());
        var1.append('(');
        var1.append(flags);
        var1.append(',');
        var1.append(getSerial());
        var1.append(')');
        var1.append(' ');
        var1.append('{');
        var1.append(' ');
        int var2;
        if (headers.length != 0) {
            var2 = 0;
            while (var2 < headers.length) {
                var1.append(getHeaderFieldName(((byte) var2)));
                var1.append('=');
                var1.append('>');
                var1.append(headers[var2]);
                var1.append(',');
                var1.append(' ');
                ++var2;
                continue;
            }
            var1.setCharAt(var1.length() - 2, ' ');
            var1.setCharAt(var1.length() - 1, '}');
        } else {
            var1.append('}');
        }
        var1.append(' ');
        var1.append('{');
        var1.append(' ');
        int var2 = null;
        try {
            var2 = getParameters();
        } catch (DBusException var3) {
            logger.debug("", var3);
        }
        if (null == var2) {
            var1.append('}');
        } else {
            if (0 != var2.length) {
                int var3 = var2;
                int var4 = var3.length;
                int var5 = 0;
                while (var5 < var4) {
                    Object var6 = var3[var5];
                    if (var6 != null) {
                        if (!(var6 instanceof Object[])) {
                            if (!(var6 instanceof byte[])) {
                                if (!(var6 instanceof int[])) {
                                    if (!(var6 instanceof short[])) {
                                        if (!(var6 instanceof long[])) {
                                            if (!(var6 instanceof boolean[])) {
                                                if (!(var6 instanceof double[])) {
                                                    if (!(var6 instanceof float[])) {
                                                        var1.append(var6);
                                                    } else {
                                                        float[] var14 = ((float[]) var6);
                                                        var1.append(Arrays.toString(var14));
                                                    }
                                                } else {
                                                    double[] var13 = ((double[]) var6);
                                                    var1.append(Arrays.toString(var13));
                                                }
                                            } else {
                                                boolean[] var12 = ((boolean[]) var6);
                                                var1.append(Arrays.toString(var12));
                                            }
                                        } else {
                                            long[] var11 = ((long[]) var6);
                                            var1.append(Arrays.toString(var11));
                                        }
                                    } else {
                                        short[] var10 = ((short[]) var6);
                                        var1.append(Arrays.toString(var10));
                                    }
                                } else {
                                    int[] var9 = ((int[]) var6);
                                    var1.append(Arrays.toString(var9));
                                }
                            } else {
                                byte[] var8 = ((byte[]) var6);
                                var1.append(Arrays.toString(var8));
                            }
                        } else {
                            Object[] var7 = ((Object[]) var6);
                            var1.append(Arrays.deepToString(var7));
                        }
                    } else {
                        var1.append("null");
                    }
                    var1.append(',');
                    var1.append(' ');
                    ++var5;
                    continue;
                }
                var1.setCharAt(var1.length() - 2, ' ');
                var1.setCharAt(var1.length() - 1, '}');
            } else {
                var1.append('}');
            }
        }
        return var1.toString();
    }

  protected Object getHeader(byte arg0) {
        return headers.length == 0 ? null : headers.length >= arg0 ? headers[arg0] : null;
    }

  protected void pad(byte arg0) {
        logger.trace("padding for {}", Character.valueOf(((char) arg0)));
        int var2 = getAlignment(arg0);
        logger.trace("{} {} {} {}", new Object[]{Integer.valueOf(preallocated), Integer.valueOf(paofs), Long.valueOf(bytecounter), Integer.valueOf(var2)});
        int var3 = ((int) ((bytecounter - ((long) preallocated)) % ((long) var2)));
        if (0 != var3) {
            var2 = var2 - var3;
            if (preallocated <= 0) {
                appendBytes(((byte[]) padding[var2]));
            } else {
                paofs = paofs + var2;
                preallocated = preallocated - var2;
            }
            logger.trace("{} {} {} {}", new Object[]{Integer.valueOf(preallocated), Integer.valueOf(paofs), Long.valueOf(bytecounter), Integer.valueOf(var2)});
            return;
        } else {
            return;
        }
    }

  protected void append(String arg0, Object[] arg1) {
        LoggingHelper.logIf(logger.isDebugEnabled(), () -> lambda$append$3(arg0, arg1));
        byte[] var3 = arg0.getBytes();
        int var4 = 0;
        int var5 = 0;
        while (var5 < var3.length) {
            logger.trace("Appending item: {} {} {}", new Object[]{Integer.valueOf(var5), Character.valueOf(((char) var3[var5])), Integer.valueOf(var4)});
            var5 = appendOne(var3, var5, arg1[var4++]);
            ++var5;
            continue;
        }
    }

  private int appendOne(byte[] arg0, int arg1, Object arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iload_2
        //      1: istore  4
        //      3: aload_0
        //      4: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //      7: ldc  #65 // '{}'
        //      9: aload_0
        //     10: getfield  #146 // org.freedesktop.dbus.messages.Message.bytecounter:J
        //     13: invokestatic  #180 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //     16: invokeinterface  #338 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //     21: aload_0
        //     22: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //     25: ldc  #17 // 'Appending type: {} value: {}'
        //     27: aload_1
        //     28: iload  4
        //     30: baload
        //     31: i2c
        //     32: invokestatic  #164 // java.lang.Character.valueOf:(C)Ljava/lang/Character;
        //     35: aload_3
        //     36: invokeinterface  #339 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //     41: aload_0
        //     42: aload_1
        //     43: iload  4
        //     45: baload
        //     46: invokevirtual  #289 // org.freedesktop.dbus.messages.Message.pad:(B)V
        //     49: aload_1
        //     50: iload  4
        //     52: baload
        //     53: lookupswitch  default->1859, 40->1535, 97->642, 98->222, 100->245, 102->268, 103->575, 104->431, 105->383, 110->415, 111->490, 113->399, 115->490, 116->322, 117->291, 118->1712, 120->306, 121->208, 123->1611
        //    208: aload_0
        //    209: aload_3
        //    210: checkcast  #88 // java.lang.Number
        //    213: invokevirtual  #181 // java.lang.Number.byteValue:()B
        //    216: invokevirtual  #245 // org.freedesktop.dbus.messages.Message.appendByte:(B)V
        //    219: goto  1859 (offset +1640)
        //    222: aload_0
        //    223: aload_3
        //    224: checkcast  #78 // java.lang.Boolean
        //    227: invokevirtual  #161 // java.lang.Boolean.booleanValue:()Z
        //    230: ifeq  237 (offset +7)
        //    233: lconst_1
        //    234: goto  238 (offset +4)
        //    237: lconst_0
        //    238: iconst_4
        //    239: invokevirtual  #248 // org.freedesktop.dbus.messages.Message.appendint:(JI)V
        //    242: goto  1859 (offset +1617)
        //    245: aload_3
        //    246: checkcast  #88 // java.lang.Number
        //    249: invokevirtual  #182 // java.lang.Number.doubleValue:()D
        //    252: invokestatic  #170 // java.lang.Double.doubleToLongBits:(D)J
        //    255: lstore  5
        //    257: aload_0
        //    258: lload  5
        //    260: bipush  8
        //    262: invokevirtual  #248 // org.freedesktop.dbus.messages.Message.appendint:(JI)V
        //    265: goto  1859 (offset +1594)
        //    268: aload_3
        //    269: checkcast  #88 // java.lang.Number
        //    272: invokevirtual  #183 // java.lang.Number.floatValue:()F
        //    275: invokestatic  #174 // java.lang.Float.floatToIntBits:(F)I
        //    278: istore  7
        //    280: aload_0
        //    281: iload  7
        //    283: i2l
        //    284: iconst_4
        //    285: invokevirtual  #248 // org.freedesktop.dbus.messages.Message.appendint:(JI)V
        //    288: goto  1859 (offset +1571)
        //    291: aload_0
        //    292: aload_3
        //    293: checkcast  #88 // java.lang.Number
        //    296: invokevirtual  #185 // java.lang.Number.longValue:()J
        //    299: iconst_4
        //    300: invokevirtual  #248 // org.freedesktop.dbus.messages.Message.appendint:(JI)V
        //    303: goto  1859 (offset +1556)
        //    306: aload_0
        //    307: aload_3
        //    308: checkcast  #88 // java.lang.Number
        //    311: invokevirtual  #185 // java.lang.Number.longValue:()J
        //    314: bipush  8
        //    316: invokevirtual  #248 // org.freedesktop.dbus.messages.Message.appendint:(JI)V
        //    319: goto  1859 (offset +1540)
        //    322: aload_0
        //    323: getfield  #142 // org.freedesktop.dbus.messages.Message.big:Z
        //    326: ifeq  356 (offset +30)
        //    329: aload_0
        //    330: aload_3
        //    331: checkcast  #129 // org.freedesktop.dbus.types.UInt64
        //    334: invokevirtual  #298 // org.freedesktop.dbus.types.UInt64.top:()J
        //    337: iconst_4
        //    338: invokevirtual  #248 // org.freedesktop.dbus.messages.Message.appendint:(JI)V
        //    341: aload_0
        //    342: aload_3
        //    343: checkcast  #129 // org.freedesktop.dbus.types.UInt64
        //    346: invokevirtual  #297 // org.freedesktop.dbus.types.UInt64.bottom:()J
        //    349: iconst_4
        //    350: invokevirtual  #248 // org.freedesktop.dbus.messages.Message.appendint:(JI)V
        //    353: goto  1859 (offset +1506)
        //    356: aload_0
        //    357: aload_3
        //    358: checkcast  #129 // org.freedesktop.dbus.types.UInt64
        //    361: invokevirtual  #297 // org.freedesktop.dbus.types.UInt64.bottom:()J
        //    364: iconst_4
        //    365: invokevirtual  #248 // org.freedesktop.dbus.messages.Message.appendint:(JI)V
        //    368: aload_0
        //    369: aload_3
        //    370: checkcast  #129 // org.freedesktop.dbus.types.UInt64
        //    373: invokevirtual  #298 // org.freedesktop.dbus.types.UInt64.top:()J
        //    376: iconst_4
        //    377: invokevirtual  #248 // org.freedesktop.dbus.messages.Message.appendint:(JI)V
        //    380: goto  1859 (offset +1479)
        //    383: aload_0
        //    384: aload_3
        //    385: checkcast  #88 // java.lang.Number
        //    388: invokevirtual  #184 // java.lang.Number.intValue:()I
        //    391: i2l
        //    392: iconst_4
        //    393: invokevirtual  #248 // org.freedesktop.dbus.messages.Message.appendint:(JI)V
        //    396: goto  1859 (offset +1463)
        //    399: aload_0
        //    400: aload_3
        //    401: checkcast  #88 // java.lang.Number
        //    404: invokevirtual  #184 // java.lang.Number.intValue:()I
        //    407: i2l
        //    408: iconst_2
        //    409: invokevirtual  #248 // org.freedesktop.dbus.messages.Message.appendint:(JI)V
        //    412: goto  1859 (offset +1447)
        //    415: aload_0
        //    416: aload_3
        //    417: checkcast  #88 // java.lang.Number
        //    420: invokevirtual  #186 // java.lang.Number.shortValue:()S
        //    423: i2l
        //    424: iconst_2
        //    425: invokevirtual  #248 // org.freedesktop.dbus.messages.Message.appendint:(JI)V
        //    428: goto  1859 (offset +1431)
        //    431: aload_0
        //    432: getfield  #148 // org.freedesktop.dbus.messages.Message.filedescriptors:Ljava/util/List;
        //    435: aload_3
        //    436: checkcast  #115 // org.freedesktop.dbus.FileDescriptor
        //    439: invokeinterface  #312 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    444: pop
        //    445: aload_0
        //    446: aload_0
        //    447: getfield  #148 // org.freedesktop.dbus.messages.Message.filedescriptors:Ljava/util/List;
        //    450: invokeinterface  #317 // java.util.List.size:()I, count 1
        //    455: i2l
        //    456: lconst_1
        //    457: lsub
        //    458: iconst_4
        //    459: invokevirtual  #248 // org.freedesktop.dbus.messages.Message.appendint:(JI)V
        //    462: aload_0
        //    463: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //    466: ldc  #31 // 'Just inserted {} as filedescriptor'
        //    468: aload_0
        //    469: getfield  #148 // org.freedesktop.dbus.messages.Message.filedescriptors:Ljava/util/List;
        //    472: invokeinterface  #317 // java.util.List.size:()I, count 1
        //    477: iconst_1
        //    478: isub
        //    479: invokestatic  #179 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    482: invokeinterface  #332 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    487: goto  1859 (offset +1372)
        //    490: aload_3
        //    491: instanceof  #122 // org.freedesktop.dbus.interfaces.DBusInterface
        //    494: ifeq  515 (offset +21)
        //    497: aload_3
        //    498: checkcast  #122 // org.freedesktop.dbus.interfaces.DBusInterface
        //    501: astore  9
        //    503: aload  9
        //    505: invokeinterface  #330 // org.freedesktop.dbus.interfaces.DBusInterface.getObjectPath:()Ljava/lang/String;, count 1
        //    510: astore  8
        //    512: goto  521 (offset +9)
        //    515: aload_3
        //    516: invokevirtual  #189 // java.lang.Object.toString:()Ljava/lang/String;
        //    519: astore  8
        //    521: aload  8
        //    523: getstatic  #139 // java.nio.charset.StandardCharsets.UTF_8:Ljava/nio/charset/Charset;
        //    526: invokevirtual  #196 // java.lang.String.getBytes:(Ljava/nio/charset/Charset;)[B
        //    529: astore  9
        //    531: aload_0
        //    532: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //    535: ldc  #13 // 'Appending String of length {}'
        //    537: aload  9
        //    539: arraylength
        //    540: invokestatic  #179 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    543: invokeinterface  #338 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    548: aload_0
        //    549: aload  9
        //    551: arraylength
        //    552: i2l
        //    553: iconst_4
        //    554: invokevirtual  #248 // org.freedesktop.dbus.messages.Message.appendint:(JI)V
        //    557: aload_0
        //    558: aload  9
        //    560: invokevirtual  #246 // org.freedesktop.dbus.messages.Message.appendBytes:([B)V
        //    563: aload_0
        //    564: getstatic  #153 // org.freedesktop.dbus.messages.Message.padding:[[B
        //    567: iconst_1
        //    568: aaload
        //    569: invokevirtual  #246 // org.freedesktop.dbus.messages.Message.appendBytes:([B)V
        //    572: goto  1859 (offset +1287)
        //    575: aload_3
        //    576: instanceof  #73 // [Ljava.lang.reflect.Type;
        //    579: ifeq  598 (offset +19)
        //    582: aload_3
        //    583: checkcast  #73 // [Ljava.lang.reflect.Type;
        //    586: astore  10
        //    588: aload  10
        //    590: invokestatic  #234 // org.freedesktop.dbus.Marshalling.getDBusType:([Ljava/lang/reflect/Type;)Ljava/lang/String;
        //    593: astore  8
        //    595: goto  604 (offset +9)
        //    598: aload_3
        //    599: checkcast  #91 // java.lang.String
        //    602: astore  8
        //    604: aload  8
        //    606: invokevirtual  #195 // java.lang.String.getBytes:()[B
        //    609: astore  10
        //    611: aload_0
        //    612: iconst_2
        //    613: aload  10
        //    615: arraylength
        //    616: iadd
        //    617: invokevirtual  #290 // org.freedesktop.dbus.messages.Message.preallocate:(I)V
        //    620: aload_0
        //    621: aload  10
        //    623: arraylength
        //    624: i2b
        //    625: invokevirtual  #245 // org.freedesktop.dbus.messages.Message.appendByte:(B)V
        //    628: aload_0
        //    629: aload  10
        //    631: invokevirtual  #246 // org.freedesktop.dbus.messages.Message.appendBytes:([B)V
        //    634: aload_0
        //    635: iconst_0
        //    636: invokevirtual  #245 // org.freedesktop.dbus.messages.Message.appendByte:(B)V
        //    639: goto  1859 (offset +1220)
        //    642: aload_0
        //    643: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //    646: invokeinterface  #336 // org.slf4j.Logger.isTraceEnabled:()Z, count 1
        //    651: ifeq  683 (offset +32)
        //    654: aload_3
        //    655: instanceof  #72 // [Ljava.lang.Object;
        //    658: ifeq  683 (offset +25)
        //    661: aload_3
        //    662: checkcast  #72 // [Ljava.lang.Object;
        //    665: astore  11
        //    667: aload_0
        //    668: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //    671: ldc  #14 // 'Appending array: {}'
        //    673: aload  11
        //    675: invokestatic  #218 // java.util.Arrays.deepToString:([Ljava/lang/Object;)Ljava/lang/String;
        //    678: invokeinterface  #338 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    683: iconst_4
        //    684: newarray  byte
        //    686: astore  11
        //    688: aload_0
        //    689: aload  11
        //    691: invokevirtual  #246 // org.freedesktop.dbus.messages.Message.appendBytes:([B)V
        //    694: aload_0
        //    695: aload_1
        //    696: iinc  4, 1
        //    699: iload  4
        //    701: baload
        //    702: invokevirtual  #289 // org.freedesktop.dbus.messages.Message.pad:(B)V
        //    705: aload_0
        //    706: getfield  #146 // org.freedesktop.dbus.messages.Message.bytecounter:J
        //    709: lstore  12
        //    711: aload_3
        //    712: invokevirtual  #188 // java.lang.Object.getClass:()Ljava/lang/Class;
        //    715: invokevirtual  #168 // java.lang.Class.isArray:()Z
        //    718: ifeq  1186 (offset +468)
        //    721: aload_3
        //    722: invokevirtual  #188 // java.lang.Object.getClass:()Ljava/lang/Class;
        //    725: invokevirtual  #165 // java.lang.Class.getComponentType:()Ljava/lang/Class;
        //    728: invokevirtual  #169 // java.lang.Class.isPrimitive:()Z
        //    731: ifeq  1186 (offset +455)
        //    734: aload_1
        //    735: iload  4
        //    737: baload
        //    738: invokestatic  #264 // org.freedesktop.dbus.messages.Message.getAlignment:(B)I
        //    741: istore  17
        //    743: aload_3
        //    744: invokestatic  #213 // java.lang.reflect.Array.getLength:(Ljava/lang/Object;)I
        //    747: istore  18
        //    749: aload_1
        //    750: iload  4
        //    752: baload
        //    753: tableswitch  default->1167, 98->927, 99->1167, 100->989, 101->1167, 102->1108, 103->1167, 104->1167, 105->873, 106->1167, 107->1167, 108->1167, 109->1167, 110->873, 111->1167, 112->1167, 113->1167, 114->1167, 115->1167, 116->1167, 117->1167, 118->1167, 119->1167, 120->873, 121->864
        //    864: aload_3
        //    865: checkcast  #67 // [B
        //    868: astore  16
        //    870: goto  1177 (offset +307)
        //    873: iload  18
        //    875: iload  17
        //    877: imul
        //    878: newarray  byte
        //    880: astore  16
        //    882: iconst_0
        //    883: istore  19
        //    885: iconst_0
        //    886: istore  20
        //    888: iload  19
        //    890: iload  18
        //    892: if_icmpge  924 (offset +32)
        //    895: aload_0
        //    896: aload_3
        //    897: iload  19
        //    899: invokestatic  #214 // java.lang.reflect.Array.getLong:(Ljava/lang/Object;I)J
        //    902: aload  16
        //    904: iload  20
        //    906: iload  17
        //    908: invokevirtual  #285 // org.freedesktop.dbus.messages.Message.marshallint:(J[BII)V
        //    911: iinc  19, 1
        //    914: iload  20
        //    916: iload  17
        //    918: iadd
        //    919: istore  20
        //    921: goto  888 (offset -33)
        //    924: goto  1177 (offset +253)
        //    927: iload  18
        //    929: iload  17
        //    931: imul
        //    932: newarray  byte
        //    934: astore  16
        //    936: iconst_0
        //    937: istore  19
        //    939: iconst_0
        //    940: istore  20
        //    942: iload  19
        //    944: iload  18
        //    946: if_icmpge  986 (offset +40)
        //    949: aload_0
        //    950: aload_3
        //    951: iload  19
        //    953: invokestatic  #212 // java.lang.reflect.Array.getBoolean:(Ljava/lang/Object;I)Z
        //    956: ifeq  963 (offset +7)
        //    959: lconst_1
        //    960: goto  964 (offset +4)
        //    963: lconst_0
        //    964: aload  16
        //    966: iload  20
        //    968: iload  17
        //    970: invokevirtual  #285 // org.freedesktop.dbus.messages.Message.marshallint:(J[BII)V
        //    973: iinc  19, 1
        //    976: iload  20
        //    978: iload  17
        //    980: iadd
        //    981: istore  20
        //    983: goto  942 (offset -41)
        //    986: goto  1177 (offset +191)
        //    989: iload  18
        //    991: iload  17
        //    993: imul
        //    994: newarray  byte
        //    996: astore  16
        //    998: aload_3
        //    999: instanceof  #69 // [F
        //   1002: ifeq  1059 (offset +57)
        //   1005: aload_3
        //   1006: checkcast  #69 // [F
        //   1009: astore  19
        //   1011: iconst_0
        //   1012: istore  20
        //   1014: iconst_0
        //   1015: istore  21
        //   1017: iload  20
        //   1019: iload  18
        //   1021: if_icmpge  1056 (offset +35)
        //   1024: aload_0
        //   1025: aload  19
        //   1027: iload  20
        //   1029: faload
        //   1030: f2d
        //   1031: invokestatic  #171 // java.lang.Double.doubleToRawLongBits:(D)J
        //   1034: aload  16
        //   1036: iload  21
        //   1038: iload  17
        //   1040: invokevirtual  #285 // org.freedesktop.dbus.messages.Message.marshallint:(J[BII)V
        //   1043: iinc  20, 1
        //   1046: iload  21
        //   1048: iload  17
        //   1050: iadd
        //   1051: istore  21
        //   1053: goto  1017 (offset -36)
        //   1056: goto  1177 (offset +121)
        //   1059: iconst_0
        //   1060: istore  20
        //   1062: iconst_0
        //   1063: istore  21
        //   1065: iload  20
        //   1067: iload  18
        //   1069: if_icmpge  1105 (offset +36)
        //   1072: aload_0
        //   1073: aload_3
        //   1074: checkcast  #68 // [D
        //   1077: iload  20
        //   1079: daload
        //   1080: invokestatic  #171 // java.lang.Double.doubleToRawLongBits:(D)J
        //   1083: aload  16
        //   1085: iload  21
        //   1087: iload  17
        //   1089: invokevirtual  #285 // org.freedesktop.dbus.messages.Message.marshallint:(J[BII)V
        //   1092: iinc  20, 1
        //   1095: iload  21
        //   1097: iload  17
        //   1099: iadd
        //   1100: istore  21
        //   1102: goto  1065 (offset -37)
        //   1105: goto  1177 (offset +72)
        //   1108: iload  18
        //   1110: iload  17
        //   1112: imul
        //   1113: newarray  byte
        //   1115: astore  16
        //   1117: iconst_0
        //   1118: istore  19
        //   1120: iconst_0
        //   1121: istore  20
        //   1123: iload  19
        //   1125: iload  18
        //   1127: if_icmpge  1164 (offset +37)
        //   1130: aload_0
        //   1131: aload_3
        //   1132: checkcast  #69 // [F
        //   1135: iload  19
        //   1137: faload
        //   1138: invokestatic  #175 // java.lang.Float.floatToRawIntBits:(F)I
        //   1141: i2l
        //   1142: aload  16
        //   1144: iload  20
        //   1146: iload  17
        //   1148: invokevirtual  #285 // org.freedesktop.dbus.messages.Message.marshallint:(J[BII)V
        //   1151: iinc  19, 1
        //   1154: iload  20
        //   1156: iload  17
        //   1158: iadd
        //   1159: istore  20
        //   1161: goto  1123 (offset -38)
        //   1164: goto  1177 (offset +13)
        //   1167: new  #119 // org.freedesktop.dbus.exceptions.MarshallingException
        //   1170: dup
        //   1171: ldc  #37 // 'Primitive array being sent as non-primitive array.'
        //   1173: invokespecial  #237 // org.freedesktop.dbus.exceptions.MarshallingException.<init>:(Ljava/lang/String;)V
        //   1176: athrow
        //   1177: aload_0
        //   1178: aload  16
        //   1180: invokevirtual  #246 // org.freedesktop.dbus.messages.Message.appendBytes:([B)V
        //   1183: goto  1471 (offset +288)
        //   1186: aload_3
        //   1187: instanceof  #104 // java.util.List
        //   1190: ifeq  1285 (offset +95)
        //   1193: aload_3
        //   1194: checkcast  #104 // java.util.List
        //   1197: astore  14
        //   1199: aload  14
        //   1201: invokeinterface  #318 // java.util.List.toArray:()[Ljava/lang/Object;, count 1
        //   1206: astore  16
        //   1208: iload  4
        //   1210: istore  17
        //   1212: aload_0
        //   1213: aload  16
        //   1215: arraylength
        //   1216: iconst_4
        //   1217: imul
        //   1218: invokevirtual  #254 // org.freedesktop.dbus.messages.Message.ensureBuffers:(I)V
        //   1221: aload  16
        //   1223: astore  18
        //   1225: aload  18
        //   1227: arraylength
        //   1228: istore  19
        //   1230: iconst_0
        //   1231: istore  20
        //   1233: iload  20
        //   1235: iload  19
        //   1237: if_icmpge  1264 (offset +27)
        //   1240: aload  18
        //   1242: iload  20
        //   1244: aaload
        //   1245: astore  21
        //   1247: aload_0
        //   1248: aload_1
        //   1249: iload  4
        //   1251: aload  21
        //   1253: invokevirtual  #247 // org.freedesktop.dbus.messages.Message.appendOne:([BILjava/lang/Object;)I
        //   1256: istore  17
        //   1258: iinc  20, 1
        //   1261: goto  1233 (offset -28)
        //   1264: aload  16
        //   1266: arraylength
        //   1267: ifne  1278 (offset +11)
        //   1270: aload_1
        //   1271: iload  17
        //   1273: invokestatic  #240 // org.freedesktop.dbus.messages.EmptyCollectionHelper.determineSignatureOffsetArray:([BI)I
        //   1276: istore  17
        //   1278: iload  17
        //   1280: istore  4
        //   1282: goto  1471 (offset +189)
        //   1285: aload_3
        //   1286: instanceof  #105 // java.util.Map
        //   1289: ifeq  1391 (offset +102)
        //   1292: aload_3
        //   1293: checkcast  #105 // java.util.Map
        //   1296: astore  15
        //   1298: iload  4
        //   1300: istore  16
        //   1302: aload_0
        //   1303: aload  15
        //   1305: invokeinterface  #322 // java.util.Map.size:()I, count 1
        //   1310: bipush  6
        //   1312: imul
        //   1313: invokevirtual  #254 // org.freedesktop.dbus.messages.Message.ensureBuffers:(I)V
        //   1316: aload  15
        //   1318: invokeinterface  #320 // java.util.Map.entrySet:()Ljava/util/Set;, count 1
        //   1323: invokeinterface  #325 // java.util.Set.iterator:()Ljava/util/Iterator;, count 1
        //   1328: astore  17
        //   1330: aload  17
        //   1332: invokeinterface  #310 // java.util.Iterator.hasNext:()Z, count 1
        //   1337: ifeq  1366 (offset +29)
        //   1340: aload  17
        //   1342: invokeinterface  #311 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //   1347: checkcast  #106 // java.util.Map$Entry
        //   1350: astore  18
        //   1352: aload_0
        //   1353: aload_1
        //   1354: iload  4
        //   1356: aload  18
        //   1358: invokevirtual  #247 // org.freedesktop.dbus.messages.Message.appendOne:([BILjava/lang/Object;)I
        //   1361: istore  16
        //   1363: goto  1330 (offset -33)
        //   1366: aload  15
        //   1368: invokeinterface  #321 // java.util.Map.isEmpty:()Z, count 1
        //   1373: ifeq  1384 (offset +11)
        //   1376: aload_1
        //   1377: iload  16
        //   1379: invokestatic  #241 // org.freedesktop.dbus.messages.EmptyCollectionHelper.determineSignatureOffsetDict:([BI)I
        //   1382: istore  16
        //   1384: iload  16
        //   1386: istore  4
        //   1388: goto  1471 (offset +83)
        //   1391: aload_3
        //   1392: checkcast  #72 // [Ljava.lang.Object;
        //   1395: astore  16
        //   1397: aload_0
        //   1398: aload  16
        //   1400: arraylength
        //   1401: iconst_4
        //   1402: imul
        //   1403: invokevirtual  #254 // org.freedesktop.dbus.messages.Message.ensureBuffers:(I)V
        //   1406: iload  4
        //   1408: istore  17
        //   1410: aload  16
        //   1412: astore  18
        //   1414: aload  18
        //   1416: arraylength
        //   1417: istore  19
        //   1419: iconst_0
        //   1420: istore  20
        //   1422: iload  20
        //   1424: iload  19
        //   1426: if_icmpge  1453 (offset +27)
        //   1429: aload  18
        //   1431: iload  20
        //   1433: aaload
        //   1434: astore  21
        //   1436: aload_0
        //   1437: aload_1
        //   1438: iload  4
        //   1440: aload  21
        //   1442: invokevirtual  #247 // org.freedesktop.dbus.messages.Message.appendOne:([BILjava/lang/Object;)I
        //   1445: istore  17
        //   1447: iinc  20, 1
        //   1450: goto  1422 (offset -28)
        //   1453: aload  16
        //   1455: arraylength
        //   1456: ifne  1467 (offset +11)
        //   1459: aload_1
        //   1460: iload  17
        //   1462: invokestatic  #240 // org.freedesktop.dbus.messages.EmptyCollectionHelper.determineSignatureOffsetArray:([BI)I
        //   1465: istore  17
        //   1467: iload  17
        //   1469: istore  4
        //   1471: aload_0
        //   1472: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //   1475: ldc  #60 // 'start: {} end: {} length: {}'
        //   1477: iconst_3
        //   1478: anewarray  #89 // java.lang.Object
        //   1481: dup
        //   1482: iconst_0
        //   1483: lload  12
        //   1485: invokestatic  #180 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //   1488: aastore
        //   1489: dup
        //   1490: iconst_1
        //   1491: aload_0
        //   1492: getfield  #146 // org.freedesktop.dbus.messages.Message.bytecounter:J
        //   1495: invokestatic  #180 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //   1498: aastore
        //   1499: dup
        //   1500: iconst_2
        //   1501: aload_0
        //   1502: getfield  #146 // org.freedesktop.dbus.messages.Message.bytecounter:J
        //   1505: lload  12
        //   1507: lsub
        //   1508: invokestatic  #180 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //   1511: aastore
        //   1512: invokeinterface  #340 // org.slf4j.Logger.trace:(Ljava/lang/String;[Ljava/lang/Object;)V, count 3
        //   1517: aload_0
        //   1518: aload_0
        //   1519: getfield  #146 // org.freedesktop.dbus.messages.Message.bytecounter:J
        //   1522: lload  12
        //   1524: lsub
        //   1525: aload  11
        //   1527: iconst_0
        //   1528: iconst_4
        //   1529: invokevirtual  #285 // org.freedesktop.dbus.messages.Message.marshallint:(J[BII)V
        //   1532: goto  1859 (offset +327)
        //   1535: aload_3
        //   1536: instanceof  #113 // org.freedesktop.dbus.Container
        //   1539: ifeq  1558 (offset +19)
        //   1542: aload_3
        //   1543: checkcast  #113 // org.freedesktop.dbus.Container
        //   1546: astore  15
        //   1548: aload  15
        //   1550: invokevirtual  #231 // org.freedesktop.dbus.Container.getParameters:()[Ljava/lang/Object;
        //   1553: astore  14
        //   1555: goto  1564 (offset +9)
        //   1558: aload_3
        //   1559: checkcast  #72 // [Ljava.lang.Object;
        //   1562: astore  14
        //   1564: aload_0
        //   1565: aload  14
        //   1567: arraylength
        //   1568: iconst_4
        //   1569: imul
        //   1570: invokevirtual  #254 // org.freedesktop.dbus.messages.Message.ensureBuffers:(I)V
        //   1573: iconst_0
        //   1574: istore  15
        //   1576: iinc  4, 1
        //   1579: aload_1
        //   1580: iload  4
        //   1582: baload
        //   1583: bipush  41
        //   1585: if_icmpeq  1859 (offset +274)
        //   1588: aload_0
        //   1589: aload_1
        //   1590: iload  4
        //   1592: aload  14
        //   1594: iload  15
        //   1596: iinc  15, 1
        //   1599: aaload
        //   1600: invokevirtual  #247 // org.freedesktop.dbus.messages.Message.appendOne:([BILjava/lang/Object;)I
        //   1603: istore  4
        //   1605: iinc  4, 1
        //   1608: goto  1579 (offset -29)
        //   1611: aload_3
        //   1612: instanceof  #106 // java.util.Map$Entry
        //   1615: ifeq  1668 (offset +53)
        //   1618: aload_3
        //   1619: checkcast  #106 // java.util.Map$Entry
        //   1622: astore  16
        //   1624: iinc  4, 1
        //   1627: aload_0
        //   1628: aload_1
        //   1629: iload  4
        //   1631: aload  16
        //   1633: invokeinterface  #323 // java.util.Map$Entry.getKey:()Ljava/lang/Object;, count 1
        //   1638: invokevirtual  #247 // org.freedesktop.dbus.messages.Message.appendOne:([BILjava/lang/Object;)I
        //   1641: istore  4
        //   1643: iinc  4, 1
        //   1646: aload_0
        //   1647: aload_1
        //   1648: iload  4
        //   1650: aload  16
        //   1652: invokeinterface  #324 // java.util.Map$Entry.getValue:()Ljava/lang/Object;, count 1
        //   1657: invokevirtual  #247 // org.freedesktop.dbus.messages.Message.appendOne:([BILjava/lang/Object;)I
        //   1660: istore  4
        //   1662: iinc  4, 1
        //   1665: goto  1859 (offset +194)
        //   1668: aload_3
        //   1669: checkcast  #72 // [Ljava.lang.Object;
        //   1672: astore  14
        //   1674: iconst_0
        //   1675: istore  15
        //   1677: iinc  4, 1
        //   1680: aload_1
        //   1681: iload  4
        //   1683: baload
        //   1684: bipush  125
        //   1686: if_icmpeq  1859 (offset +173)
        //   1689: aload_0
        //   1690: aload_1
        //   1691: iload  4
        //   1693: aload  14
        //   1695: iload  15
        //   1697: iinc  15, 1
        //   1700: aaload
        //   1701: invokevirtual  #247 // org.freedesktop.dbus.messages.Message.appendOne:([BILjava/lang/Object;)I
        //   1704: istore  4
        //   1706: iinc  4, 1
        //   1709: goto  1680 (offset -29)
        //   1712: aload_3
        //   1713: instanceof  #130 // org.freedesktop.dbus.types.Variant
        //   1716: ifeq  1766 (offset +50)
        //   1719: aload_3
        //   1720: checkcast  #130 // org.freedesktop.dbus.types.Variant
        //   1723: astore  16
        //   1725: aload_0
        //   1726: iconst_1
        //   1727: newarray  byte
        //   1729: dup
        //   1730: iconst_0
        //   1731: bipush  103
        //   1733: bastore
        //   1734: iconst_0
        //   1735: aload  16
        //   1737: invokevirtual  #300 // org.freedesktop.dbus.types.Variant.getSig:()Ljava/lang/String;
        //   1740: invokevirtual  #247 // org.freedesktop.dbus.messages.Message.appendOne:([BILjava/lang/Object;)I
        //   1743: pop
        //   1744: aload_0
        //   1745: aload  16
        //   1747: invokevirtual  #300 // org.freedesktop.dbus.types.Variant.getSig:()Ljava/lang/String;
        //   1750: invokevirtual  #195 // java.lang.String.getBytes:()[B
        //   1753: iconst_0
        //   1754: aload  16
        //   1756: invokevirtual  #301 // org.freedesktop.dbus.types.Variant.getValue:()Ljava/lang/Object;
        //   1759: invokevirtual  #247 // org.freedesktop.dbus.messages.Message.appendOne:([BILjava/lang/Object;)I
        //   1762: pop
        //   1763: goto  1859 (offset +96)
        //   1766: aload_3
        //   1767: instanceof  #72 // [Ljava.lang.Object;
        //   1770: ifeq  1820 (offset +50)
        //   1773: aload_3
        //   1774: checkcast  #72 // [Ljava.lang.Object;
        //   1777: astore  17
        //   1779: aload_0
        //   1780: iconst_1
        //   1781: newarray  byte
        //   1783: dup
        //   1784: iconst_0
        //   1785: bipush  103
        //   1787: bastore
        //   1788: iconst_0
        //   1789: aload  17
        //   1791: iconst_0
        //   1792: aaload
        //   1793: invokevirtual  #247 // org.freedesktop.dbus.messages.Message.appendOne:([BILjava/lang/Object;)I
        //   1796: pop
        //   1797: aload_0
        //   1798: aload  17
        //   1800: iconst_0
        //   1801: aaload
        //   1802: checkcast  #91 // java.lang.String
        //   1805: invokevirtual  #195 // java.lang.String.getBytes:()[B
        //   1808: iconst_0
        //   1809: aload  17
        //   1811: iconst_1
        //   1812: aaload
        //   1813: invokevirtual  #247 // org.freedesktop.dbus.messages.Message.appendOne:([BILjava/lang/Object;)I
        //   1816: pop
        //   1817: goto  1859 (offset +42)
        //   1820: aload_3
        //   1821: invokevirtual  #188 // java.lang.Object.getClass:()Ljava/lang/Class;
        //   1824: invokestatic  #233 // org.freedesktop.dbus.Marshalling.getDBusType:(Ljava/lang/reflect/Type;)[Ljava/lang/String;
        //   1827: iconst_0
        //   1828: aaload
        //   1829: astore  18
        //   1831: aload_0
        //   1832: iconst_1
        //   1833: newarray  byte
        //   1835: dup
        //   1836: iconst_0
        //   1837: bipush  103
        //   1839: bastore
        //   1840: iconst_0
        //   1841: aload  18
        //   1843: invokevirtual  #247 // org.freedesktop.dbus.messages.Message.appendOne:([BILjava/lang/Object;)I
        //   1846: pop
        //   1847: aload_0
        //   1848: aload  18
        //   1850: invokevirtual  #195 // java.lang.String.getBytes:()[B
        //   1853: iconst_0
        //   1854: aload_3
        //   1855: invokevirtual  #247 // org.freedesktop.dbus.messages.Message.appendOne:([BILjava/lang/Object;)I
        //   1858: pop
        //   1859: iload  4
        //   1861: ireturn
        //   1862: astore  4
        //   1864: aload_0
        //   1865: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //   1868: ldc  #46 // 'Trying to marshall to unconvertible type.'
        //   1870: aload  4
        //   1872: invokeinterface  #334 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //   1877: new  #119 // org.freedesktop.dbus.exceptions.MarshallingException
        //   1880: dup
        //   1881: ldc  #45 // 'Trying to marshall to unconvertible type (from {0} to {1}).'
        //   1883: iconst_2
        //   1884: anewarray  #89 // java.lang.Object
        //   1887: dup
        //   1888: iconst_0
        //   1889: aload_3
        //   1890: invokevirtual  #188 // java.lang.Object.getClass:()Ljava/lang/Class;
        //   1893: invokevirtual  #166 // java.lang.Class.getName:()Ljava/lang/String;
        //   1896: aastore
        //   1897: dup
        //   1898: iconst_1
        //   1899: aload_1
        //   1900: iload_2
        //   1901: baload
        //   1902: i2c
        //   1903: invokestatic  #164 // java.lang.Character.valueOf:(C)Ljava/lang/Character;
        //   1906: aastore
        //   1907: invokestatic  #215 // java.text.MessageFormat.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   1910: invokespecial  #237 // org.freedesktop.dbus.exceptions.MarshallingException.<init>:(Ljava/lang/String;)V
        //   1913: athrow
        //       Exception table:
        //         from 0 to 1861 target 1862 type java.lang.ClassCastException
    }

  protected int align(int arg0, byte arg1) {
        logger.trace("aligning to {}", Character.valueOf(((char) arg1)));
        int var3 = getAlignment(arg1);
        if (0 != arg0 % var3) {
            return arg0 + var3 - arg0 % var3;
        } else {
            return arg0;
        }
    }

   Object[] extractHeader(byte[] arg0) {
        int[] var2 = new int[]{0, 0};
        return extract("a(yv)", arg0, var2, (lp0, lp1, lp2, lp3) -> readHeaderVariants(lp0, lp1, lp2, lp3));
    }

  private Object readHeaderVariants(byte[] arg0, byte[] arg1, int[] arg2, boolean arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_3
        //      1: iconst_1
        //      2: aload_0
        //      3: aload_3
        //      4: iconst_1
        //      5: iaload
        //      6: aload_1
        //      7: aload_3
        //      8: iconst_0
        //      9: iaload
        //     10: baload
        //     11: invokevirtual  #243 // org.freedesktop.dbus.messages.Message.align:(IB)I
        //     14: iastore
        //     15: aconst_null
        //     16: astore  5
        //     18: aload_1
        //     19: aload_3
        //     20: iconst_0
        //     21: iaload
        //     22: baload
        //     23: bipush  97
        //     25: if_icmpne  48 (offset +23)
        //     28: aload_0
        //     29: aload_1
        //     30: aload_2
        //     31: aload_3
        //     32: iload  4
        //     34: aload_0
        //     35: invokedynamic  #346 // invokedynamic extractOne:(Lorg/freedesktop/dbus/messages/Message;)Lorg/freedesktop/dbus/messages/Message$ExtractMethod;
        //     40: invokevirtual  #258 // org.freedesktop.dbus.messages.Message.extractArray:([B[B[IZLorg/freedesktop/dbus/messages/Message$ExtractMethod;)Ljava/lang/Object;
        //     43: astore  5
        //     45: goto  141 (offset +96)
        //     48: aload_1
        //     49: aload_3
        //     50: iconst_0
        //     51: iaload
        //     52: baload
        //     53: bipush  121
        //     55: if_icmpne  69 (offset +14)
        //     58: aload_0
        //     59: aload_2
        //     60: aload_3
        //     61: invokevirtual  #259 // org.freedesktop.dbus.messages.Message.extractByte:([B[I)Ljava/lang/Object;
        //     64: astore  5
        //     66: goto  141 (offset +75)
        //     69: aload_1
        //     70: aload_3
        //     71: iconst_0
        //     72: iaload
        //     73: baload
        //     74: bipush  118
        //     76: if_icmpne  95 (offset +19)
        //     79: aload_0
        //     80: aload_2
        //     81: aload_3
        //     82: invokedynamic  #347 // invokedynamic apply:()Ljava/util/function/BiFunction;
        //     87: invokevirtual  #263 // org.freedesktop.dbus.messages.Message.extractVariant:([B[ILjava/util/function/BiFunction;)Ljava/lang/Object;
        //     90: astore  5
        //     92: goto  141 (offset +49)
        //     95: aload_1
        //     96: aload_3
        //     97: iconst_0
        //     98: iaload
        //     99: baload
        //    100: bipush  40
        //    102: if_icmpne  123 (offset +21)
        //    105: aload_0
        //    106: aload_1
        //    107: aload_2
        //    108: aload_3
        //    109: aload_0
        //    110: invokedynamic  #346 // invokedynamic extractOne:(Lorg/freedesktop/dbus/messages/Message;)Lorg/freedesktop/dbus/messages/Message$ExtractMethod;
        //    115: invokevirtual  #262 // org.freedesktop.dbus.messages.Message.extractStruct:([B[B[ILorg/freedesktop/dbus/messages/Message$ExtractMethod;)Ljava/lang/Object;
        //    118: astore  5
        //    120: goto  141 (offset +21)
        //    123: new  #120 // org.freedesktop.dbus.exceptions.MessageFormatException
        //    126: dup
        //    127: aload_1
        //    128: aload_3
        //    129: iconst_0
        //    130: iaload
        //    131: baload
        //    132: invokedynamic  #348 // invokedynamic makeConcatWithConstants:(B)Ljava/lang/String;
        //    137: invokespecial  #238 // org.freedesktop.dbus.exceptions.MessageFormatException.<init>:(Ljava/lang/String;)V
        //    140: athrow
        //    141: aload_0
        //    142: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //    145: ldc  #23 // "Extracted header signature type '{}' to: '{}'"
        //    147: aload_1
        //    148: aload_3
        //    149: iconst_0
        //    150: iaload
        //    151: baload
        //    152: i2c
        //    153: invokestatic  #164 // java.lang.Character.valueOf:(C)Ljava/lang/Character;
        //    156: aload  5
        //    158: invokeinterface  #339 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    163: aload  5
        //    165: areturn
    }

  private Object extractOne(byte[] arg0, byte[] arg1, int[] arg2, boolean arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //      4: ldc  #27 // 'Extracting type: {} from offset {}'
        //      6: aload_1
        //      7: aload_3
        //      8: iconst_0
        //      9: iaload
        //     10: baload
        //     11: i2c
        //     12: invokestatic  #164 // java.lang.Character.valueOf:(C)Ljava/lang/Character;
        //     15: aload_3
        //     16: iconst_1
        //     17: iaload
        //     18: invokestatic  #179 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //     21: invokeinterface  #339 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //     26: aconst_null
        //     27: astore  5
        //     29: aload_3
        //     30: iconst_1
        //     31: aload_0
        //     32: aload_3
        //     33: iconst_1
        //     34: iaload
        //     35: aload_1
        //     36: aload_3
        //     37: iconst_0
        //     38: iaload
        //     39: baload
        //     40: invokevirtual  #243 // org.freedesktop.dbus.messages.Message.align:(IB)I
        //     43: iastore
        //     44: aload_1
        //     45: aload_3
        //     46: iconst_0
        //     47: iaload
        //     48: baload
        //     49: lookupswitch  default->860, 40->564, 97->544, 98->505, 100->440, 102->473, 103->816, 104->677, 105->243, 110->268, 111->758, 113->294, 115->708, 116->349, 117->215, 118->661, 120->323, 121->204, 123->582
        //    204: aload_0
        //    205: aload_2
        //    206: aload_3
        //    207: invokevirtual  #259 // org.freedesktop.dbus.messages.Message.extractByte:([B[I)Ljava/lang/Object;
        //    210: astore  5
        //    212: goto  873 (offset +661)
        //    215: new  #128 // org.freedesktop.dbus.types.UInt32
        //    218: dup
        //    219: aload_0
        //    220: aload_2
        //    221: aload_3
        //    222: iconst_1
        //    223: iaload
        //    224: iconst_4
        //    225: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    228: invokespecial  #295 // org.freedesktop.dbus.types.UInt32.<init>:(J)V
        //    231: astore  5
        //    233: aload_3
        //    234: iconst_1
        //    235: dup2
        //    236: iaload
        //    237: iconst_4
        //    238: iadd
        //    239: iastore
        //    240: goto  873 (offset +633)
        //    243: aload_0
        //    244: aload_2
        //    245: aload_3
        //    246: iconst_1
        //    247: iaload
        //    248: iconst_4
        //    249: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    252: l2i
        //    253: invokestatic  #179 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    256: astore  5
        //    258: aload_3
        //    259: iconst_1
        //    260: dup2
        //    261: iaload
        //    262: iconst_4
        //    263: iadd
        //    264: iastore
        //    265: goto  873 (offset +608)
        //    268: aload_0
        //    269: aload_2
        //    270: aload_3
        //    271: iconst_1
        //    272: iaload
        //    273: iconst_2
        //    274: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    277: l2i
        //    278: i2s
        //    279: invokestatic  #190 // java.lang.Short.valueOf:(S)Ljava/lang/Short;
        //    282: astore  5
        //    284: aload_3
        //    285: iconst_1
        //    286: dup2
        //    287: iaload
        //    288: iconst_2
        //    289: iadd
        //    290: iastore
        //    291: goto  873 (offset +582)
        //    294: new  #127 // org.freedesktop.dbus.types.UInt16
        //    297: dup
        //    298: aload_0
        //    299: aload_2
        //    300: aload_3
        //    301: iconst_1
        //    302: iaload
        //    303: iconst_2
        //    304: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    307: l2i
        //    308: invokespecial  #294 // org.freedesktop.dbus.types.UInt16.<init>:(I)V
        //    311: astore  5
        //    313: aload_3
        //    314: iconst_1
        //    315: dup2
        //    316: iaload
        //    317: iconst_2
        //    318: iadd
        //    319: iastore
        //    320: goto  873 (offset +553)
        //    323: aload_0
        //    324: aload_2
        //    325: aload_3
        //    326: iconst_1
        //    327: iaload
        //    328: bipush  8
        //    330: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    333: invokestatic  #180 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //    336: astore  5
        //    338: aload_3
        //    339: iconst_1
        //    340: dup2
        //    341: iaload
        //    342: bipush  8
        //    344: iadd
        //    345: iastore
        //    346: goto  873 (offset +527)
        //    349: aload_0
        //    350: getfield  #142 // org.freedesktop.dbus.messages.Message.big:Z
        //    353: ifeq  388 (offset +35)
        //    356: aload_0
        //    357: aload_2
        //    358: aload_3
        //    359: iconst_1
        //    360: iaload
        //    361: iconst_4
        //    362: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    365: lstore  6
        //    367: aload_3
        //    368: iconst_1
        //    369: dup2
        //    370: iaload
        //    371: iconst_4
        //    372: iadd
        //    373: iastore
        //    374: aload_0
        //    375: aload_2
        //    376: aload_3
        //    377: iconst_1
        //    378: iaload
        //    379: iconst_4
        //    380: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    383: lstore  8
        //    385: goto  417 (offset +32)
        //    388: aload_0
        //    389: aload_2
        //    390: aload_3
        //    391: iconst_1
        //    392: iaload
        //    393: iconst_4
        //    394: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    397: lstore  8
        //    399: aload_3
        //    400: iconst_1
        //    401: dup2
        //    402: iaload
        //    403: iconst_4
        //    404: iadd
        //    405: iastore
        //    406: aload_0
        //    407: aload_2
        //    408: aload_3
        //    409: iconst_1
        //    410: iaload
        //    411: iconst_4
        //    412: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    415: lstore  6
        //    417: new  #129 // org.freedesktop.dbus.types.UInt64
        //    420: dup
        //    421: lload  6
        //    423: lload  8
        //    425: invokespecial  #296 // org.freedesktop.dbus.types.UInt64.<init>:(JJ)V
        //    428: astore  5
        //    430: aload_3
        //    431: iconst_1
        //    432: dup2
        //    433: iaload
        //    434: iconst_4
        //    435: iadd
        //    436: iastore
        //    437: goto  873 (offset +436)
        //    440: aload_0
        //    441: aload_2
        //    442: aload_3
        //    443: iconst_1
        //    444: iaload
        //    445: bipush  8
        //    447: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    450: lstore  10
        //    452: aload_3
        //    453: iconst_1
        //    454: dup2
        //    455: iaload
        //    456: bipush  8
        //    458: iadd
        //    459: iastore
        //    460: lload  10
        //    462: invokestatic  #172 // java.lang.Double.longBitsToDouble:(J)D
        //    465: invokestatic  #173 // java.lang.Double.valueOf:(D)Ljava/lang/Double;
        //    468: astore  5
        //    470: goto  873 (offset +403)
        //    473: aload_0
        //    474: aload_2
        //    475: aload_3
        //    476: iconst_1
        //    477: iaload
        //    478: iconst_4
        //    479: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    482: l2i
        //    483: istore  12
        //    485: aload_3
        //    486: iconst_1
        //    487: dup2
        //    488: iaload
        //    489: iconst_4
        //    490: iadd
        //    491: iastore
        //    492: iload  12
        //    494: invokestatic  #176 // java.lang.Float.intBitsToFloat:(I)F
        //    497: invokestatic  #177 // java.lang.Float.valueOf:(F)Ljava/lang/Float;
        //    500: astore  5
        //    502: goto  873 (offset +371)
        //    505: aload_0
        //    506: aload_2
        //    507: aload_3
        //    508: iconst_1
        //    509: iaload
        //    510: iconst_4
        //    511: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    514: l2i
        //    515: istore  12
        //    517: aload_3
        //    518: iconst_1
        //    519: dup2
        //    520: iaload
        //    521: iconst_4
        //    522: iadd
        //    523: iastore
        //    524: iconst_1
        //    525: iload  12
        //    527: if_icmpne  536 (offset +9)
        //    530: getstatic  #138 // java.lang.Boolean.TRUE:Ljava/lang/Boolean;
        //    533: goto  539 (offset +6)
        //    536: getstatic  #137 // java.lang.Boolean.FALSE:Ljava/lang/Boolean;
        //    539: astore  5
        //    541: goto  873 (offset +332)
        //    544: aload_0
        //    545: aload_1
        //    546: aload_2
        //    547: aload_3
        //    548: iload  4
        //    550: aload_0
        //    551: invokedynamic  #349 // invokedynamic extractOne:(Lorg/freedesktop/dbus/messages/Message;)Lorg/freedesktop/dbus/messages/Message$ExtractMethod;
        //    556: invokevirtual  #258 // org.freedesktop.dbus.messages.Message.extractArray:([B[B[IZLorg/freedesktop/dbus/messages/Message$ExtractMethod;)Ljava/lang/Object;
        //    559: astore  5
        //    561: goto  873 (offset +312)
        //    564: aload_0
        //    565: aload_1
        //    566: aload_2
        //    567: aload_3
        //    568: aload_0
        //    569: invokedynamic  #349 // invokedynamic extractOne:(Lorg/freedesktop/dbus/messages/Message;)Lorg/freedesktop/dbus/messages/Message$ExtractMethod;
        //    574: invokevirtual  #262 // org.freedesktop.dbus.messages.Message.extractStruct:([B[B[ILorg/freedesktop/dbus/messages/Message$ExtractMethod;)Ljava/lang/Object;
        //    577: astore  5
        //    579: goto  873 (offset +294)
        //    582: iconst_2
        //    583: anewarray  #89 // java.lang.Object
        //    586: astore  13
        //    588: aload_0
        //    589: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //    592: invokeinterface  #336 // org.slf4j.Logger.isTraceEnabled:()Z, count 1
        //    597: aload_0
        //    598: aload_1
        //    599: aload_3
        //    600: aload_2
        //    601: invokedynamic  #350 // invokedynamic run:(Lorg/freedesktop/dbus/messages/Message;[B[I[B)Ljava/lang/Runnable;
        //    606: invokestatic  #308 // org.freedesktop.dbus.utils.LoggingHelper.logIf:(ZLjava/lang/Runnable;)V
        //    609: aload_3
        //    610: iconst_0
        //    611: dup2
        //    612: iaload
        //    613: iconst_1
        //    614: iadd
        //    615: iastore
        //    616: aload  13
        //    618: iconst_0
        //    619: aload_0
        //    620: aload_1
        //    621: aload_2
        //    622: aload_3
        //    623: iconst_1
        //    624: invokevirtual  #261 // org.freedesktop.dbus.messages.Message.extractOne:([B[B[IZ)Ljava/lang/Object;
        //    627: aastore
        //    628: aload_3
        //    629: iconst_0
        //    630: dup2
        //    631: iaload
        //    632: iconst_1
        //    633: iadd
        //    634: iastore
        //    635: aload  13
        //    637: iconst_1
        //    638: aload_0
        //    639: aload_1
        //    640: aload_2
        //    641: aload_3
        //    642: iconst_1
        //    643: invokevirtual  #261 // org.freedesktop.dbus.messages.Message.extractOne:([B[B[IZ)Ljava/lang/Object;
        //    646: aastore
        //    647: aload_3
        //    648: iconst_0
        //    649: dup2
        //    650: iaload
        //    651: iconst_1
        //    652: iadd
        //    653: iastore
        //    654: aload  13
        //    656: astore  5
        //    658: goto  873 (offset +215)
        //    661: aload_0
        //    662: aload_2
        //    663: aload_3
        //    664: invokedynamic  #351 // invokedynamic apply:()Ljava/util/function/BiFunction;
        //    669: invokevirtual  #263 // org.freedesktop.dbus.messages.Message.extractVariant:([B[ILjava/util/function/BiFunction;)Ljava/lang/Object;
        //    672: astore  5
        //    674: goto  873 (offset +199)
        //    677: aload_0
        //    678: getfield  #148 // org.freedesktop.dbus.messages.Message.filedescriptors:Ljava/util/List;
        //    681: aload_0
        //    682: aload_2
        //    683: aload_3
        //    684: iconst_1
        //    685: iaload
        //    686: iconst_4
        //    687: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    690: l2i
        //    691: invokeinterface  #315 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //    696: astore  5
        //    698: aload_3
        //    699: iconst_1
        //    700: dup2
        //    701: iaload
        //    702: iconst_4
        //    703: iadd
        //    704: iastore
        //    705: goto  873 (offset +168)
        //    708: aload_0
        //    709: aload_2
        //    710: aload_3
        //    711: iconst_1
        //    712: iaload
        //    713: iconst_4
        //    714: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    717: l2i
        //    718: istore  14
        //    720: aload_3
        //    721: iconst_1
        //    722: dup2
        //    723: iaload
        //    724: iconst_4
        //    725: iadd
        //    726: iastore
        //    727: new  #91 // java.lang.String
        //    730: dup
        //    731: aload_2
        //    732: aload_3
        //    733: iconst_1
        //    734: iaload
        //    735: iload  14
        //    737: getstatic  #139 // java.nio.charset.StandardCharsets.UTF_8:Ljava/nio/charset/Charset;
        //    740: invokespecial  #193 // java.lang.String.<init>:([BIILjava/nio/charset/Charset;)V
        //    743: astore  5
        //    745: aload_3
        //    746: iconst_1
        //    747: dup2
        //    748: iaload
        //    749: iload  14
        //    751: iconst_1
        //    752: iadd
        //    753: iadd
        //    754: iastore
        //    755: goto  873 (offset +118)
        //    758: aload_0
        //    759: aload_2
        //    760: aload_3
        //    761: iconst_1
        //    762: iaload
        //    763: iconst_4
        //    764: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    767: l2i
        //    768: istore  14
        //    770: aload_3
        //    771: iconst_1
        //    772: dup2
        //    773: iaload
        //    774: iconst_4
        //    775: iadd
        //    776: iastore
        //    777: new  #117 // org.freedesktop.dbus.ObjectPath
        //    780: dup
        //    781: aload_0
        //    782: invokevirtual  #272 // org.freedesktop.dbus.messages.Message.getSource:()Ljava/lang/String;
        //    785: new  #91 // java.lang.String
        //    788: dup
        //    789: aload_2
        //    790: aload_3
        //    791: iconst_1
        //    792: iaload
        //    793: iload  14
        //    795: invokespecial  #192 // java.lang.String.<init>:([BII)V
        //    798: invokespecial  #236 // org.freedesktop.dbus.ObjectPath.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //    801: astore  5
        //    803: aload_3
        //    804: iconst_1
        //    805: dup2
        //    806: iaload
        //    807: iload  14
        //    809: iconst_1
        //    810: iadd
        //    811: iadd
        //    812: iastore
        //    813: goto  873 (offset +60)
        //    816: aload_2
        //    817: aload_3
        //    818: iconst_1
        //    819: dup2
        //    820: iaload
        //    821: dup_x2
        //    822: iconst_1
        //    823: iadd
        //    824: iastore
        //    825: baload
        //    826: sipush  255
        //    829: iand
        //    830: istore  14
        //    832: new  #91 // java.lang.String
        //    835: dup
        //    836: aload_2
        //    837: aload_3
        //    838: iconst_1
        //    839: iaload
        //    840: iload  14
        //    842: invokespecial  #192 // java.lang.String.<init>:([BII)V
        //    845: astore  5
        //    847: aload_3
        //    848: iconst_1
        //    849: dup2
        //    850: iaload
        //    851: iload  14
        //    853: iconst_1
        //    854: iadd
        //    855: iadd
        //    856: iastore
        //    857: goto  873 (offset +16)
        //    860: new  #121 // org.freedesktop.dbus.exceptions.UnknownTypeCodeException
        //    863: dup
        //    864: aload_1
        //    865: aload_3
        //    866: iconst_0
        //    867: iaload
        //    868: baload
        //    869: invokespecial  #239 // org.freedesktop.dbus.exceptions.UnknownTypeCodeException.<init>:(B)V
        //    872: athrow
        //    873: aload_0
        //    874: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //    877: invokeinterface  #336 // org.slf4j.Logger.isTraceEnabled:()Z, count 1
        //    882: ifeq  944 (offset +62)
        //    885: aload  5
        //    887: instanceof  #72 // [Ljava.lang.Object;
        //    890: ifeq  925 (offset +35)
        //    893: aload  5
        //    895: checkcast  #72 // [Ljava.lang.Object;
        //    898: astore  6
        //    900: aload_0
        //    901: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //    904: ldc  #25 // 'Extracted: {} (now at {})'
        //    906: aload  6
        //    908: invokestatic  #218 // java.util.Arrays.deepToString:([Ljava/lang/Object;)Ljava/lang/String;
        //    911: aload_3
        //    912: iconst_1
        //    913: iaload
        //    914: invokestatic  #179 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    917: invokeinterface  #339 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    922: goto  944 (offset +22)
        //    925: aload_0
        //    926: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //    929: ldc  #25 // 'Extracted: {} (now at {})'
        //    931: aload  5
        //    933: aload_3
        //    934: iconst_1
        //    935: iaload
        //    936: invokestatic  #179 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    939: invokeinterface  #339 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    944: aload  5
        //    946: areturn
    }

  private Object extractByte(byte[] arg0, int[] arg1) {
        arg1[1] = arg1[1] + 1;
        Byte var3 = Byte.valueOf(arg0[arg1[1]]);
        return var3;
    }

  private Object extractStruct(byte[] arg0, byte[] arg1, int[] arg2, Message_ExtractMethod arg3) {
        ArrayList var6 = new ArrayList();
        while (true) {
            arg2[0] = arg2[0] + 1;
            if (arg0[arg2[0] + 1] == 41) {
                break;
            }
            var6.add(arg3.extractOne(arg0, arg1, arg2, true));
            continue;
        }
        Object[] var5 = var6.toArray();
        return var5;
    }

  private Object extractArray(byte[] arg0, byte[] arg1, int[] arg2, boolean arg3, Message_ExtractMethod arg4) {
        long var7 = demarshallint(arg1, arg2[1], 4);
        logger.trace("Reading array of size: {}", Long.valueOf(var7));
        arg2[1] = arg2[1] + 4;
        arg2[0] = arg2[0] + 1;
        byte var9 = ((byte) getAlignment(arg0[arg2[0] + 1]));
        arg2[1] = align(arg2[1], arg0[arg2[0]]);
        int var10 = ((int) (var7 / ((long) var9)));
        if (var10 <= 67108864) {
            Object var6 = optimizePrimitives(arg0, arg1, arg2, var7, var9, var10, arg4);
            if (arg3) {
                if (!(var6 instanceof List)) {
                    if (!(var6 instanceof Map)) {
                        var6 = ArrayFrob.listify(var6);
                    }
                }
            }
            return var6;
        } else {
            throw new MarshallingException("Arrays must not exceed 67108864");
        }
    }

  private Object extractVariant(byte[] arg0, int[] arg1, BiFunction arg2) {
        int[] var5 = new int[]{0, arg1[1]};
        String var6 = ((String) extract("g", arg0, var5)[0]);
        var5[0] = 0;
        Object var4 = arg2.apply(var6, extract(var6, arg0, var5)[0]);
        arg1[1] = var5[1];
        return var4;
    }

  private Object optimizePrimitives(byte[] arg0, byte[] arg1, int[] arg2, long arg3, byte arg4, int arg5, Message_ExtractMethod arg6) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: aload_3
        //      2: iconst_0
        //      3: iaload
        //      4: baload
        //      5: tableswitch  default->575, 98->311, 99->575, 100->427, 101->575, 102->372, 103->575, 104->575, 105->208, 106->575, 107->575, 108->575, 109->575, 110->155, 111->575, 112->575, 113->575, 114->575, 115->575, 116->575, 117->575, 118->575, 119->575, 120->260, 121->124, 122->575, 123->481
        //    124: iload  7
        //    126: newarray  byte
        //    128: astore  9
        //    130: aload_2
        //    131: aload_3
        //    132: iconst_1
        //    133: iaload
        //    134: aload  9
        //    136: iconst_0
        //    137: iload  7
        //    139: invokestatic  #208 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    142: aload_3
        //    143: iconst_1
        //    144: dup2
        //    145: iaload
        //    146: i2l
        //    147: lload  4
        //    149: ladd
        //    150: l2i
        //    151: iastore
        //    152: goto  644 (offset +492)
        //    155: iload  7
        //    157: newarray  short
        //    159: astore  9
        //    161: iconst_0
        //    162: istore  10
        //    164: iload  10
        //    166: iload  7
        //    168: if_icmpge  205 (offset +37)
        //    171: aload  9
        //    173: checkcast  #74 // [S
        //    176: iload  10
        //    178: aload_0
        //    179: aload_2
        //    180: aload_3
        //    181: iconst_1
        //    182: iaload
        //    183: iload  6
        //    185: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    188: l2i
        //    189: i2s
        //    190: sastore
        //    191: iinc  10, 1
        //    194: aload_3
        //    195: iconst_1
        //    196: dup2
        //    197: iaload
        //    198: iload  6
        //    200: iadd
        //    201: iastore
        //    202: goto  164 (offset -38)
        //    205: goto  644 (offset +439)
        //    208: iload  7
        //    210: newarray  int
        //    212: astore  9
        //    214: iconst_0
        //    215: istore  10
        //    217: iload  10
        //    219: iload  7
        //    221: if_icmpge  257 (offset +36)
        //    224: aload  9
        //    226: checkcast  #70 // [I
        //    229: iload  10
        //    231: aload_0
        //    232: aload_2
        //    233: aload_3
        //    234: iconst_1
        //    235: iaload
        //    236: iload  6
        //    238: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    241: l2i
        //    242: iastore
        //    243: iinc  10, 1
        //    246: aload_3
        //    247: iconst_1
        //    248: dup2
        //    249: iaload
        //    250: iload  6
        //    252: iadd
        //    253: iastore
        //    254: goto  217 (offset -37)
        //    257: goto  644 (offset +387)
        //    260: iload  7
        //    262: newarray  long
        //    264: astore  9
        //    266: iconst_0
        //    267: istore  10
        //    269: iload  10
        //    271: iload  7
        //    273: if_icmpge  308 (offset +35)
        //    276: aload  9
        //    278: checkcast  #71 // [J
        //    281: iload  10
        //    283: aload_0
        //    284: aload_2
        //    285: aload_3
        //    286: iconst_1
        //    287: iaload
        //    288: iload  6
        //    290: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    293: lastore
        //    294: iinc  10, 1
        //    297: aload_3
        //    298: iconst_1
        //    299: dup2
        //    300: iaload
        //    301: iload  6
        //    303: iadd
        //    304: iastore
        //    305: goto  269 (offset -36)
        //    308: goto  644 (offset +336)
        //    311: iload  7
        //    313: newarray  boolean
        //    315: astore  9
        //    317: iconst_0
        //    318: istore  10
        //    320: iload  10
        //    322: iload  7
        //    324: if_icmpge  369 (offset +45)
        //    327: aload  9
        //    329: checkcast  #75 // [Z
        //    332: iload  10
        //    334: lconst_1
        //    335: aload_0
        //    336: aload_2
        //    337: aload_3
        //    338: iconst_1
        //    339: iaload
        //    340: iload  6
        //    342: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    345: lcmp
        //    346: ifne  353 (offset +7)
        //    349: iconst_1
        //    350: goto  354 (offset +4)
        //    353: iconst_0
        //    354: bastore
        //    355: iinc  10, 1
        //    358: aload_3
        //    359: iconst_1
        //    360: dup2
        //    361: iaload
        //    362: iload  6
        //    364: iadd
        //    365: iastore
        //    366: goto  320 (offset -46)
        //    369: goto  644 (offset +275)
        //    372: iload  7
        //    374: newarray  float
        //    376: astore  9
        //    378: iconst_0
        //    379: istore  10
        //    381: iload  10
        //    383: iload  7
        //    385: if_icmpge  424 (offset +39)
        //    388: aload  9
        //    390: checkcast  #69 // [F
        //    393: iload  10
        //    395: aload_0
        //    396: aload_2
        //    397: aload_3
        //    398: iconst_1
        //    399: iaload
        //    400: iload  6
        //    402: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    405: l2i
        //    406: invokestatic  #176 // java.lang.Float.intBitsToFloat:(I)F
        //    409: fastore
        //    410: iinc  10, 1
        //    413: aload_3
        //    414: iconst_1
        //    415: dup2
        //    416: iaload
        //    417: iload  6
        //    419: iadd
        //    420: iastore
        //    421: goto  381 (offset -40)
        //    424: goto  644 (offset +220)
        //    427: iload  7
        //    429: newarray  double
        //    431: astore  9
        //    433: iconst_0
        //    434: istore  10
        //    436: iload  10
        //    438: iload  7
        //    440: if_icmpge  478 (offset +38)
        //    443: aload  9
        //    445: checkcast  #68 // [D
        //    448: iload  10
        //    450: aload_0
        //    451: aload_2
        //    452: aload_3
        //    453: iconst_1
        //    454: iaload
        //    455: iload  6
        //    457: invokevirtual  #250 // org.freedesktop.dbus.messages.Message.demarshallint:([BII)J
        //    460: invokestatic  #172 // java.lang.Double.longBitsToDouble:(J)D
        //    463: dastore
        //    464: iinc  10, 1
        //    467: aload_3
        //    468: iconst_1
        //    469: dup2
        //    470: iaload
        //    471: iload  6
        //    473: iadd
        //    474: iastore
        //    475: goto  436 (offset -39)
        //    478: goto  644 (offset +166)
        //    481: aload_0
        //    482: aload_1
        //    483: aload_3
        //    484: lload  4
        //    486: invokevirtual  #291 // org.freedesktop.dbus.messages.Message.prepareCollection:([B[IJ)I
        //    489: istore  10
        //    491: aload_3
        //    492: iconst_1
        //    493: iaload
        //    494: i2l
        //    495: lload  4
        //    497: ladd
        //    498: lstore  11
        //    500: new  #101 // java.util.ArrayList
        //    503: dup
        //    504: invokespecial  #216 // java.util.ArrayList.<init>:()V
        //    507: astore  13
        //    509: aload_3
        //    510: iconst_1
        //    511: iaload
        //    512: i2l
        //    513: lload  11
        //    515: lcmp
        //    516: ifge  549 (offset +33)
        //    519: aload_3
        //    520: iconst_0
        //    521: iload  10
        //    523: iastore
        //    524: aload  13
        //    526: aload  8
        //    528: aload_1
        //    529: aload_2
        //    530: aload_3
        //    531: iconst_1
        //    532: invokeinterface  #331 // org.freedesktop.dbus.messages.Message$ExtractMethod.extractOne:([B[B[IZ)Ljava/lang/Object;, count 5
        //    537: checkcast  #72 // [Ljava.lang.Object;
        //    540: invokeinterface  #312 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    545: pop
        //    546: goto  509 (offset -37)
        //    549: new  #114 // org.freedesktop.dbus.DBusMap
        //    552: dup
        //    553: aload  13
        //    555: iconst_0
        //    556: anewarray  #72 // [Ljava.lang.Object;
        //    559: invokeinterface  #319 // java.util.List.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;, count 2
        //    564: checkcast  #76 // [[Ljava.lang.Object;
        //    567: invokespecial  #232 // org.freedesktop.dbus.DBusMap.<init>:([[Ljava/lang/Object;)V
        //    570: astore  9
        //    572: goto  644 (offset +72)
        //    575: aload_0
        //    576: aload_1
        //    577: aload_3
        //    578: lload  4
        //    580: invokevirtual  #291 // org.freedesktop.dbus.messages.Message.prepareCollection:([B[IJ)I
        //    583: istore  10
        //    585: aload_3
        //    586: iconst_1
        //    587: iaload
        //    588: i2l
        //    589: lload  4
        //    591: ladd
        //    592: lstore  11
        //    594: new  #101 // java.util.ArrayList
        //    597: dup
        //    598: invokespecial  #216 // java.util.ArrayList.<init>:()V
        //    601: astore  14
        //    603: aload_3
        //    604: iconst_1
        //    605: iaload
        //    606: i2l
        //    607: lload  11
        //    609: lcmp
        //    610: ifge  640 (offset +30)
        //    613: aload_3
        //    614: iconst_0
        //    615: iload  10
        //    617: iastore
        //    618: aload  14
        //    620: aload  8
        //    622: aload_1
        //    623: aload_2
        //    624: aload_3
        //    625: iconst_1
        //    626: invokeinterface  #331 // org.freedesktop.dbus.messages.Message$ExtractMethod.extractOne:([B[B[IZ)Ljava/lang/Object;, count 5
        //    631: invokeinterface  #312 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    636: pop
        //    637: goto  603 (offset -34)
        //    640: aload  14
        //    642: astore  9
        //    644: aload  9
        //    646: areturn
    }

  private int prepareCollection(byte[] arg0, int[] arg1, long arg2) {
        if (0L == arg2) {
            ArrayList var5 = new ArrayList();
            byte[] var6 = new byte[arg0.length - arg1[0]];
            System.arraycopy(arg0, arg1[0], var6, 0, var6.length);
            String var7 = new String(var6);
            int var8 = Marshalling.getJavaType(var7, var5, 1) - 1;
            arg1[0] = arg1[0] + var8;
            logger.trace("Aligned type: {} {} {}", new Object[]{var7, Integer.valueOf(var8), Integer.valueOf(arg1[0])});
        }
        return arg1[0];
    }

  protected Object[] extract(String arg0, byte[] arg1, int arg2) {
        return extract(arg0, arg1, new int[]{0, arg2});
    }

  protected Object[] extract(String arg0, byte[] arg1, int[] arg2) {
        return extract(arg0, arg1, arg2, (lp0, lp1, lp2, lp3) -> extractOne(lp0, lp1, lp2, lp3));
    }

   Object[] extract(String arg0, byte[] arg1, int[] arg2, Message_ExtractMethod arg3) {
        logger.trace("extract({},#{}, {{},{}}", new Object[]{arg0, Integer.valueOf(arg1.length), Integer.valueOf(arg2[0]), Integer.valueOf(arg2[1])});
        ArrayList var5 = new ArrayList();
        byte[] var6 = arg0.getBytes();
        int[] var7 = arg2;
        while (var7[0] < var6.length) {
            var5.add(arg3.extractOne(var6, arg1, var7, false));
            var7[0] = var7[0] + 1;
            continue;
        }
        return var5.toArray();
    }

  public String getSource() {
        return ((String) getHeader(7));
    }

  public String getDestination() {
        return ((String) getHeader(6));
    }

  public String getInterface() {
        return ((String) getHeader(2));
    }

  public String getPath() {
        Object var1 = getHeader(1);
        if (null != var1) {
            return var1.toString();
        } else {
            return null;
        }
    }

  public String getName() {
        if (!(this instanceof Error)) {
            return ((String) getHeader(3));
        } else {
            return ((String) getHeader(4));
        }
    }

  public String getSig() {
        return ((String) getHeader(8));
    }

  public int getFlags() {
        return flags;
    }

  public synchronized long getSerial() {
        return serial;
    }

  public long getReplySerial() {
        Number var1 = ((Number) getHeader(5));
        if (null != var1) {
            return var1.longValue();
        } else {
            return 0L;
        }
    }

  public Object[] getParameters() {
        if (null == args) {
            if (null != body) {
                String var1 = getSig();
                args = null == var1 ? new Object[0] : 0 == body.length ? new Object[0] : extract(var1, body, 0);
            }
        }
        return args;
    }

  public void setArgs(Object[] arg0) {
        args = arg0;
    }

  public void setSource(String arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aconst_null
        //      1: aload_0
        //      2: getfield  #143 // org.freedesktop.dbus.messages.Message.body:[B
        //      5: if_acmpeq  311 (offset +306)
        //      8: aload_0
        //      9: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //     12: ldc  #43 // 'Setting source'
        //     14: invokeinterface  #337 // org.slf4j.Logger.trace:(Ljava/lang/String;)V, count 2
        //     19: aload_0
        //     20: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //     23: invokeinterface  #336 // org.slf4j.Logger.isTraceEnabled:()Z, count 1
        //     28: aload_0
        //     29: invokedynamic  #352 // invokedynamic run:(Lorg/freedesktop/dbus/messages/Message;)Ljava/lang/Runnable;
        //     34: invokestatic  #308 // org.freedesktop.dbus.utils.LoggingHelper.logIf:(ZLjava/lang/Runnable;)V
        //     37: aload_0
        //     38: bipush  20
        //     40: anewarray  #67 // [B
        //     43: putfield  #159 // org.freedesktop.dbus.messages.Message.wiredata:[[B
        //     46: aload_0
        //     47: iconst_0
        //     48: putfield  #145 // org.freedesktop.dbus.messages.Message.bufferuse:I
        //     51: aload_0
        //     52: lconst_0
        //     53: putfield  #146 // org.freedesktop.dbus.messages.Message.bytecounter:J
        //     56: aload_0
        //     57: bipush  12
        //     59: invokevirtual  #290 // org.freedesktop.dbus.messages.Message.preallocate:(I)V
        //     62: aload_0
        //     63: ldc  #64 // 'yyyyuu'
        //     65: bipush  6
        //     67: anewarray  #89 // java.lang.Object
        //     70: dup
        //     71: iconst_0
        //     72: aload_0
        //     73: getfield  #142 // org.freedesktop.dbus.messages.Message.big:Z
        //     76: ifeq  84 (offset +8)
        //     79: bipush  66
        //     81: goto  86 (offset +5)
        //     84: bipush  108
        //     86: invokestatic  #163 // java.lang.Byte.valueOf:(B)Ljava/lang/Byte;
        //     89: aastore
        //     90: dup
        //     91: iconst_1
        //     92: aload_0
        //     93: getfield  #158 // org.freedesktop.dbus.messages.Message.type:B
        //     96: invokestatic  #163 // java.lang.Byte.valueOf:(B)Ljava/lang/Byte;
        //     99: aastore
        //    100: dup
        //    101: iconst_2
        //    102: aload_0
        //    103: getfield  #149 // org.freedesktop.dbus.messages.Message.flags:B
        //    106: invokestatic  #163 // java.lang.Byte.valueOf:(B)Ljava/lang/Byte;
        //    109: aastore
        //    110: dup
        //    111: iconst_3
        //    112: aload_0
        //    113: getfield  #156 // org.freedesktop.dbus.messages.Message.protover:B
        //    116: invokestatic  #163 // java.lang.Byte.valueOf:(B)Ljava/lang/Byte;
        //    119: aastore
        //    120: dup
        //    121: iconst_4
        //    122: aload_0
        //    123: getfield  #144 // org.freedesktop.dbus.messages.Message.bodylen:J
        //    126: invokestatic  #180 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //    129: aastore
        //    130: dup
        //    131: iconst_5
        //    132: aload_0
        //    133: invokevirtual  #270 // org.freedesktop.dbus.messages.Message.getSerial:()J
        //    136: invokestatic  #180 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //    139: aastore
        //    140: invokevirtual  #244 // org.freedesktop.dbus.messages.Message.append:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    143: aload_0
        //    144: getfield  #150 // org.freedesktop.dbus.messages.Message.headers:[Ljava/lang/Object;
        //    147: bipush  7
        //    149: aload_1
        //    150: aastore
        //    151: aload_0
        //    152: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //    155: invokeinterface  #336 // org.slf4j.Logger.isTraceEnabled:()Z, count 1
        //    160: aload_0
        //    161: invokedynamic  #353 // invokedynamic run:(Lorg/freedesktop/dbus/messages/Message;)Ljava/lang/Runnable;
        //    166: invokestatic  #308 // org.freedesktop.dbus.utils.LoggingHelper.logIf:(ZLjava/lang/Runnable;)V
        //    169: new  #101 // java.util.ArrayList
        //    172: dup
        //    173: aload_0
        //    174: getfield  #150 // org.freedesktop.dbus.messages.Message.headers:[Ljava/lang/Object;
        //    177: arraylength
        //    178: invokespecial  #217 // java.util.ArrayList.<init>:(I)V
        //    181: astore_2
        //    182: iconst_0
        //    183: istore_3
        //    184: iload_3
        //    185: aload_0
        //    186: getfield  #150 // org.freedesktop.dbus.messages.Message.headers:[Ljava/lang/Object;
        //    189: arraylength
        //    190: if_icmpge  264 (offset +74)
        //    193: aload_0
        //    194: getfield  #150 // org.freedesktop.dbus.messages.Message.headers:[Ljava/lang/Object;
        //    197: iload_3
        //    198: aaload
        //    199: astore  4
        //    201: aload  4
        //    203: ifnonnull  209 (offset +6)
        //    206: goto  258 (offset +52)
        //    209: iload_3
        //    210: bipush  8
        //    212: if_icmpne  235 (offset +23)
        //    215: aload_2
        //    216: aload_0
        //    217: bipush  8
        //    219: ldc  #56 // 'g'
        //    221: aload  4
        //    223: invokevirtual  #249 // org.freedesktop.dbus.messages.Message.createHeaderArgs:(BLjava/lang/String;Ljava/lang/Object;)[Ljava/lang/Object;
        //    226: invokeinterface  #312 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    231: pop
        //    232: goto  258 (offset +26)
        //    235: aload_2
        //    236: iconst_2
        //    237: anewarray  #89 // java.lang.Object
        //    240: dup
        //    241: iconst_0
        //    242: iload_3
        //    243: invokestatic  #179 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    246: aastore
        //    247: dup
        //    248: iconst_1
        //    249: aload  4
        //    251: aastore
        //    252: invokeinterface  #312 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    257: pop
        //    258: iinc  3, 1
        //    261: goto  184 (offset -77)
        //    264: aload_0
        //    265: ldc  #53 // 'a(yv)'
        //    267: iconst_1
        //    268: anewarray  #89 // java.lang.Object
        //    271: dup
        //    272: iconst_0
        //    273: aload_2
        //    274: aastore
        //    275: invokevirtual  #244 // org.freedesktop.dbus.messages.Message.append:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    278: aload_0
        //    279: getfield  #151 // org.freedesktop.dbus.messages.Message.logger:Lorg/slf4j/Logger;
        //    282: invokeinterface  #336 // org.slf4j.Logger.isTraceEnabled:()Z, count 1
        //    287: aload_0
        //    288: aload_2
        //    289: invokedynamic  #354 // invokedynamic run:(Lorg/freedesktop/dbus/messages/Message;Ljava/util/List;)Ljava/lang/Runnable;
        //    294: invokestatic  #308 // org.freedesktop.dbus.utils.LoggingHelper.logIf:(ZLjava/lang/Runnable;)V
        //    297: aload_0
        //    298: bipush  8
        //    300: invokevirtual  #289 // org.freedesktop.dbus.messages.Message.pad:(B)V
        //    303: aload_0
        //    304: aload_0
        //    305: getfield  #143 // org.freedesktop.dbus.messages.Message.body:[B
        //    308: invokevirtual  #246 // org.freedesktop.dbus.messages.Message.appendBytes:([B)V
        //    311: return
    }

   String dumpWireData() {
        StringBuilder var1 = new StringBuilder(System.lineSeparator());
        int var2 = 0;
        while (var2 < wiredata.length) {
            Object var3 = wiredata[var2];
            if (var3 != null) {
                String var4 = "Wiredata[" + var2 + "]";
                String var5 = Hexdump.format(((byte[]) var3), 80);
                String[] var6 = var5.split("\n");
                var1.append(var4).append(": ").append(((String) var6[0])).append(System.lineSeparator());
                if (var6.length > 1) {
                    var1.append(((String) Arrays.stream(var6).skip(1L).map(lp0 -> lambda$dumpWireData$10(var4, ((String) lp0))).collect(Collectors.joining(System.lineSeparator()))));
                    var1.append(System.lineSeparator());
                }
            }
            ++var2;
            continue;
        }
        return var1.toString();
    }

  public byte getType() {
        return type;
    }

  public byte getEndianess() {
        if (!endianWasSet) {
            return 0;
        } else {
            return !big ? 108 : 66;
        }
    }

  protected Object[] createHeaderArgs(byte arg0, String arg1, Object arg2) {
        getHeader()[arg0] = arg2;
        Object[] __obj1 = new Object[2];
        __obj1[0] = Byte.valueOf(arg0);
        __obj1[1] = new Object[]{arg1, arg2};
        return __obj1;
    }

  protected void padAndMarshall(List arg0, long arg1, String arg2, Object[] arg3) {
        byte[] var6 = new byte[4];
        appendBytes(var6);
        append("ua(yv)", new Object[]{Long.valueOf(arg1), arg0.toArray()});
        pad(8);
        long var7 = getByteCounter();
        if (null != arg2) {
            append(arg2, arg3);
        }
        Object[] __obj2 = new Object[4];
        __obj2[0] = arg2;
        __obj2[1] = Long.valueOf(var7);
        __obj2[2] = Long.valueOf(getByteCounter());
        __obj2[3] = Long.valueOf(getByteCounter() - var7);
        logger.trace("Appended body, type: {} start: {} end: {} size: {}", __obj2);
        marshallint(getByteCounter() - var7, var6, 0, 4);
        LoggingHelper.logIf(logger.isTraceEnabled(), () -> lambda$padAndMarshall$11(var6));
    }

  public static long demarshallint(byte[] arg0, int arg1, byte arg2, int arg3) {
        return arg2 != 66 ? demarshallintLittle(arg0, arg1, arg3) : demarshallintBig(arg0, arg1, arg3);
    }

  public static long demarshallintBig(byte[] arg0, int arg1, int arg2) {
        long var3 = 0L;
        int var5 = 0;
        while (var5 < arg2) {
            var3 = var3 << 8;
            var3 = var3 | ((long) (arg0[arg1 + var5] & 255));
            ++var5;
            continue;
        }
        return var3;
    }

  public static long demarshallintLittle(byte[] arg0, int arg1, int arg2) {
        long var3 = 0L;
        int var5 = arg2 - 1;
        while (var5 >= 0) {
            var3 = var3 << 8;
            var3 = var3 | ((long) (arg0[arg1 + var5] & 255));
            --var5;
            continue;
        }
        return var3;
    }

  public static void marshallintBig(long arg0, byte[] arg1, int arg2, int arg3) {
        long var5 = arg0;
        int var7 = arg3 - 1;
        while (var7 >= 0) {
            arg1[var7 + arg2] = ((byte) ((int) (var5 & 255L)));
            var5 = var5 >> 8;
            --var7;
            continue;
        }
    }

  public static void marshallintLittle(long arg0, byte[] arg1, int arg2, int arg3) {
        long var5 = arg0;
        int var7 = 0;
        while (var7 < arg3) {
            arg1[var7 + arg2] = ((byte) ((int) (var5 & 255L)));
            var5 = var5 >> 8;
            ++var7;
            continue;
        }
    }

  public static int getAlignment(byte arg0) {
        int __stk1;
        switch (arg0) {
            case 2:
            case 110:
            case 113:
                __stk1 = 2;
                break;
            case 4:
            case 97:
            case 98:
            case 102:
            case 104:
            case 105:
            case 111:
            case 115:
            case 117:
                __stk1 = 4;
                break;
            case 8:
            case 40:
            case 41:
            case 100:
            case 101:
            case 114:
            case 116:
            case 120:
            case 123:
            case 125:
                __stk1 = 8;
                break;
            case 1:
            case 103:
            case 118:
            case 121:
                __stk1 = 1;
                break;
            default:
                __stk1 = 1;
        }
        return __stk1;
    }

  public static String getHeaderFieldName(byte arg0) {
        String __stk1;
        switch (arg0) {
            case 1:
                __stk1 = "Path";
                break;
            case 2:
                __stk1 = "Interface";
                break;
            case 3:
                __stk1 = "Member";
                break;
            case 4:
                __stk1 = "Error Name";
                break;
            case 5:
                __stk1 = "Reply Serial";
                break;
            case 6:
                __stk1 = "Destination";
                break;
            case 7:
                __stk1 = "Sender";
                break;
            case 8:
                __stk1 = "Signature";
                break;
            case 9:
                __stk1 = "Unix FD";
                break;
            default:
                __stk1 = "Invalid";
        }
        return __stk1;
    }

  private void lambda$padAndMarshall$11(byte[] arg0) {
        logger.trace("marshalled size ({}): {}", arg0, Hexdump.format(arg0));
    }

  private static String lambda$dumpWireData$10(String arg0, String arg1) {
        return String.format("%s: %80s", new Object[]{arg0, arg1});
    }

  private void lambda$setSource$9(List arg0) {
        logger.trace("New header: {}", LoggingHelper.arraysVeryDeepString(arg0.toArray()));
        logger.trace("WireData after: {}", dumpWireData());
    }

  private void lambda$setSource$8() {
        logger.trace("WireData first append: {}", dumpWireData());
    }

  private void lambda$setSource$7() {
        logger.trace("WireData before: {}", dumpWireData());
    }

  private static Object lambda$extractOne$6(String arg0, Object arg1) {
        return new Variant(arg1, arg0);
    }

  private void lambda$extractOne$5(byte[] arg0, int[] arg1, byte[] arg2) {
        logger.trace("Extracting Dict Entry ({}) from: {}", Hexdump.toAscii(arg0, arg1[0], arg0.length - arg1[0]), Hexdump.toHex(arg2, arg1[1], arg2.length - arg1[1], true));
    }

  private static Object lambda$readHeaderVariants$4(String arg0, Object arg1) {
        return arg1;
    }

  private void lambda$append$3(String arg0, Object[] arg1) {
        logger.debug("Appending sig: {} data: {}", arg0, LoggingHelper.arraysVeryDeepString(arg1));
    }

  private void lambda$marshallint$2(long arg0, byte[] arg1, int arg2, int arg3) {
        logger.trace("Marshalled int {} to {}", Long.valueOf(arg0), Hexdump.toHex(arg1, arg2, arg3, true));
    }

  private void lambda$populate$1(Object[] arg0) {
        logger.trace("Extracted objects: {}", LoggingHelper.arraysVeryDeepString(arg0));
    }

  private void lambda$populate$0(byte[] arg0) {
        logger.trace("Message header: {}", Hexdump.toAscii(arg0));
    }

}