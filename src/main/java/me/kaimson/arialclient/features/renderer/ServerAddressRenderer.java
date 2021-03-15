package me.kaimson.arialclient.features.renderer;

import me.kaimson.arialclient.features.Feature;
import me.kaimson.arialclient.gui.ui.GuiHudEditor;
import net.minecraft.client.Minecraft;

public class ServerAddressRenderer extends DefaultModuleRender {

    @Override
    public void render(Feature feature, float x, float y) {
        String text = Minecraft.getMinecraft().getCurrentServerData() != null ? Minecraft.getMinecraft().getCurrentServerData().serverIP :
                Minecraft.getMinecraft().currentScreen instanceof GuiHudEditor ? "hypixel.net" : "";
        if (!text.isEmpty())
            render(feature, feature.getFormat(text, false), x, y);
    }
}
