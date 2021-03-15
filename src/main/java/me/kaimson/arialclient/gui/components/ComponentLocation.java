package me.kaimson.arialclient.gui.components;

import me.kaimson.arialclient.features.Feature;
import me.kaimson.arialclient.features.renderer.RenderManager;
import me.kaimson.arialclient.gui.GuiUtils;
import me.kaimson.arialclient.utils.BoxUtils;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;
import java.util.function.Consumer;

public class ComponentLocation extends ComponentButton {

    private final Feature feature;

    public ComponentLocation(Feature feature, Consumer<ComponentButton> consumer) {
        super(0, 0, 0, 0, "", consumer);
        this.feature = feature;
    }

    @Override
    public void render(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
        int x = (int) feature.getX();
        int y = (int) feature.getY();
        float scale = feature.getScale();
        int boxX = BoxUtils.getBoxOffX(feature, (int)(x / scale), feature.getWidth());
        int boxY = BoxUtils.getBoxOffY(feature, (int)(y / scale), feature.getHeight());
        int boxX2 = boxX + feature.getWidth();
        int boxY2 = boxY + feature.getHeight();
        hovered = mouseX >= boxX * scale && mouseX <= boxX2 * scale && mouseY >= boxY * scale && mouseY <= boxY2 * scale;
        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, 1.0F);
        GuiUtils.drawRect(boxX, boxY, boxX2, boxY2, hovered ? new Color(255, 100, 0, 150).getRGB() : new Color(255, 100, 0, 100).getRGB());
        GuiUtils.drawRectOutline(boxX, boxY, boxX2, boxY2, new Color(255, 100, 0, 150).getRGB());
        GlStateManager.scale(Math.pow(scale, -1), Math.pow(scale, -1), 1.0F);
        GlStateManager.popMatrix();
        RenderManager.INSTANCE.render(feature);
    }

    @Override
    protected void renderText(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {}
}
