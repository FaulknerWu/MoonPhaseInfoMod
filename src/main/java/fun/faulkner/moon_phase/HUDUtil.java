package fun.faulkner.moon_phase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;

import java.awt.*;

public class HUDUtil {

    public static TextFormatting getBrightnessTextColor(int brightnessPercent) {
        TextFormatting[] fullnessColor = new TextFormatting[]{
                TextFormatting.RED, // 0%
                TextFormatting.GOLD, // 25%
                TextFormatting.YELLOW, // 50%
                TextFormatting.GREEN, // 75%
                TextFormatting.DARK_GREEN // 100%
        };
        int colorIndex = Math.min(brightnessPercent / 25, fullnessColor.length - 1);
        return fullnessColor[colorIndex];
    }

    public static boolean isNight(Minecraft mc) {
        long time = mc.level.getDayTime() % 24000;
        return time >= 13000 && time <= 23000;
    }

    public static String getBrightnessText(Minecraft mc) {
        int brightnessPercent = (int) (mc.level.getMoonBrightness() * 100f);
        TextFormatting color = getBrightnessTextColor(brightnessPercent);
        return String.format("%s%d%%", color, brightnessPercent);
    }


    public static String getTimeLeftText(Minecraft mc) {
        long time = mc.level.getDayTime() % 24000;
        long daySecondsLeft = (24000 - time) / 20 - 50;
        return daySecondsLeft >= 60 ? (daySecondsLeft / 60) + "min " + (daySecondsLeft % 60) + "s": daySecondsLeft + "s";
    }

    public static String getMoonPhaseText(Minecraft mc) {
        int brightnessPercent = (int) (mc.level.getMoonBrightness() * 100f);
        TextFormatting color = getBrightnessTextColor(brightnessPercent);

        String[] moonPhaseKeys = new String[]{
                MoonPhase.MOD_ID + ".moonphase.full",
                MoonPhase.MOD_ID + ".moonphase.waning_gibbous",
                MoonPhase.MOD_ID + ".moonphase.last_quarter",
                MoonPhase.MOD_ID + ".moonphase.waning_crescent",
                MoonPhase.MOD_ID + ".moonphase.new",
                MoonPhase.MOD_ID + ".moonphase.waxing_crescent",
                MoonPhase.MOD_ID + ".moonphase.first_quarter",
                MoonPhase.MOD_ID + ".moonphase.waxing_gibbous"
        };
        int moonPhase = mc.level.getMoonPhase();
        String translationKey = moonPhaseKeys[moonPhase];
        String moonPhaseText = I18n.get(translationKey);

        return color.toString() + moonPhaseText;
    }

    public static int getMaxTextWidth(FontRenderer fontRenderer, String... texts) {
        int maxWidth = 0;
        for (String text : texts) {
            int width = fontRenderer.width(text);
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return maxWidth;
    }

    public static Point getTextPosition(int windowWidth, int windowHeight, int maxTextWidth, int lineHeight) {
        int x = 2;
        int y = 2;
        int y1 = windowHeight - 2 - lineHeight * 3;
        int x1 = windowWidth - 2 - maxTextWidth;
        switch (ConfigHandle.textPosition.get()) {
            case BOTTOM_LEFT:
                y = y1;
                break;
            case TOP_RIGHT:
                x = x1;
                break;
            case BOTTOM_RIGHT:
                x = x1;
                y = y1;
                break;
            case TOP_LEFT:
            default:
                break;
        }
        return new Point(x, y);
    }
}
