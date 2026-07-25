// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.ab
package dev.angelvisuals.a;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Kernel;
import java.util.Hashtable;
import org.jetbrains.annotations.NotNull;

public class ab {

    // ---- поля ----
  protected float al;
  protected Kernel field812; // было: a
  private static final String eH = "// signed: JoinerClient @ t.me/JoinerClient";
  private static final String eI = "// number obfuscation: ENABLED (XOR masking)";
  private static final String eJ = "// this jar protected by JoinerObfuscator";
  private static final String eK = "// number obfuscation: ENABLED (XOR masking)";
  private static final String eL = "// good luck with the next 9999 classes";
  private static final int cG = 276695473;
  private static final int cH = -1543825238;
  private static final int cI = 1439633968;
  private static final byte[] ad;

    static {
        ad = "GTo0?,gTeDXetIL683M)~3:s.LfPO#z\"A6:V@'~Qn)rXu1hZ4EmSl<.[98:[>[*y-DXM(jR#vjCxV8FYG!=A;usHk^G,!5AYoqXIa#lJw0f*'}pvdJVIcgT]7H*kN1ifR,P5(.fSz1iV^.Fos?c}(`nQA*;wI03#QULQ~-ao9TkJP8; ;mcmk(]_i3I(<!aOWRI$9rv.2FSTf?b@A78!(PLe,KI%:j l|Ew;!QF(p*I#RHA__E~\"K%?=4va5&283".getBytes("ISO-8859-1");
    }

  public ab(float arg0) { // было: <init>
        super();
        method1464(arg0);
    }

  public static void method1461(@NotNull Kernel arg0, int[] arg1, int[] arg2, int arg3, int arg4, boolean arg5, boolean arg6, boolean arg7, int arg8) { // было: a
        int __stk1;
        float[] var9 = arg0.getKernelData(((float[]) null));
        int var10 = arg0.getWidth();
        int var11 = var10 / (861550095 ^ 861550093);
        int var12 = -877082395 ^ -877082395;
        while (var12 < arg4) {
            int var13 = var12;
            int var14 = var12 * arg3;
            int var15 = 189374801 ^ 189374801;
            while (var15 < arg3) {
                float var16 = 0.0f;
                float var17 = 0.0f;
                float var18 = 0.0f;
                float var19 = 0.0f;
                int var20 = var11;
                int var21 = -var11;
                int var22;
                int var23;
                float var24;
                while (var21 <= var11) {
                    var24 = var9[var20 + var21];
                    if (var24 != 0.0f) {
                        var22 = var15 + var21;
                        if (var22 >= 0) {
                            if (var22 >= arg3) {
                                if (arg8 != (1971420159 ^ 1971420158)) {
                                    if (arg8 == (1263862647 ^ 1263862645)) {
                                        var22 = (var15 + arg3) % arg3;
                                    }
                                } else {
                                    var22 = arg3 - (-10586927 ^ -10586928);
                                }
                            }
                        } else {
                            if (arg8 != (-99376632 ^ -99376631)) {
                                if (arg8 == (1322420007 ^ 1322420005)) {
                                    var22 = (var15 + arg3) % arg3;
                                }
                            } else {
                                var22 = -756007901 ^ -756007901;
                            }
                        }
                        var23 = arg1[var14 + var22];
                        int var25 = var23 >> (-1991186833 ^ -1991186825) & (1586194340 ^ 1586194267);
                        int var26 = var23 >> (-531291624 ^ -531291640) & (-1868385617 ^ -1868385712);
                        int var27 = var23 >> (-354345161 ^ -354345153) & (649043608 ^ 649043559);
                        int var28 = var23 & (-1362019758 ^ -1362019667);
                        if (arg6) {
                            float var29 = ((float) var25) * 0.003921568859368563f;
                            var26 = ((int) (((float) var26) * var29));
                            var27 = ((int) (((float) var27) * var29));
                            var28 = ((int) (((float) var28) * var29));
                        }
                        var19 = var19 + var24 * ((float) var25);
                        var16 = var16 + var24 * ((float) var26);
                        var17 = var17 + var24 * ((float) var27);
                        var18 = var18 + var24 * ((float) var28);
                    }
                    ++var21;
                    continue;
                }
                float var24;
                if (arg7) {
                    if (var19 != 0.0f) {
                        if (var19 != 255.0f) {
                            var24 = 255.0f / var19;
                            var16 = var16 * var24;
                            var17 = var17 * var24;
                            var18 = var18 * var24;
                        }
                    }
                }
                __stk1 = !arg5 ? 807597820 ^ 807597571 : method1462(((int) (((double) var19) + 0.5)));
                var21 = __stk1;
                int var24 = method1462(((int) (((double) var16) + 0.5)));
                int var22 = method1462(((int) (((double) var17) + 0.5)));
                int var23 = method1462(((int) (((double) var18) + 0.5)));
                arg2[var13] = var21 << (-1705048844 ^ -1705048852) | var24 << (1601357876 ^ 1601357860) | var22 << (1626355863 ^ 1626355871) | var23;
                var13 = var13 + arg4;
                ++var15;
                continue;
            }
            ++var12;
            continue;
        }
    }

  public static int method1462(int arg0) { // было: a
        return arg0 >= 0 ? Math.min(arg0, 1869727435 ^ 1869727284) : 257419698 ^ 257419698;
    }

  public static Kernel method1463(float arg0) { // было: a
        int var1 = ((int) Math.ceil(((double) arg0)));
        int var2 = var1 * (515959950 ^ 515959948) + (996213376 ^ 996213377);
        float[] var3 = new float[var2];
        float var4 = arg0 / 3.0f;
        float var5 = 2.0f * var4 * var4;
        float var6 = 6.2831854820251465f * var4;
        float var7 = ((float) Math.sqrt(((double) var6)));
        float var8 = arg0 * arg0;
        float var9 = 0.0f;
        int var10 = -1582557591 ^ -1582557591;
        int var11 = -var1;
        while (var11 <= var1) {
            float var12 = ((float) (var11 * var11));
            if (var12 <= var8) {
                var3[var10] = ((float) Math.exp(((double) (-var12 / var5)))) / var7;
            } else {
                var3[var10] = 0.0f;
            }
            var9 = var9 + var3[var10];
            ++var10;
            ++var11;
            continue;
        }
        var11 = 1493036452 ^ 1493036452;
        while (var11 < var2) {
            var3[var11] = var3[var11] / var9;
            ++var11;
            continue;
        }
        return new Kernel(var2, -73513788 ^ -73513787, var3);
    }

  public void method1464(float arg0) { // было: k
        al = arg0;
        field812 = method1463(arg0);
    }

  public BufferedImage method1465(BufferedImage arg0, BufferedImage arg1) { // было: a
        int var3 = arg0.getWidth();
        int var4 = arg0.getHeight();
        if (arg1 == null) {
            arg1 = method1466(arg0, ((ColorModel) null));
        }
        int[] var5 = new int[var3 * var4];
        int[] var6 = new int[var3 * var4];
        arg0.getRGB(1269516771 ^ 1269516771, 412737651 ^ 412737651, var3, var4, var5, -342333865 ^ -342333865, var3);
        if (al > 0.0f) {
            method1461(field812, var5, var6, var3, var4, 1746426514 ^ 1746426515, -317970525 ^ -317970526, 239859180 ^ 239859180, 1939769604 ^ 1939769605);
            method1461(field812, var6, var5, var4, var3, 2028284684 ^ 2028284685, -699032946 ^ -699032946, 817730196 ^ 817730197, 777221438 ^ 777221439);
        }
        arg1.setRGB(-674938733 ^ -674938733, -168506290 ^ -168506290, var3, var4, var5, -864375577 ^ -864375577, var3);
        return arg1;
    }

  public BufferedImage method1466(BufferedImage arg0, ColorModel arg1) { // было: a
        if (arg1 == null) {
            arg1 = arg0.getColorModel();
        }
        return new BufferedImage(arg1, arg1.createCompatibleWritableRaster(arg0.getWidth(), arg0.getHeight()), arg1.isAlphaPremultiplied(), ((Hashtable) null));
    }

  private static int cj(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int ck(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int cl(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}