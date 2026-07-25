// исходный (обфусцированный) внутренний класс: jnr.unixsocket.UnixSocketAddress
package jnr.unixsocket;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import jnr.constants.platform.ProtocolFamily;
import jnr.unixsocket.SockAddrUnix;

public class UnixSocketAddress extends SocketAddress {

    // ---- поля ----
  private static final long serialVersionUID = 4821337010221569096L;
  private transient SockAddrUnix address;

   UnixSocketAddress() { // было: <init>
        super();
        address = SockAddrUnix.create();
        address.setFamily(ProtocolFamily.PF_UNIX);
    }

  public UnixSocketAddress(File arg0) { // было: <init>
        super();
        address = SockAddrUnix.create();
        address.setFamily(ProtocolFamily.PF_UNIX);
        address.setPath(arg0.getPath());
    }

  public UnixSocketAddress(String arg0) { // было: <init>
        super();
        address = SockAddrUnix.create();
        address.setFamily(ProtocolFamily.PF_UNIX);
        address.setPath(arg0);
    }

   SockAddrUnix getStruct() {
        return address;
    }

   int length() {
        return address.length();
    }

  public String path() {
        return address.getPath();
    }

  public String humanReadablePath() {
        String var1 = path();
        if (var1.indexOf(0) != 0) {
            return var1;
        } else {
            return var1.replace('\u0000', '@');
        }
    }

  public String toString() {
        return new StringBuilder().append("[family=").append(address.getFamily()).append(" path=").append(humanReadablePath()).append("]").toString();
    }

  public boolean equals(Object arg0) {
        if (arg0 instanceof UnixSocketAddress) {
            UnixSocketAddress var2 = ((UnixSocketAddress) arg0);
            return address.getFamily() != var2.address.getFamily() ? 0 : path().equals(var2.path());
        } else {
            return false;
        }
    }

  public int hashCode() {
        return address.hashCode();
    }

  private void writeObject(ObjectOutputStream arg0) {
        arg0.defaultWriteObject();
        arg0.writeObject(path());
    }

  private void readObject(ObjectInputStream arg0) {
        arg0.defaultReadObject();
        String var2 = ((String) arg0.readObject());
        if (null == address) {
            address = SockAddrUnix.create();
        }
        address.setPath(var2);
        address.setFamily(ProtocolFamily.PF_UNIX);
    }

}