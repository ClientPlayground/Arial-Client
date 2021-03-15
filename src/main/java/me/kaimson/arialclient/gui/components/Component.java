package me.kaimson.arialclient.gui.components;

import lombok.RequiredArgsConstructor;
import net.minecraft.client.Minecraft;

@RequiredArgsConstructor
public abstract class Component {

    public final int x;
    public final int y;
    public final int width;
    public final int height;
    public final boolean shouldScissor;

    public boolean hovered;
    public boolean enabled;

    protected final Minecraft mc = Minecraft.getMinecraft();

    public Component(int x, int y, int width, int height) {
        this(x, y, width, height, false);
    }

    public void render(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
        hovered = isHovered(xOffset, yOffset, mouseX, mouseY);
    }

    private boolean isHovered(int xOffset, int yOffset, int mouseX, int mouseY) {
        return mouseX >= x + xOffset && mouseX <= x + xOffset + width && mouseY >= y + yOffset && mouseY <= y + yOffset + height;
    }

    protected void mouseDragged(int x, int y, int mouseX, int mouseY) {}

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        return hovered;
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {}

}
