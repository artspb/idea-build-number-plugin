# Build Number Status Bar Widget

IntelliJ Platform plugin based on the
[IntelliJ Platform Plugin Template](https://github.com/JetBrains/intellij-platform-plugin-template).
All versions and compatibility ranges live in `gradle.properties`.

## Updating compatibility to a new IDE release

IDE versions map to branch numbers as `YYYY.N` → `YYN` (e.g. 2027.1 → `271`).

Make two separate commits:

1. **Increase until build** — set `pluginUntilBuild = <branch>.*` in `gradle.properties`.
   - Commit message: `Increase until build to <branch>.*` (e.g. `Increase until build to 271.*`).
   - Leave `pluginSinceBuild` and `platformVersion` alone unless explicitly asked to change them.
   - Verify with `./gradlew patchPluginXml` and check `until-build` in
     `build/tmp/patchPluginXml/plugin.xml`.
2. **Release** — bump the minor version of `pluginVersion` in `gradle.properties`
   (e.g. `1.19.0` → `1.20.0`) and add an entry right below `## [Unreleased]` in `CHANGELOG.md`:

   ```markdown
   ## [1.20.0]

   - 2027.1 compatibility.
   ```

   - Commit message: `Release <version>` (e.g. `Release 1.20.0`).
   - Tag the release commit with a lightweight tag `v<version>` (e.g. `git tag v1.20.0 <release commit>`).
   - Do not add the compare links at the bottom of `CHANGELOG.md`; the release workflow's
     `Changelog update - v<version>` commit adds them.

## Git

- Commit only the files that belong to the change (`git commit -- <paths>`); the index may hold unrelated
  staged files.
- Do not push or create releases unless asked.
