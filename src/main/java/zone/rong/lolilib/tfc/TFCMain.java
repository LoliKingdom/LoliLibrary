package zone.rong.lolilib.tfc;

import net.dries007.tfc.api.recipes.heat.HeatRecipe;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.blocks.BlockSlabTFC;
import net.dries007.tfc.objects.blocks.BlockStairsTFC;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.blocks.stone.BlockPressurePlateTFC;
import net.dries007.tfc.objects.blocks.stone.BlockRockVariant;
import net.dries007.tfc.objects.blocks.stone.BlockWoodPressurePlateTFC;
import net.dries007.tfc.objects.blocks.wood.BlockFenceTFC;
import net.dries007.tfc.objects.blocks.wood.BlockLogTFC;
import net.dries007.tfc.objects.blocks.wood.BlockPlanksTFC;
import net.dries007.tfc.objects.te.TEPlacedItem;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;
import java.util.Random;

// @Mod.EventBusSubscriber
public class TFCMain {

    public static final String STAGE_DRANK_WATER = "tfc:drank_water";
    public static final ThreadLocal<BlockPos> CURRENT_VILLAGE_CHUNK = ThreadLocal.withInitial(() -> new BlockPos(0, 0, 0));

    private static final Random treeRandom = new Random();

    public static Tree getTree(ChunkDataTFC data) {
        List<Tree> trees = data.getValidTrees();
        if (trees.isEmpty()) {
            return data.getSparseGenTree();
        }
        return trees.get(treeRandom.nextInt(trees.size()));
    }

    public static IBlockState getChunkSpecificRock(Rock rock, Block block) {
        if (block == Blocks.COBBLESTONE) {
            return BlockRockVariant.get(rock, Rock.Type.BRICKS).getDefaultState();
        } else if (block == Blocks.STONE_SLAB) {
            return BlockSlabTFC.Half.get(rock, Rock.Type.SMOOTH).getDefaultState();
        } else if (block == Blocks.STONE_PRESSURE_PLATE) {
            return BlockPressurePlateTFC.get(rock).getDefaultState();
        } else if (block == Blocks.DOUBLE_STONE_SLAB) {
            return BlockSlabTFC.Double.get(rock, Rock.Type.SMOOTH).getDefaultState();
        }
        return block.getDefaultState();
    }

    public static IBlockState getChunkSpecificRock(Rock rock, Block block, EnumFacing facing) {
        if (block == Blocks.STONE_STAIRS) {
            return BlockStairsTFC.get(rock, Rock.Type.SMOOTH).getDefaultState().withProperty(BlockStairsTFC.FACING, facing);
        }
        return block.getDefaultState();
    }

    public static IBlockState getChunkSpecificWood(Tree tree, Block block) {
        if (block == Blocks.PLANKS) {
            return BlockPlanksTFC.get(tree).getDefaultState();
        } else if (block == Blocks.LOG) {
            return BlockLogTFC.get(tree).getDefaultState();
        } else if (block == Blocks.OAK_FENCE) {
            return BlockFenceTFC.get(tree).getDefaultState();
        } else if (block == Blocks.WOODEN_PRESSURE_PLATE) {
            return BlockWoodPressurePlateTFC.get(tree).getDefaultState();
        }
        return block.getDefaultState();
    }

    public static IBlockState getChunkSpecificWood(Tree tree, Block block, EnumFacing facing) {
        if (block == Blocks.OAK_STAIRS) {
            return BlockStairsTFC.get(tree).getDefaultState().withProperty(BlockStairsTFC.FACING, facing);
        }
        return block.getDefaultState();
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        EnumFacing facing;
        BlockPos offset;
        if ((facing = event.getFace()) != null && event.getWorld().isSideSolid(event.getPos(), facing) && event.getWorld().isAirBlock((offset = event.getPos().offset(facing)))) {
            ItemStack stack = event.getItemStack();
            if (HeatRecipe.get(stack, Metal.Tier.TIER_I) != null) {
                event.getWorld().setBlockState(offset, BlocksTFC.PLACED_ITEM.getDefaultState());
                TEPlacedItem tileEntity = Helpers.getTE(event.getWorld(), offset, TEPlacedItem.class);
                if (tileEntity != null) {
                    tileEntity.insertItem(event.getEntityPlayer(), stack, 1);
                }
            }
        }
    }

}