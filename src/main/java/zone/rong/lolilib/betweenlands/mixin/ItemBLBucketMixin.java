package zone.rong.lolilib.betweenlands.mixin;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import thebetweenlands.common.item.tools.ItemBLBucket;

@Mixin(ItemBLBucket.class)
public abstract class ItemBLBucketMixin extends Item {

    @Shadow @Final private ItemStack emptySyrmorite;

    @Shadow @Final private ItemStack emptyWeedwood;

    /**
     * @author Rongmario
     * @reason No.
     */
    @Overwrite
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (this.isInCreativeTab(tab)) {
            subItems.add(this.emptySyrmorite.copy());
            subItems.add(this.emptyWeedwood.copy());
        }
    }

}
