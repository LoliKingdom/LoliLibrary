package zone.rong.lolilib.tfc.mixin.item;

import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.objects.items.rock.ItemRock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import zone.rong.lolilib.pyrotech.PyrotechMain;

@Mixin(ItemRock.class)
public abstract class ItemRockMixin extends Item {

    @Shadow @Final private Rock rock;

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote && player.isSneaking()) {
            BlockPos placePos;
            if (world.isSideSolid(pos, facing) && (world.isAirBlock(placePos = pos.up()) || world.getBlockState(placePos).getBlock().isReplaceable(world, placePos))) {
                world.setBlockState(placePos, PyrotechMain.getPTRock(this.rock).getDefaultState(), 3);
                world.playSound(null, placePos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 0.5F, 0.5F);
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.PASS;
    }

}
