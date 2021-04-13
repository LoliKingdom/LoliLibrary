package zone.rong.lolilib.vanilla.mixin;

import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serializable;

@Mixin(ResourceLocation.class)
public class ResourceLocationMixin implements Serializable { }
