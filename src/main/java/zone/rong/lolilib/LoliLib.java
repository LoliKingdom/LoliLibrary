package zone.rong.lolilib;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zone.rong.lolilib.botania.EntityManaPearl;
import zone.rong.lolilib.botania.RenderManaPearl;
import zone.rong.lolilib.capability.temperature.TemperatureCapability;
import zone.rong.lolilib.twilightforest.BlockTFPortalFrame;
import zone.rong.lolilib.vanilla.world.WorldGenOverworldStructures;

@Mod(modid = LoliLib.MOD_ID, name = LoliLib.NAME, version = "1.0")
@Mod.EventBusSubscriber(modid = LoliLib.MOD_ID)
public class LoliLib {

    public static final String MOD_ID = "lolilib";
    public static final String NAME = "Loli Library";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        TemperatureCapability.initCapability();
        GameRegistry.registerWorldGenerator(new WorldGenOverworldStructures(), 0);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (event.getSide().isClient()) {
            RenderingRegistry.registerEntityRenderingHandler(EntityManaPearl.class, new RenderManaPearl<>());
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(BlockTFPortalFrame.INSTANCE);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(BlockTFPortalFrame.INSTANCE).setRegistryName("tf_portal_frame"));
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        EntityEntry entry = EntityEntryBuilder.create().entity(EntityManaPearl.class).id(new ResourceLocation(MOD_ID, "mana_pearl"), 0).name("manapearl").tracker(64, 4, true).build();
        event.getRegistry().register(entry);
    }

}
