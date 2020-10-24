package zone.rong.lolilib.tfc.mixin.client;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.client.TFCKeybindings;
import net.dries007.tfc.network.PacketCycleItemMode;
import net.dries007.tfc.network.PacketOpenCraftingGui;
import net.dries007.tfc.network.PacketPlaceBlockSpecial;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.*;

@Mixin(TFCKeybindings.class)
public class TFCKeybindingsMixin {

    @Shadow @Final private static KeyBinding OPEN_CRAFTING_TABLE;
    @Shadow @Final private static KeyBinding CHANGE_ITEM_MODE;
    @Shadow @Final private static KeyBinding STACK_FOOD;

    @Mutable @Final private static KeyBinding PLACE_BLOCK;

    static {
        PLACE_BLOCK = null;
    }

    /**
     * @author Rongmario
     * @reason Stop PLACE_BLOCK from being registered
     */
    @Overwrite
    public static void init() {
        ClientRegistry.registerKeyBinding(OPEN_CRAFTING_TABLE);
        ClientRegistry.registerKeyBinding(CHANGE_ITEM_MODE);
        ClientRegistry.registerKeyBinding(STACK_FOOD);
    }

    /**
     * @author Rongmario
     * @reason Stop PLACE_BLOCK from being checked
     */
    @Overwrite
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onKeyEvent(InputEvent event) {
        if (OPEN_CRAFTING_TABLE.isPressed()) {
            TerraFirmaCraft.getNetwork().sendToServer(new PacketOpenCraftingGui());
        }
        if (CHANGE_ITEM_MODE.isPressed()) {
            TerraFirmaCraft.getNetwork().sendToServer(new PacketCycleItemMode());
        }
    }

}
