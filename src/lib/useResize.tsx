import { useCallback, useRef, type PointerEvent as ReactPointerEvent } from "react";

/* Растягиваемые панели по просьбе пользователя ("чтобы можно было изменять
   размер каждого под окошка") - Sidebar/FileTree по горизонтали, Terminal
   по вертикали. Простое отслеживание мыши без внешних библиотек - drag
   по ручке между панелями меняет размер через переданный setter, зажатый
   в границах [min, max]. */

export function useResizeDrag(
  axis: "x" | "y",
  size: number,
  setSize: (v: number) => void,
  min: number,
  max: number,
  invert = false,
) {
  const startRef = useRef({ pos: 0, size: 0 });

  const onPointerDown = useCallback(
    (e: ReactPointerEvent) => {
      e.preventDefault();
      startRef.current = { pos: axis === "x" ? e.clientX : e.clientY, size };
      const target = e.currentTarget;
      target.setPointerCapture(e.pointerId);

      const onMove = (ev: PointerEvent) => {
        const cur = axis === "x" ? ev.clientX : ev.clientY;
        const delta = cur - startRef.current.pos;
        const next = startRef.current.size + (invert ? -delta : delta);
        setSize(Math.min(max, Math.max(min, next)));
      };
      const onUp = () => {
        window.removeEventListener("pointermove", onMove);
        window.removeEventListener("pointerup", onUp);
      };
      window.addEventListener("pointermove", onMove);
      window.addEventListener("pointerup", onUp);
    },
    [axis, size, setSize, min, max, invert],
  );

  return onPointerDown;
}

export function ResizeHandle({
  axis,
  onPointerDown,
}: {
  axis: "x" | "y";
  onPointerDown: (e: ReactPointerEvent) => void;
}) {
  return (
    <div
      onPointerDown={onPointerDown}
      className={
        axis === "x"
          ? "group relative w-[3px] flex-none cursor-col-resize select-none"
          : "group relative h-[3px] flex-none cursor-row-resize select-none"
      }
    >
      <div
        className={
          "absolute bg-line-strong opacity-0 transition-opacity group-hover:opacity-100 group-active:bg-acid group-active:opacity-100 " +
          (axis === "x" ? "inset-y-0 left-1/2 w-px -translate-x-1/2" : "inset-x-0 top-1/2 h-px -translate-y-1/2")
        }
      />
    </div>
  );
}
