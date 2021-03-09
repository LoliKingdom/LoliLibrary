package zone.rong.lolilib.vanilla.mixin.optimizations;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(World.class)
public abstract class WorldMixin {

    @Shadow public abstract int getSeaLevel();
    @Shadow public abstract boolean isAirBlock(BlockPos pos);
    @Shadow public abstract IBlockState getBlockState(BlockPos pos);

    /**
     * @author Rongmario
     * @reason Avoid extra BlockPos allocations
     */
    @Overwrite
    public IBlockState getGroundAboveSeaLevel(BlockPos pos) {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(pos);
        mutablePos.setY(this.getSeaLevel());
        BlockPos immutablePos;
        do {
            mutablePos.setY(mutablePos.getY() + 1);
        } while (!this.isAirBlock(immutablePos = mutablePos.toImmutable())); // Zannen-desu, we have to guarantee immutability here
        return this.getBlockState(immutablePos);
    }

}
