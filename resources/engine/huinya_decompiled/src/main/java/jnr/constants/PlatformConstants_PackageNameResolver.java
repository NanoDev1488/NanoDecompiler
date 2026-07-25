// исходный (обфусцированный) внутренний класс: jnr.constants.PlatformConstants.PackageNameResolver
package jnr.constants;

final class PlatformConstants_PackageNameResolver {

    // ---- поля ----
  public static final String PACKAGE_NAME;

    static {
        PACKAGE_NAME = new PlatformConstants_PackageNameResolver().inferPackageName();
    }

  private PlatformConstants_PackageNameResolver() { // было: <init>
        super();
    }

  private String inferPackageName() {
        String __stk1;
        try {
            Class var1 = getClass();
            Package var2 = var1.getPackage();
            __stk1 = var2 == null ? var1.getName().substring(0, var1.getName().lastIndexOf(46)) : var2.getName();
        } catch (NullPointerException e1) {
            Throwable var1 = e1;
            return "jnr.constants";
        }
    }

}