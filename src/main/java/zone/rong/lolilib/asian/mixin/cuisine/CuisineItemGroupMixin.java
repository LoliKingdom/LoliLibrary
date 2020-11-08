package zone.rong.lolilib.asian.mixin.cuisine;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import snownee.cuisine.CuisineItemGroup;

@Mixin(CuisineItemGroup.class)
public abstract class CuisineItemGroupMixin extends CreativeTabs {

    protected CuisineItemGroupMixin(String label) {
        super(label);
    }

    /**
     * @author Rongmario
     * @reason No.
     */
    @Overwrite
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems(NonNullList<ItemStack> list) {
        super.displayAllRelevantItems(list);
    }

}
