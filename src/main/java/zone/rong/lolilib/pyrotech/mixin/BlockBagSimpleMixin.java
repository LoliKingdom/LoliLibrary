package zone.rong.lolilib.pyrotech.mixin;

import com.codetaylor.mc.pyrotech.modules.storage.block.BlockBagSimple;
import com.codetaylor.mc.pyrotech.modules.storage.block.spi.BlockBagBase;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockBagSimple.class)
public abstract class BlockBagSimpleMixin extends BlockBagBase {

    @Override
    public boolean isItemValidForInsertion(ItemStack itemStack) {
        return OreDictionaryHelper.doesStackMatchOre(itemStack, "rock");
    }

}
