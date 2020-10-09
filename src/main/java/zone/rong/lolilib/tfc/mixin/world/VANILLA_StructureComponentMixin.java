package zone.rong.lolilib.tfc.mixin.world;

import net.dries007.tfc.objects.blocks.wood.BlockChestTFC;
import net.dries007.tfc.objects.blocks.wood.BlockDoorTFC;
import net.dries007.tfc.world.classic.WorldTypeTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import zone.rong.lolilib.tfc.TFCMain;

import javax.annotation.Nullable;
import java.util.Random;

@Mixin(StructureComponent.class)
public abstract class VANILLA_StructureComponentMixin {

    @Shadow protected abstract void setBlockState(World worldIn, IBlockState blockstateIn, int x, int y, int z, StructureBoundingBox boundingboxIn);

    /**
     * @author Rongmario
     */
    @Overwrite
    protected boolean generateChest(World world, StructureBoundingBox bb, Random rand, BlockPos pos, ResourceLocation loc, @Nullable IBlockState state) {
        if (world.getWorldType() instanceof WorldTypeTFC) {
            BlockChestTFC chest = BlockChestTFC.getBasic(TFCMain.getTree(ChunkDataTFC.get(world, pos)));
            if (bb.isVecInside(pos) && world.getBlockState(pos).getBlock() != chest) {
                if (state == null) {
                    state = chest.correctFacing(world, pos, chest.getDefaultState());
                }
                world.setBlockState(pos, state, 2);
                System.out.println(pos);
                TileEntity tileentity = world.getTileEntity(pos);
                if (tileentity instanceof TileEntityChest) {
                    ((TileEntityChest) tileentity).setLootTable(loc, rand.nextLong());
                }
                return true;
            }
        }
        return false;
    }

    /**
     * @author Rongmario
     */
    @Overwrite
    protected void generateDoor(World world, StructureBoundingBox bb, Random rand, int x, int y, int z, EnumFacing facing, BlockDoor door) {
        if (world.getWorldType() instanceof WorldTypeTFC) {
            IBlockState tfcDoor = BlockDoorTFC.get(TFCMain.getTree(ChunkDataTFC.get(world, TFCMain.CURRENT_VILLAGE_CHUNK.get()))).getDefaultState();
            this.setBlockState(world, tfcDoor.withProperty(BlockDoor.FACING, facing), x, y, z, bb);
            this.setBlockState(world, tfcDoor.withProperty(BlockDoor.FACING, facing).withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), x, y + 1, z, bb);
        } else {
            this.setBlockState(world, door.getDefaultState().withProperty(BlockDoor.FACING, facing), x, y, z, bb);
            this.setBlockState(world, door.getDefaultState().withProperty(BlockDoor.FACING, facing).withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), x, y + 1, z, bb);
        }
    }

}
