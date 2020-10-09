package zone.rong.lolilib.tfc.mixin;

import net.darkhax.gamestages.GameStageHelper;
import net.dries007.tfc.api.capability.food.FoodStatsTFC;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import zone.rong.lolilib.tfc.TFCMain;

@Mixin(FoodStatsTFC.class)
public class FoodStatsTFCMixin {

    @Shadow(remap = false) @Final private EntityPlayer sourcePlayer;
    @Unique private boolean drankWater = false;

    @Inject(method = "attemptDrink", at = @At(value = "INVOKE", target = "Lnet/dries007/tfc/api/capability/food/FoodStatsTFC;resetCooldown()V", remap = false), remap = false)
    private void incrementDrinkStat(float value, boolean simulate, CallbackInfoReturnable<Boolean> cir) {
        if (!drankWater) {
            GameStageHelper.addStage(this.sourcePlayer, TFCMain.STAGE_DRANK_WATER);
            drankWater = true;
        }
    }

}
