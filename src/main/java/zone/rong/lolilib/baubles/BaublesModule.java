package zone.rong.lolilib.baubles;

import net.minecraftforge.fml.relauncher.Side;
import org.spongepowered.asm.mixin.Mixins;
import zone.rong.lolilib.LoliModule;

public class BaublesModule extends LoliModule {

    @Override
    public void onMixinBooterInit(Side side) {
        Mixins.addConfiguration("mixins.lolilib.baubles.json");
    }
}
