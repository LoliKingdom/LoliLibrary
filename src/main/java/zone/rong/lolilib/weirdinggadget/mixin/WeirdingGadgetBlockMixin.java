package zone.rong.lolilib.weirdinggadget.mixin;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.common.util.FakePlayer;

import com.github.atomicblom.weirdinggadget.block.WeirdingGadgetBlock;
import com.github.atomicblom.weirdinggadget.block.tileentity.WeirdingGadgetTileEntity;
import net.dries007.tfc.util.Helpers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(WeirdingGadgetBlock.class)
public abstract class WeirdingGadgetBlockMixin {

    @Shadow private static void activateChunkLoader(World worldIn, BlockPos pos, EntityPlayer placer) { }

    /**
     * @author Rongmario
     */
    @Overwrite
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        } else {
            if (player instanceof FakePlayer) {
                return false;
            }
            WeirdingGadgetTileEntity tileEntity = Helpers.getTE(world, pos, WeirdingGadgetTileEntity.class);
            if (tileEntity == null || !tileEntity.isExpired() && tileEntity.hasTicket(player)) {
                return false;
            } else {
                activateChunkLoader(world, pos, player);
                return true;
            }
        }
    }

}
