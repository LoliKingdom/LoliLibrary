package zone.rong.lolilib.warpdrive.mixin;

import cr0s.warpdrive.event.TooltipHandler;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nonnull;

@SuppressWarnings("all")
@Mixin(TooltipHandler.class)
public class TooltipHandlerMixin {

    /**
     * @author Rongmario
     * @reason Why the fuck?
     */
    @SideOnly(Side.CLIENT)
    @Overwrite(remap = false)
    public void onTooltipEvent_first(@Nonnull ItemTooltipEvent event) { } // Take off @SubscribeEvent

    /**
     * @author Rongmario
     * @reason Why the fuck?
     */
    @SideOnly(Side.CLIENT)
    @Overwrite(remap = false)
    public void onTooltipEvent_last(@Nonnull ItemTooltipEvent event) { } // Take off @SubscribeEvent

}
