// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.config.TransportConfigBuilder
package org.freedesktop.dbus.connections.config;

import java.nio.file.attribute.PosixFilePermission;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.config.SaslConfig;
import org.freedesktop.dbus.connections.config.SaslConfigBuilder;
import org.freedesktop.dbus.connections.config.TransportConfig;

public class TransportConfigBuilder {

    // ---- поля ----
  private final Supplier connectionBuilder;
  private TransportConfig config;
  private final SaslConfigBuilder saslConfigBuilder;

  public TransportConfigBuilder(Supplier arg0) { // было: <init>
        super();
        config = new TransportConfig();
        connectionBuilder = arg0;
        saslConfigBuilder = new SaslConfigBuilder(this);
    }

   TransportConfigBuilder self() {
        return this;
    }

  public TransportConfigBuilder withConfig(TransportConfig arg0) {
        config = ((TransportConfig) Objects.requireNonNull(arg0, "TransportConfig required"));
        saslConfigBuilder.withConfig(arg0.getSaslConfig());
        return self();
    }

  public TransportConfigBuilder withBusAddress(BusAddress arg0) {
        config.setBusAddress(((BusAddress) Objects.requireNonNull(arg0, "BusAddress required")));
        return self();
    }

  public BusAddress getBusAddress() {
        return config.getBusAddress();
    }

  public TransportConfigBuilder withPreConnectCallback(Consumer arg0) {
        config.setPreConnectCallback(arg0);
        return self();
    }

  public TransportConfigBuilder withAfterBindCallback(Consumer arg0) {
        config.setAfterBindCallback(arg0);
        return self();
    }

  public TransportConfigBuilder withAutoConnect(boolean arg0) {
        config.setAutoConnect(arg0);
        return self();
    }

  public TransportConfigBuilder withRegisterSelf(boolean arg0) {
        config.setRegisterSelf(arg0);
        return self();
    }

  public SaslConfigBuilder configureSasl() {
        return saslConfigBuilder;
    }

  public TransportConfigBuilder withListening(boolean arg0) {
        config.setListening(arg0);
        return self();
    }

  public TransportConfigBuilder withTimeout(int arg0) {
        if (arg0 >= 0) {
            config.setTimeout(arg0);
        }
        return self();
    }

  public TransportConfigBuilder withUnixSocketFileOwner(String arg0) {
        config.setFileOwner(arg0);
        return self();
    }

  public TransportConfigBuilder withUnixSocketFileGroup(String arg0) {
        config.setFileGroup(arg0);
        return self();
    }

  public TransportConfigBuilder withUnixSocketFilePermissions(PosixFilePermission[] arg0) {
        config.setFileUnixPermissions(arg0);
        return self();
    }

  public TransportConfigBuilder withAdditionalConfig(String arg0, Object arg1) {
        config.getAdditionalConfig().put(arg0, arg1);
        return self();
    }

  public TransportConfigBuilder withRemoveAdditionalConfig(String arg0) {
        config.getAdditionalConfig().remove(arg0);
        return self();
    }

  public TransportConfigBuilder withEndianess(byte arg0) {
        if (arg0 == 66) {
            config.setEndianess(arg0);
        } else {
            if (arg0 == 108) {
                config.setEndianess(arg0);
            }
        }
        return self();
    }

  public Object back() {
        return connectionBuilder == null ? null : connectionBuilder.get();
    }

  public TransportConfig build() {
        SaslConfig var1 = saslConfigBuilder.build();
        config.setSaslConfig(var1);
        return config;
    }

}