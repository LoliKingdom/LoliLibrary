package zone.rong.lolilib.vanilla.world;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.IWorldGenerator;
import zone.rong.lolilib.LoliLib;

import java.util.Random;

public class WorldGenOverworldStructures implements IWorldGenerator {

    public static final WorldGenerator testGenerator = new WorldGenerator() {

        private boolean generated = false;

        @Override
        public boolean generate(World world, Random rand, BlockPos position) {
            if (!generated && rand.nextInt(500) == 0) {
                System.out.println("Considered: " + position);
                TemplateManager templateManager = world.getSaveHandler().getStructureTemplateManager();
                Template template = templateManager.get(world.getMinecraftServer(), new ResourceLocation(LoliLib.MOD_ID, "tf_stronghold"));
                template.addBlocksToWorld(world, position, new PlacementSettings().setChunk(world.getChunkFromBlockCoords(position).getPos()).setSeed(rand.nextLong()));
                return generated = true;
            }
            return false;
        }
    };

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        BlockPos pos = new BlockPos((chunkX << 4) + 8, 5, (chunkX << 4) + 8);
        // testGenerator.generate(world, random, world.getTopSolidOrLiquidBlock(pos));
        testGenerator.generate(world, random, pos);
    }

}
