package zone.rong.lolilib.pyrotech;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.types.Rock;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import zone.rong.lolilib.pyrotech.block.BlockPTRock;

public class PyrotechMain {

    public static void init() {}

    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(BlockPTRock.generateBlocks());
    }

    public static BlockPTRock getPTRock(Rock rock) {
        return (BlockPTRock) ForgeRegistries.BLOCKS.getValue(new ResourceLocation(TerraFirmaCraft.MOD_ID, "pt_rock_".concat(rock.getRegistryName().getResourcePath())));
    }

}
