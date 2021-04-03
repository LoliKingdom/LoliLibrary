package zone.rong.lolilib.pyrotech.mixin.block;

import com.codetaylor.mc.pyrotech.modules.storage.block.BlockBagSimple;
import com.codetaylor.mc.pyrotech.modules.storage.block.spi.BlockBagBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockBagSimple.class)
public abstract class BlockBagSimpleMixin extends BlockBagBase {

    @Override
    public boolean isItemValidForInsertion(ItemStack itemStack) {
        for (int id : OreDictionary.getOreIDs(itemStack)) {
            if (OreDictionary.getOreName(id).equals("rock")) {
                return true;
            }
        }
        return false;
    }

}
