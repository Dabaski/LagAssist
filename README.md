# LagAssist

A Folia-compatible fork of [LagAssist](https://www.spigotmc.org/resources/lagassist-%E2%9A%A1-advanced-performance-solution-%E2%9A%A1-1-8-1-19-x-compatible.56399/) — an advanced anti-lag solution for Minecraft servers.

```
folia-supported: true
api-version: 1.21
```

Tested on **Folia 26.1.2 (master)**. Compatible with Paper 1.21.4+.

## Download

Pre-built jars are available under [Releases](https://github.com/Dabaski/LagAssist/releases).

## Building

Requires JDK 26+.

```bash
./gradlew build
```

Output: `build/libs/lagassist-<version>.jar`

## Features

| System | Description |
|---|---|
| **Smart Mob Cleaner** | Culls excess mobs when TPS drops below a configurable threshold |
| **Entity Stacker** | Lightweight mob stacking using cubic chunk-splitting algorithm |
| **Redstone Culler** | Detects and removes laggy redstone contraptions and observer machines |
| **Chunk Analyzer** | Scores chunks by lag contribution and lists the worst offenders |
| **Chunk Limiter** | Per-chunk entity/tile entity soft and hard limits |
| **Dynamic View Distance** | Auto-adjusts player view distance based on server load (Paper only) |
| **Hopper Manager** | Chunk hoppers, mob hoppers, sell hoppers, filter GUI, hopper sabotage |
| **Spawner Manager** | Custom spawner delay, range, and amount; auto-disable when TPS drops |
| **Safety Manager** | Disk space monitoring, anti-crasher packet limiting |
| **StatsBar** | Action-bar display of TPS, memory, chunks, entities |
| **Lag Map** | In-game TPS visualization on map items |
| **Log Purger** | Auto-deletes old logs by size/age |
| **Benchmarking** | Download/upload speed test, ping test, max-player approximation |

## Commands

`/lagassist` (alias `/la`) — opens the admin GUI. Subcommands:

| Command | Permission | Description |
|---|---|---|
| `mobculler` | — | Manually cull mobs |
| `redstoneculler` | — | Manually cull redstone |
| `togglespawning` | — | Toggle mob spawning |
| `togglephysics` | — | Toggle physics denial |
| `chunkanalyse` | `lagassist.chunkanalyse` | List laggiest chunks |
| `pregench` | `lagassist.generatechunks` | Pre-generate chunks |
| `stopgen` | `lagassist.generatechunks` | Stop chunk generation |
| `statsbar` | — | Toggle action-bar stats |
| `benchmark` | — | Run server benchmark |
| `ping` | — | Run ping test |
| `reload` | `lagassist.reload` | Reload config |
| `version` | — | Show version info |
| `changelog` | — | Show latest changelog |

## Configuration

Config file: `plugins/LagAssist/server.yml`

See the full [default config](src/main/resources/server.yml) for all available options.

## Dependencies

- **Required:** Paper 1.21.4+ or Folia 26.1.2+
- **Optional:** Vault (sell hopper economy)

## Building for Folia

A pre-compiled Folia 26.1.2 server jar is included in releases for testing. To build Folia yourself:

```bash
git clone https://github.com/PaperMC/Folia.git
cd Folia
./gradlew applyAllPatches
./gradlew build
./gradlew :folia-server:createPaperclipJar
```

## Known Issues

- **3 one-time ClassNotFoundException warnings at startup** — These are from legacy NMS reflection used by the chunk pregeneration feature and are completely harmless on Folia/Paper 1.21+. They do not affect functionality.
- **Chunk pregeneration** — The legacy NMS-based chunk pregenerator is not available on Folia. Use [Chunky](https://modrinth.com/plugin/chunky) instead.

## Credits

- **[Stefatorus](https://github.com/Stefatorus)** — original plugin author and permission to publish
- **[EntryRise](https://git.entryrise.com/stefatorus/LagAssist)** — original publisher
- **[erxson](https://github.com/erxson)** — Gradle build support
- **[Dabaski](https://github.com/Dabaski)** — Folia compatibility, bug fixes