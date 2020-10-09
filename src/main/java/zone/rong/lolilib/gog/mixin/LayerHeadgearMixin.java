package zone.rong.lolilib.gog.mixin;

import gaia.init.GaiaItems;
import gaia.renderer.entity.layers.LayerHeadgear;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LayerHeadgear.class)
public class LayerHeadgearMixin implements LayerRenderer<EntityPlayer> {

    @Shadow(remap = false) private ModelRenderer bipedHead;

    @Unique private final ItemStack maidDoll = new ItemStack(GaiaItems.ACCESSORY_HEADGEAR_DOLL);

    /**
     * @author Rongmario
     */
    @Overwrite(remap = false)
    public void doRenderLayer(EntityPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GlStateManager.pushMatrix();
        if (player.isSneaking()) {
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
        }
        this.bipedHead.postRender(0.0625F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.translate(0.0F, -0.25F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(0.625F, -0.625F, -0.625F);
        Minecraft.getMinecraft().getItemRenderer().renderItem(player, maidDoll, ItemCameraTransforms.TransformType.HEAD);
        GlStateManager.popMatrix();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

    /*
    // TODO - Offset when any headwear is on...?
    @Inject(method = "doRenderLayer", at = @At("HEAD"))
    private void permaMaid(EntityPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, CallbackInfo ci) {
        GlStateManager.pushMatrix();
        if (player.isSneaking()) {
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
        }
        this.bipedHead.postRender(0.0625F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.translate(0.0F, -0.25F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(0.625F, -0.625F, -0.625F);
        Minecraft.getMinecraft().getItemRenderer().renderItem(player, maidDoll, ItemCameraTransforms.TransformType.HEAD);
        GlStateManager.popMatrix();
    }
     */

}
