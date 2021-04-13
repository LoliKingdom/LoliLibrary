package zone.rong.lolilib.vanilla.mixin.texture;

import net.minecraft.client.renderer.texture.Stitcher;
import org.spongepowered.asm.mixin.Mixin;

import java.io.Serializable;

@Mixin(Stitcher.Slot.class)
public class StitcherSlotMixin implements Serializable {
}
