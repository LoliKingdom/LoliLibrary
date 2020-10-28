package zone.rong.lolilib;

import crafttweaker.CraftTweakerAPI;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;
import zone.rong.lolilib.botania.EntityManaPearl;
import zone.rong.lolilib.botania.RenderManaPearl;
import zone.rong.lolilib.capability.world.BlockDataHolder;
import zone.rong.lolilib.tfc.block.BlockCustomFirePit;
import zone.rong.lolilib.twilightforest.BlockTFPortalFrame;
import zone.rong.lolilib.vanilla.world.WorldGenOverworldStructures;

@Mod(modid = LoliLib.MOD_ID, name = LoliLib.NAME, version = "1.0", dependencies = "before:tfc")
@Mod.EventBusSubscriber(modid = LoliLib.MOD_ID)
public class LoliLib {

    public static final String MOD_ID = "lolilib";
    public static final String NAME = "Loli Library";

    public static Logger LOGGER;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
        GameRegistry.registerWorldGenerator(new WorldGenOverworldStructures(), 0);
        BlockDataHolder.init();
        if (event.getSide().isClient()) {
            OBJLoader.INSTANCE.addDomain(MOD_ID);
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (event.getSide().isClient()) {
            RenderingRegistry.registerEntityRenderingHandler(EntityManaPearl.class, new RenderManaPearl<>());
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        CraftTweakerAPI.ENABLE_SEARCH_TREE_RECALCULATION = false;
    }

    @Mod.EventHandler
    public void start(FMLLoadCompleteEvent event) {
        // Tests are done here
        // Arrays.stream(BlockFirePit.FirePitAttachment.values()).forEach(System.out::println);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(BlockTFPortalFrame.INSTANCE);
        BlockCustomFirePit.INSTANCE.register(MOD_ID);
        event.getRegistry().register(BlockCustomFirePit.INSTANCE);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(BlockTFPortalFrame.INSTANCE).setRegistryName(MOD_ID, "tf_portal_frame").setUnlocalizedName("tf_portal_frame"));
        event.getRegistry().register(new ItemBlock(BlockCustomFirePit.INSTANCE).setRegistryName(MOD_ID, "fire_pit").setUnlocalizedName("fire_pit"));
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        EntityEntry entry = EntityEntryBuilder.create().entity(EntityManaPearl.class).id(new ResourceLocation(MOD_ID, "mana_pearl"), 0).name("manapearl").tracker(64, 4, true).build();
        event.getRegistry().register(entry);
    }

}
