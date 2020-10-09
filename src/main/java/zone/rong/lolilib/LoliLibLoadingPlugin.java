package zone.rong.lolilib;

import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.relauncher.CoreModManager;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

@IFMLLoadingPlugin.SortingIndex(10000)
public class LoliLibLoadingPlugin implements IFMLLoadingPlugin {

    public LoliLibLoadingPlugin() {
        try {
            File enderio = new File("./mods/".concat("EnderIO-1.12.2-5.2.61.jar"));
            File gog = new File("./mods/".concat("GrimoireOfGaia3-1.12.2-1.7.2.jar"));
            File tfc = new File("./mods/".concat("TerraFirmaCraft-MC1.12.2-1.7.4.162.jar"));
            File pyrotech = new File("./mods/".concat("pyrotech-1.12.2-1.4.34.jar"));
            loadModJar(enderio);
            loadModJar(gog);
            loadModJar(tfc);
            loadModJar(pyrotech);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.lolilib.enderio.json");
        Mixins.addConfiguration("mixins.lolilib.gog.json");
        Mixins.addConfiguration("mixins.lolilib.tfc.json");
        Mixins.addConfiguration("mixins.lolilib.pyrotech.json");
    }

    private void loadModJar(File jar) throws Exception {
        ((LaunchClassLoader) this.getClass().getClassLoader()).addURL(jar.toURI().toURL());
        CoreModManager.getReparseableCoremods().add(jar.getName());
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
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
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

}