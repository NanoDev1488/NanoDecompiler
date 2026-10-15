pub mod entropy;
pub mod malware;
pub mod scanner;

use std::ffi::{CStr, CString};
use std::os::raw::c_char;

pub use scanner::{scan_jar, JarScanReport};

/// C-ABI интерфейс для вызова из C++ / Electron / C#
#[no_mangle]
pub extern "C" fn nanodecompiler_fastscan_jar(jar_path: *const c_char) -> *mut c_char {
    if jar_path.is_null() {
        return std::ptr::null_mut();
    }
    let c_str = unsafe { CStr::from_ptr(jar_path) };
    let path_str = match c_str.to_str() {
        Ok(s) => s,
        Err(_) => return std::ptr::null_mut(),
    };

    match scan_jar(path_str) {
        Ok(report) => match serde_json::to_string(&report) {
            Ok(json) => CString::new(json).unwrap_or_default().into_raw(),
            Err(_) => std::ptr::null_mut(),
        },
        Err(err) => {
            let err_json = format!("{{\"error\": \"{}\"}}", err.replace('"', "\\\""));
            CString::new(err_json).unwrap_or_default().into_raw()
        }
    }
}

#[no_mangle]
pub extern "C" fn nanodecompiler_fastscan_free(ptr: *mut c_char) {
    if !ptr.is_null() {
        unsafe {
            let _ = CString::from_raw(ptr);
        }
    }
}
