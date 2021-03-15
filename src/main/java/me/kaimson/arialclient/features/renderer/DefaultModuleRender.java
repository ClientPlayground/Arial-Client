package me.kaimson.arialclient.features.renderer;

import me.kaimson.arialclient.features.Feature;
import me.kaimson.arialclient.gui.GuiUtils;
import net.minecraft.client.renderer.GlStateManager;

public class DefaultModuleRender extends IModuleRender {

    private String text;

    @Override
    public void render(Feature feature, float x, float y) {
        render(feature, feature.getTranslation(), x, y);
    }

    protected void render(Feature feature, String text, float x, float y) {
        float scale = feature.getScale();
        x /= scale;
        y /= scale;
        int width = mc.fontRendererObj.getStringWidth(text);
        int height = mc.fontRendererObj.FONT_HEIGHT;
        feature.setWidth(width);
        feature.setHeight(height);

        float coordX = x;
        float coordY = y;
        switch (feature.getAnchorPoint()) {
            case TOP_LEFT:
                break;
            case TOP_CENTER:
                coordX = x - width / 2;
                break;
            case TOP_RIGHT:
                coordX = x - width;
                break;
            case CENTER_LEFT:
                coordY = y - height / 2;
                break;
            case CENTER:
                coordX = x - width / 2;
                coordY = y - height / 2;
                break;
            case CENTER_RIGHT:
                coordX = x - width;
                coordY = y - height / 2;
                break;
            case BOTTOM_LEFT:
                coordY = y - height;
                break;
            case BOTTOM_CENTER:
                coordX = x - width / 2;
                coordY = y - height;
                break;
            case BOTTOM_RIGHT:
                coordX = x - width;
                coordY = y - height;
                break;
        }
        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, 1.0F);
        GuiUtils.drawString(text, coordX, coordY);
        GlStateManager.scale(Math.pow(scale, -1), Math.pow(scale, -1), 1.0F);
        GlStateManager.popMatrix();
    }

}
