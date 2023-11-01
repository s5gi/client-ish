package net.optifine.player;

import java.awt.image.BufferedImage;
import java.lang.ref.WeakReference;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.util.ResourceLocation;

public class CapeImageBuffer extends ImageBufferDownload
{
    private AbstractClientPlayer player;
    private ResourceLocation resourceLocation;
    private boolean elytraOfCape;
    public ImageBufferDownload imageBufferDownload;
    public final WeakReference<AbstractClientPlayer> playerRef;

    public CapeImageBuffer(AbstractClientPlayer player, ResourceLocation resourceLocation) {
        playerRef = new WeakReference<>(player);
        this.resourceLocation = resourceLocation;
        imageBufferDownload = new ImageBufferDownload();
    }

    @Override
    public BufferedImage parseUserSkin(BufferedImage image) {
        // ClassTransformer will remap to:
        // CapeUtils.parseCape(image);
        return parseCape(image);
    }

    private static BufferedImage parseCape(BufferedImage image) {
        return null;
    }

    @Override
    public void skinAvailable() {
        AbstractClientPlayer player = playerRef.get();
        if (player != null) {
            // ClassTransformer will remap to:
            // player.setLocationOfCape(resourceLocation);
            setLocationOfCape(player, resourceLocation);
        }
    }

    private static void setLocationOfCape(AbstractClientPlayer player, ResourceLocation resourceLocation) {
    }

    public void cleanup()
    {
        this.player = null;
    }

    public boolean isElytraOfCape()
    {
        return this.elytraOfCape;
    }
}
