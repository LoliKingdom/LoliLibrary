package zone.rong.lolilib.capability.world;

import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zone.rong.lolilib.LoliLib;

@Mod.EventBusSubscriber
public class BlockDataHolder implements IBlockDataHolder {

    public static final ResourceLocation KEY = new ResourceLocation(LoliLib.MOD_ID, "block_data_holder");

    @CapabilityInject(IBlockDataHolder.class)
    public static final Capability<IBlockDataHolder> INSTANCE;

    static {
        INSTANCE = null;
    }

    public static void init() {
        CapabilityManager.INSTANCE.register(IBlockDataHolder.class, new Capability.IStorage<IBlockDataHolder>() {
            @Override
            public NBTBase writeNBT(Capability<IBlockDataHolder> capability, IBlockDataHolder instance, EnumFacing side) {
                return instance.serializeNBT();
            }
            @Override
            public void readNBT(Capability<IBlockDataHolder> capability, IBlockDataHolder instance, EnumFacing side, NBTBase nbt) {
                instance.deserializeNBT((NBTTagCompound) nbt);
            }
        }, () -> null);
    }

    @SubscribeEvent
    public static void onCapabilityAttach(AttachCapabilitiesEvent<World> event) {
        event.addCapability(KEY, new BlockDataHolder());
    }

    private final Object2FloatMap<BlockPos> breakPositionMap;

    private BlockDataHolder() {
        this.breakPositionMap = new Object2FloatOpenHashMap<>();
    }

    @Override
    public boolean hasBreakProgress(BlockPos pos) {
        return this.breakPositionMap.containsKey(pos);
    }

    @Override
    public float getBreakProgress(BlockPos pos) {
        return this.breakPositionMap.get(pos);
    }

    @Override
    public boolean addBreakProgress(BlockPos pos, float progress) {
        if (this.breakPositionMap.containsKey(pos)) {
            float modifiedProgress;
            this.breakPositionMap.put(pos, modifiedProgress = Math.min(1.0F, this.breakPositionMap.get(pos) + progress));
            return modifiedProgress == 1.0F;
        }
        this.breakPositionMap.put(pos, progress);
        return progress >= 1.0F;
    }

    @Override
    public void removeBreakProgress(BlockPos pos) {
        this.breakPositionMap.removeFloat(pos);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == INSTANCE;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        return capability == INSTANCE ? (T) this : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        this.breakPositionMap.object2FloatEntrySet().forEach(e -> tag.setTag(String.valueOf(e.getKey().toLong()), new NBTTagFloat(e.getFloatValue())));
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        for (String key : nbt.getKeySet()) {
            this.breakPositionMap.put(BlockPos.fromLong(Long.parseLong(key)), ((NBTTagFloat) nbt.getTag(key)).getFloat());
        }
    }

}
