package zone.rong.lolilib.tfc.mixin.world.village;

import net.dries007.tfc.world.classic.biomes.BiomesTFC;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenVillage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zone.rong.lolilib.tfc.TFCMain;

import java.util.Random;

@Mixin(MapGenVillage.class)
public class VANILLA_MapGenVillageMixin {

    static {
        MapGenVillage.VILLAGE_SPAWN_BIOMES = BiomesTFC.getWorldGenBiomes();
    }

    @Mixin(MapGenVillage.Start.class)
    public static class StartMixin {

        @Inject(method = "<init>(Lnet/minecraft/world/World;Ljava/util/Random;III)V", at = @At("RETURN"))
        private void notifyThreadLocal(World worldIn, Random rand, int x, int z, int size, CallbackInfo ci) {
            TFCMain.CURRENT_VILLAGE_CHUNK.set(new BlockPos(x >> 4, 0, z >> 4));
        }

    }


}
