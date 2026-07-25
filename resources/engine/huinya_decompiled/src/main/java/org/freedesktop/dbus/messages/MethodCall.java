// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.messages.MethodCall
package org.freedesktop.dbus.messages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.freedesktop.dbus.exceptions.MessageFormatException;
import org.freedesktop.dbus.messages.Message;
import org.freedesktop.dbus.messages.MethodBase;
import org.slf4j.Logger;

public class MethodCall extends MethodBase {

    // ---- поля ----
  private static long replyWaitTimeout;
   Message reply;

    static {
        replyWaitTimeout = Duration.ofSeconds(20L).toMillis();
    }

   MethodCall() { // было: <init>
        super();
        reply = null;
    }

  protected MethodCall(byte arg0, String arg1, String arg2, String arg3, String arg4, byte arg5, String arg6, Object[] arg7) { // было: <init>
        this(arg0, null, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }

  protected MethodCall(byte arg0, String arg1, String arg2, String arg3, String arg4, String arg5, byte arg6, String arg7, Object[] arg8) { // было: <init>
        super(arg0, 1, arg6);
        reply = null;
        if (null == arg5) {
            throw new MessageFormatException("Must specify destination, path and function name to MethodCalls.");
        } else {
            if (null != arg3) {
                Object[] var10 = getHeader();
                var10[1] = arg3;
                var10[3] = arg5;
                ArrayList var11 = new ArrayList();
                var11.add(createHeaderArgs(1, "o", arg3));
                if (null != arg1) {
                    var11.add(createHeaderArgs(7, "s", arg1));
                }
                if (null != arg2) {
                    var11.add(createHeaderArgs(6, "s", arg2));
                }
                if (null != arg4) {
                    var11.add(createHeaderArgs(2, "s", arg4));
                }
                var11.add(createHeaderArgs(3, "s", arg5));
                if (null != arg7) {
                    logger.debug("Appending arguments with signature: {}", arg7);
                    var11.add(createHeaderArgs(8, "g", arg7));
                    setArgs(arg8);
                }
                appendFileDescriptors(var11, arg8);
                padAndMarshall(var11, getSerial(), arg7, arg8);
                return;
            } else {
                throw new MessageFormatException("Must specify destination, path and function name to MethodCalls.");
            }
        }
    }

  public static void setDefaultTimeout(long arg0) {
        replyWaitTimeout = arg0;
    }

  public synchronized boolean hasReply() {
        return null != reply;
    }

  public synchronized Message getReply(long arg0) {
        logger.trace("Blocking on {}", this);
        if (null == reply) {
            try {
                wait(arg0);
            } catch (InterruptedException var3) {
                Thread.currentThread().interrupt();
            }
        } else {
            return reply;
        }
        return reply;
    }

  public synchronized Message getReply() {
        return getReply(replyWaitTimeout);
    }

  public synchronized void setReply(Message arg0) {
        logger.trace("Setting reply to {} to {}", this, arg0);
        reply = arg0;
        notifyAll();
    }

}