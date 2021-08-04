package zone.rong.lolilib.enderio.mixin;

import crazypants.enderio.base.TileEntityEio;
import crazypants.enderio.base.machine.base.te.ICap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import zone.rong.lolilib.util.Dimensions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mixin(value = TileEntityEio.class, remap = false)
public abstract class TileEntityEioMixin extends TileEntity {

    @Shadow @Final @Nonnull private ICap.List iCaps;

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY && (world == null || !Dimensions.isEnd(world))) {
            return null;
        }
        return iCaps.first(capability, facing);
    }
}
