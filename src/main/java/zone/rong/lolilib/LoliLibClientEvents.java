package zone.rong.lolilib;

import baubles.client.ClientProxy;
import baubles.client.gui.GuiBaublesButton;
import baubles.client.gui.GuiPlayerExpanded;
import baubles.common.items.ItemRing;
import baubles.common.network.PacketHandler;
import baubles.common.network.PacketOpenBaublesInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = LoliLib.MOD_ID, value = Side.CLIENT)
public class LoliLibClientEvents {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerColorHandlerItems(ColorHandlerEvent.Item event) {
        // TODO: event.getItemColors().registerItemColorHandler((stack, tintIndex) -> ConfigTFC.Client.DISPLAY.rottenFoodOverlayColor, RottenFoodItem.INSTANCE);
    }

    /**
     * For the following mods:
     * - Thaumcraft
     */
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerItemModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(ItemRing.RING, 0, new ModelResourceLocation("baubles:ring", "inventory"));
    }

    /**
     * For the following mods:
     * - Thaumcraft
     */
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void handleKeybinds(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && Minecraft.getMinecraft().inGameHasFocus && ClientProxy.KEY_BAUBLES.isPressed()) {
            PacketHandler.INSTANCE.sendToServer(new PacketOpenBaublesInventory());
        }
    }

    /**
     * For the following mods:
     * - Thaumcraft
     */
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void guiPostInit(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.getGui() instanceof GuiInventory) {
            event.getButtonList().add(new GuiBaublesButton(55, (GuiContainer) event.getGui(), 64, 9, 10, 10, I18n.format("button.baubles")));
        } else if (event.getGui() instanceof GuiPlayerExpanded) {
            event.getButtonList().add(new GuiBaublesButton(55, (GuiContainer) event.getGui(), 64, 9, 10, 10, I18n.format("button.normal")));
        }
    }

}
