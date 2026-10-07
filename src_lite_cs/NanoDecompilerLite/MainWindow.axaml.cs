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
                StatusLabel.Text = $"Ошибка: CLI движок не найден ({cliPath})";
                OpenJarButton.IsEnabled = true;
                return;
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

    private string FindCliExecutable()
    {
        string binName = RuntimeInformation.IsOSPlatform(OSPlatform.Windows) ? "NanoDecompilerCLI.exe" : "NanoDecompilerCLI";

        // 1. Рядом с исполняемым файлом
        string appDir = AppContext.BaseDirectory;
        string direct = Path.Combine(appDir, binName);
        if (File.Exists(direct)) return direct;

        // 2. В подкаталоге resources/engine
        string engineDir = Path.Combine(appDir, "resources", "engine", binName);
        if (File.Exists(engineDir)) return engineDir;

        // 3. В AppData установленного клиента (для Windows)
        if (RuntimeInformation.IsOSPlatform(OSPlatform.Windows))
        {
            string localAppData = Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData);
            string installed = Path.Combine(localAppData, "Programs", "NanoDecompiler", "resources", "engine", binName);
            if (File.Exists(installed)) return installed;
        }

        return binName;
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
