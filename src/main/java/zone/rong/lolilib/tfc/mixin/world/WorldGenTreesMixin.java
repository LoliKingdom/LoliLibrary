package zone.rong.lolilib.tfc.mixin.world;

import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.world.classic.worldgen.WorldGenTrees;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import zone.rong.lolilib.tfc.block.BlockStick;

import java.util.Random;

@Mixin(WorldGenTrees.class)
public class WorldGenTreesMixin {

    /**
     * @author Rongmario
     * @reason Remove loose sticks generation
     */
    @Overwrite
    public static void generateLooseSticks(Random rand, int chunkX, int chunkZ, World world, int amount) {
        for (int i = 0; i < amount; ++i) {
            int x = chunkX * 16 + rand.nextInt(16) + 8;
            int z = chunkZ * 16 + rand.nextInt(16) + 8;
            BlockPos pos = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));
            BlockPos downPos = pos.down();
            if (world.isAirBlock(pos)) {
                IBlockState downState = world.getBlockState(downPos);
                if (BlocksTFC.isGround(downState) && downState.isSideSolid(world, downPos, EnumFacing.UP)) {
                    world.setBlockState(pos, BlockStick.INSTANCE.getDefaultState());
                }
            }
        }
    }

}
