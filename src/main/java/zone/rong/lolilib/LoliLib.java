package zone.rong.lolilib;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zone.rong.lolilib.capability.temperature.TemperatureCapability;

@Mod(modid = LoliLib.MOD_ID, name = LoliLib.NAME, version = "1.0")
@Mod.EventBusSubscriber
public class LoliLib {

    public static final String MOD_ID = "lolilib";
    public static final String NAME = "Loli Library";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        TemperatureCapability.initCapability();
    }

}
