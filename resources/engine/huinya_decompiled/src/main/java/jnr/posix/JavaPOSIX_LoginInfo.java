// исходный (обфусцированный) внутренний класс: jnr.posix.JavaPOSIX.LoginInfo
package jnr.posix;

import jnr.posix.JavaPOSIX_IDHelper;

final class JavaPOSIX_LoginInfo {

    // ---- поля ----
  public static final int UID;
  public static final int GID;
  public static final String USERNAME;

    static {
        UID = JavaPOSIX_IDHelper.getInt("-u");
        GID = JavaPOSIX_IDHelper.getInt("-g");
        USERNAME = JavaPOSIX_IDHelper.getString("-un");
    }

   JavaPOSIX_LoginInfo() { // было: <init>
        super();
    }

}