// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.messages.MessageFactory
package org.freedesktop.dbus.messages;

import java.util.List;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.messages.Error;
import org.freedesktop.dbus.messages.Message;
import org.freedesktop.dbus.messages.MethodCall;
import org.freedesktop.dbus.messages.MethodReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MessageFactory {

    // ---- поля ----
  private static final Logger LOGGER;
  private final byte endianess;

    static {
        LOGGER = LoggerFactory.getLogger(MessageFactory.class);
    }

  public MessageFactory(byte arg0) { // было: <init>
        super();
        endianess = arg0;
    }

  public byte getEndianess() {
        return endianess;
    }

  public DBusSignal createSignal(String arg0, String arg1, String arg2, String arg3, String arg4, Object[] arg5) {
        return new DBusSignal(endianess, arg0, arg1, arg2, arg3, arg4, arg5);
    }

  public DBusSignal createSignal(String arg0, Object[] arg1) {
        DBusSignal var3 = new DBusSignal(arg0, arg1);
        var3.updateEndianess(endianess);
        return var3;
    }

  public MethodCall createMethodCall(String arg0, String arg1, String arg2, String arg3, String arg4, byte arg5, String arg6, Object[] arg7) {
        return new MethodCall(endianess, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }

  public MethodCall createMethodCall(String arg0, String arg1, String arg2, String arg3, byte arg4, String arg5, Object[] arg6) {
        return new MethodCall(endianess, arg0, arg1, arg2, arg3, arg4, arg5, arg6);
    }

  public MethodReturn createMethodReturn(MethodCall arg0, String arg1, Object[] arg2) {
        return new MethodReturn(arg0, arg1, arg2);
    }

  public MethodReturn createMethodReturn(String arg0, MethodCall arg1, String arg2, Object[] arg3) {
        return new MethodReturn(arg0, arg1, arg2, arg3);
    }

  public Error createError(Message arg0, Throwable arg1) {
        return new Error(endianess, arg0, arg1);
    }

  public Error createError(String arg0, Message arg1, Throwable arg2) {
        return new Error(endianess, arg0, arg1, arg2);
    }

  public Error createError(String arg0, String arg1, long arg2, String arg3, Object[] arg4) {
        return new Error(endianess, arg0, arg1, arg2, arg3, arg4);
    }

  public Error createError(String arg0, String arg1, String arg2, long arg3, String arg4, Object[] arg5) {
        return new Error(endianess, arg0, arg1, arg2, arg3, arg4, arg5);
    }

  public static Message createMessage(byte arg0, byte[] arg1, byte[] arg2, byte[] arg3, List arg4) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: iload_0
        //      1: tableswitch  default->72, 1->32, 2->42, 3->62, 4->52
        //     32: new  #10 // org.freedesktop.dbus.messages.MethodCall
        //     35: dup
        //     36: invokespecial  #31 // org.freedesktop.dbus.messages.MethodCall.<init>:()V
        //     39: goto  96 (offset +57)
        //     42: new  #11 // org.freedesktop.dbus.messages.MethodReturn
        //     45: dup
        //     46: invokespecial  #34 // org.freedesktop.dbus.messages.MethodReturn.<init>:()V
        //     49: goto  96 (offset +47)
        //     52: new  #6 // org.freedesktop.dbus.messages.DBusSignal
        //     55: dup
        //     56: invokespecial  #21 // org.freedesktop.dbus.messages.DBusSignal.<init>:()V
        //     59: goto  96 (offset +37)
        //     62: new  #7 // org.freedesktop.dbus.messages.Error
        //     65: dup
        //     66: invokespecial  #25 // org.freedesktop.dbus.messages.Error.<init>:()V
        //     69: goto  96 (offset +27)
        //     72: new  #5 // org.freedesktop.dbus.exceptions.MessageTypeException
        //     75: dup
        //     76: ldc  #1 // 'Message type %s unsupported'
        //     78: iconst_1
        //     79: anewarray  #3 // java.lang.Object
        //     82: dup
        //     83: iconst_0
        //     84: iload_0
        //     85: invokestatic  #17 // java.lang.Byte.valueOf:(B)Ljava/lang/Byte;
        //     88: aastore
        //     89: invokestatic  #19 // java.lang.String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //     92: invokespecial  #20 // org.freedesktop.dbus.exceptions.MessageTypeException.<init>:(Ljava/lang/String;)V
        //     95: athrow
        //     96: astore  5
        //     98: getstatic  #15 // org.freedesktop.dbus.messages.MessageFactory.LOGGER:Lorg/slf4j/Logger;
        //    101: invokeinterface  #39 // org.slf4j.Logger.isTraceEnabled:()Z, count 1
        //    106: ifeq  145 (offset +39)
        //    109: getstatic  #15 // org.freedesktop.dbus.messages.MessageFactory.LOGGER:Lorg/slf4j/Logger;
        //    112: aload_1
        //    113: invokestatic  #37 // org.freedesktop.dbus.utils.Hexdump.format:([B)Ljava/lang/String;
        //    116: invokeinterface  #40 // org.slf4j.Logger.trace:(Ljava/lang/String;)V, count 2
        //    121: getstatic  #15 // org.freedesktop.dbus.messages.MessageFactory.LOGGER:Lorg/slf4j/Logger;
        //    124: aload_2
        //    125: invokestatic  #37 // org.freedesktop.dbus.utils.Hexdump.format:([B)Ljava/lang/String;
        //    128: invokeinterface  #40 // org.slf4j.Logger.trace:(Ljava/lang/String;)V, count 2
        //    133: getstatic  #15 // org.freedesktop.dbus.messages.MessageFactory.LOGGER:Lorg/slf4j/Logger;
        //    136: aload_3
        //    137: invokestatic  #37 // org.freedesktop.dbus.utils.Hexdump.format:([B)Ljava/lang/String;
        //    140: invokeinterface  #40 // org.slf4j.Logger.trace:(Ljava/lang/String;)V, count 2
        //    145: aload  5
        //    147: aload_1
        //    148: aload_2
        //    149: aload_3
        //    150: aload  4
        //    152: invokevirtual  #30 // org.freedesktop.dbus.messages.Message.populate:([B[B[BLjava/util/List;)V
        //    155: aload  5
        //    157: areturn
    }

}