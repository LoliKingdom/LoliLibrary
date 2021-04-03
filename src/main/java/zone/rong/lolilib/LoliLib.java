package zone.rong.lolilib;

import crafttweaker.mc1120.CraftTweaker;
import it.unimi.dsi.fastutil.bytes.ByteOpenHashSet;
import it.unimi.dsi.fastutil.bytes.ByteSet;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zone.rong.lolilib.botania.EntityManaPearl;
import zone.rong.lolilib.botania.RenderManaPearl;
import zone.rong.lolilib.capability.world.BlockDataHolder;
import zone.rong.lolilib.twilightforest.BlockTFPortalFrame;
import zone.rong.lolilib.vanilla.world.WorldGenOverworldStructures;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mod(modid = LoliLib.MOD_ID, name = LoliLib.NAME, version = "1.0", dependencies = "after:*")
@Mod.EventBusSubscriber
public final class LoliLib {

    public static final Set<Class<?>> classesThatCallBakedQuad = new ObjectOpenHashSet<>();
    public static final ByteSet colorTintsThatBakedQuadsUses = new ByteOpenHashSet();
    public static final IntSet uniqueVertexFormatsBakedQuadsHas = new IntOpenHashSet();

    public static final String MOD_ID = "lolilib";
    public static final String NAME = "Loli Library";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
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
    public void postInit(FMLPostInitializationEvent event) throws NoSuchFieldException, IllegalAccessException {
        Field alreadyChangedThePlayer = CraftTweaker.class.getDeclaredField("alreadyChangedThePlayer");
        alreadyChangedThePlayer.setAccessible(true);
        alreadyChangedThePlayer.setBoolean(null, true); // Changing this allows us to bypass RecipeBook build AND search tree recalculation
        // CraftTweakerAPI.ENABLE_SEARCH_TREE_RECALCULATION = false;
    }

    @Mod.EventHandler
    public void start(FMLLoadCompleteEvent event) {
        List<IGrowable> growables = new ArrayList<>();
        List<IPlantable> plantables = new ArrayList<>();
        ForgeRegistries.BLOCKS.getValuesCollection().forEach(b -> {
            if (b.getRegistryName().getResourceDomain().equals("tfc")) {
                return; // No need to log TFC blocks.
            }
            if (b instanceof IGrowable) {
                growables.add((IGrowable) b);
            }
            if (b instanceof IPlantable) {
                plantables.add((IPlantable) b);
            }
        });
        LoliLogger.INSTANCE.info("Growables:");
        growables.forEach(g -> {
            Block block = (Block) g;
            LoliLogger.INSTANCE.info("{} - {}", block.getRegistryName().getResourceDomain(), block.getRegistryName().getResourcePath());
            block.getBlockState().getProperties().forEach(p ->  LoliLogger.INSTANCE.info("Property: {} | Allowed Values: {}", p.getName(), (Object) p.getAllowedValues().toArray()));
        });
        LoliLogger.INSTANCE.info("Plantables:");
        plantables.forEach(g -> {
            Block block = (Block) g;
            LoliLogger.INSTANCE.info("{} - {}", block.getRegistryName().getResourceDomain(), block.getRegistryName().getResourcePath());
            block.getBlockState().getProperties().forEach(p ->  LoliLogger.INSTANCE.info("Property: {} | Allowed Values: {}", p.getName(), (Object) p.getAllowedValues().toArray()));
        });
        LoliLogger.INSTANCE.info("Plantables:");
        for (Class<?> clazz : classesThatCallBakedQuad) {
            LoliLogger.INSTANCE.info("This class: {} called new BakedQuad()", clazz);
        }
        LoliLogger.INSTANCE.info("Tints that BakedQuads use: {}", colorTintsThatBakedQuadsUses.toByteArray());
        LoliLogger.INSTANCE.info("Amount of unique VertexFormats: {}", uniqueVertexFormatsBakedQuadsHas.size());
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(BlockTFPortalFrame.INSTANCE);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(BlockTFPortalFrame.INSTANCE).setRegistryName(MOD_ID, "tf_portal_frame").setUnlocalizedName("tf_portal_frame"));
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        EntityEntry entry = EntityEntryBuilder.create().entity(EntityManaPearl.class).id(new ResourceLocation(MOD_ID, "mana_pearl"), 0).name("manapearl").tracker(64, 4, true).build();
        event.getRegistry().register(entry);
    }

}
