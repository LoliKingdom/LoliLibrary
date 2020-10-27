package zone.rong.lolilib.tfc.block;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import snownee.cuisine.Cuisine;
import snownee.cuisine.CuisineRegistry;
import snownee.kiwi.block.BlockModHorizontal;

import java.util.Locale;

public class BlockCustomFirePit extends BlockModHorizontal {

    public static final PropertyEnum<Attachments> ATTACHMENT = PropertyEnum.create("attachment", Attachments.class);
    public static final BlockCustomFirePit INSTANCE = new BlockCustomFirePit();

    private static final AxisAlignedBB FIREPIT_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
    private static final AxisAlignedBB WOK_FIREPIT_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.475D, 1.0D);

    public BlockCustomFirePit() {
        super("fire_pit", Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockHorizontal.FACING, EnumFacing.NORTH).withProperty(ATTACHMENT, Attachments.NONE));
        this.setCreativeTab(Cuisine.CREATIVE_TAB);
        this.setLightLevel(0.9375F);
    }

    @SideOnly(Side.CLIENT)
    public void mapModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(this.getRegistryName(), "fire_pit"));
    }

    @Override
    public int getItemSubtypeAmount() {
        return Attachments.values().length;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return false;
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        IBlockState state = super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
        if (meta >= 0 && meta < Attachments.values().length) {
            state = state.withProperty(ATTACHMENT, Attachments.values()[meta]);
        }

        return state;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.getHorizontal(meta % 4)).withProperty(ATTACHMENT, Attachments.values()[MathHelper.clamp(meta / 4, 0, Attachments.values().length - 1)]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(BlockHorizontal.FACING)).getHorizontalIndex() + (state.getValue(ATTACHMENT)).ordinal() * 4;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, BlockHorizontal.FACING, ATTACHMENT);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return state.getValue(ATTACHMENT) == Attachments.NONE ? FIREPIT_AABB : WOK_FIREPIT_AABB;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItem(hand);
        if (stack.getItem() == CuisineRegistry.WOK && state.getValue(ATTACHMENT) == Attachments.NONE) {
            if (!playerIn.isCreative()) {
                stack.shrink(1);
            }
            world.setBlockState(pos, this.getDefaultState().withProperty(BlockHorizontal.FACING, playerIn.getHorizontalFacing().getOpposite()).withProperty(ATTACHMENT, Attachments.WOK));
            return true;
        }
        return false;
    }


    public enum Attachments implements IStringSerializable {

        NONE,
        WOK;

        @Override
        public String getName() {
            return this.toString().toLowerCase(Locale.ENGLISH);
        }

    }

}
