package zone.rong.lolilib.vanilla;

import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.spongepowered.asm.mixin.Mixins;
import zone.rong.lolilib.LoliModule;
import zone.rong.lolilib.vanilla.world.WorldGenOverworldStructures;

public class VanillaModule extends LoliModule {

    @Override
    public void onCoreModInit(Side side) {
        Mixins.addConfiguration("mixins.lolilib.vanilla.json");
    }

    @Override
    public void onFMLPostInit(FMLPostInitializationEvent event, Side side) {
        GameRegistry.registerWorldGenerator(new WorldGenOverworldStructures(), 0);
    }

}
