package zone.rong.lolilib.appeng.mixin;

import appeng.items.parts.ItemFacade;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ItemFacade.class)
public class ItemFacadeMixin {

    /**
     * @author Rongmario
     * @reason No.
     */
    @Overwrite(remap = false)
    protected void getCheckedSubItems(CreativeTabs creativeTab, NonNullList<ItemStack> itemStacks) { }

    /**
     * @author Rongmario
     * @reason No.
     */
    @Overwrite(remap = false)
    public ItemStack getCreativeTabIcon() {
        return ItemStack.EMPTY;
    }

}
