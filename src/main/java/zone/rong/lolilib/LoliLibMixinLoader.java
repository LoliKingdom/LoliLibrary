package zone.rong.lolilib;

import org.spongepowered.asm.mixin.Mixins;
import zone.rong.mixinbooter.MixinLoader;

@MixinLoader
public class LoliLibMixinLoader {

    {
        Mixins.addConfiguration("mixins.lolilib.appeng.json");
        Mixins.addConfiguration("mixins.lolilib.asian.json");
        Mixins.addConfiguration("mixins.lolilib.astralsorcery.json");
        Mixins.addConfiguration("mixins.lolilib.aurorian.json");
        Mixins.addConfiguration("mixins.lolilib.baubles.json");
        Mixins.addConfiguration("mixins.lolilib.betweenlands.json");
        Mixins.addConfiguration("mixins.lolilib.forestry.json");
        Mixins.addConfiguration("mixins.lolilib.gog.json");
        Mixins.addConfiguration("mixins.lolilib.ic2.json");
        Mixins.addConfiguration("mixins.lolilib.metallurgy.json");
        Mixins.addConfiguration("mixins.lolilib.pyrotech.json");
        Mixins.addConfiguration("mixins.lolilib.rustic.json");
        Mixins.addConfiguration("mixins.lolilib.tfc.json");
        Mixins.addConfiguration("mixins.lolilib.thaumcraft.json");
        Mixins.addConfiguration("mixins.lolilib.warpdrive.json");
    }
    
}
