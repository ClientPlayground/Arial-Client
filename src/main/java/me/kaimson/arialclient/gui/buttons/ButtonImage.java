package me.kaimson.arialclient.gui.buttons;

import me.kaimson.arialclient.Client;
import me.kaimson.arialclient.gui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class ButtonImage extends GuiButton {

    private final ResourceLocation ICON;

    public ButtonImage(int buttonId, int x, int y, int widthIn, int heightIn, String textureName) {
        super(buttonId, x, y, widthIn, heightIn, "");
        ICON = new ResourceLocation(Client.resourceLocation + "/" + textureName + ".png");
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (visible) {
            hovered = mouseX >= xPosition && mouseX <= xPosition + width && mouseY >= yPosition && mouseY <= yPosition + height;
            GuiUtils.drawRoundedRect(xPosition, yPosition, xPosition + width, yPosition + height, 2.0F, hovered ? new Color(255, 100, 0, 200).getRGB() : new Color(255, 100, 0, 100).getRGB());
            GuiUtils.drawRoundedOutline(xPosition, yPosition, xPosition + width, yPosition + height, 2.0F, 2.0F, new Color(255, 100, 0, 200).getRGB());
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            mc.getTextureManager().bindTexture(ICON);
            int b = 14;
            GuiUtils.drawModalRectWithCustomSizedTexture(xPosition, yPosition, b, b, b, b, b, b);
        }
    }
}
