// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.messages.DBusSignal
package org.freedesktop.dbus.messages;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.freedesktop.dbus.Marshalling;
import org.freedesktop.dbus.connections.base.AbstractConnectionBase;
import org.freedesktop.dbus.exceptions.MessageFormatException;
import org.freedesktop.dbus.messages.DBusSignal_CachedConstructor;
import org.freedesktop.dbus.messages.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBusSignal extends Message {

    // ---- поля ----
  private static final Logger LOGGER;
  private static final Map CLASS_CACHE;
  private static final Map TYPE_CACHE;
  private static final Map SIGNAL_NAMES;
  private static final Map INT_NAMES;
  private static final Map CACHED_CONSTRUCTORS;
  private Class clazz;
  private boolean bodydone;
  private byte[] blen;

    static {
        LOGGER = LoggerFactory.getLogger(DBusSignal.class);
        CLASS_CACHE = new ConcurrentHashMap();
        TYPE_CACHE = new ConcurrentHashMap();
        SIGNAL_NAMES = new ConcurrentHashMap();
        INT_NAMES = new ConcurrentHashMap();
        CACHED_CONSTRUCTORS = new ConcurrentHashMap();
    }

   DBusSignal() { // было: <init>
        super();
        bodydone = false;
    }

  protected DBusSignal(byte arg0, String arg1, String arg2, String arg3, String arg4, String arg5, Object[] arg6) { // было: <init>
        super(arg0, 4, 0);
        bodydone = false;
        if (null == arg2) {
            throw new MessageFormatException("Must specify object path, interface and signal name to Signals.");
        } else {
            if (null == arg4) {
                throw new MessageFormatException("Must specify object path, interface and signal name to Signals.");
            } else {
                if (null != arg3) {
                    ArrayList var8 = new ArrayList();
                    var8.add(createHeaderArgs(1, "o", arg2));
                    var8.add(createHeaderArgs(2, "s", arg3));
                    var8.add(createHeaderArgs(3, "s", arg4));
                    if (null != arg1) {
                        var8.add(createHeaderArgs(7, "s", arg1));
                    }
                    if (null != arg5) {
                        var8.add(createHeaderArgs(8, "g", arg5));
                        setArgs(arg6);
                    }
                    padAndMarshall(var8, getSerial(), arg5, arg6);
                    bodydone = true;
                    return;
                } else {
                    throw new MessageFormatException("Must specify object path, interface and signal name to Signals.");
                }
            }
        }
    }

  protected DBusSignal(String arg0, Object[] arg1) { // было: <init>
        this(0, arg0, arg1);
    }

  protected DBusSignal(byte arg0, String arg1, Object[] arg2) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: iload_1
        //      2: iconst_4
        //      3: iconst_0
        //      4: invokespecial  #125 // org.freedesktop.dbus.messages.Message.<init>:(BBB)V
        //      7: aload_0
        //      8: iconst_0
        //      9: putfield  #62 // org.freedesktop.dbus.messages.DBusSignal.bodydone:Z
        //     12: aload_2
        //     13: invokestatic  #129 // org.freedesktop.dbus.utils.DBusObjects.requireObjectPath:(Ljava/lang/String;)Ljava/lang/String;
        //     16: pop
        //     17: aload_0
        //     18: invokevirtual  #77 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     21: astore  4
        //     23: aload  4
        //     25: invokestatic  #128 // org.freedesktop.dbus.utils.DBusNamingUtil.getSignalName:(Ljava/lang/Class;)Ljava/lang/String;
        //     28: astore  5
        //     30: aload  4
        //     32: invokevirtual  #71 // java.lang.Class.getEnclosingClass:()Ljava/lang/Class;
        //     35: astore  6
        //     37: aconst_null
        //     38: aload  6
        //     40: if_acmpeq  69 (offset +29)
        //     43: ldc  #45 // org.freedesktop.dbus.interfaces.DBusInterface
        //     45: aload  6
        //     47: invokevirtual  #74 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //     50: ifeq  69 (offset +19)
        //     53: aload  6
        //     55: invokevirtual  #72 // java.lang.Class.getName:()Ljava/lang/String;
        //     58: aload  6
        //     60: invokevirtual  #73 // java.lang.Class.getSimpleName:()Ljava/lang/String;
        //     63: invokevirtual  #78 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     66: ifeq  79 (offset +13)
        //     69: new  #43 // org.freedesktop.dbus.exceptions.DBusException
        //     72: dup
        //     73: ldc  #9 // 'Signals must be declared as a member of a class implementing DBusInterface which is the member of a package.'
        //     75: invokespecial  #96 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //     78: athrow
        //     79: aload  6
        //     81: invokestatic  #127 // org.freedesktop.dbus.utils.DBusNamingUtil.getInterfaceName:(Ljava/lang/Class;)Ljava/lang/String;
        //     84: astore  7
        //     86: new  #30 // java.util.ArrayList
        //     89: dup
        //     90: invokespecial  #84 // java.util.ArrayList.<init>:()V
        //     93: astore  8
        //     95: aload  8
        //     97: aload_0
        //     98: iconst_1
        //     99: ldc  #12 // 'o'
        //    101: aload_2
        //    102: invokevirtual  #103 // org.freedesktop.dbus.messages.DBusSignal.createHeaderArgs:(BLjava/lang/String;Ljava/lang/Object;)[Ljava/lang/Object;
        //    105: invokeinterface  #134 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    110: pop
        //    111: aload  8
        //    113: aload_0
        //    114: iconst_2
        //    115: ldc  #13 // 's'
        //    117: aload  7
        //    119: invokevirtual  #103 // org.freedesktop.dbus.messages.DBusSignal.createHeaderArgs:(BLjava/lang/String;Ljava/lang/Object;)[Ljava/lang/Object;
        //    122: invokeinterface  #134 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    127: pop
        //    128: aload  8
        //    130: aload_0
        //    131: iconst_3
        //    132: ldc  #13 // 's'
        //    134: aload  5
        //    136: invokevirtual  #103 // org.freedesktop.dbus.messages.DBusSignal.createHeaderArgs:(BLjava/lang/String;Ljava/lang/Object;)[Ljava/lang/Object;
        //    139: invokeinterface  #134 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    144: pop
        //    145: aconst_null
        //    146: astore  9
        //    148: iconst_0
        //    149: aload_3
        //    150: arraylength
        //    151: if_icmpge  345 (offset +194)
        //    154: getstatic  #60 // org.freedesktop.dbus.messages.DBusSignal.TYPE_CACHE:Ljava/util/Map;
        //    157: aload  4
        //    159: invokeinterface  #138 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    164: checkcast  #15 // [Ljava.lang.reflect.Type;
        //    167: astore  10
        //    169: aconst_null
        //    170: aload  10
        //    172: if_acmpne  279 (offset +107)
        //    175: aload  4
        //    177: invokevirtual  #70 // java.lang.Class.getDeclaredConstructors:()[Ljava/lang/reflect/Constructor;
        //    180: iconst_0
        //    181: aaload
        //    182: astore  11
        //    184: aload  11
        //    186: invokevirtual  #82 // java.lang.reflect.Constructor.getGenericParameterTypes:()[Ljava/lang/reflect/Type;
        //    189: astore  12
        //    191: aload  12
        //    193: arraylength
        //    194: iconst_1
        //    195: isub
        //    196: anewarray  #28 // java.lang.reflect.Type
        //    199: astore  10
        //    201: iconst_1
        //    202: istore  13
        //    204: iload  13
        //    206: aload  10
        //    208: arraylength
        //    209: if_icmpgt  266 (offset +57)
        //    212: aload  12
        //    214: iload  13
        //    216: aaload
        //    217: instanceof  #29 // java.lang.reflect.TypeVariable
        //    220: ifeq  248 (offset +28)
        //    223: aload  10
        //    225: iload  13
        //    227: iconst_1
        //    228: isub
        //    229: aload  12
        //    231: iload  13
        //    233: aaload
        //    234: checkcast  #29 // java.lang.reflect.TypeVariable
        //    237: invokeinterface  #131 // java.lang.reflect.TypeVariable.getBounds:()[Ljava/lang/reflect/Type;, count 1
        //    242: iconst_0
        //    243: aaload
        //    244: aastore
        //    245: goto  260 (offset +15)
        //    248: aload  10
        //    250: iload  13
        //    252: iconst_1
        //    253: isub
        //    254: aload  12
        //    256: iload  13
        //    258: aaload
        //    259: aastore
        //    260: iinc  13, 1
        //    263: goto  204 (offset -59)
        //    266: getstatic  #60 // org.freedesktop.dbus.messages.DBusSignal.TYPE_CACHE:Ljava/util/Map;
        //    269: aload  4
        //    271: aload  10
        //    273: invokeinterface  #139 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    278: pop
        //    279: aload  10
        //    281: invokestatic  #94 // org.freedesktop.dbus.Marshalling.getDBusType:([Ljava/lang/reflect/Type;)Ljava/lang/String;
        //    284: astore  9
        //    286: aload  8
        //    288: aload_0
        //    289: bipush  8
        //    291: ldc  #11 // 'g'
        //    293: aload  9
        //    295: invokevirtual  #103 // org.freedesktop.dbus.messages.DBusSignal.createHeaderArgs:(BLjava/lang/String;Ljava/lang/Object;)[Ljava/lang/Object;
        //    298: invokeinterface  #134 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    303: pop
        //    304: aload_0
        //    305: aload_3
        //    306: invokevirtual  #117 // org.freedesktop.dbus.messages.DBusSignal.setArgs:([Ljava/lang/Object;)V
        //    309: goto  345 (offset +36)
        //    312: astore  10
        //    314: aload_0
        //    315: getfield  #64 // org.freedesktop.dbus.messages.DBusSignal.logger:Lorg/slf4j/Logger;
        //    318: ldc  #6 // 'Error adding signal parameters'
        //    320: aload  10
        //    322: invokeinterface  #143 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    327: new  #43 // org.freedesktop.dbus.exceptions.DBusException
        //    330: dup
        //    331: aload  10
        //    333: invokevirtual  #75 // java.lang.Exception.getMessage:()Ljava/lang/String;
        //    336: invokedynamic  #146 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //    341: invokespecial  #96 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    344: athrow
        //    345: aload_0
        //    346: iconst_4
        //    347: newarray  byte
        //    349: putfield  #61 // org.freedesktop.dbus.messages.DBusSignal.blen:[B
        //    352: aload_0
        //    353: aload_0
        //    354: getfield  #61 // org.freedesktop.dbus.messages.DBusSignal.blen:[B
        //    357: invokevirtual  #101 // org.freedesktop.dbus.messages.DBusSignal.appendBytes:([B)V
        //    360: aload_0
        //    361: ldc  #14 // 'ua(yv)'
        //    363: iconst_2
        //    364: anewarray  #20 // java.lang.Object
        //    367: dup
        //    368: iconst_0
        //    369: aload_0
        //    370: invokevirtual  #111 // org.freedesktop.dbus.messages.DBusSignal.getSerial:()J
        //    373: invokestatic  #76 // java.lang.Long.valueOf:(J)Ljava/lang/Long;
        //    376: aastore
        //    377: dup
        //    378: iconst_1
        //    379: aload  8
        //    381: invokeinterface  #136 // java.util.List.toArray:()[Ljava/lang/Object;, count 1
        //    386: aastore
        //    387: invokevirtual  #100 // org.freedesktop.dbus.messages.DBusSignal.append:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    390: aload_0
        //    391: bipush  8
        //    393: invokevirtual  #115 // org.freedesktop.dbus.messages.DBusSignal.pad:(B)V
        //    396: return
        //       Exception table:
        //         from 154 to 309 target 312 type java.lang.Exception
    }

  static void addInterfaceMap(String arg0, String arg1) {
        INT_NAMES.put(arg1, arg0);
    }

  static void addSignalMap(String arg0, String arg1) {
        SIGNAL_NAMES.put(arg1, arg0);
    }

  private static Class createSignalClass(String arg0, String arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: aload_1
        //      2: invokedynamic  #147 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //      7: astore_2
        //      8: getstatic  #56 // org.freedesktop.dbus.messages.DBusSignal.CLASS_CACHE:Ljava/util/Map;
        //     11: aload_2
        //     12: invokeinterface  #138 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     17: checkcast  #16 // java.lang.Class
        //     20: astore_3
        //     21: aconst_null
        //     22: aload_3
        //     23: if_acmpne  31 (offset +8)
        //     26: aload_2
        //     27: invokestatic  #91 // org.freedesktop.dbus.DBusMatchRule.getCachedSignalType:(Ljava/lang/String;)Ljava/lang/Class;
        //     30: astore_3
        //     31: aconst_null
        //     32: aload_3
        //     33: if_acmpeq  38 (offset +5)
        //     36: aload_3
        //     37: areturn
        //     38: aload_2
        //     39: invokestatic  #69 // java.lang.Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //     42: astore_3
        //     43: goto  61 (offset +18)
        //     46: astore  4
        //     48: getstatic  #58 // org.freedesktop.dbus.messages.DBusSignal.LOGGER:Lorg/slf4j/Logger;
        //     51: ldc  #2 // 'Class not found for {}'
        //     53: aload_2
        //     54: aload  4
        //     56: invokeinterface  #144 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //     61: getstatic  #67 // org.freedesktop.dbus.utils.CommonRegexPattern.EXCEPTION_EXTRACT_PATTERN:Ljava/util/regex/Pattern;
        //     64: aload_2
        //     65: invokevirtual  #89 // java.util.regex.Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //     68: ldc  #10 // '\\$$1'
        //     70: invokevirtual  #88 // java.util.regex.Matcher.replaceAll:(Ljava/lang/String;)Ljava/lang/String;
        //     73: astore_2
        //     74: aconst_null
        //     75: aload_3
        //     76: if_acmpne  92 (offset +16)
        //     79: getstatic  #68 // org.freedesktop.dbus.utils.CommonRegexPattern.EXCEPTION_PARTIAL_PATTERN:Ljava/util/regex/Pattern;
        //     82: aload_2
        //     83: invokevirtual  #89 // java.util.regex.Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //     86: invokevirtual  #87 // java.util.regex.Matcher.matches:()Z
        //     89: ifne  38 (offset -51)
        //     92: aconst_null
        //     93: aload_3
        //     94: if_acmpne  112 (offset +18)
        //     97: new  #43 // org.freedesktop.dbus.exceptions.DBusException
        //    100: dup
        //    101: aload_0
        //    102: aload_1
        //    103: invokedynamic  #148 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    108: invokespecial  #96 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    111: athrow
        //    112: getstatic  #56 // org.freedesktop.dbus.messages.DBusSignal.CLASS_CACHE:Ljava/util/Map;
        //    115: aload_2
        //    116: aload_3
        //    117: invokeinterface  #139 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    122: pop
        //    123: aload_3
        //    124: areturn
        //       Exception table:
        //         from 38 to 43 target 46 type java.lang.ClassNotFoundException
    }

  public DBusSignal createReal(AbstractConnectionBase arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #57 // org.freedesktop.dbus.messages.DBusSignal.INT_NAMES:Ljava/util/Map;
        //      3: aload_0
        //      4: invokevirtual  #107 // org.freedesktop.dbus.messages.DBusSignal.getInterface:()Ljava/lang/String;
        //      7: invokeinterface  #138 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     12: checkcast  #21 // java.lang.String
        //     15: astore_2
        //     16: getstatic  #59 // org.freedesktop.dbus.messages.DBusSignal.SIGNAL_NAMES:Ljava/util/Map;
        //     19: aload_0
        //     20: invokevirtual  #108 // org.freedesktop.dbus.messages.DBusSignal.getName:()Ljava/lang/String;
        //     23: invokeinterface  #138 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     28: checkcast  #21 // java.lang.String
        //     31: astore_3
        //     32: aconst_null
        //     33: aload_2
        //     34: if_acmpne  42 (offset +8)
        //     37: aload_0
        //     38: invokevirtual  #107 // org.freedesktop.dbus.messages.DBusSignal.getInterface:()Ljava/lang/String;
        //     41: astore_2
        //     42: aconst_null
        //     43: aload_3
        //     44: if_acmpne  52 (offset +8)
        //     47: aload_0
        //     48: invokevirtual  #108 // org.freedesktop.dbus.messages.DBusSignal.getName:()Ljava/lang/String;
        //     51: astore_3
        //     52: aconst_null
        //     53: aload_0
        //     54: getfield  #63 // org.freedesktop.dbus.messages.DBusSignal.clazz:Ljava/lang/Class;
        //     57: if_acmpne  69 (offset +12)
        //     60: aload_0
        //     61: aload_2
        //     62: aload_3
        //     63: invokestatic  #104 // org.freedesktop.dbus.messages.DBusSignal.createSignalClass:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Class;
        //     66: putfield  #63 // org.freedesktop.dbus.messages.DBusSignal.clazz:Ljava/lang/Class;
        //     69: aload_0
        //     70: getfield  #64 // org.freedesktop.dbus.messages.DBusSignal.logger:Lorg/slf4j/Logger;
        //     73: ldc  #3 // 'Converting signal to type: {}'
        //     75: aload_0
        //     76: getfield  #63 // org.freedesktop.dbus.messages.DBusSignal.clazz:Ljava/lang/Class;
        //     79: invokeinterface  #142 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //     84: getstatic  #55 // org.freedesktop.dbus.messages.DBusSignal.CACHED_CONSTRUCTORS:Ljava/util/Map;
        //     87: aload_0
        //     88: getfield  #63 // org.freedesktop.dbus.messages.DBusSignal.clazz:Ljava/lang/Class;
        //     91: invokeinterface  #137 // java.util.Map.containsKey:(Ljava/lang/Object;)Z, count 2
        //     96: ifne  107 (offset +11)
        //     99: aload_0
        //    100: aload_0
        //    101: getfield  #63 // org.freedesktop.dbus.messages.DBusSignal.clazz:Ljava/lang/Class;
        //    104: invokevirtual  #102 // org.freedesktop.dbus.messages.DBusSignal.cacheConstructors:(Ljava/lang/Class;)V
        //    107: getstatic  #55 // org.freedesktop.dbus.messages.DBusSignal.CACHED_CONSTRUCTORS:Ljava/util/Map;
        //    110: aload_0
        //    111: getfield  #63 // org.freedesktop.dbus.messages.DBusSignal.clazz:Ljava/lang/Class;
        //    114: invokeinterface  #138 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    119: checkcast  #33 // java.util.List
        //    122: astore  4
        //    124: aconst_null
        //    125: astore  5
        //    127: aconst_null
        //    128: astore  6
        //    130: aload_0
        //    131: invokevirtual  #109 // org.freedesktop.dbus.messages.DBusSignal.getParameters:()[Ljava/lang/Object;
        //    134: astore  7
        //    136: aload  7
        //    138: invokestatic  #85 // java.util.Arrays.stream:([Ljava/lang/Object;)Ljava/util/stream/Stream;
        //    141: invokedynamic  #149 // invokedynamic apply:()Ljava/util/function/Function;
        //    146: invokeinterface  #141 // java.util.stream.Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;, count 2
        //    151: invokestatic  #90 // java.util.stream.Collectors.toList:()Ljava/util/stream/Collector;
        //    154: invokeinterface  #140 // java.util.stream.Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;, count 2
        //    159: checkcast  #33 // java.util.List
        //    162: astore  8
        //    164: aload  4
        //    166: invokeinterface  #135 // java.util.List.iterator:()Ljava/util/Iterator;, count 1
        //    171: astore  9
        //    173: aload  9
        //    175: invokeinterface  #132 // java.util.Iterator.hasNext:()Z, count 1
        //    180: ifeq  225 (offset +45)
        //    183: aload  9
        //    185: invokeinterface  #133 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    190: checkcast  #47 // org.freedesktop.dbus.messages.DBusSignal$CachedConstructor
        //    193: astore  10
        //    195: aload  10
        //    197: aload  8
        //    199: invokevirtual  #123 // org.freedesktop.dbus.messages.DBusSignal$CachedConstructor.matchesParameters:(Ljava/util/List;)Z
        //    202: ifeq  222 (offset +20)
        //    205: aload  10
        //    207: getfield  #65 // org.freedesktop.dbus.messages.DBusSignal$CachedConstructor.constructor:Ljava/lang/reflect/Constructor;
        //    210: astore  5
        //    212: aload  10
        //    214: getfield  #66 // org.freedesktop.dbus.messages.DBusSignal$CachedConstructor.types:[Ljava/lang/reflect/Type;
        //    217: astore  6
        //    219: goto  225 (offset +6)
        //    222: goto  173 (offset -49)
        //    225: aload  5
        //    227: ifnonnull  252 (offset +25)
        //    230: aload_0
        //    231: getfield  #64 // org.freedesktop.dbus.messages.DBusSignal.logger:Lorg/slf4j/Logger;
        //    234: ldc  #5 // 'Could not find suitable constructor for class {} with argument-types: {}'
        //    236: aload_0
        //    237: getfield  #63 // org.freedesktop.dbus.messages.DBusSignal.clazz:Ljava/lang/Class;
        //    240: invokevirtual  #72 // java.lang.Class.getName:()Ljava/lang/String;
        //    243: aload  8
        //    245: invokeinterface  #145 // org.slf4j.Logger.warn:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    250: aconst_null
        //    251: areturn
        //    252: aload  7
        //    254: aload  6
        //    256: aload_1
        //    257: invokestatic  #93 // org.freedesktop.dbus.Marshalling.deSerializeParameters:([Ljava/lang/Object;[Ljava/lang/reflect/Type;Lorg/freedesktop/dbus/connections/base/AbstractConnectionBase;)[Ljava/lang/Object;
        //    260: astore  10
        //    262: aconst_null
        //    263: aload  10
        //    265: if_acmpne  292 (offset +27)
        //    268: aload  5
        //    270: iconst_1
        //    271: anewarray  #20 // java.lang.Object
        //    274: dup
        //    275: iconst_0
        //    276: aload_0
        //    277: invokevirtual  #110 // org.freedesktop.dbus.messages.DBusSignal.getPath:()Ljava/lang/String;
        //    280: aastore
        //    281: invokevirtual  #83 // java.lang.reflect.Constructor.newInstance:([Ljava/lang/Object;)Ljava/lang/Object;
        //    284: checkcast  #46 // org.freedesktop.dbus.messages.DBusSignal
        //    287: astore  9
        //    289: goto  334 (offset +45)
        //    292: aload  10
        //    294: arraylength
        //    295: iconst_1
        //    296: iadd
        //    297: anewarray  #20 // java.lang.Object
        //    300: astore  11
        //    302: aload  11
        //    304: iconst_0
        //    305: aload_0
        //    306: invokevirtual  #110 // org.freedesktop.dbus.messages.DBusSignal.getPath:()Ljava/lang/String;
        //    309: aastore
        //    310: aload  10
        //    312: iconst_0
        //    313: aload  11
        //    315: iconst_1
        //    316: aload  10
        //    318: arraylength
        //    319: invokestatic  #79 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    322: aload  5
        //    324: aload  11
        //    326: invokevirtual  #83 // java.lang.reflect.Constructor.newInstance:([Ljava/lang/Object;)Ljava/lang/Object;
        //    329: checkcast  #46 // org.freedesktop.dbus.messages.DBusSignal
        //    332: astore  9
        //    334: aload  9
        //    336: aload_1
        //    337: invokevirtual  #95 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getMessageFactory:()Lorg/freedesktop/dbus/messages/MessageFactory;
        //    340: invokevirtual  #126 // org.freedesktop.dbus.messages.MessageFactory.getEndianess:()B
        //    343: invokevirtual  #121 // org.freedesktop.dbus.messages.DBusSignal.updateEndianess:(B)V
        //    346: aload  9
        //    348: aload_0
        //    349: invokevirtual  #106 // org.freedesktop.dbus.messages.DBusSignal.getHeader:()[Ljava/lang/Object;
        //    352: invokevirtual  #119 // org.freedesktop.dbus.messages.DBusSignal.setHeader:([Ljava/lang/Object;)V
        //    355: aload  9
        //    357: aload_0
        //    358: invokevirtual  #113 // org.freedesktop.dbus.messages.DBusSignal.getWireData:()[[B
        //    361: invokevirtual  #120 // org.freedesktop.dbus.messages.DBusSignal.setWireData:([[B)V
        //    364: aload  9
        //    366: aload_0
        //    367: invokevirtual  #113 // org.freedesktop.dbus.messages.DBusSignal.getWireData:()[[B
        //    370: arraylength
        //    371: i2l
        //    372: invokevirtual  #118 // org.freedesktop.dbus.messages.DBusSignal.setByteCounter:(J)V
        //    375: aload  9
        //    377: areturn
        //    378: astore  9
        //    380: new  #43 // org.freedesktop.dbus.exceptions.DBusException
        //    383: dup
        //    384: aload  9
        //    386: invokespecial  #97 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/Throwable;)V
        //    389: athrow
        //       Exception table:
        //         from 252 to 377 target 378 type java.lang.Exception
    }

  private void cacheConstructors(Class arg0) {
        ArrayList var2 = new ArrayList();
        Constructor[] var3 = arg0.getDeclaredConstructors();
        int var4 = var3.length;
        int var5 = 0;
        while (var5 < var4) {
            Object var6 = var3[var5];
            Object var7 = var6;
            var2.add(new DBusSignal_CachedConstructor(((Constructor) var7)));
            ++var5;
            continue;
        }
        CACHED_CONSTRUCTORS.put(arg0, var2);
    }

  public void appendbody(AbstractConnectionBase arg0) {
        if (!bodydone) {
            Type[] var2 = ((Type[]) TYPE_CACHE.get(getClass()));
            Object[] var3 = Marshalling.convertParameters(getParameters(), var2, arg0);
            setArgs(var3);
            String var4 = getSig();
            long var5 = getByteCounter();
            if (null != var3) {
                if (0 < var3.length) {
                    append(var4, var3);
                }
            }
            marshallint(getByteCounter() - var5, blen, 0, 4);
            bodydone = true;
            return;
        } else {
            return;
        }
    }

}