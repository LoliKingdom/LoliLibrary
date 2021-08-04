package zone.rong.lolilib.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        // OBJLoader.INSTANCE.addDomain(LoliLib.MOD_ID);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        // RenderingRegistry.registerEntityRenderingHandler(EntityManaPearl.class, new RenderManaPearl<>());
    }
}
