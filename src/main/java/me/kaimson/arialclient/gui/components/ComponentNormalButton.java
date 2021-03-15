package me.kaimson.arialclient.gui.components;

import java.util.function.Consumer;

public class ComponentNormalButton extends ComponentButton {

    public ComponentNormalButton(int x, int y, int width, int height, String text, Consumer<ComponentButton> consumer) {
        super(x, y, width, height, text, consumer);
    }

    @Override
    public void render(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
        super.render(xOffset, yOffset, mouseX, mouseY, partialTicks);
    }
}
