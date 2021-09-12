package zone.rong.lolilib.baubles;

import baubles.api.IBauble;
import baubles.api.cap.BaublesCapabilities;
import baubles.client.ClientProxy;
import baubles.common.items.ItemRing;
import baubles.common.network.PacketHandler;
import baubles.common.network.PacketOpenBaublesInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BaublesClientEventHandler {

    @SubscribeEvent
    public void registerItemModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(ItemRing.RING, 0, new ModelResourceLocation("baubles:ring", "inventory"));
    }

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START && Minecraft.getMinecraft().inGameHasFocus && ClientProxy.KEY_BAUBLES.isPressed()) {
            PacketHandler.INSTANCE.sendToServer(new PacketOpenBaublesInventory());
        }
    }

    @SubscribeEvent
    public void tooltipEvent(ItemTooltipEvent event) {
        IBauble bauble = event.getItemStack().getCapability(BaublesCapabilities.CAPABILITY_ITEM_BAUBLE, null);
        if (bauble != null) {
            event.getToolTip().add(TextFormatting.GOLD + I18n.format("name." + bauble.getBaubleType(event.getItemStack())));
        }
    }

}
