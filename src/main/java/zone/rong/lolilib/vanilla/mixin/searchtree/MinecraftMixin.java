package zone.rong.lolilib.vanilla.mixin.searchtree;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.SearchTree;
import net.minecraft.client.util.SearchTreeManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow private SearchTreeManager searchTreeManager;

    /**
     * @author Rongmario
     * @reason To cut out recipeList from populating. And to use client player to take care of the tooltips.
     */
    @Overwrite
    public void populateSearchTreeManager() {
        final SearchTree<ItemStack> itemTree = new SearchTree<>(stack -> stack.getTooltip(null, ITooltipFlag.TooltipFlags.NORMAL)
                .stream()
                .map(TextFormatting::getTextWithoutFormattingCodes)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList()),
                stack -> Collections.singleton(Item.REGISTRY.getNameForObject(stack.getItem())));
        NonNullList<ItemStack> nnList = NonNullList.create();
        for (Item item : Item.REGISTRY) {
            item.getSubItems(CreativeTabs.SEARCH, nnList);
        }
        nnList.forEach(itemTree::add);
        this.searchTreeManager.register(SearchTreeManager.ITEMS, itemTree);
    }

}
