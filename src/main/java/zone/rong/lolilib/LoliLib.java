package zone.rong.lolilib;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import zone.rong.lolilib.proxy.CommonProxy;

import java.util.ArrayList;
import java.util.List;

@Mod(modid = LoliLib.MOD_ID, name = LoliLib.NAME, version = "1.0", dependencies = "after:*")
@Mod.EventBusSubscriber
public final class LoliLib {

    public static final String MOD_ID = "lolilib";
    public static final String NAME = "Loli Library";

    @SidedProxy(modId = MOD_ID, clientSide = "zone.rong.lolilib.proxy.ClientProxy", serverSide = "zone.rong.lolilib.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        proxy.construct(event);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void start(FMLLoadCompleteEvent event) {
        proxy.start(event);
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
    }

}
