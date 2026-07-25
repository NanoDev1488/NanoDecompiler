// исходный (обфусцированный) внутренний класс: jnr.constants.PlatformConstants
package jnr.constants;

import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import jnr.constants.PlatformConstants_Anon1;
import jnr.constants.PlatformConstants_Anon2;
import jnr.constants.PlatformConstants_PackageNameResolver;

public final class PlatformConstants {

    // ---- поля ----
  private static final PlatformConstants INSTANCE;
  public static final boolean FAKE;
  public static final Map OS_NAMES;
  public static final Map ARCH_NAMES;
  public static final String ARCH;
  public static final String OS;
  public static final String NAME;
  public static final int BIG_ENDIAN = 4321;
  public static final int LITTLE_ENDIAN = 1234;
  public static final int BYTE_ORDER;

    static {
        int __stk2;
        INSTANCE = new PlatformConstants();
        FAKE = Boolean.valueOf(System.getProperty("jnr.constants.fake", "true")).booleanValue();
        OS_NAMES = new PlatformConstants_Anon1();
        ARCH_NAMES = new PlatformConstants_Anon2();
        ARCH = initArchitecture();
        OS = initOperatingSystem();
        NAME = String.format("%s-%s", new Object[]{ARCH, OS});
        __stk2 = !ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN) ? 1234 : 4321;
        BYTE_ORDER = __stk2;
    }

  public static PlatformConstants getPlatform() {
        return INSTANCE;
    }

  private PlatformConstants() { // было: <init>
        super();
    }

  private static String getConstantsPackageName() {
        return PlatformConstants_PackageNameResolver.PACKAGE_NAME;
    }

  public String[] getPackagePrefixes() {
        if (!FAKE) {
            return new String[]{getArchPackageName(), getOSPackageName()};
        } else {
            return new String[]{getArchPackageName(), getOSPackageName(), getFakePackageName()};
        }
    }

  public String getArchPackageName() {
        return String.format("%s.platform.%s.%s", new Object[]{getConstantsPackageName(), OS, ARCH});
    }

  public String getOSPackageName() {
        return String.format("%s.platform.%s", new Object[]{getConstantsPackageName(), OS});
    }

  public String getFakePackageName() {
        return String.format("%s.platform.fake", new Object[]{getConstantsPackageName()});
    }

  private static String initOperatingSystem() {
        String var0 = getProperty("os.name", "unknown").toLowerCase();
        Iterator var1 = OS_NAMES.keySet().iterator();
        String var2;
        while (true) {
            if (!var1.hasNext()) {
                if (!var0.startsWith("windows")) {
                    return var0;
                } else {
                    return "windows";
                }
            }
            var2 = ((String) var1.next());
            if (var2.equalsIgnoreCase(var0)) {
                break;
            }
            continue;
        }
        return ((String) OS_NAMES.get(var2));
    }

  private static final String initArchitecture() {
        String var0 = getProperty("os.arch", "unknown").toLowerCase();
        Iterator var1 = ARCH_NAMES.keySet().iterator();
        String var2;
        while (true) {
            if (!var1.hasNext()) {
                return var0;
            }
            var2 = ((String) var1.next());
            if (var2.equalsIgnoreCase(var0)) {
                break;
            }
            continue;
        }
        return ((String) ARCH_NAMES.get(var2));
    }

  private static String getProperty(String arg0, String arg1) {
        String __stk1;
        try {
            __stk1 = System.getProperty(arg0, arg1);
        } catch (SecurityException var2) {
            return arg1;
        }
    }

}