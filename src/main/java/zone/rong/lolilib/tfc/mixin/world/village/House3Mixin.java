package zone.rong.lolilib.tfc.mixin.world.village;

import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
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

@Mixin(StructureVillagePieces.House3.class)
public abstract class House3Mixin extends StructureVillagePieces.Village {

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox bb) {
        if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, bb);
            if (this.averageGroundLvl < 0) {
                return true;
            }
            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 7 - 1, 0);
        }
        BlockPos pos = TFCMain.CURRENT_VILLAGE_CHUNK.get();
        ChunkDataTFC data = ChunkDataTFC.get(world, pos);
        Rock rock = data.getRock1(pos);
        Tree tree = TFCMain.getTree(data);
        IBlockState stone = TFCMain.getChunkSpecificRock(rock, Blocks.COBBLESTONE);
        IBlockState northStairs = TFCMain.getChunkSpecificWood(tree, Blocks.OAK_STAIRS, EnumFacing.NORTH);
        IBlockState southStairs = TFCMain.getChunkSpecificWood(tree, Blocks.OAK_STAIRS, EnumFacing.SOUTH);
        IBlockState eastStairs = TFCMain.getChunkSpecificWood(tree, Blocks.OAK_STAIRS, EnumFacing.EAST);
        IBlockState westStairs = TFCMain.getChunkSpecificWood(tree, Blocks.OAK_STAIRS, EnumFacing.WEST);
        IBlockState planks =  TFCMain.getChunkSpecificWood(tree, Blocks.PLANKS);
        IBlockState log = TFCMain.getChunkSpecificWood(tree, Blocks.LOG);
        this.fillWithBlocks(world, bb, 1, 1, 1, 7, 4, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithBlocks(world, bb, 2, 1, 6, 8, 4, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithBlocks(world, bb, 2, 0, 5, 8, 0, 10, planks, planks, false);
        this.fillWithBlocks(world, bb, 1, 0, 1, 7, 0, 4, planks, planks, false);
        this.fillWithBlocks(world, bb, 0, 0, 0, 0, 3, 5, stone, stone, false);
        this.fillWithBlocks(world, bb, 8, 0, 0, 8, 3, 10, stone, stone, false);
        this.fillWithBlocks(world, bb, 1, 0, 0, 7, 2, 0, stone, stone, false);
        this.fillWithBlocks(world, bb, 1, 0, 5, 2, 1, 5, stone, stone, false);
        this.fillWithBlocks(world, bb, 2, 0, 6, 2, 3, 10, stone, stone, false);
        this.fillWithBlocks(world, bb, 3, 0, 10, 7, 3, 10, stone, stone, false);
        this.fillWithBlocks(world, bb, 1, 2, 0, 7, 3, 0, planks, planks, false);
        this.fillWithBlocks(world, bb, 1, 2, 5, 2, 3, 5, planks, planks, false);
        this.fillWithBlocks(world, bb, 0, 4, 1, 8, 4, 1, planks, planks, false);
        this.fillWithBlocks(world, bb, 0, 4, 4, 3, 4, 4, planks, planks, false);
        this.fillWithBlocks(world, bb, 0, 5, 2, 8, 5, 3, planks, planks, false);
        this.setBlockState(world, planks, 0, 4, 2, bb);
        this.setBlockState(world, planks, 0, 4, 3, bb);
        this.setBlockState(world, planks, 8, 4, 2, bb);
        this.setBlockState(world, planks, 8, 4, 3, bb);
        this.setBlockState(world, planks, 8, 4, 4, bb);
        for (int i = -1; i <= 2; ++i) {
            for (int j = 0; j <= 8; ++j) {
                this.setBlockState(world, northStairs, j, 4 + i, i, bb);
                if ((i > -1 || j <= 1) && (i > 0 || j <= 3) && (i > 1 || j != 5)) {
                    this.setBlockState(world, southStairs, j, 4 + i, 5 - i, bb);
                }
            }
        }
        this.fillWithBlocks(world, bb, 3, 4, 5, 3, 4, 10, planks, planks, false);
        this.fillWithBlocks(world, bb, 7, 4, 2, 7, 4, 10, planks, planks, false);
        this.fillWithBlocks(world, bb, 4, 5, 4, 4, 5, 10, planks, planks, false);
        this.fillWithBlocks(world, bb, 6, 5, 4, 6, 5, 10, planks, planks, false);
        this.fillWithBlocks(world, bb, 5, 6, 3, 5, 6, 10, planks, planks, false);
        for (int k = 4; k >= 1; --k) {
            this.setBlockState(world, planks, k, 2 + k, 7 - k, bb);
            for (int k1 = 8 - k; k1 <= 10; ++k1) {
                this.setBlockState(world, eastStairs, k, 2 + k, k1, bb);
            }
        }
        this.setBlockState(world, planks, 6, 6, 3, bb);
        this.setBlockState(world, planks, 7, 5, 4, bb);
        this.setBlockState(world, westStairs, 6, 6, 4, bb);
        for (int l = 6; l <= 8; ++l) {
            for (int l1 = 5; l1 <= 10; ++l1) {
                this.setBlockState(world, westStairs, l, 12 - l, l1, bb);
            }
        }
        this.setBlockState(world, log, 0, 2, 1, bb);
        this.setBlockState(world, log, 0, 2, 4, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 3, bb);
        this.setBlockState(world, log, 4, 2, 0, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 5, 2, 0, bb);
        this.setBlockState(world, log, 6, 2, 0, bb);
        this.setBlockState(world, log, 8, 2, 1, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 2, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 3, bb);
        this.setBlockState(world, log, 8, 2, 4, bb);
        this.setBlockState(world, planks, 8, 2, 5, bb);
        this.setBlockState(world, log, 8, 2, 6, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 7, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 8, bb);
        this.setBlockState(world, log, 8, 2, 9, bb);
        this.setBlockState(world, log, 2, 2, 6, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 2, 2, 7, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 2, 2, 8, bb);
        this.setBlockState(world, log, 2, 2, 9, bb);
        this.setBlockState(world, log, 4, 4, 10, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 5, 4, 10, bb);
        this.setBlockState(world, log, 6, 4, 10, bb);
        this.setBlockState(world, planks, 5, 5, 10, bb);
        this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, 1, 0, bb);
        this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, 2, 0, bb);
        this.placeTorch(world, EnumFacing.NORTH, 2, 3, 1, bb);
        this.createVillageDoor(world, bb, rand, 2, 1, 0, EnumFacing.NORTH);
        this.fillWithBlocks(world, bb, 1, 0, -1, 3, 2, -1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        if (this.getBlockStateFromPos(world, 2, 0, -1, bb).getMaterial() == Material.AIR && this.getBlockStateFromPos(world, 2, -1, -1, bb).getMaterial() != Material.AIR) {
            this.setBlockState(world, northStairs, 2, 0, -1, bb);
            if (this.getBlockStateFromPos(world, 2, -1, -1, bb).getBlock() == Blocks.GRASS_PATH) {
                this.setBlockState(world, Blocks.GRASS.getDefaultState(), 2, -1, -1, bb);
            }
        }
        for (int i1 = 0; i1 < 5; ++i1) {
            for (int i2 = 0; i2 < 9; ++i2) {
                this.clearCurrentPositionBlocksUpwards(world, i2, 7, i1, bb);
                this.replaceAirAndLiquidDownwards(world, stone, i2, -1, i1, bb);
            }
        }
        for (int j1 = 5; j1 < 11; ++j1) {
            for (int j2 = 2; j2 < 9; ++j2) {
                this.clearCurrentPositionBlocksUpwards(world, j2, 7, j1, bb);
                this.replaceAirAndLiquidDownwards(world, stone, j2, -1, j1, bb);
            }
        }
        this.spawnVillagers(world, bb, 4, 1, 2, 2);
        return true;
    }
    
}
