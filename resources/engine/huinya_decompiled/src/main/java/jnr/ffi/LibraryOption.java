// исходный (обфусцированный) внутренний класс: jnr.ffi.LibraryOption
package jnr.ffi;

public enum LibraryOption {

    SaveError,
    IgnoreError,
    TypeMapper,
    FunctionMapper,
    CallingConvention,
    LoadNow,
    PreferCustomPaths;

  private LibraryOption() { // было: <init>
        // (пустое тело)
    }

}