package zone.rong.lolilib.forge.mixin;

import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import org.spongepowered.asm.mixin.*;
import zone.rong.lolilib.forge.IOreDictionary;
import zone.rong.lolilib.util.VanillaUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mixin(OreDictionary.class)
public class OreDictionaryMixin implements IOreDictionary {

    @Shadow @Mutable private static List<String> idToName = null;
    @Shadow @Mutable private static Map<String, Integer> nameToId = null;
    @Shadow @Mutable private static List<NonNullList<ItemStack>> idToStack = null;
    @Shadow @Mutable private static List<NonNullList<ItemStack>> idToStackUn = null;
    @Shadow @Mutable private static Map<Integer, List<Integer>> stackToId = null;
    @Shadow @Final @Mutable public static final NonNullList<ItemStack> EMPTY_LIST = null;

    @Unique private static final Object2ObjectMap<ItemStack, Set<String>> ITEM_TO_IDS;
    @Unique private static final Object2IntMap<String> ID_TO_INT;

    static {
        ITEM_TO_IDS = new Object2ObjectOpenCustomHashMap<>(VanillaUtils.getItemStackHashStrategy());
        ID_TO_INT = new Object2IntOpenHashMap<>();
    }

    /**
     * @author Rongmario
     * @reason While we can't stop other mods registering using a new ItemStack instance each time
     *         We can stop Forge's own.
     */
    @Overwrite
    private static void initVanillaEntries() {

    }


    /**
     * Retrieves a list of all unique ore names that are already registered.
     *
     * @return All unique ore names that are currently registered.
     *
     * @author Rongmario
     */
    @Overwrite
    public static String[] getOreNames() {
        return ITEM_TO_IDS.values().stream().flatMap(Collection::stream).toArray(String[]::new);
    }

    /**
     * @author Rongmario
     * @reason the main registerOre Implementation
     */
    @Overwrite
    private static void registerOreImpl(String name, ItemStack ore) {
        if (name.equals("Unknown") || ore.isEmpty()) {
            return;
        }
        ITEM_TO_IDS.computeIfAbsent(ore, k -> new ObjectOpenHashSet<>()).add(name);
        ID_TO_INT.putIfAbsent(name, name.hashCode()); // Compatibility sake
        MinecraftForge.EVENT_BUS.post(new OreDictionary.OreRegisterEvent(name, ore));
    }

}
