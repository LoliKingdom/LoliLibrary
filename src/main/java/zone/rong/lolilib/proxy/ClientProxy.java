package zone.rong.lolilib.proxy;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zone.rong.lolilib.LoliLib;
import zone.rong.lolilib.botania.EntityManaPearl;
import zone.rong.lolilib.botania.RenderManaPearl;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        OBJLoader.INSTANCE.addDomain(LoliLib.MOD_ID);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        RenderingRegistry.registerEntityRenderingHandler(EntityManaPearl.class, new RenderManaPearl<>());
    }
}
