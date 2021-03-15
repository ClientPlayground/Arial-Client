package me.kaimson.arialclient.features.renderer;

import me.kaimson.arialclient.features.Feature;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.BlockPos;

public class CoordinatesRenderer extends IModuleRender {

    @Override
    public void render(Feature feature, float x, float y) {
        BlockPos pos = mc.thePlayer.getPosition();
        String text = "X: " + pos.getX() + "\nY: " + pos.getY() + "\nZ: " + pos.getZ();
        float scale = feature.getScale();
        x /= scale;
        y /= scale;
        int width = 0;
        for (String s : text.split("\n"))
            width = Math.max(width, mc.fontRendererObj.getStringWidth(s));
        int height = mc.fontRendererObj.FONT_HEIGHT * 3;
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
        drawString(text, coordX, coordY);
        GlStateManager.scale(Math.pow(scale, -1), Math.pow(scale, -1), 1.0F);
        GlStateManager.popMatrix();
    }

    private void drawString(String text, float x, float y) {
        int i = 0;
        for (String s : text.split("\n")) {
            mc.fontRendererObj.drawString(s, x, y + i * mc.fontRendererObj.FONT_HEIGHT, 0xFFFFFF, false);
            i++;
        }
    }

}
