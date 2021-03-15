package me.kaimson.arialclient.gui.components.settings;

import me.kaimson.arialclient.config.Config;
import me.kaimson.arialclient.features.Feature;
import me.kaimson.arialclient.gui.GuiUtils;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;

public class ComponentVersionCheckbox extends ComponentSetting {

    public ComponentVersionCheckbox(int x, int y, int width, int height, Feature setting) {
        super(x, y, width, height, setting);
    }

    @Override
    public void render(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
        super.render(xOffset, yOffset, mouseX, mouseY, partialTicks);
        GuiUtils.drawRoundedRect(x + xOffset, y + yOffset, x + xOffset + width, y + yOffset + height, 2.0F, new Color(255, 120, 0, 100).getRGB());
        GuiUtils.drawRoundedOutline(x + xOffset, y + yOffset, x + xOffset + width, y + yOffset + height, 2.0F, 2.0F, new Color(255, 120, 0, 150).getRGB());
        GuiUtils.drawString(setting.getTranslation(), x + xOffset - mc.fontRendererObj.getStringWidth(setting.getTranslation()) - 3, y + yOffset + 1);
        GuiUtils.drawRoundedRect(x + xOffset + (setting.isEnabled() ? 0 : width / 2), y + yOffset, x + xOffset + (setting.isEnabled() ? width / 2 : width), y + yOffset + height, 2.0F,
                setting.isEnabled() ? new Color(255, 0, 0, 100).getRGB() : new Color(0, 255, 0, 100).getRGB());
        // 1.7 / 1.8 text
        float scale = 0.75F;
        GlStateManager.scale(scale, scale, 1.0F);
        GuiUtils.drawString("1.7", (x + xOffset + 1) / scale, (y + yOffset + (height - 8) / 2) / scale + 2);
        GuiUtils.drawString("1.8", (x + xOffset + width - 2) / scale - mc.fontRendererObj.getStringWidth("1.7"), (y + yOffset + (height - 8) / 2) / scale + 2);
        GlStateManager.scale(Math.pow(scale, -1), Math.pow(scale, -1), 1.0F);
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (super.mouseClicked(mouseX, mouseY, mouseButton))
            Config.INSTANCE.setEnabled(setting, !setting.isEnabled());
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

}
