package zone.rong.lolilib;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.relauncher.Side;
import org.spongepowered.asm.mixin.Mixins;
import zone.rong.mixinbooter.MixinLoader;

import java.util.Map;

@MixinLoader
public class LoliLibMixinLoader {

    {
        boolean isClient = ((Map) Launch.blackboard.get("launchArgs")).containsKey("--assetIndex");
        LoliModule.INSTANCES.forEach(m -> m.onMixinBooterInit(isClient ? Side.CLIENT : Side.SERVER));
        Mixins.addConfiguration("mixins.lolilib.appeng.json");
        Mixins.addConfiguration("mixins.lolilib.asian.json");
        Mixins.addConfiguration("mixins.lolilib.astralsorcery.json");
        Mixins.addConfiguration("mixins.lolilib.baubles.json");
        Mixins.addConfiguration("mixins.lolilib.betweenlands.json");
        Mixins.addConfiguration("mixins.lolilib.enderio.json");
        Mixins.addConfiguration("mixins.lolilib.forestry.json");
        Mixins.addConfiguration("mixins.lolilib.gog.json");
        // Mixins.addConfiguration("mixins.lolilib.metallurgy.json");
        Mixins.addConfiguration("mixins.lolilib.pyrotech.json");
        Mixins.addConfiguration("mixins.lolilib.rustic.json");
        Mixins.addConfiguration("mixins.lolilib.thaumcraft.json");
        Mixins.addConfiguration("mixins.lolilib.warpdrive.json");
    }
    
}
