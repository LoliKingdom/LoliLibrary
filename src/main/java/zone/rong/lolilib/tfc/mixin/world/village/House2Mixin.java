package zone.rong.lolilib.tfc.mixin.world.village;

import com.codetaylor.mc.pyrotech.modules.storage.ModuleStorage;
import com.codetaylor.mc.pyrotech.modules.tech.basic.ModuleTechBasic;
import com.codetaylor.mc.pyrotech.modules.tech.bloomery.ModuleTechBloomery;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.blocks.metal.BlockAnvilTFC;
import net.dries007.tfc.objects.blocks.stone.BlockStoneAnvil;
import net.dries007.tfc.objects.blocks.wood.BlockChestTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.storage.loot.LootTableList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import zone.rong.lolilib.tfc.TFCMain;

import java.util.Random;

@Mixin(StructureVillagePieces.House2.class)
public abstract class House2Mixin extends StructureVillagePieces.Village {

    @Shadow private boolean hasMadeChest;

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox bb) {
        if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, bb);
            if (this.averageGroundLvl < 0) {
                return true;
            }
            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 6 - 1, 0);
        }
        BlockPos pos = TFCMain.CURRENT_VILLAGE_CHUNK.get();
        ChunkDataTFC data = ChunkDataTFC.get(world, pos);
        Rock rock = data.getRock1(pos);
        Tree tree = TFCMain.getTree(data);
        IBlockState iblockstate = TFCMain.getChunkSpecificRock(rock, Blocks.COBBLESTONE);
        IBlockState iblockstate1 = TFCMain.getChunkSpecificWood(tree, Blocks.OAK_STAIRS, EnumFacing.NORTH);
        IBlockState iblockstate2 = TFCMain.getChunkSpecificWood(tree, Blocks.OAK_STAIRS, EnumFacing.WEST);
        IBlockState iblockstate3 = TFCMain.getChunkSpecificWood(tree, Blocks.PLANKS);
        IBlockState iblockstate4 = TFCMain.getChunkSpecificRock(rock, Blocks.STONE_STAIRS, EnumFacing.NORTH);
        IBlockState iblockstate5 = TFCMain.getChunkSpecificWood(tree, Blocks.LOG);
        IBlockState iblockstate6 = TFCMain.getChunkSpecificWood(tree, Blocks.OAK_FENCE);
        IBlockState stoneSlab = TFCMain.getChunkSpecificRock(rock, Blocks.STONE_SLAB);
        // IBlockState doubleStoneSlab = TFCMain.getChunkSpecificRock(rock, Blocks.DOUBLE_STONE_SLAB);
        IBlockState woodPlate = TFCMain.getChunkSpecificWood(tree, Blocks.WOODEN_PRESSURE_PLATE);
        this.fillWithBlocks(world, bb, 0, 1, 0, 9, 4, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithBlocks(world, bb, 0, 0, 0, 9, 0, 6, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, bb, 0, 4, 0, 9, 4, 6, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, bb, 0, 5, 0, 9, 5, 6, stoneSlab, stoneSlab, false);
        this.fillWithBlocks(world, bb, 1, 5, 1, 8, 5, 5, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithBlocks(world, bb, 1, 1, 0, 2, 3, 0, iblockstate3, iblockstate3, false);
        this.fillWithBlocks(world, bb, 0, 1, 0, 0, 4, 0, iblockstate5, iblockstate5, false);
        this.fillWithBlocks(world, bb, 3, 1, 0, 3, 4, 0, iblockstate5, iblockstate5, false);
        this.fillWithBlocks(world, bb, 0, 1, 6, 0, 4, 6, iblockstate5, iblockstate5, false);
        this.setBlockState(world, iblockstate3, 3, 3, 1, bb);
        this.fillWithBlocks(world, bb, 3, 1, 2, 3, 3, 2, iblockstate3, iblockstate3, false);
        this.fillWithBlocks(world, bb, 4, 1, 3, 5, 3, 3, iblockstate3, iblockstate3, false);
        this.fillWithBlocks(world, bb, 0, 1, 1, 0, 3, 5, iblockstate3, iblockstate3, false);
        this.fillWithBlocks(world, bb, 1, 1, 6, 5, 3, 6, iblockstate3, iblockstate3, false);
        this.fillWithBlocks(world, bb, 5, 1, 0, 5, 3, 0, iblockstate6, iblockstate6, false);
        this.fillWithBlocks(world, bb, 9, 1, 0, 9, 3, 0, iblockstate6, iblockstate6, false);
        this.fillWithBlocks(world, bb, 6, 1, 4, 9, 4, 6, iblockstate, iblockstate, false);
        this.setBlockState(world, Blocks.FLOWING_LAVA.getDefaultState(), 7, 1, 5, bb);
        this.setBlockState(world, Blocks.FLOWING_LAVA.getDefaultState(), 8, 1, 5, bb);
        this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), 9, 2, 5, bb);
        this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), 9, 2, 4, bb);
        this.fillWithBlocks(world, bb, 7, 2, 4, 8, 2, 5, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.setBlockState(world, ModuleStorage.Blocks.TANK.getDefaultState(), 6, 1, 3, bb);
        // this.setBlockState(world, iblockstate, 6, 1, 3, bb);
        // this.setBlockState(world, Blocks.FURNACE.getDefaultState(), 6, 2, 3, bb);
        // this.setBlockState(world, Blocks.FURNACE.getDefaultState(), 6, 3, 3, bb);
        // this.setBlockState(world, doubleStoneSlab, 8, 1, 1, bb);
        this.setBlockState(world, BlockAnvilTFC.get(Metal.BISMUTH_BRONZE).getDefaultState(), 8, 1, 1, bb);
        // this.setBlockState(world, rand.nextInt(10) == 0 ? BlockAnvilTFC.get(Metal.GOLD).getDefaultState() : ModuleTechBasic.Blocks.ANVIL_GRANITE.getDefaultState(), 8, 1, 1, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 4, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 2, 2, 6, bb);
        this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 4, 2, 6, bb);
        this.setBlockState(world, iblockstate6, 2, 1, 4, bb);
        this.setBlockState(world, woodPlate, 2, 2, 4, bb);
        this.setBlockState(world, iblockstate3, 1, 1, 5, bb);
        this.setBlockState(world, iblockstate1, 2, 1, 5, bb);
        this.setBlockState(world, iblockstate2, 1, 1, 4, bb);
        if (!this.hasMadeChest && bb.isVecInside(new BlockPos(this.getXWithOffset(5, 5), this.getYWithOffset(1), this.getZWithOffset(5, 5)))) {
            this.hasMadeChest = true;
            this.generateChest(world, bb, rand, 5, 1, 5, LootTableList.CHESTS_VILLAGE_BLACKSMITH);
        }
        for (int i = 6; i <= 8; ++i) {
            if (this.getBlockStateFromPos(world, i, 0, -1, bb).getMaterial() == Material.AIR && this.getBlockStateFromPos(world, i, -1, -1, bb).getMaterial() != Material.AIR) {
                this.setBlockState(world, iblockstate4, i, 0, -1, bb);
                if (this.getBlockStateFromPos(world, i, -1, -1, bb).getBlock() == Blocks.GRASS_PATH) {
                    this.setBlockState(world, Blocks.GRASS.getDefaultState(), i, -1, -1, bb);
                }
            }
        }
        for (int k = 0; k < 7; ++k) {
            for (int j = 0; j < 10; ++j) {
                this.clearCurrentPositionBlocksUpwards(world, j, 6, k, bb);
                this.replaceAirAndLiquidDownwards(world, iblockstate, j, -1, k, bb);
            }
        }
        this.spawnVillagers(world, bb, 7, 1, 1, 1);
        return true;
    }
    
}
