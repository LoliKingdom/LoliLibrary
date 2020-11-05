package zone.rong.lolilib.appeng.mixin;

import appeng.core.AEConfig;
import appeng.core.AppEng;
import appeng.core.features.AEFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AppEng.class)
public class AppEngMixin {

    @Redirect(method = "preInit", at = @At(value = "INVOKE", target = "Lappeng/core/AEConfig;isFeatureEnabled(Lappeng/core/features/AEFeature;)Z", remap = false), remap = false)
    private boolean neverInitializeCreativeTab(AEConfig aeConfig, AEFeature f) {
        return false;
    }

}
