package zone.rong.lolilib.vanilla.mixin;

import net.minecraft.block.Block;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.RegistryBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import zone.rong.lolilib.util.EnhancedBlockCallbacks;

@Mixin(GameData.class)
public class GameDataMixin {

    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/registries/RegistryBuilder;addCallback(Ljava/lang/Object;)Lnet/minecraftforge/registries/RegistryBuilder;", ordinal = 0, remap = false), remap = false)
    private static RegistryBuilder<Block> onAddingBlockCallback(RegistryBuilder<Block> registryBuilder, Object inst) {
        return registryBuilder.addCallback(EnhancedBlockCallbacks.INSTANCE);
    }

}
