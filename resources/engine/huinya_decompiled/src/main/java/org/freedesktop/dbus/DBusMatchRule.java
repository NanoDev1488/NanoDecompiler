// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.DBusMatchRule
package org.freedesktop.dbus;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.exceptions.DBusExecutionException;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.messages.Message;

public class DBusMatchRule {

    // ---- поля ----
  private static final String MSG_TYPE_METHOD_REPLY = "method_reply";
  private static final String MSG_TYPE_METHOD_CALL = "method_call";
  private static final String MSG_TYPE_ERROR = "error";
  private static final String MSG_TYPE_SIGNAL = "signal";
  private static final Map SIGNALTYPEMAP;
  private static final List MATCHRULE_EQUALS_OPERATIONS;
  private static final List SIGNAL_EQUALS_OPERATIONS;
  private final String type;
  private final String iface;
  private final String member;
  private final String object;
  private final String source;

    static {
        SIGNALTYPEMAP = new ConcurrentHashMap();
        MATCHRULE_EQUALS_OPERATIONS = List.of(lp0 -> (((DBusMatchRule) lp0)).getInterface(), lp0 -> (((DBusMatchRule) lp0)).getMember(), lp0 -> (((DBusMatchRule) lp0)).getObject(), lp0 -> (((DBusMatchRule) lp0)).getSource());
        SIGNAL_EQUALS_OPERATIONS = List.of(lp0 -> (((Message) lp0)).getInterface(), lp0 -> (((Message) lp0)).getName(), lp0 -> (((Message) lp0)).getPath(), lp0 -> (((Message) lp0)).getSource());
    }

  public DBusMatchRule(String arg0, String arg1, String arg2) { // было: <init>
        this(arg0, arg1, arg2, null);
    }

  public DBusMatchRule(String arg0, String arg1, String arg2, String arg3) { // было: <init>
        super();
        type = arg0;
        iface = arg1;
        member = arg2;
        object = arg3;
        source = null;
    }

  public DBusMatchRule(DBusExecutionException arg0) { // было: <init>
        this(arg0.getClass());
    }

  public DBusMatchRule(Message arg0) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokespecial  #52 // java.lang.Object.<init>:()V
        //      4: aload_0
        //      5: aload_1
        //      6: invokevirtual  #77 // org.freedesktop.dbus.messages.Message.getInterface:()Ljava/lang/String;
        //      9: putfield  #45 // org.freedesktop.dbus.DBusMatchRule.iface:Ljava/lang/String;
        //     12: aload_0
        //     13: aconst_null
        //     14: putfield  #48 // org.freedesktop.dbus.DBusMatchRule.source:Ljava/lang/String;
        //     17: aload_0
        //     18: aconst_null
        //     19: putfield  #47 // org.freedesktop.dbus.DBusMatchRule.object:Ljava/lang/String;
        //     22: aload_0
        //     23: aload_1
        //     24: instanceof  #36 // org.freedesktop.dbus.messages.Error
        //     27: ifeq  34 (offset +7)
        //     30: aconst_null
        //     31: goto  38 (offset +7)
        //     34: aload_1
        //     35: invokevirtual  #78 // org.freedesktop.dbus.messages.Message.getName:()Ljava/lang/String;
        //     38: putfield  #46 // org.freedesktop.dbus.DBusMatchRule.member:Ljava/lang/String;
        //     41: aload_1
        //     42: instanceof  #35 // org.freedesktop.dbus.messages.DBusSignal
        //     45: ifeq  57 (offset +12)
        //     48: aload_0
        //     49: ldc  #17 // 'signal'
        //     51: putfield  #49 // org.freedesktop.dbus.DBusMatchRule.type:Ljava/lang/String;
        //     54: goto  110 (offset +56)
        //     57: aload_1
        //     58: instanceof  #36 // org.freedesktop.dbus.messages.Error
        //     61: ifeq  73 (offset +12)
        //     64: aload_0
        //     65: ldc  #10 // 'error'
        //     67: putfield  #49 // org.freedesktop.dbus.DBusMatchRule.type:Ljava/lang/String;
        //     70: goto  110 (offset +40)
        //     73: aload_1
        //     74: instanceof  #38 // org.freedesktop.dbus.messages.MethodCall
        //     77: ifeq  89 (offset +12)
        //     80: aload_0
        //     81: ldc  #13 // 'method_call'
        //     83: putfield  #49 // org.freedesktop.dbus.DBusMatchRule.type:Ljava/lang/String;
        //     86: goto  110 (offset +24)
        //     89: aload_1
        //     90: instanceof  #39 // org.freedesktop.dbus.messages.MethodReturn
        //     93: ifeq  105 (offset +12)
        //     96: aload_0
        //     97: ldc  #14 // 'method_reply'
        //     99: putfield  #49 // org.freedesktop.dbus.DBusMatchRule.type:Ljava/lang/String;
        //    102: goto  110 (offset +8)
        //    105: aload_0
        //    106: aconst_null
        //    107: putfield  #49 // org.freedesktop.dbus.DBusMatchRule.type:Ljava/lang/String;
        //    110: return
    }

  public DBusMatchRule(Class arg0, String arg1) { // было: <init>
        this(arg0, null, null, "method_call", arg1);
    }

   DBusMatchRule(Class arg0, String arg1, String arg2, String arg3, String arg4) { // было: <init>
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokespecial  #52 // java.lang.Object.<init>:()V
        //      4: ldc  #34 // org.freedesktop.dbus.interfaces.DBusInterface
        //      6: aload_1
        //      7: invokevirtual  #51 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //     10: ifeq  62 (offset +52)
        //     13: aload_0
        //     14: aload_1
        //     15: invokestatic  #81 // org.freedesktop.dbus.utils.DBusNamingUtil.getInterfaceName:(Ljava/lang/Class;)Ljava/lang/String;
        //     18: putfield  #45 // org.freedesktop.dbus.DBusMatchRule.iface:Ljava/lang/String;
        //     21: aload_0
        //     22: aload_0
        //     23: getfield  #45 // org.freedesktop.dbus.DBusMatchRule.iface:Ljava/lang/String;
        //     26: invokevirtual  #67 // org.freedesktop.dbus.DBusMatchRule.assertDBusInterface:(Ljava/lang/String;)V
        //     29: aload_0
        //     30: aload  5
        //     32: ifnull  40 (offset +8)
        //     35: aload  5
        //     37: goto  41 (offset +4)
        //     40: aconst_null
        //     41: putfield  #46 // org.freedesktop.dbus.DBusMatchRule.member:Ljava/lang/String;
        //     44: aload_0
        //     45: aload  4
        //     47: ifnull  55 (offset +8)
        //     50: aload  4
        //     52: goto  56 (offset +4)
        //     55: aconst_null
        //     56: putfield  #49 // org.freedesktop.dbus.DBusMatchRule.type:Ljava/lang/String;
        //     59: goto  253 (offset +194)
        //     62: ldc  #35 // org.freedesktop.dbus.messages.DBusSignal
        //     64: aload_1
        //     65: invokevirtual  #51 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //     68: ifeq  168 (offset +100)
        //     71: aconst_null
        //     72: aload_1
        //     73: invokevirtual  #50 // java.lang.Class.getEnclosingClass:()Ljava/lang/Class;
        //     76: if_acmpne  89 (offset +13)
        //     79: new  #32 // org.freedesktop.dbus.exceptions.DBusException
        //     82: dup
        //     83: ldc  #9 // 'Signals must be declared as a member of a class implementing DBusInterface which is the member of a package.'
        //     85: invokespecial  #72 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //     88: athrow
        //     89: aload_0
        //     90: aload_1
        //     91: invokevirtual  #50 // java.lang.Class.getEnclosingClass:()Ljava/lang/Class;
        //     94: invokestatic  #81 // org.freedesktop.dbus.utils.DBusNamingUtil.getInterfaceName:(Ljava/lang/Class;)Ljava/lang/String;
        //     97: putfield  #45 // org.freedesktop.dbus.DBusMatchRule.iface:Ljava/lang/String;
        //    100: aload_0
        //    101: aload_0
        //    102: getfield  #45 // org.freedesktop.dbus.DBusMatchRule.iface:Ljava/lang/String;
        //    105: invokevirtual  #67 // org.freedesktop.dbus.DBusMatchRule.assertDBusInterface:(Ljava/lang/String;)V
        //    108: aload_0
        //    109: aload  5
        //    111: ifnull  119 (offset +8)
        //    114: aload  5
        //    116: goto  123 (offset +7)
        //    119: aload_1
        //    120: invokestatic  #82 // org.freedesktop.dbus.utils.DBusNamingUtil.getSignalName:(Ljava/lang/Class;)Ljava/lang/String;
        //    123: putfield  #46 // org.freedesktop.dbus.DBusMatchRule.member:Ljava/lang/String;
        //    126: getstatic  #43 // org.freedesktop.dbus.DBusMatchRule.SIGNALTYPEMAP:Ljava/util/Map;
        //    129: aload_0
        //    130: getfield  #45 // org.freedesktop.dbus.DBusMatchRule.iface:Ljava/lang/String;
        //    133: aload_0
        //    134: getfield  #46 // org.freedesktop.dbus.DBusMatchRule.member:Ljava/lang/String;
        //    137: invokedynamic  #89 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    142: aload_1
        //    143: invokeinterface  #87 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //    148: pop
        //    149: aload_0
        //    150: aload  4
        //    152: ifnull  160 (offset +8)
        //    155: aload  4
        //    157: goto  162 (offset +5)
        //    160: ldc  #17 // 'signal'
        //    162: putfield  #49 // org.freedesktop.dbus.DBusMatchRule.type:Ljava/lang/String;
        //    165: goto  253 (offset +88)
        //    168: ldc  #36 // org.freedesktop.dbus.messages.Error
        //    170: aload_1
        //    171: invokevirtual  #51 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    174: ifne  186 (offset +12)
        //    177: ldc  #33 // org.freedesktop.dbus.exceptions.DBusExecutionException
        //    179: aload_1
        //    180: invokevirtual  #51 // java.lang.Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    183: ifeq  236 (offset +53)
        //    186: aload_0
        //    187: aload_1
        //    188: invokestatic  #81 // org.freedesktop.dbus.utils.DBusNamingUtil.getInterfaceName:(Ljava/lang/Class;)Ljava/lang/String;
        //    191: putfield  #45 // org.freedesktop.dbus.DBusMatchRule.iface:Ljava/lang/String;
        //    194: aload_0
        //    195: aload_0
        //    196: getfield  #45 // org.freedesktop.dbus.DBusMatchRule.iface:Ljava/lang/String;
        //    199: invokevirtual  #67 // org.freedesktop.dbus.DBusMatchRule.assertDBusInterface:(Ljava/lang/String;)V
        //    202: aload_0
        //    203: aload  5
        //    205: ifnull  213 (offset +8)
        //    208: aload  5
        //    210: goto  214 (offset +4)
        //    213: aconst_null
        //    214: putfield  #46 // org.freedesktop.dbus.DBusMatchRule.member:Ljava/lang/String;
        //    217: aload_0
        //    218: aload  4
        //    220: ifnull  228 (offset +8)
        //    223: aload  4
        //    225: goto  230 (offset +5)
        //    228: ldc  #10 // 'error'
        //    230: putfield  #49 // org.freedesktop.dbus.DBusMatchRule.type:Ljava/lang/String;
        //    233: goto  253 (offset +20)
        //    236: new  #32 // org.freedesktop.dbus.exceptions.DBusException
        //    239: dup
        //    240: aload_1
        //    241: invokestatic  #57 // java.lang.String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    244: invokedynamic  #90 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //    249: invokespecial  #72 // org.freedesktop.dbus.exceptions.DBusException.<init>:(Ljava/lang/String;)V
        //    252: athrow
        //    253: aload_0
        //    254: aload_2
        //    255: putfield  #48 // org.freedesktop.dbus.DBusMatchRule.source:Ljava/lang/String;
        //    258: aload_0
        //    259: aload_3
        //    260: putfield  #47 // org.freedesktop.dbus.DBusMatchRule.object:Ljava/lang/String;
        //    263: return
    }

  public DBusMatchRule(Class arg0, String arg1, String arg2) { // было: <init>
        this(arg0, arg1, arg2, null, null);
    }

  public DBusMatchRule(Class arg0) { // было: <init>
        this(arg0, null, null);
    }

  public static Class getCachedSignalType(String arg0) {
        return ((Class) SIGNALTYPEMAP.get(arg0));
    }

   void assertDBusInterface(String arg0) {
        if (arg0 == null) {
            throw new DBusException("DBusInterfaces must be defined in a package.");
        } else {
            if (arg0.isEmpty()) {
                throw new DBusException("DBusInterfaces must be defined in a package.");
            } else {
                if (arg0.startsWith(".")) {
                    throw new DBusException("DBusInterfaces must be defined in a package.");
                } else {
                    if (arg0.contains(".")) {
                        return;
                    } else {
                        throw new DBusException("DBusInterfaces must be defined in a package.");
                    }
                }
            }
        }
    }

  public boolean matches(DBusMatchRule arg0, boolean arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: ifnonnull  6 (offset +5)
        //      4: iconst_0
        //      5: ireturn
        //      6: iload_2
        //      7: ifeq  72 (offset +65)
        //     10: aload_1
        //     11: invokevirtual  #68 // org.freedesktop.dbus.DBusMatchRule.getInterface:()Ljava/lang/String;
        //     14: aload_0
        //     15: invokevirtual  #68 // org.freedesktop.dbus.DBusMatchRule.getInterface:()Ljava/lang/String;
        //     18: invokestatic  #83 // org.freedesktop.dbus.utils.Util.strEquals:(Ljava/lang/String;Ljava/lang/String;)Z
        //     21: ifeq  70 (offset +49)
        //     24: aload_1
        //     25: invokevirtual  #69 // org.freedesktop.dbus.DBusMatchRule.getMember:()Ljava/lang/String;
        //     28: aload_0
        //     29: invokevirtual  #69 // org.freedesktop.dbus.DBusMatchRule.getMember:()Ljava/lang/String;
        //     32: invokestatic  #83 // org.freedesktop.dbus.utils.Util.strEquals:(Ljava/lang/String;Ljava/lang/String;)Z
        //     35: ifeq  70 (offset +35)
        //     38: aload_1
        //     39: invokevirtual  #70 // org.freedesktop.dbus.DBusMatchRule.getObject:()Ljava/lang/String;
        //     42: aload_0
        //     43: invokevirtual  #70 // org.freedesktop.dbus.DBusMatchRule.getObject:()Ljava/lang/String;
        //     46: invokestatic  #83 // org.freedesktop.dbus.utils.Util.strEquals:(Ljava/lang/String;Ljava/lang/String;)Z
        //     49: ifeq  70 (offset +21)
        //     52: aload_1
        //     53: invokevirtual  #71 // org.freedesktop.dbus.DBusMatchRule.getSource:()Ljava/lang/String;
        //     56: aload_0
        //     57: invokevirtual  #71 // org.freedesktop.dbus.DBusMatchRule.getSource:()Ljava/lang/String;
        //     60: invokestatic  #83 // org.freedesktop.dbus.utils.Util.strEquals:(Ljava/lang/String;Ljava/lang/String;)Z
        //     63: ifeq  70 (offset +7)
        //     66: iconst_1
        //     67: goto  71 (offset +4)
        //     70: iconst_0
        //     71: ireturn
        //     72: iconst_4
        //     73: anewarray  #21 // java.lang.String
        //     76: dup
        //     77: iconst_0
        //     78: aload_0
        //     79: invokevirtual  #68 // org.freedesktop.dbus.DBusMatchRule.getInterface:()Ljava/lang/String;
        //     82: aastore
        //     83: dup
        //     84: iconst_1
        //     85: aload_0
        //     86: invokevirtual  #69 // org.freedesktop.dbus.DBusMatchRule.getMember:()Ljava/lang/String;
        //     89: aastore
        //     90: dup
        //     91: iconst_2
        //     92: aload_0
        //     93: invokevirtual  #70 // org.freedesktop.dbus.DBusMatchRule.getObject:()Ljava/lang/String;
        //     96: aastore
        //     97: dup
        //     98: iconst_3
        //     99: aload_0
        //    100: invokevirtual  #71 // org.freedesktop.dbus.DBusMatchRule.getSource:()Ljava/lang/String;
        //    103: aastore
        //    104: astore_3
        //    105: iconst_0
        //    106: istore  4
        //    108: iload  4
        //    110: aload_3
        //    111: arraylength
        //    112: if_icmpge  169 (offset +57)
        //    115: aload_3
        //    116: iload  4
        //    118: aaload
        //    119: ifnonnull  125 (offset +6)
        //    122: goto  163 (offset +41)
        //    125: getstatic  #42 // org.freedesktop.dbus.DBusMatchRule.MATCHRULE_EQUALS_OPERATIONS:Ljava/util/List;
        //    128: iload  4
        //    130: invokeinterface  #84 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //    135: checkcast  #30 // java.util.function.Function
        //    138: astore  5
        //    140: aload_3
        //    141: iload  4
        //    143: aaload
        //    144: aload  5
        //    146: aload_1
        //    147: invokeinterface  #88 // java.util.function.Function.apply:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    152: checkcast  #21 // java.lang.String
        //    155: invokestatic  #83 // org.freedesktop.dbus.utils.Util.strEquals:(Ljava/lang/String;Ljava/lang/String;)Z
        //    158: ifne  163 (offset +5)
        //    161: iconst_0
        //    162: ireturn
        //    163: iinc  4, 1
        //    166: goto  108 (offset -58)
        //    169: iconst_1
        //    170: ireturn
    }

  public boolean matches(DBusSignal arg0, boolean arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: ifnonnull  6 (offset +5)
        //      4: iconst_0
        //      5: ireturn
        //      6: iload_2
        //      7: ifeq  72 (offset +65)
        //     10: aload_1
        //     11: invokevirtual  #73 // org.freedesktop.dbus.messages.DBusSignal.getInterface:()Ljava/lang/String;
        //     14: aload_0
        //     15: invokevirtual  #68 // org.freedesktop.dbus.DBusMatchRule.getInterface:()Ljava/lang/String;
        //     18: invokestatic  #83 // org.freedesktop.dbus.utils.Util.strEquals:(Ljava/lang/String;Ljava/lang/String;)Z
        //     21: ifeq  70 (offset +49)
        //     24: aload_1
        //     25: invokevirtual  #74 // org.freedesktop.dbus.messages.DBusSignal.getName:()Ljava/lang/String;
        //     28: aload_0
        //     29: invokevirtual  #69 // org.freedesktop.dbus.DBusMatchRule.getMember:()Ljava/lang/String;
        //     32: invokestatic  #83 // org.freedesktop.dbus.utils.Util.strEquals:(Ljava/lang/String;Ljava/lang/String;)Z
        //     35: ifeq  70 (offset +35)
        //     38: aload_1
        //     39: invokevirtual  #75 // org.freedesktop.dbus.messages.DBusSignal.getPath:()Ljava/lang/String;
        //     42: aload_0
        //     43: invokevirtual  #70 // org.freedesktop.dbus.DBusMatchRule.getObject:()Ljava/lang/String;
        //     46: invokestatic  #83 // org.freedesktop.dbus.utils.Util.strEquals:(Ljava/lang/String;Ljava/lang/String;)Z
        //     49: ifeq  70 (offset +21)
        //     52: aload_1
        //     53: invokevirtual  #76 // org.freedesktop.dbus.messages.DBusSignal.getSource:()Ljava/lang/String;
        //     56: aload_0
        //     57: invokevirtual  #71 // org.freedesktop.dbus.DBusMatchRule.getSource:()Ljava/lang/String;
        //     60: invokestatic  #83 // org.freedesktop.dbus.utils.Util.strEquals:(Ljava/lang/String;Ljava/lang/String;)Z
        //     63: ifeq  70 (offset +7)
        //     66: iconst_1
        //     67: goto  71 (offset +4)
        //     70: iconst_0
        //     71: ireturn
        //     72: iconst_4
        //     73: anewarray  #21 // java.lang.String
        //     76: dup
        //     77: iconst_0
        //     78: aload_0
        //     79: invokevirtual  #68 // org.freedesktop.dbus.DBusMatchRule.getInterface:()Ljava/lang/String;
        //     82: aastore
        //     83: dup
        //     84: iconst_1
        //     85: aload_0
        //     86: invokevirtual  #69 // org.freedesktop.dbus.DBusMatchRule.getMember:()Ljava/lang/String;
        //     89: aastore
        //     90: dup
        //     91: iconst_2
        //     92: aload_0
        //     93: invokevirtual  #70 // org.freedesktop.dbus.DBusMatchRule.getObject:()Ljava/lang/String;
        //     96: aastore
        //     97: dup
        //     98: iconst_3
        //     99: aload_0
        //    100: invokevirtual  #71 // org.freedesktop.dbus.DBusMatchRule.getSource:()Ljava/lang/String;
        //    103: aastore
        //    104: astore_3
        //    105: iconst_0
        //    106: istore  4
        //    108: iload  4
        //    110: aload_3
        //    111: arraylength
        //    112: if_icmpge  169 (offset +57)
        //    115: aload_3
        //    116: iload  4
        //    118: aaload
        //    119: ifnonnull  125 (offset +6)
        //    122: goto  163 (offset +41)
        //    125: getstatic  #44 // org.freedesktop.dbus.DBusMatchRule.SIGNAL_EQUALS_OPERATIONS:Ljava/util/List;
        //    128: iload  4
        //    130: invokeinterface  #84 // java.util.List.get:(I)Ljava/lang/Object;, count 2
        //    135: checkcast  #30 // java.util.function.Function
        //    138: astore  5
        //    140: aload_3
        //    141: iload  4
        //    143: aaload
        //    144: aload  5
        //    146: aload_1
        //    147: invokeinterface  #88 // java.util.function.Function.apply:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //    152: checkcast  #21 // java.lang.String
        //    155: invokestatic  #83 // org.freedesktop.dbus.utils.Util.strEquals:(Ljava/lang/String;Ljava/lang/String;)Z
        //    158: ifne  163 (offset +5)
        //    161: iconst_0
        //    162: ireturn
        //    163: iinc  4, 1
        //    166: goto  108 (offset -58)
        //    169: iconst_1
        //    170: ireturn
    }

  public String toString() {
        String __stk1;
        String __stk2;
        String __stk3;
        String __stk4;
        Object var1 = null;
        if (null != type) {
            var1 = "type='" + type + "'";
        }
        if (null != member) {
            __stk1 = null != var1 ? var1 + ",member='" + member + "'" : "member='" + member + "'";
            var1 = __stk1;
        }
        if (null != iface) {
            __stk2 = null != var1 ? var1 + ",interface='" + iface + "'" : "interface='" + iface + "'";
            var1 = __stk2;
        }
        if (null != source) {
            __stk3 = null != var1 ? var1 + ",sender='" + source + "'" : "sender='" + source + "'";
            var1 = __stk3;
        }
        if (null != object) {
            __stk4 = null != var1 ? var1 + ",path='" + object + "'" : "path='" + object + "'";
            var1 = __stk4;
        }
        return ((String) var1);
    }

  public int hashCode() {
        return Objects.hash(new Object[]{iface, member, object, source, type});
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 instanceof DBusMatchRule) {
                DBusMatchRule var2 = ((DBusMatchRule) arg0);
                return !Objects.equals(iface, var2.iface) ? 0 : !Objects.equals(member, var2.member) ? 0 : !Objects.equals(object, var2.object) ? 0 : !Objects.equals(source, var2.source) ? 0 : Objects.equals(type, var2.type);
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

  public String getType() {
        return type;
    }

  public String getInterface() {
        return iface;
    }

  public String getMember() {
        return member;
    }

  public String getSource() {
        return source;
    }

  public String getObject() {
        return object;
    }

}