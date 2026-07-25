// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.messages.EmptyCollectionHelper
package org.freedesktop.dbus.messages;

import java.util.Arrays;
import org.freedesktop.dbus.messages.EmptyCollectionHelper_ECollectionSubType;

final class EmptyCollectionHelper {

  private EmptyCollectionHelper() { // было: <init>
        super();
    }

  static int determineSignatureOffsetDict(byte[] arg0, int arg1) {
        return determineEndOfBracketStructure(arg0, arg1, '{', '}');
    }

  static int determineSignatureOffsetArray(byte[] arg0, int arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: iload_1
        //      2: invokestatic  #23 // org.freedesktop.dbus.messages.EmptyCollectionHelper.determineSubSignature:([BI)Ljava/lang/String;
        //      5: astore_2
        //      6: aload_2
        //      7: invokevirtual  #15 // java.lang.String.isEmpty:()Z
        //     10: ifeq  15 (offset +5)
        //     13: iload_1
        //     14: ireturn
        //     15: aload_0
        //     16: iload_1
        //     17: baload
        //     18: i2c
        //     19: invokestatic  #18 // org.freedesktop.dbus.messages.EmptyCollectionHelper.determineCollectionSubType:(C)Lorg/freedesktop/dbus/messages/EmptyCollectionHelper$ECollectionSubType;
        //     22: astore_3
        //     23: aload_3
        //     24: invokevirtual  #24 // org.freedesktop.dbus.messages.EmptyCollectionHelper$ECollectionSubType.ordinal:()I
        //     27: tableswitch  default->86, 0->74, 1->66, 2->56, 3->82
        //     56: aload_0
        //     57: iload_1
        //     58: iconst_1
        //     59: iadd
        //     60: invokestatic  #20 // org.freedesktop.dbus.messages.EmptyCollectionHelper.determineSignatureOffsetArray:([BI)I
        //     63: goto  96 (offset +33)
        //     66: aload_0
        //     67: iload_1
        //     68: invokestatic  #21 // org.freedesktop.dbus.messages.EmptyCollectionHelper.determineSignatureOffsetDict:([BI)I
        //     71: goto  96 (offset +25)
        //     74: aload_0
        //     75: iload_1
        //     76: invokestatic  #22 // org.freedesktop.dbus.messages.EmptyCollectionHelper.determineSignatureOffsetStruct:([BI)I
        //     79: goto  96 (offset +17)
        //     82: iload_1
        //     83: goto  96 (offset +13)
        //     86: new  #2 // java.lang.IllegalStateException
        //     89: dup
        //     90: ldc  #1 // 'Unable to parse signature for empty collection'
        //     92: invokespecial  #12 // java.lang.IllegalStateException.<init>:(Ljava/lang/String;)V
        //     95: athrow
        //     96: ireturn
    }

  private static int determineSignatureOffsetStruct(byte[] arg0, int arg1) {
        return determineEndOfBracketStructure(arg0, arg1, '(', ')');
    }

  private static int determineEndOfBracketStructure(byte[] arg0, int arg1, char arg2, char arg3) {
        String var4 = determineSubSignature(arg0, arg1);
        int var5;
        if (!var4.isEmpty()) {
            var5 = 0;
            int var6 = 0;
            char[] var7 = var4.toCharArray();
            int var8 = var7.length;
            int var9 = 0;
        } else {
            return arg1;
        }
        while (true) {
            if (var9 >= var8) {
                throw new IllegalStateException("Unable to parse signature for empty collection");
            } else {
                char var10 = var7[var9];
                if (var10 != arg2) {
                    if (var10 == arg3) {
                        --var6;
                    }
                } else {
                    ++var6;
                }
            }
            if (var6 == 0) {
                break;
            }
            ++var5;
            ++var9;
            continue;
        }
        return arg1 + var5;
    }

  private static String determineSubSignature(byte[] arg0, int arg1) {
        byte[] var2 = Arrays.copyOfRange(arg0, arg1, arg0.length);
        return new String(var2);
    }

  private static EmptyCollectionHelper_ECollectionSubType determineCollectionSubType(char arg0) {
        EmptyCollectionHelper_ECollectionSubType __stk1;
        switch (arg0) {
            case 40:
                __stk1 = EmptyCollectionHelper_ECollectionSubType.STRUCT;
                break;
            case 123:
                __stk1 = EmptyCollectionHelper_ECollectionSubType.DICT;
                break;
            case 97:
                __stk1 = EmptyCollectionHelper_ECollectionSubType.ARRAY;
                break;
            default:
                __stk1 = EmptyCollectionHelper_ECollectionSubType.PRIMITIVE;
        }
        return __stk1;
    }

}