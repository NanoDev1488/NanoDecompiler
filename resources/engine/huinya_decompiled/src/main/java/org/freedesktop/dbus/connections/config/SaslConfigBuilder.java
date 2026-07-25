// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.config.SaslConfigBuilder
package org.freedesktop.dbus.connections.config;

import java.util.OptionalLong;
import org.freedesktop.dbus.connections.config.SaslConfig;
import org.freedesktop.dbus.connections.config.TransportConfigBuilder;
import org.freedesktop.dbus.connections.transports.TransportBuilder_SaslAuthMode;

public final class SaslConfigBuilder {

    // ---- поля ----
  private SaslConfig saslConfig;
  private final TransportConfigBuilder transportBuilder;

   SaslConfigBuilder(TransportConfigBuilder arg0) { // было: <init>
        super();
        saslConfig = new SaslConfig();
        transportBuilder = arg0;
    }

  public TransportConfigBuilder back() {
        return transportBuilder;
    }

  public SaslConfigBuilder withAuthMode(TransportBuilder_SaslAuthMode arg0) {
        if (arg0 != null) {
            saslConfig.setAuthMode(arg0.getAuthMode());
        }
        return this;
    }

  public SaslConfigBuilder withSaslUid(Long arg0) {
        saslConfig.setSaslUid(OptionalLong.of(arg0.longValue()));
        return this;
    }

  public SaslConfigBuilder withStrictCookiePermissions(boolean arg0) {
        saslConfig.setStrictCookiePermissions(arg0);
        return this;
    }

  public SaslConfig build() {
        return saslConfig;
    }

   SaslConfigBuilder withConfig(SaslConfig arg0) {
        if (arg0 != null) {
            saslConfig = arg0;
        }
        return this;
    }

}