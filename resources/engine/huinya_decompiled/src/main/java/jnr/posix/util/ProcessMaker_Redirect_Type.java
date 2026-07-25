// исходный (обфусцированный) внутренний класс: jnr.posix.util.ProcessMaker.Redirect.Type
package jnr.posix.util;

enum ProcessMaker_Redirect_Type {

    APPEND,
    INHERIT,
    PIPE,
    READ,
    WRITE;

  private ProcessMaker_Redirect_Type() { // было: <init>
        // (пустое тело)
    }

}