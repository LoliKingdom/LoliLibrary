package zone.rong.lolilib.baubles.mixin;

import baubles.api.cap.BaublesContainer;
import baubles.api.cap.BaublesContainerProvider;
import baubles.common.event.EventHandlerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EventHandlerEntity.class)
public class EventHandlerEntityMixin {

    @Unique private static final ResourceLocation KEY = new ResourceLocation("baubles", "container");

    /**
     * @author Rongmario
     * @reason Object Hell
     */
    @SubscribeEvent
    @Overwrite(remap = false)
    public void attachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(KEY, new BaublesContainerProvider(new BaublesContainer()));
        }
    }
}
