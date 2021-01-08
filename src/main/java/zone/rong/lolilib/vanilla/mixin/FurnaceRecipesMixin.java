package zone.rong.lolilib.vanilla.mixin;

import net.minecraft.item.crafting.FurnaceRecipes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import zone.rong.lolilib.vanilla.BetterFurnaceRecipes;

@Mixin(FurnaceRecipes.class)
public class FurnaceRecipesMixin {

    /**
     * @author Rongmario
     * @reason Return a more efficient instance of FurnaceRecipes
     */
    @Overwrite
    public static FurnaceRecipes instance() {
        return BetterFurnaceRecipes.INSTANCE;
    }

}
