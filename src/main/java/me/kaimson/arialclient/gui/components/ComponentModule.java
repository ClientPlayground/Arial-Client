package me.kaimson.arialclient.gui.components;

import me.kaimson.arialclient.Client;
import me.kaimson.arialclient.features.Feature;
import me.kaimson.arialclient.gui.GuiUtils;
import me.kaimson.arialclient.gui.ui.GuiSettings;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.function.Consumer;

public class ComponentModule extends ComponentButton {

    private final Feature feature;
    private final ResourceLocation ICON = new ResourceLocation(Client.resourceLocation + "/settings.png");

    private boolean settingsHovered;

    public ComponentModule(int x, int y, int width, int height, String text, Feature feature, Consumer<ComponentButton> consumer) {
        super(x, y, width, height, text, consumer, true);
        this.feature = feature;
    }

    @Override
    public void render(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
        super.render(xOffset, yOffset, mouseX, mouseY, partialTicks);
/*        GuiUtils.drawRoundedCorner(x, y + height - 12, x + width, y + height, 2.0F, feature.isEnabled() ? new Color(0, 255, 0, 175).getRGB() : new Color(255, 0, 0, 175).getRGB(), new int[] { 1, 1, 0, 0 });*/
        GuiUtils.drawCenteredString(feature.isEnabled() ? "Enabled" : "Disabled", x + (feature.hasSettings() ? width / 3 : width / 2), y + height - 10, feature.isEnabled() ? new Color(0, 255, 0).getRGB() : new Color(255, 0, 0).getRGB(), true);
        GuiUtils.drawRect(x, y + height - 12, x + width, y + height - 13, new Color(255, 0, 0,255).getRGB());
        GuiUtils.drawRoundedOutlineGradient(x, y, x + width, y + height, 2.0F, 2.0F, new Color(255, 100, 0, 255).getRGB(), new Color(255, 0, 0, 255).getRGB());
        if (feature.hasSettings()) {
            GuiUtils.drawRect(x + width - width / 3, y + height - 13, x + width - width / 3 - 1, y + height, new Color(255, 0, 0, 255).getRGB());
            mc.getTextureManager().bindTexture(ICON);
            int b = 10;
            GuiUtils.drawModalRectWithCustomSizedTexture(x + width - width / 3 + 7, y + height - 11, b, b, b, b, b, b);
            settingsHovered = mouseX >= x + xOffset + width - width / 3 && mouseX <= x + xOffset + width && mouseY >= y + yOffset + height - 12 && mouseY <= y + yOffset + height;
            if (settingsHovered)
                GuiUtils.drawRect(x + width - width / 3, y + height - 12, x + width, y + height, new Color(255, 255, 255, 100).getRGB());
        }
    }

    @Override
    protected void renderBackground(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
        GuiUtils.drawRoundedRect(x, y, x + width, y + height, 2.0F, new Color(255, 255, 255, 60).getRGB());
    }

    @Override
    protected void renderText(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
        if (!(mc.fontRendererObj.getStringWidth(text) > width))
            GuiUtils.drawCenteredString(text, x + width / 2, y - 5 + (height - 8) / 2, new Color(255, 255, 255, 255).getRGB(), true);
        else
            renderTextWithSpacing(text);
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (settingsHovered)
            mc.displayGuiScreen(new GuiSettings(feature, null));
        return !settingsHovered && super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    private void renderTextWithSpacing(String text) {
/*        String[] texts = new String[] { text.split(" ")[0] + " " + text.split(" ")[1], text.split(" ")[2] };*/
        String[] texts = text.split(" ");
        int i = 0;
        for (String s : texts) {
            GuiUtils.drawCenteredString(s, x + width / 2, y - (texts.length * 5) + (i * 9) + (height - 8) / 2, true);
            i++;
        }
    }

}