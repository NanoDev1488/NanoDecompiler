// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.config.TransportConfig
package org.freedesktop.dbus.connections.config;

import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.config.SaslConfig;
import org.freedesktop.dbus.connections.impl.BaseConnectionBuilder;
import org.freedesktop.dbus.utils.Util;

public final class TransportConfig {

    // ---- поля ----
  private SaslConfig saslConfig;
  private BusAddress busAddress;
  private Consumer preConnectCallback;
  private Consumer afterBindCallback;
  private int timeout;
  private boolean autoConnect;
  private String fileOwner;
  private String fileGroup;
  private byte endianess;
  private boolean registerSelf;
  private Set fileUnixPermissions;
  private Map additionalConfig;

  public TransportConfig(BusAddress arg0) { // было: <init>
        super();
        timeout = 10000;
        autoConnect = true;
        endianess = BaseConnectionBuilder.getSystemEndianness();
        registerSelf = true;
        additionalConfig = new LinkedHashMap();
        busAddress = arg0;
    }

  public TransportConfig() { // было: <init>
        this(null);
    }

  public BusAddress getBusAddress() {
        return busAddress;
    }

  public void setBusAddress(BusAddress arg0) {
        busAddress = ((BusAddress) Objects.requireNonNull(arg0, "BusAddress required"));
    }

  public void setListening(boolean arg0) {
        updateBusAddress(arg0);
    }

  public boolean isListening() {
        return busAddress == null ? 0 : busAddress.isListeningSocket();
    }

  public Consumer getPreConnectCallback() {
        return preConnectCallback;
    }

  public void setPreConnectCallback(Consumer arg0) {
        preConnectCallback = arg0;
    }

  public Consumer getAfterBindCallback() {
        return afterBindCallback;
    }

  public void setAfterBindCallback(Consumer arg0) {
        afterBindCallback = arg0;
    }

  public boolean isAutoConnect() {
        return autoConnect;
    }

  public void setAutoConnect(boolean arg0) {
        autoConnect = arg0;
    }

  public int getTimeout() {
        return timeout;
    }

  public void setTimeout(int arg0) {
        timeout = arg0;
    }

  public String getFileOwner() {
        return fileOwner;
    }

  public void setFileOwner(String arg0) {
        fileOwner = arg0;
    }

  public String getFileGroup() {
        return fileGroup;
    }

  public void setFileGroup(String arg0) {
        fileGroup = arg0;
    }

  public Set getFileUnixPermissions() {
        return fileUnixPermissions;
    }

  public void setFileUnixPermissions(PosixFilePermission[] arg0) {
        if (!Util.isWindows()) {
            if (arg0 == null) {
                return;
            } else {
                if (arg0.length >= 1) {
                    fileUnixPermissions = new LinkedHashSet(Arrays.asList(arg0));
                    return;
                } else {
                    return;
                }
            }
        } else {
            return;
        }
    }

  public Map getAdditionalConfig() {
        return additionalConfig;
    }

  public void setAdditionalConfig(Map arg0) {
        additionalConfig = arg0;
    }

  public SaslConfig getSaslConfig() {
        if (saslConfig == null) {
            saslConfig = new SaslConfig();
        }
        return saslConfig;
    }

   void setSaslConfig(SaslConfig arg0) {
        saslConfig = arg0;
    }

  public byte getEndianess() {
        return endianess;
    }

  public void setEndianess(byte arg0) {
        endianess = arg0;
    }

  public boolean isRegisterSelf() {
        return registerSelf;
    }

  public void setRegisterSelf(boolean arg0) {
        registerSelf = arg0;
    }

   void updateBusAddress(boolean arg0) {
        if (busAddress != null) {
            if (busAddress.isListeningSocket()) {
                if (busAddress.isListeningSocket()) {
                    if (!arg0) {
                        busAddress.removeParameter("listen");
                    }
                }
            } else {
                if (!arg0) {
                    if (busAddress.isListeningSocket()) {
                        if (!arg0) {
                            busAddress.removeParameter("listen");
                        }
                    }
                } else {
                    busAddress.addParameter("listen", "true");
                }
            }
            return;
        } else {
            return;
        }
    }

}