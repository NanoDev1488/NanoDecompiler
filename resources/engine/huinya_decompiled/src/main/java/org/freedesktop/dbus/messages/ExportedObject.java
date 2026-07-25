// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.messages.ExportedObject
package org.freedesktop.dbus.messages;

import java.lang.annotation.Annotation;
import java.lang.ref.Reference;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;
import org.freedesktop.dbus.Marshalling;
import org.freedesktop.dbus.TypeRef;
import org.freedesktop.dbus.annotations.DBusBoundProperty;
import org.freedesktop.dbus.annotations.DBusIgnore;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.annotations.DBusMemberName;
import org.freedesktop.dbus.annotations.DBusProperty;
import org.freedesktop.dbus.annotations.DBusProperty_Access;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.utils.DBusNamingUtil;
import org.freedesktop.dbus.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExportedObject {

    // ---- поля ----
  private final Map methods;
  private final Map propertyMethods;
  private final String introspectionData;
  private final Reference object;

  public ExportedObject(DBusInterface arg0, boolean arg1) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokespecial  #119 // java.lang.Object.<init>:()V
        //      4: aload_0
        //      5: iload_2
        //      6: ifeq  20 (offset +14)
        //      9: new  #52 // java.lang.ref.WeakReference
        //     12: dup
        //     13: aload_1
        //     14: invokespecial  #131 // java.lang.ref.WeakReference.<init>:(Ljava/lang/Object;)V
        //     17: goto  28 (offset +11)
        //     20: new  #78 // org.freedesktop.dbus.StrongReference
        //     23: dup
        //     24: aload_1
        //     25: invokespecial  #161 // org.freedesktop.dbus.StrongReference.<init>:(Ljava/lang/Object;)V
        //     28: putfield  #106 // org.freedesktop.dbus.messages.ExportedObject.object:Ljava/lang/ref/Reference;
        //     31: aload_0
        //     32: new  #63 // java.util.HashMap
        //     35: dup
        //     36: invokespecial  #148 // java.util.HashMap.<init>:()V
        //     39: putfield  #105 // org.freedesktop.dbus.messages.ExportedObject.methods:Ljava/util/Map;
        //     42: aload_0
        //     43: new  #63 // java.util.HashMap
        //     46: dup
        //     47: invokespecial  #148 // java.util.HashMap.<init>:()V
        //     50: putfield  #107 // org.freedesktop.dbus.messages.ExportedObject.propertyMethods:Ljava/util/Map;
        //     53: aload_0
        //     54: aload_1
        //     55: invokeinterface  #219 // org.freedesktop.dbus.interfaces.DBusInterface.getClass:()Ljava/lang/Class;, count 1
        //     60: invokevirtual  #173 // org.freedesktop.dbus.messages.ExportedObject.getDBusInterfaces:(Ljava/lang/Class;)Ljava/util/Set;
        //     63: astore_3
        //     64: aload_3
        //     65: ldc  #92 // org.freedesktop.dbus.interfaces.Introspectable
        //     67: invokeinterface  #210 // java.util.Set.add:(Ljava/lang/Object;)Z, count 2
        //     72: pop
        //     73: aload_3
        //     74: ldc  #93 // org.freedesktop.dbus.interfaces.Peer
        //     76: invokeinterface  #210 // java.util.Set.add:(Ljava/lang/Object;)Z, count 2
        //     81: pop
        //     82: aload_0
        //     83: aload_0
        //     84: aload_3
        //     85: invokevirtual  #168 // org.freedesktop.dbus.messages.ExportedObject.generateIntrospectionXml:(Ljava/util/Set;)Ljava/lang/String;
        //     88: putfield  #104 // org.freedesktop.dbus.messages.ExportedObject.introspectionData:Ljava/lang/String;
        //     91: return
    }

  protected String generateAnnotationsXml(AnnotatedElement arg0) {
        StringBuilder var2 = new StringBuilder();
        Annotation[] var3 = arg0.getDeclaredAnnotations();
        int var4 = var3.length;
        int var5 = 0;
        while (var5 < var4) {
            Object var6 = var3[var5];
            if (var6.annotationType().isAnnotationPresent(DBusInterfaceName.class)) {
                Class var7 = var6.annotationType();
                String var8 = "";
                Method var9;
                try {
                    var9 = var7.getMethod("value", new Class[0]);
                    if (var9 != null) {
                        var8 = var9.invoke(var6, new Object[0]).toString();
                    }
                    var9 = DBusNamingUtil.getAnnotationName(var7);
                    var2.append("  <annotation name=\"").append(var9).append("\" value=\"").append(var8).append("\" />\n");
                } catch (NoSuchMethodException e1) {
                    var9 = e1;
                    LoggerFactory.getLogger(getClass()).trace("Could not find value", var9);
                }
                String var9 = DBusNamingUtil.getAnnotationName(var7);
                var2.append("  <annotation name=\"").append(var9).append("\" value=\"").append(var8).append("\" />\n");
            }
            ++var5;
            continue;
        }
        return var2.toString();
    }

  protected String generatePropertyXml(DBusProperty arg0) {
        return generatePropertyXml(arg0.name(), arg0.type(), arg0.access());
    }

  protected String generatePropertyXml(String arg0, Class arg1, DBusProperty_Access arg2) {
        String var4;
        Type var5;
        if (!TypeRef.class.isAssignableFrom(arg1)) {
            if (!List.class.equals(arg1)) {
                if (!Map.class.equals(arg1)) {
                    var4 = Marshalling.getDBusType(new Type[]{arg1});
                } else {
                    var4 = "a{vv}";
                }
            } else {
                var4 = "av";
            }
        } else {
            var5 = ((Type) Optional.ofNullable(Util.unwrapTypeRef(arg1)).orElseThrow(() -> lambda$generatePropertyXml$0(arg0)));
            var4 = Marshalling.getDBusType(new Type[]{var5});
        }
        String var5 = arg2.getAccessName();
        return "<property name=\"" + arg0 + "\" type=\"" + var4 + "\" access=\"" + var5 + "\" />";
    }

  protected String generatePropertiesXml(Class arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #44 // java.lang.StringBuilder
        //      3: dup
        //      4: invokespecial  #125 // java.lang.StringBuilder.<init>:()V
        //      7: astore_2
        //      8: new  #63 // java.util.HashMap
        //     11: dup
        //     12: invokespecial  #148 // java.util.HashMap.<init>:()V
        //     15: astore_3
        //     16: aload_1
        //     17: ldc  #85 // org.freedesktop.dbus.annotations.DBusProperties
        //     19: invokevirtual  #108 // java.lang.Class.getAnnotation:(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
        //     22: checkcast  #85 // org.freedesktop.dbus.annotations.DBusProperties
        //     25: astore  4
        //     27: aload  4
        //     29: ifnull  135 (offset +106)
        //     32: aload  4
        //     34: invokeinterface  #215 // org.freedesktop.dbus.annotations.DBusProperties.value:()[Lorg/freedesktop/dbus/annotations/DBusProperty;, count 1
        //     39: astore  5
        //     41: aload  5
        //     43: arraylength
        //     44: istore  6
        //     46: iconst_0
        //     47: istore  7
        //     49: iload  7
        //     51: iload  6
        //     53: if_icmpge  135 (offset +82)
        //     56: aload  5
        //     58: iload  7
        //     60: aaload
        //     61: astore  8
        //     63: aload_3
        //     64: aload  8
        //     66: invokeinterface  #217 // org.freedesktop.dbus.annotations.DBusProperty.name:()Ljava/lang/String;, count 1
        //     71: invokeinterface  #201 // java.util.Map.containsKey:(Ljava/lang/Object;)Z, count 2
        //     76: ifeq  106 (offset +30)
        //     79: new  #89 // org.freedesktop.dbus.exceptions.DBusException
        //     82: dup
        //     83: ldc  #30 // "Property ''{0}'' defined multiple times."
        //     85: iconst_1
        //     86: anewarray  #42 // java.lang.Object
        //     89: dup
        //     90: iconst_0
        //     91: aload  8
        //     93: invokeinterface  #217 // org.freedesktop.dbus.annotations.DBusProperty.name:()Ljava/lang/String;, count 1
        //     98: aastore
        //     99: invokestatic  #146 // java.text.MessageFormat.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    102: invokespecial  #164 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    105: athrow
        //    106: aload_3
        //    107: aload  8
        //    109: invokeinterface  #217 // org.freedesktop.dbus.annotations.DBusProperty.name:()Ljava/lang/String;, count 1
        //    114: new  #96 // org.freedesktop.dbus.propertyref.PropertyRef
        //    117: dup
        //    118: aload  8
        //    120: invokespecial  #179 // org.freedesktop.dbus.propertyref.PropertyRef.<init>:(Lorg/freedesktop/dbus/annotations/DBusProperty;)V
        //    123: invokeinterface  #203 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    128: pop
        //    129: iinc  7, 1
        //    132: goto  49 (offset -83)
        //    135: aload_1
        //    136: ldc  #86 // org.freedesktop.dbus.annotations.DBusProperty
        //    138: invokevirtual  #108 // java.lang.Class.getAnnotation:(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
        //    141: checkcast  #86 // org.freedesktop.dbus.annotations.DBusProperty
        //    144: astore  5
        //    146: aload  5
        //    148: ifnull  217 (offset +69)
        //    151: aload_3
        //    152: aload  5
        //    154: invokeinterface  #217 // org.freedesktop.dbus.annotations.DBusProperty.name:()Ljava/lang/String;, count 1
        //    159: invokeinterface  #201 // java.util.Map.containsKey:(Ljava/lang/Object;)Z, count 2
        //    164: ifeq  194 (offset +30)
        //    167: new  #89 // org.freedesktop.dbus.exceptions.DBusException
        //    170: dup
        //    171: ldc  #30 // "Property ''{0}'' defined multiple times."
        //    173: iconst_1
        //    174: anewarray  #42 // java.lang.Object
        //    177: dup
        //    178: iconst_0
        //    179: aload  5
        //    181: invokeinterface  #217 // org.freedesktop.dbus.annotations.DBusProperty.name:()Ljava/lang/String;, count 1
        //    186: aastore
        //    187: invokestatic  #146 // java.text.MessageFormat.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    190: invokespecial  #164 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    193: athrow
        //    194: aload_3
        //    195: aload  5
        //    197: invokeinterface  #217 // org.freedesktop.dbus.annotations.DBusProperty.name:()Ljava/lang/String;, count 1
        //    202: new  #96 // org.freedesktop.dbus.propertyref.PropertyRef
        //    205: dup
        //    206: aload  5
        //    208: invokespecial  #179 // org.freedesktop.dbus.propertyref.PropertyRef.<init>:(Lorg/freedesktop/dbus/annotations/DBusProperty;)V
        //    211: invokeinterface  #203 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    216: pop
        //    217: aload_1
        //    218: invokevirtual  #111 // java.lang.Class.getDeclaredMethods:()[Ljava/lang/reflect/Method;
        //    221: astore  6
        //    223: aload  6
        //    225: arraylength
        //    226: istore  7
        //    228: iconst_0
        //    229: istore  8
        //    231: iload  8
        //    233: iload  7
        //    235: if_icmpge  424 (offset +189)
        //    238: aload  6
        //    240: iload  8
        //    242: aaload
        //    243: astore  9
        //    245: aload  9
        //    247: ldc  #81 // org.freedesktop.dbus.annotations.DBusBoundProperty
        //    249: invokevirtual  #133 // java.lang.reflect.Method.getAnnotation:(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
        //    252: checkcast  #81 // org.freedesktop.dbus.annotations.DBusBoundProperty
        //    255: astore  10
        //    257: aload  10
        //    259: ifnull  418 (offset +159)
        //    262: aload  9
        //    264: invokestatic  #189 // org.freedesktop.dbus.utils.DBusNamingUtil.getPropertyName:(Ljava/lang/reflect/Method;)Ljava/lang/String;
        //    267: astore  11
        //    269: aload  9
        //    271: invokestatic  #180 // org.freedesktop.dbus.propertyref.PropertyRef.accessForMethod:(Ljava/lang/reflect/Method;)Lorg/freedesktop/dbus/annotations/DBusProperty$Access;
        //    274: astore  12
        //    276: aload  9
        //    278: invokestatic  #181 // org.freedesktop.dbus.propertyref.PropertyRef.checkMethod:(Ljava/lang/reflect/Method;)V
        //    281: aload  9
        //    283: invokestatic  #185 // org.freedesktop.dbus.propertyref.PropertyRef.typeForMethod:(Ljava/lang/reflect/Method;)Ljava/lang/Class;
        //    286: astore  13
        //    288: new  #96 // org.freedesktop.dbus.propertyref.PropertyRef
        //    291: dup
        //    292: aload  11
        //    294: aload  13
        //    296: aload  12
        //    298: invokespecial  #178 // org.freedesktop.dbus.propertyref.PropertyRef.<init>:(Ljava/lang/String;Ljava/lang/Class;Lorg/freedesktop/dbus/annotations/DBusProperty$Access;)V
        //    301: astore  14
        //    303: aload_0
        //    304: getfield  #107 // org.freedesktop.dbus.messages.ExportedObject.propertyMethods:Ljava/util/Map;
        //    307: aload  14
        //    309: aload  9
        //    311: invokeinterface  #203 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    316: pop
        //    317: aload_3
        //    318: aload  11
        //    320: invokeinterface  #201 // java.util.Map.containsKey:(Ljava/lang/Object;)Z, count 2
        //    325: ifeq  407 (offset +82)
        //    328: aload_3
        //    329: aload  11
        //    331: invokeinterface  #202 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    336: checkcast  #96 // org.freedesktop.dbus.propertyref.PropertyRef
        //    339: astore  15
        //    341: aload  12
        //    343: aload  15
        //    345: invokevirtual  #182 // org.freedesktop.dbus.propertyref.PropertyRef.getAccess:()Lorg/freedesktop/dbus/annotations/DBusProperty$Access;
        //    348: invokevirtual  #162 // org.freedesktop.dbus.annotations.DBusProperty$Access.equals:(Ljava/lang/Object;)Z
        //    351: ifeq  381 (offset +30)
        //    354: new  #89 // org.freedesktop.dbus.exceptions.DBusException
        //    357: dup
        //    358: ldc  #31 // "Property ''{0}'' has access mode ''{1}'' defined multiple times."
        //    360: iconst_2
        //    361: anewarray  #42 // java.lang.Object
        //    364: dup
        //    365: iconst_0
        //    366: aload  11
        //    368: aastore
        //    369: dup
        //    370: iconst_1
        //    371: aload  12
        //    373: aastore
        //    374: invokestatic  #146 // java.text.MessageFormat.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    377: invokespecial  #164 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    380: athrow
        //    381: aload_3
        //    382: aload  11
        //    384: new  #96 // org.freedesktop.dbus.propertyref.PropertyRef
        //    387: dup
        //    388: aload  11
        //    390: aload  13
        //    392: getstatic  #102 // org.freedesktop.dbus.annotations.DBusProperty$Access.READ_WRITE:Lorg/freedesktop/dbus/annotations/DBusProperty$Access;
        //    395: invokespecial  #178 // org.freedesktop.dbus.propertyref.PropertyRef.<init>:(Ljava/lang/String;Ljava/lang/Class;Lorg/freedesktop/dbus/annotations/DBusProperty$Access;)V
        //    398: invokeinterface  #203 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    403: pop
        //    404: goto  418 (offset +14)
        //    407: aload_3
        //    408: aload  11
        //    410: aload  14
        //    412: invokeinterface  #203 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    417: pop
        //    418: iinc  8, 1
        //    421: goto  231 (offset -190)
        //    424: aload_3
        //    425: invokeinterface  #206 // java.util.Map.values:()Ljava/util/Collection;, count 1
        //    430: invokeinterface  #196 // java.util.Collection.iterator:()Ljava/util/Iterator;, count 1
        //    435: astore  6
        //    437: aload  6
        //    439: invokeinterface  #197 // java.util.Iterator.hasNext:()Z, count 1
        //    444: ifeq  496 (offset +52)
        //    447: aload  6
        //    449: invokeinterface  #198 // java.util.Iterator.next:()Ljava/lang/Object;, count 1
        //    454: checkcast  #96 // org.freedesktop.dbus.propertyref.PropertyRef
        //    457: astore  7
        //    459: aload_2
        //    460: ldc  #4 // '  '
        //    462: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    465: aload_0
        //    466: aload  7
        //    468: invokevirtual  #183 // org.freedesktop.dbus.propertyref.PropertyRef.getName:()Ljava/lang/String;
        //    471: aload  7
        //    473: invokevirtual  #184 // org.freedesktop.dbus.propertyref.PropertyRef.getType:()Ljava/lang/Class;
        //    476: aload  7
        //    478: invokevirtual  #182 // org.freedesktop.dbus.propertyref.PropertyRef.getAccess:()Lorg/freedesktop/dbus/annotations/DBusProperty$Access;
        //    481: invokevirtual  #171 // org.freedesktop.dbus.messages.ExportedObject.generatePropertyXml:(Ljava/lang/String;Ljava/lang/Class;Lorg/freedesktop/dbus/annotations/DBusProperty$Access;)Ljava/lang/String;
        //    484: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    487: ldc  #3 // '\n'
        //    489: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    492: pop
        //    493: goto  437 (offset -56)
        //    496: aload_2
        //    497: invokevirtual  #127 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    500: areturn
    }

  protected String generateMethodsXml(Class arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: new  #44 // java.lang.StringBuilder
        //      3: dup
        //      4: invokespecial  #125 // java.lang.StringBuilder.<init>:()V
        //      7: astore_2
        //      8: aload_1
        //      9: invokevirtual  #111 // java.lang.Class.getDeclaredMethods:()[Ljava/lang/reflect/Method;
        //     12: astore_3
        //     13: aload_3
        //     14: arraylength
        //     15: istore  4
        //     17: iconst_0
        //     18: istore  5
        //     20: iload  5
        //     22: iload  4
        //     24: if_icmpge  540 (offset +516)
        //     27: aload_3
        //     28: iload  5
        //     30: aaload
        //     31: astore  6
        //     33: aload  6
        //     35: invokestatic  #174 // org.freedesktop.dbus.messages.ExportedObject.isExcluded:(Ljava/lang/reflect/Method;)Z
        //     38: ifeq  44 (offset +6)
        //     41: goto  534 (offset +493)
        //     44: aload  6
        //     46: invokestatic  #188 // org.freedesktop.dbus.utils.DBusNamingUtil.getMethodName:(Ljava/lang/reflect/Method;)Ljava/lang/String;
        //     49: astore  7
        //     51: aload  7
        //     53: invokevirtual  #124 // java.lang.String.length:()I
        //     56: sipush  255
        //     59: if_icmple  77 (offset +18)
        //     62: new  #89 // org.freedesktop.dbus.exceptions.DBusException
        //     65: dup
        //     66: aload  7
        //     68: invokedynamic  #223 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //     73: invokespecial  #164 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //     76: athrow
        //     77: aload_2
        //     78: ldc  #10 // '  <method name="'
        //     80: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     83: aload  7
        //     85: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     88: ldc  #15 // '" >\n'
        //     90: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //     93: pop
        //     94: aload_2
        //     95: aload_0
        //     96: aload  6
        //     98: invokevirtual  #167 // org.freedesktop.dbus.messages.ExportedObject.generateAnnotationsXml:(Ljava/lang/reflect/AnnotatedElement;)Ljava/lang/String;
        //    101: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    104: pop
        //    105: aload  6
        //    107: invokevirtual  #134 // java.lang.reflect.Method.getExceptionTypes:()[Ljava/lang/Class;
        //    110: astore  8
        //    112: aload  8
        //    114: arraylength
        //    115: istore  9
        //    117: iconst_0
        //    118: istore  10
        //    120: iload  10
        //    122: iload  9
        //    124: if_icmpge  181 (offset +57)
        //    127: aload  8
        //    129: iload  10
        //    131: aaload
        //    132: astore  11
        //    134: ldc  #90 // org.freedesktop.dbus.exceptions.DBusExecutionException
        //    136: aload  11
        //    138: invokevirtual  #118 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    141: ifeq  175 (offset +34)
        //    144: aload_2
        //    145: ldc  #5 // '   <annotation name="org.freedesktop.DBus.Method.Error" value="'
        //    147: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    150: getstatic  #103 // org.freedesktop.dbus.connections.AbstractConnection.DOLLAR_PATTERN:Ljava/util/regex/Pattern;
        //    153: aload  11
        //    155: invokevirtual  #114 // java.lang.Class.getName:()Ljava/lang/String;
        //    158: invokevirtual  #157 // java.util.regex.Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //    161: ldc  #21 // '.'
        //    163: invokevirtual  #156 // java.util.regex.Matcher.replaceAll:(Ljava/lang/String;)Ljava/lang/String;
        //    166: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    169: ldc  #14 // '" />\n'
        //    171: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    174: pop
        //    175: iinc  10, 1
        //    178: goto  120 (offset -58)
        //    181: new  #44 // java.lang.StringBuilder
        //    184: dup
        //    185: invokespecial  #125 // java.lang.StringBuilder.<init>:()V
        //    188: astore  8
        //    190: aload  6
        //    192: invokevirtual  #135 // java.lang.reflect.Method.getGenericParameterTypes:()[Ljava/lang/reflect/Type;
        //    195: astore  9
        //    197: aload  9
        //    199: arraylength
        //    200: istore  10
        //    202: iconst_0
        //    203: istore  11
        //    205: iload  11
        //    207: iload  10
        //    209: if_icmpge  285 (offset +76)
        //    212: aload  9
        //    214: iload  11
        //    216: aaload
        //    217: astore  12
        //    219: aload  12
        //    221: invokestatic  #158 // org.freedesktop.dbus.Marshalling.getDBusType:(Ljava/lang/reflect/Type;)[Ljava/lang/String;
        //    224: astore  13
        //    226: aload  13
        //    228: arraylength
        //    229: istore  14
        //    231: iconst_0
        //    232: istore  15
        //    234: iload  15
        //    236: iload  14
        //    238: if_icmpge  279 (offset +41)
        //    241: aload  13
        //    243: iload  15
        //    245: aaload
        //    246: astore  16
        //    248: aload_2
        //    249: ldc  #6 // '   <arg type="'
        //    251: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    254: aload  16
        //    256: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    259: ldc  #16 // '" direction="in"/>\n'
        //    261: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    264: pop
        //    265: aload  8
        //    267: aload  16
        //    269: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    272: pop
        //    273: iinc  15, 1
        //    276: goto  234 (offset -42)
        //    279: iinc  11, 1
        //    282: goto  205 (offset -77)
        //    285: getstatic  #101 // java.lang.Void.TYPE:Ljava/lang/Class;
        //    288: aload  6
        //    290: invokevirtual  #136 // java.lang.reflect.Method.getGenericReturnType:()Ljava/lang/reflect/Type;
        //    293: invokevirtual  #120 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    296: ifne  501 (offset +205)
        //    299: ldc  #79 // org.freedesktop.dbus.Tuple
        //    301: aload  6
        //    303: invokevirtual  #140 // java.lang.reflect.Method.getReturnType:()Ljava/lang/Class;
        //    306: invokevirtual  #118 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    309: ifeq  423 (offset +114)
        //    312: aload  6
        //    314: invokevirtual  #136 // java.lang.reflect.Method.getGenericReturnType:()Ljava/lang/reflect/Type;
        //    317: checkcast  #58 // java.lang.reflect.ParameterizedType
        //    320: astore  9
        //    322: aload  9
        //    324: invokeinterface  #195 // java.lang.reflect.ParameterizedType.getActualTypeArguments:()[Ljava/lang/reflect/Type;, count 1
        //    329: astore  10
        //    331: aload  10
        //    333: astore  11
        //    335: aload  11
        //    337: arraylength
        //    338: istore  12
        //    340: iconst_0
        //    341: istore  13
        //    343: iload  13
        //    345: iload  12
        //    347: if_icmpge  420 (offset +73)
        //    350: aload  11
        //    352: iload  13
        //    354: aaload
        //    355: astore  14
        //    357: aload  14
        //    359: ifnull  414 (offset +55)
        //    362: aload  14
        //    364: invokestatic  #158 // org.freedesktop.dbus.Marshalling.getDBusType:(Ljava/lang/reflect/Type;)[Ljava/lang/String;
        //    367: astore  15
        //    369: aload  15
        //    371: arraylength
        //    372: istore  16
        //    374: iconst_0
        //    375: istore  17
        //    377: iload  17
        //    379: iload  16
        //    381: if_icmpge  414 (offset +33)
        //    384: aload  15
        //    386: iload  17
        //    388: aaload
        //    389: astore  18
        //    391: aload_2
        //    392: ldc  #6 // '   <arg type="'
        //    394: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    397: aload  18
        //    399: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    402: ldc  #18 // '" direction="out"/>\n'
        //    404: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    407: pop
        //    408: iinc  17, 1
        //    411: goto  377 (offset -34)
        //    414: iinc  13, 1
        //    417: goto  343 (offset -74)
        //    420: goto  501 (offset +81)
        //    423: ldc  #38 // [Ljava.lang.Object;
        //    425: aload  6
        //    427: invokevirtual  #136 // java.lang.reflect.Method.getGenericReturnType:()Ljava/lang/reflect/Type;
        //    430: invokevirtual  #120 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //    433: ifeq  446 (offset +13)
        //    436: new  #89 // org.freedesktop.dbus.exceptions.DBusException
        //    439: dup
        //    440: ldc  #32 // 'Return type of Object[] cannot be introspected properly'
        //    442: invokespecial  #164 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    445: athrow
        //    446: aload  6
        //    448: invokevirtual  #136 // java.lang.reflect.Method.getGenericReturnType:()Ljava/lang/reflect/Type;
        //    451: invokestatic  #158 // org.freedesktop.dbus.Marshalling.getDBusType:(Ljava/lang/reflect/Type;)[Ljava/lang/String;
        //    454: astore  9
        //    456: aload  9
        //    458: arraylength
        //    459: istore  10
        //    461: iconst_0
        //    462: istore  11
        //    464: iload  11
        //    466: iload  10
        //    468: if_icmpge  501 (offset +33)
        //    471: aload  9
        //    473: iload  11
        //    475: aaload
        //    476: astore  12
        //    478: aload_2
        //    479: ldc  #6 // '   <arg type="'
        //    481: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    484: aload  12
        //    486: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    489: ldc  #18 // '" direction="out"/>\n'
        //    491: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    494: pop
        //    495: iinc  11, 1
        //    498: goto  464 (offset -34)
        //    501: aload_2
        //    502: ldc  #7 // '  </method>\n'
        //    504: invokevirtual  #126 // java.lang.StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    507: pop
        //    508: aload_0
        //    509: getfield  #105 // org.freedesktop.dbus.messages.ExportedObject.methods:Ljava/util/Map;
        //    512: new  #77 // org.freedesktop.dbus.MethodTuple
        //    515: dup
        //    516: aload  7
        //    518: aload  8
        //    520: invokevirtual  #127 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    523: invokespecial  #160 // org.freedesktop.dbus.MethodTuple.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //    526: aload  6
        //    528: invokeinterface  #204 // java.util.Map.putIfAbsent:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    533: pop
        //    534: iinc  5, 1
        //    537: goto  20 (offset -517)
        //    540: aload_2
        //    541: invokevirtual  #127 // java.lang.StringBuilder.toString:()Ljava/lang/String;
        //    544: areturn
    }

  protected String generateSignalsXml(Class arg0) {
        StringBuilder var2 = new StringBuilder();
        Class[] var3 = arg0.getDeclaredClasses();
        int var4 = var3.length;
        int var5 = 0;
        String var7;
        while (true) {
            if (var5 >= var4) {
                return var2.toString();
            }
            Object var6 = var3[var5];
            if (!DBusSignal.class.isAssignableFrom(((Class) var6))) {
                ++var5;
                continue;
            } else {
                var7 = DBusNamingUtil.getSignalName(((Class) var6));
                if (var6.isAnnotationPresent(DBusMemberName.class)) {
                    DBusSignal.addSignalMap(var6.getSimpleName(), var7);
                }
                if (var7.length() > 255) {
                    break;
                }
                var2.append("  <signal name=\"").append(var7).append("\">\n");
                Object var8 = var6.getConstructors()[0];
                Type[] var9 = var8.getGenericParameterTypes();
                int var10 = 1;
                while (var10 < var9.length) {
                    String[] var11 = Marshalling.getDBusType(((Type) var9[var10]));
                    int var12 = var11.length;
                    int var13 = 0;
                    while (var13 < var12) {
                        Object var14 = var11[var13];
                        var2.append("   <arg type=\"").append(((String) var14)).append("\" direction=\"out\" />\n");
                        ++var13;
                        continue;
                    }
                    ++var10;
                    continue;
                }
                var2.append(generateAnnotationsXml(((AnnotatedElement) var6)));
                var2.append("  </signal>\n");
                ++var5;
                continue;
            }
        }
        throw new DBusException("Introspected signal name exceeds 255 characters. Cannot export objects with signals of type " + var7);
    }

  protected Set getDBusInterfaces(Class arg0) {
        Objects.requireNonNull(arg0, "inputClazz must not be null");
        LinkedHashSet var2 = new LinkedHashSet();
        LinkedHashSet var3 = new LinkedHashSet();
        LinkedList var4 = new LinkedList();
        var4.add(arg0);
        while (!var4.isEmpty()) {
            Class var5 = ((Class) var4.poll());
            var3.add(var5);
            Class var6 = var5.getSuperclass();
            if (var6 != null) {
                if (DBusInterface.class.isAssignableFrom(var6)) {
                    var4.add(var6);
                }
            }
            List var7 = Arrays.asList(var5.getInterfaces());
            if (var7.contains(DBusInterface.class)) {
                var2.add(var5);
            }
            Objects.requireNonNull(DBusInterface.class);
            Objects.requireNonNull(var4);
            var7.stream().filter(lp0 -> DBusInterface.class.isAssignableFrom(((Class) lp0))).filter(lp0 -> lambda$getDBusInterfaces$1(((Class) lp0))).filter(lp0 -> lambda$getDBusInterfaces$2(var3, ((Class) lp0))).forEach(lp0 -> var4.add(lp0));
            continue;
        }
        return var2;
    }

  private String generateIntrospectionXml(Set arg0) {
        StringBuilder var2 = new StringBuilder();
        Iterator var3 = arg0.iterator();
        while (true) {
            if (!var3.hasNext()) {
                return var2.toString();
            }
            Class var4 = ((Class) var3.next());
            String var5 = DBusNamingUtil.getInterfaceName(var4);
            if (var5.equals(var4.getSimpleName())) {
                break;
            }
            if (var5.length() <= 255) {
                if (var4.isAnnotationPresent(DBusInterfaceName.class)) {
                    DBusSignal.addInterfaceMap(var4.getName(), var5);
                }
            } else {
                throw new DBusException("Introspected interface name exceeds 255 characters. Cannot export objects of type " + var5);
            }
            var2.append(" <interface name=\"").append(var5).append("\">\n");
            var2.append(generateAnnotationsXml(var4));
            var2.append(generateMethodsXml(var4));
            var2.append(generatePropertiesXml(var4));
            var2.append(generateSignalsXml(var4));
            var2.append(" </interface>\n");
            continue;
        }
        throw new DBusException("DBusInterfaces cannot be declared outside a package");
    }

  public Map getMethods() {
        return methods;
    }

  public Map getPropertyMethods() {
        return propertyMethods;
    }

  public Reference getObject() {
        return object;
    }

  public String getIntrospectiondata() {
        return introspectionData;
    }

  public String toString() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #121 // java.lang.Object.getClass:()Ljava/lang/Class;
        //      4: invokevirtual  #115 // java.lang.Class.getSimpleName:()Ljava/lang/String;
        //      7: aload_0
        //      8: getfield  #105 // org.freedesktop.dbus.messages.ExportedObject.methods:Ljava/util/Map;
        //     11: invokeinterface  #205 // java.util.Map.size:()I, count 1
        //     16: aload_0
        //     17: getfield  #107 // org.freedesktop.dbus.messages.ExportedObject.propertyMethods:Ljava/util/Map;
        //     20: invokeinterface  #205 // java.util.Map.size:()I, count 1
        //     25: aload_0
        //     26: getfield  #106 // org.freedesktop.dbus.messages.ExportedObject.object:Ljava/lang/ref/Reference;
        //     29: invokevirtual  #130 // java.lang.ref.Reference.get:()Ljava/lang/Object;
        //     32: ifnull  45 (offset +13)
        //     35: aload_0
        //     36: getfield  #106 // org.freedesktop.dbus.messages.ExportedObject.object:Ljava/lang/ref/Reference;
        //     39: invokestatic  #153 // java.util.Objects.toString:(Ljava/lang/Object;)Ljava/lang/String;
        //     42: goto  47 (offset +5)
        //     45: ldc  #22 // '<no object referenced>'
        //     47: invokedynamic  #230 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;IILjava/lang/String;)Ljava/lang/String;
        //     52: areturn
    }

  public static boolean isExcluded(Method arg0) {
        return arg0 == null ? 1 : !Modifier.isPublic(arg0.getModifiers()) ? 1 : arg0.isSynthetic() ? 1 : arg0.isDefault() ? 1 : arg0.isBridge() ? 1 : arg0.getAnnotation(DBusIgnore.class) != null ? 1 : arg0.getAnnotation(DBusBoundProperty.class) != null ? 1 : !arg0.getName().equals("getObjectPath") ? 0 : !arg0.getReturnType().equals(String.class) ? 0 : arg0.getParameterCount() == 0;
    }

  private static boolean lambda$getDBusInterfaces$2(Set arg0, Class arg1) {
        return !arg0.contains(arg1);
    }

  private static boolean lambda$getDBusInterfaces$1(Class arg0) {
        return arg0 != DBusInterface.class;
    }

  private static DBusException lambda$generatePropertyXml$0(String arg0) {
        return new DBusException("Could not read TypeRef type for property '" + arg0 + "'");
    }

}