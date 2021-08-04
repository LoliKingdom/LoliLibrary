package zone.rong.lolilib.enderio.mixin;

import crazypants.enderio.base.machine.baselegacy.AbstractPoweredMachineEntity;
import crazypants.enderio.base.power.forge.tile.ILegacyPoweredTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import zone.rong.lolilib.util.Dimensions;

@Mixin(AbstractPoweredMachineEntity.class)
public abstract class AbstractPoweredMachineEntityMixin extends TileEntity implements ILegacyPoweredTile {

    @Shadow private int storedEnergyRF;

    @Redirect(method = "doUpdate", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/world/World;isRemote:Z"), remap = false)
    private boolean checkWorld(World world) {
        return world.isRemote || !Dimensions.isEnd(world);
    }

    @Override
    public void setEnergyStored(int stored) {
        if (Dimensions.isEnd(world)) {
            storedEnergyRF = world.isRemote ? stored : MathHelper.clamp(stored, 0, getMaxEnergyStored());
        }
    }

}
