// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.bin.EmbeddedDBusDaemon
package org.freedesktop.dbus.bin;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import org.freedesktop.dbus.bin.DBusDaemon;
import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.transports.AbstractTransport;
import org.freedesktop.dbus.connections.transports.TransportBuilder_SaslAuthMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmbeddedDBusDaemon implements Closeable {

    // ---- поля ----
  private static final Logger LOGGER;
  private final BusAddress address;
  private DBusDaemon daemon;
  private final AtomicBoolean closed;
  private TransportBuilder_SaslAuthMode saslAuthMode;
  private String unixSocketFileOwner;
  private String unixSocketFileGroup;
  private PosixFilePermission[] unixSocketFilePermissions;
  private Consumer connectCallback;
  private Consumer bindCallback;
  private CountDownLatch startupLatch;

    static {
        LOGGER = LoggerFactory.getLogger(EmbeddedDBusDaemon.class);
    }

  public EmbeddedDBusDaemon(BusAddress arg0) { // было: <init>
        super();
        closed = new AtomicBoolean(false);
        startupLatch = new CountDownLatch(1);
        address = BusAddress.of(((BusAddress) Objects.requireNonNull(arg0, "Address required")));
    }

  public EmbeddedDBusDaemon(String arg0) { // было: <init>
        this(BusAddress.of(arg0));
    }

  public synchronized void close() {
        closed.set(true);
        startupLatch = new CountDownLatch(1);
        if (daemon != null) {
            daemon.close();
            try {
                daemon.join(5000L);
            } catch (InterruptedException var1) {
                LOGGER.debug("Interrupted while waiting for daemon thread to terminate");
                Thread.currentThread().interrupt();
            }
            daemon = null;
        }
    }

  public void startInForeground() {
        try {
            closed.set(false);
            startListening();
        } catch (IOException var1) {
            if (closed.get()) {
                return;
            } else {
                throw new RuntimeException(var1);
            }
        }
    }

  public void startInBackground() {
        Thread var1 = new Thread(() -> startInForeground());
        String var2 = address.toString().replaceAll("^([^,]+),.+", "$1");
        var1.setName("EmbeddedDBusDaemon-" + var2);
        var1.setDaemon(true);
        var1.setUncaughtExceptionHandler((lp0, lp1) -> lambda$startInBackground$0(lp0, lp1));
        var1.start();
    }

  public void startInBackgroundAndWait(long arg0) {
        startInBackground();
        try {
            if (!startupLatch.await(arg0, TimeUnit.MILLISECONDS)) {
                throw new IllegalStateException("Daemon not started after " + arg0 + " milliseconds");
            }
        } catch (InterruptedException var3) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Startup of daemon interrupted");
        }
    }

  public void startInBackgroundAndWait() {
        startInBackground();
        try {
            startupLatch.await();
        } catch (InterruptedException var1) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for daemon to start");
        }
    }

  public synchronized boolean isRunning() {
        return startupLatch.getCount() != 0L ? 0 : daemon == null ? 0 : daemon.isRunning();
    }

  public TransportBuilder_SaslAuthMode getSaslAuthMode() {
        return saslAuthMode;
    }

  public void setSaslAuthMode(TransportBuilder_SaslAuthMode arg0) {
        saslAuthMode = arg0;
    }

  public void setUnixSocketOwner(String arg0) {
        unixSocketFileOwner = arg0;
    }

  public void setUnixSocketGroup(String arg0) {
        unixSocketFileGroup = arg0;
    }

  public void setUnixSocketPermissions(PosixFilePermission[] arg0) {
        unixSocketFilePermissions = arg0;
    }

  public Consumer getConnectCallback() {
        return connectCallback;
    }

  public void setConnectCallback(Consumer arg0) {
        connectCallback = arg0;
    }

  public Consumer getBindCallback() {
        return bindCallback;
    }

  public void setBindCallback(Consumer arg0) {
        bindCallback = arg0;
    }

  private synchronized void setDaemonAndStart(AbstractTransport arg0) {
        daemon = new DBusDaemon(arg0);
        daemon.start();
    }

  private void startListening() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: invokestatic  #121 // org.freedesktop.dbus.connections.transports.TransportBuilder.getRegisteredBusTypes:()Ljava/util/List;
        //      3: aload_0
        //      4: getfield  #53 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.address:Lorg/freedesktop/dbus/connections/BusAddress;
        //      7: invokevirtual  #101 // org.freedesktop.dbus.connections.BusAddress.getBusType:()Ljava/lang/String;
        //     10: invokeinterface  #123 // java.util.List.contains:(Ljava/lang/Object;)Z, count 2
        //     15: ifne  38 (offset +23)
        //     18: new  #17 // java.lang.IllegalArgumentException
        //     21: dup
        //     22: aload_0
        //     23: getfield  #53 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.address:Lorg/freedesktop/dbus/connections/BusAddress;
        //     26: invokevirtual  #102 // org.freedesktop.dbus.connections.BusAddress.getType:()Ljava/lang/String;
        //     29: invokedynamic  #133 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //     34: invokespecial  #63 // java.lang.IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //     37: athrow
        //     38: getstatic  #52 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //     41: ldc  #2 // 'About to initialize transport on: {}'
        //     43: aload_0
        //     44: getfield  #53 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.address:Lorg/freedesktop/dbus/connections/BusAddress;
        //     47: invokeinterface  #126 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //     52: aload_0
        //     53: getfield  #53 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.address:Lorg/freedesktop/dbus/connections/BusAddress;
        //     56: invokestatic  #120 // org.freedesktop.dbus.connections.transports.TransportBuilder.create:(Lorg/freedesktop/dbus/connections/BusAddress;)Lorg/freedesktop/dbus/connections/transports/TransportBuilder;
        //     59: invokevirtual  #119 // org.freedesktop.dbus.connections.transports.TransportBuilder.configure:()Lorg/freedesktop/dbus/connections/config/TransportConfigBuilder;
        //     62: aload_0
        //     63: getfield  #61 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.unixSocketFileOwner:Ljava/lang/String;
        //     66: invokevirtual  #114 // org.freedesktop.dbus.connections.config.TransportConfigBuilder.withUnixSocketFileOwner:(Ljava/lang/String;)Lorg/freedesktop/dbus/connections/config/TransportConfigBuilder;
        //     69: aload_0
        //     70: getfield  #60 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.unixSocketFileGroup:Ljava/lang/String;
        //     73: invokevirtual  #113 // org.freedesktop.dbus.connections.config.TransportConfigBuilder.withUnixSocketFileGroup:(Ljava/lang/String;)Lorg/freedesktop/dbus/connections/config/TransportConfigBuilder;
        //     76: aload_0
        //     77: getfield  #62 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.unixSocketFilePermissions:[Ljava/nio/file/attribute/PosixFilePermission;
        //     80: invokevirtual  #115 // org.freedesktop.dbus.connections.config.TransportConfigBuilder.withUnixSocketFilePermissions:([Ljava/nio/file/attribute/PosixFilePermission;)Lorg/freedesktop/dbus/connections/config/TransportConfigBuilder;
        //     83: aload_0
        //     84: getfield  #56 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.connectCallback:Ljava/util/function/Consumer;
        //     87: invokevirtual  #112 // org.freedesktop.dbus.connections.config.TransportConfigBuilder.withPreConnectCallback:(Ljava/util/function/Consumer;)Lorg/freedesktop/dbus/connections/config/TransportConfigBuilder;
        //     90: aload_0
        //     91: invokedynamic  #134 // invokedynamic accept:(Lorg/freedesktop/dbus/bin/EmbeddedDBusDaemon;)Ljava/util/function/Consumer;
        //     96: invokevirtual  #110 // org.freedesktop.dbus.connections.config.TransportConfigBuilder.withAfterBindCallback:(Ljava/util/function/Consumer;)Lorg/freedesktop/dbus/connections/config/TransportConfigBuilder;
        //     99: iconst_0
        //    100: invokevirtual  #111 // org.freedesktop.dbus.connections.config.TransportConfigBuilder.withAutoConnect:(Z)Lorg/freedesktop/dbus/connections/config/TransportConfigBuilder;
        //    103: invokevirtual  #109 // org.freedesktop.dbus.connections.config.TransportConfigBuilder.configureSasl:()Lorg/freedesktop/dbus/connections/config/SaslConfigBuilder;
        //    106: aload_0
        //    107: invokevirtual  #94 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.getSaslAuthMode:()Lorg/freedesktop/dbus/connections/transports/TransportBuilder$SaslAuthMode;
        //    110: invokevirtual  #107 // org.freedesktop.dbus.connections.config.SaslConfigBuilder.withAuthMode:(Lorg/freedesktop/dbus/connections/transports/TransportBuilder$SaslAuthMode;)Lorg/freedesktop/dbus/connections/config/SaslConfigBuilder;
        //    113: invokevirtual  #106 // org.freedesktop.dbus.connections.config.SaslConfigBuilder.back:()Lorg/freedesktop/dbus/connections/config/TransportConfigBuilder;
        //    116: invokevirtual  #108 // org.freedesktop.dbus.connections.config.TransportConfigBuilder.back:()Ljava/lang/Object;
        //    119: checkcast  #42 // org.freedesktop.dbus.connections.transports.TransportBuilder
        //    122: invokevirtual  #118 // org.freedesktop.dbus.connections.transports.TransportBuilder.build:()Lorg/freedesktop/dbus/connections/transports/AbstractTransport;
        //    125: astore_1
        //    126: aload_0
        //    127: aload_1
        //    128: invokevirtual  #97 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.setDaemonAndStart:(Lorg/freedesktop/dbus/connections/transports/AbstractTransport;)V
        //    131: getstatic  #52 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    134: ldc  #5 // 'Begin listening to: {}'
        //    136: aload_1
        //    137: invokeinterface  #126 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    142: aload_1
        //    143: invokevirtual  #117 // org.freedesktop.dbus.connections.transports.AbstractTransport.listen:()Lorg/freedesktop/dbus/connections/transports/TransportConnection;
        //    146: astore_2
        //    147: aload_0
        //    148: getfield  #57 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.daemon:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //    151: aload_2
        //    152: invokevirtual  #88 // org.freedesktop.dbus.bin.DBusDaemon.addSock:(Lorg/freedesktop/dbus/connections/transports/TransportConnection;)V
        //    155: goto  185 (offset +30)
        //    158: astore_2
        //    159: getstatic  #52 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    162: ldc  #4 // 'Authentication failed'
        //    164: aload_2
        //    165: invokeinterface  #128 // org.slf4j.Logger.error:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    170: goto  185 (offset +15)
        //    173: astore_2
        //    174: getstatic  #52 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    177: ldc  #6 // 'Connection closed'
        //    179: aload_2
        //    180: invokeinterface  #127 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    185: aload_0
        //    186: getfield  #57 // org.freedesktop.dbus.bin.EmbeddedDBusDaemon.daemon:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //    189: invokevirtual  #90 // org.freedesktop.dbus.bin.DBusDaemon.isRunning:()Z
        //    192: ifne  131 (offset -61)
        //    195: aload_1
        //    196: ifnull  226 (offset +30)
        //    199: aload_1
        //    200: invokevirtual  #116 // org.freedesktop.dbus.connections.transports.AbstractTransport.close:()V
        //    203: goto  226 (offset +23)
        //    206: astore_2
        //    207: aload_1
        //    208: ifnull  224 (offset +16)
        //    211: aload_1
        //    212: invokevirtual  #116 // org.freedesktop.dbus.connections.transports.AbstractTransport.close:()V
        //    215: goto  224 (offset +9)
        //    218: astore_3
        //    219: aload_2
        //    220: aload_3
        //    221: invokevirtual  #75 // java.lang.Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //    224: aload_2
        //    225: athrow
        //    226: return
        //       Exception table:
        //         from 131 to 155 target 158 type org.freedesktop.dbus.exceptions.AuthenticationException
        //         from 131 to 155 target 173 type org.freedesktop.dbus.exceptions.SocketClosedException
        //         from 126 to 195 target 206 type java.lang.Throwable
        //         from 211 to 215 target 218 type java.lang.Throwable
    }

  private void lambda$startListening$1(AbstractTransport arg0) {
        if (bindCallback != null) {
            bindCallback.accept(arg0);
        }
        startupLatch.countDown();
    }

  private static void lambda$startInBackground$0(Thread arg0, Throwable arg1) {
        LOGGER.error("Got uncaught exception", arg1);
    }

}