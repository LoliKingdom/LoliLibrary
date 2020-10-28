package zone.rong.lolilib.baubles.mixin.client;

import baubles.client.ClientProxy;
import baubles.client.gui.GuiPlayerExpanded;
import baubles.common.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static baubles.client.ClientProxy.KEY_BAUBLES;

@Mixin(ClientProxy.class)
public abstract class ClientProxyMixin extends CommonProxy {

    /**
     * @author Rongmario
     * @reason Register better listeners ourselves + fix duplicate listeners registering
     */
    @Overwrite(remap = false)
    public void registerEventHandlers() {
        super.registerEventHandlers();
        ClientRegistry.registerKeyBinding(KEY_BAUBLES);
    }

    /**
     * @author Rongmario
     * @reason No need to check WorldClient
     */
    @Overwrite(remap = false)
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return ID == 0 ? new GuiPlayerExpanded(player) : null;
    }

}
