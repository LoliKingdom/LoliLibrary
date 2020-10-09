package zone.rong.lolilib.tfc.mixin.world.village;

import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.fluids.FluidsTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import org.spongepowered.asm.mixin.Mixin;
import zone.rong.lolilib.tfc.TFCMain;

import java.util.Random;

@Mixin(StructureVillagePieces.Well.class)
public abstract class WellMixin extends StructureVillagePieces.Village {
    
    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox bb) {
        if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, bb);
            if (this.averageGroundLvl < 0) {
                return true;
            }
            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 3, 0);
        }
        BlockPos pos = TFCMain.CURRENT_VILLAGE_CHUNK.get();
        ChunkDataTFC data = ChunkDataTFC.get(world, pos);
        Rock rock = data.getRock1(pos);
        Tree tree = TFCMain.getTree(data);
        IBlockState iblockstate = TFCMain.getChunkSpecificRock(rock, Blocks.COBBLESTONE);
        IBlockState iblockstate1 = TFCMain.getChunkSpecificWood(tree, Blocks.OAK_FENCE);
        this.fillWithBlocks(world, bb, 1, 0, 1, 4, 12, 4, iblockstate, FluidsTFC.FRESH_WATER.get().getBlock().getDefaultState(), false);
        this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, 12, 2, bb);
        this.setBlockState(world, Blocks.AIR.getDefaultState(), 3, 12, 2, bb);
        this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, 12, 3, bb);
        this.setBlockState(world, Blocks.AIR.getDefaultState(), 3, 12, 3, bb);
        this.setBlockState(world, iblockstate1, 1, 13, 1, bb);
        this.setBlockState(world, iblockstate1, 1, 14, 1, bb);
        this.setBlockState(world, iblockstate1, 4, 13, 1, bb);
        this.setBlockState(world, iblockstate1, 4, 14, 1, bb);
        this.setBlockState(world, iblockstate1, 1, 13, 4, bb);
        this.setBlockState(world, iblockstate1, 1, 14, 4, bb);
        this.setBlockState(world, iblockstate1, 4, 13, 4, bb);
        this.setBlockState(world, iblockstate1, 4, 14, 4, bb);
        this.fillWithBlocks(world, bb, 1, 15, 1, 4, 15, 4, iblockstate, iblockstate, false);
        for (int i = 0; i <= 5; ++i) {
            for (int j = 0; j <= 5; ++j) {
                if (j == 0 || j == 5 || i == 0 || i == 5) {
                    this.setBlockState(world, iblockstate, j, 11, i, bb);
                    this.clearCurrentPositionBlocksUpwards(world, j, 12, i, bb);
                }
            }
        }
        return true;
    }
    
}
