package zone.rong.lolilib.vanilla;

import it.unimi.dsi.fastutil.Hash;
import net.minecraft.item.ItemStack;

public class ItemStackHashStrategy implements Hash.Strategy<ItemStack> {

    public static final ItemStackHashStrategy INSTANCE = new ItemStackHashStrategy();

    private ItemStackHashStrategy() { }

    @Override
    public int hashCode(ItemStack o) {
        int hash = 1;
        hash = hash * 31 + o.getItem().hashCode();
        int metadata = o.getMetadata();
        return (metadata == Short.MAX_VALUE || metadata == 0) ? hash : hash * 31 + metadata;
    }

    @Override
    public boolean equals(ItemStack a, ItemStack b) {
        if (a == null || b == null) {
            return false;
        }
        return a.getItem() == b.getItem() && (a.getMetadata() == Short.MAX_VALUE || b.getMetadata() == Short.MAX_VALUE || a.getMetadata() == b.getMetadata());
    }

}
