package zone.rong.lolilib.tfc.mixin.block;

import net.dries007.tfc.objects.blocks.devices.BlockFirePit;
import net.dries007.tfc.objects.blocks.property.ILightableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import snownee.cuisine.CuisineRegistry;
import zone.rong.lolilib.tfc.block.BlockCustomFirePit;

@Mixin(BlockFirePit.class)
public abstract class BlockFirePitMixin extends Block implements ILightableBlock {

    @Shadow @Final public static PropertyEnum<BlockFirePit.FirePitAttachment> ATTACHMENT;

    public BlockFirePitMixin(Material materialIn) {
        super(materialIn);
    }

    /**
     * @author Rongmario
     * @reason Allow TFC firepit to be transformed into our custom one
     */
    @Overwrite
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        if (stack.getItem() == CuisineRegistry.WOK && state.getValue(ATTACHMENT) == BlockFirePit.FirePitAttachment.NONE) {
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            world.setBlockState(pos, BlockCustomFirePit.INSTANCE.getDefaultState().withProperty(BlockHorizontal.FACING, facing.getOpposite()).withProperty(BlockCustomFirePit.ATTACHMENT, BlockCustomFirePit.Attachments.WOK));
            return true;
        } else {
            world.setBlockState(pos, state.withProperty(LIT, true)); // TODO - FIX
        }
        return false;
    }

}
