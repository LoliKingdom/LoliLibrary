package zone.rong.lolilib.tfc.mixin.block;

import net.dries007.tfc.objects.blocks.stone.BlockOreTFC;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BlockOreTFC.class)
public class BlockOreTFCMixin {

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 0))
    private int increaseHarvestLevel(int original) {
        return 1;
    }

}
