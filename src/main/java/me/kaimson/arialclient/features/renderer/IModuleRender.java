package me.kaimson.arialclient.features.renderer;

import me.kaimson.arialclient.features.Feature;
import net.minecraft.client.Minecraft;

public abstract class IModuleRender {

    protected final Minecraft mc = Minecraft.getMinecraft();

    public abstract void render(Feature feature, float x, float y);

}
