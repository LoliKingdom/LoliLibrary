package zone.rong.lolilib.pyrotech.mixin.block;

import com.codetaylor.mc.pyrotech.modules.storage.block.BlockBagDurable;
import com.codetaylor.mc.pyrotech.modules.storage.block.spi.BlockBagBase;
import net.dries007.tfc.objects.items.metal.ItemSmallOre;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockBagDurable.class)
public abstract class BlockBagDurableMixin extends BlockBagBase {

    @Override
    public boolean isItemValidForInsertion(ItemStack itemStack) {
        return itemStack.getItem() instanceof ItemSmallOre || OreDictionaryHelper.doesStackMatchOre(itemStack, "rock") || OreDictionaryHelper.doesStackMatchOre(itemStack, "cobblestone");
    }

}
