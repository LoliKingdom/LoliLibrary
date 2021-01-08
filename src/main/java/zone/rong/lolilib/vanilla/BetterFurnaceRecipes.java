package zone.rong.lolilib.vanilla;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.FMLLog;

import java.util.Map;

/**
 * Having to make the two maps static because superclass constructors gets called regardless of super() call when init
 */
public class BetterFurnaceRecipes extends FurnaceRecipes {

    private static final Map<ItemStack, ItemStack> smelt;
    private static final Object2FloatMap<ItemStack> experience;

    public static final BetterFurnaceRecipes INSTANCE;

    static {
        smelt = new Object2ObjectOpenCustomHashMap<>(ItemStackHashStrategy.INSTANCE);
        experience = new Object2FloatOpenHashMap<>();
        INSTANCE = new BetterFurnaceRecipes(); // Load last as this triggers the superclass ctor
    }

    /**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     */
    @Override
    public void addSmeltingRecipe(ItemStack input, ItemStack stack, float exp) {
        if (getSmeltingResult(input) != ItemStack.EMPTY) {
            FMLLog.log.info("Ignored smelting recipe with conflicting input: {} = {}", input, stack);
            return;
        }
        smelt.put(input, stack);
        experience.put(stack, exp);
    }

    /**
     * Adds a smelting recipe, where the input item is an instance of Block.
     */
    @Override
    public void addSmeltingRecipeForBlock(Block input, ItemStack stack, float experience) {
        this.addSmelting(Item.getItemFromBlock(input), stack, experience);
    }

    /**
     * Adds a smelting recipe using an Item as the input item.
     */
    @Override
    public void addSmelting(Item input, ItemStack stack, float experience) {
        this.addSmeltingRecipe(new ItemStack(input, 1, 32767), stack, experience);
    }

    @Override
    public ItemStack getSmeltingResult(ItemStack stack) {
        return smelt.getOrDefault(stack, ItemStack.EMPTY);
    }

    @Override
    public Map<ItemStack, ItemStack> getSmeltingList() {
        return smelt;
    }

    @Override
    public float getSmeltingExperience(ItemStack stack) {
        float exp = stack.getItem().getSmeltingExperience(stack);
        if (exp == -1) {
            exp = experience.getOrDefault(stack, 0F);
        }
        return exp;
    }

}
