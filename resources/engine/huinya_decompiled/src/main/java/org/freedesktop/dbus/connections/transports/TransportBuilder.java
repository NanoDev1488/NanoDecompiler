// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.transports.TransportBuilder
package org.freedesktop.dbus.connections.transports;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.config.TransportConfig;
import org.freedesktop.dbus.connections.config.TransportConfigBuilder;
import org.freedesktop.dbus.connections.transports.AbstractTransport;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.exceptions.TransportRegistrationException;
import org.freedesktop.dbus.spi.transport.ITransportProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TransportBuilder {

    // ---- поля ----
  private static final Logger LOGGER;
  private static final Map PROVIDERS;
  private TransportConfigBuilder transportConfigBuilder;

    static {
        LOGGER = LoggerFactory.getLogger(TransportBuilder.class);
        PROVIDERS = getTransportProvider();
    }

  private TransportBuilder(TransportConfig arg0) { // было: <init>
        super();
        transportConfigBuilder = new TransportConfigBuilder(() -> lambda$new$0());
        if (arg0 != null) {
            transportConfigBuilder.withConfig(arg0);
        }
    }

  static Map getTransportProvider() {
        ConcurrentHashMap var0 = new ConcurrentHashMap();
        try {
            ServiceLoader var1 = ServiceLoader.load(ITransportProvider.class, TransportBuilder.class.getClassLoader());
            Iterator var2 = var1.iterator();
            ITransportProvider var3;
            String var4;
            while (true) {
                if (!var2.hasNext()) {
                    if (var0.isEmpty()) {
                        throw new TransportRegistrationException("No dbus-java-transport found in classpath, please add a transport module");
                    }
                } else {
                    var3 = ((ITransportProvider) var2.next());
                    var4 = var3.getSupportedBusType();
                    if (var4 != null) {
                        var4 = var4.toUpperCase(Locale.US);
                        LOGGER.debug("Found provider '{}' named '{}' providing bustype '{}'", new Object[]{var3.getClass().getSimpleName(), var3.getTransportName(), var4});
                        if (var0.containsKey(var4)) {
                            break;
                        }
                        var0.put(var4, var3);
                        continue;
                    } else {
                        LOGGER.warn("Transport {} is invalid: No bustype configured", var3.getClass());
                        continue;
                    }
                }
                return var0;
            }
            throw new TransportRegistrationException("Found transport " + (((ITransportProvider) var0.get(var4))).getClass().getName() + " and " + var3.getClass().getName() + " both providing transport for socket type " + var4 + ", please only add one of them to classpath.");
        } catch (ServiceConfigurationError e1) {
            Throwable var1 = e1;
            LOGGER.error("Could not initialize service provider.", var1);
        }
        return var0;
    }

  public static TransportBuilder create(String arg0) {
        TransportConfig var1 = new TransportConfig();
        var1.setBusAddress(BusAddress.of(arg0));
        return new TransportBuilder(var1);
    }

  public static TransportBuilder create(TransportConfig arg0) {
        return new TransportBuilder(arg0);
    }

  public static TransportBuilder create() {
        return new TransportBuilder(null);
    }

  public static TransportBuilder create(BusAddress arg0) {
        Objects.requireNonNull(arg0, "BusAddress required");
        return new TransportBuilder(new TransportConfig(arg0));
    }

  public static TransportBuilder createWithDynamicSession(String arg0) {
        String var1 = createDynamicSession(arg0, false);
        if (var1 != null) {
            return create(var1);
        } else {
            throw new DBusException("Could not create dynamic session for transport type '" + arg0 + "'");
        }
    }

  public TransportConfigBuilder configure() {
        return transportConfigBuilder;
    }

  public AbstractTransport build() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #104 // org.freedesktop.dbus.connections.transports.TransportBuilder.getAddress:()Lorg/freedesktop/dbus/connections/BusAddress;
        //      4: astore_1
        //      5: aload_0
        //      6: getfield  #57 // org.freedesktop.dbus.connections.transports.TransportBuilder.transportConfigBuilder:Lorg/freedesktop/dbus/connections/config/TransportConfigBuilder;
        //      9: invokevirtual  #94 // org.freedesktop.dbus.connections.config.TransportConfigBuilder.build:()Lorg/freedesktop/dbus/connections/config/TransportConfig;
        //     12: astore_2
        //     13: aload_1
        //     14: ifnonnull  27 (offset +13)
        //     17: new  #46 // org.freedesktop.dbus.exceptions.DBusException
        //     20: dup
        //     21: ldc  #14 // 'Transport requires a BusAddress, use withBusAddress() to configure before building'
        //     23: invokespecial  #107 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //     26: athrow
        //     27: aload_2
        //     28: invokevirtual  #88 // org.freedesktop.dbus.connections.config.TransportConfig.getSaslConfig:()Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //     31: invokevirtual  #79 // org.freedesktop.dbus.connections.config.SaslConfig.getAuthMode:()I
        //     34: istore_3
        //     35: aconst_null
        //     36: astore  4
        //     38: getstatic  #56 // org.freedesktop.dbus.connections.transports.TransportBuilder.PROVIDERS:Ljava/util/Map;
        //     41: aload_2
        //     42: invokevirtual  #83 // org.freedesktop.dbus.connections.config.TransportConfig.getBusAddress:()Lorg/freedesktop/dbus/connections/BusAddress;
        //     45: invokevirtual  #75 // org.freedesktop.dbus.connections.BusAddress.getBusType:()Ljava/lang/String;
        //     48: invokeinterface  #113 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     53: checkcast  #49 // org.freedesktop.dbus.spi.transport.ITransportProvider
        //     56: astore  5
        //     58: aload  5
        //     60: ifnonnull  83 (offset +23)
        //     63: new  #46 // org.freedesktop.dbus.exceptions.DBusException
        //     66: dup
        //     67: aload_2
        //     68: invokevirtual  #83 // org.freedesktop.dbus.connections.config.TransportConfig.getBusAddress:()Lorg/freedesktop/dbus/connections/BusAddress;
        //     71: invokevirtual  #75 // org.freedesktop.dbus.connections.BusAddress.getBusType:()Ljava/lang/String;
        //     74: invokedynamic  #131 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //     79: invokespecial  #107 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //     82: athrow
        //     83: getstatic  #55 // org.freedesktop.dbus.connections.transports.TransportBuilder.LOGGER:Lorg/slf4j/Logger;
        //     86: ldc  #17 // 'Using transport {} for address {}'
        //     88: aload  5
        //     90: invokeinterface  #122 // org.freedesktop.dbus.spi.transport.ITransportProvider.getTransportName:()Ljava/lang/String;, count 1
        //     95: aload_2
        //     96: invokevirtual  #83 // org.freedesktop.dbus.connections.config.TransportConfig.getBusAddress:()Lorg/freedesktop/dbus/connections/BusAddress;
        //     99: invokeinterface  #126 // org.slf4j.Logger.info:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    104: aload  5
        //    106: aload_1
        //    107: aload_2
        //    108: invokeinterface  #119 // org.freedesktop.dbus.spi.transport.ITransportProvider.createTransport:(Lorg/freedesktop/dbus/connections/BusAddress;Lorg/freedesktop/dbus/connections/config/TransportConfig;)Lorg/freedesktop/dbus/connections/transports/AbstractTransport;, count 3
        //    113: astore  4
        //    115: aload  4
        //    117: ldc  #13 // 'Transport required'
        //    119: invokestatic  #71 // java.util.Objects.requireNonNull:(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;
        //    122: pop
        //    123: iload_3
        //    124: ifle  147 (offset +23)
        //    127: aload_2
        //    128: invokevirtual  #88 // org.freedesktop.dbus.connections.config.TransportConfig.getSaslConfig:()Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //    131: invokevirtual  #79 // org.freedesktop.dbus.connections.config.SaslConfig.getAuthMode:()I
        //    134: iload_3
        //    135: if_icmpeq  147 (offset +12)
        //    138: aload  4
        //    140: invokevirtual  #98 // org.freedesktop.dbus.connections.transports.AbstractTransport.getSaslConfig:()Lorg/freedesktop/dbus/connections/config/SaslConfig;
        //    143: iload_3
        //    144: invokevirtual  #80 // org.freedesktop.dbus.connections.config.SaslConfig.setAuthMode:(I)V
        //    147: goto  164 (offset +17)
        //    150: astore  6
        //    152: getstatic  #55 // org.freedesktop.dbus.connections.transports.TransportBuilder.LOGGER:Lorg/slf4j/Logger;
        //    155: ldc  #7 // 'Could not initialize transport'
        //    157: aload  6
        //    159: invokeinterface  #125 // org.slf4j.Logger.error:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    164: aload  4
        //    166: ifnonnull  190 (offset +24)
        //    169: new  #46 // org.freedesktop.dbus.exceptions.DBusException
        //    172: dup
        //    173: aload_1
        //    174: invokevirtual  #76 // org.freedesktop.dbus.connections.BusAddress.getType:()Ljava/lang/String;
        //    177: aload_1
        //    178: invokevirtual  #75 // org.freedesktop.dbus.connections.BusAddress.getBusType:()Ljava/lang/String;
        //    181: invokedynamic  #132 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    186: invokespecial  #107 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    189: athrow
        //    190: aload_1
        //    191: invokevirtual  #77 // org.freedesktop.dbus.connections.BusAddress.isListeningSocket:()Z
        //    194: ifeq  229 (offset +35)
        //    197: aload_1
        //    198: instanceof  #43 // org.freedesktop.dbus.connections.transports.IFileBasedBusAddress
        //    201: ifeq  229 (offset +28)
        //    204: aload_1
        //    205: checkcast  #43 // org.freedesktop.dbus.connections.transports.IFileBasedBusAddress
        //    208: astore  6
        //    210: aload  6
        //    212: aload_2
        //    213: invokevirtual  #85 // org.freedesktop.dbus.connections.config.TransportConfig.getFileOwner:()Ljava/lang/String;
        //    216: aload_2
        //    217: invokevirtual  #84 // org.freedesktop.dbus.connections.config.TransportConfig.getFileGroup:()Ljava/lang/String;
        //    220: aload_2
        //    221: invokevirtual  #86 // org.freedesktop.dbus.connections.config.TransportConfig.getFileUnixPermissions:()Ljava/util/Set;
        //    224: invokeinterface  #117 // org.freedesktop.dbus.connections.transports.IFileBasedBusAddress.updatePermissions:(Ljava/lang/String;Ljava/lang/String;Ljava/util/Set;)V, count 4
        //    229: aload  4
        //    231: aload_2
        //    232: invokevirtual  #87 // org.freedesktop.dbus.connections.config.TransportConfig.getPreConnectCallback:()Ljava/util/function/Consumer;
        //    235: invokevirtual  #99 // org.freedesktop.dbus.connections.transports.AbstractTransport.setPreConnectCallback:(Ljava/util/function/Consumer;)V
        //    238: aload_2
        //    239: invokevirtual  #90 // org.freedesktop.dbus.connections.config.TransportConfig.isAutoConnect:()Z
        //    242: ifeq  409 (offset +167)
        //    245: aload_2
        //    246: invokevirtual  #91 // org.freedesktop.dbus.connections.config.TransportConfig.isListening:()Z
        //    249: ifne  409 (offset +160)
        //    252: aconst_null
        //    253: astore  6
        //    255: sipush  500
        //    258: aload_2
        //    259: invokevirtual  #89 // org.freedesktop.dbus.connections.config.TransportConfig.getTimeout:()I
        //    262: invokestatic  #62 // java.lang.Math.max:(II)I
        //    265: sipush  500
        //    268: idiv
        //    269: istore  7
        //    271: iconst_0
        //    272: istore  8
        //    274: iinc  8, 1
        //    277: aload  4
        //    279: invokevirtual  #97 // org.freedesktop.dbus.connections.transports.AbstractTransport.connect:()Ljava/nio/channels/SocketChannel;
        //    282: astore  6
        //    284: goto  367 (offset +83)
        //    287: astore  9
        //    289: getstatic  #55 // org.freedesktop.dbus.connections.transports.TransportBuilder.LOGGER:Lorg/slf4j/Logger;
        //    292: ldc  #4 // 'Connection to {} failed, reconnect attempt {} of {}'
        //    294: iconst_3
        //    295: anewarray  #23 // java.lang.Object
        //    298: dup
        //    299: iconst_0
        //    300: aload_0
        //    301: invokevirtual  #104 // org.freedesktop.dbus.connections.transports.TransportBuilder.getAddress:()Lorg/freedesktop/dbus/connections/BusAddress;
        //    304: aastore
        //    305: dup
        //    306: iconst_1
        //    307: iload  8
        //    309: invokestatic  #61 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    312: aastore
        //    313: dup
        //    314: iconst_2
        //    315: iload  7
        //    317: invokestatic  #61 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    320: aastore
        //    321: invokeinterface  #124 // org.slf4j.Logger.debug:(Ljava/lang/String;[Ljava/lang/Object;)V, count 3
        //    326: iload  8
        //    328: iload  7
        //    330: if_icmplt  336 (offset +6)
        //    333: aload  9
        //    335: athrow
        //    336: ldc2_w  #52 // 500L
        //    339: invokestatic  #67 // java.lang.Thread.sleep:(J)V
        //    342: goto  367 (offset +25)
        //    345: astore  10
        //    347: getstatic  #55 // org.freedesktop.dbus.connections.transports.TransportBuilder.LOGGER:Lorg/slf4j/Logger;
        //    350: ldc  #10 // 'Interrupted while waiting for connection retry for address {}'
        //    352: aload_0
        //    353: invokevirtual  #104 // org.freedesktop.dbus.connections.transports.TransportBuilder.getAddress:()Lorg/freedesktop/dbus/connections/BusAddress;
        //    356: invokeinterface  #123 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    361: invokestatic  #65 // java.lang.Thread.currentThread:()Ljava/lang/Thread;
        //    364: invokevirtual  #66 // java.lang.Thread.interrupt:()V
        //    367: aload  6
        //    369: ifnull  274 (offset -95)
        //    372: getstatic  #55 // org.freedesktop.dbus.connections.transports.TransportBuilder.LOGGER:Lorg/slf4j/Logger;
        //    375: ldc  #3 // 'Connection to {} established after {} of {} attempts'
        //    377: iconst_3
        //    378: anewarray  #23 // java.lang.Object
        //    381: dup
        //    382: iconst_0
        //    383: aload_0
        //    384: invokevirtual  #104 // org.freedesktop.dbus.connections.transports.TransportBuilder.getAddress:()Lorg/freedesktop/dbus/connections/BusAddress;
        //    387: aastore
        //    388: dup
        //    389: iconst_1
        //    390: iload  8
        //    392: invokestatic  #61 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    395: aastore
        //    396: dup
        //    397: iconst_2
        //    398: iload  7
        //    400: invokestatic  #61 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    403: aastore
        //    404: invokeinterface  #124 // org.slf4j.Logger.debug:(Ljava/lang/String;[Ljava/lang/Object;)V, count 3
        //    409: aload  4
        //    411: areturn
        //       Exception table:
        //         from 104 to 147 target 150 type org.freedesktop.dbus.exceptions.TransportConfigurationException
        //         from 274 to 284 target 287 type java.io.IOException
        //         from 336 to 342 target 345 type java.lang.InterruptedException
    }

  public BusAddress getAddress() {
        return configure().getBusAddress();
    }

  public static List getRegisteredBusTypes() {
        return new ArrayList(PROVIDERS.keySet());
    }

  public static String createDynamicSession(String arg0, boolean arg1) {
        Objects.requireNonNull(arg0, "Bustype required");
        ITransportProvider var2 = ((ITransportProvider) PROVIDERS.get(arg0.toUpperCase(Locale.US)));
        if (var2 == null) {
            return null;
        } else {
            return var2.createDynamicSessionAddress(arg1);
        }
    }

  private TransportBuilder lambda$new$0() {
        return this;
    }

}