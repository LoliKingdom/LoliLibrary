package zone.rong.lolilib.forge.mixin;

import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLLog;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Mixin(DimensionManager.class)
public abstract class DimensionManagerMixin {

    @Shadow @Final private static ConcurrentMap<World, World> weakWorldMap;

    @Shadow @Final private static Int2ObjectMap<WorldServer> worlds;

    @Shadow @Final private static Multiset<Integer> leakedWorlds;

    @Shadow public static Integer[] getIDs() { return new Integer[0]; }

    /**
     * @author Rongmario
     * @reason Gives a more descriptive log on what World is leaking.
     */
    @Overwrite(remap = false)
    public static Integer[] getIDs(boolean check) {
        if (check) {
            List<World> allWorlds = Lists.newArrayList(weakWorldMap.keySet());
            allWorlds.removeAll(worlds.values());
            for (World w : allWorlds) {
                leakedWorlds.add(System.identityHashCode(w));
            }
            for (World w : allWorlds) {
                int leakCount = leakedWorlds.count(System.identityHashCode(w));
                if (leakCount == 5) {
                    FMLLog.log.debug("The world {}, {} of {} may have leaked: first encounter (5 occurrences).\n", Integer.toHexString(System.identityHashCode(w)), w.getProviderName(), w.getWorldInfo().getWorldName());
                } else if (leakCount % 5 == 0) {
                    FMLLog.log.debug("The world {}, {} of {} may have leaked: seen {} times.\n", Integer.toHexString(System.identityHashCode(w)), w.getProviderName(), w.getWorldInfo().getWorldName(), leakCount);
                }
            }
        }
        return getIDs();
    }

}
