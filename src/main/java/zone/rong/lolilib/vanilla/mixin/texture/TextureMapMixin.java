package zone.rong.lolilib.vanilla.mixin.texture;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.ReportedException;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.common.ProgressManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.*;
import java.util.Map;
import java.util.Set;

@Mixin(TextureMap.class)
public abstract class TextureMapMixin extends AbstractTexture {

    @Shadow @Final private static Logger LOGGER;
    @Shadow @Final protected String basePath;
    @Shadow @Final protected Map<String, TextureAtlasSprite> mapRegisteredSprites;
    @Shadow @Final protected TextureAtlasSprite missingImage;

    @Shadow private int mipmapLevels;

    @Inject(method = "finishLoading", at = @At("TAIL"))
    private void afterFinishingLoading(Stitcher stitcher, ProgressManager.ProgressBar bar, int j, int k, CallbackInfo ci) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(getCache());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(stitcher);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load() {
        try {
            // Load stitcher
            FileInputStream fileInputStream = new FileInputStream(getCache());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Stitcher stitcher = (Stitcher) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            LOGGER.info("Loaded: {}x{} {}-atlas", stitcher.getCurrentWidth(), stitcher.getCurrentHeight(), this.basePath);
            TextureUtil.allocateTextureImpl(this.getGlTextureId(), this.mipmapLevels, stitcher.getCurrentWidth(), stitcher.getCurrentHeight());
            Set<TextureAtlasSprite> set = new ObjectOpenHashSet<>(this.mapRegisteredSprites.values());
            for (TextureAtlasSprite sprite : stitcher.getStichSlots()) {

                // Deal with custom sprites here before it is uploaded
                if (sprite.getClass() != TextureAtlasSprite.class) {
                    // Perhaps custom sprite - check in case
                }

                set.remove(sprite);
                try {
                    TextureUtil.uploadTextureMipmap(sprite.getFrameTextureData(0), sprite.getIconWidth(), sprite.getIconHeight(), sprite.getOriginX(), sprite.getOriginY(), false, false);
                } catch (Throwable throwable) {
                    CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Stitching texture atlas");
                    CrashReportCategory crashreportcategory = crashreport.makeCategory("Texture being stitched together");
                    crashreportcategory.addCrashSection("Atlas path", this.basePath);
                    crashreportcategory.addCrashSection("Sprite", sprite);
                    throw new ReportedException(crashreport);
                }
            }
            for (TextureAtlasSprite sprite : set) {
                sprite.copyFrom(this.missingImage);
            }
            ForgeHooksClient.onTextureStitchedPost((TextureMap) (Object) this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File getCache() {
        File cacheFolder = new File(Launch.minecraftHome, "cache");
        cacheFolder.mkdir();
        return new File(cacheFolder, "vanilla-stitcher.bin");
    }

}
