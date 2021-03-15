package me.kaimson.arialclient.gui.components.settings;

import me.kaimson.arialclient.config.Config;
import me.kaimson.arialclient.features.Feature;
import me.kaimson.arialclient.gui.GuiUtils;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ComponentCheckbox extends ComponentSetting {

    public ComponentCheckbox(int x, int y, int width, int height, Feature setting) {
        super(x, y, width, height, setting);
    }

    @Override
    public void render(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
        super.render(xOffset, yOffset, mouseX, mouseY, partialTicks);
        GuiUtils.drawRoundedRect(x + xOffset, y + yOffset, x + xOffset + height, y + yOffset + height, 2.0F, new Color(255, 120, 0, 100).getRGB());
        GuiUtils.drawRoundedOutline(x + xOffset, y + yOffset, x + xOffset + height, y + yOffset + height, 2.0F, 2.0F, new Color(255, 120, 0, 150).getRGB());
        // Check mark
        if (setting.isEnabled()) {
            GlStateManager.disableTexture2D();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glLineWidth(3.0F);
            GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex2i(x + xOffset + height / 3, y + yOffset + height / 2);
            GL11.glVertex2i(x + xOffset + height / 2, y + yOffset + height * 3 / 4);
            GL11.glVertex2i(x + xOffset + height / 2, y + yOffset + height * 3 / 4);
            GL11.glVertex2i(x + xOffset + height - 1, y + yOffset + 3);
            GL11.glEnd();
            GlStateManager.enableTexture2D();
        }
        render(xOffset + height + 4, yOffset + 1.5F);
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (super.mouseClicked(mouseX, mouseY, mouseButton))
            Config.INSTANCE.setEnabled(setting, !setting.isEnabled());
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
