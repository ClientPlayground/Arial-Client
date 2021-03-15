package me.kaimson.arialclient.utils;

import me.kaimson.arialclient.config.Config;
import me.kaimson.arialclient.features.Feature;
import me.kaimson.arialclient.gui.GuiUtils;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;

public class Crosshair {

    public static final Crosshair INSTANCE = new Crosshair();

    public void render(int x, int y) {
        int mode = (int) Config.INSTANCE.getCustoms().getOrDefault(Feature.CROSSHAIR_MODE, 0);
        float gap = (float) Config.INSTANCE.getCustoms().getOrDefault(Feature.CROSSHAIR_RENDERGAP, 1.0F);
        float thickness = (float) Config.INSTANCE.getCustoms().getOrDefault(Feature.CROSSHAIR_THICKNESS, 1.0F);
/*        float width = (float) Config.INSTANCE.getCustoms().getOrDefault(Feature.CUSTOM_CROSSHAIR_WIDTH, 0.6F);
        float height = (float) Config.INSTANCE.getCustoms().getOrDefault(Feature.CUSTOM_CROSSHAIR_HEIGHT, 0.6F);*/
        float size = (float) Config.INSTANCE.getCustoms().getOrDefault(Feature.CROSSHAIR_SIZE, 0.6F);
        float width = size;
        float height = size;

        GlStateManager.pushMatrix();
        switch (mode) {
            // Circle
            case 0:
                GuiUtils.drawCircle(x, y, gap, thickness, new Color(getColor(x, (int)thickness), true), true);
                break;
            // Square
            case 1:
                GuiUtils.drawFilledRect((x - gap - thickness), (y - gap - thickness), (x + gap + thickness), (y - gap), getColor(x, (int)thickness), true);
                GuiUtils.drawFilledRect((x - gap - thickness), (y + gap), (x + gap + thickness), (y + gap + thickness), getColor(x, (int)thickness), true);
                GuiUtils.drawFilledRect((x - gap - thickness), (y - gap), (x - gap), (y + gap), getColor(x, (int)thickness), true);
                GuiUtils.drawFilledRect((x + gap), (y - gap), (x + gap + thickness), (y + gap), getColor(x, (int)thickness), true);
                break;
            // Cross
            case 2:
                GuiUtils.drawVerticalLine(x, y - gap - height, y - gap, thickness, getColor(x, (int)thickness), false);
                GuiUtils.drawVerticalLine(x, y + gap, y + gap + height, thickness, getColor(x, (int)thickness), false);
                GuiUtils.drawLine(x - gap - width, x - gap, y, thickness, getColor(x, (int)thickness), false);
                GuiUtils.drawLine(x + gap, x + gap + width, y, thickness, getColor(x, (int)thickness), false);
                break;
        }
        GlStateManager.popMatrix();
    }

    private int getColor(double offset, int width) {
        int color = (int) Config.INSTANCE.getCustoms().getOrDefault(Feature.CROSSHAIR_COLOR, new Color(255, 255, 255, 255).getRGB());
/*        if (Feature.CUSTOM_CROSSHAIR_RAINBOW.isEnabled())
            color = ChromaUtil.getColor(offset, width);*/
        return color;
    }

}
