// исходный (обфусцированный) внутренний класс: jnr.ffi.Platform.Windows
package jnr.ffi;

import jnr.ffi.Platform_OS;
import jnr.ffi.Platform_Supported;

class Platform_Windows extends Platform_Supported {

    // ---- поля ----
  private static final String WINDOWS_SERVER = "server";
  private static final String WINDOWS_VISTA = "windows vista";
  private static final String WINDOWS_7 = "windows 7";
  private static final String WINDOWS_8 = "windows 8";
  private static final String WINDOWS_10 = "windows 10";
  private static final String WINDOWS_11 = "windows 11";

  public Platform_Windows() { // было: <init>
        super(Platform_OS.WINDOWS);
    }

  private String osName() {
        return System.getProperty("os.name").toLowerCase();
    }

  public boolean isServer() {
        return osName().contains("server");
    }

  public boolean isVista() {
        return osName().contains("windows vista");
    }

  public boolean is7() {
        return osName().contains("windows 7");
    }

  public boolean is8() {
        return osName().contains("windows 8");
    }

  public boolean is10() {
        return osName().contains("windows 10");
    }

  public boolean is11() {
        return osName().contains("windows 11");
    }

}