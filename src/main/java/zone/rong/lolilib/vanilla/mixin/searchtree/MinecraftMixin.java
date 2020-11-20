package zone.rong.lolilib.vanilla.mixin.searchtree;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.SearchTreeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import zone.rong.lolilib.jei.JEIItemSearchTree;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow private SearchTreeManager searchTreeManager;

    /**
     * @author Rongmario
     * @reason An empty SearchTree is implemented, it will utilize JEI's own 'search tree'
     */
    @Overwrite
    public void populateSearchTreeManager() {
        /*
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
         */
        this.searchTreeManager.register(SearchTreeManager.ITEMS, new JEIItemSearchTree());
    }

}
