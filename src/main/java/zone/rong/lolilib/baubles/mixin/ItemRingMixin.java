package zone.rong.lolilib.baubles.mixin;

import baubles.common.items.ItemRing;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemRing.class)
public class ItemRingMixin {

    @Redirect(method = "registerItems", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/registries/IForgeRegistry;register(Lnet/minecraftforge/registries/IForgeRegistryEntry;)V", remap = false), remap = false)
    private static <V extends IForgeRegistryEntry> void registerItems(IForgeRegistry registry, V value) {
        registry.register((new ItemRing()).setUnlocalizedName("Ring").setRegistryName("baubles", "ring"));
    }

}
