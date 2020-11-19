package zone.rong.lolilib.forestry;

import forestry.core.gui.GuiForestry;
import mezz.jei.api.gui.IAdvancedGuiHandler;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

public class ForestryAdvancedGuiHandler implements IAdvancedGuiHandler<GuiForestry> {
    public ForestryAdvancedGuiHandler() {
    }

    public Class<GuiForestry> getGuiContainerClass() {
        return GuiForestry.class;
    }

    @Nullable
    public List<Rectangle> getGuiExtraAreas(GuiForestry guiContainer) {
        return guiContainer.getExtraGuiAreas();
    }

    @Nullable
    public Object getIngredientUnderMouse(GuiForestry guiContainer, int mouseX, int mouseY) {
        return guiContainer.getFluidStackAtPosition(mouseX, mouseY);
    }
}