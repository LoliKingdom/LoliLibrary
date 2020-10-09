package zone.rong.lolilib.astralsorcery.mixin;

import hellfirepvp.astralsorcery.common.CommonProxy;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CommonProxy.class)
public class CommonProxyMixin {

    @Redirect(method = "preInit", at = @At(value = "INVOKE", target = "Lhellfirepvp/astralsorcery/common/util/LootTableUtil;initLootTable()V", remap = false), remap = false)
    private void stopLootTableInitialization() { }

    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/eventhandler/EventBus;register(Ljava/lang/Object;)V", ordinal = 9, remap = false), remap = false)
    private void stopLootTableListenerRegistration(EventBus eventBus, Object target) { }

}
