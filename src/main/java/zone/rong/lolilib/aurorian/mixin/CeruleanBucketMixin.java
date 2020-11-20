package zone.rong.lolilib.aurorian.mixin;

import com.elseytd.theaurorian.Items.TAItem_Tool_Cerulean_Bucket;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TAItem_Tool_Cerulean_Bucket.class)
public abstract class CeruleanBucketMixin extends Item {

    @Shadow(remap = false) @Final private ItemStack empty;

    /**
     * @author Rongmario
     * @reason No.
     */
    @Overwrite
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (this.isInCreativeTab(tab)) {
            subItems.add(this.empty);
        }
    }

}
