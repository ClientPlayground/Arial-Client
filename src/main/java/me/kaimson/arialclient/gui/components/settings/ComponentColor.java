package me.kaimson.arialclient.gui.components.settings;

import me.kaimson.arialclient.features.Feature;

public class ComponentColor extends ComponentSetting {

    public ComponentColor(int x, int y, int width, int height, Feature setting) {
        super(x, y, width, height, setting);
    }

    @Override
    public void render(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
/*        GuiUtils.drawRoundedRect(x + xOffset, y + yOffset, x + xOffset + width, y + yOffset + height, 2.0F, new Color(255, 120, 0, 100).getRGB());*/
        render(xOffset, yOffset + 1);
    }
}
