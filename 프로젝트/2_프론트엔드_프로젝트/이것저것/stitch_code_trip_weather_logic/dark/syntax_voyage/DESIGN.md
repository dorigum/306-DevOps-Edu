# Design System Document

## 1. Overview & Creative North Star
### The Creative North Star: "The Terminal Nomad"
This design system rejects the "cookie-cutter" travel agency aesthetic. Instead, it positions itself as a high-end digital terminal for the spontaneous traveler. We are merging the raw, structural beauty of a code editor with the lush, immersive escapism of travel photography. 

The experience is defined by **Intentional Asymmetry** and **Editorial Layering**. We avoid rigid, center-aligned templates. Instead, we use "code-like" indentation for content blocks, overlapping photography, and high-contrast typography scales that make every page feel like a bespoke digital magazine. The aesthetic is "Syntax & Scenery"—where the precision of the logic meets the chaos of the journey.

---

## 2. Colors
Our palette is rooted in a deep, nocturnal base (`surface: #131313`) punctuated by high-vibrancy "syntax" accents.

*   **Syntax Accents:** 
    *   **Primary (`primary: #c3f5ff`):** Our "Cyan Variable." Used for high-priority calls to action and weather-driven focal points.
    *   **Secondary (`secondary: #a8e430`):** Our "Lime Function." Represents spontaneous action and positive "Success" states.
    *   **Tertiary (`tertiary: #f4e8ff`):** Our "Purple Comment." Soft, elegant accents for meta-data and decorative developer-themed details.

### The "No-Line" Rule
**Explicit Instruction:** Do not use 1px solid borders to section off the UI. This is a common hallmark of "standard" design. In this system, boundaries are defined exclusively by:
1.  **Background Color Shifts:** A section on `surface` can transition into a `surface-container-low` section to indicate a change in context.
2.  **Tonal Transitions:** Using subtle shifts between `surface-container-highest` and `surface-dim` to create edge definition without harsh lines.

### Surface Hierarchy & Nesting
Treat the UI as a physical stack of semi-transparent layers. 
*   **Lowest Layer:** `surface-container-lowest` (#0e0e0e) for the deep background.
*   **Mid-Level:** `surface-container` (#202020) for standard content blocks.
*   **Active Layer:** `surface-container-highest` (#353535) for cards or interactive elements that need to "pop."

### The "Glass & Gradient" Rule
To elevate the "developer" look into something premium:
*   **Glassmorphism:** Use `surface-variant` with a `backdrop-blur` of 12px–20px for floating navigation bars or weather overlays.
*   **Signature Gradients:** Use a subtle linear gradient from `primary` (#c3f5ff) to `primary-container` (#00e5ff) for primary CTAs. This adds a "glow" effect reminiscent of a high-end monitor.

---

## 3. Typography
We utilize a dual-font strategy that pits the technical against the editorial.

*   **Display & Headlines (Space Grotesk):** This font carries the "dev" spirit. Its geometric, slightly technical curves feel like a high-end monospaced font but with the readability of a headline face. 
    *   *Usage:* Use `display-lg` (3.5rem) with tight letter-spacing for destination names.
*   **Body & Titles (Inter):** The workhorse. Clean, neutral, and premium.
    *   *Usage:* Use `body-md` for descriptions. Ensure a high contrast in weight between `title-lg` and `body-sm` to maintain editorial hierarchy.
*   **Labels & Metadata (Space Grotesk - Monospaced Accents):** Use `label-sm` in all-caps or with "code" symbols (e.g., `// DATE: 10.24.24`) to lean into the developer aesthetic.

---

## 4. Elevation & Depth
We eschew traditional "drop shadows" in favor of **Tonal Layering**.

*   **The Layering Principle:** Depth is achieved by "stacking" surface tiers. An element on `surface-container-low` should contain a card set to `surface-container-highest`. This creates a soft, natural lift.
*   **Ambient Shadows:** For floating elements (like "Book Now" modals), use a diffused shadow: `box-shadow: 0 20px 40px rgba(0, 0, 0, 0.4)`. The shadow should feel like ambient light blockage, not a black outline.
*   **The "Ghost Border" Fallback:** If a container absolutely requires a border for accessibility, use `outline-variant` at 15% opacity. It should be felt, not seen.
*   **Brutalism & Brackets:** Use photography that overlaps container edges. A "code snippet" card might have a `primary` color "bracket" icon floating halfway off the edge of the card, breaking the grid and adding depth.

---

## 5. Components

### Cards & Lists
*   **Visual Direction:** Cards must never use dividers. Separate content using `vertical whitespace` (24px–32px) or a background shift from `surface-container-low` to `surface-container-high`.
*   **Image Handling:** Cards should prioritize high-quality photography with a `rounded-xl` (0.75rem) corner radius. Use a `surface-tint` overlay at 10% on images to tie them into the dark theme.

### Buttons
*   **Primary:** A "syntax-highlight" block. Background: `primary` gradient; Text: `on_primary_fixed_variant`. No border. Rounded: `md`.
*   **Secondary/Ghost:** `outline` text with a trailing code symbol (e.g., `View Destination ->`).
*   **Tertiary:** Monospaced text in `secondary` (#a8e430), e.g., `[ edit_trip ]`.

### Chips (Code Tags)
*   Used for weather or flight data. 
*   **Style:** `surface-container-highest` background, `label-sm` typography. Surround text with brackets: `[ 72°F ]` or `< Spontaneous />`.

### Input Fields
*   **The Terminal Input:** Dark backgrounds (`surface-container-lowest`). Use a flashing cursor animation (a 2px wide `primary` vertical bar) to mimic a terminal window. No visible border—only a `primary` underline on focus.

### "Syntax" Breadcrumbs
*   Instead of `Home > Travel > Paris`, use `root / travel / paris`. This reinforces the developer aesthetic without being overbearing.

---

## 6. Do's and Don'ts

### Do:
*   **Do** use `Space Grotesk` for all numerical data (weather, prices, dates).
*   **Do** use asymmetrical layouts where photography is slightly offset from the text containers.
*   **Do** treat weather icons as "syntax symbols." Use thin-line SVG icons that match the `outline` color.
*   **Do** use "comments" in the UI: `/* Note: Prices may fluctuate based on cloud cover */`.

### Don't:
*   **Don't** use 1px solid borders or `#000000` shadows. It kills the premium "glassy" feel.
*   **Don't** use standard sans-serif fonts for data; it loses the developer aesthetic.
*   **Don't** use generic travel icons. Avoid the "clipart" look. Icons must be precise, geometric, and technical.
*   **Don't** center everything. Spontaneous travel feels more authentic when the layout feels like a "log" or a "manifest."