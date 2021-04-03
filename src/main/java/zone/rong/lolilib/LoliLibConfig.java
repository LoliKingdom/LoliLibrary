package zone.rong.lolilib;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zone.rong.lolilib.util.LazyInt;

@Mod.EventBusSubscriber(modid = LoliLib.MOD_ID)
public class LoliLibConfig {

    public static final LazyInt backgroundTooltipColour = new LazyInt(() -> ((int) ((long) Long.decode(Aesthetics.backgroundTooltipColour))), 0xD4000000);
    public static final LazyInt startTooltipColour = new LazyInt(() -> ((int) ((long) Long.decode(Aesthetics.startTooltipColour))), 0xD4333399);
    public static final LazyInt endTooltipColour = new LazyInt(() -> ((int) ((long) Long.decode(Aesthetics.endTooltipColour))), 0xD4FF00CC);

    @SubscribeEvent
    public static void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(LoliLib.MOD_ID)) {
            ConfigManager.sync(LoliLib.MOD_ID, Config.Type.INSTANCE);
            backgroundTooltipColour.invalidate();
            startTooltipColour.invalidate();
            endTooltipColour.invalidate();
        }
    }

    @Config(modid = LoliLib.MOD_ID, category = "aesthetics", name = "lolilib-aesthetics")
    public static class Aesthetics {

        @Config.Comment("Tooltip Background Colour in ARGB format")
        public static String backgroundTooltipColour = "0xD4000000";

        @Config.Comment("Start of the Tooltip's Colour in ARGB format")
        public static String startTooltipColour = "0xD4333399";

        @Config.Comment("End of the Tooltip's Colour in ARGB format")
        public static String endTooltipColour = "0xD4FF00CC";

    }
}
