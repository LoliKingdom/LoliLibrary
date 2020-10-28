package zone.rong.lolilib.forge.mixin;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.IRegistryDelegate;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.Locale;

@Mixin(IForgeRegistryEntry.Impl.class)
public abstract class IForgeRegistryEntryImplMixin<T extends IForgeRegistryEntry<T>> implements IForgeRegistryEntry<T> {

    @Shadow private ResourceLocation registryName;

    @Shadow @Nullable public abstract ResourceLocation getRegistryName();

    @Shadow @Final public IRegistryDelegate<T> delegate;

    /**
     * @author Rongmario
     * @reason Simplify and isolate sanity checks to only setRegistryName(String)
     */
    @Overwrite(remap = false)
    public final T setRegistryName(String name) {
        if (getRegistryName() == null) {
            /*
            int index = name.lastIndexOf(':');
            String oldPrefix = index == -1 ? "" : name.substring(0, index).toLowerCase(Locale.ROOT);
            name = index == -1 ? name : name.substring(index + 1);
            ModContainer container = Loader.instance().activeModContainer();
            String newPrefix = container == null || container instanceof InjectedModContainer && ((InjectedModContainer) container).wrappedContainer instanceof FMLContainer ? "minecraft" : container.getModId().toLowerCase(Locale.ROOT);
            this.registryName = new ResourceLocation(!oldPrefix.isEmpty() && !oldPrefix.equals(newPrefix) ? newPrefix : oldPrefix, name);
             */
            this.registryName = GameData.checkPrefix(name, true);
            return (T) this;
        }
        throw new IllegalStateException("Attempted to set registry name with existing registry name! New: " + name + " Old: " + this.delegate.name());
    }

    /**
     * @author Rongmario
     * @reason Simplify
     */
    @Overwrite(remap = false)
    public final T setRegistryName(ResourceLocation name) {
        if (getRegistryName() == null) {
            this.registryName = name;
            return (T) this;
        }
        throw new IllegalStateException("Attempted to set registry name with existing registry name! New: " + name + " Old: " + this.delegate.name());
    }

    /**
     * @author Rongmario
     * @reason Simplify
     */
    @Overwrite(remap = false)
    public final T setRegistryName(String modId, String name) {
        if (getRegistryName() == null) {
            this.registryName = new ResourceLocation(modId, name);
            return (T) this;
        }
        throw new IllegalStateException("Attempted to set registry name with existing registry name! New: " + name + " Old: " + this.delegate.name());
    }

}
