// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.config.SaslConfig
package org.freedesktop.dbus.connections.config;

import java.util.OptionalLong;
import org.freedesktop.dbus.connections.SASL_SaslMode;

public class SaslConfig {

    // ---- поля ----
  private SASL_SaslMode mode;
  private int authMode;
  private String guid;
  private OptionalLong saslUid;
  private boolean strictCookiePermissions;
  private boolean fileDescriptorSupport;

   SaslConfig() { // было: <init>
        super();
        mode = SASL_SaslMode.CLIENT;
        authMode = 0;
        saslUid = OptionalLong.empty();
    }

  public SASL_SaslMode getMode() {
        return mode;
    }

  public void setMode(SASL_SaslMode arg0) {
        mode = arg0;
    }

  public int getAuthMode() {
        return authMode;
    }

  public void setAuthMode(int arg0) {
        authMode = arg0;
    }

  public String getGuid() {
        return guid;
    }

  public void setGuid(String arg0) {
        guid = arg0;
    }

  public OptionalLong getSaslUid() {
        return saslUid;
    }

  public void setSaslUid(OptionalLong arg0) {
        saslUid = arg0;
    }

  public boolean isStrictCookiePermissions() {
        return strictCookiePermissions;
    }

  public void setStrictCookiePermissions(boolean arg0) {
        strictCookiePermissions = arg0;
    }

  public boolean isFileDescriptorSupport() {
        return fileDescriptorSupport;
    }

  public void setFileDescriptorSupport(boolean arg0) {
        fileDescriptorSupport = arg0;
    }

  public String toString() {
        return getClass().getSimpleName() + " [mode=" + String.valueOf(mode) + ", authMode=" + authMode + ", guid=" + guid + ", saslUid=" + String.valueOf(saslUid) + ", strictCookiePermissions=" + strictCookiePermissions + ", fileDescriptorSupport=" + fileDescriptorSupport + "]";
    }

}