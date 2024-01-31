package fun.faulkner.moon_phase;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigHandle {

    public enum TextPosition {
        TOP_LEFT,
        BOTTOM_LEFT,
        TOP_RIGHT,
        BOTTOM_RIGHT
    }

    public static ForgeConfigSpec.EnumValue<TextPosition> textPosition;
    public static ForgeConfigSpec.BooleanValue showMoonPhaseOverBrightness;
    public static ForgeConfigSpec.BooleanValue showTimeLeft;
    public static final ForgeConfigSpec COMMON_CONFIG;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        textPosition = COMMON_BUILDER
                .comment("Position of the moon phase text on the screen")
                .defineEnum("textPosition", TextPosition.TOP_LEFT);

        showMoonPhaseOverBrightness = COMMON_BUILDER
                .comment("Choose what to display: moon phase text or brightness percentage")
                .define("showMoonPhaseOverBrightness", true);

        showTimeLeft = COMMON_BUILDER
                .comment("Show the time left until a new day")
                .define("showTimeLeft", false);

        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
