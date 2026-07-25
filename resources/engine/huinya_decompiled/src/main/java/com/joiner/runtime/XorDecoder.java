// исходный (обфусцированный) внутренний класс: com.joiner.runtime.XorDecoder
package com.joiner.runtime;

public final class XorDecoder {

  private XorDecoder() { // было: <init>
        super();
    }

  public static String method1946(String arg0, int arg1) { // было: d
        byte[] var2 = arg0.getBytes("ISO-8859-1");
        byte[] var3 = new byte[var2.length];
        int var4 = 0;
        while (var4 < var2.length) {
            var3[var4] = ((byte) (var2[var4] ^ arg1 >>> ((var4 & 3) << 3) & 255));
            ++var4;
            continue;
        }
        return new String(var3, "UTF-8");
    }

}