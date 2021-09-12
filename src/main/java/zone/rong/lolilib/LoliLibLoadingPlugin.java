package zone.rong.lolilib;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
@IFMLLoadingPlugin.SortingIndex(-750)
@IFMLLoadingPlugin.Name("Loli Extension Plugin")
public class LoliLibLoadingPlugin implements IFMLLoadingPlugin {

    static {
        LoliModule.initModules();
    }

    public LoliLibLoadingPlugin() {
        MixinBootstrap.init();
        LoliModule.INSTANCES.forEach(m -> m.onCoreModInit(FMLLaunchHandler.side()));
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[] { "zone.rong.lolilib.LoliLibTransformer" };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) { }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
    
}