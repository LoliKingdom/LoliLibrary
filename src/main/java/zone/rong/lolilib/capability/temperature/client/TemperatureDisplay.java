package zone.rong.lolilib.capability.temperature.client;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber
public class TemperatureDisplay {

    public static final ResourceLocation COLD_VIGNETTE = new ResourceLocation("tfcambiental:textures/gui/cold_vignette.png");
    public static final ResourceLocation HOT_VIGNETTE = new ResourceLocation("tfcambiental:textures/gui/hot_vignette.png");
    public static final ResourceLocation MINUS = new ResourceLocation("tfcambiental:textures/gui/lower.png");
    public static final ResourceLocation PLUS = new ResourceLocation("tfcambiental:textures/gui/higher.png");
    public static final ResourceLocation MINUSER = new ResourceLocation("tfcambiental:textures/gui/lowerer.png");
    public static final ResourceLocation PLUSER = new ResourceLocation("tfcambiental:textures/gui/higherer.png");

    @SubscribeEvent
    public void render(RenderGameOverlayEvent.Pre event) {

    }

}
