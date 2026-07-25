// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.E
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import dev.angelvisuals.a.ClassA50_ClassA51;
import dev.angelvisuals.a.aR;
import dev.angelvisuals.a.do;
import java.util.LinkedList;
import java.util.Queue;

public class ClassA52 {

    // ---- поля ----
  private final Queue field221; // было: a
  private static final String cd = "// stop. seriously. go play minecraft instead";
  private static final String ce = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String cf = "// string encryption: ENABLED (AES-128/ECB + XOR)";
  private static final String cg = "// you are reading machine-generated garbage";
  private static final String ch = "// flow obfuscation: ENABLED";
  private static final int aY = -341010083;
  private static final int aZ = 1783852802;
  private static final int ba = -798418089;
  private static final byte[] field222; // было: F

    static {
        field222 = "@|mJ~|0:##O]oFB?N/L~so =`C^a-`k'L/|5AwtI}Db@>e8O$<Mbf#!AH~BI\\d1S;Ea._k.9D[>\"46o@]mt:{$'Tn*9()f*mO+Q+*v\\~~?@w9Kb{jdX3c[y&9-+UQTsZp#T <cjA`N6XdNX3I#3W+jD$reqe7xW$5\"1}cygAUqrO]&#f:hIE24{n4k^@KI3}j}Q1\\{J9a5NdEAcrhPJW lZeE>+#`1RLu3*C[aphbmy\\I15/q/[ b@@Ptr*uXI8!".getBytes("ISO-8859-1");
    }

  public ClassA52() { // было: <init>
        super();
        field221 = new LinkedList();
        EventManager.register(this);
    }

  public void method443(Object arg0) { // было: a
        ClassA50_ClassA51 var2 = ((ClassA50_ClassA51) field221.peek());
        if (var2 != null) {
            boolean var3 = var2.method441(arg0);
            if (var3) {
                field221.poll();
            }
        }
    }

    @EventTarget
  public void method444(aR arg0) { // было: a
        method443(arg0);
    }

    @EventTarget(0)
  public void method445(do arg0) { // было: b
        method443(arg0);
    }

  public void method446(ClassA50_ClassA51 arg0) { // было: a
        field221.add(arg0);
    }

  public boolean method447() { // было: o
        return field221.isEmpty();
    }

  private static int aP(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int aQ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int aR(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}