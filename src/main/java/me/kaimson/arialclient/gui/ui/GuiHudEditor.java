package me.kaimson.arialclient.gui.ui;

import me.kaimson.arialclient.Client;
import me.kaimson.arialclient.blur.BlurShader;
import me.kaimson.arialclient.config.Config;
import me.kaimson.arialclient.features.Feature;
import me.kaimson.arialclient.gui.GuiUtils;
import me.kaimson.arialclient.gui.components.ComponentCustomButton;
import me.kaimson.arialclient.gui.components.ComponentLocation;
import me.kaimson.arialclient.gui.screen.GuiScreen;
import me.kaimson.arialclient.utils.BoxUtils;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.Arrays;

public class GuiHudEditor extends GuiScreen {

    private Feature dragging;
    private float xOffset;
    private float yOffset;
    private ScaledResolution sr;

    @Override
    public void initGui() {
        super.initGui();
        this.sr = new ScaledResolution(mc);

        BlurShader.INSTANCE.onGuiOpen();

        this.components.add(new ComponentCustomButton(width / 2 - 50, height / 2 - 10, 100, 20, "Mods", (button, xOff, yOff, ticks) -> {
            GuiUtils.drawRect(button.x, button.y, button.x + button.width, button.y + button.height, button.hovered ? new Color(0, 0, 0, 100).getRGB() : new Color(0, 0, 0, 50).getRGB());
            GuiUtils.drawRectOutline(button.x, button.y, button.x + button.width, button.y + button.height, button.hovered ? new Color(0, 0, 0, 150).getRGB() : new Color(0, 0, 0, 100).getRGB());
        }, (button) -> this.mc.displayGuiScreen(new GuiMainSettings(this))));
        Arrays.stream(Feature.values()).filter(feature -> feature.isModule() && feature.isEnabled()).forEach(feature -> components.add(new ComponentLocation(feature, (button) -> {
            dragging = feature;
            xOffset = (Mouse.getX() / sr.getScaleFactor()) - feature.getX();
            yOffset = ((mc.displayHeight - Mouse.getY()) / sr.getScaleFactor()) - feature.getY();
            xOffset *= -1;
            yOffset *= -1;

            switch (dragging.getAnchorPoint()) {
                case TOP_RIGHT:
                    xOffset += -dragging.getWidth();
                    break;
                case TOP_CENTER:
                    xOffset += -dragging.getWidth() / 2;
                    break;
                case CENTER_LEFT:
                    yOffset += -dragging.getHeight() / 2;
                    break;
                case CENTER:
                    xOffset += -dragging.getWidth() / 2;
                    yOffset += -dragging.getHeight() / 2;
                    break;
                case CENTER_RIGHT:
                    xOffset += -dragging.getWidth();
                    yOffset += -dragging.getHeight() / 2;
                    break;
                case BOTTOM_LEFT:
                    yOffset += -dragging.getHeight();
                    break;
                case BOTTOM_CENTER:
                    xOffset += -dragging.getWidth() / 2;
                    yOffset += -dragging.getHeight();
                    break;
                case BOTTOM_RIGHT:
                    xOffset += -dragging.getWidth();
                    yOffset += -dragging.getHeight();
            }
        })));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawRect(0, 0, width, height, new Color(0, 0, 0, 100).getRGB());
        GuiUtils.drawCenteredString(EnumChatFormatting.RED + "A" + EnumChatFormatting.WHITE + "rial " + EnumChatFormatting.GRAY + "C" + EnumChatFormatting.WHITE + "lient", width / 2 + 5, height / 2 - mc.fontRendererObj.FONT_HEIGHT - 15);
        mc.getTextureManager().bindTexture(new ResourceLocation(Client.resourceLocation + "/logo.png"));
        int b = 12;
        GuiUtils.drawModalRectWithCustomSizedTexture(width / 2 - mc.fontRendererObj.getStringWidth(Client.name) + 16, height / 2 - mc.fontRendererObj.FONT_HEIGHT - 17, 0, 0, b, b, b, b);

        if (dragging != null && Mouse.isButtonDown(0)) {
            int x = mouseX - BoxUtils.getBoxOffX(dragging, dragging.getAnchorPoint().getX(sr.getScaledWidth()), dragging.getWidth());
            int y = mouseY - BoxUtils.getBoxOffY(dragging, dragging.getAnchorPoint().getY(sr.getScaledHeight()), dragging.getHeight());
            x += xOffset;
            y += yOffset;
            Config.INSTANCE.setPosition(dragging, x, y);
            Config.INSTANCE.setClosestAnchorPoint(dragging);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        dragging = null;
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        BlurShader.INSTANCE.onGuiClose();
        Config.INSTANCE.saveConfig();
    }
}
