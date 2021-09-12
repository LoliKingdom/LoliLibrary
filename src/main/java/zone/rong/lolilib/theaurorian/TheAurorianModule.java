package zone.rong.lolilib.theaurorian;

import net.minecraftforge.fml.relauncher.Side;
import org.spongepowered.asm.mixin.Mixins;
import zone.rong.lolilib.LoliModule;

public class TheAurorianModule extends LoliModule {

    @Override
    public void onMixinBooterInit(Side side) {
        Mixins.addConfiguration("mixins.lolilib.theaurorian.json");
    }

}
