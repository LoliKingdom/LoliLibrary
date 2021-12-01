package zone.rong.lolilib;

import it.unimi.dsi.fastutil.objects.ReferenceArraySet;
import it.unimi.dsi.fastutil.objects.ReferenceSet;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;
import zone.rong.lolilib.baubles.BaublesModule;
import zone.rong.lolilib.cofh.CoFHModule;
import zone.rong.lolilib.theaurorian.TheAurorianModule;

public class LoliModule {

    public static final ReferenceSet<LoliModule> INSTANCES = new ReferenceArraySet<>();

    static void initModules() {
        INSTANCES.add(new BaublesModule());
        INSTANCES.add(new CoFHModule());
        INSTANCES.add(new TheAurorianModule());
    }

    public void onCoreModInit(Side side) { }

    public void onMixinBooterInit(Side side) { }

    public void onFMLConstruct(FMLConstructionEvent event, Side side) { }

    public void onFMLPreInit(FMLPreInitializationEvent event, Side side) { }

    public void onFMLInit(FMLInitializationEvent event, Side side) { }

    public void onFMLPostInit(FMLPostInitializationEvent event, Side side) { }

    public void onFMLLoadComplete(FMLLoadCompleteEvent event, Side side) { }

}
