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
            File astralsorcery = new File("./mods/".concat("astralsorcery-1.12.2-1.10.27.jar"));
            // File botania = new File("./mods/".concat("Botania+r1.10-357.jar"));
            File baubles = new File("./mods/".concat("Baubles-1.12-1.5.2.jar"));
            File enderio = new File("./mods/".concat("EnderIO-1.12.2-5.2.61.jar"));
            File gog = new File("./mods/".concat("GrimoireOfGaia3-1.12.2-1.7.2.jar"));
            File ic2 = new File("./mods/".concat("IC2Classic+1.12-1.5.5.2.1.jar"));
            File tfc = new File("./mods/".concat("TerraFirmaCraft-MC1.12.2-1.7.6.164.jar"));
            File mmlib = new File("./mods/".concat("MMLib-1.5.0.jar"));
            File pyrotech = new File("./mods/".concat("pyrotech-1.12.2-1.4.34.jar"));
            File sakura = new File("./mods/".concat("Sakura-1.0.1.0-1.12.2.jar"));
            loadModJar(astralsorcery);
            // loadModJar(botania);
            loadModJar(baubles);
            loadModJar(enderio);
            loadModJar(gog);
            loadModJar(ic2);
            loadModJar(tfc);
            loadModJar(mmlib);
            loadModJar(pyrotech);
            loadModJar(sakura);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.lolilib.vanilla.json");
        Mixins.addConfiguration("mixins.lolilib.asian.json");
        Mixins.addConfiguration("mixins.lolilib.astralsorcery.json");
        // Mixins.addConfiguration("mixins.lolilib.botania.json");
        Mixins.addConfiguration("mixins.lolilib.baubles.json");
        Mixins.addConfiguration("mixins.lolilib.forge.json");
        Mixins.addConfiguration("mixins.lolilib.gog.json");
        Mixins.addConfiguration("mixins.lolilib.ic2.json");
        Mixins.addConfiguration("mixins.lolilib.tfc.json");
        Mixins.addConfiguration("mixins.lolilib.thaumcraft.json");
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