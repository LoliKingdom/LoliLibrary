package zone.rong.lolilib;

import net.minecraftforge.common.config.Config;

@Config(modid = LoliLib.MOD_ID)
public class LoliLibConfig {

    public static final LoliLibConfig INSTANCE = new LoliLibConfig();

    @Config.Comment("Enable this to use an internal StringPool. Recommend if not using Openj9, otherwise, turn this off and use '-Xenablestringconstantgc' as an argument")
    public boolean enableLoliStringPool = false;

}
