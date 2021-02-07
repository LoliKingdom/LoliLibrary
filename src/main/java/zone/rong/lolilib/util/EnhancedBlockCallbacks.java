package zone.rong.lolilib.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockObserver;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.registries.*;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.lang.reflect.Field;

public class EnhancedBlockCallbacks implements IForgeRegistry.AddCallback<Block>, IForgeRegistry.ClearCallback<Block>, IForgeRegistry.CreateCallback<Block>, IForgeRegistry.DummyFactory<Block> {
    public static final EnhancedBlockCallbacks INSTANCE = new EnhancedBlockCallbacks();

    private static final ResourceLocation BLOCKSTATE_TO_ID = new ResourceLocation("minecraft:blockstatetoid");
    private static final ResourceLocation BLOCK_TO_ITEM = new ResourceLocation("minecraft:blocktoitemmap");

    @Override
    @SuppressWarnings({"unchecked", "deprecation", "ConstantConditions"})
    public void onAdd(IForgeRegistryInternal<Block> owner, RegistryManager stage, int id, Block block, @Nullable Block oldBlock) {
        ObjectIntIdentityMap<IBlockState> blockstateMap = owner.getSlaveMap(BLOCKSTATE_TO_ID, ObjectIntIdentityMap.class);
        if (oldBlock != null) {
            for (IBlockState state : oldBlock.getBlockState().getValidStates()) {
                blockstateMap.remove(state);
            }
        }

        if ("minecraft:tripwire".equals(block.getRegistryName().toString())) { // Tripwire is crap so we have to special case whee!
            for (int meta = 0; meta < 15; meta++) {
                blockstateMap.put(block.getStateFromMeta(meta), id << 4 | meta);
            }
        }

        // So, due to blocks having more in-world states then metadata allows, we have to turn the map into a semi-milti-bimap.
        // We can do this however because the implementation of the map is last set wins. So we can add all states, then fix the meta bimap.
        // Multiple states -> meta. But meta to CORRECT state.

        final boolean[] usedMeta = new boolean[16]; //Hold a list of known meta from all states.
        for (IBlockState state : block.getBlockState().getValidStates())
        {
            final int meta = block.getMetaFromState(state);
            blockstateMap.put(state, id << 4 | meta); //Add ALL the things!
            usedMeta[meta] = true;
        }

        for (int meta = 0; meta < 16; meta++) {
            if (block.getClass() == BlockObserver.class) {
                continue; // Observers are bad and have non-cyclical states. So we HAVE to use the vanilla logic above.
            }
            if (usedMeta[meta]) {
                blockstateMap.put(block.getStateFromMeta(meta), id << 4 | meta); // Put the CORRECT thing!
            }
        }

        if (oldBlock != null) {
            BiMap<Block, Item> blockToItem = owner.getSlaveMap(BLOCK_TO_ITEM, BiMap.class);
            Item item = blockToItem.get(oldBlock);
            if (item != null) {
                blockToItem.forcePut(block, item);
            }
        }
    }

    @Override
    public void onClear(IForgeRegistryInternal<Block> owner, RegistryManager stage)
    {
        owner.getSlaveMap(BLOCKSTATE_TO_ID, ObjectIntIdentityMap.class).clear();
    }

    @Override
    public void onCreate(IForgeRegistryInternal<Block> owner, RegistryManager stage)
    {
        final ObjectIntIdentityMap<IBlockState> idMap = new ObjectIntIdentityMap<IBlockState>()
        {
            @Override
            @SuppressWarnings({"deprecation", "ConstantConditions"})
            public int get(IBlockState key)
            {
                int id = this.identityMap.getInt(key);
                // There are some cases where this map is queried to serialize a state that is valid,
                // But somehow not in this list, so attempt to get real metadata. Doing this here saves us 7 patches
                if (id == -1 && key != null) {
                    id = this.identityMap.getInt(key.getBlock().getStateFromMeta(key.getBlock().getMetaFromState(key)));
                }
                return id;
            }
        };
        owner.setSlaveMap(BLOCKSTATE_TO_ID, idMap);
        owner.setSlaveMap(BLOCK_TO_ITEM, HashBiMap.create());
    }

    @Override
    public Block createDummy(ResourceLocation key) {
        Block ret = new BlockDummyAir().setUnlocalizedName("air");
        forceRegistryName(ret, key);
        return ret;
    }

    // A named class so DummyBlockReplacementTest can detect if its a dummy
    private static class BlockDummyAir extends BlockAir {
        private BlockDummyAir() { }
    }

    private static Field regName;
    private static void forceRegistryName(IForgeRegistryEntry<?> entry, ResourceLocation name) {
        if (regName == null) {
            try {
                regName = IForgeRegistryEntry.Impl.class.getDeclaredField("registryName");
                regName.setAccessible(true);
            }
            catch (NoSuchFieldException | SecurityException e) {
                FMLLog.log.error("Could not get `registryName` field from IForgeRegistryEntry.Impl");
                FMLLog.log.throwing(Level.ERROR, e);
                throw new RuntimeException(e);
            }
        }
        try {
            regName.set(entry, name);
        }
        catch (IllegalArgumentException | IllegalAccessException e) {
            FMLLog.log.error("Could not set `registryName` field in IForgeRegistryEntry.Impl to `{}`", name.toString());
            FMLLog.log.throwing(Level.ERROR, e);
            throw new RuntimeException(e);
        }
    }

}
