package zone.rong.lolilib.tfc.mixin.world.village;

import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.blocks.stone.BlockRockVariant;
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

@Mixin(StructureVillagePieces.Path.class)
public abstract class PathMixin extends StructureVillagePieces.Road {

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox bb) {
        BlockPos pos = TFCMain.CURRENT_VILLAGE_CHUNK.get();
        ChunkDataTFC data = ChunkDataTFC.get(world, pos);
        Rock rock = data.getRock1(pos);
        Tree tree = TFCMain.getTree(data);
        IBlockState path = this.getBiomeSpecificBlockState(Blocks.GRASS_PATH.getDefaultState());
        IBlockState planks = TFCMain.getChunkSpecificWood(tree, Blocks.PLANKS);
        IBlockState gravel = TFCMain.getChunkSpecificRock(rock, Blocks.GRAVEL);
        IBlockState stone = TFCMain.getChunkSpecificRock(rock, Blocks.COBBLESTONE);
        for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i) {
            for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j) {
                BlockPos blockpos = new BlockPos(i, 64, j);
                if (bb.isVecInside(blockpos)) {
                    blockpos = world.getTopSolidOrLiquidBlock(blockpos).down();
                    if (blockpos.getY() < world.getSeaLevel()) {
                        blockpos = new BlockPos(blockpos.getX(), world.getSeaLevel() - 1, blockpos.getZ());
                    }
                    while (blockpos.getY() >= world.getSeaLevel() - 1) {
                        IBlockState check = world.getBlockState(blockpos);
                        if (check.getBlock() instanceof BlockRockVariant) {
                            Rock.Type type = ((BlockRockVariant) check.getBlock()).getType();
                            if (type.isGrass && world.isAirBlock(blockpos.up())) {
                                world.setBlockState(blockpos, path, 2);
                                break;
                            } else if (type == Rock.Type.SAND) {
                                world.setBlockState(blockpos, gravel, 2);
                                world.setBlockState(blockpos.down(), stone, 2);
                                break;
                            }
                        } else if (check.getMaterial().isLiquid()) {
                            world.setBlockState(blockpos, planks, 2);
                            break;
                        }
                        blockpos = blockpos.down();
                    }
                }
            }
        }
        return true;
    }

}