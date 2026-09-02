package com.thememecult.fwen.client;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Client-only config. Lives in {@code config/fwen-client.toml}.
 */
public final class FwenClientConfig {
    private FwenClientConfig() {}

    public static final ModConfigSpec SPEC;

    /** Master switch for the fullscreen overlay. Photosensitivity escape hatch. */
    public static final ModConfigSpec.BooleanValue OVERLAY_ENABLED;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.comment(
                "Bad Apple!! overlay.",
                "",
                "PHOTOSENSITIVITY: the overlay is a high-contrast black-and-white video that",
                "covers the whole screen. Set enabled = false to disable it completely.",
                "The status effect still applies, it just draws nothing.",
                "Drinking milk also removes the effect at any time."
        ).push("overlay");

        OVERLAY_ENABLED = builder
                .comment("Draw the fullscreen Bad Apple!! overlay while the effect is active.")
                .define("enabled", true);

        builder.pop();

        SPEC = builder.build();
    }
}
