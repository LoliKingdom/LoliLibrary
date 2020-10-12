package zone.rong.lolilib.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Utils {

    public static AxisAlignedBB makeAABB(int fromX, int fromY, int fromZ, int toX, int toY, int toZ) {
        return new AxisAlignedBB(fromX / 16F, fromY / 16F, fromZ / 16F, toX / 16F, toY / 16F, toZ / 16F);
    }

    public static RayTraceResult rayTrace(World world, EntityPlayer player, boolean useLiquids) {
        float f = -player.rotationPitch;
        float f1 = -player.rotationYaw;
        double d0 = player.posX, d1 = player.posY + (double)player.getEyeHeight(), d2 = player.posZ;
        Vec3d vec3d = new Vec3d(d0, d1, d2);
        float f2 = MathHelper.cos(f1 * 0.017453292F - (float)Math.PI), f3 = MathHelper.sin(f1 * 0.017453292F - (float)Math.PI), f4 = -MathHelper.cos(f * 0.017453292F), f5 = MathHelper.sin(f * 0.017453292F);
        double d3 = player.getEntityAttribute(EntityPlayer.REACH_DISTANCE).getAttributeValue();
        return world.rayTraceBlocks(vec3d, vec3d.addVector((double) f3 * f4 * d3, (double) f5 * d3, (double) f2 * f4 * d3), useLiquids, !useLiquids, false);
    }

}
