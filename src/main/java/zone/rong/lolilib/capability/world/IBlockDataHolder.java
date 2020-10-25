package zone.rong.lolilib.capability.world;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public interface IBlockDataHolder extends ICapabilitySerializable<NBTTagCompound> {

    boolean hasBreakProgress(BlockPos pos);

    float getBreakProgress(BlockPos pos);

    boolean addBreakProgress(BlockPos pos, float progress);

    void removeBreakProgress(BlockPos pos);

}
