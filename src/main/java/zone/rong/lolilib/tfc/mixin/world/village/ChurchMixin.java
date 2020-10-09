package zone.rong.lolilib.tfc.mixin.world.village;

import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import org.spongepowered.asm.mixin.Mixin;
import zone.rong.lolilib.tfc.TFCMain;

import java.util.Random;

@Mixin(StructureVillagePieces.Church.class)
public abstract class ChurchMixin extends StructureVillagePieces.Village {
    
    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox bb) {
        if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, bb);
            if (this.averageGroundLvl < 0) {
                return true;
            }
            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 12 - 1, 0);
        }
        BlockPos pos = TFCMain.CURRENT_VILLAGE_CHUNK.get();
        ChunkDataTFC data = ChunkDataTFC.get(world, pos);
        Rock rock = data.getRock1(pos);
        IBlockState stone = TFCMain.getChunkSpecificRock(rock, Blocks.COBBLESTONE);
        IBlockState northStairs = TFCMain.getChunkSpecificRock(rock, Blocks.STONE_STAIRS, EnumFacing.NORTH);
        IBlockState westStairs = TFCMain.getChunkSpecificRock(rock, Blocks.STONE_STAIRS, EnumFacing.WEST);
        IBlockState eastStairs = TFCMain.getChunkSpecificRock(rock, Blocks.STONE_STAIRS, EnumFacing.EAST);
        this.fillWithBlocks(world, bb, 1, 1, 1, 3, 3, 7, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithBlocks(world, bb, 1, 5, 1, 3, 9, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithBlocks(world, bb, 1, 0, 0, 3, 0, 8, stone, stone, false);
        this.fillWithBlocks(world, bb, 1, 1, 0, 3, 10, 0, stone, stone, false);
        this.fillWithBlocks(world, bb, 0, 1, 1, 0, 10, 3, stone, stone, false);
        this.fillWithBlocks(world, bb, 4, 1, 1, 4, 10, 3, stone, stone, false);
        this.fillWithBlocks(world, bb, 0, 0, 4, 0, 4, 7, stone, stone, false);
        this.fillWithBlocks(world, bb, 4, 0, 4, 4, 4, 7, stone, stone, false);
        this.fillWithBlocks(world, bb, 1, 1, 8, 3, 4, 8, stone, stone, false);
        this.fillWithBlocks(world, bb, 1, 5, 4, 3, 10, 4, stone, stone, false);
        this.fillWithBlocks(world, bb, 1, 5, 5, 3, 5, 7, stone, stone, false);
        this.fillWithBlocks(world, bb, 0, 9, 0, 4, 9, 4, stone, stone, false);
        this.fillWithBlocks(world, bb, 0, 4, 0, 4, 4, 4, stone, stone, false);
        this.setBlockState(world, stone, 0, 11, 2, bb);
        this.setBlockState(world, stone, 4, 11, 2, bb);
        this.setBlockState(world, stone, 2, 11, 0, bb);
        this.setBlockState(world, stone, 2, 11, 4, bb);
        this.setBlockState(world, stone, 1, 1, 6, bb);
        this.setBlockState(world, stone, 1, 1, 7, bb);
        this.setBlockState(world, stone, 2, 1, 7, bb);
        this.setBlockState(world, stone, 3, 1, 6, bb);
        this.setBlockState(world, stone, 3, 1, 7, bb);
        this.setBlockState(world, northStairs, 1, 1, 5, bb);
        this.setBlockState(world, northStairs, 2, 1, 6, bb);
        this.setBlockState(world, northStairs, 3, 1, 5, bb);
        this.setBlockState(world, westStairs, 1, 2, 7, bb);
        this.setBlockState(world, eastStairs, 3, 2, 7, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 3, 2, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 4, 2, 2, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 4, 3, 2, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 6, 2, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 7, 2, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 4, 6, 2, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 4, 7, 2, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 2, 6, 0, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 2, 7, 0, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 2, 6, 4, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 2, 7, 4, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 3, 6, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 4, 3, 6, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 2, 3, 8, bb);
        this.placeTorch(world, EnumFacing.SOUTH, 2, 4, 7, bb);
        this.placeTorch(world, EnumFacing.EAST, 1, 4, 6, bb);
        this.placeTorch(world, EnumFacing.WEST, 3, 4, 6, bb);
        this.placeTorch(world, EnumFacing.NORTH, 2, 4, 5, bb);
        IBlockState ladder = Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.WEST);
        for (int i = 1; i <= 9; ++i) {
            this.setBlockState(world, ladder, 3, i, 3, bb);
        }
        this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, 1, 0, bb);
        this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, 2, 0, bb);
        this.createVillageDoor(world, bb, rand, 2, 1, 0, EnumFacing.NORTH);
        if (this.getBlockStateFromPos(world, 2, 0, -1, bb).getMaterial() == Material.AIR && this.getBlockStateFromPos(world, 2, -1, -1, bb).getMaterial() != Material.AIR) {
            this.setBlockState(world, northStairs, 2, 0, -1, bb);
            if (this.getBlockStateFromPos(world, 2, -1, -1, bb).getBlock() == Blocks.GRASS_PATH) {
                this.setBlockState(world, Blocks.GRASS.getDefaultState(), 2, -1, -1, bb);
            }
        }
        for (int k = 0; k < 9; ++k) {
            for (int j = 0; j < 5; ++j) {
                this.clearCurrentPositionBlocksUpwards(world, j, 12, k, bb);
                this.replaceAirAndLiquidDownwards(world, stone, j, -1, k, bb);
            }
        }
        this.spawnVillagers(world, bb, 2, 1, 2, 1);
        return true;
    }
    
}
