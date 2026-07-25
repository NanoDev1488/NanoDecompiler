// исходный (обфусцированный) внутренний класс: jnr.posix.Passwd
package jnr.posix;

public interface Passwd {

  public abstract String getLoginName();

  public abstract String getPassword();

  public abstract long getUID();

  public abstract long getGID();

  public abstract int getPasswdChangeTime();

  public abstract String getAccessClass();

  public abstract String getGECOS();

  public abstract String getHome();

  public abstract String getShell();

  public abstract int getExpire();

}