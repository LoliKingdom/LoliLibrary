package zone.rong.lolilib.capability.temperature;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;

@FunctionalInterface
public interface ITemperatureOwner {

    float getTemperatureDifference(IBlockState state, EntityPlayer player);

}
