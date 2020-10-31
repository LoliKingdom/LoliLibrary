package zone.rong.lolilib.redpill;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IRegistryDelegate;
import zone.rong.lolilib.LoliLib;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Redpill {

    private static final int stateLimit = 16;

    private static final MethodHandles.Lookup lookup;
    private static final Field modelLoader$customStateMappers, stateMap$ignored;

    static {
        lookup = MethodHandles.lookup();
        Field bruh$1 = null;
        Field bruh$2 = null;
        try {
            bruh$1 = ModelLoader.class.getDeclaredField("customStateMappers");
            bruh$1.setAccessible(true);
            bruh$2 = StateMap.class.getDeclaredField("ignored");
            bruh$2.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        modelLoader$customStateMappers = bruh$1;
        stateMap$ignored = bruh$2;
    }

    public static void based() throws Throwable {
        Loader.instance().getActiveModList().stream().map(ModContainer::getModId).forEach(id -> {
            final AtomicInteger count = new AtomicInteger(0);
            ForgeRegistries.BLOCKS.getKeys().stream().filter(r -> r.getResourceDomain().equals(id)).forEach(r -> {
                ForgeRegistries.BLOCKS.getValue(r).getBlockState().getValidStates().forEach(s -> {
                    count.incrementAndGet();
                });
            });
            LoliLib.LOGGER.info("{} has {} blockstates.", id, count.get());
        });
        Map<IRegistryDelegate<Block>, IStateMapper> map = (Map<IRegistryDelegate<Block>, IStateMapper>) modelLoader$customStateMappers.get(null);
        ForgeRegistries.BLOCKS.forEach(b -> {
            if (b.getBlockState().getValidStates().size() > stateLimit) {
                LoliLib.LOGGER.warn("{} has {} blockstates", b.getRegistryName(), b.getBlockState().getValidStates().size());
                // LoliLib.LOGGER.warn("That is approximately {} bytes taken up,", new ObjectSizeCalculator(ObjectSizeCalculator.getEffectiveMemoryLayoutSpecification()).calculateObjectSize(b.getBlockState()));
                if (map.containsKey(b.delegate)) {
                    IStateMapper stateMapper = map.get(b.delegate);
                    if (stateMapper instanceof StateMap) {
                        try {
                            List<IProperty<?>> ignored = (List<IProperty<?>>) stateMap$ignored.get(stateMapper);
                            if (!ignored.isEmpty()) {
                                for (IProperty<?> property : ignored) {
                                    LoliLib.LOGGER.error("{} has {} property disabled for model generation.", b.getRegistryName(), property);
                                }
                            }
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                }
            }
        });
    }

}
