package me.kaimson.arialclient.gui.components;

import me.kaimson.arialclient.gui.GuiUtils;

public abstract class ComponentText extends Component {

    protected final String text;

    public ComponentText(int x, int y, int width, int height, String text) {
        this(x, y, width, height, text, false);
    }

    public ComponentText(int x, int y, int width, int height, String text, boolean shouldScissor) {
        super(x, y, width, height, shouldScissor);
        this.text = text;
    }

    @Override
    public void render(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
        super.render(xOffset, yOffset, mouseX, mouseY, partialTicks);
        renderText(xOffset, yOffset, mouseX, mouseY, partialTicks);
    }

    protected void renderText(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
        GuiUtils.drawCenteredString(text, x + width / 2, y + (height - 8) / 2, enabled ? 0xFFFFFF : 10526880, true);
    }
}
