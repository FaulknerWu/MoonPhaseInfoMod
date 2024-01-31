package fun.faulkner.moon_phase;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(MoonPhase.MOD_ID)
public class MoonPhase {

    public static final String MOD_ID = "moon_phase";

    public MoonPhase() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandle.COMMON_CONFIG);
    }

}
