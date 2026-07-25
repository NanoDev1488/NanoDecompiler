// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.messages.ObjectTree.TreeNode
package org.freedesktop.dbus.messages;

import org.freedesktop.dbus.messages.ExportedObject;

class ObjectTree_TreeNode {

    // ---- поля ----
   String name;
   ExportedObject object;
   String data;
   ObjectTree_TreeNode right;
   ObjectTree_TreeNode down;

   ObjectTree_TreeNode(String arg0) { // было: <init>
        super();
        name = arg0;
    }

   ObjectTree_TreeNode(String arg0, ExportedObject arg1, String arg2) { // было: <init>
        super();
        name = arg0;
        object = arg1;
        data = arg2;
    }

}