package zone.rong.lolilib.vanilla.mixin.optimizations;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3i;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

/**
 * Thanks to Lithium -
 *
 * The vector of each Direction is usually stored inside another object, which introduces indirection and makes things
 * harder for the JVM to optimize. This patch simply hoists the offset fields to the EnumFacing enum itself.
 */
@Mixin(EnumFacing.class)
public class EnumFacingMixin {

    @Shadow @Final public static EnumFacing[] VALUES;

    /**
     * @reason Do not allocate an excessive number of EnumFacing arrays
     * @author JellySquid
     */
    @Overwrite
    public static EnumFacing random(Random rand) {
        return VALUES[rand.nextInt(VALUES.length)];
    }

    @Shadow @Final private int opposite;

    private int offsetX, offsetY, offsetZ; // TODO - ASM hack to make these final

    @Inject(method = "<init>", at = @At("RETURN"))
    private void reinit(String enumName, int ordinal, int id, int idOpposite, int idHorizontal, String name, EnumFacing.AxisDirection direction, EnumFacing.Axis axis, Vec3i vector, CallbackInfo ci) {
        this.offsetX = vector.getX();
        this.offsetY = vector.getY();
        this.offsetZ = vector.getZ();
    }

    /**
     * @reason Avoid the modulo/abs operations
     * @author JellySquid
     */
    @Overwrite
    public EnumFacing getOpposite() {
        return VALUES[this.opposite];
    }

    /**
     * @reason Avoid indirection to aid inlining
     * @author JellySquid
     */
    @Overwrite
    public int getFrontOffsetX() {
        return this.offsetX;
    }

    /**
     * @reason Avoid indirection to aid inlining
     * @author JellySquid
     */
    @Overwrite
    public int getFrontOffsetY() {
        return this.offsetY;
    }

    /**
     * @reason Avoid indirection to aid inlining
     * @author JellySquid
     */
    @Overwrite
    public int getFrontOffsetZ() {
        return this.offsetZ;
    }

}
