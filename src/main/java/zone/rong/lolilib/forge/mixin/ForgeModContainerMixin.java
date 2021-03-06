package zone.rong.lolilib.forge.mixin;

import com.google.common.eventbus.Subscribe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.stats.StatList;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLModIdMappingEvent;
import net.minecraftforge.oredict.OreDictionary;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ForgeModContainer.class, remap = false)
public class ForgeModContainerMixin {

    /**
     * @author Rongmario
     * @reason To take out the client recipe book
     */
    @Subscribe
    @Overwrite
    public void mappingChanged(FMLModIdMappingEvent event) {
        OreDictionary.rebakeMap();
        StatList.reinit();
        Ingredient.invalidateAll();
        FMLCommonHandler.instance().reloadSearchTrees();
        FMLCommonHandler.instance().reloadCreativeSettings();
    }

}
