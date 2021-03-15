package me.kaimson.arialclient.gui.components;

import me.kaimson.arialclient.gui.GuiUtils;

import java.awt.*;
import java.util.function.Consumer;

public abstract class ComponentButton extends ComponentText {

    private final Consumer<ComponentButton> consumer;

    public ComponentButton(int x, int y, int width, int height, String text, Consumer<ComponentButton> consumer) {
        this(x, y, width, height, text, consumer, false);
    }

    public ComponentButton(int x, int y, int width, int height, String text, Consumer<ComponentButton> consumer, boolean shouldScissor) {
        super(x, y, width, height, text, shouldScissor);
        this.consumer = consumer;
    }

    @Override
    public void render(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
        renderBackground(xOffset, yOffset, mouseX, mouseY, partialTicks);
        super.render(xOffset, yOffset, mouseX, mouseY, partialTicks);
    }

    protected void renderBackground(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
        GuiUtils.drawRoundedRect(x, y, x + width, y + height, 2.0F, enabled ? new Color(0, 0, 0, 75).getRGB() : new Color(0, 0, 0, 50).getRGB());
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (super.mouseClicked(mouseX, mouseY, mouseButton))
            consumer.accept(this);
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
