package zone.rong.lolilib.vanilla;

import it.unimi.dsi.fastutil.Hash;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemStackHashStrategy implements Hash.Strategy<ItemStack> {

    public static final ItemStackHashStrategy INSTANCE = new ItemStackHashStrategy();

    private ItemStackHashStrategy() { }

    @Override
    public int hashCode(ItemStack o) {
        int hash = 1;
        hash = hash * 31 + Item.REGISTRY.getNameForObject(o.getItem()).hashCode();
        return o.getMetadata() != 32767 ? hash * 31 + o.getMetadata() : hash;
    }
    @Override
    public boolean equals(ItemStack a, ItemStack b) {
        if (b == null) {
            return false;
        }
        return a.getItem() == b.getItem() && (b.getMetadata() == 32767 || b.getMetadata() == a.getMetadata());
    }

}
