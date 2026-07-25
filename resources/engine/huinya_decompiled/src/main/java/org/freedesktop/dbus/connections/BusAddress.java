// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.BusAddress
package org.freedesktop.dbus.connections;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusAddress {

    // ---- поля ----
  private static final Logger LOGGER;
  private String type;
  private final Map parameters;

    static {
        LOGGER = LoggerFactory.getLogger(BusAddress.class);
    }

  protected BusAddress(BusAddress arg0) { // было: <init>
        super();
        parameters = new LinkedHashMap();
        if (arg0 != null) {
            parameters.putAll(arg0.parameters);
            type = arg0.type;
        }
    }

  public static BusAddress of(BusAddress arg0) {
        return new BusAddress(arg0);
    }

  public static BusAddress of(String arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: ifnull  11 (offset +10)
        //      4: aload_0
        //      5: invokevirtual  #38 // java.lang.String.isEmpty:()Z
        //      8: ifeq  21 (offset +13)
        //     11: new  #29 // org.freedesktop.dbus.exceptions.InvalidBusAddressException
        //     14: dup
        //     15: ldc  #6 // 'Bus address is blank'
        //     17: invokespecial  #51 // org.freedesktop.dbus.exceptions.InvalidBusAddressException.<init>:(Ljava/lang/String;)V
        //     20: athrow
        //     21: new  #28 // org.freedesktop.dbus.connections.BusAddress
        //     24: dup
        //     25: aconst_null
        //     26: checkcast  #28 // org.freedesktop.dbus.connections.BusAddress
        //     29: invokespecial  #47 // org.freedesktop.dbus.connections.BusAddress.<init>:(Lorg/freedesktop/dbus/connections/BusAddress;)V
        //     32: astore_1
        //     33: getstatic  #33 // org.freedesktop.dbus.connections.BusAddress.LOGGER:Lorg/slf4j/Logger;
        //     36: ldc  #8 // 'Parsing bus address: {}'
        //     38: aload_0
        //     39: invokeinterface  #65 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //     44: aload_0
        //     45: ldc  #4 // ':'
        //     47: iconst_2
        //     48: invokevirtual  #40 // java.lang.String.split:(Ljava/lang/String;I)[Ljava/lang/String;
        //     51: astore_2
        //     52: aload_2
        //     53: arraylength
        //     54: iconst_2
        //     55: if_icmpge  72 (offset +17)
        //     58: new  #29 // org.freedesktop.dbus.exceptions.InvalidBusAddressException
        //     61: dup
        //     62: aload_0
        //     63: invokedynamic  #66 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //     68: invokespecial  #51 // org.freedesktop.dbus.exceptions.InvalidBusAddressException.<init>:(Ljava/lang/String;)V
        //     71: athrow
        //     72: aload_1
        //     73: aload_2
        //     74: iconst_0
        //     75: aaload
        //     76: ifnull  91 (offset +15)
        //     79: aload_2
        //     80: iconst_0
        //     81: aaload
        //     82: getstatic  #32 // java.util.Locale.US:Ljava/util/Locale;
        //     85: invokevirtual  #41 // java.lang.String.toLowerCase:(Ljava/util/Locale;)Ljava/lang/String;
        //     88: goto  92 (offset +4)
        //     91: aconst_null
        //     92: putfield  #35 // org.freedesktop.dbus.connections.BusAddress.type:Ljava/lang/String;
        //     95: aload_1
        //     96: getfield  #35 // org.freedesktop.dbus.connections.BusAddress.type:Ljava/lang/String;
        //     99: ifnonnull  118 (offset +19)
        //    102: new  #29 // org.freedesktop.dbus.exceptions.InvalidBusAddressException
        //    105: dup
        //    106: aload_2
        //    107: iconst_0
        //    108: aaload
        //    109: invokedynamic  #67 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //    114: invokespecial  #51 // org.freedesktop.dbus.exceptions.InvalidBusAddressException.<init>:(Ljava/lang/String;)V
        //    117: athrow
        //    118: getstatic  #33 // org.freedesktop.dbus.connections.BusAddress.LOGGER:Lorg/slf4j/Logger;
        //    121: ldc  #10 // 'Transport type: {}'
        //    123: aload_1
        //    124: getfield  #35 // org.freedesktop.dbus.connections.BusAddress.type:Ljava/lang/String;
        //    127: invokeinterface  #65 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    132: aload_2
        //    133: iconst_1
        //    134: aaload
        //    135: ldc  #3 // ','
        //    137: invokevirtual  #39 // java.lang.String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    140: astore_3
        //    141: aload_3
        //    142: astore  4
        //    144: aload  4
        //    146: arraylength
        //    147: istore  5
        //    149: iconst_0
        //    150: istore  6
        //    152: iload  6
        //    154: iload  5
        //    156: if_icmpge  195 (offset +39)
        //    159: aload  4
        //    161: iload  6
        //    163: aaload
        //    164: astore  7
        //    166: aload  7
        //    168: ldc  #5 // '='
        //    170: iconst_2
        //    171: invokevirtual  #40 // java.lang.String.split:(Ljava/lang/String;I)[Ljava/lang/String;
        //    174: astore  8
        //    176: aload_1
        //    177: aload  8
        //    179: iconst_0
        //    180: aaload
        //    181: aload  8
        //    183: iconst_1
        //    184: aaload
        //    185: invokevirtual  #48 // org.freedesktop.dbus.connections.BusAddress.addParameter:(Ljava/lang/String;Ljava/lang/String;)Lorg/freedesktop/dbus/connections/BusAddress;
        //    188: pop
        //    189: iinc  6, 1
        //    192: goto  152 (offset -40)
        //    195: getstatic  #33 // org.freedesktop.dbus.connections.BusAddress.LOGGER:Lorg/slf4j/Logger;
        //    198: ldc  #9 // 'Transport options: {}'
        //    200: aload_1
        //    201: getfield  #34 // org.freedesktop.dbus.connections.BusAddress.parameters:Ljava/util/Map;
        //    204: invokeinterface  #65 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    209: aload_1
        //    210: areturn
    }

  public String getType() {
        return type;
    }

  public String getBusType() {
        String __stk1;
        __stk1 = type != null ? type.toUpperCase(Locale.US) : null;
        return ((String) __stk1);
    }

  public boolean isBusType(String arg0) {
        return type == null ? 0 : type.equalsIgnoreCase(arg0);
    }

  public boolean isListeningSocket() {
        return parameters.containsKey("listen");
    }

  public String getGuid() {
        return ((String) parameters.get("guid"));
    }

  public final String toString() {
        return type + ":" + ((String) parameters.entrySet().stream().map(lp0 -> lambda$toString$0(((Entry) lp0))).collect(Collectors.joining(",")));
    }

  public boolean isServer() {
        return isListeningSocket();
    }

  public BusAddress addParameter(String arg0, String arg1) {
        parameters.put(arg0, arg1);
        return this;
    }

  public BusAddress removeParameter(String arg0) {
        parameters.remove(arg0);
        return this;
    }

  public boolean hasParameter(String arg0) {
        return parameters.containsKey(arg0);
    }

  public String getParameterValue(String arg0) {
        return ((String) parameters.get(arg0));
    }

  public String getParameterValue(String arg0, String arg1) {
        return ((String) parameters.getOrDefault(arg0, arg1));
    }

  public BusAddress getListenerAddress() {
        if (isListeningSocket()) {
            return this;
        } else {
            return new BusAddress(this).addParameter("listen", "true");
        }
    }

  private static String lambda$toString$0(Entry arg0) {
        return ((String) arg0.getKey()) + "=" + ((String) arg0.getValue());
    }

}