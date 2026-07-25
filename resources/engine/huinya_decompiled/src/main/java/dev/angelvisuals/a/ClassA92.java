// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.h
package dev.angelvisuals.a;

import com.darkmagician6.eventapi.EventTarget;
import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.bA;
import dev.angelvisuals.a.bI;
import dev.angelvisuals.a.bb;
import dev.angelvisuals.a.cK;
import net.minecraft.class_746;

@bI(name = "ItemScroller", a = "OTHER", I = "Плавная смена предметов колесом с задержкой")
public final class ClassA92 extends cK {

    // ---- поля ----
  public static final ClassA92 field344; // было: a
  public final bA field345; // было: b
  private long field346; // было: c
  private static final String field347 = "// Joiner sees you"; // было: J
  private static final String field348 = "// number obfuscation: ENABLED (XOR masking)"; // было: K
  private static final String field349 = "// this jar protected by JoinerObfuscator"; // было: L
  private static final String field350 = "// Joiner sees you"; // было: M
  private static final String field351 = "// this jar protected by JoinerObfuscator"; // было: N
  private static final int field352 = -572561690; // было: B
  private static final int field353 = 56329988; // было: C
  private static final int field354 = -1682594737; // было: D
  private static final byte[] field355; // было: h

    static {
        field355 = "0xVy\\7E!U!jQT@ [cNtR=Oa{e)5 WI3E;r&Oj0hW&@l_QA+F.1E\"@*-Z?~ED;+Ykwu/9P-[vQA3.~`?zv0juK4V$W4Yx`Ztr0}*st]1dGz37JwfZYL7)';$Fp*.HWGFOa$QE/+X3O_Q}1J)~ 1\"t'w<@hr7t..X$=i-9=aAv_@!}6t4oyD w9rYz)i7ley>5[Eq>g6r WfoO\\f [G~)&~|PYd8%m$$v*%'hA(,`&ub_}}TQ=\\_ls&=u34.1*BCc:".getBytes("ISO-8859-1");
        field344 = new ClassA92();
    }

  private ClassA92() { // было: <init>
        super();
        field345 = new bA(Decryptor.method1945(XorDecoder.method1946(">°ä\u0011\u0018Ðü$\u0019àâ\u0017 í\r8îÃ:/ìæ+\u001eõÃ+'éñf'Èé\u001f\u001f¶Ô(\u001dîác", 1542169779 ^ 89859813)), 90.0f, 0.0f, 500.0f, 5.0f);
    }

    @EventTarget
  public void method738(bb arg0) { // было: a
        int __stk1;
        if (mc.field_1724 == null) {
            return;
        } else {
            if (arg0.method243() != 0.0) {
                long var2 = System.currentTimeMillis();
                arg0.setCancelled(-2116265468 ^ -2116265467);
                if (((float) (var2 - field346)) >= field345.bp()) {
                    field346 = var2;
                    __stk1 = arg0.method243() <= 0.0 ? 1476940168 ^ 1476940169 : -978858940 ^ 978858939;
                    int var4 = __stk1;
                    mc.field_1724.method_31548().field_7545 = Math.floorMod(mc.field_1724.method_31548().field_7545 + var4, -977112879 ^ -977112872);
                    return;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

  private static int method739(int arg0, int arg1) { // было: v
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method740(int arg0, int arg1) { // было: w
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int method741(int arg0, int arg1) { // было: x
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}