# FastStats Java

Documentation: https://docs.faststats.dev/java

## Building

Run Gradle from the repository root. The library modules use the standard Java lifecycle, while deployable example plugins and mods use the packaging task expected by their platform.

### Libraries

Use `build` for the reusable FastStats libraries:

```sh
./gradlew :core:build
./gradlew :config:build
./gradlew :bukkit:build
./gradlew :bungeecord:build
./gradlew :fabric:build
./gradlew :hytale:build
./gradlew :minestom:build
./gradlew :nukkit:build
./gradlew :sponge:build
./gradlew :velocity:build
```

Library jars are written to each module's `build/libs` directory. Fabric also produces a remapped jar through Fabric Loom as part of its build output.

### Bukkit, BungeeCord, Hytale, Minestom, Nukkit, Sponge, and Velocity examples

These examples use Shadow so FastStats is bundled into the deployable plugin or server jar. Build the `shadowJar` task directly when you want the artifact to install or run:

```sh
./gradlew :bukkit:example-plugin:shadowJar
./gradlew :bungeecord:example-plugin:shadowJar
./gradlew :hytale:example-plugin:shadowJar
./gradlew :minestom:example-server:shadowJar
./gradlew :nukkit:example-plugin:shadowJar
./gradlew :sponge:example-plugin:shadowJar
./gradlew :velocity:example-plugin:shadowJar
```

Use the `*-all.jar` file from the example module's `build/libs` directory.

### Fabric example mod

Fabric mods should be packaged by Fabric Loom, not Shadow. Build the remapped mod jar with:

```sh
./gradlew :fabric:example-mod:remapJar
```

Use `fabric/example-mod/build/libs/example-mod-<version>.jar`. Do not use a Shadow `*-all.jar` for Fabric; it can bundle Minecraft and loader internals into the mod.

### Building everything

To compile and test all modules with the standard lifecycle, run:

```sh
./gradlew build
```

For deployable example artifacts, run the platform-specific commands above after `build` or instead of it.
