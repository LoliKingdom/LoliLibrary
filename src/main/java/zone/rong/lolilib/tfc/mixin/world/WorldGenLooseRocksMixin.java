package zone.rong.lolilib.tfc.mixin.world;

import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.te.TEPlacedItemFlat;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.world.classic.worldgen.WorldGenLooseRocks;
import net.dries007.tfc.world.classic.worldgen.vein.Vein;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import zone.rong.lolilib.pyrotech.PyrotechMain;

import javax.annotation.Nullable;

@Mixin(WorldGenLooseRocks.class)
public class WorldGenLooseRocksMixin {

    /**
     * @author Rongmario
     * @reason Generate our own rock (blocks) instead. Note: Config options are ignored. TODO: use GT rocks
     */
    @Overwrite(remap = false)
    private void generateRock(World world, BlockPos pos, @Nullable Vein vein, Rock rock) {
        if (world.isAirBlock(pos)) {
            BlockPos downPos = pos.down();
            IBlockState downState = world.getBlockState(downPos);
            if (BlocksTFC.isSoil(downState) && downState.isSideSolid(world, downPos, EnumFacing.UP)) {
                if (vein != null && vein.getType() != null) {
                    world.setBlockState(pos, BlocksTFC.PLACED_ITEM_FLAT.getDefaultState(), 2);
                    TEPlacedItemFlat tile = Helpers.getTE(world, pos, TEPlacedItemFlat.class);
                    tile.setStack(vein.getType().getLooseRockItem());
                } else {
                    world.setBlockState(pos, PyrotechMain.getPTRock(rock).getDefaultState(), 2);
                }
            }
        }
    }

}
