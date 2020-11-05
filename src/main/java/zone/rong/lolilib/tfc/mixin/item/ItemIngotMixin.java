package zone.rong.lolilib.tfc.mixin.item;

import net.dries007.tfc.objects.items.metal.ItemIngot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nonnull;

@Mixin(ItemIngot.class)
public class ItemIngotMixin {

    /**
     * @author Rongmario
     * @reason Stop placing of ingots from taking place
     */
    @Nonnull
    @Overwrite
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return EnumActionResult.PASS;
    }

}
