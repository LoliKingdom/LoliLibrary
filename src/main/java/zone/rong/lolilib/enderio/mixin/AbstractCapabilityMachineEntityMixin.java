package zone.rong.lolilib.enderio.mixin;

import crazypants.enderio.base.machine.base.te.AbstractCapabilityMachineEntity;
import crazypants.enderio.base.machine.base.te.IEnergyLogic;
import crazypants.enderio.base.machine.base.te.NullEnergyLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import zone.rong.lolilib.util.Dimensions;

import javax.annotation.Nonnull;

@Mixin(value = AbstractCapabilityMachineEntity.class, remap = false)
public abstract class AbstractCapabilityMachineEntityMixin extends TileEntity {

    @Shadow @Final @Mutable @Nonnull private IEnergyLogic energyLogic;

    @Override
    protected void setWorldCreate(World world) {
        super.setWorldCreate(world);
        if (!Dimensions.isEnd(world)) {
            energyLogic = NullEnergyLogic.INSTANCE;
        }
    }
}
