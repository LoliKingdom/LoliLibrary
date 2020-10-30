package zone.rong.lolilib.vanilla.mixin.searchtree;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.stats.RecipeBook;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nullable;

@Mixin(RecipeBook.class)
public class RecipeBookMixin {

    /**
     * @author Rongmario
     * @reason All recipes are able to be crafted
     */
    @Overwrite
    public boolean isUnlocked(@Nullable IRecipe recipe) {
        return true;
    }

    /**
     * @author Rongmario
     * @reason no new recipes
     */
    @Overwrite
    @SideOnly(Side.CLIENT)
    public boolean isNew(IRecipe recipe) {
        return false;
    }

}
