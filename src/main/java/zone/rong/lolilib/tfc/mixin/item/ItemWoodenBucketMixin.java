package zone.rong.lolilib.tfc.mixin.item;

import net.dries007.tfc.objects.items.wood.ItemWoodenBucket;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ItemWoodenBucket.class)
public abstract class ItemWoodenBucketMixin extends Item {

    /**
     * @author Rongmario
     * @reason No.
     */
    @Overwrite
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            items.add(new ItemStack(this));
        }
    }

}
