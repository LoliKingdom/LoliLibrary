package zone.rong.lolilib.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import it.unimi.dsi.fastutil.Hash;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import zone.rong.lolilib.LoliLibConfig;

import java.util.concurrent.ExecutionException;

import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;

public class VanillaUtils {

    private static final Cache<String, ResourceLocation> resourceLocationCache;

    private static Hash.Strategy<ItemStack> stackHashStrategy;

    static {
        resourceLocationCache = CacheBuilder.newBuilder().concurrencyLevel(2).maximumSize(10000).softValues().build();
    }

    public static String retrieveCanonicalString(String s) {
        return LoliLibConfig.INSTANCE.enableLoliStringPool ? s.intern() : StringPool.POOL.getCanonicalString(s);
    }

    public static ResourceLocation retrieveCanonicalResourceLocation(String domain, String path) {
        try {
            return resourceLocationCache.get(domain + ":" + path, () -> new ResourceLocation(domain, path));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new ResourceLocation(domain, path);
    }

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

    public static Hash.Strategy<ItemStack> getItemStackHashStrategy() {
        return stackHashStrategy = new Hash.Strategy<ItemStack>() {
            @Override
            public int hashCode(ItemStack stack) {
                int hash = Item.REGISTRY.getIDForObject(stack.getItem().delegate.get());
                if (stack.getItemDamage() == WILDCARD_VALUE) {
                    return hash;
                }
                return (hash | ((stack.getItemDamage() + 1) << 16));
            }
            @Override
            public boolean equals(ItemStack stack, ItemStack other) {
                return stack.getItem() == other.getItem() && stack.getItemDamage() == other.getItemDamage();
            }
        };
    }

    private VanillaUtils() {}

}
