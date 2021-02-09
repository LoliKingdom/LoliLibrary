package zone.rong.lolilib.vanilla;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenCustomHashMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class FurnaceHelper {

    private static final Object2IntMap<ItemStack> cookTimes = new Object2IntOpenCustomHashMap<>(ItemStackHashStrategy.INSTANCE);

    static {
        cookTimes.defaultReturnValue(200);
    }

    public static int getCookTime(ItemStack stack) {
        return cookTimes.getInt(stack);
    }

}
