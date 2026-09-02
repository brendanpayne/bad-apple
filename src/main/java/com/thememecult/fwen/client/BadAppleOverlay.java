package com.thememecult.fwen.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.thememecult.fwen.BadApple;
import com.thememecult.fwen.Fwen;
import com.thememecult.fwen.FwenEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

@EventBusSubscriber(modid = Fwen.MOD_ID, value = Dist.CLIENT)
public final class BadAppleOverlay {
    private BadAppleOverlay() {}

    private static final ResourceLocation ATLAS =
            ResourceLocation.fromNamespaceAndPath(Fwen.MOD_ID, "textures/gui/bad_apple_atlas.png");

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        if (!FwenClientConfig.OVERLAY_ENABLED.get()) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) {
            return;
        }

        MobEffectInstance effect = mc.player.getEffect(FwenEffects.BAD_APPLE);
        if (effect == null) {
            return;
        }

        int frame = frameFor(effect, event.getPartialTick().getGameTimeDeltaPartialTick(false));

        int u = (frame % BadApple.COLUMNS) * BadApple.CELL_WIDTH;
        int v = (frame / BadApple.COLUMNS) * BadApple.CELL_HEIGHT;

        GuiGraphics graphics = event.getGuiGraphics();
        int width = mc.getWindow().getGuiScaledWidth();
        int height = mc.getWindow().getGuiScaledHeight();

        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        graphics.blit(ATLAS,
                0, 0, width, height,  // destination, stretched to fill
                u, v,                                     // source origin in the atlas
                BadApple.CELL_WIDTH, BadApple.CELL_HEIGHT,
                BadApple.ATLAS_WIDTH, BadApple.ATLAS_HEIGHT);

        RenderSystem.disableBlend();
        // Gui.render leaves depth testing enabled around the layer manager.
        RenderSystem.enableDepthTest();
    }

    /** Frame index from the effect's own remaining duration */
    public static int frameFor(MobEffectInstance effect, float partialTick) {
        float elapsedTicks = (BadApple.DURATION_TICKS - effect.getDuration()) + partialTick;
        int frame = (int) (elapsedTicks * BadApple.FPS / 20.0F);
        if (frame < 0) {
            return 0;
        }
        return Math.min(frame, BadApple.FRAME_COUNT - 1);
    }
}
