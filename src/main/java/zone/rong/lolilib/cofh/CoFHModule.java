package zone.rong.lolilib.cofh;

import cn.mcmod.sakura.block.BlockLoader;
import cofh.thermalexpansion.util.managers.device.TapperManager;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import zone.rong.lolilib.LoliModule;

public class CoFHModule extends LoliModule {

    @Override
    public void onFMLPostInit(FMLPostInitializationEvent event, Side side) {
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
}
