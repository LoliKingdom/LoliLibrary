package zone.rong.lolilib;

import baubles.api.IBauble;
import baubles.api.cap.BaublesCapabilities;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = LoliLib.MOD_ID)
public class LoliLibEvents {

    /**
     * For the following mods:
     * - Thaumcraft
     */
    @SubscribeEvent
    public static void tooltipEvent(ItemTooltipEvent event) {
        IBauble bauble;
        ItemStack stack = event.getItemStack();
        if ((bauble = stack.getCapability(BaublesCapabilities.CAPABILITY_ITEM_BAUBLE, null)) != null) {
            event.getToolTip().add(TextFormatting.GOLD + I18n.format("name." + bauble.getBaubleType(stack)));
        }
    }

}
