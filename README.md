# HeadShow

HeadShow is an AllayMC plugin that customizes player nametags and scoretags using PlaceholderAPI.

## Requirements

- Java 21 or higher
- Allay server
- PlaceholderAPI plugin

## Installation

1. Build the plugin:

```bash
./gradlew shadowJar
```

2. Copy the jar from `build/libs` into the server `plugins` directory.
3. Install PlaceholderAPI and start the server.

## Configuration

`config.yml` is generated on first run.

```yaml
nametag: "{player_name}"
scoretag: "{player_ping}ms"
update-interval: 20
```

- `nametag` and `scoretag` support PlaceholderAPI variables.
- `update-interval` is in ticks (20 ticks = 1 second).
- Set `nametag` or `scoretag` to an empty string to clear it.

## Development

- `./gradlew runServer` to start a local server with the plugin.
