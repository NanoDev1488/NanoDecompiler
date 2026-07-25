// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.messages.ObjectTree
package org.freedesktop.dbus.messages;

import java.util.regex.Pattern;
import org.freedesktop.dbus.messages.ExportedObject;
import org.freedesktop.dbus.messages.ObjectTree_TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectTree {

    // ---- поля ----
  public static final Pattern SLASH_PATTERN;
  private final Logger logger;
  private ObjectTree_TreeNode root;

    static {
        SLASH_PATTERN = Pattern.compile("/");
    }

  public ObjectTree() { // было: <init>
        super();
        logger = LoggerFactory.getLogger(getClass());
        root = new ObjectTree_TreeNode("");
    }

  private ObjectTree_TreeNode recursiveFind(ObjectTree_TreeNode arg0, String arg1) {
        if (!"/".equals(arg1)) {
            String[] var3 = arg1.split("/", 2);
            if (!arg1.startsWith(arg0.name)) {
                if (arg0.right != null) {
                    if (0 <= arg0.right.name.compareTo(((String) var3[0]))) {
                        return recursiveFind(arg0.right, arg1);
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                if (!arg1.equals(arg0.name)) {
                    if (arg0.down != null) {
                        return recursiveFind(arg0.down, ((String) var3[1]));
                    } else {
                        return null;
                    }
                } else {
                    return arg0;
                }
            }
        } else {
            return arg0;
        }
    }

  private ObjectTree_TreeNode recursiveAdd(ObjectTree_TreeNode arg0, String arg1, ExportedObject arg2, String arg3) {
        String[] var5 = SLASH_PATTERN.split(arg1, 2);
        if (!arg1.startsWith(arg0.name)) {
            if (arg0.right != null) {
                if (0 <= arg0.right.name.compareTo(((String) var5[0]))) {
                    arg0.right = recursiveAdd(arg0.right, arg1, arg2, arg3);
                } else {
                    ObjectTree_TreeNode var6 = new ObjectTree_TreeNode(((String) var5[0]));
                    var6.right = arg0.right;
                    arg0.right = var6;
                    arg0.right = recursiveAdd(arg0.right, arg1, arg2, arg3);
                }
            } else {
                arg0.right = new ObjectTree_TreeNode(((String) var5[0]));
                arg0.right = recursiveAdd(arg0.right, arg1, arg2, arg3);
            }
        } else {
            if (1 == var5.length) {
                arg0.object = arg2;
                arg0.data = arg3;
            } else {
                if (!"".equals(var5[1])) {
                    if (arg0.down == null) {
                        String[] var6 = var5[1].split("/", 2);
                        arg0.down = new ObjectTree_TreeNode(((String) var6[0]));
                    }
                    arg0.down = recursiveAdd(arg0.down, ((String) var5[1]), arg2, arg3);
                } else {
                    arg0.object = arg2;
                    arg0.data = arg3;
                }
            }
        }
        return arg0;
    }

  public synchronized void add(String arg0, ExportedObject arg1, String arg2) {
        logger.debug("Adding {} to object tree", arg0);
        root = recursiveAdd(root, arg0, arg1, arg2);
    }

  private ObjectTree_TreeNode recursiveRemove(ObjectTree_TreeNode arg0, String arg1) {
        String[] var3 = arg1.split("/", 2);
        if (!var3[0].equals(arg0.name)) {
            if (arg0.right != null) {
                if (0 <= arg0.right.name.compareTo(((String) var3[0]))) {
                    arg0.right = recursiveRemove(arg0.right, arg1);
                    return arg0;
                } else {
                    return arg0;
                }
            } else {
                return arg0;
            }
        } else {
            if (1 == var3.length) {
                arg0.object = null;
                arg0.data = null;
                if (arg0.down == null) {
                    return arg0.right;
                } else {
                    return arg0;
                }
            } else {
                if (!"".equals(var3[1])) {
                    if (arg0.down == null) {
                        return arg0;
                    } else {
                        arg0.down = recursiveRemove(arg0.down, ((String) var3[1]));
                        if (arg0.down != null) {
                            return arg0;
                        } else {
                            if (arg0.data != null) {
                                return arg0;
                            } else {
                                return arg0.right;
                            }
                        }
                    }
                } else {
                    arg0.object = null;
                    arg0.data = null;
                    if (arg0.down == null) {
                        return arg0.right;
                    } else {
                        return arg0;
                    }
                }
            }
        }
    }

  public synchronized void remove(String arg0) {
        logger.debug("Removing {} from object tree", arg0);
        recursiveRemove(root, arg0);
    }

  public String Introspect(String arg0) {
        ObjectTree_TreeNode var2 = recursiveFind(root, arg0);
        StringBuilder var3;
        if (null != var2) {
            var3 = new StringBuilder();
            var3.append("<node name=\"");
            var3.append(arg0);
            var3.append("\">\n");
            if (null != var2.data) {
                var3.append(var2.data);
            }
        } else {
            return null;
        }
        var2 = var2.down;
        while (null != var2) {
            var3.append("<node name=\"");
            var3.append(var2.name);
            var3.append("\"/>\n");
            var2 = var2.right;
            continue;
        }
        var3.append("</node>");
        return var3.toString();
    }

  private String recursivePrint(ObjectTree_TreeNode arg0) {
        String var2 = "";
        if (null != arg0) {
            var2 = var2 + arg0.name;
            if (null != arg0.object) {
                var2 = var2 + "*";
            }
            if (null != arg0.down) {
                var2 = var2 + "/{" + recursivePrint(arg0.down) + "}";
            }
            if (null != arg0.right) {
                var2 = var2 + ", " + recursivePrint(arg0.right);
            }
        }
        return var2;
    }

  public String toString() {
        return recursivePrint(root);
    }

}