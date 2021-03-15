package me.kaimson.arialclient.gui.buttons;

import me.kaimson.arialclient.gui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ButtonClose extends GuiButton {

    public ButtonClose(int buttonId, int x, int y, int width, int height) {
        super(buttonId, x, y, width, height, "");
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (visible) {
            hovered = mouseX >= xPosition && mouseX <= xPosition + width && mouseY >= yPosition && mouseY <= yPosition + height;
            // Draw background
            GuiUtils.drawRoundedRect(xPosition, yPosition, xPosition + width, yPosition + height, 2.0F, hovered ? new Color(255, 120, 0, 200).getRGB() : new Color(255, 120, 0, 100).getRGB());
            GuiUtils.drawRoundedOutline(xPosition, yPosition, xPosition + width, yPosition + height, 2.0F, 2.0F, new Color(255, 100, 0, 200).getRGB());
            // Draw X
            GlStateManager.pushMatrix();
            GlStateManager.disableTexture2D();
            GL11.glPushMatrix();
            GuiUtils.setGlColor(new Color(255, 255, 255, 255).getRGB());
            GL11.glLineWidth(3.0F);
            GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex2i(xPosition + 2, yPosition + 2);
            GL11.glVertex2i(xPosition + width - 2, yPosition + height - 2);
            GL11.glEnd();
            GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex2i(xPosition + width - 2, yPosition + 2);
            GL11.glVertex2i(xPosition + 2, yPosition + height - 2);
            GL11.glEnd();
            GL11.glPopMatrix();
            GlStateManager.enableTexture2D();
            GlStateManager.popMatrix();
        }
    }
}
