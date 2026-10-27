# HMCL Neon Disable Window Decorator

An HMCL CE / HMCL Neon plugin that removes the launcher's custom window decoration: no drop
shadow, no 8 px outer padding, and no rounded corners. The window content fills the transparent
stage edge to edge.

## What it changes

HMCL draws its own decoration on a `StageStyle.TRANSPARENT` stage. This plugin mixes into two
internal classes and forces the "edge to edge" branch:

| Target | Method | Effect |
| --- | --- | --- |
| `org.jackhuang.hmcl.ui.decorator.Decorator` | `updateWindowDecoration(boolean)` | Forces `true`, so `root` padding becomes `Insets.EMPTY` and the `DropShadow` is not installed. |
| `org.jackhuang.hmcl.ui.decorator.MainWindowPane` | `setWindowEdgeToEdge(boolean)` | Forces `true`, so the clip's arc width/height stay at `0.0`. |

Window resizing, dragging, maximize and full-screen behavior continue to use HMCL's own
handling. Because the shadow insets become empty, the resize hit area falls back to HMCL's
8 px inward `RESIZE_INSETS`.

## Requirements

- HMCL CE / HMCL Neon `26.8` or newer
- Java 17 or newer
- A local HMCL jar for compilation (`HMCL_JAR` or `lib/HMCL-*.jar`)

## Build

```bash
export HMCL_JAR=/path/to/HMCL-CE-26.8-release.2.jar
./gradlew packageNpl
```

The package is written to `build/npl/io.github.nixie24.disablewindowdecorator-v1.0.0.npl`.
Install it through HMCL's plugin manager and restart the launcher; Mixin changes are applied
before the UI classes load.

## Release

Pushing a `v*` tag runs `.github/workflows/release.yml`, which:

1. downloads the pinned HMCL Neon jar,
2. builds and validates the `.npl`,
3. writes the bound `packageUrl`, `sha256` and `size` into `manifest.json`,
4. creates the GitHub Release and pushes `manifest.json` to the default branch.

The repository must keep the lowercase `hmclce` topic for community discovery.

## Compatibility

The plugin targets HMCL internals, not a stable public API. If HMCL Neon renames
`updateWindowDecoration`, `setWindowEdgeToEdge`, or changes the shadow/clip fields, the mixin
will fail to apply. `injectors.defaultRequire = 1` makes that failure explicit instead of
silently leaving the decoration enabled.

## License

MIT. HMCL itself is GPL-3.0-or-later; redistributing a plugin that links against HMCL may
require GPL-compatible terms. Review this before publishing to a wider audience.
