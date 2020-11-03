package zone.rong.lolilib.forge.mixin;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.UniversalBucket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mixin(UniversalBucket.class)
public class UniversalBucketMixin {

    /**
     * @author Rongmario
     * @reason Stop buckets registering as tab items
     */
    @Overwrite(remap = false)
    public void getSubItems(@Nullable CreativeTabs tab, @Nonnull NonNullList<ItemStack> subItems) {}

}
