package me.kaimson.arialclient.gui.components.settings;

import me.kaimson.arialclient.config.Config;
import me.kaimson.arialclient.features.Feature;
import me.kaimson.arialclient.gui.GuiUtils;
import me.kaimson.arialclient.utils.MathUtil;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;
import java.math.BigDecimal;

public class ComponentSlider extends ComponentSetting {

    private final float min;
    private final float max;
    private final float step;
    private final float current;

    private String display;
    private String displayString;

    protected boolean dragging;
    protected float sliderValue;

    public ComponentSlider(int x, int y, int width, int height, float min, float max, float step, float current, Feature setting) {
        super(x, y, width, height, setting);
        this.min = min;
        this.max = max;
        this.step = step;
        this.current = current;
        this.display = "";
        this.displayString = String.valueOf(setting.getOrDefault(current));
        this.sliderValue = MathUtil.normalizeValue((float)setting.getOrDefault(current), min, max, step);
    }

    @Override
    public void render(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
        mouseDragged(x, y, mouseX, mouseY);
        hovered = mouseX >= x + xOffset && mouseX <= x + xOffset + width && mouseY >= y + yOffset && mouseY <= y + yOffset + height;

        GuiUtils.drawString(setting.getTranslation(), x + xOffset - mc.fontRendererObj.getStringWidth(setting.getTranslation()) - 3, y + yOffset - 3);

        GuiUtils.drawRoundedRect(x, y, x + width, y + height, 2.1F, new Color(255, 120, 0, 100).getRGB());
        // Draw "progress"
        GuiUtils.drawRoundedRect(x, y, x + (int)(this.sliderValue * (float)(this.width - 8)) + 4, y + height, 2.1F, new Color(255, 100, 0, 200).getRGB());

        if (mouseX >= x + (int)(this.sliderValue * (float)(this.width - 8) + 1) && mouseX <= x + (int)(this.sliderValue * (float)(this.width - 8) + 8) && hovered)
            GuiUtils.drawPartialCircle(x + (int)(this.sliderValue * (float)(this.width - 8)) + 4, y + (float)height / 2, 3.2F, 0, 360, 1.0F, new Color(255, 255, 255, 255), true);
        GuiUtils.drawPartialCircle(x + (int)(this.sliderValue * (float)(this.width - 8)) + 4, y + (float)height / 2, 1.6F, 0, 360, 8.0F, new Color(255, 160, 0, 255), true);

        float scale = 0.7F;
        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, 1.0F);
        GuiUtils.drawCenteredString(displayString, (x + (float)this.width / 2) / scale, (y - (float)(this.height - 8) / 2 - 7) / scale, 14737632, true);
        GlStateManager.scale(Math.pow(scale, -1), Math.pow(scale, -1), 1.0F);
        GlStateManager.popMatrix();
    }

    @Override
    protected void mouseDragged(int x, int y, int mouseX, int mouseY) {
        if (dragging) {
            this.sliderValue = (mouseX - (x + 4)) / (float)(this.width - 8);
            this.sliderValue = MathUtil.normalizeValue(MathUtil.denormalizeValue(this.sliderValue, min, max, step), min, max, step);
            this.displayString = display + (getRoundedValue(MathUtil.denormalizeValue(sliderValue, min, max, step)));
        }
    }
    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (super.mouseClicked(mouseX, mouseY, mouseButton)) {
            this.sliderValue = (mouseX - (this.x + 4)) / (float)(this.width - 8);
            this.displayString = display + (getRoundedValue(MathUtil.denormalizeValue(sliderValue, min, max, step)));
            this.dragging = true;
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        this.dragging = false;
        Config.INSTANCE.getCustoms().put(setting, MathUtil.denormalizeValue(sliderValue, min, max, step));
        Config.INSTANCE.saveConfig();
/*        Client.config.getCustoms().put(ingame, MathUtil.denormalizeValue(sliderValue, min, max, step));
        Client.config.saveConfig();*/
    }

    protected float getRoundedValue(float value) {
        return new BigDecimal(String.valueOf(value)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

}
