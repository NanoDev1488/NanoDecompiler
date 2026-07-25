// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.Marshalling
package org.freedesktop.dbus;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.FileDescriptor;
import org.freedesktop.dbus.ObjectPath;
import org.freedesktop.dbus.connections.AbstractConnection;
import org.freedesktop.dbus.connections.base.AbstractConnectionBase;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.types.UInt16;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.UInt64;
import org.freedesktop.dbus.types.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Marshalling {

    // ---- поля ----
  private static final String MTH_NAME_DESERIALIZE = "deserialize";
  private static final String ERROR_MULTI_VALUED_ARRAY = "Multi-valued array types not permitted";
  private static final Logger LOGGER;
  private static final Map TYPE_CACHE;
  private static final Map CLASS_TO_ARGUMENTTYPE;

    static {
        LOGGER = LoggerFactory.getLogger(Marshalling.class);
        TYPE_CACHE = new ConcurrentHashMap();
        CLASS_TO_ARGUMENTTYPE = new LinkedHashMap();
        CLASS_TO_ARGUMENTTYPE.put(Boolean.class, Byte.valueOf(98));
        CLASS_TO_ARGUMENTTYPE.put(Boolean.TYPE, Byte.valueOf(98));
        CLASS_TO_ARGUMENTTYPE.put(Byte.class, Byte.valueOf(121));
        CLASS_TO_ARGUMENTTYPE.put(Byte.TYPE, Byte.valueOf(121));
        CLASS_TO_ARGUMENTTYPE.put(Short.class, Byte.valueOf(110));
        CLASS_TO_ARGUMENTTYPE.put(Short.TYPE, Byte.valueOf(110));
        CLASS_TO_ARGUMENTTYPE.put(Integer.class, Byte.valueOf(105));
        CLASS_TO_ARGUMENTTYPE.put(Integer.TYPE, Byte.valueOf(105));
        CLASS_TO_ARGUMENTTYPE.put(Long.class, Byte.valueOf(120));
        CLASS_TO_ARGUMENTTYPE.put(Long.TYPE, Byte.valueOf(120));
        CLASS_TO_ARGUMENTTYPE.put(Double.class, Byte.valueOf(100));
        CLASS_TO_ARGUMENTTYPE.put(Double.TYPE, Byte.valueOf(100));
        if (!AbstractConnection.FLOAT_SUPPORT) {
            CLASS_TO_ARGUMENTTYPE.put(Float.class, Byte.valueOf(100));
            CLASS_TO_ARGUMENTTYPE.put(Float.TYPE, Byte.valueOf(100));
        } else {
            CLASS_TO_ARGUMENTTYPE.put(Float.class, Byte.valueOf(102));
            CLASS_TO_ARGUMENTTYPE.put(Float.TYPE, Byte.valueOf(102));
        }
        CLASS_TO_ARGUMENTTYPE.put(UInt16.class, Byte.valueOf(113));
        CLASS_TO_ARGUMENTTYPE.put(UInt32.class, Byte.valueOf(117));
        CLASS_TO_ARGUMENTTYPE.put(UInt64.class, Byte.valueOf(116));
        CLASS_TO_ARGUMENTTYPE.put(CharSequence.class, Byte.valueOf(115));
        CLASS_TO_ARGUMENTTYPE.put(Variant.class, Byte.valueOf(118));
        CLASS_TO_ARGUMENTTYPE.put(FileDescriptor.class, Byte.valueOf(104));
        CLASS_TO_ARGUMENTTYPE.put(DBusInterface.class, Byte.valueOf(111));
        CLASS_TO_ARGUMENTTYPE.put(DBusPath.class, Byte.valueOf(111));
        CLASS_TO_ARGUMENTTYPE.put(ObjectPath.class, Byte.valueOf(111));
    }

  private Marshalling() { // было: <init>
        super();
    }

  public static String getDBusType(Type[] arg0) {
        StringBuilder var1 = new StringBuilder();
        Type[] var2 = arg0;
        int var3 = var2.length;
        int var4 = 0;
        while (var4 < var3) {
            Object var5 = var2[var4];
            String[] var6 = getDBusType(((Type) var5));
            int var7 = var6.length;
            int var8 = 0;
            while (var8 < var7) {
                Object var9 = var6[var8];
                var1.append(((String) var9));
                ++var8;
                continue;
            }
            ++var4;
            continue;
        }
        return var1.toString();
    }

  public static String[] getDBusType(Type arg0) {
        String[] var1 = ((String[]) TYPE_CACHE.get(arg0));
        if (null == var1) {
            var1 = getDBusType(arg0, false);
            TYPE_CACHE.put(arg0, var1);
            return var1;
        } else {
            return var1;
        }
    }

  public static String[] getDBusType(Type arg0, boolean arg1) {
        return recursiveGetDBusType(new StringBuffer[10], arg0, arg1, 0);
    }

  private static String[] recursiveGetDBusType(StringBuffer[] arg0, Type arg1, boolean arg2, int arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: arraylength
        //      2: iload_3
        //      3: if_icmpgt  26 (offset +23)
        //      6: aload_0
        //      7: arraylength
        //      8: anewarray  #55 // java.lang.StringBuffer
        //     11: astore  4
        //     13: aload_0
        //     14: iconst_0
        //     15: aload  4
        //     17: iconst_0
        //     18: aload_0
        //     19: arraylength
        //     20: invokestatic  #153 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //     23: aload  4
        //     25: astore_0
        //     26: aconst_null
        //     27: aload_0
        //     28: iload_3
        //     29: aaload
        //     30: if_acmpne  46 (offset +16)
        //     33: aload_0
        //     34: iload_3
        //     35: new  #55 // java.lang.StringBuffer
        //     38: dup
        //     39: invokespecial  #144 // java.lang.StringBuffer.<init>:()V
        //     42: aastore
        //     43: goto  60 (offset +17)
        //     46: aload_0
        //     47: iload_3
        //     48: aaload
        //     49: iconst_0
        //     50: aload_0
        //     51: iload_3
        //     52: aaload
        //     53: invokevirtual  #148 // java.lang.StringBuffer.length:()I
        //     56: invokevirtual  #147 // java.lang.StringBuffer.delete:(II)Ljava/lang/StringBuffer;
        //     59: pop
        //     60: iload_2
        //     61: ifeq  88 (offset +27)
        //     64: aload_1
        //     65: instanceof  #43 // java.lang.Class
        //     68: ifne  88 (offset +20)
        //     71: new  #92 // org.freedesktop.dbus.exceptions.DBusException
        //     74: dup
        //     75: aload_1
        //     76: invokestatic  #143 // java.lang.String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //     79: invokedynamic  #228 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //     84: invokespecial  #190 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //     87: athrow
        //     88: aload_1
        //     89: instanceof  #69 // java.lang.reflect.TypeVariable
        //     92: ifeq  107 (offset +15)
        //     95: aload_0
        //     96: iload_3
        //     97: aaload
        //     98: bipush  118
        //    100: invokevirtual  #145 // java.lang.StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //    103: pop
        //    104: goto  1415 (offset +1311)
        //    107: aload_1
        //    108: instanceof  #65 // java.lang.reflect.GenericArrayType
        //    111: ifeq  177 (offset +66)
        //    114: aload_1
        //    115: checkcast  #65 // java.lang.reflect.GenericArrayType
        //    118: astore  4
        //    120: aload_0
        //    121: iload_3
        //    122: aaload
        //    123: bipush  97
        //    125: invokevirtual  #145 // java.lang.StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //    128: pop
        //    129: aload_0
        //    130: aload  4
        //    132: invokeinterface  #199 // java.lang.reflect.GenericArrayType.getGenericComponentType:()Ljava/lang/reflect/Type;, count 1
        //    137: iconst_0
        //    138: iload_3
        //    139: iconst_1
        //    140: iadd
        //    141: invokestatic  #184 // org.freedesktop.dbus.Marshalling.recursiveGetDBusType:([Ljava/lang/StringBuffer;Ljava/lang/reflect/Type;ZI)[Ljava/lang/String;
        //    144: astore  8
        //    146: aload  8
        //    148: arraylength
        //    149: iconst_1
        //    150: if_icmpeq  163 (offset +13)
        //    153: new  #92 // org.freedesktop.dbus.exceptions.DBusException
        //    156: dup
        //    157: ldc  #20 // 'Multi-valued array types not permitted'
        //    159: invokespecial  #190 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    162: athrow
        //    163: aload_0
        //    164: iload_3
        //    165: aaload
        //    166: aload  8
        //    168: iconst_0
        //    169: aaload
        //    170: invokevirtual  #146 // java.lang.StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    173: pop
        //    174: goto  1415 (offset +1241)
        //    177: aload_1
        //    178: instanceof  #43 // java.lang.Class
        //    181: ifeq  196 (offset +15)
        //    184: ldc  #94 // org.freedesktop.dbus.interfaces.DBusSerializable
        //    186: aload_1
        //    187: checkcast  #43 // java.lang.Class
        //    190: invokevirtual  #128 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    193: ifne  227 (offset +34)
        //    196: aload_1
        //    197: instanceof  #67 // java.lang.reflect.ParameterizedType
        //    200: ifeq  451 (offset +251)
        //    203: aload_1
        //    204: checkcast  #67 // java.lang.reflect.ParameterizedType
        //    207: astore  5
        //    209: ldc  #94 // org.freedesktop.dbus.interfaces.DBusSerializable
        //    211: aload  5
        //    213: invokeinterface  #201 // java.lang.reflect.ParameterizedType.getRawType:()Ljava/lang/reflect/Type;, count 1
        //    218: checkcast  #43 // java.lang.Class
        //    221: invokevirtual  #128 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    224: ifeq  451 (offset +227)
        //    227: aconst_null
        //    228: astore  8
        //    230: aload_1
        //    231: instanceof  #43 // java.lang.Class
        //    234: ifeq  301 (offset +67)
        //    237: aload_1
        //    238: checkcast  #43 // java.lang.Class
        //    241: astore  9
        //    243: aload  9
        //    245: invokevirtual  #125 // java.lang.Class.getDeclaredMethods:()[Ljava/lang/reflect/Method;
        //    248: astore  10
        //    250: aload  10
        //    252: arraylength
        //    253: istore  11
        //    255: iconst_0
        //    256: istore  12
        //    258: iload  12
        //    260: iload  11
        //    262: if_icmpge  298 (offset +36)
        //    265: aload  10
        //    267: iload  12
        //    269: aaload
        //    270: astore  13
        //    272: aload  13
        //    274: invokevirtual  #162 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    277: ldc  #33 // 'deserialize'
        //    279: invokevirtual  #137 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    282: ifeq  292 (offset +10)
        //    285: aload  13
        //    287: invokevirtual  #161 // java.lang.reflect.Method.getGenericParameterTypes:()[Ljava/lang/reflect/Type;
        //    290: astore  8
        //    292: iinc  12, 1
        //    295: goto  258 (offset -37)
        //    298: goto  366 (offset +68)
        //    301: aload_1
        //    302: checkcast  #67 // java.lang.reflect.ParameterizedType
        //    305: invokeinterface  #201 // java.lang.reflect.ParameterizedType.getRawType:()Ljava/lang/reflect/Type;, count 1
        //    310: checkcast  #43 // java.lang.Class
        //    313: invokevirtual  #125 // java.lang.Class.getDeclaredMethods:()[Ljava/lang/reflect/Method;
        //    316: astore  10
        //    318: aload  10
        //    320: arraylength
        //    321: istore  11
        //    323: iconst_0
        //    324: istore  12
        //    326: iload  12
        //    328: iload  11
        //    330: if_icmpge  366 (offset +36)
        //    333: aload  10
        //    335: iload  12
        //    337: aaload
        //    338: astore  13
        //    340: aload  13
        //    342: invokevirtual  #162 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    345: ldc  #33 // 'deserialize'
        //    347: invokevirtual  #137 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    350: ifeq  360 (offset +10)
        //    353: aload  13
        //    355: invokevirtual  #161 // java.lang.reflect.Method.getGenericParameterTypes:()[Ljava/lang/reflect/Type;
        //    358: astore  8
        //    360: iinc  12, 1
        //    363: goto  326 (offset -37)
        //    366: aconst_null
        //    367: aload  8
        //    369: if_acmpne  382 (offset +13)
        //    372: new  #92 // org.freedesktop.dbus.exceptions.DBusException
        //    375: dup
        //    376: ldc  #28 // 'Serializable classes must implement a deserialize method'
        //    378: invokespecial  #190 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    381: athrow
        //    382: aload  8
        //    384: arraylength
        //    385: anewarray  #54 // java.lang.String
        //    388: astore  9
        //    390: iconst_0
        //    391: istore  10
        //    393: iload  10
        //    395: aload  9
        //    397: arraylength
        //    398: if_icmpge  448 (offset +50)
        //    401: aload_0
        //    402: aload  8
        //    404: iload  10
        //    406: aaload
        //    407: iconst_0
        //    408: iload_3
        //    409: iconst_1
        //    410: iadd
        //    411: invokestatic  #184 // org.freedesktop.dbus.Marshalling.recursiveGetDBusType:([Ljava/lang/StringBuffer;Ljava/lang/reflect/Type;ZI)[Ljava/lang/String;
        //    414: astore  11
        //    416: iconst_1
        //    417: aload  11
        //    419: arraylength
        //    420: if_icmpeq  433 (offset +13)
        //    423: new  #92 // org.freedesktop.dbus.exceptions.DBusException
        //    426: dup
        //    427: ldc  #29 // 'Serializable classes must serialize to native DBus types'
        //    429: invokespecial  #190 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    432: athrow
        //    433: aload  9
        //    435: iload  10
        //    437: aload  11
        //    439: iconst_0
        //    440: aaload
        //    441: aastore
        //    442: iinc  10, 1
        //    445: goto  393 (offset -52)
        //    448: aload  9
        //    450: areturn
        //    451: aload_1
        //    452: instanceof  #67 // java.lang.reflect.ParameterizedType
        //    455: ifeq  956 (offset +501)
        //    458: aload_1
        //    459: checkcast  #67 // java.lang.reflect.ParameterizedType
        //    462: astore  6
        //    464: aload  6
        //    466: invokeinterface  #201 // java.lang.reflect.ParameterizedType.getRawType:()Ljava/lang/reflect/Type;, count 1
        //    471: ldc  #76 // java.util.Map
        //    473: invokeinterface  #202 // java.lang.reflect.Type.equals:(Ljava/lang/Object;)Z, count 2
        //    478: ifeq  622 (offset +144)
        //    481: aload_0
        //    482: iload_3
        //    483: aaload
        //    484: ldc  #32 // 'a{'
        //    486: invokevirtual  #146 // java.lang.StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    489: pop
        //    490: aload  6
        //    492: invokeinterface  #200 // java.lang.reflect.ParameterizedType.getActualTypeArguments:()[Ljava/lang/reflect/Type;, count 1
        //    497: astore  8
        //    499: aload_0
        //    500: aload  8
        //    502: iconst_0
        //    503: aaload
        //    504: iconst_1
        //    505: iload_3
        //    506: iconst_1
        //    507: iadd
        //    508: invokestatic  #184 // org.freedesktop.dbus.Marshalling.recursiveGetDBusType:([Ljava/lang/StringBuffer;Ljava/lang/reflect/Type;ZI)[Ljava/lang/String;
        //    511: astore  9
        //    513: aload  9
        //    515: arraylength
        //    516: iconst_1
        //    517: if_icmpeq  530 (offset +13)
        //    520: new  #92 // org.freedesktop.dbus.exceptions.DBusException
        //    523: dup
        //    524: ldc  #20 // 'Multi-valued array types not permitted'
        //    526: invokespecial  #190 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    529: athrow
        //    530: aload_0
        //    531: iload_3
        //    532: aaload
        //    533: aload  9
        //    535: iconst_0
        //    536: aaload
        //    537: invokevirtual  #146 // java.lang.StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    540: pop
        //    541: aload_0
        //    542: aload  8
        //    544: iconst_1
        //    545: aaload
        //    546: iconst_0
        //    547: iload_3
        //    548: iconst_1
        //    549: iadd
        //    550: invokestatic  #184 // org.freedesktop.dbus.Marshalling.recursiveGetDBusType:([Ljava/lang/StringBuffer;Ljava/lang/reflect/Type;ZI)[Ljava/lang/String;
        //    553: astore  9
        //    555: aload  9
        //    557: arraylength
        //    558: iconst_1
        //    559: if_icmpeq  572 (offset +13)
        //    562: new  #92 // org.freedesktop.dbus.exceptions.DBusException
        //    565: dup
        //    566: ldc  #20 // 'Multi-valued array types not permitted'
        //    568: invokespecial  #190 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    571: athrow
        //    572: aload_0
        //    573: iload_3
        //    574: aaload
        //    575: aload  9
        //    577: iconst_0
        //    578: aaload
        //    579: invokevirtual  #146 // java.lang.StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    582: pop
        //    583: goto  610 (offset +27)
        //    586: astore  9
        //    588: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //    591: ldc  #1 // ''
        //    593: aload  9
        //    595: invokeinterface  #219 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    600: new  #92 // org.freedesktop.dbus.exceptions.DBusException
        //    603: dup
        //    604: ldc  #19 // 'Map must have 2 parameters'
        //    606: invokespecial  #190 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    609: athrow
        //    610: aload_0
        //    611: iload_3
        //    612: aaload
        //    613: bipush  125
        //    615: invokevirtual  #145 // java.lang.StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //    618: pop
        //    619: goto  1415 (offset +796)
        //    622: ldc  #75 // java.util.List
        //    624: aload  6
        //    626: invokeinterface  #201 // java.lang.reflect.ParameterizedType.getRawType:()Ljava/lang/reflect/Type;, count 1
        //    631: checkcast  #43 // java.lang.Class
        //    634: invokevirtual  #128 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    637: ifeq  751 (offset +114)
        //    640: aload  6
        //    642: invokeinterface  #200 // java.lang.reflect.ParameterizedType.getActualTypeArguments:()[Ljava/lang/reflect/Type;, count 1
        //    647: astore  8
        //    649: aload  8
        //    651: arraylength
        //    652: istore  9
        //    654: iconst_0
        //    655: istore  10
        //    657: iload  10
        //    659: iload  9
        //    661: if_icmpge  748 (offset +87)
        //    664: aload  8
        //    666: iload  10
        //    668: aaload
        //    669: astore  11
        //    671: ldc  #68 // java.lang.reflect.Type
        //    673: aload  11
        //    675: invokevirtual  #134 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    678: ifeq  693 (offset +15)
        //    681: aload_0
        //    682: iload_3
        //    683: aaload
        //    684: bipush  103
        //    686: invokevirtual  #145 // java.lang.StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //    689: pop
        //    690: goto  742 (offset +52)
        //    693: aload_0
        //    694: aload  11
        //    696: iconst_0
        //    697: iload_3
        //    698: iconst_1
        //    699: iadd
        //    700: invokestatic  #184 // org.freedesktop.dbus.Marshalling.recursiveGetDBusType:([Ljava/lang/StringBuffer;Ljava/lang/reflect/Type;ZI)[Ljava/lang/String;
        //    703: astore  12
        //    705: aload  12
        //    707: arraylength
        //    708: iconst_1
        //    709: if_icmpeq  722 (offset +13)
        //    712: new  #92 // org.freedesktop.dbus.exceptions.DBusException
        //    715: dup
        //    716: ldc  #20 // 'Multi-valued array types not permitted'
        //    718: invokespecial  #190 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    721: athrow
        //    722: aload_0
        //    723: iload_3
        //    724: aaload
        //    725: bipush  97
        //    727: invokevirtual  #145 // java.lang.StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //    730: pop
        //    731: aload_0
        //    732: iload_3
        //    733: aaload
        //    734: aload  12
        //    736: iconst_0
        //    737: aaload
        //    738: invokevirtual  #146 // java.lang.StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    741: pop
        //    742: iinc  10, 1
        //    745: goto  657 (offset -88)
        //    748: goto  1415 (offset +667)
        //    751: aload  6
        //    753: invokeinterface  #201 // java.lang.reflect.ParameterizedType.getRawType:()Ljava/lang/reflect/Type;, count 1
        //    758: ldc  #101 // org.freedesktop.dbus.types.Variant
        //    760: invokeinterface  #202 // java.lang.reflect.Type.equals:(Ljava/lang/Object;)Z, count 2
        //    765: ifeq  780 (offset +15)
        //    768: aload_0
        //    769: iload_3
        //    770: aaload
        //    771: bipush  118
        //    773: invokevirtual  #145 // java.lang.StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //    776: pop
        //    777: goto  1415 (offset +638)
        //    780: ldc  #93 // org.freedesktop.dbus.interfaces.DBusInterface
        //    782: aload  6
        //    784: invokeinterface  #201 // java.lang.reflect.ParameterizedType.getRawType:()Ljava/lang/reflect/Type;, count 1
        //    789: checkcast  #43 // java.lang.Class
        //    792: invokevirtual  #128 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    795: ifeq  810 (offset +15)
        //    798: aload_0
        //    799: iload_3
        //    800: aaload
        //    801: bipush  111
        //    803: invokevirtual  #145 // java.lang.StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //    806: pop
        //    807: goto  1415 (offset +608)
        //    810: ldc  #87 // org.freedesktop.dbus.Struct
        //    812: aload  6
        //    814: invokeinterface  #201 // java.lang.reflect.ParameterizedType.getRawType:()Ljava/lang/reflect/Type;, count 1
        //    819: checkcast  #43 // java.lang.Class
        //    822: invokevirtual  #128 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    825: ifeq  840 (offset +15)
        //    828: aload_0
        //    829: iload_3
        //    830: aaload
        //    831: bipush  40
        //    833: invokevirtual  #145 // java.lang.StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //    836: pop
        //    837: goto  1415 (offset +578)
        //    840: ldc  #88 // org.freedesktop.dbus.Tuple
        //    842: aload  6
        //    844: invokeinterface  #201 // java.lang.reflect.ParameterizedType.getRawType:()Ljava/lang/reflect/Type;, count 1
        //    849: checkcast  #43 // java.lang.Class
        //    852: invokevirtual  #128 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    855: ifeq  939 (offset +84)
        //    858: aload  6
        //    860: invokeinterface  #200 // java.lang.reflect.ParameterizedType.getActualTypeArguments:()[Ljava/lang/reflect/Type;, count 1
        //    865: astore  8
        //    867: new  #70 // java.util.ArrayList
        //    870: dup
        //    871: invokespecial  #165 // java.util.ArrayList.<init>:()V
        //    874: astore  9
        //    876: aload  8
        //    878: astore  10
        //    880: aload  10
        //    882: arraylength
        //    883: istore  11
        //    885: iconst_0
        //    886: istore  12
        //    888: iload  12
        //    890: iload  11
        //    892: if_icmpge  924 (offset +32)
        //    895: aload  10
        //    897: iload  12
        //    899: aaload
        //    900: astore  13
        //    902: aload  9
        //    904: aload_0
        //    905: aload  13
        //    907: iconst_0
        //    908: iload_3
        //    909: iconst_1
        //    910: iadd
        //    911: invokestatic  #184 // org.freedesktop.dbus.Marshalling.recursiveGetDBusType:([Ljava/lang/StringBuffer;Ljava/lang/reflect/Type;ZI)[Ljava/lang/String;
        //    914: invokestatic  #168 // java.util.Collections.addAll:(Ljava/util/Collection;[Ljava/lang/Object;)Z
        //    917: pop
        //    918: iinc  12, 1
        //    921: goto  888 (offset -33)
        //    924: aload  9
        //    926: iconst_0
        //    927: anewarray  #54 // java.lang.String
        //    930: invokeinterface  #210 // java.util.List.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;, count 2
        //    935: checkcast  #36 // [Ljava.lang.String;
        //    938: areturn
        //    939: new  #92 // org.freedesktop.dbus.exceptions.DBusException
        //    942: dup
        //    943: aload_1
        //    944: invokestatic  #143 // java.lang.String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    947: invokedynamic  #229 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //    952: invokespecial  #190 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    955: athrow
        //    956: aload_1
        //    957: instanceof  #43 // java.lang.Class
        //    960: ifeq  1415 (offset +455)
        //    963: aload_1
        //    964: checkcast  #43 // java.lang.Class
        //    967: astore  7
        //    969: aload  7
        //    971: invokevirtual  #127 // java.lang.Class.isArray:()Z
        //    974: ifeq  1061 (offset +87)
        //    977: ldc  #68 // java.lang.reflect.Type
        //    979: aload_1
        //    980: checkcast  #43 // java.lang.Class
        //    983: invokevirtual  #121 // java.lang.Class.getComponentType:()Ljava/lang/Class;
        //    986: invokevirtual  #134 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    989: ifeq  1004 (offset +15)
        //    992: aload_0
        //    993: iload_3
        //    994: aaload
        //    995: bipush  103
        //    997: invokevirtual  #145 // java.lang.StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   1000: pop
        //   1001: goto  1415 (offset +414)
        //   1004: aload_0
        //   1005: iload_3
        //   1006: aaload
        //   1007: bipush  97
        //   1009: invokevirtual  #145 // java.lang.StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   1012: pop
        //   1013: aload_0
        //   1014: aload_1
        //   1015: checkcast  #43 // java.lang.Class
        //   1018: invokevirtual  #121 // java.lang.Class.getComponentType:()Ljava/lang/Class;
        //   1021: iconst_0
        //   1022: iload_3
        //   1023: iconst_1
        //   1024: iadd
        //   1025: invokestatic  #184 // org.freedesktop.dbus.Marshalling.recursiveGetDBusType:([Ljava/lang/StringBuffer;Ljava/lang/reflect/Type;ZI)[Ljava/lang/String;
        //   1028: astore  8
        //   1030: aload  8
        //   1032: arraylength
        //   1033: iconst_1
        //   1034: if_icmpeq  1047 (offset +13)
        //   1037: new  #92 // org.freedesktop.dbus.exceptions.DBusException
        //   1040: dup
        //   1041: ldc  #20 // 'Multi-valued array types not permitted'
        //   1043: invokespecial  #190 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //   1046: athrow
        //   1047: aload_0
        //   1048: iload_3
        //   1049: aaload
        //   1050: aload  8
        //   1052: iconst_0
        //   1053: aaload
        //   1054: invokevirtual  #146 // java.lang.StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   1057: pop
        //   1058: goto  1415 (offset +357)
        //   1061: ldc  #87 // org.freedesktop.dbus.Struct
        //   1063: aload_1
        //   1064: checkcast  #43 // java.lang.Class
        //   1067: invokevirtual  #128 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   1070: ifeq  1283 (offset +213)
        //   1073: aload_0
        //   1074: iload_3
        //   1075: aaload
        //   1076: bipush  40
        //   1078: invokevirtual  #145 // java.lang.StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   1081: pop
        //   1082: aload_1
        //   1083: invokestatic  #172 // org.freedesktop.dbus.Container.getTypeCache:(Ljava/lang/reflect/Type;)[Ljava/lang/reflect/Type;
        //   1086: astore  8
        //   1088: aconst_null
        //   1089: aload  8
        //   1091: if_acmpne  1185 (offset +94)
        //   1094: aload_1
        //   1095: checkcast  #43 // java.lang.Class
        //   1098: invokevirtual  #124 // java.lang.Class.getDeclaredFields:()[Ljava/lang/reflect/Field;
        //   1101: astore  9
        //   1103: aload  9
        //   1105: arraylength
        //   1106: anewarray  #68 // java.lang.reflect.Type
        //   1109: astore  8
        //   1111: aload  9
        //   1113: astore  10
        //   1115: aload  10
        //   1117: arraylength
        //   1118: istore  11
        //   1120: iconst_0
        //   1121: istore  12
        //   1123: iload  12
        //   1125: iload  11
        //   1127: if_icmpge  1179 (offset +52)
        //   1130: aload  10
        //   1132: iload  12
        //   1134: aaload
        //   1135: astore  13
        //   1137: aload  13
        //   1139: ldc  #89 // org.freedesktop.dbus.annotations.Position
        //   1141: invokevirtual  #159 // java.lang.reflect.Field.getAnnotation:(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
        //   1144: checkcast  #89 // org.freedesktop.dbus.annotations.Position
        //   1147: astore  14
        //   1149: aconst_null
        //   1150: aload  14
        //   1152: if_acmpne  1158 (offset +6)
        //   1155: goto  1173 (offset +18)
        //   1158: aload  8
        //   1160: aload  14
        //   1162: invokeinterface  #217 // org.freedesktop.dbus.annotations.Position.value:()I, count 1
        //   1167: aload  13
        //   1169: invokevirtual  #160 // java.lang.reflect.Field.getGenericType:()Ljava/lang/reflect/Type;
        //   1172: aastore
        //   1173: iinc  12, 1
        //   1176: goto  1123 (offset -53)
        //   1179: aload_1
        //   1180: aload  8
        //   1182: invokestatic  #173 // org.freedesktop.dbus.Container.putTypeCache:(Ljava/lang/reflect/Type;[Ljava/lang/reflect/Type;)V
        //   1185: aload  8
        //   1187: astore  9
        //   1189: aload  9
        //   1191: arraylength
        //   1192: istore  10
        //   1194: iconst_0
        //   1195: istore  11
        //   1197: iload  11
        //   1199: iload  10
        //   1201: if_icmpge  1271 (offset +70)
        //   1204: aload  9
        //   1206: iload  11
        //   1208: aaload
        //   1209: astore  12
        //   1211: aload  12
        //   1213: ifnull  1265 (offset +52)
        //   1216: aload_0
        //   1217: aload  12
        //   1219: iconst_0
        //   1220: iload_3
        //   1221: iconst_1
        //   1222: iadd
        //   1223: invokestatic  #184 // org.freedesktop.dbus.Marshalling.recursiveGetDBusType:([Ljava/lang/StringBuffer;Ljava/lang/reflect/Type;ZI)[Ljava/lang/String;
        //   1226: astore  13
        //   1228: aload  13
        //   1230: arraylength
        //   1231: istore  14
        //   1233: iconst_0
        //   1234: istore  15
        //   1236: iload  15
        //   1238: iload  14
        //   1240: if_icmpge  1265 (offset +25)
        //   1243: aload  13
        //   1245: iload  15
        //   1247: aaload
        //   1248: astore  16
        //   1250: aload_0
        //   1251: iload_3
        //   1252: aaload
        //   1253: aload  16
        //   1255: invokevirtual  #146 // java.lang.StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   1258: pop
        //   1259: iinc  15, 1
        //   1262: goto  1236 (offset -26)
        //   1265: iinc  11, 1
        //   1268: goto  1197 (offset -71)
        //   1271: aload_0
        //   1272: iload_3
        //   1273: aaload
        //   1274: bipush  41
        //   1276: invokevirtual  #145 // java.lang.StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   1279: pop
        //   1280: goto  1415 (offset +135)
        //   1283: ldc  #45 // java.lang.Enum
        //   1285: aload  7
        //   1287: invokevirtual  #128 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   1290: ifeq  1305 (offset +15)
        //   1293: aload_0
        //   1294: iload_3
        //   1295: aaload
        //   1296: bipush  115
        //   1298: invokevirtual  #145 // java.lang.StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   1301: pop
        //   1302: goto  1415 (offset +113)
        //   1305: iconst_0
        //   1306: istore  8
        //   1308: getstatic  #113 // org.freedesktop.dbus.Marshalling.CLASS_TO_ARGUMENTTYPE:Ljava/util/Map;
        //   1311: invokeinterface  #211 // java.util.Map.entrySet:()Ljava/util/Set;, count 1
        //   1316: invokeinterface  #216 // java.util.Set.iterator:()Ljava/util/Iterator;, count 1
        //   1321: astore  9
        //   1323: aload  9
        //   1325: invokeinterface  #204 // java.util.Iterator.hasNext:()Z, count 1
        //   1330: ifeq  1393 (offset +63)
        //   1333: aload  9
        //   1335: invokeinterface  #205 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //   1340: checkcast  #77 // java.util.Map$Entry
        //   1343: astore  10
        //   1345: aload  10
        //   1347: invokeinterface  #214 // java.util.Map$Entry.getKey:()Ljava/lang/Object;, count 1
        //   1352: checkcast  #43 // java.lang.Class
        //   1355: aload  7
        //   1357: invokevirtual  #128 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   1360: ifeq  1390 (offset +30)
        //   1363: aload_0
        //   1364: iload_3
        //   1365: aaload
        //   1366: aload  10
        //   1368: invokeinterface  #215 // java.util.Map$Entry.getValue:()Ljava/lang/Object;, count 1
        //   1373: checkcast  #40 // java.lang.Byte
        //   1376: invokevirtual  #117 // java.lang.Byte.byteValue:()B
        //   1379: i2c
        //   1380: invokevirtual  #145 // java.lang.StringBuffer.append:(C)Ljava/lang/StringBuffer;
        //   1383: pop
        //   1384: iconst_1
        //   1385: istore  8
        //   1387: goto  1393 (offset +6)
        //   1390: goto  1323 (offset -67)
        //   1393: iload  8
        //   1395: ifne  1415 (offset +20)
        //   1398: new  #92 // org.freedesktop.dbus.exceptions.DBusException
        //   1401: dup
        //   1402: aload_1
        //   1403: invokestatic  #143 // java.lang.String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   1406: invokedynamic  #230 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //   1411: invokespecial  #190 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //   1414: athrow
        //   1415: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //   1418: ldc  #3 // 'Converted Java type: {} to D-Bus Type: {}'
        //   1420: aload_1
        //   1421: aload_0
        //   1422: iload_3
        //   1423: aaload
        //   1424: invokeinterface  #225 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //   1429: iconst_1
        //   1430: anewarray  #54 // java.lang.String
        //   1433: dup
        //   1434: iconst_0
        //   1435: aload_0
        //   1436: iload_3
        //   1437: aaload
        //   1438: invokevirtual  #149 // java.lang.StringBuffer.toString:()Ljava/lang/String;
        //   1441: aastore
        //   1442: areturn
        //       Exception table:
        //         from 499 to 583 target 586 type java.lang.ArrayIndexOutOfBoundsException
    }

  public static int getJavaType(String arg0, List arg1, int arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aconst_null
        //      1: aload_0
        //      2: if_acmpeq  17 (offset +15)
        //      5: aload_0
        //      6: invokevirtual  #139 // java.lang.String.isEmpty:()Z
        //      9: ifne  17 (offset +8)
        //     12: iconst_0
        //     13: iload_2
        //     14: if_icmpne  19 (offset +5)
        //     17: iconst_0
        //     18: ireturn
        //     19: iconst_0
        //     20: istore_3
        //     21: iload_3
        //     22: aload_0
        //     23: invokevirtual  #140 // java.lang.String.length:()I
        //     26: if_icmpge  718 (offset +692)
        //     29: iconst_m1
        //     30: iload_2
        //     31: if_icmpeq  44 (offset +13)
        //     34: iload_2
        //     35: aload_1
        //     36: invokeinterface  #209 // java.util.List.size:()I, count 1
        //     41: if_icmple  718 (offset +677)
        //     44: aload_0
        //     45: iload_3
        //     46: invokevirtual  #136 // java.lang.String.charAt:(I)C
        //     49: lookupswitch  default->680, 40->204, 97->318, 98->469, 100->577, 102->589, 103->625, 104->613, 105->529, 110->481, 111->505, 113->517, 115->601, 116->565, 117->541, 118->457, 120->553, 121->493, 123->637
        //    204: iload_3
        //    205: iconst_1
        //    206: iadd
        //    207: istore  4
        //    209: iconst_1
        //    210: istore  5
        //    212: iload  5
        //    214: ifle  254 (offset +40)
        //    217: bipush  41
        //    219: aload_0
        //    220: iload  4
        //    222: invokevirtual  #136 // java.lang.String.charAt:(I)C
        //    225: if_icmpne  234 (offset +9)
        //    228: iinc  5, -1
        //    231: goto  248 (offset +17)
        //    234: bipush  40
        //    236: aload_0
        //    237: iload  4
        //    239: invokevirtual  #136 // java.lang.String.charAt:(I)C
        //    242: if_icmpne  248 (offset +6)
        //    245: iinc  5, 1
        //    248: iinc  4, 1
        //    251: goto  212 (offset -39)
        //    254: new  #70 // java.util.ArrayList
        //    257: dup
        //    258: invokespecial  #165 // java.util.ArrayList.<init>:()V
        //    261: astore  5
        //    263: aload_0
        //    264: iload_3
        //    265: iconst_1
        //    266: iadd
        //    267: iload  4
        //    269: iconst_1
        //    270: isub
        //    271: invokevirtual  #142 // java.lang.String.substring:(II)Ljava/lang/String;
        //    274: aload  5
        //    276: iconst_m1
        //    277: invokestatic  #181 // org.freedesktop.dbus.Marshalling.getJavaType:(Ljava/lang/String;Ljava/util/List;I)I
        //    280: istore  6
        //    282: aload_1
        //    283: new  #97 // org.freedesktop.dbus.types.DBusStructType
        //    286: dup
        //    287: aload  5
        //    289: iconst_0
        //    290: anewarray  #68 // java.lang.reflect.Type
        //    293: invokeinterface  #210 // java.util.List.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;, count 2
        //    298: checkcast  #37 // [Ljava.lang.reflect.Type;
        //    301: invokespecial  #193 // org.freedesktop.dbus.types.DBusStructType.<init>:([Ljava/lang/reflect/Type;)V
        //    304: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    309: pop
        //    310: iload  4
        //    312: iconst_1
        //    313: isub
        //    314: istore_3
        //    315: goto  712 (offset +397)
        //    318: bipush  123
        //    320: aload_0
        //    321: iload_3
        //    322: iconst_1
        //    323: iadd
        //    324: invokevirtual  #136 // java.lang.String.charAt:(I)C
        //    327: if_icmpne  400 (offset +73)
        //    330: new  #70 // java.util.ArrayList
        //    333: dup
        //    334: invokespecial  #165 // java.util.ArrayList.<init>:()V
        //    337: astore  5
        //    339: aload_0
        //    340: iload_3
        //    341: iconst_2
        //    342: iadd
        //    343: invokevirtual  #141 // java.lang.String.substring:(I)Ljava/lang/String;
        //    346: aload  5
        //    348: iconst_2
        //    349: invokestatic  #181 // org.freedesktop.dbus.Marshalling.getJavaType:(Ljava/lang/String;Ljava/util/List;I)I
        //    352: istore  6
        //    354: aload_1
        //    355: new  #96 // org.freedesktop.dbus.types.DBusMapType
        //    358: dup
        //    359: aload  5
        //    361: iconst_0
        //    362: invokeinterface  #207 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //    367: checkcast  #68 // java.lang.reflect.Type
        //    370: aload  5
        //    372: iconst_1
        //    373: invokeinterface  #207 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //    378: checkcast  #68 // java.lang.reflect.Type
        //    381: invokespecial  #192 // org.freedesktop.dbus.types.DBusMapType.<init>:(Ljava/lang/reflect/Type;Ljava/lang/reflect/Type;)V
        //    384: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    389: pop
        //    390: iload_3
        //    391: iload  6
        //    393: iconst_2
        //    394: iadd
        //    395: iadd
        //    396: istore_3
        //    397: goto  712 (offset +315)
        //    400: new  #70 // java.util.ArrayList
        //    403: dup
        //    404: invokespecial  #165 // java.util.ArrayList.<init>:()V
        //    407: astore  5
        //    409: aload_0
        //    410: iload_3
        //    411: iconst_1
        //    412: iadd
        //    413: invokevirtual  #141 // java.lang.String.substring:(I)Ljava/lang/String;
        //    416: aload  5
        //    418: iconst_1
        //    419: invokestatic  #181 // org.freedesktop.dbus.Marshalling.getJavaType:(Ljava/lang/String;Ljava/util/List;I)I
        //    422: istore  6
        //    424: aload_1
        //    425: new  #95 // org.freedesktop.dbus.types.DBusListType
        //    428: dup
        //    429: aload  5
        //    431: iconst_0
        //    432: invokeinterface  #207 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //    437: checkcast  #68 // java.lang.reflect.Type
        //    440: invokespecial  #191 // org.freedesktop.dbus.types.DBusListType.<init>:(Ljava/lang/reflect/Type;)V
        //    443: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    448: pop
        //    449: iload_3
        //    450: iload  6
        //    452: iadd
        //    453: istore_3
        //    454: goto  712 (offset +258)
        //    457: aload_1
        //    458: ldc  #101 // org.freedesktop.dbus.types.Variant
        //    460: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    465: pop
        //    466: goto  712 (offset +246)
        //    469: aload_1
        //    470: ldc  #39 // java.lang.Boolean
        //    472: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    477: pop
        //    478: goto  712 (offset +234)
        //    481: aload_1
        //    482: ldc  #53 // java.lang.Short
        //    484: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    489: pop
        //    490: goto  712 (offset +222)
        //    493: aload_1
        //    494: ldc  #40 // java.lang.Byte
        //    496: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    501: pop
        //    502: goto  712 (offset +210)
        //    505: aload_1
        //    506: ldc  #83 // org.freedesktop.dbus.DBusPath
        //    508: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    513: pop
        //    514: goto  712 (offset +198)
        //    517: aload_1
        //    518: ldc  #98 // org.freedesktop.dbus.types.UInt16
        //    520: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    525: pop
        //    526: goto  712 (offset +186)
        //    529: aload_1
        //    530: ldc  #49 // java.lang.Integer
        //    532: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    537: pop
        //    538: goto  712 (offset +174)
        //    541: aload_1
        //    542: ldc  #99 // org.freedesktop.dbus.types.UInt32
        //    544: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    549: pop
        //    550: goto  712 (offset +162)
        //    553: aload_1
        //    554: ldc  #50 // java.lang.Long
        //    556: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    561: pop
        //    562: goto  712 (offset +150)
        //    565: aload_1
        //    566: ldc  #100 // org.freedesktop.dbus.types.UInt64
        //    568: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    573: pop
        //    574: goto  712 (offset +138)
        //    577: aload_1
        //    578: ldc  #44 // java.lang.Double
        //    580: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    585: pop
        //    586: goto  712 (offset +126)
        //    589: aload_1
        //    590: ldc  #46 // java.lang.Float
        //    592: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    597: pop
        //    598: goto  712 (offset +114)
        //    601: aload_1
        //    602: ldc  #41 // java.lang.CharSequence
        //    604: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    609: pop
        //    610: goto  712 (offset +102)
        //    613: aload_1
        //    614: ldc  #84 // org.freedesktop.dbus.FileDescriptor
        //    616: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    621: pop
        //    622: goto  712 (offset +90)
        //    625: aload_1
        //    626: ldc  #37 // [Ljava.lang.reflect.Type;
        //    628: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    633: pop
        //    634: goto  712 (offset +78)
        //    637: aload_1
        //    638: ldc  #77 // java.util.Map$Entry
        //    640: invokeinterface  #206 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //    645: pop
        //    646: new  #70 // java.util.ArrayList
        //    649: dup
        //    650: invokespecial  #165 // java.util.ArrayList.<init>:()V
        //    653: astore  5
        //    655: aload_0
        //    656: iload_3
        //    657: iconst_1
        //    658: iadd
        //    659: invokevirtual  #141 // java.lang.String.substring:(I)Ljava/lang/String;
        //    662: aload  5
        //    664: iconst_2
        //    665: invokestatic  #181 // org.freedesktop.dbus.Marshalling.getJavaType:(Ljava/lang/String;Ljava/util/List;I)I
        //    668: istore  6
        //    670: iload_3
        //    671: iload  6
        //    673: iconst_1
        //    674: iadd
        //    675: iadd
        //    676: istore_3
        //    677: goto  712 (offset +35)
        //    680: new  #92 // org.freedesktop.dbus.exceptions.DBusException
        //    683: dup
        //    684: ldc  #18 // 'Failed to parse DBus type signature: %s (%s).'
        //    686: iconst_2
        //    687: anewarray  #52 // java.lang.Object
        //    690: dup
        //    691: iconst_0
        //    692: aload_0
        //    693: aastore
        //    694: dup
        //    695: iconst_1
        //    696: aload_0
        //    697: iload_3
        //    698: invokevirtual  #136 // java.lang.String.charAt:(I)C
        //    701: invokestatic  #119 // java.lang.Character.valueOf:(C)Ljava/lang/Character;
        //    704: aastore
        //    705: invokestatic  #138 // java.lang.String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    708: invokespecial  #190 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    711: athrow
        //    712: iinc  3, 1
        //    715: goto  21 (offset -694)
        //    718: iload_3
        //    719: ireturn
        //    720: astore_3
        //    721: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //    724: ldc  #16 // 'Failed to parse DBus type signature.'
        //    726: aload_3
        //    727: invokeinterface  #219 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    732: new  #92 // org.freedesktop.dbus.exceptions.DBusException
        //    735: dup
        //    736: aload_0
        //    737: invokedynamic  #231 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //    742: invokespecial  #190 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    745: athrow
        //       Exception table:
        //         from 19 to 719 target 720 type java.lang.IndexOutOfBoundsException
    }

  public static Object[] convertParameters(Object[] arg0, Type[] arg1, String[] arg2, AbstractConnectionBase arg3) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: ifnonnull  6 (offset +5)
        //      4: aconst_null
        //      5: areturn
        //      6: aload_0
        //      7: astore  4
        //      9: aload_1
        //     10: astore  5
        //     12: iconst_0
        //     13: istore  6
        //     15: iconst_0
        //     16: istore  7
        //     18: iload  7
        //     20: aload  4
        //     22: arraylength
        //     23: if_icmpge  636 (offset +613)
        //     26: aconst_null
        //     27: aload  4
        //     29: iload  7
        //     31: aaload
        //     32: if_acmpne  38 (offset +6)
        //     35: goto  630 (offset +595)
        //     38: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //     41: ldc  #4 // "Converting {} from '{}' to {}"
        //     43: iconst_3
        //     44: anewarray  #52 // java.lang.Object
        //     47: dup
        //     48: iconst_0
        //     49: iload  7
        //     51: invokestatic  #131 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //     54: aastore
        //     55: dup
        //     56: iconst_1
        //     57: aload  4
        //     59: iload  7
        //     61: aaload
        //     62: aastore
        //     63: dup
        //     64: iconst_2
        //     65: aload  5
        //     67: iload  7
        //     69: aaload
        //     70: aastore
        //     71: invokeinterface  #227 // org.slf4j.Logger.trace:(Ljava/lang/String;[Ljava/lang/Object;)V, count 3
        //     76: aload  4
        //     78: iload  7
        //     80: aaload
        //     81: astore  11
        //     83: aload  11
        //     85: instanceof  #94 // org.freedesktop.dbus.interfaces.DBusSerializable
        //     88: ifeq  308 (offset +220)
        //     91: aload  11
        //     93: checkcast  #94 // org.freedesktop.dbus.interfaces.DBusSerializable
        //     96: astore  8
        //     98: aload  4
        //    100: iload  7
        //    102: aaload
        //    103: invokevirtual  #135 // java.lang.Object.getClass:()Ljava/lang/Class;
        //    106: invokevirtual  #125 // java.lang.Class.getDeclaredMethods:()[Ljava/lang/reflect/Method;
        //    109: astore  11
        //    111: aload  11
        //    113: arraylength
        //    114: istore  12
        //    116: iconst_0
        //    117: istore  13
        //    119: iload  13
        //    121: iload  12
        //    123: if_icmpge  302 (offset +179)
        //    126: aload  11
        //    128: iload  13
        //    130: aaload
        //    131: astore  14
        //    133: aload  14
        //    135: invokevirtual  #162 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    138: ldc  #33 // 'deserialize'
        //    140: invokevirtual  #137 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    143: ifeq  296 (offset +153)
        //    146: aload  14
        //    148: invokevirtual  #163 // java.lang.reflect.Method.getParameterTypes:()[Ljava/lang/Class;
        //    151: astore  15
        //    153: aload  5
        //    155: arraylength
        //    156: aload  15
        //    158: arraylength
        //    159: iadd
        //    160: iconst_1
        //    161: isub
        //    162: anewarray  #68 // java.lang.reflect.Type
        //    165: astore  16
        //    167: aload  5
        //    169: iconst_0
        //    170: aload  16
        //    172: iconst_0
        //    173: iload  7
        //    175: invokestatic  #153 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    178: aload  15
        //    180: iconst_0
        //    181: aload  16
        //    183: iload  7
        //    185: aload  15
        //    187: arraylength
        //    188: invokestatic  #153 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    191: aload  5
        //    193: iload  7
        //    195: iconst_1
        //    196: iadd
        //    197: aload  16
        //    199: iload  7
        //    201: aload  15
        //    203: arraylength
        //    204: iadd
        //    205: aload  5
        //    207: arraylength
        //    208: iload  7
        //    210: isub
        //    211: iconst_1
        //    212: isub
        //    213: invokestatic  #153 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    216: aload  16
        //    218: astore  5
        //    220: aload  8
        //    222: invokeinterface  #218 // org.freedesktop.dbus.interfaces.DBusSerializable.serialize:()[Ljava/lang/Object;, count 1
        //    227: astore  17
        //    229: aload  4
        //    231: arraylength
        //    232: aload  17
        //    234: arraylength
        //    235: iadd
        //    236: iconst_1
        //    237: isub
        //    238: anewarray  #52 // java.lang.Object
        //    241: astore  18
        //    243: aload  4
        //    245: iconst_0
        //    246: aload  18
        //    248: iconst_0
        //    249: iload  7
        //    251: invokestatic  #153 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    254: aload  17
        //    256: iconst_0
        //    257: aload  18
        //    259: iload  7
        //    261: aload  17
        //    263: arraylength
        //    264: invokestatic  #153 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    267: aload  4
        //    269: iload  7
        //    271: iconst_1
        //    272: iadd
        //    273: aload  18
        //    275: iload  7
        //    277: aload  17
        //    279: arraylength
        //    280: iadd
        //    281: aload  4
        //    283: arraylength
        //    284: iload  7
        //    286: isub
        //    287: iconst_1
        //    288: isub
        //    289: invokestatic  #153 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    292: aload  18
        //    294: astore  4
        //    296: iinc  13, 1
        //    299: goto  119 (offset -180)
        //    302: iinc  7, -1
        //    305: goto  630 (offset +325)
        //    308: aload  4
        //    310: iload  7
        //    312: aaload
        //    313: astore  11
        //    315: aload  11
        //    317: instanceof  #88 // org.freedesktop.dbus.Tuple
        //    320: ifeq  512 (offset +192)
        //    323: aload  11
        //    325: checkcast  #88 // org.freedesktop.dbus.Tuple
        //    328: astore  9
        //    330: aload  5
        //    332: iload  7
        //    334: aaload
        //    335: checkcast  #67 // java.lang.reflect.ParameterizedType
        //    338: invokeinterface  #200 // java.lang.reflect.ParameterizedType.getActualTypeArguments:()[Ljava/lang/reflect/Type;, count 1
        //    343: astore  11
        //    345: aload  5
        //    347: arraylength
        //    348: aload  11
        //    350: arraylength
        //    351: iadd
        //    352: iconst_1
        //    353: isub
        //    354: anewarray  #68 // java.lang.reflect.Type
        //    357: astore  12
        //    359: aload  5
        //    361: iconst_0
        //    362: aload  12
        //    364: iconst_0
        //    365: iload  7
        //    367: invokestatic  #153 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    370: aload  11
        //    372: iconst_0
        //    373: aload  12
        //    375: iload  7
        //    377: aload  11
        //    379: arraylength
        //    380: invokestatic  #153 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    383: aload  5
        //    385: iload  7
        //    387: iconst_1
        //    388: iadd
        //    389: aload  12
        //    391: iload  7
        //    393: aload  11
        //    395: arraylength
        //    396: iadd
        //    397: aload  5
        //    399: arraylength
        //    400: iload  7
        //    402: isub
        //    403: iconst_1
        //    404: isub
        //    405: invokestatic  #153 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    408: aload  12
        //    410: astore  5
        //    412: aload  9
        //    414: invokevirtual  #187 // org.freedesktop.dbus.Tuple.getParameters:()[Ljava/lang/Object;
        //    417: astore  13
        //    419: aload  4
        //    421: arraylength
        //    422: aload  13
        //    424: arraylength
        //    425: iadd
        //    426: iconst_1
        //    427: isub
        //    428: anewarray  #52 // java.lang.Object
        //    431: astore  14
        //    433: aload  4
        //    435: iconst_0
        //    436: aload  14
        //    438: iconst_0
        //    439: iload  7
        //    441: invokestatic  #153 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    444: aload  13
        //    446: iconst_0
        //    447: aload  14
        //    449: iload  7
        //    451: aload  13
        //    453: arraylength
        //    454: invokestatic  #153 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    457: aload  4
        //    459: iload  7
        //    461: iconst_1
        //    462: iadd
        //    463: aload  14
        //    465: iload  7
        //    467: aload  13
        //    469: arraylength
        //    470: iadd
        //    471: aload  4
        //    473: arraylength
        //    474: iload  7
        //    476: isub
        //    477: iconst_1
        //    478: isub
        //    479: invokestatic  #153 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    482: aload  14
        //    484: astore  4
        //    486: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //    489: invokeinterface  #222 // org.slf4j.Logger.isTraceEnabled:()Z, count 1
        //    494: aload  14
        //    496: aload  12
        //    498: invokedynamic  #232 // invokedynamic run:([Ljava/lang/Object;[Ljava/lang/reflect/Type;)Ljava/lang/Runnable;
        //    503: invokestatic  #197 // org.freedesktop.dbus.utils.LoggingHelper.logIf:(ZLjava/lang/Runnable;)V
        //    506: iinc  7, -1
        //    509: goto  630 (offset +121)
        //    512: aload  5
        //    514: iload  7
        //    516: aaload
        //    517: instanceof  #69 // java.lang.reflect.TypeVariable
        //    520: ifeq  597 (offset +77)
        //    523: aload  4
        //    525: iload  7
        //    527: aaload
        //    528: instanceof  #101 // org.freedesktop.dbus.types.Variant
        //    531: ifne  597 (offset +66)
        //    534: aload_2
        //    535: ifnull  577 (offset +42)
        //    538: aload_2
        //    539: arraylength
        //    540: ifle  577 (offset +37)
        //    543: aload_2
        //    544: arraylength
        //    545: iload  6
        //    547: if_icmple  577 (offset +30)
        //    550: aload  4
        //    552: iload  7
        //    554: new  #101 // org.freedesktop.dbus.types.Variant
        //    557: dup
        //    558: aload  4
        //    560: iload  7
        //    562: aaload
        //    563: aload_2
        //    564: iload  6
        //    566: aaload
        //    567: invokespecial  #195 // org.freedesktop.dbus.types.Variant.<init>:(Ljava/lang/Object;Ljava/lang/String;)V
        //    570: aastore
        //    571: iinc  6, 1
        //    574: goto  630 (offset +56)
        //    577: aload  4
        //    579: iload  7
        //    581: new  #101 // org.freedesktop.dbus.types.Variant
        //    584: dup
        //    585: aload  4
        //    587: iload  7
        //    589: aaload
        //    590: invokespecial  #194 // org.freedesktop.dbus.types.Variant.<init>:(Ljava/lang/Object;)V
        //    593: aastore
        //    594: goto  630 (offset +36)
        //    597: aload  4
        //    599: iload  7
        //    601: aaload
        //    602: astore  11
        //    604: aload  11
        //    606: instanceof  #93 // org.freedesktop.dbus.interfaces.DBusInterface
        //    609: ifeq  630 (offset +21)
        //    612: aload  11
        //    614: checkcast  #93 // org.freedesktop.dbus.interfaces.DBusInterface
        //    617: astore  10
        //    619: aload  4
        //    621: iload  7
        //    623: aload_3
        //    624: aload  10
        //    626: invokevirtual  #189 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getExportedObject:(Lorg/freedesktop/dbus/interfaces/DBusInterface;)Ljava/lang/String;
        //    629: aastore
        //    630: iinc  7, 1
        //    633: goto  18 (offset -615)
        //    636: aload  4
        //    638: areturn
    }

  public static Object[] convertParameters(Object[] arg0, Type[] arg1, AbstractConnectionBase arg2) {
        return convertParameters(arg0, arg1, null, arg2);
    }

  static Object deSerializeParameter(Object arg0, Type arg1, AbstractConnectionBase arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //      3: ldc  #8 // 'Deserializing from {} to {}'
        //      5: aload_0
        //      6: invokevirtual  #135 // java.lang.Object.getClass:()Ljava/lang/Class;
        //      9: aload_1
        //     10: invokeinterface  #225 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //     15: aload_0
        //     16: astore_3
        //     17: aload_1
        //     18: instanceof  #69 // java.lang.reflect.TypeVariable
        //     21: ifeq  54 (offset +33)
        //     24: aload_3
        //     25: instanceof  #101 // org.freedesktop.dbus.types.Variant
        //     28: ifeq  54 (offset +26)
        //     31: aload_3
        //     32: checkcast  #101 // org.freedesktop.dbus.types.Variant
        //     35: astore  4
        //     37: aload  4
        //     39: invokevirtual  #196 // org.freedesktop.dbus.types.Variant.getValue:()Ljava/lang/Object;
        //     42: astore_3
        //     43: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //     46: ldc  #30 // 'Type is variant, unwrapping to {}'
        //     48: aload_3
        //     49: invokeinterface  #224 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //     54: aload_1
        //     55: instanceof  #43 // java.lang.Class
        //     58: ifeq  125 (offset +67)
        //     61: aload_1
        //     62: checkcast  #43 // java.lang.Class
        //     65: invokevirtual  #127 // java.lang.Class.isArray:()Z
        //     68: ifeq  125 (offset +57)
        //     71: aload_1
        //     72: checkcast  #43 // java.lang.Class
        //     75: invokevirtual  #121 // java.lang.Class.getComponentType:()Ljava/lang/Class;
        //     78: ldc  #68 // java.lang.reflect.Type
        //     80: invokevirtual  #134 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //     83: ifeq  125 (offset +42)
        //     86: aload_3
        //     87: instanceof  #54 // java.lang.String
        //     90: ifeq  125 (offset +35)
        //     93: new  #70 // java.util.ArrayList
        //     96: dup
        //     97: invokespecial  #165 // java.util.ArrayList.<init>:()V
        //    100: astore  4
        //    102: aload_3
        //    103: checkcast  #54 // java.lang.String
        //    106: aload  4
        //    108: iconst_m1
        //    109: invokestatic  #181 // org.freedesktop.dbus.Marshalling.getJavaType:(Ljava/lang/String;Ljava/util/List;I)I
        //    112: pop
        //    113: aload  4
        //    115: iconst_0
        //    116: anewarray  #68 // java.lang.reflect.Type
        //    119: invokeinterface  #210 // java.util.List.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;, count 2
        //    124: astore_3
        //    125: aload_3
        //    126: instanceof  #86 // org.freedesktop.dbus.ObjectPath
        //    129: ifeq  202 (offset +73)
        //    132: aload_3
        //    133: checkcast  #86 // org.freedesktop.dbus.ObjectPath
        //    136: astore  4
        //    138: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //    141: ldc  #24 // 'Parameter is ObjectPath'
        //    143: invokeinterface  #223 // org.slf4j.Logger.trace:(Ljava/lang/String;)V, count 2
        //    148: aload_1
        //    149: instanceof  #43 // java.lang.Class
        //    152: ifeq  189 (offset +37)
        //    155: ldc  #93 // org.freedesktop.dbus.interfaces.DBusInterface
        //    157: aload_1
        //    158: checkcast  #43 // java.lang.Class
        //    161: invokevirtual  #128 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    164: ifeq  189 (offset +25)
        //    167: aload_2
        //    168: aload  4
        //    170: invokevirtual  #186 // org.freedesktop.dbus.ObjectPath.getSource:()Ljava/lang/String;
        //    173: aload  4
        //    175: invokevirtual  #185 // org.freedesktop.dbus.ObjectPath.getPath:()Ljava/lang/String;
        //    178: aload_1
        //    179: checkcast  #43 // java.lang.Class
        //    182: invokevirtual  #188 // org.freedesktop.dbus.connections.base.AbstractConnectionBase.getExportedObject:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Class;)Lorg/freedesktop/dbus/interfaces/DBusInterface;
        //    185: astore_3
        //    186: goto  202 (offset +16)
        //    189: new  #83 // org.freedesktop.dbus.DBusPath
        //    192: dup
        //    193: aload  4
        //    195: invokevirtual  #185 // org.freedesktop.dbus.ObjectPath.getPath:()Ljava/lang/String;
        //    198: invokespecial  #174 // org.freedesktop.dbus.DBusPath.<init>:(Ljava/lang/String;)V
        //    201: astore_3
        //    202: aload_3
        //    203: instanceof  #54 // java.lang.String
        //    206: ifeq  254 (offset +48)
        //    209: aload_3
        //    210: checkcast  #54 // java.lang.String
        //    213: astore  4
        //    215: aload_1
        //    216: instanceof  #43 // java.lang.Class
        //    219: ifeq  254 (offset +35)
        //    222: ldc  #45 // java.lang.Enum
        //    224: aload_1
        //    225: checkcast  #43 // java.lang.Class
        //    228: invokevirtual  #128 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    231: ifeq  254 (offset +23)
        //    234: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //    237: ldc  #31 // 'Type seems to be an enum'
        //    239: invokeinterface  #223 // org.slf4j.Logger.trace:(Ljava/lang/String;)V, count 2
        //    244: aload_1
        //    245: checkcast  #43 // java.lang.Class
        //    248: aload  4
        //    250: invokestatic  #129 // java.lang.Enum.valueOf:(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;
        //    253: astore_3
        //    254: aload_3
        //    255: instanceof  #35 // [Ljava.lang.Object;
        //    258: ifeq  471 (offset +213)
        //    261: aload_3
        //    262: checkcast  #35 // [Ljava.lang.Object;
        //    265: astore  4
        //    267: aload_1
        //    268: instanceof  #43 // java.lang.Class
        //    271: ifeq  471 (offset +200)
        //    274: ldc  #87 // org.freedesktop.dbus.Struct
        //    276: aload_1
        //    277: checkcast  #43 // java.lang.Class
        //    280: invokevirtual  #128 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    283: ifeq  471 (offset +188)
        //    286: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //    289: ldc  #6 // 'Creating Struct {} from {}'
        //    291: aload_1
        //    292: aload_3
        //    293: invokeinterface  #225 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    298: aload_1
        //    299: invokestatic  #172 // org.freedesktop.dbus.Container.getTypeCache:(Ljava/lang/reflect/Type;)[Ljava/lang/reflect/Type;
        //    302: astore  5
        //    304: aload  5
        //    306: ifnonnull  400 (offset +94)
        //    309: aload_1
        //    310: checkcast  #43 // java.lang.Class
        //    313: invokevirtual  #124 // java.lang.Class.getDeclaredFields:()[Ljava/lang/reflect/Field;
        //    316: astore  6
        //    318: aload  6
        //    320: arraylength
        //    321: anewarray  #68 // java.lang.reflect.Type
        //    324: astore  5
        //    326: aload  6
        //    328: astore  7
        //    330: aload  7
        //    332: arraylength
        //    333: istore  8
        //    335: iconst_0
        //    336: istore  9
        //    338: iload  9
        //    340: iload  8
        //    342: if_icmpge  394 (offset +52)
        //    345: aload  7
        //    347: iload  9
        //    349: aaload
        //    350: astore  10
        //    352: aload  10
        //    354: ldc  #89 // org.freedesktop.dbus.annotations.Position
        //    356: invokevirtual  #159 // java.lang.reflect.Field.getAnnotation:(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
        //    359: checkcast  #89 // org.freedesktop.dbus.annotations.Position
        //    362: astore  11
        //    364: aconst_null
        //    365: aload  11
        //    367: if_acmpne  373 (offset +6)
        //    370: goto  388 (offset +18)
        //    373: aload  5
        //    375: aload  11
        //    377: invokeinterface  #217 // org.freedesktop.dbus.annotations.Position.value:()I, count 1
        //    382: aload  10
        //    384: invokevirtual  #160 // java.lang.reflect.Field.getGenericType:()Ljava/lang/reflect/Type;
        //    387: aastore
        //    388: iinc  9, 1
        //    391: goto  338 (offset -53)
        //    394: aload_1
        //    395: aload  5
        //    397: invokestatic  #173 // org.freedesktop.dbus.Container.putTypeCache:(Ljava/lang/reflect/Type;[Ljava/lang/reflect/Type;)V
        //    400: aload  4
        //    402: aload  5
        //    404: aload_2
        //    405: invokestatic  #178 // org.freedesktop.dbus.Marshalling.deSerializeParameters:([Ljava/lang/Object;[Ljava/lang/reflect/Type;Lorg/freedesktop/dbus/connections/base/AbstractConnectionBase;)[Ljava/lang/Object;
        //    408: astore_3
        //    409: aload_1
        //    410: checkcast  #43 // java.lang.Class
        //    413: invokevirtual  #123 // java.lang.Class.getDeclaredConstructors:()[Ljava/lang/reflect/Constructor;
        //    416: astore  6
        //    418: aload  6
        //    420: arraylength
        //    421: istore  7
        //    423: iconst_0
        //    424: istore  8
        //    426: iload  8
        //    428: iload  7
        //    430: if_icmpge  471 (offset +41)
        //    433: aload  6
        //    435: iload  8
        //    437: aaload
        //    438: astore  9
        //    440: aload  9
        //    442: aload  4
        //    444: invokevirtual  #158 // java.lang.reflect.Constructor.newInstance:([Ljava/lang/Object;)Ljava/lang/Object;
        //    447: astore_3
        //    448: goto  471 (offset +23)
        //    451: astore  10
        //    453: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //    456: ldc  #5 // 'Could not create new instance'
        //    458: aload  10
        //    460: invokeinterface  #226 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    465: iinc  8, 1
        //    468: goto  426 (offset -42)
        //    471: aload_3
        //    472: instanceof  #35 // [Ljava.lang.Object;
        //    475: ifeq  523 (offset +48)
        //    478: aload_3
        //    479: checkcast  #35 // [Ljava.lang.Object;
        //    482: astore  4
        //    484: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //    487: ldc  #26 // 'Parameter is object array'
        //    489: invokeinterface  #223 // org.slf4j.Logger.trace:(Ljava/lang/String;)V, count 2
        //    494: aload  4
        //    496: arraylength
        //    497: anewarray  #68 // java.lang.reflect.Type
        //    500: astore  5
        //    502: aload  5
        //    504: aload_3
        //    505: invokevirtual  #135 // java.lang.Object.getClass:()Ljava/lang/Class;
        //    508: invokevirtual  #121 // java.lang.Class.getComponentType:()Ljava/lang/Class;
        //    511: invokestatic  #167 // java.util.Arrays.fill:([Ljava/lang/Object;Ljava/lang/Object;)V
        //    514: aload  4
        //    516: aload  5
        //    518: aload_2
        //    519: invokestatic  #178 // org.freedesktop.dbus.Marshalling.deSerializeParameters:([Ljava/lang/Object;[Ljava/lang/reflect/Type;Lorg/freedesktop/dbus/connections/base/AbstractConnectionBase;)[Ljava/lang/Object;
        //    522: astore_3
        //    523: aload_3
        //    524: instanceof  #75 // java.util.List
        //    527: ifeq  645 (offset +118)
        //    530: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //    533: ldc  #23 // 'Parameter is List'
        //    535: invokeinterface  #223 // org.slf4j.Logger.trace:(Ljava/lang/String;)V, count 2
        //    540: aload_1
        //    541: instanceof  #67 // java.lang.reflect.ParameterizedType
        //    544: ifeq  567 (offset +23)
        //    547: aload_1
        //    548: checkcast  #67 // java.lang.reflect.ParameterizedType
        //    551: astore  5
        //    553: aload  5
        //    555: invokeinterface  #200 // java.lang.reflect.ParameterizedType.getActualTypeArguments:()[Ljava/lang/reflect/Type;, count 1
        //    560: iconst_0
        //    561: aaload
        //    562: astore  4
        //    564: goto  628 (offset +64)
        //    567: aload_1
        //    568: instanceof  #65 // java.lang.reflect.GenericArrayType
        //    571: ifeq  592 (offset +21)
        //    574: aload_1
        //    575: checkcast  #65 // java.lang.reflect.GenericArrayType
        //    578: astore  6
        //    580: aload  6
        //    582: invokeinterface  #199 // java.lang.reflect.GenericArrayType.getGenericComponentType:()Ljava/lang/reflect/Type;, count 1
        //    587: astore  4
        //    589: goto  628 (offset +39)
        //    592: aload_1
        //    593: instanceof  #43 // java.lang.Class
        //    596: ifeq  625 (offset +29)
        //    599: aload_1
        //    600: checkcast  #43 // java.lang.Class
        //    603: astore  7
        //    605: aload_1
        //    606: checkcast  #43 // java.lang.Class
        //    609: invokevirtual  #127 // java.lang.Class.isArray:()Z
        //    612: ifeq  625 (offset +13)
        //    615: aload  7
        //    617: invokevirtual  #121 // java.lang.Class.getComponentType:()Ljava/lang/Class;
        //    620: astore  4
        //    622: goto  628 (offset +6)
        //    625: aconst_null
        //    626: astore  4
        //    628: aconst_null
        //    629: aload  4
        //    631: if_acmpeq  645 (offset +14)
        //    634: aload_3
        //    635: checkcast  #75 // java.util.List
        //    638: aload  4
        //    640: aload_2
        //    641: invokestatic  #177 // org.freedesktop.dbus.Marshalling.deSerializeParameters:(Ljava/util/List;Ljava/lang/reflect/Type;Lorg/freedesktop/dbus/connections/base/AbstractConnectionBase;)Ljava/util/List;
        //    644: astore_3
        //    645: aload_1
        //    646: ldc  #46 // java.lang.Float
        //    648: invokeinterface  #202 // java.lang.reflect.Type.equals:(Ljava/lang/Object;)Z, count 2
        //    653: ifne  668 (offset +15)
        //    656: aload_1
        //    657: getstatic  #108 // java.lang.Float.TYPE:Ljava/lang/Class;
        //    660: invokeinterface  #202 // java.lang.reflect.Type.equals:(Ljava/lang/Object;)Z, count 2
        //    665: ifeq  697 (offset +32)
        //    668: aload_3
        //    669: instanceof  #46 // java.lang.Float
        //    672: ifne  697 (offset +25)
        //    675: aload_3
        //    676: checkcast  #51 // java.lang.Number
        //    679: invokevirtual  #132 // java.lang.Number.floatValue:()F
        //    682: invokestatic  #130 // java.lang.Float.valueOf:(F)Ljava/lang/Float;
        //    685: astore_3
        //    686: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //    689: ldc  #25 // 'Parameter is float of value: {}'
        //    691: aload_3
        //    692: invokeinterface  #224 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    697: aload_3
        //    698: instanceof  #35 // [Ljava.lang.Object;
        //    701: ifne  721 (offset +20)
        //    704: aload_3
        //    705: instanceof  #75 // java.util.List
        //    708: ifne  721 (offset +13)
        //    711: aload_3
        //    712: invokevirtual  #135 // java.lang.Object.getClass:()Ljava/lang/Class;
        //    715: invokevirtual  #127 // java.lang.Class.isArray:()Z
        //    718: ifeq  964 (offset +246)
        //    721: aload_1
        //    722: instanceof  #67 // java.lang.reflect.ParameterizedType
        //    725: ifeq  752 (offset +27)
        //    728: aload_1
        //    729: checkcast  #67 // java.lang.reflect.ParameterizedType
        //    732: astore  4
        //    734: aload_3
        //    735: aload  4
        //    737: invokeinterface  #201 // java.lang.reflect.ParameterizedType.getRawType:()Ljava/lang/reflect/Type;, count 1
        //    742: checkcast  #43 // java.lang.Class
        //    745: invokestatic  #171 // org.freedesktop.dbus.ArrayFrob.convert:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    748: astore_3
        //    749: goto  964 (offset +215)
        //    752: aload_1
        //    753: instanceof  #65 // java.lang.reflect.GenericArrayType
        //    756: ifeq  844 (offset +88)
        //    759: aload_1
        //    760: checkcast  #65 // java.lang.reflect.GenericArrayType
        //    763: astore  5
        //    765: aload  5
        //    767: invokeinterface  #199 // java.lang.reflect.GenericArrayType.getGenericComponentType:()Ljava/lang/reflect/Type;, count 1
        //    772: astore  7
        //    774: aconst_null
        //    775: astore  8
        //    777: aload  7
        //    779: instanceof  #43 // java.lang.Class
        //    782: ifeq  796 (offset +14)
        //    785: aload  7
        //    787: checkcast  #43 // java.lang.Class
        //    790: astore  9
        //    792: aload  9
        //    794: astore  8
        //    796: aload  7
        //    798: instanceof  #67 // java.lang.reflect.ParameterizedType
        //    801: ifeq  823 (offset +22)
        //    804: aload  7
        //    806: checkcast  #67 // java.lang.reflect.ParameterizedType
        //    809: astore  9
        //    811: aload  9
        //    813: invokeinterface  #201 // java.lang.reflect.ParameterizedType.getRawType:()Ljava/lang/reflect/Type;, count 1
        //    818: checkcast  #43 // java.lang.Class
        //    821: astore  8
        //    823: aload  8
        //    825: iconst_0
        //    826: invokestatic  #156 // java.lang.reflect.Array.newInstance:(Ljava/lang/Class;I)Ljava/lang/Object;
        //    829: astore  9
        //    831: aload_3
        //    832: aload  9
        //    834: invokevirtual  #135 // java.lang.Object.getClass:()Ljava/lang/Class;
        //    837: invokestatic  #171 // org.freedesktop.dbus.ArrayFrob.convert:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    840: astore_3
        //    841: goto  964 (offset +123)
        //    844: aload_1
        //    845: instanceof  #43 // java.lang.Class
        //    848: ifeq  964 (offset +116)
        //    851: aload_1
        //    852: checkcast  #43 // java.lang.Class
        //    855: astore  6
        //    857: aload_1
        //    858: checkcast  #43 // java.lang.Class
        //    861: invokevirtual  #127 // java.lang.Class.isArray:()Z
        //    864: ifeq  964 (offset +100)
        //    867: aload  6
        //    869: invokevirtual  #121 // java.lang.Class.getComponentType:()Ljava/lang/Class;
        //    872: astore  7
        //    874: aload  7
        //    876: ldc  #46 // java.lang.Float
        //    878: invokevirtual  #134 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    881: ifne  895 (offset +14)
        //    884: aload  7
        //    886: getstatic  #108 // java.lang.Float.TYPE:Ljava/lang/Class;
        //    889: invokevirtual  #134 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    892: ifeq  946 (offset +54)
        //    895: aload_3
        //    896: instanceof  #34 // [D
        //    899: ifeq  946 (offset +47)
        //    902: aload_3
        //    903: checkcast  #34 // [D
        //    906: astore  8
        //    908: aload  8
        //    910: arraylength
        //    911: newarray  float
        //    913: astore  9
        //    915: iconst_0
        //    916: istore  10
        //    918: iload  10
        //    920: aload  8
        //    922: arraylength
        //    923: if_icmpge  943 (offset +20)
        //    926: aload  9
        //    928: iload  10
        //    930: aload  8
        //    932: iload  10
        //    934: daload
        //    935: d2f
        //    936: fastore
        //    937: iinc  10, 1
        //    940: goto  918 (offset -22)
        //    943: aload  9
        //    945: astore_3
        //    946: aload  7
        //    948: iconst_0
        //    949: invokestatic  #156 // java.lang.reflect.Array.newInstance:(Ljava/lang/Class;I)Ljava/lang/Object;
        //    952: astore  8
        //    954: aload_3
        //    955: aload  8
        //    957: invokevirtual  #135 // java.lang.Object.getClass:()Ljava/lang/Class;
        //    960: invokestatic  #171 // org.freedesktop.dbus.ArrayFrob.convert:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    963: astore_3
        //    964: aload_3
        //    965: instanceof  #82 // org.freedesktop.dbus.DBusMap
        //    968: ifeq  1097 (offset +129)
        //    971: aload_3
        //    972: checkcast  #82 // org.freedesktop.dbus.DBusMap
        //    975: astore  4
        //    977: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //    980: ldc  #7 // 'Deserializing a Map'
        //    982: invokeinterface  #223 // org.slf4j.Logger.trace:(Ljava/lang/String;)V, count 2
        //    987: aload_1
        //    988: instanceof  #67 // java.lang.reflect.ParameterizedType
        //    991: ifeq  1012 (offset +21)
        //    994: aload_1
        //    995: checkcast  #67 // java.lang.reflect.ParameterizedType
        //    998: astore  6
        //   1000: aload  6
        //   1002: invokeinterface  #200 // java.lang.reflect.ParameterizedType.getActualTypeArguments:()[Ljava/lang/reflect/Type;, count 1
        //   1007: astore  5
        //   1009: goto  1021 (offset +12)
        //   1012: aload_3
        //   1013: invokevirtual  #135 // java.lang.Object.getClass:()Ljava/lang/Class;
        //   1016: invokevirtual  #126 // java.lang.Class.getTypeParameters:()[Ljava/lang/reflect/TypeVariable;
        //   1019: astore  5
        //   1021: iconst_0
        //   1022: istore  6
        //   1024: iload  6
        //   1026: aload  4
        //   1028: getfield  #112 // org.freedesktop.dbus.DBusMap.entries:[[Ljava/lang/Object;
        //   1031: arraylength
        //   1032: if_icmpge  1097 (offset +65)
        //   1035: aload  4
        //   1037: getfield  #112 // org.freedesktop.dbus.DBusMap.entries:[[Ljava/lang/Object;
        //   1040: iload  6
        //   1042: aaload
        //   1043: iconst_0
        //   1044: aload  4
        //   1046: getfield  #112 // org.freedesktop.dbus.DBusMap.entries:[[Ljava/lang/Object;
        //   1049: iload  6
        //   1051: aaload
        //   1052: iconst_0
        //   1053: aaload
        //   1054: aload  5
        //   1056: iconst_0
        //   1057: aaload
        //   1058: aload_2
        //   1059: invokestatic  #176 // org.freedesktop.dbus.Marshalling.deSerializeParameter:(Ljava/lang/Object;Ljava/lang/reflect/Type;Lorg/freedesktop/dbus/connections/base/AbstractConnectionBase;)Ljava/lang/Object;
        //   1062: aastore
        //   1063: aload  4
        //   1065: getfield  #112 // org.freedesktop.dbus.DBusMap.entries:[[Ljava/lang/Object;
        //   1068: iload  6
        //   1070: aaload
        //   1071: iconst_1
        //   1072: aload  4
        //   1074: getfield  #112 // org.freedesktop.dbus.DBusMap.entries:[[Ljava/lang/Object;
        //   1077: iload  6
        //   1079: aaload
        //   1080: iconst_1
        //   1081: aaload
        //   1082: aload  5
        //   1084: iconst_1
        //   1085: aaload
        //   1086: aload_2
        //   1087: invokestatic  #176 // org.freedesktop.dbus.Marshalling.deSerializeParameter:(Ljava/lang/Object;Ljava/lang/reflect/Type;Lorg/freedesktop/dbus/connections/base/AbstractConnectionBase;)Ljava/lang/Object;
        //   1090: aastore
        //   1091: iinc  6, 1
        //   1094: goto  1024 (offset -70)
        //   1097: aload_3
        //   1098: areturn
        //       Exception table:
        //         from 440 to 448 target 451 type java.lang.IllegalArgumentException
    }

  static List deSerializeParameters(List arg0, Type arg1, AbstractConnectionBase arg2) {
        LOGGER.trace("Deserializing from {} to {}", arg0, arg1);
        int var3;
        if (arg0 != null) {
            var3 = 0;
        } else {
            return null;
        }
        while (var3 < arg0.size()) {
            if (arg0.get(var3) != null) {
                arg0.set(var3, deSerializeParameter(arg0.get(var3), arg1, arg2));
            }
            ++var3;
            continue;
        }
        return arg0;
    }

  public static Object[] deSerializeParameters(Object[] arg0, Type[] arg1, AbstractConnectionBase arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //      3: invokeinterface  #222 // org.slf4j.Logger.isTraceEnabled:()Z, count 1
        //      8: aload_0
        //      9: aload_1
        //     10: invokedynamic  #233 // invokedynamic run:([Ljava/lang/Object;[Ljava/lang/reflect/Type;)Ljava/lang/Runnable;
        //     15: invokestatic  #197 // org.freedesktop.dbus.utils.LoggingHelper.logIf:(ZLjava/lang/Runnable;)V
        //     18: aconst_null
        //     19: aload_0
        //     20: if_acmpne  25 (offset +5)
        //     23: aconst_null
        //     24: areturn
        //     25: aload_0
        //     26: astore_3
        //     27: aload_1
        //     28: astore  4
        //     30: aload  4
        //     32: arraylength
        //     33: iconst_1
        //     34: if_icmpne  85 (offset +51)
        //     37: aload  4
        //     39: iconst_0
        //     40: aaload
        //     41: astore  6
        //     43: aload  6
        //     45: instanceof  #67 // java.lang.reflect.ParameterizedType
        //     48: ifeq  85 (offset +37)
        //     51: aload  6
        //     53: checkcast  #67 // java.lang.reflect.ParameterizedType
        //     56: astore  5
        //     58: ldc  #88 // org.freedesktop.dbus.Tuple
        //     60: aload  5
        //     62: invokeinterface  #201 // java.lang.reflect.ParameterizedType.getRawType:()Ljava/lang/reflect/Type;, count 1
        //     67: checkcast  #43 // java.lang.Class
        //     70: invokevirtual  #128 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //     73: ifeq  85 (offset +12)
        //     76: aload  5
        //     78: invokeinterface  #200 // java.lang.reflect.ParameterizedType.getActualTypeArguments:()[Ljava/lang/reflect/Type;, count 1
        //     83: astore  4
        //     85: aload  4
        //     87: arraylength
        //     88: iconst_1
        //     89: if_icmpne  203 (offset +114)
        //     92: aload  4
        //     94: iconst_0
        //     95: aaload
        //     96: astore  6
        //     98: aload  6
        //    100: instanceof  #43 // java.lang.Class
        //    103: ifeq  203 (offset +100)
        //    106: aload  6
        //    108: checkcast  #43 // java.lang.Class
        //    111: astore  5
        //    113: ldc  #88 // org.freedesktop.dbus.Tuple
        //    115: aload  5
        //    117: invokevirtual  #128 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    120: ifeq  203 (offset +83)
        //    123: aload  4
        //    125: iconst_0
        //    126: aaload
        //    127: invokeinterface  #203 // java.lang.reflect.Type.getTypeName:()Ljava/lang/String;, count 1
        //    132: astore  6
        //    134: aload  6
        //    136: invokestatic  #120 // java.lang.Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //    139: invokevirtual  #123 // java.lang.Class.getDeclaredConstructors:()[Ljava/lang/reflect/Constructor;
        //    142: astore  7
        //    144: aload  7
        //    146: arraylength
        //    147: iconst_1
        //    148: if_icmpeq  161 (offset +13)
        //    151: new  #92 // org.freedesktop.dbus.exceptions.DBusException
        //    154: dup
        //    155: ldc  #11 // 'Error deserializing message: We had a Tuple type but wrong number of constructors for this Tuple. There should be exactly one.'
        //    157: invokespecial  #190 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    160: athrow
        //    161: aload  7
        //    163: iconst_0
        //    164: aaload
        //    165: invokevirtual  #157 // java.lang.reflect.Constructor.getParameterCount:()I
        //    168: aload_3
        //    169: arraylength
        //    170: if_icmpeq  183 (offset +13)
        //    173: new  #92 // org.freedesktop.dbus.exceptions.DBusException
        //    176: dup
        //    177: ldc  #10 // 'Error deserializing message: We had a Tuple type but it had wrong number of constructor arguments. The number of constructor arguments should match the number of parameters to deserialize.'
        //    179: invokespecial  #190 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    182: athrow
        //    183: aload  7
        //    185: iconst_0
        //    186: aaload
        //    187: aload_3
        //    188: invokevirtual  #158 // java.lang.reflect.Constructor.newInstance:([Ljava/lang/Object;)Ljava/lang/Object;
        //    191: astore  8
        //    193: iconst_1
        //    194: anewarray  #52 // java.lang.Object
        //    197: dup
        //    198: iconst_0
        //    199: aload  8
        //    201: aastore
        //    202: areturn
        //    203: iconst_0
        //    204: istore  5
        //    206: iload  5
        //    208: aload_3
        //    209: arraylength
        //    210: if_icmpge  672 (offset +462)
        //    213: iload  5
        //    215: aload  4
        //    217: arraylength
        //    218: if_icmplt  298 (offset +80)
        //    221: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //    224: invokeinterface  #221 // org.slf4j.Logger.isDebugEnabled:()Z, count 1
        //    229: ifeq  288 (offset +59)
        //    232: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //    235: ldc  #27 // 'Parameter length differs, expected {} but got {}'
        //    237: aload_3
        //    238: arraylength
        //    239: invokestatic  #131 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    242: aload  4
        //    244: arraylength
        //    245: invokestatic  #131 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    248: invokeinterface  #220 // org.slf4j.Logger.error:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    253: iconst_0
        //    254: istore  6
        //    256: iload  6
        //    258: aload_3
        //    259: arraylength
        //    260: if_icmpge  288 (offset +28)
        //    263: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //    266: ldc  #13 // "Error, Parameters differ: {}, '{}'"
        //    268: iload  6
        //    270: invokestatic  #131 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    273: aload_3
        //    274: iload  6
        //    276: aaload
        //    277: invokeinterface  #220 // org.slf4j.Logger.error:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    282: iinc  6, 1
        //    285: goto  256 (offset -29)
        //    288: new  #92 // org.freedesktop.dbus.exceptions.DBusException
        //    291: dup
        //    292: ldc  #12 // "Error deserializing message: number of parameters didn't match receiving signature"
        //    294: invokespecial  #190 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    297: athrow
        //    298: aconst_null
        //    299: aload_3
        //    300: iload  5
        //    302: aaload
        //    303: if_acmpne  309 (offset +6)
        //    306: goto  666 (offset +360)
        //    309: aload  4
        //    311: iload  5
        //    313: aaload
        //    314: instanceof  #43 // java.lang.Class
        //    317: ifeq  336 (offset +19)
        //    320: ldc  #94 // org.freedesktop.dbus.interfaces.DBusSerializable
        //    322: aload  4
        //    324: iload  5
        //    326: aaload
        //    327: checkcast  #43 // java.lang.Class
        //    330: invokevirtual  #128 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    333: ifne  376 (offset +43)
        //    336: aload  4
        //    338: iload  5
        //    340: aaload
        //    341: astore  7
        //    343: aload  7
        //    345: instanceof  #67 // java.lang.reflect.ParameterizedType
        //    348: ifeq  649 (offset +301)
        //    351: aload  7
        //    353: checkcast  #67 // java.lang.reflect.ParameterizedType
        //    356: astore  6
        //    358: ldc  #94 // org.freedesktop.dbus.interfaces.DBusSerializable
        //    360: aload  6
        //    362: invokeinterface  #201 // java.lang.reflect.ParameterizedType.getRawType:()Ljava/lang/reflect/Type;, count 1
        //    367: checkcast  #43 // java.lang.Class
        //    370: invokevirtual  #128 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    373: ifeq  649 (offset +276)
        //    376: aload  4
        //    378: iload  5
        //    380: aaload
        //    381: instanceof  #43 // java.lang.Class
        //    384: ifeq  400 (offset +16)
        //    387: aload  4
        //    389: iload  5
        //    391: aaload
        //    392: checkcast  #43 // java.lang.Class
        //    395: astore  7
        //    397: goto  418 (offset +21)
        //    400: aload  4
        //    402: iload  5
        //    404: aaload
        //    405: checkcast  #67 // java.lang.reflect.ParameterizedType
        //    408: invokeinterface  #201 // java.lang.reflect.ParameterizedType.getRawType:()Ljava/lang/reflect/Type;, count 1
        //    413: checkcast  #43 // java.lang.Class
        //    416: astore  7
        //    418: aload  7
        //    420: invokevirtual  #125 // java.lang.Class.getDeclaredMethods:()[Ljava/lang/reflect/Method;
        //    423: astore  8
        //    425: aload  8
        //    427: arraylength
        //    428: istore  9
        //    430: iconst_0
        //    431: istore  10
        //    433: iload  10
        //    435: iload  9
        //    437: if_icmpge  646 (offset +209)
        //    440: aload  8
        //    442: iload  10
        //    444: aaload
        //    445: astore  11
        //    447: aload  11
        //    449: invokevirtual  #162 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    452: ldc  #33 // 'deserialize'
        //    454: invokevirtual  #137 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    457: ifeq  640 (offset +183)
        //    460: aload  11
        //    462: invokevirtual  #161 // java.lang.reflect.Method.getGenericParameterTypes:()[Ljava/lang/reflect/Type;
        //    465: astore  12
        //    467: aload  12
        //    469: arraylength
        //    470: anewarray  #52 // java.lang.Object
        //    473: astore  13
        //    475: aload_3
        //    476: iload  5
        //    478: aload  13
        //    480: iconst_0
        //    481: aload  12
        //    483: arraylength
        //    484: invokestatic  #153 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    487: aload  13
        //    489: aload  12
        //    491: aload_2
        //    492: invokestatic  #178 // org.freedesktop.dbus.Marshalling.deSerializeParameters:([Ljava/lang/Object;[Ljava/lang/reflect/Type;Lorg/freedesktop/dbus/connections/base/AbstractConnectionBase;)[Ljava/lang/Object;
        //    495: astore  13
        //    497: aload  7
        //    499: iconst_0
        //    500: anewarray  #43 // java.lang.Class
        //    503: invokevirtual  #122 // java.lang.Class.getDeclaredConstructor:([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
        //    506: iconst_0
        //    507: anewarray  #52 // java.lang.Object
        //    510: invokevirtual  #158 // java.lang.reflect.Constructor.newInstance:([Ljava/lang/Object;)Ljava/lang/Object;
        //    513: checkcast  #94 // org.freedesktop.dbus.interfaces.DBusSerializable
        //    516: astore  14
        //    518: aload  11
        //    520: aload  14
        //    522: aload  13
        //    524: invokevirtual  #164 // java.lang.reflect.Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    527: pop
        //    528: aload_3
        //    529: arraylength
        //    530: aload  12
        //    532: arraylength
        //    533: isub
        //    534: iconst_1
        //    535: iadd
        //    536: anewarray  #52 // java.lang.Object
        //    539: astore  15
        //    541: aload_3
        //    542: iconst_0
        //    543: aload  15
        //    545: iconst_0
        //    546: iload  5
        //    548: invokestatic  #153 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    551: aload  15
        //    553: iload  5
        //    555: aload  14
        //    557: aastore
        //    558: aload_3
        //    559: iload  5
        //    561: aload  12
        //    563: arraylength
        //    564: iadd
        //    565: aload  15
        //    567: iload  5
        //    569: iconst_1
        //    570: iadd
        //    571: aload_3
        //    572: arraylength
        //    573: iload  5
        //    575: isub
        //    576: aload  12
        //    578: arraylength
        //    579: isub
        //    580: invokestatic  #153 // java.lang.System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    583: aload  15
        //    585: astore_3
        //    586: goto  640 (offset +54)
        //    589: astore  13
        //    591: getstatic  #114 // org.freedesktop.dbus.Marshalling.LOGGER:Lorg/slf4j/Logger;
        //    594: ldc  #1 // ''
        //    596: aload  13
        //    598: invokeinterface  #219 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    603: new  #92 // org.freedesktop.dbus.exceptions.DBusException
        //    606: dup
        //    607: ldc  #22 // 'Not enough elements to create custom object from serialized data (%s < %s).'
        //    609: iconst_2
        //    610: anewarray  #52 // java.lang.Object
        //    613: dup
        //    614: iconst_0
        //    615: aload_3
        //    616: arraylength
        //    617: iload  5
        //    619: isub
        //    620: invokestatic  #131 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    623: aastore
        //    624: dup
        //    625: iconst_1
        //    626: aload  12
        //    628: arraylength
        //    629: invokestatic  #131 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    632: aastore
        //    633: invokestatic  #138 // java.lang.String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    636: invokespecial  #190 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    639: athrow
        //    640: iinc  10, 1
        //    643: goto  433 (offset -210)
        //    646: goto  666 (offset +20)
        //    649: aload_3
        //    650: iload  5
        //    652: aload_3
        //    653: iload  5
        //    655: aaload
        //    656: aload  4
        //    658: iload  5
        //    660: aaload
        //    661: aload_2
        //    662: invokestatic  #176 // org.freedesktop.dbus.Marshalling.deSerializeParameter:(Ljava/lang/Object;Ljava/lang/reflect/Type;Lorg/freedesktop/dbus/connections/base/AbstractConnectionBase;)Ljava/lang/Object;
        //    665: aastore
        //    666: iinc  5, 1
        //    669: goto  206 (offset -463)
        //    672: aload_3
        //    673: areturn
        //       Exception table:
        //         from 467 to 586 target 589 type java.lang.ArrayIndexOutOfBoundsException
    }

  private static void lambda$deSerializeParameters$1(Object[] arg0, Type[] arg1) {
        LOGGER.trace("Deserializing from {} to {} ", Arrays.deepToString(arg0), Arrays.deepToString(arg1));
    }

  private static void lambda$convertParameters$0(Object[] arg0, Type[] arg1) {
        LOGGER.trace("New params: {}, new types: {}", Arrays.deepToString(arg0), Arrays.deepToString(arg1));
    }

}