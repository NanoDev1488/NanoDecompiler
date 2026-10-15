/// Вычисление энтропии Шеннона для среза байт (0.0 .. 8.0)
/// Значения > 7.2 обычно указывают на зашифрованные или сжатые данные (пейлоады, AES/XOR).
pub fn shannon_entropy(data: &[u8]) -> f64 {
    if data.is_empty() {
        return 0.0;
    }
    let mut counts = [0usize; 256];
    for &b in data {
        counts[b as usize] += 1;
    }
    let len = data.len() as f64;
    let mut entropy = 0.0;
    for &c in counts.iter() {
        if c > 0 {
            let p = c as f64 / len;
            entropy -= p * p.log2();
        }
    }
    entropy
}

/// Проверка строки на подозрительность (base64 / hex payload)
pub fn is_suspicious_payload_string(s: &str) -> bool {
    if s.len() < 32 {
        return false;
    }
    let entropy = shannon_entropy(s.as_bytes());
    // Высокая энтропия + длинная строка без пробелов
    entropy > 4.5 && !s.contains(' ')
}
