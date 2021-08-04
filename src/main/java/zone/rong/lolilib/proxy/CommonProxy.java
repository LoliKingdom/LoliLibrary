package zone.rong.lolilib.proxy;

import com.elseytd.theaurorian.TABiomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zone.rong.lolilib.vanilla.world.WorldGenOverworldStructures;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        GameRegistry.registerWorldGenerator(new WorldGenOverworldStructures(), 0);
        // BlockDataHolder.init();
    }

    public void init(FMLInitializationEvent event) {
        TABiomes.initBiomeManagerAndDictionary();
    }

    public void postInit(FMLPostInitializationEvent event) {
    }

}
