using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Net.Http;
using System.Runtime.InteropServices;
using System.Text.Json;
using System.Threading.Tasks;
using Avalonia.Controls;
using Avalonia.Interactivity;
using Avalonia.Platform.Storage;
using AvaloniaEdit.Highlighting;

namespace NanoDecompilerLite;

public partial class MainWindow : Window
{
    private string? _currentOutDir;
    private readonly HttpClient _http = new() { Timeout = TimeSpan.FromSeconds(6) };
    private const string CurrentVersion = "1.9.158";

    public MainWindow()
    {
        InitializeComponent();

        OpenJarButton.Click += OnOpenJarClicked;
        CheckUpdateButton.Click += OnCheckUpdateClicked;
        CopyCodeButton.Click += OnCopyCodeClicked;
        FileTreeView.SelectionChanged += OnTreeSelectionChanged;
        SearchBox.TextChanged += (s, e) => ApplyFilter();

        // Настройка синтаксической подсветки C# / Java в AvaloniaEdit
        CodeEditor.SyntaxHighlighting = HighlightingManager.Instance.GetDefinition("C#") ??
                                        HighlightingManager.Instance.GetDefinition("Java");

        // Фоновая проверка обновления при старте
        _ = CheckForUpdatesAsync(silent: true);
    }

    private async void OnOpenJarClicked(object? sender, RoutedEventArgs e)
    {
        var topLevel = TopLevel.GetTopLevel(this);
        if (topLevel == null) return;

        var files = await topLevel.StorageProvider.OpenFilePickerAsync(new FilePickerOpenOptions
        {
            Title = "Выберите JAR или архив для декомпиляции",
            AllowMultiple = false,
            FileTypeFilter = new[]
            {
                new FilePickerFileType("Java Archive (*.jar)") { Patterns = new[] { "*.jar", "*.zip", "*.7z", "*.tar.gz" } },
                new FilePickerFileType("Все файлы") { Patterns = new[] { "*.*" } }
            }
        });

        if (files.Count == 0) return;

        string jarPath = files[0].Path.LocalPath;
        await DecompileJarAsync(jarPath);
    }

    private async Task DecompileJarAsync(string jarPath)
    {
        string baseTemp = Path.Combine(Path.GetTempPath(), "NanoDecompilerLite");
        Directory.CreateDirectory(baseTemp);
        string outDir = Path.Combine(baseTemp, Path.GetFileNameWithoutExtension(jarPath) + "_" + Guid.NewGuid().ToString("N")[..8]);
        Directory.CreateDirectory(outDir);

        StatusLabel.Text = $"Декомпиляция {Path.GetFileName(jarPath)} через движок C++...";
        OpenJarButton.IsEnabled = false;

        try
        {
            string cliPath = FindCliExecutable();
            if (!File.Exists(cliPath))
            {
                StatusLabel.Text = "CLI движок не найден автоматически. Выберите файл движка...";
                var picked = await StorageProvider.OpenFilePickerAsync(new FilePickerOpenOptions
                {
                    Title = "Укажите NanoDecompilerCLI.exe / NanoDecompilerClApi.exe",
                    AllowMultiple = false,
                    FileTypeFilter = new List<FilePickerFileType>
                    {
                        new FilePickerFileType("NanoDecompiler CLI Engine") { Patterns = new[] { "NanoDecompilerCLI*.exe", "NanoDecompilerClApi*.exe", "NanoDecompilerCLI*", "*.exe" } },
                        new FilePickerFileType("Все файлы") { Patterns = new[] { "*.*" } }
                    }
                });

                if (picked.Count > 0 && File.Exists(picked[0].Path.LocalPath))
                {
                    _manualCliPath = picked[0].Path.LocalPath;
                    cliPath = _manualCliPath;
                }
                else
                {
                    StatusLabel.Text = $"Ошибка: CLI движок не найден ({cliPath}). Установите полный клиент или поместите NanoDecompilerCLI.exe рядом.";
                    OpenJarButton.IsEnabled = true;
                    return;
                }
            }

            var psi = new ProcessStartInfo
            {
                FileName = cliPath,
                Arguments = $"\"{jarPath}\" \"{outDir}\" --no-legitimacy-check --json-output",
                UseShellExecute = false,
                RedirectStandardOutput = true,
                RedirectStandardError = true,
                CreateNoWindow = true
            };

            var sw = Stopwatch.StartNew();
            using var proc = Process.Start(psi);
            if (proc == null) throw new Exception("Не удалось запустить процесс движка.");

            string stdout = await proc.StandardOutput.ReadToEndAsync();
            await proc.WaitForExitAsync();
            sw.Stop();

            _currentOutDir = outDir;
            PopulateFileTree(outDir);
            StatusLabel.Text = $"Успешно декомпилировано за {sw.ElapsedMilliseconds} мс. Найдено классов: {Directory.GetFiles(outDir, "*.java", SearchOption.AllDirectories).Length}";
        }
        catch (Exception ex)
        {
            StatusLabel.Text = $"Ошибка декомпиляции: {ex.Message}";
        }
        finally
        {
            OpenJarButton.IsEnabled = true;
        }
    }

    private string? _manualCliPath;

    private string FindCliExecutable()
    {
        if (!string.IsNullOrEmpty(_manualCliPath) && File.Exists(_manualCliPath))
            return _manualCliPath;

        string defaultBinName = RuntimeInformation.IsOSPlatform(OSPlatform.Windows) ? "NanoDecompilerCLI.exe" : "NanoDecompilerCLI";
        var candidateNames = RuntimeInformation.IsOSPlatform(OSPlatform.Windows)
            ? new[] { "NanoDecompilerCLI.exe", "NanoDecompilerClApi.exe", "NanoDecompilerClApi-windows.exe" }
            : new[] { "NanoDecompilerCLI", "NanoDecompilerClApi", "NanoDecompilerCLI-Linux-x64", "NanoDecompilerCLI-macOS-arm64", "NanoDecompilerCLI-macOS-x64" };

        var searchDirs = new List<string>();

        // 1. Process directory and BaseDirectory
        try
        {
            if (!string.IsNullOrEmpty(Environment.ProcessPath))
            {
                var procDir = Path.GetDirectoryName(Environment.ProcessPath);
                if (!string.IsNullOrEmpty(procDir)) searchDirs.Add(procDir);
            }
        }
        catch { }
        try { searchDirs.Add(AppContext.BaseDirectory); } catch { }
        try { searchDirs.Add(Directory.GetCurrentDirectory()); } catch { }

        // 2. Parent directory
        try
        {
            var p1 = Directory.GetParent(AppContext.BaseDirectory)?.FullName;
            if (!string.IsNullOrEmpty(p1)) searchDirs.Add(p1);
        }
        catch { }

        // 3. Desktop directories (including Desktop\NanoDecompiler)
        try
        {
            string desktop = Environment.GetFolderPath(Environment.SpecialFolder.DesktopDirectory);
            if (!string.IsNullOrEmpty(desktop))
            {
                searchDirs.Add(Path.Combine(desktop, "NanoDecompiler"));
                searchDirs.Add(desktop);
            }
        }
        catch { }

        // 4. Downloads directories (including Downloads\NanoDecompiler)
        try
        {
            string userProfile = Environment.GetFolderPath(Environment.SpecialFolder.UserProfile);
            if (!string.IsNullOrEmpty(userProfile))
            {
                searchDirs.Add(Path.Combine(userProfile, "Downloads", "NanoDecompiler"));
                searchDirs.Add(Path.Combine(userProfile, "Downloads"));
            }
        }
        catch { }

        // 5. Windows standard Program Files & AppData
        if (RuntimeInformation.IsOSPlatform(OSPlatform.Windows))
        {
            try
            {
                string pf = Environment.GetFolderPath(Environment.SpecialFolder.ProgramFiles);
                if (!string.IsNullOrEmpty(pf)) searchDirs.Add(Path.Combine(pf, "NanoDecompiler"));
            }
            catch { }
            try
            {
                string pf86 = Environment.GetFolderPath(Environment.SpecialFolder.ProgramFilesX86);
                if (!string.IsNullOrEmpty(pf86)) searchDirs.Add(Path.Combine(pf86, "NanoDecompiler"));
            }
            catch { }
            try
            {
                string localAppData = Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData);
                if (!string.IsNullOrEmpty(localAppData)) searchDirs.Add(Path.Combine(localAppData, "Programs", "NanoDecompiler"));
            }
            catch { }
        }
        else
        {
            // Linux / macOS
            searchDirs.Add("/opt/NanoDecompiler");
            searchDirs.Add("/opt/NanoDecompiler/resources/engine");
            searchDirs.Add("/usr/local/bin");
            searchDirs.Add("/usr/bin");
            searchDirs.Add("/Applications/NanoDecompiler.app/Contents/Resources");
            searchDirs.Add("/Applications/NanoDecompiler.app/Contents/Resources/engine");
        }

        // Check PATH environment variable
        try
        {
            var pathEnv = Environment.GetEnvironmentVariable("PATH");
            if (!string.IsNullOrEmpty(pathEnv))
            {
                char sep = RuntimeInformation.IsOSPlatform(OSPlatform.Windows) ? ';' : ':';
                foreach (var p in pathEnv.Split(sep, StringSplitOptions.RemoveEmptyEntries))
                {
                    if (Directory.Exists(p)) searchDirs.Add(p);
                }
            }
        }
        catch { }

        // Now search all candidate directories
        foreach (var dir in searchDirs.Distinct())
        {
            if (!Directory.Exists(dir)) continue;

            // Direct in dir
            foreach (var name in candidateNames)
            {
                string candidate = Path.Combine(dir, name);
                if (File.Exists(candidate)) return candidate;
            }

            // In resources/engine
            string subEngine = Path.Combine(dir, "resources", "engine");
            if (Directory.Exists(subEngine))
            {
                foreach (var name in candidateNames)
                {
                    string candidate = Path.Combine(subEngine, name);
                    if (File.Exists(candidate)) return candidate;
                }
            }

            // Wildcard search in dir for NanoDecompilerCLI*.exe / NanoDecompilerClApi*.exe
            try
            {
                string pattern = RuntimeInformation.IsOSPlatform(OSPlatform.Windows) ? "NanoDecompilerCLI*.exe" : "NanoDecompilerCLI*";
                string patternApi = RuntimeInformation.IsOSPlatform(OSPlatform.Windows) ? "NanoDecompilerClApi*.exe" : "NanoDecompilerClApi*";

                var matches = Directory.GetFiles(dir, pattern, SearchOption.TopDirectoryOnly)
                    .Concat(Directory.GetFiles(dir, patternApi, SearchOption.TopDirectoryOnly))
                    .OrderByDescending(f => f)
                    .ToList();
                if (matches.Count > 0) return matches[0];

                if (Directory.Exists(subEngine))
                {
                    var subMatches = Directory.GetFiles(subEngine, pattern, SearchOption.TopDirectoryOnly)
                        .Concat(Directory.GetFiles(subEngine, patternApi, SearchOption.TopDirectoryOnly))
                        .OrderByDescending(f => f)
                        .ToList();
                    if (subMatches.Count > 0) return subMatches[0];
                }
            }
            catch { }
        }

        return defaultBinName;
    }

    private FileNode? _rootNode;

    private void PopulateFileTree(string rootDir)
    {
        FileTreeView.ItemsSource = null;
        _rootNode = CreateDirectoryNode(new DirectoryInfo(rootDir));
        ApplyFilter();
    }

    private void ApplyFilter()
    {
        if (_rootNode == null) return;
        string q = SearchBox.Text?.Trim().ToLowerInvariant() ?? "";
        if (string.IsNullOrEmpty(q))
        {
            FileTreeView.ItemsSource = _rootNode.Children;
        }
        else
        {
            var filtered = FilterNode(_rootNode, q);
            FileTreeView.ItemsSource = filtered?.Children ?? new List<FileNode>();
        }
    }

    private FileNode? FilterNode(FileNode node, string q)
    {
        if (!node.IsDirectory)
        {
            return node.Name.ToLowerInvariant().Contains(q) ? node : null;
        }

        var matchNode = new FileNode
        {
            Name = node.Name,
            FullPath = node.FullPath,
            IsDirectory = true
        };

        foreach (var c in node.Children)
        {
            var matchedChild = FilterNode(c, q);
            if (matchedChild != null)
            {
                matchNode.Children.Add(matchedChild);
            }
        }

        if (matchNode.Children.Count > 0 || node.Name.ToLowerInvariant().Contains(q))
        {
            return matchNode;
        }
        return null;
    }

    private FileNode CreateDirectoryNode(DirectoryInfo dir)
    {
        var node = new FileNode { Name = dir.Name, FullPath = dir.FullName, IsDirectory = true };
        foreach (var sub in dir.GetDirectories())
        {
            node.Children.Add(CreateDirectoryNode(sub));
        }
        foreach (var file in dir.GetFiles())
        {
            node.Children.Add(new FileNode { Name = file.Name, FullPath = file.FullName, IsDirectory = false });
        }
        return node;
    }

    private void OnTreeSelectionChanged(object? sender, SelectionChangedEventArgs e)
    {
        if (FileTreeView.SelectedItem is FileNode selected && !selected.IsDirectory)
        {
            try
            {
                string text = File.ReadAllText(selected.FullPath);
                CodeEditor.Text = text;
                CurrentFileLabel.Text = selected.Name;
            }
            catch (Exception ex)
            {
                CodeEditor.Text = $"Ошибка чтения файла: {ex.Message}";
            }
        }
    }

    private async void OnCopyCodeClicked(object? sender, RoutedEventArgs e)
    {
        var topLevel = TopLevel.GetTopLevel(this);
        if (topLevel?.Clipboard != null && !string.IsNullOrEmpty(CodeEditor.Text))
        {
            await topLevel.Clipboard.SetTextAsync(CodeEditor.Text);
            StatusLabel.Text = "Код скопирован в буфер обмена.";
        }
    }

    private async void OnCheckUpdateClicked(object? sender, RoutedEventArgs e)
    {
        UpdateStatusLabel.Text = "Проверка...";
        await CheckForUpdatesAsync(silent: false);
    }

    private async Task CheckForUpdatesAsync(bool silent)
    {
        try
        {
            _http.DefaultRequestHeaders.UserAgent.Clear();
            _http.DefaultRequestHeaders.UserAgent.ParseAdd("NanoDecompilerLite-Updater");

            string json = await _http.GetStringAsync("https://api.github.com/repos/NanoDev1488/NanoDecompiler/releases/latest");
            using var doc = JsonDocument.Parse(json);
            string tagName = doc.RootElement.GetProperty("tag_name").GetString() ?? "";
            string remoteVer = tagName.TrimStart('v', 'V');

            if (IsNewer(remoteVer, CurrentVersion))
            {
                UpdateStatusLabel.Text = $"Доступно обновление: v{remoteVer}!";
                if (!silent)
                {
                    StatusLabel.Text = $"Новая версия {tagName} доступна на GitHub. Скачайте в релизах.";
                }
            }
            else
            {
                UpdateStatusLabel.Text = "У вас актуальная версия";
                if (!silent)
                {
                    StatusLabel.Text = $"Установлена последняя версия v{CurrentVersion}.";
                }
            }
        }
        catch (Exception ex)
        {
            if (!silent) StatusLabel.Text = $"Ошибка проверки обновлений: {ex.Message}";
        }
    }

    private static bool IsNewer(string remote, string local)
    {
        var pR = remote.Split('.');
        var pL = local.Split('.');
        for (int i = 0; i < Math.Max(pR.Length, pL.Length); i++)
        {
            int r = i < pR.Length && int.TryParse(pR[i], out var vi) ? vi : 0;
            int l = i < pL.Length && int.TryParse(pL[i], out var vl) ? vl : 0;
            if (r > l) return true;
            if (r < l) return false;
        }
        return false;
    }
}

public class FileNode
{
    public string Name { get; set; } = "";
    public string FullPath { get; set; } = "";
    public bool IsDirectory { get; set; }
    public List<FileNode> Children { get; set; } = new();
    public string Icon => IsDirectory ? "📁" : (Name.EndsWith(".java") ? "☕" : "📄");

    public override string ToString() => Name;
}
