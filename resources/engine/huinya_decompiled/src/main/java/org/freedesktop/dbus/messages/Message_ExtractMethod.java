// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.messages.Message.ExtractMethod
package org.freedesktop.dbus.messages;

@FunctionalInterface
interface Message_ExtractMethod {

  public abstract Object extractOne(byte[] arg0, byte[] arg1, int[] arg2, boolean arg3);

}