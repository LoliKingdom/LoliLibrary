package zone.rong.lolilib.vanilla.mixin.texture;

import net.minecraft.client.renderer.texture.Stitcher;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import zone.rong.lolilib.vanilla.MissingSpriteGetter;

import java.io.Serializable;
import java.util.Set;

@Mixin(Stitcher.class)
public class StitcherMixin implements Serializable, MissingSpriteGetter {

    @Shadow @Final private Set<Stitcher.Holder> setStitchHolders;

    @Override
    public TextureAtlasSprite getMissingSprite() {
        return this.setStitchHolders.parallelStream().filter(h -> h.getAtlasSprite().getIconName().equals("missingno")).findFirst().get().getAtlasSprite();
    }
}
