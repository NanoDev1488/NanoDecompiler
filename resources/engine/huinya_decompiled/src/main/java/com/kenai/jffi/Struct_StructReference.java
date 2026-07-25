// исходный (обфусцированный) внутренний класс: com.kenai.jffi.Struct.StructReference
package com.kenai.jffi;

import com.kenai.jffi.Struct;
import com.kenai.jffi.Struct_Anon1;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.List;

final class Struct_StructReference extends WeakReference {

    // ---- поля ----
   List fieldsList;

  private Struct_StructReference(Struct arg0, ReferenceQueue arg1, List arg2) { // было: <init>
        super(arg0, arg1);
        fieldsList = arg2;
    }

   Struct_StructReference(Struct arg0, ReferenceQueue arg1, List arg2, Struct_Anon1 arg3) { // было: <init>
        this(arg0, arg1, arg2);
    }

}