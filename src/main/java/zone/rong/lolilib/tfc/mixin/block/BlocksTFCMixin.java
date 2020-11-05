package zone.rong.lolilib.tfc.mixin.block;

import net.dries007.tfc.objects.blocks.BlockPlacedItemFlat;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.blocks.metal.BlockIngotPile;
import net.dries007.tfc.objects.te.TEIngotPile;
import net.dries007.tfc.objects.te.TEPlacedItemFlat;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.registries.IForgeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlocksTFC.class)
public class BlocksTFCMixin {

    @Inject(method = "register(Lnet/minecraftforge/registries/IForgeRegistry;Ljava/lang/String;Lnet/minecraft/block/Block;)Lnet/minecraft/block/Block;", at = @At("HEAD"), cancellable = true, remap = false)
    private static void removeBlockRegistrations(IForgeRegistry<Block> r, String name, Block block, CallbackInfoReturnable<Block> cir) {
        if (block instanceof BlockPlacedItemFlat || block instanceof BlockIngotPile) {
            cir.setReturnValue(Blocks.AIR);
        }
    }

    @Inject(method = "register(Ljava/lang/Class;Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true, remap = false)
    private static void removeTileRegistrations(Class<TileEntity> te, String name, CallbackInfo ci) {
        if (te.equals(TEPlacedItemFlat.class) || te.equals(TEIngotPile.class)) {
            ci.cancel();
        }
    }

}
