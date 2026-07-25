// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.bin.DBusDaemon.DBusServer
package org.freedesktop.dbus.bin;

import java.util.Map;
import java.util.Set;
import org.freedesktop.dbus.bin.DBusDaemon;
import org.freedesktop.dbus.bin.DBusDaemon_ConnectionStruct;
import org.freedesktop.dbus.connections.transports.TransportConnection;
import org.freedesktop.dbus.interfaces.DBus;
import org.freedesktop.dbus.interfaces.Introspectable;
import org.freedesktop.dbus.interfaces.Peer;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.messages.Message;
import org.freedesktop.dbus.messages.MessageFactory;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.utils.AddressBuilder;
import org.slf4j.Logger;

public class DBusDaemon_DBusServer implements DBus, Introspectable, Peer {

    // ---- поля ----
  private final String machineId;
  private DBusDaemon_ConnectionStruct connStruct;
  final DBusDaemon this$0;

  public DBusDaemon_DBusServer(DBusDaemon arg0) { // было: <init>
        super();
        this$0 = arg0;
        machineId = AddressBuilder.createMachineId();
    }

  private DBusSignal generateNameAcquiredSignal(TransportConnection arg0, String arg1) {
        return arg0.getMessageFactory().createSignal("org.freedesktop.DBus", "/org/freedesktop/DBus", "org.freedesktop.DBus", "NameAcquired", "s", new Object[]{arg1});
    }

  private DBusSignal generatedNameOwnerChangedSignal(TransportConnection arg0, String arg1, String arg2, String arg3) {
        return arg0.getMessageFactory().createSignal("org.freedesktop.DBus", "/org/freedesktop/DBus", "org.freedesktop.DBus", "NameOwnerChanged", "sss", new Object[]{arg1, arg2, arg3});
    }

  public boolean isRemote() {
        return false;
    }

  public String Hello() {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //      4: dup
        //      5: astore_1
        //      6: monitorenter
        //      7: aconst_null
        //      8: aload_0
        //      9: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //     12: getfield  #61 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //     15: if_acmpeq  28 (offset +13)
        //     18: new  #41 // org.freedesktop.dbus.errors.AccessDenied
        //     21: dup
        //     22: ldc  #9 // 'Connection has already sent a Hello message'
        //     24: invokespecial  #80 // org.freedesktop.dbus.errors.AccessDenied.<init>:(Ljava/lang/String;)V
        //     27: athrow
        //     28: aload_0
        //     29: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //     32: aload_0
        //     33: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //     36: getfield  #58 // org.freedesktop.dbus.bin.DBusDaemon.nextUnique:Ljava/util/concurrent/atomic/AtomicInteger;
        //     39: invokevirtual  #73 // java.util.concurrent.atomic.AtomicInteger.incrementAndGet:()I
        //     42: invokedynamic  #105 // invokedynamic makeConcatWithConstants:(I)Ljava/lang/String;
        //     47: putfield  #61 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //     50: aload_1
        //     51: monitorexit
        //     52: goto  60 (offset +8)
        //     55: astore_2
        //     56: aload_1
        //     57: monitorexit
        //     58: aload_2
        //     59: athrow
        //     60: aload_0
        //     61: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //     64: getfield  #57 // org.freedesktop.dbus.bin.DBusDaemon.names:Ljava/util/Map;
        //     67: aload_0
        //     68: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //     71: getfield  #61 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //     74: aload_0
        //     75: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //     78: invokeinterface  #97 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //     83: pop
        //     84: getstatic  #55 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //     87: ldc  #8 // 'Client {} registered'
        //     89: aload_0
        //     90: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //     93: getfield  #61 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //     96: invokeinterface  #101 // org.slf4j.Logger.info:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //    101: aload_0
        //    102: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //    105: aload_0
        //    106: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //    109: aload_0
        //    110: aload_0
        //    111: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //    114: getfield  #60 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.connection:Lorg/freedesktop/dbus/connections/transports/TransportConnection;
        //    117: aload_0
        //    118: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //    121: getfield  #61 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //    124: invokevirtual  #77 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.generateNameAcquiredSignal:(Lorg/freedesktop/dbus/connections/transports/TransportConnection;Ljava/lang/String;)Lorg/freedesktop/dbus/messages/DBusSignal;
        //    127: invokevirtual  #75 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;)V
        //    130: aload_0
        //    131: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //    134: aconst_null
        //    135: aload_0
        //    136: aload_0
        //    137: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //    140: getfield  #60 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.connection:Lorg/freedesktop/dbus/connections/transports/TransportConnection;
        //    143: aload_0
        //    144: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //    147: getfield  #61 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //    150: ldc  #1 // ''
        //    152: aload_0
        //    153: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //    156: getfield  #61 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //    159: invokevirtual  #78 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.generatedNameOwnerChangedSignal:(Lorg/freedesktop/dbus/connections/transports/TransportConnection;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/freedesktop/dbus/messages/DBusSignal;
        //    162: invokevirtual  #75 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;)V
        //    165: goto  180 (offset +15)
        //    168: astore_1
        //    169: getstatic  #55 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    172: ldc  #1 // ''
        //    174: aload_1
        //    175: invokeinterface  #100 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    180: aload_0
        //    181: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //    184: getfield  #61 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //    187: areturn
        //       Exception table:
        //         from 7 to 52 target 55 type any
        //         from 55 to 58 target 55 type any
        //         from 101 to 165 target 168 type org.freedesktop.dbus.exceptions.DBusException
    }

  public String[] ListNames() {
        Set var2 = this$0.names.keySet();
        String[] var1 = ((String[]) var2.toArray(new String[0]));
        return var1;
    }

  public boolean NameHasOwner(String arg0) {
        return this$0.names.containsKey(arg0);
    }

  public String GetNameOwner(String arg0) {
        DBusDaemon_ConnectionStruct var2 = ((DBusDaemon_ConnectionStruct) this$0.names.get(arg0));
        String var3;
        if (null != var2) {
            var3 = var2.unique;
        } else {
            var3 = "";
        }
        return var3;
    }

  public UInt32 GetConnectionUnixUser(String arg0) {
        return new UInt32(0L);
    }

  public UInt32 StartServiceByName(String arg0, UInt32 arg1) {
        return new UInt32(0L);
    }

  public UInt32 RequestName(String arg0, UInt32 arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iconst_0
        //      1: istore_3
        //      2: aload_0
        //      3: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //      6: getfield  #57 // org.freedesktop.dbus.bin.DBusDaemon.names:Ljava/util/Map;
        //      9: dup
        //     10: astore  4
        //     12: monitorenter
        //     13: aload_0
        //     14: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //     17: getfield  #57 // org.freedesktop.dbus.bin.DBusDaemon.names:Ljava/util/Map;
        //     20: aload_1
        //     21: invokeinterface  #94 // java.util.Map.containsKey:(Ljava/lang/Object;)Z, count 2
        //     26: dup
        //     27: istore_3
        //     28: ifne  49 (offset +21)
        //     31: aload_0
        //     32: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //     35: getfield  #57 // org.freedesktop.dbus.bin.DBusDaemon.names:Ljava/util/Map;
        //     38: aload_1
        //     39: aload_0
        //     40: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //     43: invokeinterface  #97 // java.util.Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //     48: pop
        //     49: aload  4
        //     51: monitorexit
        //     52: goto  63 (offset +11)
        //     55: astore  5
        //     57: aload  4
        //     59: monitorexit
        //     60: aload  5
        //     62: athrow
        //     63: iload_3
        //     64: ifeq  73 (offset +9)
        //     67: iconst_3
        //     68: istore  4
        //     70: goto  163 (offset +93)
        //     73: getstatic  #55 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //     76: ldc  #7 // 'Client {} acquired name {}'
        //     78: aload_0
        //     79: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //     82: getfield  #61 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //     85: aload_1
        //     86: invokeinterface  #102 // org.slf4j.Logger.info:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //     91: iconst_1
        //     92: istore  4
        //     94: aload_0
        //     95: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //     98: aload_0
        //     99: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //    102: aload_0
        //    103: aload_0
        //    104: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //    107: getfield  #60 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.connection:Lorg/freedesktop/dbus/connections/transports/TransportConnection;
        //    110: aload_1
        //    111: invokevirtual  #77 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.generateNameAcquiredSignal:(Lorg/freedesktop/dbus/connections/transports/TransportConnection;Ljava/lang/String;)Lorg/freedesktop/dbus/messages/DBusSignal;
        //    114: invokevirtual  #75 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;)V
        //    117: aload_0
        //    118: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //    121: aconst_null
        //    122: aload_0
        //    123: aload_0
        //    124: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //    127: getfield  #60 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.connection:Lorg/freedesktop/dbus/connections/transports/TransportConnection;
        //    130: aload_1
        //    131: ldc  #1 // ''
        //    133: aload_0
        //    134: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //    137: getfield  #61 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //    140: invokevirtual  #78 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.generatedNameOwnerChangedSignal:(Lorg/freedesktop/dbus/connections/transports/TransportConnection;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/freedesktop/dbus/messages/DBusSignal;
        //    143: invokevirtual  #75 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;)V
        //    146: goto  163 (offset +17)
        //    149: astore  5
        //    151: getstatic  #55 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    154: ldc  #1 // ''
        //    156: aload  5
        //    158: invokeinterface  #100 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    163: new  #52 // org.freedesktop.dbus.types.UInt32
        //    166: dup
        //    167: iload  4
        //    169: i2l
        //    170: invokespecial  #90 // org.freedesktop.dbus.types.UInt32.<init>:(J)V
        //    173: areturn
        //       Exception table:
        //         from 13 to 52 target 55 type any
        //         from 55 to 60 target 55 type any
        //         from 94 to 146 target 149 type org.freedesktop.dbus.exceptions.DBusException
    }

  public UInt32 ReleaseName(String arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iconst_0
        //      1: istore_2
        //      2: aload_0
        //      3: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //      6: getfield  #57 // org.freedesktop.dbus.bin.DBusDaemon.names:Ljava/util/Map;
        //      9: dup
        //     10: astore_3
        //     11: monitorenter
        //     12: aload_0
        //     13: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //     16: getfield  #57 // org.freedesktop.dbus.bin.DBusDaemon.names:Ljava/util/Map;
        //     19: aload_1
        //     20: invokeinterface  #94 // java.util.Map.containsKey:(Ljava/lang/Object;)Z, count 2
        //     25: ifeq  76 (offset +51)
        //     28: aload_0
        //     29: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //     32: getfield  #57 // org.freedesktop.dbus.bin.DBusDaemon.names:Ljava/util/Map;
        //     35: aload_1
        //     36: invokeinterface  #95 // java.util.Map.get:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     41: checkcast  #38 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct
        //     44: aload_0
        //     45: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //     48: invokevirtual  #67 // java.lang.Object.equals:(Ljava/lang/Object;)Z
        //     51: ifeq  76 (offset +25)
        //     54: aload_0
        //     55: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //     58: getfield  #57 // org.freedesktop.dbus.bin.DBusDaemon.names:Ljava/util/Map;
        //     61: aload_1
        //     62: invokeinterface  #98 // java.util.Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;, count 2
        //     67: ifnull  74 (offset +7)
        //     70: iconst_1
        //     71: goto  75 (offset +4)
        //     74: iconst_0
        //     75: istore_2
        //     76: aload_3
        //     77: monitorexit
        //     78: goto  88 (offset +10)
        //     81: astore  4
        //     83: aload_3
        //     84: monitorexit
        //     85: aload  4
        //     87: athrow
        //     88: iload_2
        //     89: ifne  97 (offset +8)
        //     92: iconst_2
        //     93: istore_3
        //     94: goto  182 (offset +88)
        //     97: getstatic  #55 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    100: ldc  #7 // 'Client {} acquired name {}'
        //    102: aload_0
        //    103: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //    106: getfield  #61 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //    109: aload_1
        //    110: invokeinterface  #102 // org.slf4j.Logger.info:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //    115: iconst_1
        //    116: istore_3
        //    117: aload_0
        //    118: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //    121: aload_0
        //    122: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //    125: new  #45 // org.freedesktop.dbus.interfaces.DBus$NameLost
        //    128: dup
        //    129: ldc  #2 // '/org/freedesktop/DBus'
        //    131: aload_1
        //    132: invokespecial  #81 // org.freedesktop.dbus.interfaces.DBus$NameLost.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //    135: invokevirtual  #75 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;)V
        //    138: aload_0
        //    139: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //    142: aconst_null
        //    143: new  #46 // org.freedesktop.dbus.interfaces.DBus$NameOwnerChanged
        //    146: dup
        //    147: ldc  #2 // '/org/freedesktop/DBus'
        //    149: aload_1
        //    150: aload_0
        //    151: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //    154: getfield  #61 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //    157: ldc  #1 // ''
        //    159: invokespecial  #82 // org.freedesktop.dbus.interfaces.DBus$NameOwnerChanged.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    162: invokevirtual  #75 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;)V
        //    165: goto  182 (offset +17)
        //    168: astore  4
        //    170: getstatic  #55 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    173: ldc  #1 // ''
        //    175: aload  4
        //    177: invokeinterface  #100 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    182: new  #52 // org.freedesktop.dbus.types.UInt32
        //    185: dup
        //    186: iload_3
        //    187: i2l
        //    188: invokespecial  #90 // org.freedesktop.dbus.types.UInt32.<init>:(J)V
        //    191: areturn
        //       Exception table:
        //         from 12 to 78 target 81 type any
        //         from 81 to 85 target 81 type any
        //         from 117 to 165 target 168 type org.freedesktop.dbus.exceptions.DBusException
    }

  public void AddMatch(String arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #55 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //      3: ldc  #5 // 'Adding match rule: {}'
        //      5: aload_1
        //      6: invokeinterface  #103 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;)V, count 3
        //     11: aload_0
        //     12: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //     15: getfield  #59 // org.freedesktop.dbus.bin.DBusDaemon.sigrecips:Ljava/util/List;
        //     18: dup
        //     19: astore_2
        //     20: monitorenter
        //     21: aload_0
        //     22: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //     25: getfield  #59 // org.freedesktop.dbus.bin.DBusDaemon.sigrecips:Ljava/util/List;
        //     28: aload_0
        //     29: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //     32: invokeinterface  #93 // java.util.List.contains:(Ljava/lang/Object;)Z, count 2
        //     37: ifne  57 (offset +20)
        //     40: aload_0
        //     41: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //     44: getfield  #59 // org.freedesktop.dbus.bin.DBusDaemon.sigrecips:Ljava/util/List;
        //     47: aload_0
        //     48: getfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //     51: invokeinterface  #92 // java.util.List.add:(Ljava/lang/Object;)Z, count 2
        //     56: pop
        //     57: aload_2
        //     58: monitorexit
        //     59: goto  67 (offset +8)
        //     62: astore_3
        //     63: aload_2
        //     64: monitorexit
        //     65: aload_3
        //     66: athrow
        //     67: return
        //       Exception table:
        //         from 21 to 59 target 62 type any
        //         from 62 to 65 target 62 type any
    }

  public void RemoveMatch(String arg0) {
        DBusDaemon.LOGGER.trace("Removing match rule: {}", arg0);
    }

  public String[] ListQueuedOwners(String arg0) {
        return new String[0];
    }

  public UInt32 GetConnectionUnixProcessID(String arg0) {
        return new UInt32(0L);
    }

  public Byte[] GetConnectionSELinuxSecurityContext(String arg0) {
        return new Byte[0];
    }

  private void handleMessage(DBusDaemon_ConnectionStruct arg0, Message arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: getstatic  #55 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //      3: ldc  #10 // 'Handling message {}  from {}'
        //      5: aload_2
        //      6: aload_1
        //      7: getfield  #61 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //     10: invokeinterface  #104 // org.slf4j.Logger.trace:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V, count 4
        //     15: aload_2
        //     16: instanceof  #51 // org.freedesktop.dbus.messages.MethodCall
        //     19: ifne  23 (offset +4)
        //     22: return
        //     23: aload_2
        //     24: invokevirtual  #84 // org.freedesktop.dbus.messages.Message.getParameters:()[Ljava/lang/Object;
        //     27: astore_3
        //     28: aload_3
        //     29: arraylength
        //     30: anewarray  #22 // java.lang.Class
        //     33: astore  4
        //     35: iconst_0
        //     36: istore  5
        //     38: iload  5
        //     40: aload  4
        //     42: arraylength
        //     43: if_icmpge  64 (offset +21)
        //     46: aload  4
        //     48: iload  5
        //     50: aload_3
        //     51: iload  5
        //     53: aaload
        //     54: invokevirtual  #68 // java.lang.Object.getClass:()Ljava/lang/Class;
        //     57: aastore
        //     58: iinc  5, 1
        //     61: goto  38 (offset -23)
        //     64: aconst_null
        //     65: astore  5
        //     67: aconst_null
        //     68: astore  6
        //     70: aload_1
        //     71: getfield  #60 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.connection:Lorg/freedesktop/dbus/connections/transports/TransportConnection;
        //     74: invokevirtual  #79 // org.freedesktop.dbus.connections.transports.TransportConnection.getMessageFactory:()Lorg/freedesktop/dbus/messages/MessageFactory;
        //     77: astore  7
        //     79: ldc  #39 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer
        //     81: aload_2
        //     82: invokevirtual  #83 // org.freedesktop.dbus.messages.Message.getName:()Ljava/lang/String;
        //     85: aload  4
        //     87: invokevirtual  #65 // java.lang.Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //     90: astore  5
        //     92: aload_0
        //     93: aload_1
        //     94: putfield  #62 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.connStruct:Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;
        //     97: aload  5
        //     99: aload_0
        //    100: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //    103: getfield  #56 // org.freedesktop.dbus.bin.DBusDaemon.dbusServer:Lorg/freedesktop/dbus/bin/DBusDaemon$DBusServer;
        //    106: aload_3
        //    107: invokevirtual  #72 // java.lang.reflect.Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    110: astore  6
        //    112: aconst_null
        //    113: aload  6
        //    115: if_acmpne  146 (offset +31)
        //    118: aload_0
        //    119: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //    122: aload_1
        //    123: aload  7
        //    125: ldc  #15 // 'org.freedesktop.DBus'
        //    127: aload_2
        //    128: checkcast  #51 // org.freedesktop.dbus.messages.MethodCall
        //    131: aconst_null
        //    132: iconst_0
        //    133: anewarray  #25 // java.lang.Object
        //    136: invokevirtual  #88 // org.freedesktop.dbus.messages.MessageFactory.createMethodReturn:(Ljava/lang/String;Lorg/freedesktop/dbus/messages/MethodCall;Ljava/lang/String;[Ljava/lang/Object;)Lorg/freedesktop/dbus/messages/MethodReturn;
        //    139: iconst_1
        //    140: invokevirtual  #76 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;Z)V
        //    143: goto  189 (offset +46)
        //    146: aload  5
        //    148: invokevirtual  #71 // java.lang.reflect.Method.getGenericReturnType:()Ljava/lang/reflect/Type;
        //    151: invokestatic  #74 // org.freedesktop.dbus.Marshalling.getDBusType:(Ljava/lang/reflect/Type;)[Ljava/lang/String;
        //    154: iconst_0
        //    155: aaload
        //    156: astore  8
        //    158: aload_0
        //    159: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //    162: aload_1
        //    163: aload  7
        //    165: ldc  #15 // 'org.freedesktop.DBus'
        //    167: aload_2
        //    168: checkcast  #51 // org.freedesktop.dbus.messages.MethodCall
        //    171: aload  8
        //    173: iconst_1
        //    174: anewarray  #25 // java.lang.Object
        //    177: dup
        //    178: iconst_0
        //    179: aload  6
        //    181: aastore
        //    182: invokevirtual  #88 // org.freedesktop.dbus.messages.MessageFactory.createMethodReturn:(Ljava/lang/String;Lorg/freedesktop/dbus/messages/MethodCall;Ljava/lang/String;[Ljava/lang/Object;)Lorg/freedesktop/dbus/messages/MethodReturn;
        //    185: iconst_1
        //    186: invokevirtual  #76 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;Z)V
        //    189: goto  322 (offset +133)
        //    192: astore  8
        //    194: getstatic  #55 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    197: ldc  #1 // ''
        //    199: aload  8
        //    201: invokeinterface  #100 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    206: aload_0
        //    207: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //    210: aload_1
        //    211: aload  7
        //    213: ldc  #15 // 'org.freedesktop.DBus'
        //    215: aload_2
        //    216: aload  8
        //    218: invokevirtual  #70 // java.lang.reflect.InvocationTargetException.getCause:()Ljava/lang/Throwable;
        //    221: invokevirtual  #87 // org.freedesktop.dbus.messages.MessageFactory.createError:(Ljava/lang/String;Lorg/freedesktop/dbus/messages/Message;Ljava/lang/Throwable;)Lorg/freedesktop/dbus/messages/Error;
        //    224: invokevirtual  #75 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;)V
        //    227: goto  322 (offset +95)
        //    230: astore  8
        //    232: getstatic  #55 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    235: ldc  #1 // ''
        //    237: aload  8
        //    239: invokeinterface  #100 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    244: aload_0
        //    245: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //    248: aload_1
        //    249: aload  7
        //    251: ldc  #15 // 'org.freedesktop.DBus'
        //    253: aload_2
        //    254: aload  8
        //    256: invokevirtual  #87 // org.freedesktop.dbus.messages.MessageFactory.createError:(Ljava/lang/String;Lorg/freedesktop/dbus/messages/Message;Ljava/lang/Throwable;)Lorg/freedesktop/dbus/messages/Error;
        //    259: invokevirtual  #75 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;)V
        //    262: goto  322 (offset +60)
        //    265: astore  8
        //    267: getstatic  #55 // org.freedesktop.dbus.bin.DBusDaemon.LOGGER:Lorg/slf4j/Logger;
        //    270: ldc  #1 // ''
        //    272: aload  8
        //    274: invokeinterface  #100 // org.slf4j.Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V, count 3
        //    279: aload_0
        //    280: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //    283: aload_1
        //    284: aload  7
        //    286: ldc  #15 // 'org.freedesktop.DBus'
        //    288: aload_1
        //    289: getfield  #61 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //    292: ldc  #16 // 'org.freedesktop.DBus.Error.GeneralError'
        //    294: aload_2
        //    295: invokevirtual  #85 // org.freedesktop.dbus.messages.Message.getSerial:()J
        //    298: ldc  #18 // 's'
        //    300: iconst_1
        //    301: anewarray  #25 // java.lang.Object
        //    304: dup
        //    305: iconst_0
        //    306: aload_2
        //    307: invokevirtual  #83 // org.freedesktop.dbus.messages.Message.getName:()Ljava/lang/String;
        //    310: invokedynamic  #106 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //    315: aastore
        //    316: invokevirtual  #86 // org.freedesktop.dbus.messages.MessageFactory.createError:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;[Ljava/lang/Object;)Lorg/freedesktop/dbus/messages/Error;
        //    319: invokevirtual  #75 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;)V
        //    322: goto  370 (offset +48)
        //    325: astore  8
        //    327: aload_0
        //    328: getfield  #64 // org.freedesktop.dbus.bin.DBusDaemon$DBusServer.this$0:Lorg/freedesktop/dbus/bin/DBusDaemon;
        //    331: aload_1
        //    332: aload  7
        //    334: ldc  #15 // 'org.freedesktop.DBus'
        //    336: aload_1
        //    337: getfield  #61 // org.freedesktop.dbus.bin.DBusDaemon$ConnectionStruct.unique:Ljava/lang/String;
        //    340: ldc  #17 // 'org.freedesktop.DBus.Error.UnknownMethod'
        //    342: aload_2
        //    343: invokevirtual  #85 // org.freedesktop.dbus.messages.Message.getSerial:()J
        //    346: ldc  #18 // 's'
        //    348: iconst_1
        //    349: anewarray  #25 // java.lang.Object
        //    352: dup
        //    353: iconst_0
        //    354: aload_2
        //    355: invokevirtual  #83 // org.freedesktop.dbus.messages.Message.getName:()Ljava/lang/String;
        //    358: invokedynamic  #107 // invokedynamic makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        //    363: aastore
        //    364: invokevirtual  #86 // org.freedesktop.dbus.messages.MessageFactory.createError:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;[Ljava/lang/Object;)Lorg/freedesktop/dbus/messages/Error;
        //    367: invokevirtual  #75 // org.freedesktop.dbus.bin.DBusDaemon.send:(Lorg/freedesktop/dbus/bin/DBusDaemon$ConnectionStruct;Lorg/freedesktop/dbus/messages/Message;)V
        //    370: return
        //       Exception table:
        //         from 92 to 189 target 192 type java.lang.reflect.InvocationTargetException
        //         from 92 to 189 target 230 type org.freedesktop.dbus.exceptions.DBusExecutionException
        //         from 92 to 189 target 265 type java.lang.Exception
        //         from 79 to 322 target 325 type java.lang.NoSuchMethodException
    }

  public String getObjectPath() {
        return null;
    }

  public String Introspect() {
        return "<!DOCTYPE node PUBLIC \"-//freedesktop//DTD D-BUS Object Introspection 1.0//EN\"\n\"http://www.freedesktop.org/standards/dbus/1.0/introspect.dtd\">\n<node>\n  <interface name=\"org.freedesktop.DBus.Introspectable\">\n    <method name=\"Introspect\">\n      <arg name=\"data\" direction=\"out\" type=\"s\"/>\n    </method>\n  </interface>\n  <interface name=\"org.freedesktop.DBus\">\n    <method name=\"RequestName\">\n      <arg direction=\"in\" type=\"s\"/>\n      <arg direction=\"in\" type=\"u\"/>\n      <arg direction=\"out\" type=\"u\"/>\n    </method>\n    <method name=\"ReleaseName\">\n      <arg direction=\"in\" type=\"s\"/>\n      <arg direction=\"out\" type=\"u\"/>\n    </method>\n    <method name=\"StartServiceByName\">\n      <arg direction=\"in\" type=\"s\"/>\n      <arg direction=\"in\" type=\"u\"/>\n      <arg direction=\"out\" type=\"u\"/>\n    </method>\n    <method name=\"Hello\">\n      <arg direction=\"out\" type=\"s\"/>\n    </method>\n    <method name=\"NameHasOwner\">\n      <arg direction=\"in\" type=\"s\"/>\n      <arg direction=\"out\" type=\"b\"/>\n    </method>\n    <method name=\"ListNames\">\n      <arg direction=\"out\" type=\"as\"/>\n    </method>\n    <method name=\"ListActivatableNames\">\n      <arg direction=\"out\" type=\"as\"/>\n    </method>\n    <method name=\"AddMatch\">\n      <arg direction=\"in\" type=\"s\"/>\n    </method>\n    <method name=\"RemoveMatch\">\n      <arg direction=\"in\" type=\"s\"/>\n    </method>\n    <method name=\"GetNameOwner\">\n      <arg direction=\"in\" type=\"s\"/>\n      <arg direction=\"out\" type=\"s\"/>\n    </method>\n    <method name=\"ListQueuedOwners\">\n      <arg direction=\"in\" type=\"s\"/>\n      <arg direction=\"out\" type=\"as\"/>\n    </method>\n    <method name=\"GetConnectionUnixUser\">\n      <arg direction=\"in\" type=\"s\"/>\n      <arg direction=\"out\" type=\"u\"/>\n    </method>\n    <method name=\"GetConnectionUnixProcessID\">\n      <arg direction=\"in\" type=\"s\"/>\n      <arg direction=\"out\" type=\"u\"/>\n    </method>\n    <method name=\"GetConnectionSELinuxSecurityContext\">\n      <arg direction=\"in\" type=\"s\"/>\n      <arg direction=\"out\" type=\"ay\"/>\n    </method>\n    <method name=\"ReloadConfig\">\n    </method>\n    <signal name=\"NameOwnerChanged\">\n      <arg type=\"s\"/>\n      <arg type=\"s\"/>\n      <arg type=\"s\"/>\n    </signal>\n    <signal name=\"NameLost\">\n      <arg type=\"s\"/>\n    </signal>\n    <signal name=\"NameAcquired\">\n      <arg type=\"s\"/>\n    </signal>\n  </interface>\n</node>";
    }

  public void Ping() {
        // (пустое тело)
    }

  public String[] ListActivatableNames() {
        return null;
    }

  public Map GetConnectionCredentials(String arg0) {
        return null;
    }

  public Byte[] GetAdtAuditSessionData(String arg0) {
        return null;
    }

  public void UpdateActivationEnvironment(Map[] arg0) {
        // (пустое тело)
    }

  public String GetId() {
        return null;
    }

  public String GetMachineId() {
        return machineId;
    }

}