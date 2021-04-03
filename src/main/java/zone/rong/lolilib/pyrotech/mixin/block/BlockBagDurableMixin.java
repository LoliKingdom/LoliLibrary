package zone.rong.lolilib.pyrotech.mixin.block;

import com.codetaylor.mc.pyrotech.modules.storage.block.BlockBagDurable;
import com.codetaylor.mc.pyrotech.modules.storage.block.spi.BlockBagBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockBagDurable.class)
public abstract class BlockBagDurableMixin extends BlockBagBase {

    @Override
    public boolean isItemValidForInsertion(ItemStack itemStack) {
        for (int id : OreDictionary.getOreIDs(itemStack)) {
            String name = OreDictionary.getOreName(id);
            if (name.equals("rock") || name.startsWith("oreSmall") || name.equals("cobblestone")) {
                return true;
            }
        }
        return false;
    }

}
