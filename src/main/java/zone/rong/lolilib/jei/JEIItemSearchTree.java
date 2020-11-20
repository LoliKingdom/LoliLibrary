package zone.rong.lolilib.jei;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mezz.jei.gui.ingredients.IIngredientListElement;
import mezz.jei.ingredients.IngredientFilter;
import net.minecraft.client.util.SearchTree;
import net.minecraft.item.ItemStack;
import zone.rong.lolilib.LoliLogger;

import java.util.Collections;
import java.util.List;

public class JEIItemSearchTree extends SearchTree<ItemStack> {

    public JEIItemSearchTree() {
        super(null, null);
    }

    @Override
    public void recalculate() {}

    @Override
    public void add(ItemStack element) {}

    @Override
    @SuppressWarnings("unchecked")
    public List<ItemStack> search(String searchText) {
        try {
            final List<ItemStack> results = new ObjectArrayList<>();
            for (final IIngredientListElement<?> element : ((List<IIngredientListElement<?>>) IngredientListUncachedContainer.handle_getIngredientListUncached.invokeExact((IngredientFilter) LoliJEIPlugin.ingredientFilter, searchText))) {
                if (element.getIngredient() instanceof ItemStack) {
                    results.add((ItemStack) element.getIngredient());
                }
            }
            return results;
        } catch (Throwable e) {
            return Collections.emptyList();
        }
    }

    /*
    @Override
    @SuppressWarnings("unchecked")
    public List<ItemStack> search(String searchText) {
        try {
            return ((List<IIngredientListElement<?>>) IngredientListUncachedContainer.handle_getIngredientListUncached.invokeExact(LoliJEIPlugin.ingredientFilter, searchText))
                    .stream()
                    .filter(e -> e.getIngredient() instanceof ItemStack)
                    .map(e -> ((ItemStack) e.getIngredient()))
                    .collect(Collectors.toList());
        } catch (Throwable e) {
            return Collections.emptyList();
        }
    }
     */

}
