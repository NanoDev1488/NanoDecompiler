import { cn } from "../utils/cn";

export function Toggle({
  checked,
  onChange,
  label,
}: {
  checked: boolean;
  onChange: (next: boolean) => void;
  label: string;
}) {
  return (
    <button
      type="button"
      role="switch"
      aria-checked={checked}
      onClick={() => onChange(!checked)}
      className={cn(
        "relative h-[20px] w-[36px] flex-none rounded-full transition-colors duration-150",
        checked ? "bg-acid" : "bg-line-strong",
      )}
      aria-label={label}
    >
      <span
        className={cn(
          "absolute top-[2px] left-[2px] block size-[16px] rounded-full bg-bg transition-transform duration-150",
          checked && "translate-x-[16px]",
        )}
      />
    </button>
  );
}

export function Kbd({ children }: { children: string }) {
  return <span className="kbd">{children}</span>;
}
