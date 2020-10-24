package zone.rong.lolilib.tfc.mixin;

import net.dries007.tfc.TerraFirmaCraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TerraFirmaCraft.class)
public class TerraFirmaCraftMixin {

    @Redirect(method = "preInit", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/network/simpleimpl/SimpleNetworkWrapper;registerMessage(Lnet/minecraftforge/fml/common/network/simpleimpl/IMessageHandler;Ljava/lang/Class;ILnet/minecraftforge/fml/relauncher/Side;)V", ordinal = 1, remap = false), remap = false)
    private void stopPacketPlaceBlockSpecialPacketRegistering(SimpleNetworkWrapper simpleNetworkWrapper, IMessageHandler<? super IMessage, ? extends IMessage> messageHandler, Class<IMessage> requestMessageType, int discriminator, Side side) {
        // NO-OP
    }

}
