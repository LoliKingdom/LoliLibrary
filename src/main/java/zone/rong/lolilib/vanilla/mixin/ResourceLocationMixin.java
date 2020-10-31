package zone.rong.lolilib.vanilla.mixin;

import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import zone.rong.lolilib.util.StringPool;

import java.util.Locale;

@Mixin(ResourceLocation.class)
public class ResourceLocationMixin {

    @Redirect(method = "<init>(I[Ljava/lang/String;)V", at = @At(value = "INVOKE", target = "Ljava/lang/String;toLowerCase(Ljava/util/Locale;)Ljava/lang/String;", ordinal = 0, remap = false))
    private String internDomain(String s, Locale locale) {
        return StringPool.POOL.getCanonicalString(s.toLowerCase(locale));
    }

    @Redirect(method = "<init>(I[Ljava/lang/String;)V", at = @At(value = "INVOKE", target = "Ljava/lang/String;toLowerCase(Ljava/util/Locale;)Ljava/lang/String;", ordinal = 1, remap = false))
    private String internPath(String s, Locale locale) {
        return StringPool.POOL.getCanonicalString(s.toLowerCase(locale));
    }

}
