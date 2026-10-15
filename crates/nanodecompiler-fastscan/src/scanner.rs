use std::fs::File;
use std::io::{Cursor, Read};
use std::path::Path;
use rayon::prelude::*;
use serde::{Deserialize, Serialize};
use zip::ZipArchive;

use crate::entropy::shannon_entropy;
use crate::malware::{MalwareScanner, ThreatFinding};

#[derive(Debug, Serialize, Deserialize)]
pub struct JarScanReport {
    pub jar_name: String,
    pub file_size_bytes: u64,
    pub total_classes: usize,
    pub total_packages: usize,
    pub plugin_name: Option<String>,
    pub main_class: Option<String>,
    pub api_version: Option<String>,
    pub danger_score: u32, // 0..100
    pub max_entropy: f64,
    pub threats: Vec<ThreatFinding>,
}

pub fn scan_jar<P: AsRef<Path>>(path: P) -> Result<JarScanReport, String> {
    let p = path.as_ref();
    let file = File::open(p).map_err(|e| format!("Failed to open jar: {}", e))?;
    let metadata = file.metadata().map_err(|e| format!("Failed to read metadata: {}", e))?;
    let file_size = metadata.len();

    let mmap = unsafe { memmap2::Mmap::map(&file).map_err(|e| format!("Failed to mmap jar: {}", e))? };
    let reader = Cursor::new(&mmap[..]);
    let mut zip = ZipArchive::new(reader).map_err(|e| format!("Bad zip format: {}", e))?;

    let mut class_entries = Vec::new();
    let mut plugin_yml_content: Option<String> = None;

    for i in 0..zip.len() {
        if let Ok(mut entry) = zip.by_index(i) {
            let name = entry.name().to_string();
            if name == "plugin.yml" {
                let mut s = String::new();
                let _ = entry.read_to_string(&mut s);
                plugin_yml_content = Some(s);
            } else if name.ends_with(".class") && !name.contains("module-info") {
                let mut buf = Vec::with_capacity(entry.size() as usize);
                let _ = entry.read_to_end(&mut buf);
                class_entries.push((name, buf));
            }
        }
    }

    let total_classes = class_entries.len();
    let mut packages = std::collections::HashSet::new();
    for (name, _) in &class_entries {
        if let Some(pos) = name.rfind('/') {
            packages.insert(name[..pos].to_string());
        }
    }

    // Параллельный анализ классов через Rayon
    let results: Vec<(Vec<ThreatFinding>, f64)> = class_entries
        .par_iter()
        .map(|(name, bytes)| {
            let entropy = shannon_entropy(bytes);
            let threats = MalwareScanner::scan_class_bytes(name, bytes);
            (threats, entropy)
        })
        .collect();

    let mut all_threats = Vec::new();
    let mut max_entropy: f64 = 0.0;
    for (threats, entropy) in results {
        all_threats.extend(threats);
        if entropy > max_entropy {
            max_entropy = entropy;
        }
    }

    let mut danger_score = 0u32;
    for t in &all_threats {
        match t.severity.as_str() {
            "CRITICAL" => danger_score = danger_score.saturating_add(35),
            "HIGH" => danger_score = danger_score.saturating_add(20),
            "MEDIUM" => danger_score = danger_score.saturating_add(10),
            _ => danger_score = danger_score.saturating_add(5),
        }
    }
    if max_entropy > 7.4 {
        danger_score = danger_score.saturating_add(15);
    }
    danger_score = danger_score.min(100);

    let (plugin_name, main_class, api_version) = parse_plugin_yml(plugin_yml_content.as_deref());

    Ok(JarScanReport {
        jar_name: p.file_name().unwrap_or_default().to_string_lossy().to_string(),
        file_size_bytes: file_size,
        total_classes,
        total_packages: packages.len(),
        plugin_name,
        main_class,
        api_version,
        danger_score,
        max_entropy,
        threats: all_threats,
    })
}

fn parse_plugin_yml(content: Option<&str>) -> (Option<String>, Option<String>, Option<String>) {
    let mut name = None;
    let mut main = None;
    let mut api_ver = None;

    if let Some(text) = content {
        for line in text.lines() {
            let trimmed = line.trim();
            if trimmed.starts_with("name:") {
                name = Some(trimmed.trim_start_matches("name:").trim().trim_matches('\'').trim_matches('"').to_string());
            } else if trimmed.starts_with("main:") {
                main = Some(trimmed.trim_start_matches("main:").trim().trim_matches('\'').trim_matches('"').to_string());
            } else if trimmed.starts_with("api-version:") {
                api_ver = Some(trimmed.trim_start_matches("api-version:").trim().trim_matches('\'').trim_matches('"').to_string());
            }
        }
    }
    (name, main, api_ver)
}
