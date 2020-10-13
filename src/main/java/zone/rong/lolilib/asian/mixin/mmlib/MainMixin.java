package zone.rong.lolilib.asian.mixin.mmlib;

import cn.mcmod_mmf.mmlib.Main;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.oredict.OreDictionary;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Main.class)
public class MainMixin {

    /**
     * @author Rongmario
     */
    @Overwrite
    @Method(modid = "tfc")
    private void registerTFCOreDict() { } // TODO: should we register TFC oredicts?

    /**
     * @author Rongmario
     */
    @Overwrite
    private void registerVanillaFoods() {
        OreDictionary.registerOre("sakuraLeaves", new ItemStack(Items.DYE, 1, 9));
    }

}
