// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.FileDescriptor
package org.freedesktop.dbus;

import java.io.FileDescriptor;
import java.util.Optional;
import org.freedesktop.dbus.exceptions.MarshallingException;
import org.freedesktop.dbus.spi.message.ISocketProvider;
import org.freedesktop.dbus.utils.ReflectionFileDescriptorHelper;

public final class FileDescriptor {

    // ---- поля ----
  private final int fd;

  public FileDescriptor(int arg0) { // было: <init>
        super();
        fd = arg0;
    }

  public FileDescriptor toJavaFileDescriptor(ISocketProvider arg0) {
        if (arg0 == null) {
            return ((FileDescriptor) ReflectionFileDescriptorHelper.getInstance().flatMap(lp0 -> lambda$toJavaFileDescriptor$0(((ReflectionFileDescriptorHelper) lp0))).orElseThrow(() -> lambda$toJavaFileDescriptor$1()));
        } else {
            Optional var2 = arg0.createFileDescriptor(fd);
            if (!var2.isPresent()) {
                return ((FileDescriptor) ReflectionFileDescriptorHelper.getInstance().flatMap(lp0 -> lambda$toJavaFileDescriptor$0(((ReflectionFileDescriptorHelper) lp0))).orElseThrow(() -> lambda$toJavaFileDescriptor$1()));
            } else {
                return ((FileDescriptor) var2.get());
            }
        }
    }

  public int getIntFileDescriptor() {
        return fd;
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 == null) {
                return false;
            } else {
                if (getClass() == arg0.getClass()) {
                    org.freedesktop.dbus.FileDescriptor var2 = ((org.freedesktop.dbus.FileDescriptor) arg0);
                    return fd == var2.fd;
                } else {
                    return false;
                }
            }
        } else {
            return true;
        }
    }

  public int hashCode() {
        return fd;
    }

  public String toString() {
        return org.freedesktop.dbus.FileDescriptor.class.getSimpleName() + "[fd=" + fd + "]";
    }

  public static org.freedesktop.dbus.FileDescriptor fromJavaFileDescriptor(FileDescriptor arg0, ISocketProvider arg1) {
        if (arg1 == null) {
            return new org.freedesktop.dbus.FileDescriptor((((Integer) ReflectionFileDescriptorHelper.getInstance().flatMap(lp0 -> lambda$fromJavaFileDescriptor$2(arg0, ((ReflectionFileDescriptorHelper) lp0))).orElseThrow(() -> lambda$fromJavaFileDescriptor$3()))).intValue());
        } else {
            Optional var2 = arg1.getFileDescriptorValue(arg0);
            if (!var2.isPresent()) {
                return new org.freedesktop.dbus.FileDescriptor((((Integer) ReflectionFileDescriptorHelper.getInstance().flatMap(lp0 -> lambda$fromJavaFileDescriptor$2(arg0, ((ReflectionFileDescriptorHelper) lp0))).orElseThrow(() -> lambda$fromJavaFileDescriptor$3()))).intValue());
            } else {
                return new org.freedesktop.dbus.FileDescriptor((((Integer) var2.get())).intValue());
            }
        }
    }

  private static MarshallingException lambda$fromJavaFileDescriptor$3() {
        return new MarshallingException("Could not get FileDescriptor value");
    }

  private static Optional lambda$fromJavaFileDescriptor$2(FileDescriptor arg0, ReflectionFileDescriptorHelper arg1) {
        return arg1.getFileDescriptorValue(arg0);
    }

  private static MarshallingException lambda$toJavaFileDescriptor$1() {
        return new MarshallingException("Could not create new FileDescriptor instance");
    }

  private Optional lambda$toJavaFileDescriptor$0(ReflectionFileDescriptorHelper arg0) {
        return arg0.createFileDescriptor(fd);
    }

}