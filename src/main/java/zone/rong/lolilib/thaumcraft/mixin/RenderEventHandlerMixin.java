package zone.rong.lolilib.thaumcraft.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.client.lib.events.RenderEventHandler;

@Mixin(RenderEventHandler.class)
public class RenderEventHandlerMixin {

    @Redirect(method = "tooltipEvent(Lnet/minecraftforge/event/entity/player/ItemTooltipEvent;)V", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;isGrabbed()Z", remap = false), remap = false)
    private static boolean writeTooltipWhenStageUnlocked() {
        return !ThaumcraftCapabilities.getKnowledge(Minecraft.getMinecraft().player).isResearchKnown("!gotthaumonomicon");
    }

    @Redirect(method = "tooltipEvent(Lnet/minecraftforge/client/event/RenderTooltipEvent$PostBackground;)V", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;isGrabbed()Z", remap = false), remap = false)
    private static boolean drawTooltipWhenStageUnlocked() {
        return !ThaumcraftCapabilities.getKnowledge(Minecraft.getMinecraft().player).isResearchKnown("!gotthaumonomicon");
    }

}
