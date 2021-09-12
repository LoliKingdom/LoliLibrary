package zone.rong.lolilib;

import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = LoliLib.MOD_ID, value = Side.CLIENT)
public class LoliLibClientEvents {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRenderTooltipColour(RenderTooltipEvent.Color event) {
        event.setBackground(LoliLibConfig.backgroundTooltipColour.get());
        event.setBorderStart(LoliLibConfig.startTooltipColour.get());
        event.setBorderEnd(LoliLibConfig.endTooltipColour.get());
    }

}
