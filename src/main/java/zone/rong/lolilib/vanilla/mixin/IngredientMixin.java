package zone.rong.lolilib.vanilla.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zone.rong.lolilib.LoliLib;

@Mixin(Ingredient.class)
public class IngredientMixin {

    @Inject(method = "<init>([Lnet/minecraft/item/ItemStack;)V", at = @At("RETURN"))
    private void inject(ItemStack[] p_i47503_1_, CallbackInfo ci) {
        LoliLib.ingredientClasses.add(this.getClass());
    }

}
