package zone.rong.lolilib.vanilla.mixin.optimizations;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockStateContainer.StateImplementation.class)
public class StateImplementationMixin {

    @Shadow @Final private ImmutableMap<IProperty<?>, Comparable<?>> properties;

    private int hashCode; // Cache

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = this.properties.hashCode();
        }
        return result;
    }
}
