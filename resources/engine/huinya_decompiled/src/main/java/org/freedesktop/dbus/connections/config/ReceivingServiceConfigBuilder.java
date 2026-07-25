// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.config.ReceivingServiceConfigBuilder
package org.freedesktop.dbus.connections.config;

import java.util.function.Supplier;
import org.freedesktop.dbus.connections.config.ReceivingServiceConfig;
import org.freedesktop.dbus.connections.config.ReceivingServiceConfigBuilder_Anon1;
import org.freedesktop.dbus.connections.impl.BaseConnectionBuilder;
import org.freedesktop.dbus.connections.shared.IThreadPoolRetryHandler;
import org.freedesktop.dbus.utils.Util;

public final class ReceivingServiceConfigBuilder {

    // ---- поля ----
  public static final int DEFAULT_HANDLER_RETRIES = 10;
  private static final ReceivingServiceConfig DEFAULT_CFG;
  private static final IThreadPoolRetryHandler DEFAULT_RETRYHANDLER;
  private final Supplier connectionBuilder;
  private final ReceivingServiceConfig config;

    static {
        DEFAULT_CFG = new ReceivingServiceConfig();
        DEFAULT_RETRYHANDLER = new ReceivingServiceConfigBuilder_Anon1();
    }

  public ReceivingServiceConfigBuilder(Supplier arg0) { // было: <init>
        super();
        config = new ReceivingServiceConfig();
        connectionBuilder = arg0;
        config.setRetryHandler(DEFAULT_RETRYHANDLER);
    }

  public ReceivingServiceConfigBuilder withSignalThreadCount(int arg0) {
        config.setSignalThreadPoolSize(Math.max(1, arg0));
        return this;
    }

  public ReceivingServiceConfigBuilder withErrorHandlerThreadCount(int arg0) {
        config.setErrorThreadPoolSize(Math.max(1, arg0));
        return this;
    }

  public ReceivingServiceConfigBuilder withMethodCallThreadCount(int arg0) {
        config.setMethodCallThreadPoolSize(Math.max(1, arg0));
        return this;
    }

  public ReceivingServiceConfigBuilder withMethodReturnThreadCount(int arg0) {
        config.setMethodReturnThreadPoolSize(Math.max(1, arg0));
        return this;
    }

  public ReceivingServiceConfigBuilder withSignalThreadPriority(int arg0) {
        config.setSignalThreadPriority(Util.checkIntInRange(arg0, 1, 10));
        return this;
    }

  public ReceivingServiceConfigBuilder withErrorThreadPriority(int arg0) {
        config.setErrorThreadPriority(Util.checkIntInRange(arg0, 1, 10));
        return this;
    }

  public ReceivingServiceConfigBuilder withMethedCallThreadPriority(int arg0) {
        config.setMethodCallThreadPriority(Util.checkIntInRange(arg0, 1, 10));
        return this;
    }

  public ReceivingServiceConfigBuilder withMethodReturnThreadPriority(int arg0) {
        config.setMethodReturnThreadPriority(Util.checkIntInRange(arg0, 1, 10));
        return this;
    }

  public ReceivingServiceConfigBuilder withRetryHandler(IThreadPoolRetryHandler arg0) {
        config.setRetryHandler(arg0);
        return this;
    }

  public ReceivingServiceConfig build() {
        return config;
    }

  public BaseConnectionBuilder connectionConfig() {
        return ((BaseConnectionBuilder) connectionBuilder.get());
    }

  public static ReceivingServiceConfig getDefaultConfig() {
        return DEFAULT_CFG;
    }

  public static IThreadPoolRetryHandler getDefaultRetryHandler() {
        return DEFAULT_RETRYHANDLER;
    }

}