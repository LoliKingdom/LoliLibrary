package zone.rong.lolilib.vanilla.mixin.searchtree;

import net.minecraft.client.gui.recipebook.GuiRecipeBook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(GuiRecipeBook.class)
public class GuiRecipeBookMixin {

    /**
     * @author Rongmario
     * @reason Always report false
     */
    @Overwrite
    public boolean isVisible() {
        return false;
    }

}
