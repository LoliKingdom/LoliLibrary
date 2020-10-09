package zone.rong.lolilib.tfc.mixin.world.village;

import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.blocks.BlockStairsTFC;
import net.dries007.tfc.objects.blocks.stone.BlockPressurePlateTFC;
import net.dries007.tfc.objects.blocks.stone.BlockRockVariant;
import net.dries007.tfc.objects.blocks.stone.BlockWoodPressurePlateTFC;
import net.dries007.tfc.objects.blocks.wood.BlockFenceTFC;
import net.dries007.tfc.objects.blocks.wood.BlockPlanksTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.block.Block;
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

@Mixin(StructureVillagePieces.House1.class)
public abstract class House1Mixin extends StructureVillagePieces.Village {
    
    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox bb) {
        if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, bb);
            if (this.averageGroundLvl < 0) {
                return true;
            }
            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 9 - 1, 0);
        }
        BlockPos pos = TFCMain.CURRENT_VILLAGE_CHUNK.get();
        ChunkDataTFC data = ChunkDataTFC.get(world, pos);
        Rock rock = data.getRock1(pos);
        Tree tree = TFCMain.getTree(data);
        IBlockState iblockstate = TFCMain.getChunkSpecificRock(rock, Blocks.COBBLESTONE);
        IBlockState iblockstate1 = TFCMain.getChunkSpecificWood(tree, Blocks.OAK_STAIRS, EnumFacing.NORTH);
        IBlockState iblockstate2 = TFCMain.getChunkSpecificWood(tree, Blocks.OAK_STAIRS, EnumFacing.SOUTH);
        IBlockState iblockstate3 = TFCMain.getChunkSpecificWood(tree, Blocks.OAK_STAIRS, EnumFacing.EAST);
        IBlockState iblockstate4 = TFCMain.getChunkSpecificWood(tree, Blocks.PLANKS);
        IBlockState iblockstate5 = TFCMain.getChunkSpecificRock(rock, Blocks.STONE_STAIRS, EnumFacing.NORTH);
        IBlockState iblockstate6 = TFCMain.getChunkSpecificWood(tree, Blocks.OAK_FENCE);
        IBlockState woodPlate = TFCMain.getChunkSpecificWood(tree, Blocks.WOODEN_PRESSURE_PLATE);
        this.fillWithBlocks(world, bb, 1, 1, 1, 7, 5, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithBlocks(world, bb, 0, 0, 0, 8, 0, 5, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, bb, 0, 5, 0, 8, 5, 5, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, bb, 0, 6, 1, 8, 6, 4, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, bb, 0, 7, 2, 8, 7, 3, iblockstate, iblockstate, false);
        for (int i = -1; i <= 2; ++i) {
            for (int j = 0; j <= 8; ++j) {
                this.setBlockState(world, iblockstate1, j, 6 + i, i, bb);
                this.setBlockState(world, iblockstate2, j, 6 + i, 5 - i, bb);
            }
        }
        this.fillWithBlocks(world, bb, 0, 1, 0, 0, 1, 5, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, bb, 1, 1, 5, 8, 1, 5, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, bb, 8, 1, 0, 8, 1, 4, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, bb, 2, 1, 0, 7, 1, 0, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, bb, 0, 2, 0, 0, 4, 0, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, bb, 0, 2, 5, 0, 4, 5, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, bb, 8, 2, 5, 8, 4, 5, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, bb, 8, 2, 0, 8, 4, 0, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, bb, 0, 2, 1, 0, 4, 4, iblockstate4, iblockstate4, false);
        this.fillWithBlocks(world, bb, 1, 2, 5, 7, 4, 5, iblockstate4, iblockstate4, false);
        this.fillWithBlocks(world, bb, 8, 2, 1, 8, 4, 4, iblockstate4, iblockstate4, false);
        this.fillWithBlocks(world, bb, 1, 2, 0, 7, 4, 0, iblockstate4, iblockstate4, false);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 4, 2, 0, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 5, 2, 0, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 6, 2, 0, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 4, 3, 0, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 5, 3, 0, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 6, 3, 0, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 3, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 3, 2, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 3, 3, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 2, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 3, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 8, 3, 2, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 8, 3, 3, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 2, 2, 5, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 3, 2, 5, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 5, 2, 5, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 6, 2, 5, bb);
        this.fillWithBlocks(world, bb, 1, 4, 1, 7, 4, 1, iblockstate4, iblockstate4, false);
        this.fillWithBlocks(world, bb, 1, 4, 4, 7, 4, 4, iblockstate4, iblockstate4, false);
        this.fillWithBlocks(world, bb, 1, 3, 4, 7, 3, 4, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
        this.setBlockState(world, iblockstate4, 7, 1, 4, bb);
        this.setBlockState(world, iblockstate3, 7, 1, 3, bb);
        this.setBlockState(world, iblockstate1, 6, 1, 4, bb);
        this.setBlockState(world, iblockstate1, 5, 1, 4, bb);
        this.setBlockState(world, iblockstate1, 4, 1, 4, bb);
        this.setBlockState(world, iblockstate1, 3, 1, 4, bb);
        this.setBlockState(world, iblockstate6, 6, 1, 3, bb);
        this.setBlockState(world, woodPlate, 6, 2, 3, bb);
        this.setBlockState(world, iblockstate6, 4, 1, 3, bb);
        this.setBlockState(world, woodPlate, 4, 2, 3, bb);
        this.setBlockState(world, Blocks.CRAFTING_TABLE.getDefaultState(), 7, 1, 1, bb);
        this.setBlockState(world, Blocks.AIR.getDefaultState(), 1, 1, 0, bb);
        this.setBlockState(world, Blocks.AIR.getDefaultState(), 1, 2, 0, bb);
        this.createVillageDoor(world, bb, rand, 1, 1, 0, EnumFacing.NORTH);
        if (this.getBlockStateFromPos(world, 1, 0, -1, bb).getMaterial() == Material.AIR && this.getBlockStateFromPos(world, 1, -1, -1, bb).getMaterial() != Material.AIR) {
            this.setBlockState(world, iblockstate5, 1, 0, -1, bb);
            if (this.getBlockStateFromPos(world, 1, -1, -1, bb).getBlock() == Blocks.GRASS_PATH) {
                this.setBlockState(world, Blocks.GRASS.getDefaultState(), 1, -1, -1, bb);
            }
        }
        for (int l = 0; l < 6; ++l) {
            for (int k = 0; k < 9; ++k) {
                this.clearCurrentPositionBlocksUpwards(world, k, 9, l, bb);
                this.replaceAirAndLiquidDownwards(world, iblockstate, k, -1, l, bb);
            }
        }
        this.spawnVillagers(world, bb, 2, 1, 2, 1);
        return true;
    }
    
}
