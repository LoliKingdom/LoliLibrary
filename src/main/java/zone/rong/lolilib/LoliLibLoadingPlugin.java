package zone.rong.lolilib;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
@IFMLLoadingPlugin.SortingIndex(-750)
@IFMLLoadingPlugin.Name("Loli Extension Plugin")
public class LoliLibLoadingPlugin implements IFMLLoadingPlugin {

    public LoliLibLoadingPlugin() {
        Mixins.addConfiguration("mixins.lolilib.forge.json");
        Mixins.addConfiguration("mixins.lolilib.vanilla.json");
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