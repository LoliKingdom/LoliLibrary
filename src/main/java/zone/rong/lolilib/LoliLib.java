package zone.rong.lolilib;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import zone.rong.lolilib.botania.EntityManaPearl;
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

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        EntityEntry entry = EntityEntryBuilder.create().entity(EntityManaPearl.class).id(new ResourceLocation(MOD_ID, "mana_pearl"), 0).name("manapearl").tracker(64, 4, true).build();
        event.getRegistry().register(entry);
    }

}
