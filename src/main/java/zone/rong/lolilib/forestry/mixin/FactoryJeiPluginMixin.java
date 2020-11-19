package zone.rong.lolilib.forestry.mixin;

import com.google.common.base.Preconditions;
import forestry.core.utils.JeiUtil;
import forestry.factory.ModuleFactory;
import forestry.factory.blocks.BlockRegistryFactory;
import forestry.factory.gui.*;
import forestry.factory.recipes.jei.FactoryJeiPlugin;
import forestry.factory.recipes.jei.bottler.BottlerRecipeCategory;
import forestry.factory.recipes.jei.bottler.BottlerRecipeMaker;
import forestry.factory.recipes.jei.carpenter.CarpenterRecipeCategory;
import forestry.factory.recipes.jei.carpenter.CarpenterRecipeMaker;
import forestry.factory.recipes.jei.carpenter.CarpenterRecipeTransferHandler;
import forestry.factory.recipes.jei.centrifuge.CentrifugeRecipeCategory;
import forestry.factory.recipes.jei.centrifuge.CentrifugeRecipeMaker;
import forestry.factory.recipes.jei.fabricator.FabricatorRecipeCategory;
import forestry.factory.recipes.jei.fabricator.FabricatorRecipeMaker;
import forestry.factory.recipes.jei.fabricator.FabricatorRecipeTransferHandler;
import forestry.factory.recipes.jei.fermenter.FermenterRecipeCategory;
import forestry.factory.recipes.jei.fermenter.FermenterRecipeMaker;
import forestry.factory.recipes.jei.moistener.MoistenerRecipeCategory;
import forestry.factory.recipes.jei.moistener.MoistenerRecipeMaker;
import forestry.factory.recipes.jei.rainmaker.RainmakerRecipeCategory;
import forestry.factory.recipes.jei.rainmaker.RainmakerRecipeMaker;
import forestry.factory.recipes.jei.squeezer.SqueezerRecipeCategory;
import forestry.factory.recipes.jei.squeezer.SqueezerRecipeMaker;
import forestry.factory.recipes.jei.still.StillRecipeCategory;
import forestry.factory.recipes.jei.still.StillRecipeMaker;
import forestry.modules.ModuleHelper;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.gui.IAdvancedGuiHandler;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.time.StopWatch;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import zone.rong.lolilib.LoliLogger;
import zone.rong.lolilib.forestry.ForestryAdvancedGuiHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Mixin(FactoryJeiPlugin.class)
public class FactoryJeiPluginMixin {

    @Shadow @Nullable public static IJeiHelpers jeiHelpers;

    // LoliLogger.INSTANCE.info("{} took {}ms", "Forestry-FactoryJeiPlugin", stopWatch.getTime(TimeUnit.MILLISECONDS));

    /**
     * @author Rongmario
     */
    @Overwrite(remap = false)
    public void register(IModRegistry registry) {
        StopWatch stopWatch = StopWatch.createStarted();
        if (ModuleHelper.isEnabled("factory")) {
            registry.addAdvancedGuiHandlers(new ForestryAdvancedGuiHandler());
            jeiHelpers = registry.getJeiHelpers();
            BlockRegistryFactory blocks = ModuleFactory.getBlocks();
            Preconditions.checkNotNull(blocks);
            IRecipeTransferRegistry transferRegistry = registry.getRecipeTransferRegistry();
            LoliLogger.INSTANCE.info("{} took {}ms", "Forestry-FactoryJeiPlugin INITIALIZATION", stopWatch.getTime(TimeUnit.MILLISECONDS));
            stopWatch.reset();
            stopWatch.start();
            if (ModuleFactory.machineEnabled("bottler")) {
                registry.addRecipes(BottlerRecipeMaker.getBottlerRecipes(registry.getIngredientRegistry()), "forestry.bottler");
                registry.addRecipeClickArea(GuiBottler.class, 107, 33, 26, 22, "forestry.bottler");
                registry.addRecipeClickArea(GuiBottler.class, 45, 33, 26, 22, "forestry.bottler");
                registry.addRecipeCatalyst(new ItemStack(blocks.bottler), new String[]{"forestry.bottler"});
                LoliLogger.INSTANCE.info("{} took {}ms", "Forestry-FactoryJeiPlugin BOTTLER", stopWatch.getTime(TimeUnit.MILLISECONDS));
                stopWatch.reset();
                stopWatch.start();
            } else {
                jeiHelpers.getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(blocks.bottler));
            }

            if (ModuleFactory.machineEnabled("carpenter")) {
                registry.addRecipes(CarpenterRecipeMaker.getCarpenterRecipes(), "forestry.carpenter");
                registry.addRecipeClickArea(GuiCarpenter.class, 98, 48, 21, 26, new String[]{"forestry.carpenter"});
                registry.addRecipeCatalyst(new ItemStack(blocks.carpenter), new String[]{"forestry.carpenter"});
                transferRegistry.addRecipeTransferHandler(new CarpenterRecipeTransferHandler(), "forestry.carpenter");
                LoliLogger.INSTANCE.info("{} took {}ms", "Forestry-FactoryJeiPlugin CARPENTER", stopWatch.getTime(TimeUnit.MILLISECONDS));
                stopWatch.reset();
                stopWatch.start();
            } else {
                jeiHelpers.getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(blocks.carpenter));
            }

            if (ModuleFactory.machineEnabled("centrifuge")) {
                registry.addRecipes(CentrifugeRecipeMaker.getCentrifugeRecipe(), "forestry.centrifuge");
                registry.addRecipeClickArea(GuiCentrifuge.class, 38, 22, 38, 14, "forestry.centrifuge");
                registry.addRecipeClickArea(GuiCentrifuge.class, 38, 54, 38, 14, "forestry.centrifuge");
                registry.addRecipeCatalyst(new ItemStack(blocks.centrifuge), "forestry.centrifuge");
                LoliLogger.INSTANCE.info("{} took {}ms", "Forestry-FactoryJeiPlugin CENTRIFUGE", stopWatch.getTime(TimeUnit.MILLISECONDS));
                stopWatch.reset();
                stopWatch.start();
            } else {
                jeiHelpers.getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(blocks.centrifuge));
            }

            if (ModuleFactory.machineEnabled("fabricator")) {
                registry.addRecipes(FabricatorRecipeMaker.getFabricatorRecipes(), "forestry.fabricator");
                registry.addRecipeClickArea(GuiFabricator.class, 121, 53, 18, 18, new String[]{"forestry.fabricator"});
                registry.addRecipeCatalyst(new ItemStack(blocks.fabricator), new String[]{"forestry.fabricator"});
                transferRegistry.addRecipeTransferHandler(new FabricatorRecipeTransferHandler(), "forestry.fabricator");
                LoliLogger.INSTANCE.info("{} took {}ms", "Forestry-FactoryJeiPlugin FABRICATOR", stopWatch.getTime(TimeUnit.MILLISECONDS));
                stopWatch.reset();
                stopWatch.start();
            } else {
                jeiHelpers.getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(blocks.fabricator));
            }

            if (ModuleFactory.machineEnabled("fermenter")) {
                registry.addRecipes(FermenterRecipeMaker.getFermenterRecipes(jeiHelpers.getStackHelper()), "forestry.fermenter");
                registry.addRecipeClickArea(GuiFermenter.class, 72, 40, 32, 18, new String[]{"forestry.fermenter"});
                registry.addRecipeCatalyst(new ItemStack(blocks.fermenter), new String[]{"forestry.fermenter"});
                LoliLogger.INSTANCE.info("{} took {}ms", "Forestry-FactoryJeiPlugin FERMENTER", stopWatch.getTime(TimeUnit.MILLISECONDS));
                stopWatch.reset();
                stopWatch.start();
            } else {
                jeiHelpers.getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(blocks.fermenter));
            }

            if (ModuleFactory.machineEnabled("moistener")) {
                registry.addRecipes(MoistenerRecipeMaker.getMoistenerRecipes(), "forestry.moistener");
                registry.addRecipeClickArea(GuiMoistener.class, 123, 35, 19, 21, new String[]{"forestry.moistener"});
                registry.addRecipeCatalyst(new ItemStack(blocks.moistener), new String[]{"forestry.moistener"});
                LoliLogger.INSTANCE.info("{} took {}ms", "Forestry-FactoryJeiPlugin MOISTENER", stopWatch.getTime(TimeUnit.MILLISECONDS));
                stopWatch.reset();
                stopWatch.start();
            } else {
                jeiHelpers.getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(blocks.moistener));
            }

            if (ModuleFactory.machineEnabled("rainmaker")) {
                registry.addRecipes(RainmakerRecipeMaker.getRecipes(), "forestry.rainmaker");
                registry.addRecipeCatalyst(new ItemStack(blocks.rainmaker), new String[]{"forestry.rainmaker"});
                LoliLogger.INSTANCE.info("{} took {}ms", "Forestry-FactoryJeiPlugin RAINMAKER", stopWatch.getTime(TimeUnit.MILLISECONDS));
                stopWatch.reset();
                stopWatch.start();
            } else {
                jeiHelpers.getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(blocks.rainmaker));
            }

            if (ModuleFactory.machineEnabled("squeezer")) {
                registry.addRecipes(SqueezerRecipeMaker.getSqueezerRecipes(), "forestry.squeezer");
                registry.addRecipes(SqueezerRecipeMaker.getSqueezerContainerRecipes(registry.getIngredientRegistry()), "forestry.squeezer");
                registry.addRecipeClickArea(GuiSqueezer.class, 76, 41, 43, 16, new String[]{"forestry.squeezer"});
                registry.addRecipeCatalyst(new ItemStack(blocks.squeezer), new String[]{"forestry.squeezer"});
                LoliLogger.INSTANCE.info("{} took {}ms", "Forestry-FactoryJeiPlugin SQUEEZER", stopWatch.getTime(TimeUnit.MILLISECONDS));
                stopWatch.reset();
                stopWatch.start();
            } else {
                jeiHelpers.getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(blocks.squeezer));
            }

            if (ModuleFactory.machineEnabled("still")) {
                registry.addRecipes(StillRecipeMaker.getStillRecipes(), "forestry.still");
                registry.addRecipeClickArea(GuiStill.class, 73, 17, 33, 57, new String[]{"forestry.still"});
                registry.addRecipeCatalyst(new ItemStack(blocks.still), new String[]{"forestry.still"});
                LoliLogger.INSTANCE.info("{} took {}ms", "Forestry-FactoryJeiPlugin STILL", stopWatch.getTime(TimeUnit.MILLISECONDS));
                stopWatch.reset();
                stopWatch.start();
            } else {
                jeiHelpers.getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(blocks.still));
            }

            if (ModuleFactory.machineEnabled("raintank")) {
                JeiUtil.addDescription(registry, new Block[]{blocks.raintank});
                LoliLogger.INSTANCE.info("{} took {}ms", "Forestry-FactoryJeiPlugin RAINTANK", stopWatch.getTime(TimeUnit.MILLISECONDS));
                stopWatch.stop();
            } else {
                jeiHelpers.getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(blocks.raintank));
            }

        }
    }

}
