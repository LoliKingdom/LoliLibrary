package zone.rong.lolilib.forestry.mixin;

import forestry.factory.recipes.jei.bottler.BottlerRecipeMaker;
import forestry.factory.recipes.jei.bottler.BottlerRecipeWrapper;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
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
     *
     * TODO: needed? Bottler now disabled as of 20-11-2020
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
                    for (Fluid fluid : FluidRegistry.getBucketFluids()) {
                        FluidStack fluidStack = new FluidStack(fluid, fluidHandler.getTankProperties()[0].getCapacity());
                        fluidHandler.fill(fluidStack, true);
                        drainedStack = fluidHandler.getContainer();
                        recipes.add(new BottlerRecipeWrapper(drainedStack, fluidStack, s, false));
                        recipes.add(new BottlerRecipeWrapper(s, fluidStack, drainedStack, true));
                    }
                });
        return recipes;
    }

}
