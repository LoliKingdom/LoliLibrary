package zone.rong.lolilib.metallurgy.mixin;

import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nonnull;

@Mixin(MetallurgyTabs.class)
public abstract class MetallurgyTabsMixin extends CreativeTabs {

    protected MetallurgyTabsMixin(String label) {
        super(label);
    }

    /**
     * @author Rongmario
     * @reason No.
     */
    @Overwrite
    public void displayAllRelevantItems(@Nonnull NonNullList<ItemStack> stacks) {
        super.displayAllRelevantItems(stacks);
    }

}
