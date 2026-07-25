// исходный (обфусцированный) внутренний класс: jnr.posix.Group
package jnr.posix;

public interface Group {

  public abstract String getName();

  public abstract String getPassword();

  public abstract long getGID();

  public abstract String[] getMembers();

}