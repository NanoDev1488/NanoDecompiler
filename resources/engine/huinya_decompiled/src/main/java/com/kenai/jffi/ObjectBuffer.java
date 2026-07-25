// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ObjectBuffer
package com.kenai.jffi;

import java.nio.Buffer;

final class ObjectBuffer {

    // ---- поля ----
  public static final int IN = 1;
  public static final int OUT = 2;
  public static final int ZERO_TERMINATE = 4;
  public static final int PINNED = 8;
  public static final int CLEAR = 16;
  static final int INDEX_SHIFT = 16;
  static final int INDEX_MASK = 16711680;
  static final int TYPE_SHIFT = 24;
  static final int TYPE_MASK = -16777216;
  static final int PRIM_MASK = 251658240;
  static final int FLAGS_SHIFT = 0;
  static final int FLAGS_MASK = 255;
  static final int ARRAY = 268435456;
  static final int BUFFER = 536870912;
  static final int JNI = 1073741824;
  static final int BYTE = 16777216;
  static final int SHORT = 33554432;
  static final int INT = 50331648;
  static final int LONG = 67108864;
  static final int FLOAT = 83886080;
  static final int DOUBLE = 100663296;
  static final int BOOLEAN = 117440512;
  static final int CHAR = 134217728;
  public static final int JNIENV = 16777216;
  public static final int JNIOBJECT = 33554432;
  private Object[] objects;
  private int[] info;
  private int infoIndex;
  private int objectIndex;

   ObjectBuffer() { // было: <init>
        super();
        infoIndex = 0;
        objectIndex = 0;
        objects = new Object[1];
        info = new int[objects.length * 3];
    }

   ObjectBuffer(int arg0) { // было: <init>
        super();
        infoIndex = 0;
        objectIndex = 0;
        objects = new Object[arg0];
        info = new int[arg0 * 3];
    }

  final int objectCount() {
        return objectIndex;
    }

  final int[] info() {
        return info;
    }

  final Object[] objects() {
        return objects;
    }

  private final void ensureSpace() {
        if (objects.length <= objectIndex + 1) {
            Object[] var1 = new Object[objects.length << 1];
            System.arraycopy(objects, 0, var1, 0, objectIndex);
            objects = var1;
            int[] var2 = new int[objects.length * 3];
            System.arraycopy(info, 0, var2, 0, objectIndex * 3);
            info = var2;
        }
    }

  static final int makeObjectFlags(int arg0, int arg1, int arg2) {
        return arg0 & 255 | arg2 << 16 & 16711680 | arg1;
    }

  static final int makeBufferFlags(int arg0) {
        return arg0 << 16 & 16711680 | 536870912;
    }

  private static final int makeJNIFlags(int arg0, int arg1) {
        return arg0 << 16 & 16711680 | 1073741824 | arg1;
    }

  public void putArray(int arg0, byte[] arg1, int arg2, int arg3, int arg4) {
        putObject(arg1, arg2, arg3, makeObjectFlags(arg4, 285212672, arg0));
    }

  public void putArray(int arg0, short[] arg1, int arg2, int arg3, int arg4) {
        putObject(arg1, arg2, arg3, makeObjectFlags(arg4, 301989888, arg0));
    }

  public void putArray(int arg0, int[] arg1, int arg2, int arg3, int arg4) {
        putObject(arg1, arg2, arg3, makeObjectFlags(arg4, 318767104, arg0));
    }

  public void putArray(int arg0, long[] arg1, int arg2, int arg3, int arg4) {
        putObject(arg1, arg2, arg3, makeObjectFlags(arg4, 335544320, arg0));
    }

  public void putArray(int arg0, float[] arg1, int arg2, int arg3, int arg4) {
        putObject(arg1, arg2, arg3, makeObjectFlags(arg4, 352321536, arg0));
    }

  public void putArray(int arg0, double[] arg1, int arg2, int arg3, int arg4) {
        putObject(arg1, arg2, arg3, makeObjectFlags(arg4, 369098752, arg0));
    }

  public void putArray(int arg0, boolean[] arg1, int arg2, int arg3, int arg4) {
        putObject(arg1, arg2, arg3, makeObjectFlags(arg4, 385875968, arg0));
    }

  public void putArray(int arg0, char[] arg1, int arg2, int arg3, int arg4) {
        putObject(arg1, arg2, arg3, makeObjectFlags(arg4, 402653184, arg0));
    }

  public void putDirectBuffer(int arg0, Buffer arg1, int arg2, int arg3) {
        putObject(arg1, arg2, arg3, makeBufferFlags(arg0));
    }

  public void putJNI(int arg0, Object arg1, int arg2) {
        putObject(arg1, 0, 0, makeJNIFlags(arg0, arg2));
    }

   void putObject(Object arg0, int arg1, int arg2, int arg3) {
        ensureSpace();
        objectIndex = objectIndex + 1;
        objects[objectIndex] = arg0;
        infoIndex = infoIndex + 1;
        info[infoIndex] = arg3;
        infoIndex = infoIndex + 1;
        info[infoIndex] = arg1;
        infoIndex = infoIndex + 1;
        info[infoIndex] = arg2;
    }

}