package zone.rong.lolilib.rustic.mixin;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(targets = "rustic.core.Rustic$2")
public class Rustic_FarmingTabMixin {

    /**
     * @author Rongmario
     * @reason No.
     */
    @Overwrite
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems(NonNullList<ItemStack> stacks) {
        for (Item item : Item.REGISTRY) {
            item.getSubItems((CreativeTabs) (Object) this, stacks);
        }
    }

}
