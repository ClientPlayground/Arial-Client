package me.kaimson.arialclient.features.renderer.keystrokes;

import me.kaimson.arialclient.gui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class KeyUtils {
    private final Minecraft mc = Minecraft.getMinecraft();

    private final KeystrokesRenderer keystrokes;

    public static KeyUtils instance;

    public KeyUtils(KeystrokesRenderer keystrokes) {
        this.keystrokes = keystrokes;
        instance = this;
    }

    public void drawKeyText(String text, double width, double height, boolean pressed) {
        GuiUtils.drawString(text, (int)(width - mc.fontRendererObj.getStringWidth(text)) / 2 + 1, (int)(height - mc.fontRendererObj.FONT_HEIGHT) / 2 + 1, keystrokes.getColor((width - mc.fontRendererObj.getStringWidth(text)) / 2 + 1, pressed));
    }

    public void drawSpacebar(double width, double height, boolean pressed) {
        drawColoredRect(width * 0.25, height / 2.0 - 1.0, width * 0.75, height / 2.0 + 1.0, pressed);
    }

    private void drawColoredRect(double x1, double y1, double x2, double y2, boolean invertColor)
    {
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glBegin(GL11.GL_QUADS);
        GuiUtils.setGlColor(keystrokes.getColor(x1, invertColor));
        GL11.glVertex3d(x1, y2, 0.0);
        GuiUtils.setGlColor(keystrokes.getColor(x2, invertColor));
        GL11.glVertex3d(x2, y2, 0.0);
        GL11.glVertex3d(x2, y1, 0.0);
        GuiUtils.setGlColor(keystrokes.getColor(x1, invertColor));
        GL11.glVertex3d(x1, y1, 0.0);
        GL11.glEnd();
        GlStateManager.enableTexture2D();
        GuiUtils.setGlColor(new Color(255, 255, 255, 255).getRGB());
        GlStateManager.popMatrix();
    }
}
