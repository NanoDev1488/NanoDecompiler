// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.messages.Error
package org.freedesktop.dbus.messages;

import java.util.ArrayList;
import java.util.List;
import org.freedesktop.dbus.exceptions.DBusExecutionException;
import org.freedesktop.dbus.exceptions.MessageFormatException;
import org.freedesktop.dbus.messages.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Error extends Message {

    // ---- поля ----
  private static final String DEFAULT_NULL_EXCEPTION_ERROR_MSG = "Unsupported NULL Exception";
  private static final Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(Error.class);
    }

  protected Error() { // было: <init>
        super();
    }

  protected Error(byte arg0, String arg1, String arg2, long arg3, String arg4, Object[] arg5) { // было: <init>
        this(arg0, null, arg1, arg2, arg3, arg4, arg5);
    }

  protected Error(byte arg0, String arg1, String arg2, String arg3, long arg4, String arg5, Object[] arg6) { // было: <init>
        super(arg0, 3, 0);
        if (null != arg3) {
            ArrayList var9 = new ArrayList();
            var9.add(createHeaderArgs(4, "s", arg3));
            var9.add(createHeaderArgs(5, "u", Long.valueOf(arg4)));
            if (null != arg1) {
                var9.add(createHeaderArgs(7, "s", arg1));
            }
            if (null != arg2) {
                var9.add(createHeaderArgs(6, "s", arg2));
            }
            if (null != arg5) {
                var9.add(createHeaderArgs(8, "g", arg5));
                setArgs(arg6);
            }
            padAndMarshall(var9, getSerial(), arg5, arg6);
            return;
        } else {
            throw new MessageFormatException("Must specify error name to Errors.");
        }
    }

  protected Error(byte arg0, String arg1, Message arg2, Throwable arg3) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: iload_1
        //      2: aload_2
        //      3: aload_3
        //      4: invokevirtual  #86 // org.freedesktop.dbus.messages.Message.getSource:()Ljava/lang/String;
        //      7: getstatic  #43 // org.freedesktop.dbus.connections.AbstractConnection.DOLLAR_PATTERN:Ljava/util/regex/Pattern;
        //     10: aload  4
        //     12: invokestatic  #64 // java.util.Optional.ofNullable:(Ljava/lang/Object;)Ljava/util/Optional;
        //     15: new  #14 // java.io.IOException
        //     18: dup
        //     19: ldc  #7 // 'Unsupported NULL Exception'
        //     21: invokespecial  #48 // java.io.IOException.<init>:(Ljava/lang/String;)V
        //     24: invokevirtual  #65 // java.util.Optional.orElse:(Ljava/lang/Object;)Ljava/lang/Object;
        //     27: checkcast  #21 // java.lang.Throwable
        //     30: invokevirtual  #54 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     33: invokevirtual  #51 // java.lang.Class.getName:()Ljava/lang/String;
        //     36: invokevirtual  #68 // java.util.regex.Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //     39: ldc  #3 // '.'
        //     41: invokevirtual  #67 // java.util.regex.Matcher.replaceAll:(Ljava/lang/String;)Ljava/lang/String;
        //     44: aload_3
        //     45: invokevirtual  #85 // org.freedesktop.dbus.messages.Message.getSerial:()J
        //     48: ldc  #12 // 's'
        //     50: iconst_1
        //     51: anewarray  #19 // java.lang.Object
        //     54: dup
        //     55: iconst_0
        //     56: aload  4
        //     58: ifnonnull  66 (offset +8)
        //     61: ldc  #7 // 'Unsupported NULL Exception'
        //     63: goto  71 (offset +8)
        //     66: aload  4
        //     68: invokevirtual  #58 // java.lang.Throwable.getMessage:()Ljava/lang/String;
        //     71: aastore
        //     72: invokespecial  #74 // org.freedesktop.dbus.messages.Error.<init>:(BLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;[Ljava/lang/Object;)V
        //     75: return
    }

  protected Error(byte arg0, Message arg1, Throwable arg2) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: iload_1
        //      2: aload_2
        //      3: invokevirtual  #86 // org.freedesktop.dbus.messages.Message.getSource:()Ljava/lang/String;
        //      6: getstatic  #43 // org.freedesktop.dbus.connections.AbstractConnection.DOLLAR_PATTERN:Ljava/util/regex/Pattern;
        //      9: aload_3
        //     10: invokestatic  #64 // java.util.Optional.ofNullable:(Ljava/lang/Object;)Ljava/util/Optional;
        //     13: new  #14 // java.io.IOException
        //     16: dup
        //     17: ldc  #7 // 'Unsupported NULL Exception'
        //     19: invokespecial  #48 // java.io.IOException.<init>:(Ljava/lang/String;)V
        //     22: invokevirtual  #65 // java.util.Optional.orElse:(Ljava/lang/Object;)Ljava/lang/Object;
        //     25: checkcast  #21 // java.lang.Throwable
        //     28: invokevirtual  #54 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     31: invokevirtual  #51 // java.lang.Class.getName:()Ljava/lang/String;
        //     34: invokevirtual  #68 // java.util.regex.Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //     37: ldc  #3 // '.'
        //     39: invokevirtual  #67 // java.util.regex.Matcher.replaceAll:(Ljava/lang/String;)Ljava/lang/String;
        //     42: aload_2
        //     43: invokevirtual  #85 // org.freedesktop.dbus.messages.Message.getSerial:()J
        //     46: ldc  #12 // 's'
        //     48: iconst_1
        //     49: anewarray  #19 // java.lang.Object
        //     52: dup
        //     53: iconst_0
        //     54: aload_3
        //     55: ifnonnull  63 (offset +8)
        //     58: ldc  #7 // 'Unsupported NULL Exception'
        //     60: goto  67 (offset +7)
        //     63: aload_3
        //     64: invokevirtual  #58 // java.lang.Throwable.getMessage:()Ljava/lang/String;
        //     67: aastore
        //     68: invokespecial  #73 // org.freedesktop.dbus.messages.Error.<init>:(BLjava/lang/String;Ljava/lang/String;JLjava/lang/String;[Ljava/lang/Object;)V
        //     71: return
    }

  private static Class createExceptionClass(String arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aconst_null
        //      1: astore_1
        //      2: aload_0
        //      3: astore_2
        //      4: aload_2
        //      5: ldc  #10 // 'org.freedesktop.DBus.Error.'
        //      7: invokevirtual  #56 // java.lang.String.startsWith:(Ljava/lang/String;)Z
        //     10: ifeq  22 (offset +12)
        //     13: aload_2
        //     14: ldc  #10 // 'org.freedesktop.DBus.Error.'
        //     16: ldc  #11 // 'org.freedesktop.dbus.errors.'
        //     18: invokevirtual  #55 // java.lang.String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //     21: astore_2
        //     22: aload_2
        //     23: invokestatic  #49 // java.lang.Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //     26: astore_1
        //     27: goto  43 (offset +16)
        //     30: astore_3
        //     31: getstatic  #44 // org.freedesktop.dbus.messages.Error.LOGGER:Lorg/slf4j/Logger;
        //     34: ldc  #5 // 'Could not find class for name {}'
        //     36: aload_2
        //     37: aload_3
        //     38: invokeinterface  #92 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //     43: getstatic  #46 // org.freedesktop.dbus.utils.CommonRegexPattern.EXCEPTION_EXTRACT_PATTERN:Ljava/util/regex/Pattern;
        //     46: aload_2
        //     47: invokevirtual  #68 // java.util.regex.Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //     50: ldc  #8 // '\\$$1'
        //     52: invokevirtual  #67 // java.util.regex.Matcher.replaceAll:(Ljava/lang/String;)Ljava/lang/String;
        //     55: astore_2
        //     56: aconst_null
        //     57: aload_1
        //     58: if_acmpne  74 (offset +16)
        //     61: getstatic  #47 // org.freedesktop.dbus.utils.CommonRegexPattern.EXCEPTION_PARTIAL_PATTERN:Ljava/util/regex/Pattern;
        //     64: aload_2
        //     65: invokevirtual  #68 // java.util.regex.Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //     68: invokevirtual  #66 // java.util.regex.Matcher.matches:()Z
        //     71: ifne  22 (offset -49)
        //     74: aload_1
        //     75: areturn
        //       Exception table:
        //         from 22 to 27 target 30 type java.lang.ClassNotFoundException
    }

  public DBusExecutionException getException() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #78 // org.freedesktop.dbus.messages.Error.getName:()Ljava/lang/String;
        //      4: invokestatic  #75 // org.freedesktop.dbus.messages.Error.createExceptionClass:(Ljava/lang/String;)Ljava/lang/Class;
        //      7: astore_1
        //      8: aconst_null
        //      9: aload_1
        //     10: if_acmpeq  22 (offset +12)
        //     13: ldc  #36 // org.freedesktop.dbus.exceptions.DBusExecutionException
        //     15: aload_1
        //     16: invokevirtual  #52 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //     19: ifne  25 (offset +6)
        //     22: ldc  #36 // org.freedesktop.dbus.exceptions.DBusExecutionException
        //     24: astore_1
        //     25: aload_1
        //     26: iconst_1
        //     27: anewarray  #15 // java.lang.Class
        //     30: dup
        //     31: iconst_0
        //     32: ldc  #20 // java.lang.String
        //     34: aastore
        //     35: invokevirtual  #50 // java.lang.Class.getConstructor:([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
        //     38: astore_2
        //     39: aload_0
        //     40: invokevirtual  #79 // org.freedesktop.dbus.messages.Error.getParameters:()[Ljava/lang/Object;
        //     43: astore  4
        //     45: aconst_null
        //     46: aload  4
        //     48: if_acmpeq  58 (offset +10)
        //     51: iconst_0
        //     52: aload  4
        //     54: arraylength
        //     55: if_icmpne  78 (offset +23)
        //     58: aload_2
        //     59: iconst_1
        //     60: anewarray  #19 // java.lang.Object
        //     63: dup
        //     64: iconst_0
        //     65: ldc  #1 // ''
        //     67: aastore
        //     68: invokevirtual  #60 // java.lang.reflect.Constructor.newInstance:([Ljava/lang/Object;)Ljava/lang/Object;
        //     71: checkcast  #36 // org.freedesktop.dbus.exceptions.DBusExecutionException
        //     74: astore_3
        //     75: goto  124 (offset +49)
        //     78: aload_2
        //     79: iconst_1
        //     80: anewarray  #19 // java.lang.Object
        //     83: dup
        //     84: iconst_0
        //     85: aload  4
        //     87: invokestatic  #62 // java.util.Arrays.stream:([Ljava/lang/Object;)Ljava/util/stream/Stream;
        //     90: invokedynamic  #94 // invokedynamic apply:()Ljava/util/function/Function;
        //     95: invokeinterface  #90 // java.util.stream.Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;, count 2
        //    100: ldc  #2 // ' '
        //    102: invokestatic  #69 // java.util.stream.Collectors.joining:(Ljava/lang/CharSequence;)Ljava/util/stream/Collector;
        //    105: invokeinterface  #89 // java.util.stream.Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;, count 2
        //    110: checkcast  #20 // java.lang.String
        //    113: invokevirtual  #57 // java.lang.String.trim:()Ljava/lang/String;
        //    116: aastore
        //    117: invokevirtual  #60 // java.lang.reflect.Constructor.newInstance:([Ljava/lang/Object;)Ljava/lang/Object;
        //    120: checkcast  #36 // org.freedesktop.dbus.exceptions.DBusExecutionException
        //    123: astore_3
        //    124: aload_3
        //    125: aload_0
        //    126: invokevirtual  #78 // org.freedesktop.dbus.messages.Error.getName:()Ljava/lang/String;
        //    129: invokevirtual  #71 // org.freedesktop.dbus.exceptions.DBusExecutionException.setType:(Ljava/lang/String;)V
        //    132: aload_3
        //    133: areturn
        //    134: astore_1
        //    135: aload_0
        //    136: getfield  #45 // org.freedesktop.dbus.messages.Error.logger:Lorg/slf4j/Logger;
        //    139: ldc  #1 // ''
        //    141: aload_1
        //    142: invokeinterface  #91 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    147: aconst_null
        //    148: astore_3
        //    149: aload_0
        //    150: invokevirtual  #79 // org.freedesktop.dbus.messages.Error.getParameters:()[Ljava/lang/Object;
        //    153: astore_3
        //    154: goto  171 (offset +17)
        //    157: astore  4
        //    159: getstatic  #44 // org.freedesktop.dbus.messages.Error.LOGGER:Lorg/slf4j/Logger;
        //    162: ldc  #4 // 'Cannot retrieve parameters'
        //    164: aload  4
        //    166: invokeinterface  #93 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    171: aconst_null
        //    172: aload_3
        //    173: if_acmpeq  182 (offset +9)
        //    176: iconst_0
        //    177: aload_3
        //    178: arraylength
        //    179: if_icmpne  195 (offset +16)
        //    182: new  #36 // org.freedesktop.dbus.exceptions.DBusExecutionException
        //    185: dup
        //    186: ldc  #1 // ''
        //    188: invokespecial  #70 // org.freedesktop.dbus.exceptions.DBusExecutionException.<init>:(Ljava/lang/String;)V
        //    191: astore_2
        //    192: goto  233 (offset +41)
        //    195: new  #36 // org.freedesktop.dbus.exceptions.DBusExecutionException
        //    198: dup
        //    199: aload_3
        //    200: invokestatic  #62 // java.util.Arrays.stream:([Ljava/lang/Object;)Ljava/util/stream/Stream;
        //    203: invokedynamic  #94 // invokedynamic apply:()Ljava/util/function/Function;
        //    208: invokeinterface  #90 // java.util.stream.Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;, count 2
        //    213: ldc  #2 // ' '
        //    215: invokestatic  #69 // java.util.stream.Collectors.joining:(Ljava/lang/CharSequence;)Ljava/util/stream/Collector;
        //    218: invokeinterface  #89 // java.util.stream.Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;, count 2
        //    223: checkcast  #20 // java.lang.String
        //    226: invokevirtual  #57 // java.lang.String.trim:()Ljava/lang/String;
        //    229: invokespecial  #70 // org.freedesktop.dbus.exceptions.DBusExecutionException.<init>:(Ljava/lang/String;)V
        //    232: astore_2
        //    233: aload_2
        //    234: aload_0
        //    235: invokevirtual  #78 // org.freedesktop.dbus.messages.Error.getName:()Ljava/lang/String;
        //    238: invokevirtual  #71 // org.freedesktop.dbus.exceptions.DBusExecutionException.setType:(Ljava/lang/String;)V
        //    241: aload_2
        //    242: areturn
        //       Exception table:
        //         from 0 to 133 target 134 type java.lang.Exception
        //         from 149 to 154 target 157 type java.lang.Exception
    }

  public void throwException() {
        throw getException();
    }

}