package fun.faulkner.moon_phase;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;


@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MoonPhase.MOD_ID)
public class RenderHUDEvent {
    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;

        if (!HUDUtil.isNight(mc)) return;

        MatrixStack matrixStack = event.getMatrixStack();
        FontRenderer fontRenderer = mc.font;

        int windowWidth = mc.getWindow().getGuiScaledWidth();
        int windowHeight = mc.getWindow().getGuiScaledHeight();

        String brightnessText = HUDUtil.getBrightnessText(mc);
        String timeLeftText = HUDUtil.getTimeLeftText(mc);
        String moonPhaseText = HUDUtil.getMoonPhaseText(mc);

        int maxTextWidth = HUDUtil.getMaxTextWidth(fontRenderer, brightnessText, timeLeftText, moonPhaseText);

        Point textPosition = HUDUtil.getTextPosition(windowWidth, windowHeight, maxTextWidth, fontRenderer.lineHeight);

        boolean shouldShowMoonPhase = ConfigHandle.showMoonPhaseOverBrightness.get();
        boolean shouldShowTimeLeft = ConfigHandle.showTimeLeft.get();

        if (shouldShowMoonPhase) {
            drawString(matrixStack, fontRenderer, moonPhaseText, textPosition.x, textPosition.y + fontRenderer.lineHeight * 2, 0xffffff);
        }
        else {
            drawString(matrixStack, fontRenderer, brightnessText, textPosition.x, textPosition.y + fontRenderer.lineHeight * 2, 0xffffff);
        }
        if (shouldShowTimeLeft) {
            drawString(matrixStack, fontRenderer, timeLeftText, textPosition.x, textPosition.y + fontRenderer.lineHeight, TextFormatting.DARK_AQUA.getColor());
        }
    }

    private static void drawString(MatrixStack matrixStack, FontRenderer fontRenderer, String text, int x, int y, int color) {
        fontRenderer.draw(matrixStack, text, x, y, color);
    }
}