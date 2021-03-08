package zone.rong.lolilib.tfc.block;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.objects.blocks.stone.BlockFarmlandTFC;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import zone.rong.lolilib.tfc.TFCMain;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockStick extends Block {

    public static final BlockStick INSTANCE = new BlockStick();

    public BlockStick() {
        super(Material.WOOD);
        this.setHardness(0.1F);
        this.setSoundType(SoundType.WOOD);
        setRegistryName(TerraFirmaCraft.MOD_ID, "stick_block");
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.add(new ItemStack(Items.STICK));
    }

    @Override
    public boolean canPlaceBlockAt(World world, @Nonnull BlockPos pos) {
        return world.isSideSolid(pos.down(), EnumFacing.UP) && super.canPlaceBlockAt(world, pos);
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, @Nonnull BlockPos pos) {
        return true;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
        super.neighborChanged(state, world, pos, blockIn, fromPos);
        BlockPos downPos;
        if (!world.isSideSolid((downPos = pos.down()), EnumFacing.UP) && !(world.getBlockState(downPos).getBlock() instanceof BlockFarmlandTFC)) {
            world.destroyBlock(pos, true);
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            world.destroyBlock(pos, true);
        }
        return true;
    }

    @Override
    public boolean canHarvestBlock(IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityPlayer player) {
        return true;
    }

    @Override
    @Nonnull
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Items.STICK);
    }

    @Nonnull
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Nonnull
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return TFCMain.GROUNDCOVER_AABB;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public boolean isPassable(IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public boolean isSideSolid(IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean addLandingEffects(IBlockState state, WorldServer world, BlockPos blockPosition, IBlockState iblockstate, EntityLivingBase entity, int numberOfParticles) {
        return true;
    }

    @Override
    public boolean addRunningEffects(IBlockState state, World world, BlockPos pos, Entity entity) {
        return true;
    }

}
