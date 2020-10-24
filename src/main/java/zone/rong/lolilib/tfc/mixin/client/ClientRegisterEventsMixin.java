package zone.rong.lolilib.tfc.mixin.client;

import net.dries007.tfc.client.ClientRegisterEvents;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(ClientRegisterEvents.class)
public class ClientRegisterEventsMixin {

    @Redirect(method = "registerModels", remap = false,
            slice = @Slice(
                    from = @At(value = "FIELD", opcode = Opcodes.GETSTATIC, target = "Lnet/dries007/tfc/objects/blocks/BlocksTFC;PIT_KILN:Lnet/dries007/tfc/objects/blocks/devices/BlockPitKiln;", ordinal = 0, remap = false),
                    to = @At(value = "FIELD", opcode = Opcodes.GETSTATIC, target = "Lnet/dries007/tfc/objects/blocks/BlocksTFC;INGOT_PILE:Lnet/dries007/tfc/objects/blocks/metal/BlockIngotPile;", ordinal = 0)),
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/model/ModelLoader;setCustomStateMapper(Lnet/minecraft/block/Block;Lnet/minecraft/client/renderer/block/statemap/IStateMapper;)V", ordinal = 1, remap = false))
    private static void removeFlatItemMapper(Block block, IStateMapper mapper) {
        // NO-OP
    }

    @Redirect(method = "registerModels", remap = false,
            slice = @Slice(
                    from = @At(value = "FIELD", opcode = Opcodes.GETSTATIC, target = "Lnet/dries007/tfc/objects/blocks/BlocksTFC;PLACED_ITEM_FLAT:Lnet/dries007/tfc/objects/blocks/BlockPlacedItemFlat;", ordinal = 0, remap = false),
                    to = @At(value = "FIELD", opcode = Opcodes.GETSTATIC, target = "Lnet/dries007/tfc/objects/blocks/BlocksTFC;PLACED_ITEM:Lnet/dries007/tfc/objects/blocks/BlockPlacedItem;", ordinal = 0)),
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/model/ModelLoader;setCustomStateMapper(Lnet/minecraft/block/Block;Lnet/minecraft/client/renderer/block/statemap/IStateMapper;)V", ordinal = 1, remap = false))
    private static void removeIngotPileMapper(Block block, IStateMapper mapper) {
        // NO-OP
    }

    @Redirect(method = "registerModels", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/client/registry/ClientRegistry;bindTileEntitySpecialRenderer(Ljava/lang/Class;Lnet/minecraft/client/renderer/tileentity/TileEntitySpecialRenderer;)V", ordinal = 3, remap = false), remap = false)
    private static void removeFlatItemTESR(Class<TileEntity> tileEntityClass, TileEntitySpecialRenderer<? super TileEntity> specialRenderer) {
        // NO-OP
    }

    @Redirect(method = "registerModels", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/client/registry/ClientRegistry;bindTileEntitySpecialRenderer(Ljava/lang/Class;Lnet/minecraft/client/renderer/tileentity/TileEntitySpecialRenderer;)V", ordinal = 6, remap = false), remap = false)
    private static void removeIngotPileTESR(Class<TileEntity> tileEntityClass, TileEntitySpecialRenderer<? super TileEntity> specialRenderer) {
        // NO-OP
    }

    /*
    @Redirect(method = "registerModels",
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap;of(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableMap;", ordinal = 1, remap = false),
                    to = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap;of(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableMap;", ordinal = 2, remap = false)),
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/model/ModelLoader;setCustomStateMapper(Lnet/minecraft/block/Block;Lnet/minecraft/client/renderer/block/statemap/IStateMapper;)V", remap = false))
    private static void removeIngotPileMapper(Block block, IStateMapper mapper) {
        // NO-OP
    }
     */

}
