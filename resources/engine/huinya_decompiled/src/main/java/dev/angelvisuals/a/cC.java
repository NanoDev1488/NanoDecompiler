// исходный (обфусцированный) внутренний класс: dev.angelvisuals.a.cc
package dev.angelvisuals.a;

import com.joiner.runtime.Decryptor;
import com.joiner.runtime.XorDecoder;
import dev.angelvisuals.a.bE;
import dev.angelvisuals.a.bR;
import dev.angelvisuals.a.bU;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.connections.impl.DBusConnectionBuilder;
import org.freedesktop.dbus.interfaces.DBus;
import org.freedesktop.dbus.interfaces.Properties;
import org.jetbrains.annotations.NotNull;

@Metadata
@SourceDebugExtension
public final class cc implements bE {

    // ---- поля ----
    @NotNull
  public static final cc field1011; // было: a
  private static final DBusConnection field1012; // было: a
  private static final DBus field1013; // было: a
  private static final String sj = "// reverse-engineering this jar is a waste of time, friend";
  private static final String sk = "// number obfuscation: ENABLED (XOR masking)";
  private static final String sl = "// nice try. closed source for a reason.";
  private static final String sm = "// good luck with the next 9999 classes";
  private static final String sn = "// if you want to skid - at least credit the original (t.me/JoinerClient)";
  private static final int kL = -1275917892;
  private static final int kM = 1728954510;
  private static final int kN = 1086089506;
  private static final byte[] cy;

    static {
        cy = "is[4cO2<d}=41wQuwaCd'XbR4:r$pb@V[p;\\Sd\"#RU:BSRMy'BwUJ,i8]lwPV,e/p.6P-},y(EUwg%_E2^AL QdZd3QXBUTwp,UYV$<(UwW \"(aihyR0[.v0L1&c1XAj?y$}mqG49\\{y)3v,S7UPlS4f|lHh:OhVT'*pN?\"z*\\wlJu;1C8#.Gy(#\\fYST|k'xc%eXh(Oyvst760S$_^/FY^qIa^&'(!Z+%{_|l'V4NXI\"|l+fijccD.~/wQZYU+(".getBytes("ISO-8859-1");
        field1011 = new cc();
        field1012 = DBusConnectionBuilder.forSessionBus().build();
        field1013 = ((DBus) field1012.getRemoteObject(Decryptor.method1945(XorDecoder.method1946("Tø**\t/ö\u0007h:òvþ)\\,±[õ\u001aº_-ºSÆ\u0008)\u0016éNûFä", 181739056 ^ -743726551)), Decryptor.method1945(XorDecoder.method1946("­\u0010@Ú\u0013]Å·\u001bzè¢\n\u001cÈ®\u0019EÚÕ\t\u0014", -22260664 ^ 1350904494)), DBus.class));
    }

  private cc() { // было: <init>
        super();
    }

    @NotNull
  public List method1920() { // было: m
        int __stk1;
        Intrinsics.checkNotNullExpressionValue(field1013.ListNames(), Decryptor.method1945(XorDecoder.method1946("ôrâò|Þ¶Ï[ÿÝFÐãzÃ½K¦Ã", -1079067959 ^ 1094037580)));
        Object[] var1 = ((Object[]) field1013.ListNames());
        int var2 = -1136123073 ^ -1136123073;
        Object[] var3 = var1;
        Collection var4 = ((Collection) new ArrayList());
        int var5 = -613820805 ^ -613820805;
        int var6 = 1292628812 ^ 1292628812;
        int var7 = var3.length;
        while (var6 < var7) {
            Object var8 = var3[var6];
            String var9 = ((String) var8);
            int var10 = -589131403 ^ -589131403;
            Intrinsics.checkNotNull(var9);
            if (StringsKt.startsWith$default(var9, Decryptor.method1945(XorDecoder.method1946("\u001cõmt\n|jSö@H\núW$\u0001öP$PíOf&vuUå\u001eT\u0015òyk\u0012üC \u0013Ä\r/", -1795729483 ^ -2034048048)), 1962749137 ^ 1962749137, -1529641986 ^ -1529641988, null)) {
                var4.add(var8);
            }
            ++var6;
            continue;
        }
        var1 = ((Iterable) ((List) var4));
        var2 = 1869714455 ^ 1869714455;
        var3 = var1;
        var4 = ((Collection) new ArrayList(CollectionsKt.collectionSizeOrDefault(var1, 855617481 ^ 855617475)));
        var5 = -1631838361 ^ -1631838361;
        var6 = var3.iterator();
        while (var6.hasNext()) {
            var7 = var6.next();
            String var8 = ((String) var7);
            Collection var11 = var4;
            int var9 = -1852388103 ^ -1852388103;
            Intrinsics.checkNotNullExpressionValue(field1012.getRemoteObject(((String) var8), Decryptor.method1945(XorDecoder.method1946("·\u001e¾Ã6Ë7ÏÀ$ºÆDï\u001dÒ<·Ã\u0002¶ã½?Ô¶1§ÂH", 1261082535 ^ 1054675060)), bR.class), Decryptor.method1945(XorDecoder.method1946("\u000cG9æ$^-À��Q\u000bÂ\u0002w0ë\u0019D\u0011Ü\u0019\\+ý7hMë\u0012_\u0006ìcf1¢-a/À\u0004A\u001b°", 2145134082 ^ -224390057)));
            Intrinsics.checkNotNull(var8);
            var11.add(new bU(((bR) field1012.getRemoteObject(((String) var8), Decryptor.method1945(XorDecoder.method1946("·\u001e¾Ã6Ë7ÏÀ$ºÆDï\u001dÒ<·Ã\u0002¶ã½?Ô¶1§ÂH", 1261082535 ^ 1054675060)), bR.class)), StringsKt.removePrefix(((String) var8), ((CharSequence) Decryptor.method1945(XorDecoder.method1946("åAX1ó0I/ªBu\róNbaøBea©Yz#ß6C0¬Q+\u0011ìFL.ëHveêp8j", 377544338 ^ 1098964494))))));
            continue;
        }
        var1 = ((Iterable) ((List) var4));
        var2 = 521710954 ^ 521710954;
        var3 = var1;
        var4 = ((Collection) new ArrayList());
        var5 = -1835647868 ^ -1835647868;
        var6 = var3.iterator();
        while (var6.hasNext()) {
            var7 = var6.next();
            bU var8 = ((bU) var7);
            int var9 = -814706755 ^ -814706755;
            __stk1 = Intrinsics.areEqual(field1011.method1921(var8.method1922(), Decryptor.method1945(XorDecoder.method1946("\u0007J:<oVß N&û\u0018l]Æ?M#Ø%5S", 1487916809 ^ 916962215))), Decryptor.method1945(XorDecoder.method1946("\u001bÒþ\u0010%¨Ów(ÑÏK)ÎÏT8·ÐAHëª\u0019", 818791624 ^ 341465273))) ? 1511056654 ^ 1511056654 : 1755670331 ^ 1755670330;
            if (__stk1 == 0) {
                continue;
            }
            var4.add(var7);
            continue;
        }
        return ((List) var4);
    }

  public final Object method1921(@NotNull String arg0, @NotNull String arg1) { // было: a
        Intrinsics.checkNotNullParameter(arg0, Decryptor.method1945(XorDecoder.method1946("ý9ôâ\u0004òÔÖ+¶åè.õö÷#­Ý0þ", 2061190319 ^ -568842998)));
        Intrinsics.checkNotNullParameter(arg1, Decryptor.method1945(XorDecoder.method1946("ì��¹\u0008T\u0012ãw\u0014Ì\u0003)÷\u00035ÕgÊB", 1251638035 ^ 896355753)));
        Properties var3 = ((Properties) field1012.getRemoteObject("org.mpris.MediaPlayer2." + arg0, Decryptor.method1945(XorDecoder.method1946("G\u0010ºî@»ê\u0013¨¨ã@ÈÊG÷W°¥æU¤Æn³ñe½µçGÄ", -950499638 ^ 1048323083)), Properties.class));
        return var3.Get(Decryptor.method1945(XorDecoder.method1946("\u0010\rö\n\u0006|ç\u0014_\u000eÛ6\u0006\u0002ÌZ\r\u000eËZ\\-Æ\u001e\r\u0007#\u001e\u0015ë\u000eP.ü\u0006?=×\u0005#\u0015ëQ", -124291305 ^ -1808188546)), arg1);
    }

  private static int jr(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int js(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

  private static int jt(int arg0, int arg1) {
        return (arg0 ^ arg1) * arg0 + arg1 ^ arg0;
    }

}