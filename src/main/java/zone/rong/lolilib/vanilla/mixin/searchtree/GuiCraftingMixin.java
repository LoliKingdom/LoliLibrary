package zone.rong.lolilib.vanilla.mixin.searchtree;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.*;

import java.io.IOException;

@Mixin(GuiCrafting.class)
public abstract class GuiCraftingMixin extends GuiContainer {

    protected GuiCraftingMixin(Container inventorySlotsIn) {
        super(inventorySlotsIn);
    }

    // TODO: remove the variable by ASM
    /*
    @Redirect(method = "<init>(Lnet/minecraft/entity/player/InventoryPlayer;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V", at = @At(value = "NEW", target = "net/minecraft/client/gui/recipebook/GuiRecipeBook"))
    private GuiRecipeBook throwANullBook() {
        return null;
    }
     */

    /**
     * @author Rongmario
     * @reason Get recipe book outta here
     */
    @Overwrite
    public void initGui() {
        super.initGui();
    }

    /**
     * @author Rongmario
     * @reason Get recipe book outta here
     */
    @Overwrite
    public void updateScreen() {
        super.updateScreen();
    }

    /**
     * @author Rongmario
     * @reason Get recipe book outta here
     */
    @Overwrite
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    /**
     * @author Rongmario
     * @reason Get recipe book outta here
     */
    @Overwrite
    protected boolean isPointInRegion(int rectX, int rectY, int rectWidth, int rectHeight, int pointX, int pointY) {
        return super.isPointInRegion(rectX, rectY, rectWidth, rectHeight, pointX, pointY);
    }

    /**
     * @author Rongmario
     * @reason Get recipe book outta here
     */
    @Overwrite
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    /**
     * @author Rongmario
     * @reason Get recipe book outta here
     */
    @Overwrite
    protected boolean hasClickedOutside(int p_193983_1_, int p_193983_2_, int p_193983_3_, int p_193983_4_) {
        return super.hasClickedOutside(p_193983_1_, p_193983_2_, p_193983_3_, p_193983_4_);
    }

    /**
     * @author Rongmario
     * @reason Get recipe book outta here
     */
    @Overwrite
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    /**
     * @author Rongmario
     * @reason Get recipe book outta here
     */
    @Overwrite
    protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type) {
        super.handleMouseClick(slotIn, slotId, mouseButton, type);
    }

    /**
     * @author Rongmario
     * @reason Get recipe book outta here
     */
    @Overwrite
    public void recipesUpdated() { }

    /**
     * @author Rongmario
     * @reason Get recipe book outta here
     */
    @Overwrite
    public void onGuiClosed() {
        super.onGuiClosed();
    }

}
