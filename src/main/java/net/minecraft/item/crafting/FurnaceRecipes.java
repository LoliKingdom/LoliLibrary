package net.minecraft.item.crafting;

import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;
import zone.rong.lolilib.vanilla.ItemStackHashStrategy;

import java.util.Map;

/**
 * {@link zone.rong.lolilib.LoliLibTransformer}
 */
public class FurnaceRecipes {

    private static final FurnaceRecipes SMELTING_BASE = new FurnaceRecipes();

    public static FurnaceRecipes instance() {
        return SMELTING_BASE;
    }

    private final Map<ItemStack, ItemStack> smelt;
    private final Object2FloatMap<ItemStack> experience;

    public FurnaceRecipes() {
        this.smelt = new Object2ObjectOpenCustomHashMap<>(ItemStackHashStrategy.INSTANCE);
        this.experience = new Object2FloatOpenHashMap<>();

        this.experience.defaultReturnValue(0.005F);

        this.addSmeltingRecipeForBlock(Blocks.IRON_ORE, new ItemStack(Items.IRON_INGOT), 0.7F);
        this.addSmeltingRecipeForBlock(Blocks.GOLD_ORE, new ItemStack(Items.GOLD_INGOT), 1.0F);
        this.addSmeltingRecipeForBlock(Blocks.DIAMOND_ORE, new ItemStack(Items.DIAMOND), 1.0F);
        this.addSmeltingRecipeForBlock(Blocks.SAND, new ItemStack(Blocks.GLASS), 0.1F);
        this.addSmelting(Items.PORKCHOP, new ItemStack(Items.COOKED_PORKCHOP), 0.35F);
        this.addSmelting(Items.BEEF, new ItemStack(Items.COOKED_BEEF), 0.35F);
        this.addSmelting(Items.CHICKEN, new ItemStack(Items.COOKED_CHICKEN), 0.35F);
        this.addSmelting(Items.RABBIT, new ItemStack(Items.COOKED_RABBIT), 0.35F);
        this.addSmelting(Items.MUTTON, new ItemStack(Items.COOKED_MUTTON), 0.35F);
        this.addSmeltingRecipeForBlock(Blocks.COBBLESTONE, new ItemStack(Blocks.STONE), 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.DEFAULT_META), new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.CRACKED_META), 0.1F);
        this.addSmelting(Items.CLAY_BALL, new ItemStack(Items.BRICK), 0.3F);
        this.addSmeltingRecipeForBlock(Blocks.CLAY, new ItemStack(Blocks.HARDENED_CLAY), 0.35F);
        this.addSmeltingRecipeForBlock(Blocks.CACTUS, new ItemStack(Items.DYE, 1, EnumDyeColor.GREEN.getDyeDamage()), 0.2F);
        this.addSmeltingRecipeForBlock(Blocks.LOG, new ItemStack(Items.COAL, 1, 1), 0.15F);
        this.addSmeltingRecipeForBlock(Blocks.LOG2, new ItemStack(Items.COAL, 1, 1), 0.15F);
        this.addSmeltingRecipeForBlock(Blocks.EMERALD_ORE, new ItemStack(Items.EMERALD), 1.0F);
        this.addSmelting(Items.POTATO, new ItemStack(Items.BAKED_POTATO), 0.35F);
        this.addSmeltingRecipeForBlock(Blocks.NETHERRACK, new ItemStack(Items.NETHERBRICK), 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.SPONGE, 1, 1), new ItemStack(Blocks.SPONGE, 1, 0), 0.15F);
        this.addSmelting(Items.CHORUS_FRUIT, new ItemStack(Items.CHORUS_FRUIT_POPPED), 0.1F);

        for (ItemFishFood.FishType itemfishfood$fishtype : ItemFishFood.FishType.values())
        {
            if (itemfishfood$fishtype.canCook())
            {
                this.addSmeltingRecipe(new ItemStack(Items.FISH, 1, itemfishfood$fishtype.getMetadata()), new ItemStack(Items.COOKED_FISH, 1, itemfishfood$fishtype.getMetadata()), 0.35F);
            }
        }

        this.addSmeltingRecipeForBlock(Blocks.COAL_ORE, new ItemStack(Items.COAL), 0.1F);
        this.addSmeltingRecipeForBlock(Blocks.REDSTONE_ORE, new ItemStack(Items.REDSTONE), 0.7F);
        this.addSmeltingRecipeForBlock(Blocks.LAPIS_ORE, new ItemStack(Items.DYE, 1, EnumDyeColor.BLUE.getDyeDamage()), 0.2F);
        this.addSmeltingRecipeForBlock(Blocks.QUARTZ_ORE, new ItemStack(Items.QUARTZ), 0.2F);

        final ItemStack ironNugget = new ItemStack(Items.IRON_NUGGET);
        final ItemStack goldNugget = new ItemStack(Items.GOLD_NUGGET);

        this.addSmelting(Items.CHAINMAIL_HELMET, ironNugget, 0.1F);
        this.addSmelting(Items.CHAINMAIL_CHESTPLATE, ironNugget, 0.1F);
        this.addSmelting(Items.CHAINMAIL_LEGGINGS, ironNugget, 0.1F);
        this.addSmelting(Items.CHAINMAIL_BOOTS, ironNugget, 0.1F);
        this.addSmelting(Items.IRON_PICKAXE, ironNugget, 0.1F);
        this.addSmelting(Items.IRON_SHOVEL, ironNugget, 0.1F);
        this.addSmelting(Items.IRON_AXE, ironNugget, 0.1F);
        this.addSmelting(Items.IRON_HOE, ironNugget, 0.1F);
        this.addSmelting(Items.IRON_SWORD, ironNugget, 0.1F);
        this.addSmelting(Items.IRON_HELMET, ironNugget, 0.1F);
        this.addSmelting(Items.IRON_CHESTPLATE, ironNugget, 0.1F);
        this.addSmelting(Items.IRON_LEGGINGS, ironNugget, 0.1F);
        this.addSmelting(Items.IRON_BOOTS, ironNugget, 0.1F);
        this.addSmelting(Items.IRON_HORSE_ARMOR, ironNugget, 0.1F);
        this.addSmelting(Items.GOLDEN_PICKAXE, goldNugget, 0.1F);
        this.addSmelting(Items.GOLDEN_SHOVEL, goldNugget, 0.1F);
        this.addSmelting(Items.GOLDEN_AXE, goldNugget, 0.1F);
        this.addSmelting(Items.GOLDEN_HOE, goldNugget, 0.1F);
        this.addSmelting(Items.GOLDEN_SWORD, goldNugget, 0.1F);
        this.addSmelting(Items.GOLDEN_HELMET, goldNugget, 0.1F);
        this.addSmelting(Items.GOLDEN_CHESTPLATE, goldNugget, 0.1F);
        this.addSmelting(Items.GOLDEN_LEGGINGS, goldNugget, 0.1F);
        this.addSmelting(Items.GOLDEN_BOOTS, goldNugget, 0.1F);
        this.addSmelting(Items.GOLDEN_HORSE_ARMOR, goldNugget, 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.WHITE.getMetadata()), new ItemStack(Blocks.WHITE_GLAZED_TERRACOTTA), 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.ORANGE.getMetadata()), new ItemStack(Blocks.ORANGE_GLAZED_TERRACOTTA), 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.MAGENTA.getMetadata()), new ItemStack(Blocks.MAGENTA_GLAZED_TERRACOTTA), 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.LIGHT_BLUE.getMetadata()), new ItemStack(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA), 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.YELLOW.getMetadata()), new ItemStack(Blocks.YELLOW_GLAZED_TERRACOTTA), 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.LIME.getMetadata()), new ItemStack(Blocks.LIME_GLAZED_TERRACOTTA), 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.PINK.getMetadata()), new ItemStack(Blocks.PINK_GLAZED_TERRACOTTA), 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.GRAY.getMetadata()), new ItemStack(Blocks.GRAY_GLAZED_TERRACOTTA), 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.SILVER.getMetadata()), new ItemStack(Blocks.SILVER_GLAZED_TERRACOTTA), 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.CYAN.getMetadata()), new ItemStack(Blocks.CYAN_GLAZED_TERRACOTTA), 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.PURPLE.getMetadata()), new ItemStack(Blocks.PURPLE_GLAZED_TERRACOTTA), 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.BLUE.getMetadata()), new ItemStack(Blocks.BLUE_GLAZED_TERRACOTTA), 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.BROWN.getMetadata()), new ItemStack(Blocks.BROWN_GLAZED_TERRACOTTA), 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.GREEN.getMetadata()), new ItemStack(Blocks.GREEN_GLAZED_TERRACOTTA), 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.RED.getMetadata()), new ItemStack(Blocks.RED_GLAZED_TERRACOTTA), 0.1F);
        this.addSmeltingRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.BLACK.getMetadata()), new ItemStack(Blocks.BLACK_GLAZED_TERRACOTTA), 0.1F);
    }

    /**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     */
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
    public void addSmeltingRecipeForBlock(Block input, ItemStack stack, float experience) {
        this.addSmelting(Item.getItemFromBlock(input), stack, experience);
    }

    /**
     * Adds a smelting recipe using an Item as the input item.
     */
    public void addSmelting(Item input, ItemStack stack, float experience) {
        this.addSmeltingRecipe(new ItemStack(input, 1, 32767), stack, experience);
    }

    public ItemStack getSmeltingResult(ItemStack stack) {
        ItemStack result = smelt.get(stack);
        return result == null ? ItemStack.EMPTY : result;
    }

    public Map<ItemStack, ItemStack> getSmeltingList() {
        return smelt;
    }

    public float getSmeltingExperience(ItemStack stack) {
        float exp = stack.getItem().getSmeltingExperience(stack);
        if (exp == -1) {
            exp = experience.getFloat(stack);
        }
        return exp;
    }

}

