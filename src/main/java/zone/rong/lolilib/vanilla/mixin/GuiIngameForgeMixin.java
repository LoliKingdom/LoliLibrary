package zone.rong.lolilib.vanilla.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.AIR;

@Mixin(GuiIngameForge.class)
public abstract class GuiIngameForgeMixin extends GuiIngame {

    public GuiIngameForgeMixin(Minecraft mcIn) {
        super(mcIn);
    }

    @Shadow(remap = false) protected abstract boolean pre(RenderGameOverlayEvent.ElementType type);

    @Shadow(remap = false) protected abstract void post(RenderGameOverlayEvent.ElementType type);

    @Shadow(remap = false) public static int right_height;

    /**
     * @author Rongmario
     */
    @Overwrite(remap = false)
    protected void renderAir(int width, int height) {
        if (pre(AIR)) {
            return;
        }
        mc.mcProfiler.startSection("air");
        EntityPlayer player = (EntityPlayer) this.mc.getRenderViewEntity();
        GlStateManager.enableBlend();
        int left = width / 2 + 91;
        int top = height - right_height;
        int air = player.getAir();
        if (air < 300) {
            int full = MathHelper.ceil((double) (air - 2) * 10.0D / 300.0D);
            int partial = MathHelper.ceil((double) air * 10.0D / 300.0D) - full;
            for (int i = 0; i < full + partial; ++i) {
                drawTexturedModalRect(left - i * 8 - 9, top, (i < full ? 16 : 25), 18, 9, 9);
            }
        }
        right_height += 10;
        GlStateManager.disableBlend();
        mc.mcProfiler.endSection();
        post(AIR);
    }

}
