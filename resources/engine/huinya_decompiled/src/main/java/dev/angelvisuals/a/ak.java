// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.aK
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.cF;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public abstract class aK implements cF {

    // ---- поля ----
  private int fo;
  private static final String iZ = "// stop. seriously. go play minecraft instead";
  private static final String ja = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String jb = "// number obfuscation: ENABLED (XOR masking)";
  private static final String jc = "// good luck with the next 9999 classes";
  private static final String jd = "// every class watermarked, every string encrypted, every number xored";
  private static final int fp = -1250019138;
  private static final int fq = -100672321;
  private static final int fr = 221716510;
  private static final byte[] aU;

    static {
        aU = "4[1gkGY ulQY9z)*:5$Azs3.0~:@jxY\\>ctTd]4-nj@l{OSOiHU6xnR;oo?W>~ mkS1[:;\"Avj]h/d#0yiK]I;rNFZ4XFb`pa:b6>{3/AT@-^>Da#cB(VBp[j/@KD$AO@]iL,gL-MH<)(mJWi6f)BxEy$9H+n|V]m# G>r<=fE?%6j),J<RW66&Z.nI!OeYe?4@*~9^|g$ZdIUNc' Po15q|;tAsT?-9kb{k?-Lk\"i,uD\"9!Un~B=/X(NQ\"t[jk;".getBytes("ISO-8859-1");
    }

  public aK() { // было: <init>
        super();
        int var1 = method1871(-2115125101 ^ -2115155038, method1878());
        int var2 = method1871(733458358 ^ 733422726, method1877());
        fo = GL20.glCreateProgram();
        GL20.glAttachShader(fo, var1);
        GL20.glAttachShader(fo, var2);
        GL20.glBindAttribLocation(fo, -587062168 ^ -587062168, Decryptor.method1945(XorDecoder.method1946("èá\u0011Ú§ô\u000fô±Æ\u0015öðò'Á¥\u0002Ù¤X", 829835766 ^ 1423346294)));
        GL20.glLinkProgram(fo);
        if (GL20.glGetProgrami(fo, 917843132 ^ 917874494) != 0) {
        }
    }

  private int method1871(int arg0, String arg1) { // было: a
        int var3 = GL20.glCreateShader(arg0);
        GL20.glShaderSource(var3, arg1);
        GL20.glCompileShader(var3);
        if (GL20.glGetShaderi(var3, -1998501747 ^ -1998470388) != 0) {
        }
        return var3;
    }

  public void method1872() { // было: N
        GL20.glUseProgram(fo);
    }

  public void method1873() { // было: O
        GL20.glUseProgram(-1643566292 ^ -1643566292);
    }

  public void method1874() { // было: P
        int var1 = GL30.glGenVertexArrays();
        int var2 = GL15.glGenBuffers();
        GL30.glBindVertexArray(var1);
        GL15.glBindBuffer(527675238 ^ 527640564, var2);
        FloatBuffer var3 = BufferUtils.createFloatBuffer(1781786374 ^ 1781786382);
        float[] __obj1 = new float[679648590 ^ 679648582];
        __obj1[1087871539 ^ 1087871539] = -1.0f;
        __obj1[781951207 ^ 781951206] = -1.0f;
        __obj1[924582326 ^ 924582324] = 1.0f;
        __obj1[1981751528 ^ 1981751531] = -1.0f;
        __obj1[122902019 ^ 122902023] = 1.0f;
        __obj1[-1769954814 ^ -1769954809] = 1.0f;
        __obj1[134581136 ^ 134581142] = -1.0f;
        __obj1[-568922867 ^ -568922870] = 1.0f;
        var3.put(__obj1).flip();
        GL15.glBufferData(530533114 ^ 530563688, var3, 1880554006 ^ 1880519410);
        GL20.glEnableVertexAttribArray(-1976466410 ^ -1976466410);
        GL20.glVertexAttribPointer(640215338 ^ 640215338, 1958933669 ^ 1958933671, 1477627875 ^ 1477630949, -1530707450 ^ -1530707450, 1745889970 ^ 1745889970, -8522157179032230969L ^ -8522157179032230969L);
        GL11.glDrawArrays(-1825098915 ^ -1825098917, -666940277 ^ -666940277, 1798353520 ^ 1798353524);
        GL20.glDisableVertexAttribArray(-1632540053 ^ -1632540053);
        GL30.glBindVertexArray(1465476128 ^ 1465476128);
        GL15.glBindBuffer(-905472623 ^ -905503485, 978865879 ^ 978865879);
        GL15.glDeleteBuffers(var2);
        GL30.glDeleteVertexArrays(var1);
    }

  public int method1875(String arg0) { // было: b
        return GL20.glGetUniformLocation(fo, arg0);
    }

  public void method1876(String arg0, float[] arg1) { // было: a
        int var3 = method1875(arg0);
        if (var3 != (821501161 ^ -821501162)) {
            switch (arg1.length) {
                case 1:
                    GL20.glUniform1f(var3, arg1[1688797356 ^ 1688797356]);
                    break;
                case 2:
                    GL20.glUniform2f(var3, arg1[803194356 ^ 803194356], arg1[-1084611544 ^ -1084611543]);
                    break;
                case 3:
                    GL20.glUniform3f(var3, arg1[1147974462 ^ 1147974462], arg1[2083866391 ^ 2083866390], arg1[-396766425 ^ -396766427]);
                    break;
                case 4:
                    GL20.glUniform4f(var3, arg1[1407126057 ^ 1407126057], arg1[179403157 ^ 179403156], arg1[-1345086485 ^ -1345086487], arg1[-525419796 ^ -525419793]);
                default:
            }
        } else {
            return;
        }
    }

  public abstract String method1877(); // было: v

  public String method1878() { // было: w
        return Decryptor.method1945(XorDecoder.method1946("½÷à×£É¦¼×¢õ¼ùÀ­óó¬¡ð¬ÿá¹Ýùóø²ÿ¼Èúæ¹É¤Ã¯ñ¿Ô©µóîìÿ¦ä©õ¸éáüÒ¨´ºëó³¾¯õÊó¡ç¡ü÷¸¿Ë¨®¹¤¸ÿûÑåê¿¦ñÿùÈ¥¦àçýó¥ø¤õ½¯ÔùÝ¬±È°¯Ï¹ô¡¿Ã¥¯éòµºÐ¶²Ò¸©ñ§¡ë©ó±òãõ¸ùø°£¶¾Ê³÷®Ñ¡ûÄªúå¼óó§çþøÜ¹òõÖ¿õ §ýÌ²¦Ë±Õ³èµ¼è²§ö", 21356189 ^ -1534189993));
    }

  public boolean method1879() { // было: B
        return fo == 0 ? -447367660 ^ -447367660 : -1779056315 ^ -1779056316;
    }

  private static int eI(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int eJ(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int eK(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}