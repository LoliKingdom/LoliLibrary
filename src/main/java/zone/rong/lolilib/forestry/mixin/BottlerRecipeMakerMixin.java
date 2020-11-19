package zone.rong.lolilib.forestry.mixin;

import forestry.factory.recipes.jei.bottler.BottlerRecipeMaker;
import forestry.factory.recipes.jei.bottler.BottlerRecipeWrapper;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;

@Mixin(BottlerRecipeMaker.class)
public class BottlerRecipeMakerMixin {

    /**
     * @author Rongmario
     * @reason Eliminate load time by at least half a minute.
     */
    @Overwrite(remap = false)
    public static List<BottlerRecipeWrapper> getBottlerRecipes(IIngredientRegistry ingredientRegistry) {
        final List<BottlerRecipeWrapper> recipes = new ObjectArrayList<>();
        ingredientRegistry.getAllIngredients(VanillaTypes.ITEM)
                .stream()
                .filter(s -> s.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null))
                .forEach(s -> {
                    ItemStack drainedStack = s.copy();
                    IFluidHandlerItem fluidHandler = FluidUtil.getFluidHandler(drainedStack);
                    FluidStack drain = fluidHandler.drain(16000, true);
                    drainedStack = fluidHandler.getContainer();
                    if (drain != null && drain.amount > 0) {
                        // recipes.add(new BottlerRecipeWrapper(s, FluidStack))
                    } else {

                    }
                });
        return recipes;
    }

}
