// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.RemoteInvocationHandler
package org.freedesktop.dbus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import org.freedesktop.dbus.Marshalling;
import org.freedesktop.dbus.RemoteObject;
import org.freedesktop.dbus.annotations.MethodNoReply;
import org.freedesktop.dbus.connections.AbstractConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.exceptions.DBusExecutionException;
import org.freedesktop.dbus.interfaces.CallbackHandler;
import org.freedesktop.dbus.messages.MessageFactory;
import org.freedesktop.dbus.messages.MethodCall;
import org.freedesktop.dbus.utils.DBusNamingUtil;
import org.freedesktop.dbus.utils.LoggingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteInvocationHandler implements InvocationHandler {

    // ---- поля ----
  public static final int CALL_TYPE_SYNC = 0;
  public static final int CALL_TYPE_ASYNC = 1;
  public static final int CALL_TYPE_CALLBACK = 2;
  private static final Logger LOGGER;
   AbstractConnection conn;
   RemoteObject remote;

    static {
        LOGGER = LoggerFactory.getLogger(RemoteInvocationHandler.class);
    }

  public RemoteInvocationHandler(AbstractConnection arg0, RemoteObject arg1) { // было: <init>
        super();
        remote = arg1;
        conn = arg0;
    }

  public RemoteObject getRemote() {
        return remote;
    }

  public Object invoke(Object arg0, Method arg1, Object[] arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_2
        //      1: invokevirtual  #94 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //      4: ldc  #22 // 'isRemote'
        //      6: invokevirtual  #87 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //      9: ifeq  17 (offset +8)
        //     12: iconst_1
        //     13: invokestatic  #73 // java.lang.Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //     16: areturn
        //     17: aload_2
        //     18: invokevirtual  #94 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //     21: ldc  #20 // 'getObjectPath'
        //     23: invokevirtual  #87 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     26: ifeq  37 (offset +11)
        //     29: aload_0
        //     30: getfield  #72 // org.freedesktop.dbus.RemoteInvocationHandler.remote:Lorg/freedesktop/dbus/RemoteObject;
        //     33: invokevirtual  #111 // org.freedesktop.dbus.RemoteObject.getObjectPath:()Ljava/lang/String;
        //     36: areturn
        //     37: aload_2
        //     38: invokevirtual  #94 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //     41: ldc  #16 // 'clone'
        //     43: invokevirtual  #87 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     46: ifeq  51 (offset +5)
        //     49: aconst_null
        //     50: areturn
        //     51: aload_2
        //     52: invokevirtual  #94 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //     55: ldc  #17 // 'equals'
        //     57: invokevirtual  #87 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //     60: ifeq  115 (offset +55)
        //     63: iconst_1
        //     64: aload_3
        //     65: arraylength
        //     66: if_icmpne  106 (offset +40)
        //     69: aload_3
        //     70: iconst_0
        //     71: aaload
        //     72: ifnull  101 (offset +29)
        //     75: aload_0
        //     76: getfield  #72 // org.freedesktop.dbus.RemoteInvocationHandler.remote:Lorg/freedesktop/dbus/RemoteObject;
        //     79: aload_3
        //     80: iconst_0
        //     81: aaload
        //     82: invokestatic  #97 // java.lang.reflect.Proxy.getInvocationHandler:(Ljava/lang/Object;)Ljava/lang/reflect/InvocationHandler;
        //     85: checkcast  #48 // org.freedesktop.dbus.RemoteInvocationHandler
        //     88: getfield  #72 // org.freedesktop.dbus.RemoteInvocationHandler.remote:Lorg/freedesktop/dbus/RemoteObject;
        //     91: invokevirtual  #108 // org.freedesktop.dbus.RemoteObject.equals:(Ljava/lang/Object;)Z
        //     94: ifeq  101 (offset +7)
        //     97: iconst_1
        //     98: goto  102 (offset +4)
        //    101: iconst_0
        //    102: invokestatic  #73 // java.lang.Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    105: areturn
        //    106: goto  463 (offset +357)
        //    109: astore  4
        //    111: getstatic  #68 // java.lang.Boolean.FALSE:Ljava/lang/Boolean;
        //    114: areturn
        //    115: aload_2
        //    116: invokevirtual  #94 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    119: ldc  #18 // 'finalize'
        //    121: invokevirtual  #87 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    124: ifeq  129 (offset +5)
        //    127: aconst_null
        //    128: areturn
        //    129: aload_2
        //    130: invokevirtual  #94 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    133: ldc  #19 // 'getClass'
        //    135: invokevirtual  #87 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    138: ifeq  144 (offset +6)
        //    141: ldc  #58 // org.freedesktop.dbus.interfaces.DBusInterface
        //    143: areturn
        //    144: aload_2
        //    145: invokevirtual  #94 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    148: ldc  #21 // 'hashCode'
        //    150: invokevirtual  #87 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    153: ifeq  167 (offset +14)
        //    156: aload_0
        //    157: getfield  #72 // org.freedesktop.dbus.RemoteInvocationHandler.remote:Lorg/freedesktop/dbus/RemoteObject;
        //    160: invokevirtual  #112 // org.freedesktop.dbus.RemoteObject.hashCode:()I
        //    163: invokestatic  #78 // java.lang.Integer.valueOf:(I)Ljava/lang/Integer;
        //    166: areturn
        //    167: aload_2
        //    168: invokevirtual  #94 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    171: ldc  #23 // 'notify'
        //    173: invokevirtual  #87 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    176: ifeq  210 (offset +34)
        //    179: aload_0
        //    180: getfield  #72 // org.freedesktop.dbus.RemoteInvocationHandler.remote:Lorg/freedesktop/dbus/RemoteObject;
        //    183: dup
        //    184: astore  4
        //    186: monitorenter
        //    187: aload_0
        //    188: getfield  #72 // org.freedesktop.dbus.RemoteInvocationHandler.remote:Lorg/freedesktop/dbus/RemoteObject;
        //    191: invokevirtual  #82 // java.lang.Object.notify:()V
        //    194: aload  4
        //    196: monitorexit
        //    197: goto  208 (offset +11)
        //    200: astore  5
        //    202: aload  4
        //    204: monitorexit
        //    205: aload  5
        //    207: athrow
        //    208: aconst_null
        //    209: areturn
        //    210: aload_2
        //    211: invokevirtual  #94 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    214: ldc  #24 // 'notifyAll'
        //    216: invokevirtual  #87 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    219: ifeq  253 (offset +34)
        //    222: aload_0
        //    223: getfield  #72 // org.freedesktop.dbus.RemoteInvocationHandler.remote:Lorg/freedesktop/dbus/RemoteObject;
        //    226: dup
        //    227: astore  4
        //    229: monitorenter
        //    230: aload_0
        //    231: getfield  #72 // org.freedesktop.dbus.RemoteInvocationHandler.remote:Lorg/freedesktop/dbus/RemoteObject;
        //    234: invokevirtual  #83 // java.lang.Object.notifyAll:()V
        //    237: aload  4
        //    239: monitorexit
        //    240: goto  251 (offset +11)
        //    243: astore  6
        //    245: aload  4
        //    247: monitorexit
        //    248: aload  6
        //    250: athrow
        //    251: aconst_null
        //    252: areturn
        //    253: aload_2
        //    254: invokevirtual  #94 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    257: ldc  #26 // 'wait'
        //    259: invokevirtual  #87 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    262: ifeq  420 (offset +158)
        //    265: aload_0
        //    266: getfield  #72 // org.freedesktop.dbus.RemoteInvocationHandler.remote:Lorg/freedesktop/dbus/RemoteObject;
        //    269: dup
        //    270: astore  4
        //    272: monitorenter
        //    273: aload_3
        //    274: arraylength
        //    275: ifne  288 (offset +13)
        //    278: aload_0
        //    279: getfield  #72 // org.freedesktop.dbus.RemoteInvocationHandler.remote:Lorg/freedesktop/dbus/RemoteObject;
        //    282: invokevirtual  #84 // java.lang.Object.wait:()V
        //    285: goto  392 (offset +107)
        //    288: aload_3
        //    289: arraylength
        //    290: iconst_1
        //    291: if_icmpne  329 (offset +38)
        //    294: aload_3
        //    295: iconst_0
        //    296: aaload
        //    297: astore  8
        //    299: aload  8
        //    301: instanceof  #32 // java.lang.Long
        //    304: ifeq  329 (offset +25)
        //    307: aload  8
        //    309: checkcast  #32 // java.lang.Long
        //    312: astore  5
        //    314: aload_0
        //    315: getfield  #72 // org.freedesktop.dbus.RemoteInvocationHandler.remote:Lorg/freedesktop/dbus/RemoteObject;
        //    318: aload  5
        //    320: invokevirtual  #79 // java.lang.Long.longValue:()J
        //    323: invokevirtual  #85 // java.lang.Object.wait:(J)V
        //    326: goto  392 (offset +66)
        //    329: aload_3
        //    330: arraylength
        //    331: iconst_2
        //    332: if_icmpne  392 (offset +60)
        //    335: aload_3
        //    336: iconst_0
        //    337: aaload
        //    338: astore  8
        //    340: aload  8
        //    342: instanceof  #32 // java.lang.Long
        //    345: ifeq  392 (offset +47)
        //    348: aload  8
        //    350: checkcast  #32 // java.lang.Long
        //    353: astore  6
        //    355: aload_3
        //    356: iconst_1
        //    357: aaload
        //    358: astore  8
        //    360: aload  8
        //    362: instanceof  #31 // java.lang.Integer
        //    365: ifeq  392 (offset +27)
        //    368: aload  8
        //    370: checkcast  #31 // java.lang.Integer
        //    373: astore  7
        //    375: aload_0
        //    376: getfield  #72 // org.freedesktop.dbus.RemoteInvocationHandler.remote:Lorg/freedesktop/dbus/RemoteObject;
        //    379: aload  6
        //    381: invokevirtual  #79 // java.lang.Long.longValue:()J
        //    384: aload  7
        //    386: invokevirtual  #77 // java.lang.Integer.intValue:()I
        //    389: invokevirtual  #86 // java.lang.Object.wait:(JI)V
        //    392: aload_3
        //    393: arraylength
        //    394: iconst_2
        //    395: if_icmpgt  403 (offset +8)
        //    398: aconst_null
        //    399: aload  4
        //    401: monitorexit
        //    402: areturn
        //    403: aload  4
        //    405: monitorexit
        //    406: goto  417 (offset +11)
        //    409: astore  9
        //    411: aload  4
        //    413: monitorexit
        //    414: aload  9
        //    416: athrow
        //    417: goto  463 (offset +46)
        //    420: aload_2
        //    421: invokevirtual  #94 // java.lang.reflect.Method.getName:()Ljava/lang/String;
        //    424: ldc  #25 // 'toString'
        //    426: invokevirtual  #87 // java.lang.String.equals:(Ljava/lang/Object;)Z
        //    429: ifeq  440 (offset +11)
        //    432: aload_0
        //    433: getfield  #72 // org.freedesktop.dbus.RemoteInvocationHandler.remote:Lorg/freedesktop/dbus/RemoteObject;
        //    436: invokevirtual  #114 // org.freedesktop.dbus.RemoteObject.toString:()Ljava/lang/String;
        //    439: areturn
        //    440: aload_2
        //    441: ldc  #51 // org.freedesktop.dbus.annotations.DBusBoundProperty
        //    443: invokevirtual  #96 // java.lang.reflect.Method.isAnnotationPresent:(Ljava/lang/Class;)Z
        //    446: ifeq  463 (offset +17)
        //    449: aload_0
        //    450: getfield  #71 // org.freedesktop.dbus.RemoteInvocationHandler.conn:Lorg/freedesktop/dbus/connections/AbstractConnection;
        //    453: aload_0
        //    454: getfield  #72 // org.freedesktop.dbus.RemoteInvocationHandler.remote:Lorg/freedesktop/dbus/RemoteObject;
        //    457: aload_2
        //    458: aload_3
        //    459: invokestatic  #130 // org.freedesktop.dbus.propertyref.PropRefRemoteHandler.handleDBusBoundProperty:(Lorg/freedesktop/dbus/connections/AbstractConnection;Lorg/freedesktop/dbus/RemoteObject;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;
        //    462: areturn
        //    463: aload_0
        //    464: getfield  #72 // org.freedesktop.dbus.RemoteInvocationHandler.remote:Lorg/freedesktop/dbus/RemoteObject;
        //    467: aload_2
        //    468: aload_0
        //    469: getfield  #71 // org.freedesktop.dbus.RemoteInvocationHandler.conn:Lorg/freedesktop/dbus/connections/AbstractConnection;
        //    472: iconst_0
        //    473: aconst_null
        //    474: aload_3
        //    475: invokestatic  #104 // org.freedesktop.dbus.RemoteInvocationHandler.executeRemoteMethod:(Lorg/freedesktop/dbus/RemoteObject;Ljava/lang/reflect/Method;Lorg/freedesktop/dbus/connections/AbstractConnection;ILorg/freedesktop/dbus/interfaces/CallbackHandler;[Ljava/lang/Object;)Ljava/lang/Object;
        //    478: areturn
        //       Exception table:
        //         from 63 to 105 target 109 type java.lang.IllegalArgumentException
        //         from 187 to 197 target 200 type any
        //         from 200 to 205 target 200 type any
        //         from 230 to 240 target 243 type any
        //         from 243 to 248 target 243 type any
        //         from 273 to 402 target 409 type any
        //         from 403 to 406 target 409 type any
        //         from 409 to 414 target 409 type any
    }

  public static Object convertRV(Object[] arg0, Method arg1, AbstractConnection arg2) {
        return convertRV(arg0, new Type[]{arg1.getGenericReturnType()}, arg1, arg2);
    }

  public static Object convertRV(Object[] arg0, Type[] arg1, Method arg2, AbstractConnection arg3) {
        Class var4 = arg2.getReturnType();
        Object[] var5 = arg0;
        if (var5 == null) {
            if (null == var4) {
                return null;
            } else {
                if (!Void.TYPE.equals(var4)) {
                    throw new DBusException("Wrong return type (got void, expected a value)");
                } else {
                    return null;
                }
            }
        }
        try {
            LoggingHelper.logIf(LOGGER.isTraceEnabled(), () -> lambda$convertRV$0(arg0, arg2));
            var5 = Marshalling.deSerializeParameters(var5, arg1, arg3);
        } catch (Exception var6) {
            LOGGER.debug("Wrong return type.", var6);
            throw new DBusException(String.format("Wrong return type (failed to de-serialize correct types: %s )", new Object[]{var6.getMessage()}), var6);
        }
    }

  public static Object executeRemoteMethod(RemoteObject arg0, Method arg1, AbstractConnection arg2, int arg3, CallbackHandler arg4, Object[] arg5) {
        return executeRemoteMethod(arg0, arg1, new Type[]{arg1.getGenericReturnType()}, arg2, arg3, arg4, arg5);
    }

  public static Object executeRemoteMethod(RemoteObject arg0, Method arg1, String[] arg2, Type[] arg3, AbstractConnection arg4, int arg5, CallbackHandler arg6, Object[] arg7) {
        Type[] var8 = arg1.getGenericParameterTypes();
        Object var9 = null;
        Object[] var10 = arg7;
        if (var8.length <= 0) {
            int var12 = 0;
            if (!arg0.isAutostart()) {
                var12 = ((byte) (var12 | 2));
            }
            if (arg5 == 1) {
                var12 = ((byte) (var12 | 64));
            }
            if (arg1.isAnnotationPresent(MethodNoReply.class)) {
                var12 = ((byte) (var12 | 1));
            }
            try {
                String var13 = DBusNamingUtil.getMethodName(arg1);
                if (null != arg0.getInterface()) {
                    String var14 = DBusNamingUtil.getInterfaceName(arg0.getInterface());
                    MethodCall var11 = arg4.getMessageFactory().createMethodCall(null, arg0.getBusName(), arg0.getObjectPath(), var14, var13, var12, ((String) var9), var10);
                } else {
                    MethodCall var11 = arg4.getMessageFactory().createMethodCall(null, arg0.getBusName(), arg0.getObjectPath(), null, var13, var12, ((String) var9), var10);
                }
            } catch (DBusException e1) {
                Throwable var13 = e1;
                LOGGER.debug("Failed to construct outgoing method call.", var13);
                throw new DBusExecutionException("Failed to construct outgoing method call: " + var13.getMessage());
            }
        } else {
            try {
                var9 = Marshalling.getDBusType(var8);
                var10 = Marshalling.convertParameters(var10, var8, arg2, arg4);
            } catch (DBusException var11) {
                throw new DBusExecutionException("Failed to construct D-Bus type: " + var11.getMessage());
            }
        }
    }

  public static Object executeRemoteMethod(RemoteObject arg0, Method arg1, Type[] arg2, AbstractConnection arg3, int arg4, CallbackHandler arg5, Object[] arg6) {
        return executeRemoteMethod(arg0, arg1, null, arg2, arg3, arg4, arg5, arg6);
    }

  private static void lambda$convertRV$0(Object[] arg0, Method arg1) {
        LOGGER.trace("Converting return parameters from {} to type {}", Arrays.deepToString(arg0), arg1.getGenericReturnType());
    }

}