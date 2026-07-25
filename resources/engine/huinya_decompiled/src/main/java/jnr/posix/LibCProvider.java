// исходный (обфусцированный) внутренний класс: jnr.posix.LibCProvider
package jnr.posix;

import jnr.posix.Crypt;
import jnr.posix.LibC;

public interface LibCProvider {

  public abstract LibC getLibC();

  public abstract Crypt getCrypt();

}