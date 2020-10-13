package zone.rong.lolilib.twilightforest;

import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;
import vazkii.botania.common.item.material.ItemManaResource;
import zone.rong.lolilib.LoliLib;

public class BlockTFPortalFrame extends BlockEndPortalFrame {

    public static final BlockTFPortalFrame INSTANCE = new BlockTFPortalFrame();

    protected BlockTFPortalFrame() {
        super();
        setRegistryName(LoliLib.MOD_ID, "tf_portal_frame");
        setUnlocalizedName(LoliLib.MOD_ID + "." + "tf_portal_frame");
        setLightLevel(0.125F);
        setHardness(-1.0F);
        setResistance(6000000.0F);
        setCreativeTab(TFItems.creativeTab);
        setSoundType(SoundType.GLASS);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote || state.getValue(EYE)) {
            return false;
        }
        ItemStack stack = player.getHeldItem(hand);
        if (stack.getItem() instanceof ItemManaResource && stack.getItemDamage() == 1) {
            player.swingArm(hand);
            world.setBlockState(pos, state.withProperty(EYE, true), 2);
            world.updateComparatorOutputLevel(pos, this);
            stack.shrink(1);
            for (int i = 0; i < 16; ++i) {
                double d0 = (pos.getX() + (5.0F + world.rand.nextFloat() * 6.0F) / 16.0F);
                double d1 = pos.getY() + 0.8125F;
                double d2 = (pos.getZ() + (5.0F + world.rand.nextFloat() * 6.0F) / 16.0F);
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
            world.playSound(null, pos, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            BlockPattern.PatternHelper patternHelper = getOrCreatePortalShape().match(world, pos);
            if (patternHelper != null) {
                BlockPos blockpos = patternHelper.getFrontTopLeft().add(-3, 0, -3);
                for (int j = 0; j < 3; ++j) {
                    for (int k = 0; k < 3; ++k) {
                        world.setBlockState(blockpos.add(j, 0, k), TFBlocks.twilight_portal.getDefaultState(), 2);
                    }
                }
                // TODO: summon lightning?
                world.playBroadcastSound(1038, blockpos.add(1, 0, 1), 0);
                return true;
            }
        }
        return false;
    }



}
