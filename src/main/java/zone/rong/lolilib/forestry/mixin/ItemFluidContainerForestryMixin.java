package zone.rong.lolilib.forestry.mixin;

import forestry.core.items.ItemFluidContainerForestry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ItemFluidContainerForestry.class)
public abstract class ItemFluidContainerForestryMixin extends Item {

    /**
     * @author Rongmario
     * @reason No.
     */
    @Overwrite
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (this.isInCreativeTab(tab)) {
            subItems.add(new ItemStack(this));
        }
    }

}
