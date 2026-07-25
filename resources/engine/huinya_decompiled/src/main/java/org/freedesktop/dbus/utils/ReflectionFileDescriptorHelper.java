// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.utils.ReflectionFileDescriptorHelper
package org.freedesktop.dbus.utils;

import java.io.FileDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ReflectionFileDescriptorHelper {

    // ---- поля ----
  private static final Logger LOGGER;
  private static final Optional INSTANCE;
  private final Field fdField;
  private final Constructor constructor;

    static {
        LOGGER = LoggerFactory.getLogger(ReflectionFileDescriptorHelper.class);
        INSTANCE = createInstance();
    }

  private ReflectionFileDescriptorHelper() { // было: <init>
        super();
        fdField = FileDescriptor.class.getDeclaredField("fd");
        fdField.setAccessible(true);
        constructor = FileDescriptor.class.getDeclaredConstructor(new Class[]{Integer.TYPE});
        constructor.setAccessible(true);
    }

  public Optional getFileDescriptorValue(FileDescriptor arg0) {
        Optional __stk1;
        try {
            __stk1 = Optional.of(Integer.valueOf(fdField.getInt(arg0)));
        } catch (SecurityException var2) {
            LOGGER.error("Could not get file descriptor by reflection.", var2);
            return Optional.empty();
        }
    }

  public Optional createFileDescriptor(int arg0) {
        Optional __stk2;
        try {
            __stk2 = Optional.of(((FileDescriptor) constructor.newInstance(new Object[]{Integer.valueOf(arg0)})));
        } catch (SecurityException var2) {
            LOGGER.error("Could not create new FileDescriptor instance by reflection.", var2);
            return Optional.empty();
        }
    }

  private static Optional createInstance() {
        Optional __stk1;
        try {
            __stk1 = Optional.of(new ReflectionFileDescriptorHelper());
        } catch (ReflectiveOperationException var0) {
            LOGGER.error("Unable to hook up java.io.FileDescriptor by using reflection.", var0);
            return Optional.empty();
        }
    }

  public static Optional getInstance() {
        return INSTANCE;
    }

}