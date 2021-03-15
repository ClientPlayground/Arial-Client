package me.kaimson.arialclient.gui.components.settings;

import me.kaimson.arialclient.config.Config;
import me.kaimson.arialclient.features.Feature;
import me.kaimson.arialclient.gui.GuiUtils;
import me.kaimson.arialclient.gui.components.ComponentButton;

import java.awt.*;
import java.util.Arrays;
import java.util.function.Consumer;

public class ComponentMode extends ComponentSetting {

    private final ChangeComponent prev;
    private final ChangeComponent next;

    private int currentMode;
    private final int maxMode;

    public ComponentMode(int x, int y, int width, int height, Feature setting) {
        super(x, y, width, height, setting);
        this.currentMode = (int) Config.INSTANCE.getCustoms().getOrDefault(setting, 0);
        this.maxMode = (int) Arrays.stream(Feature.values()).filter(s -> s != setting && s.name().startsWith(setting.name())).count();
        this.prev = new ChangeComponent(x + mc.fontRendererObj.getStringWidth(setting.getTranslation()) + 2, y, 10, 10, "<", (button) -> {
            if (button.enabled) {
                currentMode--;
                lock();
                Config.INSTANCE.getCustoms().put(setting, currentMode);
            }
        });
        this.next = new ChangeComponent(x + width - 10, y, 10, 10, ">", (button) -> {
           if (button.enabled) {
               currentMode++;
               lock();
               Config.INSTANCE.getCustoms().put(setting, currentMode);
           }
        });
        lock();
    }

    @Override
    public void render(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
        GuiUtils.drawString(setting.getTranslation(), x + xOffset, y + yOffset + (height - 8) / 2);
        GuiUtils.drawCenteredString(setting.getSettingMode().modes()[currentMode].getTranslation(), x + xOffset + width / 2 + mc.fontRendererObj.getStringWidth(setting.getTranslation()) / 2, y + yOffset + (height - 8) / 2);
        this.prev.render(xOffset, yOffset, mouseX, mouseY, partialTicks);
        this.next.render(xOffset, yOffset, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        prev.mouseClicked(mouseX, mouseY, mouseButton);
        next.mouseClicked(mouseX, mouseY, mouseButton);
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    private void lock() {
        prev.enabled = true;
        next.enabled = true;
        prev.enabled = currentMode > 0;
        next.enabled = currentMode + 1 < maxMode;
    }

    private static class ChangeComponent extends ComponentButton {

        public ChangeComponent(int x, int y, int width, int height, String text, Consumer<ComponentButton> consumer) {
            super(x, y, width, height, text, consumer, true);
        }

        @Override
        public void render(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
            super.render(xOffset, yOffset, mouseX, mouseY, partialTicks);
        }

        @Override
        protected void renderText(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
            super.renderText(xOffset, yOffset, mouseX, mouseY, partialTicks);
        }

        @Override
        protected void renderBackground(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
            GuiUtils.drawRoundedRect(x + xOffset, y + yOffset, x + xOffset + width, y + yOffset + height, 2.0F, new Color(255, 120, 0, 150).getRGB());
        }

    }

}
