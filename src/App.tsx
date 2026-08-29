import { useEffect } from "react";
import { EngineProvider, useEngine } from "./state/engine";
import { Titlebar } from "./components/Titlebar";
import { AppHeader } from "./components/AppHeader";
import { Sidebar } from "./components/Sidebar";
import { Workspace } from "./components/Workspace";
import { Terminal } from "./components/Terminal";
import { StatusBar } from "./components/StatusBar";
import { SettingsModal } from "./components/SettingsModal";
import { UpdateModal } from "./components/UpdateModal";
import { CommandPalette } from "./components/CommandPalette";
import { Toasts } from "./components/Toasts";

function Shell() {
  const { settingsOpen, updateModalOpen, paletteOpen } = useEngine();

  // браузер иначе открывает перетащенный файл как страницу
  useEffect(() => {
    const prevent = (e: DragEvent) => e.preventDefault();
    window.addEventListener("dragover", prevent);
    window.addEventListener("drop", prevent);
    return () => {
      window.removeEventListener("dragover", prevent);
      window.removeEventListener("drop", prevent);
    };
  }, []);

  return (
    <div className="flex h-screen w-screen min-w-[960px] flex-col overflow-hidden bg-bg text-ink">
      <Titlebar />
      <AppHeader />
      <main className="flex min-h-0 flex-1">
        <Sidebar />
        <section className="flex min-w-0 flex-1 flex-col">
          <Workspace />
          <Terminal />
        </section>
      </main>
      <StatusBar />

      {settingsOpen && <SettingsModal />}
      {updateModalOpen && <UpdateModal />}
      {paletteOpen && <CommandPalette />}
      <Toasts />
    </div>
  );
}

export default function App() {
  return (
    <EngineProvider>
      <Shell />
    </EngineProvider>
  );
}
