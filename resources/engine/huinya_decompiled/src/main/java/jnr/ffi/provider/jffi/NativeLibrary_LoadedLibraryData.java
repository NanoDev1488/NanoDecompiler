// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NativeLibrary.LoadedLibraryData
package jnr.ffi.provider.jffi;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NativeLibrary_LoadedLibraryData {

    // ---- поля ----
  private final List libraryNames;
  private final List searchPaths;
  private final List successfulPaths;

   NativeLibrary_LoadedLibraryData(List arg0, List arg1, List arg2) { // было: <init>
        super();
        libraryNames = Collections.unmodifiableList(arg0);
        searchPaths = Collections.unmodifiableList(arg1);
        successfulPaths = Collections.unmodifiableList(arg2);
    }

  public List getLibraryNames() {
        return libraryNames;
    }

  public List getSearchPaths() {
        return searchPaths;
    }

  public List getSuccessfulPaths() {
        return successfulPaths;
    }

  public boolean equals(Object arg0) {
        if (this != arg0) {
            if (arg0 instanceof NativeLibrary_LoadedLibraryData) {
                NativeLibrary_LoadedLibraryData var2 = ((NativeLibrary_LoadedLibraryData) arg0);
                return !Objects.equals(libraryNames, var2.libraryNames) ? 0 : !Objects.equals(searchPaths, var2.searchPaths) ? 0 : Objects.equals(successfulPaths, var2.successfulPaths);
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

  public int hashCode() {
        return Objects.hash(new Object[]{libraryNames, searchPaths, successfulPaths});
    }

  public String toString() {
        return new StringBuilder().append("LoadedLibraryData {libraryNames=").append(libraryNames).append(", searchPaths=").append(searchPaths).append(", successfulPaths=").append(successfulPaths).append('}').toString();
    }

}