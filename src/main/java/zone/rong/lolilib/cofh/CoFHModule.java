package zone.rong.lolilib.cofh;

import cn.mcmod.sakura.block.BlockLoader;
import cofh.thermalexpansion.util.managers.device.TapperManager;
import cofh.thermalexpansion.util.managers.machine.CrucibleManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import zone.rong.lolilib.LoliLogger;
import zone.rong.lolilib.LoliModule;
import zone.rong.lolilib.util.LoliTimer;

public class CoFHModule extends LoliModule {

    @Override
    public void onFMLPostInit(FMLPostInitializationEvent event, Side side) {
        // Adds maple to tapping list
        TapperManager.addLeafMapping(BlockLoader.MAPLE_LOG.getDefaultState(), BlockLoader.MAPLE_LEAVE_GREEN.getDefaultState());
        TapperManager.addLeafMapping(BlockLoader.MAPLE_LOG.getDefaultState(), BlockLoader.MAPLE_LEAVE_ORANGE.getDefaultState());
        TapperManager.addLeafMapping(BlockLoader.MAPLE_LOG.getDefaultState(), BlockLoader.MAPLE_LEAVE_RED.getDefaultState());
        TapperManager.addLeafMapping(BlockLoader.MAPLE_LOG.getDefaultState(), BlockLoader.MAPLE_LEAVE_YELLOW.getDefaultState());
        TapperManager.addLeafMapping(BlockLoader.MAPLE_LOG_SAP.getDefaultState(), BlockLoader.MAPLE_LEAVE_GREEN.getDefaultState());
        TapperManager.addLeafMapping(BlockLoader.MAPLE_LOG_SAP.getDefaultState(), BlockLoader.MAPLE_LEAVE_ORANGE.getDefaultState());
        TapperManager.addLeafMapping(BlockLoader.MAPLE_LOG_SAP.getDefaultState(), BlockLoader.MAPLE_LEAVE_RED.getDefaultState());
        TapperManager.addLeafMapping(BlockLoader.MAPLE_LOG_SAP.getDefaultState(), BlockLoader.MAPLE_LEAVE_YELLOW.getDefaultState());
        TapperManager.addBlockStateMapping(BlockLoader.MAPLE_LOG.getDefaultState(), new FluidStack(BlockLoader.MAPLE_SYRUP_FLUID, 40));
        TapperManager.addBlockStateMapping(BlockLoader.MAPLE_LOG_SAP.getDefaultState(), new FluidStack(BlockLoader.MAPLE_SYRUP_FLUID, 80));
    }

    @Override
    public void onFMLLoadComplete(FMLLoadCompleteEvent event, Side side) {
        // Disallows ores smelting and doubling+ in the magma crucible
        LoliTimer timer = LoliTimer.createAndStart(logger -> logger.info("Removing ore melting recipes from Thermal Expansion's Magma Crucible."));
        int oreRecipesRemoved = 0;
        for (CrucibleManager.CrucibleRecipe recipe : CrucibleManager.getRecipeList()) {
            ItemStack input = recipe.getInput();
            for (int oreId : OreDictionary.getOreIDs(input)) {
                if (OreDictionary.getOreName(oreId).startsWith("ore")) {
                    if (recipe.getOutput().amount % 144 == 0) {
                        CrucibleManager.removeRecipe(input);
                        oreRecipesRemoved++;
                    }
                }
            }
        }
        int finalOreRecipesRemoved = oreRecipesRemoved;
        timer.stop((logger, elapsedTime) -> logger.info("Removed {} of Magma Crucible's ore melting recipes", finalOreRecipesRemoved));
    }
}
