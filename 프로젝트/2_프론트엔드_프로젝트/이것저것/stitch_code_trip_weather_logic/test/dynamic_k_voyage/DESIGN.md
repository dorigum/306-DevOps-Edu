# Design System Document

## 1. Overview & Creative North Star: "The Digital Curator"

This design system moves beyond the standard travel portal to become a **Digital Curator**. It is designed to feel like a high-end travel editorial—sophisticated, breathable, and deeply intentional. While inspired by the vibrant energy of Korean tourism, the execution avoids "template" rigidity in favor of organic flow and layered depth.

**The Creative North Star** is centered on three pillars:
*   **Asymmetric Harmony:** Breaking the grid with overlapping imagery and offset text to create a sense of movement and discovery.
*   **Atmospheric Depth:** Using tonal shifts instead of lines to create a soft, inviting environment.
*   **High-Definition Clarity:** Prioritizing generous white space and bold typography to let world-class imagery breathe.

Through the use of **plusJakartaSans**, we evoke a contemporary, global feel that remains welcoming and highly legible. This is not just a UI; it is a premium canvas for cultural storytelling.

---

## 2. Colors & Tonal Architecture

Our palette is anchored in a "Deep Sea" primary blue and a "Gilded Sun" secondary yellow. However, the secret to this system’s premium feel lies in its neutral foundation.

### The Color Logic
*   **Primary (#0c5bae):** Used for authoritative moments and primary actions.
*   **Secondary (#7c5800):** Used for "discovery" accents and highlighting cultural significance.
*   **Tertiary (#00657b):** A sophisticated teal for environmental or nature-related content categories.

### The "No-Line" Rule
**Explicit Instruction:** Prohibit the use of 1px solid borders for sectioning content. Boundaries must be defined solely through background color shifts. To separate a section, transition from `surface` to `surface-container-low`.

### Surface Hierarchy & Nesting
Treat the UI as physical layers of fine paper. 
*   **Base:** Use `surface` (#fbf9f9) for the main canvas.
*   **Nesting:** Place a `surface-container-low` card within a `surface-container` section to create "natural" depth. The hierarchy of importance is defined by the subtle shift in lightness, not by adding structural lines.

### The "Glass & Gradient" Rule
To add "soul" to the digital interface:
*   **Glassmorphism:** For floating navigation or over-image labels, use `surface` at 70% opacity with a `24px` backdrop-blur. 
*   **Signature Gradients:** Main CTAs or Hero sections should utilize a subtle linear gradient from `primary` (#0c5bae) to `primary-container` (#3674c9) at a 135-degree angle. This prevents the "flat" look of standard frameworks.

---

## 3. Typography: Editorial Authority

We use **Plus Jakarta Sans** to balance modern tech aesthetics with humanist warmth.

| Level | Token | Size | Weight | Intent |
| :--- | :--- | :--- | :--- | :--- |
| **Display** | `display-lg` | 3.5rem | 700 | Large-scale editorial impact for Hero titles. |
| **Headline** | `headline-lg` | 2rem | 600 | Defining major content sections. |
| **Title** | `title-lg` | 1.375rem | 600 | Card headings and sub-headers. |
| **Body** | `body-lg` | 1rem | 400 | Long-form reading and descriptions. |
| **Label** | `label-md` | 0.75rem | 500 | Metadata, tags, and overlines. |

**Stylistic Note:** Always pair `display-lg` with generous letter-spacing (-0.02em) to maintain a "tight" editorial look. Increase line-height for `body-lg` to 1.6 for maximum readability.

---

## 4. Elevation & Depth

We eschew traditional shadows in favor of **Tonal Layering**.

*   **The Layering Principle:** Depth is achieved by "stacking" surface tokens. A `surface-container-lowest` card placed on a `surface-container-high` background creates a "lifted" effect that feels integrated into the environment.
*   **Ambient Shadows:** If a floating element (like a Modal or FAB) requires a shadow, use a "Cloud Shadow": `box-shadow: 0 20px 40px rgba(27, 28, 28, 0.06)`. The shadow must be low-opacity and tinted by the `on-surface` color.
*   **The "Ghost Border" Fallback:** If a border is required for accessibility, use `outline-variant` (#c2c6d3) at **15% opacity**. Never use a 100% opaque border.

---

## 5. Components

### Buttons
*   **Primary:** Rounded `xl` (1.5rem), using the Signature Gradient. No border.
*   **Secondary:** `surface-container-highest` background with `on-surface` text.
*   **Tertiary:** Ghost style; text-only with `primary` color, transitioning to a `surface-variant` background on hover.

### Chips (Discovery Tags)
*   **Style:** Pill-shaped (`full` roundedness). 
*   **Color:** `primary-fixed` background with `on-primary-fixed` text for high contrast without the aggression of a dark background.

### Cards (The "Container" Rule)
*   **Rule:** Forbid divider lines within cards. 
*   **Spacing:** Use `2rem` (32px) padding as a standard. Separate the image from the text using a `surface-container-lowest` background for the text area and a `surface-dim` background for the image placeholder.

### Input Fields
*   **Style:** Soft-filled. Use `surface-container-high` as the background. 
*   **Active State:** Instead of a thick border, use a 2px `primary` underline or a subtle `primary` glow (using the Ghost Border rule).

### Contextual Components: "The Cultural Carousel"
*   **Asymmetric Imagery:** Create a component where the image slightly breaks the container's top boundary, creating a 3D effect.
*   **Curated Tooltips:** Use `inverse-surface` with `80%` opacity and `md` (0.75rem) roundedness for a premium, non-obstructive information layer.

---

## 6. Do's and Don'ts

### Do
*   **Do** use asymmetrical layouts where images and text blocks overlap.
*   **Do** use white space as a structural element to group related items.
*   **Do** apply `xl` (1.5rem) corner radii to all large containers and images to maintain the "welcoming" theme.
*   **Do** use `backdrop-blur` on headers to keep the UI feeling "light" as the user scrolls over vibrant images.

### Don't
*   **Don't** use 1px solid borders to separate sections. Use background color shifts.
*   **Don't** use pure black (#000000) for text. Always use `on-surface` (#1b1c1c) for a softer, more premium reading experience.
*   **Don't** crowd the interface. If it feels full, increase the `surface` padding.
*   **Don't** use high-contrast shadows. If you can clearly see where the shadow ends, it is too dark.