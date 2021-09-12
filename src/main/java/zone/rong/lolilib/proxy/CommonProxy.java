package zone.rong.lolilib.proxy;

import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;
import zone.rong.lolilib.LoliModule;

public class CommonProxy {

    public void construct(FMLConstructionEvent event) {
        LoliModule.INSTANCES.forEach(m -> m.onFMLConstruct(event, Side.SERVER));
    }

    public void preInit(FMLPreInitializationEvent event) {
        LoliModule.INSTANCES.forEach(m -> m.onFMLPreInit(event, Side.SERVER));
    }
    public void init(FMLInitializationEvent event) {
        LoliModule.INSTANCES.forEach(m -> m.onFMLInit(event, Side.SERVER));
    }

    public void postInit(FMLPostInitializationEvent event) {
        LoliModule.INSTANCES.forEach(m -> m.onFMLPostInit(event, Side.SERVER));
    }

    public void start(FMLLoadCompleteEvent event) {
        LoliModule.INSTANCES.forEach(m -> m.onFMLLoadComplete(event, Side.SERVER));
    }

}
