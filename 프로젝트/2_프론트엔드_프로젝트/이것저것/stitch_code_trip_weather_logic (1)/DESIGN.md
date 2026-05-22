# Design System Document

## 1. Overview & Creative North Star
The Creative North Star for this design system is **"The Syntactic Voyager."** 

This concept bridges the gap between the structured logic of software development and the fluid, expansive world of global travel. We are moving away from the "template" look of traditional travel sites. Instead of rigid grids and 1px borders, we utilize **Intentional Asymmetry** and **Tonal Layering** to create an editorial experience that feels curated, not generated. 

The aesthetic is a "White-Box IDE"—a clean, high-clarity canvas where travel content is treated with the same precision as a beautifully refactored codebase. We use Plus Jakarta Sans to provide a friendly, modern voice, while syntax-inspired color accents provide the "playful" developer soul.

---

## 2. Colors & Surface Philosophy
The palette is rooted in high-contrast clarity, using a white-based UI with deep, intellectual blues and vibrant, "syntax-style" secondary accents.

### The "No-Line" Rule
**Explicit Instruction:** Designers are prohibited from using 1px solid borders to section off content. Boundaries must be defined solely through background color shifts. Use `surface-container-low` (#eff4ff) for large sections to distinguish them from the main `surface` (#f8f9ff). If a card needs to stand out, place a `surface-container-lowest` (#ffffff) element on top of a `surface-container` (#e6eeff) background.

### Surface Hierarchy & Nesting
Treat the UI as a series of physical layers.
*   **Level 0 (Base):** `surface` (#f8f9ff)
*   **Level 1 (Sectioning):** `surface-container-low` (#eff4ff)
*   **Level 2 (Active Cards):** `surface-container-lowest` (#ffffff)
*   **Level 3 (Pop-overs/Modals):** `surface-bright` (#f8f9ff)

### The Glass & Gradient Rule
To achieve a premium feel, floating elements (like navigation bars or "Quick-Book" widgets) must use **Glassmorphism**. Apply a semi-transparent version of `surface` with a 12px to 20px backdrop-blur. 

### Signature Textures
Main CTAs and Hero sections should avoid flat colors. Use a subtle linear gradient (135°) transitioning from `primary` (#0c5bae) to `primary-container` (#3674c9). This provides a "visual soul" and depth that mimics high-end hardware interfaces.

---

## 3. Typography
We use **Plus Jakarta Sans** exclusively to maintain a modern, geometric, yet approachable rhythm.

*   **Display (lg/md/sm):** Used for "Hero" destinations. Use tight letter-spacing (-0.02em) to create an authoritative, editorial feel.
*   **Headline (lg/md/sm):** Reserved for section titles (e.g., "Top Commits in Seoul"). These drive the content-heavy layout.
*   **Title (lg/md/sm):** Used for card headings and navigation items.
*   **Body (lg/md/sm):** Optimized for long-form travel guides. Ensure a line-height of at least 1.6 for maximum accessibility.
*   **Label (md/sm):** This is our "Syntax Layer." These are used for tags, metadata (e.g., `LAT/LONG`, `PR_OPEN`), and technical travel specs. Use uppercase with increased letter-spacing (+0.05em) for labels.

---

## 4. Elevation & Depth
In this system, depth is a result of **Tonal Layering**, not structural scaffolding.

*   **The Layering Principle:** Stack containers to create natural lift. A `surface-container-lowest` card sitting on a `surface-container-high` section creates a soft, sophisticated elevation without the need for heavy shadows.
*   **Ambient Shadows:** If an element must float (e.g., a "Commit Booking" button), use a shadow with a 32px blur, 0px spread, and 6% opacity. The shadow color must be a tinted version of `on-surface` (#0d1c2e), never pure black.
*   **The "Ghost Border" Fallback:** If accessibility requires a stroke (e.g., on an input field), use `outline-variant` (#c2c6d3) at **15% opacity**. This creates a "suggestion" of a boundary rather than a hard wall.
*   **Glassmorphism:** Use `surface-variant` (#d5e3fc) at 70% opacity with a blur for a "frosted glass" effect on top of content-heavy image backgrounds.

---

## 5. Components

### Buttons
*   **Primary:** Gradient of `primary` to `primary-container`. Corner radius `full` (9999px). No border.
*   **Secondary:** `surface-container-high` background with `primary` text.
*   **Tertiary:** Text-only, using `primary` color with a `label-md` weight.

### Syntax Chips (Tags)
These represent the "Code" aesthetic. Use syntax-highlighting logic:
*   **Location Tags:** `tertiary-container` (#0075d7) background with `on-tertiary-container` (#fefcff) text.
*   **Status Tags:** `secondary-container` (#f0e49d) for "Available" or "Open Source" vibes.
*   **Radius:** `sm` (0.25rem) to mimic the look of code editor tokens.

### Input Fields
*   **Style:** `surface-container-lowest` background. 
*   **Border:** "Ghost Border" (15% `outline-variant`).
*   **State:** On focus, transition the ghost border to 100% `primary` opacity and add a subtle `primary-fixed` glow.

### Cards & Lists
**Forbid the use of divider lines.**
*   To separate list items, use a 12px vertical gap.
*   For cards, use a `xl` (1.5rem) corner radius.
*   Group content using "Code Blocks"—wrap related info in a `surface-container-low` background with an `md` (0.75rem) radius.

### The "Terminal" Widget (App Specific)
A unique component for this system: A dark-themed `inverse-surface` (#233144) box used for displaying "Trip Metadata" (Flight numbers, coordinates, weather strings) in a monospaced-adjacent style using the `label-sm` token.

---

## 6. Do's and Don'ts

### Do:
*   **Do** use white space as your primary separator. If in doubt, double the margin.
*   **Do** mix "Rounded XL" containers with "Rounded SM" tags to create a professional/playful contrast.
*   **Do** use `primary-fixed-dim` (#a9c7ff) for subtle background accents behind iconography.

### Don't:
*   **Don't** use 1px solid borders for anything other than high-contrast accessibility needs.
*   **Don't** use pure black (#000000). Use `on-background` (#0d1c2e) for all text to maintain tonal harmony.
*   **Don't** use standard "Drop Shadows" from a UI kit. Only use the Ambient Shadow spec mentioned in Section 4.
*   **Don't** overcrowd the layout. This design system requires "breathing room" to feel premium. Fall back to the "Visit Korea" editorial spacing for content-heavy pages.