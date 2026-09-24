import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App";
import { LogWindow } from "./components/LogWindow";

// НОВОЕ v1.9.8 (окно логов разработчика, HANDOFF п.16): второе окно
// (electron/main.ts::openLogWindow) грузит тот же index.html, но с
// #/logs в хэше - здесь решаем, что рендерить, вместо отдельного
// HTML-файла (проще: один бандл, одна точка входа).
createRoot(document.getElementById("root")!).render(
  <StrictMode>{location.hash === "#/logs" ? <LogWindow /> : <App />}</StrictMode>,
);
