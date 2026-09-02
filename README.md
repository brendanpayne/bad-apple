# Bad Apple

A joke consumable for a private Minecraft modpack.

An unappetizing looking consumable, the **Bad Apple**. Eat it, and the
Touhou "Bad Apple!!" shadow art video plays fullscreen over your HUD until it
finishes, about three and a half minutes later.

The video is pure black-and-white silhouette, so the white areas are simply
transparent and the game carries on underneath. You can still walk around. You
just cannot see very much.

## Photosensitivity

The overlay is high-contrast black and white covering the entire screen.

If that is a problem for you, turn it off in `config/fwen-client.toml`:

```toml
[overlay]
    enabled = false
```

The item still works, it just draws nothing. Drinking milk also cancels the
effect at any time, like any other status effect.

## Contents

| | |
|---|---|
| Item | `fwen:bad_apple` — edible, roughly apple-tier |
| Effect | `fwen:bad_apple` — harmless, does nothing but play the video |
| Recipe | Apple + ink sac, shapeless |

The recipe is a placeholder. In the pack proper it is made with a Create Spout
using nuclear waste, added separately.

## Requirements

- Minecraft 1.21.1
- NeoForge 21.1.248 or newer

No other mods or libraries required.

## Building

```
./gradlew build
```

The jar lands in `build/libs/`. To try it in a dev client:

```
./gradlew runClient
```

## Credits

"Bad Apple!!" is originally from Touhou (ZUN / Team Shanghai Alice), with the
shadow art video by Anira. The audio and video assets bundled here belong to
their respective creators and are included for use in a private pack.

Code is All Rights Reserved.
