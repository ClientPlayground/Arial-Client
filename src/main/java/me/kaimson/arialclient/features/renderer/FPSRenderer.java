package me.kaimson.arialclient.features.renderer;

import me.kaimson.arialclient.features.Feature;
import net.minecraft.client.Minecraft;

public class FPSRenderer extends DefaultModuleRender {

    @Override
    public void render(Feature feature, float x, float y) {
        render(feature, feature.getFormat(Minecraft.getDebugFPS()), x, y);
    }
}
