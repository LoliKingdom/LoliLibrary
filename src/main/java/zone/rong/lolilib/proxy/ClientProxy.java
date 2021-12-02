package zone.rong.lolilib.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.tutorial.TutorialSteps;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void start(FMLLoadCompleteEvent event) {
        Minecraft.getMinecraft().getTutorial().setStep(TutorialSteps.NONE);
    }
}
