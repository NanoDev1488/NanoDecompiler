// исходный (обфусцированный) внутренний класс: com.joiner.runtime.Decryptor
package com.joiner.runtime;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.HashMap;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public final class Decryptor {

    // ---- поля ----
  private static final HashMap CACHE;
  private static final byte[] KEY;

    static {
        CACHE = new HashMap();
        KEY = new byte[]{66, 114, 70, 87, 77, 110, 56, 49, 69, 51, 73, 118, 72, 68, 67, 82};
    }

  public static String method1945(String arg0) { // было: d
        String __stk1;
        String var1 = ((String) CACHE.get(arg0));
        if (var1 != null) {
            return var1;
        }
        try {
            Cipher var2 = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec var3 = new SecretKeySpec(KEY, "AES");
            var2.init(2, var3);
            String var4 = new String(var2.doFinal(Base64.getDecoder().decode(arg0)), "UTF-8");
            CACHE.put(arg0, var4);
            __stk1 = var4;
        } catch (Exception e1) {
            var1 = e1;
            throw new RuntimeException(var1);
        }
    }

  private Decryptor() { // было: <init>
        super();
    }

}