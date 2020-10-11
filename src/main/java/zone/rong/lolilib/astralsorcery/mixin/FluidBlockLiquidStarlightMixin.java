package zone.rong.lolilib.astralsorcery.mixin;

import com.lumintorious.ambiental.api.IBlockTemperatureOwner;
import com.lumintorious.ambiental.modifiers.BlockModifier;
import hellfirepvp.astralsorcery.common.block.fluid.FluidBlockLiquidStarlight;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import zone.rong.lolilib.capability.temperature.ITemperatureOwner;

@Mixin(FluidBlockLiquidStarlight.class)
public class FluidBlockLiquidStarlightMixin implements IBlockTemperatureOwner, ITemperatureOwner {

    @Override
    public BlockModifier getModifier(IBlockState iBlockState, BlockPos blockPos, EntityPlayer entityPlayer) {
        return new BlockModifier("starlight_fluid_block", -50.0F, 5.0F, true);
    }

    @Override
    public float getTemperatureDifference(IBlockState state, EntityPlayer player) {
        return -10.0F;
    }

}