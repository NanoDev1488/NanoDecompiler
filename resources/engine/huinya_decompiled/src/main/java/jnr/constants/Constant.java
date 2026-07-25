// исходный (обфусцированный) внутренний класс: jnr.constants.Constant
package jnr.constants;

public interface Constant {

  public abstract int intValue();

  public abstract long longValue();

  public abstract String name();

  public abstract boolean defined();

}