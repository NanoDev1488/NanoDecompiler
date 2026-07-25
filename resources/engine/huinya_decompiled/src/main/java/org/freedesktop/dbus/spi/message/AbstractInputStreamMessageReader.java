// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader
package org.freedesktop.dbus.spi.message;

import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Objects;
import org.freedesktop.dbus.messages.Message;
import org.freedesktop.dbus.spi.message.IMessageReader;
import org.freedesktop.dbus.spi.message.ISocketProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractInputStreamMessageReader implements IMessageReader {

    // ---- поля ----
  private final Logger logger;
  private final int[] len;
  private final byte[] buf;
  private final byte[] tbuf;
  private final SocketChannel inputChannel;
  private byte[] header;
  private byte[] body;
  private final ISocketProvider socketProviderImpl;

  protected AbstractInputStreamMessageReader(SocketChannel arg0, ISocketProvider arg1) { // было: <init>
        super();
        logger = LoggerFactory.getLogger(getClass());
        socketProviderImpl = ((ISocketProvider) Objects.requireNonNull(arg1, "ISocketProvider implementation required"));
        inputChannel = ((SocketChannel) Objects.requireNonNull(arg0, "SocketChannel required"));
        len = new int[4];
        tbuf = new byte[4];
        buf = new byte[12];
        len[1] = 0;
        len[0] = 0;
    }

  public final Message readMessage() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //      4: iconst_0
        //      5: iaload
        //      6: bipush  12
        //      8: if_icmpge  77 (offset +69)
        //     11: aload_0
        //     12: getfield  #42 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.buf:[B
        //     15: aload_0
        //     16: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //     19: iconst_0
        //     20: iaload
        //     21: bipush  12
        //     23: aload_0
        //     24: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //     27: iconst_0
        //     28: iaload
        //     29: isub
        //     30: invokestatic  #59 // java.nio.ByteBuffer.wrap:([BII)Ljava/nio/ByteBuffer;
        //     33: astore_1
        //     34: aload_0
        //     35: getfield  #44 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.inputChannel:Ljava/nio/channels/SocketChannel;
        //     38: aload_1
        //     39: invokevirtual  #62 // java.nio.channels.SocketChannel.read:(Ljava/nio/ByteBuffer;)I
        //     42: istore_2
        //     43: iload_2
        //     44: ifge  61 (offset +17)
        //     47: new  #16 // java.io.EOFException
        //     50: dup
        //     51: iload_2
        //     52: invokedynamic  #76 // invokedynamic makeConcatWithConstants:(I)Ljava/lang/String;
        //     57: invokespecial  #49 // java.io.EOFException.<init>:(Ljava/lang/String;)V
        //     60: athrow
        //     61: aload_0
        //     62: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //     65: iconst_0
        //     66: dup2
        //     67: iaload
        //     68: iload_2
        //     69: iadd
        //     70: iastore
        //     71: goto  77 (offset +6)
        //     74: astore_1
        //     75: aconst_null
        //     76: areturn
        //     77: aload_0
        //     78: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //     81: iconst_0
        //     82: iaload
        //     83: ifne  88 (offset +5)
        //     86: aconst_null
        //     87: areturn
        //     88: aload_0
        //     89: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //     92: iconst_0
        //     93: iaload
        //     94: bipush  12
        //     96: if_icmpge  121 (offset +25)
        //     99: aload_0
        //    100: getfield  #46 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.logger:Lorg/slf4j/Logger;
        //    103: ldc  #10 // 'Only got {} of 12 bytes of header'
        //    105: aload_0
        //    106: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    109: iconst_0
        //    110: iaload
        //    111: invokestatic  #52 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    114: invokeinterface  #73 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    119: aconst_null
        //    120: areturn
        //    121: aload_0
        //    122: getfield  #42 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.buf:[B
        //    125: iconst_3
        //    126: baload
        //    127: istore_1
        //    128: iload_1
        //    129: iconst_1
        //    130: if_icmple  157 (offset +27)
        //    133: new  #33 // org.freedesktop.dbus.exceptions.MessageProtocolVersionException
        //    136: dup
        //    137: ldc  #14 // 'Protocol version %s is unsupported'
        //    139: iconst_1
        //    140: anewarray  #20 // java.lang.Object
        //    143: dup
        //    144: iconst_0
        //    145: iload_1
        //    146: invokestatic  #50 // java.lang.Byte.valueOf:(B)Ljava/lang/Byte;
        //    149: aastore
        //    150: invokestatic  #55 // java.lang.String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    153: invokespecial  #65 // org.freedesktop.dbus.exceptions.MessageProtocolVersionException.<init>:(Ljava/lang/String;)V
        //    156: athrow
        //    157: aload_0
        //    158: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    161: iconst_1
        //    162: iaload
        //    163: iconst_4
        //    164: if_icmpge  230 (offset +66)
        //    167: aload_0
        //    168: getfield  #44 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.inputChannel:Ljava/nio/channels/SocketChannel;
        //    171: aload_0
        //    172: getfield  #48 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.tbuf:[B
        //    175: aload_0
        //    176: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    179: iconst_1
        //    180: iaload
        //    181: iconst_4
        //    182: aload_0
        //    183: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    186: iconst_1
        //    187: iaload
        //    188: isub
        //    189: invokestatic  #59 // java.nio.ByteBuffer.wrap:([BII)Ljava/nio/ByteBuffer;
        //    192: invokevirtual  #62 // java.nio.channels.SocketChannel.read:(Ljava/nio/ByteBuffer;)I
        //    195: istore_2
        //    196: iload_2
        //    197: ifge  214 (offset +17)
        //    200: new  #16 // java.io.EOFException
        //    203: dup
        //    204: iload_2
        //    205: invokedynamic  #77 // invokedynamic makeConcatWithConstants:(I)Ljava/lang/String;
        //    210: invokespecial  #49 // java.io.EOFException.<init>:(Ljava/lang/String;)V
        //    213: athrow
        //    214: aload_0
        //    215: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    218: iconst_1
        //    219: dup2
        //    220: iaload
        //    221: iload_2
        //    222: iadd
        //    223: iastore
        //    224: goto  230 (offset +6)
        //    227: astore_2
        //    228: aconst_null
        //    229: areturn
        //    230: aload_0
        //    231: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    234: iconst_1
        //    235: iaload
        //    236: iconst_4
        //    237: if_icmpge  262 (offset +25)
        //    240: aload_0
        //    241: getfield  #46 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.logger:Lorg/slf4j/Logger;
        //    244: ldc  #11 // 'Only got {} of 4 bytes of header'
        //    246: aload_0
        //    247: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    250: iconst_1
        //    251: iaload
        //    252: invokestatic  #52 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    255: invokeinterface  #73 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    260: aconst_null
        //    261: areturn
        //    262: aload_0
        //    263: getfield  #42 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.buf:[B
        //    266: iconst_0
        //    267: baload
        //    268: istore_2
        //    269: aload_0
        //    270: getfield  #43 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.header:[B
        //    273: ifnonnull  310 (offset +37)
        //    276: aload_0
        //    277: getfield  #48 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.tbuf:[B
        //    280: iconst_0
        //    281: iload_2
        //    282: iconst_4
        //    283: invokestatic  #66 // org.freedesktop.dbus.messages.Message.demarshallint:([BIBI)J
        //    286: l2i
        //    287: istore_3
        //    288: iload_3
        //    289: bipush  7
        //    291: iand
        //    292: istore  4
        //    294: iload  4
        //    296: ifeq  307 (offset +11)
        //    299: iload_3
        //    300: bipush  8
        //    302: iload  4
        //    304: isub
        //    305: iadd
        //    306: istore_3
        //    307: goto  319 (offset +12)
        //    310: aload_0
        //    311: getfield  #43 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.header:[B
        //    314: arraylength
        //    315: bipush  8
        //    317: isub
        //    318: istore_3
        //    319: aload_0
        //    320: getfield  #43 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.header:[B
        //    323: ifnonnull  357 (offset +34)
        //    326: aload_0
        //    327: iload_3
        //    328: bipush  8
        //    330: iadd
        //    331: newarray  byte
        //    333: putfield  #43 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.header:[B
        //    336: aload_0
        //    337: getfield  #48 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.tbuf:[B
        //    340: iconst_0
        //    341: aload_0
        //    342: getfield  #43 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.header:[B
        //    345: iconst_0
        //    346: iconst_4
        //    347: invokestatic  #57 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    350: aload_0
        //    351: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    354: iconst_2
        //    355: iconst_0
        //    356: iastore
        //    357: aload_0
        //    358: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    361: iconst_2
        //    362: iaload
        //    363: iload_3
        //    364: if_icmpge  438 (offset +74)
        //    367: aload_0
        //    368: getfield  #44 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.inputChannel:Ljava/nio/channels/SocketChannel;
        //    371: aload_0
        //    372: getfield  #43 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.header:[B
        //    375: bipush  8
        //    377: aload_0
        //    378: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    381: iconst_2
        //    382: iaload
        //    383: iadd
        //    384: iload_3
        //    385: aload_0
        //    386: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    389: iconst_2
        //    390: iaload
        //    391: isub
        //    392: invokestatic  #59 // java.nio.ByteBuffer.wrap:([BII)Ljava/nio/ByteBuffer;
        //    395: invokevirtual  #62 // java.nio.channels.SocketChannel.read:(Ljava/nio/ByteBuffer;)I
        //    398: istore  4
        //    400: iload  4
        //    402: ifge  420 (offset +18)
        //    405: new  #16 // java.io.EOFException
        //    408: dup
        //    409: iload  4
        //    411: invokedynamic  #78 // invokedynamic makeConcatWithConstants:(I)Ljava/lang/String;
        //    416: invokespecial  #49 // java.io.EOFException.<init>:(Ljava/lang/String;)V
        //    419: athrow
        //    420: aload_0
        //    421: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    424: iconst_2
        //    425: dup2
        //    426: iaload
        //    427: iload  4
        //    429: iadd
        //    430: iastore
        //    431: goto  438 (offset +7)
        //    434: astore  4
        //    436: aconst_null
        //    437: areturn
        //    438: aload_0
        //    439: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    442: iconst_2
        //    443: iaload
        //    444: iload_3
        //    445: if_icmpge  474 (offset +29)
        //    448: aload_0
        //    449: getfield  #46 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.logger:Lorg/slf4j/Logger;
        //    452: ldc  #13 // 'Only got {} of {} bytes of header'
        //    454: aload_0
        //    455: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    458: iconst_2
        //    459: iaload
        //    460: invokestatic  #52 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    463: iload_3
        //    464: invokestatic  #52 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    467: invokeinterface  #74 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    472: aconst_null
        //    473: areturn
        //    474: aload_0
        //    475: getfield  #42 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.buf:[B
        //    478: iconst_1
        //    479: baload
        //    480: istore  4
        //    482: aload_0
        //    483: getfield  #41 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.body:[B
        //    486: ifnonnull  513 (offset +27)
        //    489: aload_0
        //    490: aload_0
        //    491: getfield  #42 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.buf:[B
        //    494: iconst_4
        //    495: iload_2
        //    496: iconst_4
        //    497: invokestatic  #66 // org.freedesktop.dbus.messages.Message.demarshallint:([BIBI)J
        //    500: l2i
        //    501: newarray  byte
        //    503: putfield  #41 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.body:[B
        //    506: aload_0
        //    507: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    510: iconst_3
        //    511: iconst_0
        //    512: iastore
        //    513: aload_0
        //    514: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    517: iconst_3
        //    518: iaload
        //    519: aload_0
        //    520: getfield  #41 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.body:[B
        //    523: arraylength
        //    524: if_icmpge  599 (offset +75)
        //    527: aload_0
        //    528: getfield  #44 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.inputChannel:Ljava/nio/channels/SocketChannel;
        //    531: aload_0
        //    532: getfield  #41 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.body:[B
        //    535: aload_0
        //    536: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    539: iconst_3
        //    540: iaload
        //    541: aload_0
        //    542: getfield  #41 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.body:[B
        //    545: arraylength
        //    546: aload_0
        //    547: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    550: iconst_3
        //    551: iaload
        //    552: isub
        //    553: invokestatic  #59 // java.nio.ByteBuffer.wrap:([BII)Ljava/nio/ByteBuffer;
        //    556: invokevirtual  #62 // java.nio.channels.SocketChannel.read:(Ljava/nio/ByteBuffer;)I
        //    559: istore  5
        //    561: iload  5
        //    563: ifge  581 (offset +18)
        //    566: new  #16 // java.io.EOFException
        //    569: dup
        //    570: iload  5
        //    572: invokedynamic  #79 // invokedynamic makeConcatWithConstants:(I)Ljava/lang/String;
        //    577: invokespecial  #49 // java.io.EOFException.<init>:(Ljava/lang/String;)V
        //    580: athrow
        //    581: aload_0
        //    582: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    585: iconst_3
        //    586: dup2
        //    587: iaload
        //    588: iload  5
        //    590: iadd
        //    591: iastore
        //    592: goto  599 (offset +7)
        //    595: astore  5
        //    597: aconst_null
        //    598: areturn
        //    599: aload_0
        //    600: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    603: iconst_3
        //    604: iaload
        //    605: aload_0
        //    606: getfield  #41 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.body:[B
        //    609: arraylength
        //    610: if_icmpge  643 (offset +33)
        //    613: aload_0
        //    614: getfield  #46 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.logger:Lorg/slf4j/Logger;
        //    617: ldc  #12 // 'Only got {} of {} bytes of body'
        //    619: aload_0
        //    620: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    623: iconst_3
        //    624: iaload
        //    625: invokestatic  #52 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    628: aload_0
        //    629: getfield  #41 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.body:[B
        //    632: arraylength
        //    633: invokestatic  #52 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    636: invokeinterface  #74 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    641: aconst_null
        //    642: areturn
        //    643: aconst_null
        //    644: astore  5
        //    646: aload_0
        //    647: getfield  #47 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.socketProviderImpl:Lorg/freedesktop/dbus/spi/message/ISocketProvider;
        //    650: invokeinterface  #70 // org.freedesktop.dbus.spi.message.ISocketProvider.isFileDescriptorPassingSupported:()Z, count 1
        //    655: ifeq  668 (offset +13)
        //    658: aload_0
        //    659: aload_0
        //    660: getfield  #44 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.inputChannel:Ljava/nio/channels/SocketChannel;
        //    663: invokevirtual  #68 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.readFileDescriptors:(Ljava/nio/channels/SocketChannel;)Ljava/util/List;
        //    666: astore  5
        //    668: iload  4
        //    670: aload_0
        //    671: getfield  #42 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.buf:[B
        //    674: aload_0
        //    675: getfield  #43 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.header:[B
        //    678: aload_0
        //    679: getfield  #41 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.body:[B
        //    682: aload  5
        //    684: invokestatic  #67 // org.freedesktop.dbus.messages.MessageFactory.createMessage:(B[B[B[BLjava/util/List;)Lorg/freedesktop/dbus/messages/Message;
        //    687: astore  6
        //    689: aload_0
        //    690: getfield  #46 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.logger:Lorg/slf4j/Logger;
        //    693: ldc  #6 // '=> {}'
        //    695: aload  6
        //    697: invokeinterface  #71 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    702: aload  6
        //    704: astore  7
        //    706: aload_0
        //    707: getfield  #48 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.tbuf:[B
        //    710: iconst_0
        //    711: invokestatic  #63 // java.util.Arrays.fill:([BB)V
        //    714: aload_0
        //    715: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    718: iconst_1
        //    719: iconst_0
        //    720: iastore
        //    721: aload_0
        //    722: aconst_null
        //    723: putfield  #41 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.body:[B
        //    726: aload_0
        //    727: aconst_null
        //    728: putfield  #43 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.header:[B
        //    731: aload_0
        //    732: getfield  #42 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.buf:[B
        //    735: iconst_0
        //    736: invokestatic  #63 // java.util.Arrays.fill:([BB)V
        //    739: aload_0
        //    740: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    743: iconst_0
        //    744: iconst_0
        //    745: iastore
        //    746: aload  7
        //    748: areturn
        //    749: astore  5
        //    751: aload_0
        //    752: getfield  #46 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.logger:Lorg/slf4j/Logger;
        //    755: ldc  #8 // 'Exception while creating message.'
        //    757: aload  5
        //    759: invokeinterface  #75 // org.slf4j.Logger.warn:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    764: aload  5
        //    766: athrow
        //    767: astore  8
        //    769: aload_0
        //    770: getfield  #48 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.tbuf:[B
        //    773: iconst_0
        //    774: invokestatic  #63 // java.util.Arrays.fill:([BB)V
        //    777: aload_0
        //    778: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    781: iconst_1
        //    782: iconst_0
        //    783: iastore
        //    784: aload_0
        //    785: aconst_null
        //    786: putfield  #41 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.body:[B
        //    789: aload_0
        //    790: aconst_null
        //    791: putfield  #43 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.header:[B
        //    794: aload_0
        //    795: getfield  #42 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.buf:[B
        //    798: iconst_0
        //    799: invokestatic  #63 // java.util.Arrays.fill:([BB)V
        //    802: aload_0
        //    803: getfield  #45 // org.freedesktop.dbus.spi.message.AbstractInputStreamMessageReader.len:[I
        //    806: iconst_0
        //    807: iconst_0
        //    808: iastore
        //    809: aload  8
        //    811: athrow
        //       Exception table:
        //         from 11 to 71 target 74 type java.net.SocketTimeoutException
        //         from 167 to 224 target 227 type java.net.SocketTimeoutException
        //         from 367 to 431 target 434 type java.net.SocketTimeoutException
        //         from 527 to 592 target 595 type java.net.SocketTimeoutException
        //         from 643 to 706 target 749 type org.freedesktop.dbus.exceptions.DBusException
        //         from 643 to 706 target 749 type java.lang.RuntimeException
        //         from 643 to 706 target 767 type any
        //         from 749 to 769 target 767 type any
    }

  protected abstract List readFileDescriptors(SocketChannel arg0);

  protected Logger getLogger() {
        return logger;
    }

  protected ISocketProvider getSocketProviderImpl() {
        return socketProviderImpl;
    }

  public void close() {
        if (inputChannel.isOpen()) {
            logger.trace("Closing Message Reader");
            inputChannel.close();
        }
    }

  public boolean isClosed() {
        return !inputChannel.isOpen();
    }

  public String toString() {
        return getClass().getSimpleName() + " [inputChannel=" + String.valueOf(inputChannel) + ", socketProviderImpl=" + String.valueOf(socketProviderImpl) + "]";
    }

}