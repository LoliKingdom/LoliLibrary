package zone.rong.lolilib.tfc.mixin.world;

import net.dries007.tfc.world.classic.worldgen.WorldGenTrees;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Random;

@Mixin(WorldGenTrees.class)
public class WorldGenTreesMixin {

    /**
     * @author Rongmario
     * @reason Remove loose sticks generation
     */
    @Overwrite
    public static void generateLooseSticks(Random rand, int chunkX, int chunkZ, World world, int amount) {
        // NO-OP
    }

}
