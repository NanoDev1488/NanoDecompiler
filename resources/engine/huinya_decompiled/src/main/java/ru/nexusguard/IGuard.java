// исходный (обфусцированный) внутренний класс: ru.nexusguard.IGuard
package ru.nexusguard;

public interface IGuard {

  public abstract String username();

  public abstract String hwid();

  public abstract String role();

  public abstract int uid();

  public abstract String roleName();

}