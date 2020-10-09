package zone.rong.lolilib.util;

import net.minecraft.util.math.AxisAlignedBB;

public class AABBUtils {

    public static AxisAlignedBB makeAABB(int fromX, int fromY, int fromZ, int toX, int toY, int toZ) {
        return new AxisAlignedBB(fromX / 16F, fromY / 16F, fromZ / 16F, toX / 16F, toY / 16F, toZ / 16F);
    }

}
