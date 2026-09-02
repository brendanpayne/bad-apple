package com.thememecult.fwen.client;

import com.thememecult.fwen.Fwen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

/**
 * Client entry point. Never classloaded on a dedicated server.
 */
@Mod(value = Fwen.MOD_ID, dist = Dist.CLIENT)
public class FwenClient {
    public FwenClient(ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, FwenClientConfig.SPEC, "fwen-client.toml");
    }
}
