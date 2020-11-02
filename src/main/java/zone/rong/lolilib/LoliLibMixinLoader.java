package zone.rong.lolilib;

import org.spongepowered.asm.mixin.Mixins;
import zone.rong.mixinbooter.MixinLoader;

@MixinLoader
public class LoliLibMixinLoader {

    {
        Mixins.addConfiguration("mixins.lolilib.asian.json");
        Mixins.addConfiguration("mixins.lolilib.astralsorcery.json");
        Mixins.addConfiguration("mixins.lolilib.baubles.json");
        Mixins.addConfiguration("mixins.lolilib.gog.json");
        Mixins.addConfiguration("mixins.lolilib.ic2.json");
        Mixins.addConfiguration("mixins.lolilib.pyrotech.json");
        Mixins.addConfiguration("mixins.lolilib.tfc.json");
        Mixins.addConfiguration("mixins.lolilib.thaumcraft.json");
    }
    
}
