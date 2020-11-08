package zone.rong.lolilib.rustic.mixin;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("all")
@Mixin(targets = "rustic.core.Rustic$2")
public abstract class Rustic_FarmingTabMixin extends CreativeTabs {

    protected Rustic_FarmingTabMixin(String label) {
        super(label);
    }

    /**
     * @author Rongmario
     * @reason No.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems(NonNullList<ItemStack> stacks) {
        for (Item item : Item.REGISTRY) {
            item.getSubItems((CreativeTabs) (Object) this, stacks);
        }
    }

}
