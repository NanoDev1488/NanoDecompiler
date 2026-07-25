// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.SASL.Command
package org.freedesktop.dbus.connections;

import java.util.Arrays;
import org.freedesktop.dbus.connections.SASL_SaslCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SASL_Command {

    // ---- поля ----
  private final Logger logger;
  private SASL_SaslCommand command;
  private int mechs;
  private String data;
  private String response;

  public SASL_Command() { // было: <init>
        super();
        logger = LoggerFactory.getLogger(getClass());
    }

  public SASL_Command(String arg0) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokespecial  #49 // java.lang.Object.<init>:()V
        //      4: aload_0
        //      5: aload_0
        //      6: invokevirtual  #50 // java.lang.Object.getClass:()Ljava/lang/Class;
        //      9: invokestatic  #59 // org.slf4j.LoggerFactory.getLogger:(Ljava/lang/Class;)Lorg/slf4j/Logger;
        //     12: putfield  #36 // org.freedesktop.dbus.connections.SASL$Command.logger:Lorg/slf4j/Logger;
        //     15: aload_1
        //     16: ldc  #1 // ' '
        //     18: invokevirtual  #51 // java.lang.String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //     21: astore_2
        //     22: aload_0
        //     23: getfield  #36 // org.freedesktop.dbus.connections.SASL$Command.logger:Lorg/slf4j/Logger;
        //     26: invokeinterface  #60 // org.slf4j.Logger.isTraceEnabled:()Z, count 1
        //     31: aload_0
        //     32: aload_2
        //     33: invokedynamic  #62 // invokedynamic run:(Lorg/freedesktop/dbus/connections/SASL$Command;[Ljava/lang/String;)Ljava/lang/Runnable;
        //     38: invokestatic  #58 // org.freedesktop.dbus.utils.LoggingHelper.logIf:(ZLjava/lang/Runnable;)V
        //     41: iconst_0
        //     42: getstatic  #33 // org.freedesktop.dbus.connections.SASL.COL:Ljava/text/Collator;
        //     45: aload_2
        //     46: iconst_0
        //     47: aaload
        //     48: ldc  #16 // 'OK'
        //     50: invokevirtual  #55 // java.text.Collator.compare:(Ljava/lang/String;Ljava/lang/String;)I
        //     53: if_icmpne  73 (offset +20)
        //     56: aload_0
        //     57: getstatic  #46 // org.freedesktop.dbus.connections.SASL$SaslCommand.OK:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //     60: putfield  #34 // org.freedesktop.dbus.connections.SASL$Command.command:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //     63: aload_0
        //     64: aload_2
        //     65: iconst_1
        //     66: aaload
        //     67: putfield  #35 // org.freedesktop.dbus.connections.SASL$Command.data:Ljava/lang/String;
        //     70: goto  493 (offset +423)
        //     73: iconst_0
        //     74: getstatic  #33 // org.freedesktop.dbus.connections.SASL.COL:Ljava/text/Collator;
        //     77: aload_2
        //     78: iconst_0
        //     79: aaload
        //     80: ldc  #4 // 'AUTH'
        //     82: invokevirtual  #55 // java.text.Collator.compare:(Ljava/lang/String;Ljava/lang/String;)I
        //     85: if_icmpne  183 (offset +98)
        //     88: aload_0
        //     89: getstatic  #40 // org.freedesktop.dbus.connections.SASL$SaslCommand.AUTH:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //     92: putfield  #34 // org.freedesktop.dbus.connections.SASL$Command.command:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //     95: aload_2
        //     96: arraylength
        //     97: iconst_1
        //     98: if_icmple  167 (offset +69)
        //    101: iconst_0
        //    102: getstatic  #33 // org.freedesktop.dbus.connections.SASL.COL:Ljava/text/Collator;
        //    105: aload_2
        //    106: iconst_1
        //    107: aaload
        //    108: ldc  #13 // 'EXTERNAL'
        //    110: invokevirtual  #55 // java.text.Collator.compare:(Ljava/lang/String;Ljava/lang/String;)I
        //    113: if_icmpne  124 (offset +11)
        //    116: aload_0
        //    117: iconst_1
        //    118: putfield  #37 // org.freedesktop.dbus.connections.SASL$Command.mechs:I
        //    121: goto  167 (offset +46)
        //    124: iconst_0
        //    125: getstatic  #33 // org.freedesktop.dbus.connections.SASL.COL:Ljava/text/Collator;
        //    128: aload_2
        //    129: iconst_1
        //    130: aaload
        //    131: ldc  #11 // 'DBUS_COOKIE_SHA1'
        //    133: invokevirtual  #55 // java.text.Collator.compare:(Ljava/lang/String;Ljava/lang/String;)I
        //    136: if_icmpne  147 (offset +11)
        //    139: aload_0
        //    140: iconst_2
        //    141: putfield  #37 // org.freedesktop.dbus.connections.SASL$Command.mechs:I
        //    144: goto  167 (offset +23)
        //    147: iconst_0
        //    148: getstatic  #33 // org.freedesktop.dbus.connections.SASL.COL:Ljava/text/Collator;
        //    151: aload_2
        //    152: iconst_1
        //    153: aaload
        //    154: ldc  #3 // 'ANONYMOUS'
        //    156: invokevirtual  #55 // java.text.Collator.compare:(Ljava/lang/String;Ljava/lang/String;)I
        //    159: if_icmpne  167 (offset +8)
        //    162: aload_0
        //    163: iconst_4
        //    164: putfield  #37 // org.freedesktop.dbus.connections.SASL$Command.mechs:I
        //    167: aload_2
        //    168: arraylength
        //    169: iconst_2
        //    170: if_icmple  493 (offset +323)
        //    173: aload_0
        //    174: aload_2
        //    175: iconst_2
        //    176: aaload
        //    177: putfield  #35 // org.freedesktop.dbus.connections.SASL$Command.data:Ljava/lang/String;
        //    180: goto  493 (offset +313)
        //    183: iconst_0
        //    184: getstatic  #33 // org.freedesktop.dbus.connections.SASL.COL:Ljava/text/Collator;
        //    187: aload_2
        //    188: iconst_0
        //    189: aaload
        //    190: ldc  #10 // 'DATA'
        //    192: invokevirtual  #55 // java.text.Collator.compare:(Ljava/lang/String;Ljava/lang/String;)I
        //    195: if_icmpne  225 (offset +30)
        //    198: aload_0
        //    199: getstatic  #43 // org.freedesktop.dbus.connections.SASL$SaslCommand.DATA:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    202: putfield  #34 // org.freedesktop.dbus.connections.SASL$Command.command:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    205: aload_0
        //    206: aload_2
        //    207: arraylength
        //    208: iconst_2
        //    209: if_icmpge  216 (offset +7)
        //    212: aconst_null
        //    213: goto  219 (offset +6)
        //    216: aload_2
        //    217: iconst_1
        //    218: aaload
        //    219: putfield  #35 // org.freedesktop.dbus.connections.SASL$Command.data:Ljava/lang/String;
        //    222: goto  493 (offset +271)
        //    225: iconst_0
        //    226: getstatic  #33 // org.freedesktop.dbus.connections.SASL.COL:Ljava/text/Collator;
        //    229: aload_2
        //    230: iconst_0
        //    231: aaload
        //    232: ldc  #17 // 'REJECTED'
        //    234: invokevirtual  #55 // java.text.Collator.compare:(Ljava/lang/String;Ljava/lang/String;)I
        //    237: if_icmpne  345 (offset +108)
        //    240: aload_0
        //    241: getstatic  #47 // org.freedesktop.dbus.connections.SASL$SaslCommand.REJECTED:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    244: putfield  #34 // org.freedesktop.dbus.connections.SASL$Command.command:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    247: iconst_1
        //    248: istore_3
        //    249: iload_3
        //    250: aload_2
        //    251: arraylength
        //    252: if_icmpge  342 (offset +90)
        //    255: iconst_0
        //    256: getstatic  #33 // org.freedesktop.dbus.connections.SASL.COL:Ljava/text/Collator;
        //    259: aload_2
        //    260: iload_3
        //    261: aaload
        //    262: ldc  #13 // 'EXTERNAL'
        //    264: invokevirtual  #55 // java.text.Collator.compare:(Ljava/lang/String;Ljava/lang/String;)I
        //    267: if_icmpne  283 (offset +16)
        //    270: aload_0
        //    271: dup
        //    272: getfield  #37 // org.freedesktop.dbus.connections.SASL$Command.mechs:I
        //    275: iconst_1
        //    276: ior
        //    277: putfield  #37 // org.freedesktop.dbus.connections.SASL$Command.mechs:I
        //    280: goto  336 (offset +56)
        //    283: iconst_0
        //    284: getstatic  #33 // org.freedesktop.dbus.connections.SASL.COL:Ljava/text/Collator;
        //    287: aload_2
        //    288: iload_3
        //    289: aaload
        //    290: ldc  #11 // 'DBUS_COOKIE_SHA1'
        //    292: invokevirtual  #55 // java.text.Collator.compare:(Ljava/lang/String;Ljava/lang/String;)I
        //    295: if_icmpne  311 (offset +16)
        //    298: aload_0
        //    299: dup
        //    300: getfield  #37 // org.freedesktop.dbus.connections.SASL$Command.mechs:I
        //    303: iconst_2
        //    304: ior
        //    305: putfield  #37 // org.freedesktop.dbus.connections.SASL$Command.mechs:I
        //    308: goto  336 (offset +28)
        //    311: iconst_0
        //    312: getstatic  #33 // org.freedesktop.dbus.connections.SASL.COL:Ljava/text/Collator;
        //    315: aload_2
        //    316: iload_3
        //    317: aaload
        //    318: ldc  #3 // 'ANONYMOUS'
        //    320: invokevirtual  #55 // java.text.Collator.compare:(Ljava/lang/String;Ljava/lang/String;)I
        //    323: if_icmpne  336 (offset +13)
        //    326: aload_0
        //    327: dup
        //    328: getfield  #37 // org.freedesktop.dbus.connections.SASL$Command.mechs:I
        //    331: iconst_4
        //    332: ior
        //    333: putfield  #37 // org.freedesktop.dbus.connections.SASL$Command.mechs:I
        //    336: iinc  3, 1
        //    339: goto  249 (offset -90)
        //    342: goto  493 (offset +151)
        //    345: iconst_0
        //    346: getstatic  #33 // org.freedesktop.dbus.connections.SASL.COL:Ljava/text/Collator;
        //    349: aload_2
        //    350: iconst_0
        //    351: aaload
        //    352: ldc  #5 // 'BEGIN'
        //    354: invokevirtual  #55 // java.text.Collator.compare:(Ljava/lang/String;Ljava/lang/String;)I
        //    357: if_icmpne  370 (offset +13)
        //    360: aload_0
        //    361: getstatic  #41 // org.freedesktop.dbus.connections.SASL$SaslCommand.BEGIN:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    364: putfield  #34 // org.freedesktop.dbus.connections.SASL$Command.command:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    367: goto  493 (offset +126)
        //    370: iconst_0
        //    371: getstatic  #33 // org.freedesktop.dbus.connections.SASL.COL:Ljava/text/Collator;
        //    374: aload_2
        //    375: iconst_0
        //    376: aaload
        //    377: ldc  #6 // 'CANCEL'
        //    379: invokevirtual  #55 // java.text.Collator.compare:(Ljava/lang/String;Ljava/lang/String;)I
        //    382: if_icmpne  395 (offset +13)
        //    385: aload_0
        //    386: getstatic  #42 // org.freedesktop.dbus.connections.SASL$SaslCommand.CANCEL:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    389: putfield  #34 // org.freedesktop.dbus.connections.SASL$Command.command:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    392: goto  493 (offset +101)
        //    395: iconst_0
        //    396: getstatic  #33 // org.freedesktop.dbus.connections.SASL.COL:Ljava/text/Collator;
        //    399: aload_2
        //    400: iconst_0
        //    401: aaload
        //    402: ldc  #12 // 'ERROR'
        //    404: invokevirtual  #55 // java.text.Collator.compare:(Ljava/lang/String;Ljava/lang/String;)I
        //    407: if_icmpne  427 (offset +20)
        //    410: aload_0
        //    411: getstatic  #44 // org.freedesktop.dbus.connections.SASL$SaslCommand.ERROR:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    414: putfield  #34 // org.freedesktop.dbus.connections.SASL$Command.command:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    417: aload_0
        //    418: aload_2
        //    419: iconst_1
        //    420: aaload
        //    421: putfield  #35 // org.freedesktop.dbus.connections.SASL$Command.data:Ljava/lang/String;
        //    424: goto  493 (offset +69)
        //    427: iconst_0
        //    428: getstatic  #33 // org.freedesktop.dbus.connections.SASL.COL:Ljava/text/Collator;
        //    431: aload_2
        //    432: iconst_0
        //    433: aaload
        //    434: ldc  #15 // 'NEGOTIATE_UNIX_FD'
        //    436: invokevirtual  #55 // java.text.Collator.compare:(Ljava/lang/String;Ljava/lang/String;)I
        //    439: if_icmpne  452 (offset +13)
        //    442: aload_0
        //    443: getstatic  #45 // org.freedesktop.dbus.connections.SASL$SaslCommand.NEGOTIATE_UNIX_FD:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    446: putfield  #34 // org.freedesktop.dbus.connections.SASL$Command.command:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    449: goto  493 (offset +44)
        //    452: iconst_0
        //    453: getstatic  #33 // org.freedesktop.dbus.connections.SASL.COL:Ljava/text/Collator;
        //    456: aload_2
        //    457: iconst_0
        //    458: aaload
        //    459: ldc  #2 // 'AGREE_UNIX_FD'
        //    461: invokevirtual  #55 // java.text.Collator.compare:(Ljava/lang/String;Ljava/lang/String;)I
        //    464: if_icmpne  477 (offset +13)
        //    467: aload_0
        //    468: getstatic  #39 // org.freedesktop.dbus.connections.SASL$SaslCommand.AGREE_UNIX_FD:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    471: putfield  #34 // org.freedesktop.dbus.connections.SASL$Command.command:Lorg/freedesktop/dbus/connections/SASL$SaslCommand;
        //    474: goto  493 (offset +19)
        //    477: new  #18 // java.io.IOException
        //    480: dup
        //    481: aload_2
        //    482: iconst_0
        //    483: aaload
        //    484: invokedynamic  #63 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //    489: invokespecial  #48 // java.io.IOException.<init>:(Ljava/lang/String;)V
        //    492: athrow
        //    493: aload_0
        //    494: getfield  #36 // org.freedesktop.dbus.connections.SASL$Command.logger:Lorg/slf4j/Logger;
        //    497: ldc  #8 // 'Created command: {}'
        //    499: aload_0
        //    500: invokeinterface  #61 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    505: return
    }

  public SASL_SaslCommand getCommand() {
        return command;
    }

  public int getMechs() {
        return mechs;
    }

  public String getData() {
        return data;
    }

  public String getResponse() {
        return response;
    }

  public void setResponse(String arg0) {
        response = arg0;
    }

  public String toString() {
        return "Command(" + String.valueOf(command) + ", " + mechs + ", " + data + ")";
    }

  private void lambda$new$0(String[] arg0) {
        logger.trace("Creating command from: {}", Arrays.toString(arg0));
    }

}