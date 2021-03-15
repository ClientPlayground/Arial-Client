package me.kaimson.arialclient.features.renderer;

import me.kaimson.arialclient.features.Feature;
import net.minecraft.client.Minecraft;

public class PingRenderer extends DefaultModuleRender {

    @Override
    public void render(Feature feature, float x, float y) {
        render(feature, feature.getFormat((Minecraft.getMinecraft().getNetHandler() != null ? Minecraft.getMinecraft().getNetHandler().getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()) != null ?
                Minecraft.getMinecraft().getNetHandler().getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()).getResponseTime() : -1 : -1) + " ms", false), x, y);
    }
}
