package zone.rong.lolilib.tfc.mixin.world;

import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.util.WorldRegenHandler;
import net.dries007.tfc.util.calendar.CalendarTFC;
import net.dries007.tfc.util.calendar.Month;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.dries007.tfc.world.classic.worldgen.*;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.spongepowered.asm.mixin.*;

import java.util.List;
import java.util.Random;

@Mixin(WorldRegenHandler.class)
public class WorldRegenHandlerMixin {

    @Shadow @Final private static List<ChunkPos> POSITIONS;
    @Shadow @Final private static Random RANDOM;
    @Shadow @Final private static WorldGenWildCrops CROPS_GEN;
    @Shadow @Final private static WorldGenBerryBushes BUSH_GEN;
    @Shadow @Final private static WorldGenSnowIce SNOW_GEN;
    @Final @Mutable private static WorldGenLooseRocks LOOSE_ROCKS_GEN;

    static {
        LOOSE_ROCKS_GEN = null;
    }

    /**
     * @author Rongmario
     * @reason Remove ChunkDataEvent.LOAD event listener
     */
    @Overwrite
    @SuppressWarnings("all")
    public static void onChunkLoad(ChunkDataEvent.Load event) {
    }

    /**
     * @author Rongmario
     * @reason Clean up + remove LOOSE_ROCK_GEN
     */
    @Overwrite
    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        if (!event.world.isRemote && event.phase == TickEvent.Phase.END && !POSITIONS.isEmpty()) {
            ChunkPos pos = POSITIONS.remove(0);
            ChunkDataTFC chunkDataTFC = ChunkDataTFC.get(event.world, pos.getBlock(0, 0, 0));
            IChunkProvider chunkProvider = event.world.getChunkProvider();
            IChunkGenerator chunkGenerator = ((ChunkProviderServer) chunkProvider).chunkGenerator;
            long updateDelta = CalendarTFC.PLAYER_TIME.getTicks() - chunkDataTFC.getLastUpdateTick();
            if (updateDelta > ConfigTFC.General.WORLD_REGEN.minimumTime * 24000 && !chunkDataTFC.isSpawnProtected()) {
                chunkDataTFC.resetLastUpdateTick();
            }
            if (CalendarTFC.CALENDAR_TIME.getMonthOfYear().isWithin(Month.APRIL, Month.JULY) && !chunkDataTFC.isSpawnProtected() && CalendarTFC.CALENDAR_TIME.getTotalYears() > chunkDataTFC.getLastUpdateYear()) {
                if (RANDOM.nextInt(20) == 0) {
                    CROPS_GEN.generate(RANDOM, pos.x, pos.z, event.world, chunkGenerator, chunkProvider);
                }
                BUSH_GEN.generate(RANDOM, pos.x, pos.z, event.world, chunkGenerator, chunkProvider);
                chunkDataTFC.resetLastUpdateYear();
            }
            if (updateDelta > 96000L) {
                SNOW_GEN.generate(RANDOM, pos.x, pos.z, event.world, chunkGenerator, chunkProvider);
            }
        }

    }

}
