package zone.rong.lolilib.vanilla.mixin.optimizations;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * Inlining many BlockPos methods - taken from Lithium
 */
@Mixin(BlockPos.class)
public abstract class BlockPosMixin extends Vec3i {

    public BlockPosMixin(int xIn, int yIn, int zIn) {
        super(xIn, yIn, zIn);
    }

    /**
     * @author JellySquid
     * @reason Simplify and inline
     */
    @Overwrite
    public BlockPos up() {
        return new BlockPos(this.getX(), this.getY() + 1, this.getZ());
    }

    /**
     * @author JellySquid
     * @reason Simplify and inline
     */
    @Overwrite
    public BlockPos up(int distance) {
        return new BlockPos(this.getX(), this.getY() + distance, this.getZ());
    }

    /**
     * @author JellySquid
     * @reason Simplify and inline
     */
    @Overwrite
    public BlockPos down() {
        return new BlockPos(this.getX(), this.getY() - 1, this.getZ());
    }

    /**
     * @author JellySquid
     * @reason Simplify and inline
     */
    @Overwrite
    public BlockPos down(int distance) {
        return new BlockPos(this.getX(), this.getY() - distance, this.getZ());
    }

    /**
     * @author JellySquid
     * @reason Simplify and inline
     */
    @Overwrite
    public BlockPos north() {
        return new BlockPos(this.getX(), this.getY(), this.getZ() - 1);
    }

    /**
     * @author JellySquid
     * @reason Simplify and inline
     */
    @Overwrite
    public BlockPos north(int distance) {
        return new BlockPos(this.getX(), this.getY(), this.getZ() - distance);
    }

    /**
     * @author JellySquid
     * @reason Simplify and inline
     */
    @Overwrite
    public BlockPos south() {
        return new BlockPos(this.getX(), this.getY(), this.getZ() + 1);
    }

    /**
     * @author JellySquid
     * @reason Simplify and inline
     */
    @Overwrite
    public BlockPos south(int distance) {
        return new BlockPos(this.getX(), this.getY(), this.getZ() + distance);
    }

    /**
     * @author JellySquid
     * @reason Simplify and inline
     */
    @Overwrite
    public BlockPos west() {
        return new BlockPos(this.getX() - 1, this.getY(), this.getZ());
    }

    /**
     * @author JellySquid
     * @reason Simplify and inline
     */
    @Overwrite
    public BlockPos west(int distance) {
        return new BlockPos(this.getX() - distance, this.getY(), this.getZ());
    }

    /**
     * @author JellySquid
     * @reason Simplify and inline
     */
    @Overwrite
    public BlockPos east() {
        return new BlockPos(this.getX() + 1, this.getY(), this.getZ());
    }

    /**
     * @author JellySquid
     * @reason Simplify and inline
     */
    @Overwrite
    public BlockPos east(int distance) {
        return new BlockPos(this.getX() + distance, this.getY(), this.getZ());
    }

}
