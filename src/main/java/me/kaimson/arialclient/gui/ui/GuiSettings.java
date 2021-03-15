package me.kaimson.arialclient.gui.ui;

import me.kaimson.arialclient.Client;
import me.kaimson.arialclient.features.Feature;
import me.kaimson.arialclient.gui.GuiUtils;
import me.kaimson.arialclient.gui.components.settings.*;
import me.kaimson.arialclient.gui.screen.GuiScrolling;
import me.kaimson.arialclient.utils.Crosshair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GuiSettings extends SettingBase {

    private final Feature feature;
    private int row;
    private List<Feature> settings;
    private ScaledResolution sr;
    private final ResourceLocation BACKGROUND = new ResourceLocation(Client.resourceLocation + "/background_old.png");

    public GuiSettings(Feature feature, GuiScreen parentScreen) {
        super(parentScreen);
        this.feature = feature;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.sr = new ScaledResolution(mc);
        this.settings = feature.getSettings();
        row = 1;

        AtomicInteger i = new AtomicInteger();
        settings.forEach(feature -> addComponentFor(feature, i.incrementAndGet()));
        registerScroll(new Scroll(settings, width, height, height / 10 + height / 6, height - height / 10 - 10, 18, width / 2 + getWidth(66, gap) / 2 + gap / 2));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // Draw background
        mc.getTextureManager().bindTexture(BACKGROUND);
        GuiUtils.drawModalRectWithCustomSizedTexture(0, 0, width, height, width, height, width, height);

        int l = width / 2 - getWidth(66, gap) / 2;
        int r = width / 2 + getWidth(66, gap) / 2 + gap;
        int t = height / 10 + 10;
        int b = height - height / 10 - height / 6;
        GL11.glScissor(l * sr.getScaleFactor(), t * sr.getScaleFactor(), r * sr.getScaleFactor() - l * sr.getScaleFactor(), b * sr.getScaleFactor() - t * sr.getScaleFactor());
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (feature == Feature.CROSSHAIR)
            Crosshair.INSTANCE.render(sr.getScaledWidth() / 2, sr.getScaledHeight() / 2);
    }

    private void addComponentFor(Feature feature, int i) {
        int boxWidth = (getWidth(66, gap) - (columns - 1) * gap) / columns - gap;
        int boxHeight = 10;

        int x = width / 2 - boxWidth / 2;
        int y = (int) getRowHeight(row, boxHeight + 10);

        switch (feature.getSettingType()) {
            case COLOR:
                components.add(new ComponentColor(x + 1, y + 1, boxWidth, boxHeight, feature));
                break;
            case SLIDER:
                if (!feature.isGlobalSettingSlider())
                    components.add(new ComponentSlider(x + 3 + mc.fontRendererObj.getStringWidth(feature.getTranslation()), y + 3, boxWidth + 10, 6, feature.getSliderValues()[0], feature.getSliderValues()[1], feature.getSliderValues()[2], feature.getSliderValues()[3], feature));
                else
                    components.add(new ComponentScale(x + 3 + mc.fontRendererObj.getStringWidth(feature.getTranslation()), y + 3, boxWidth + 10, 6, feature.getSliderValues()[0], feature.getSliderValues()[1], feature.getSliderValues()[2], feature.getSliderValues()[3], this.feature, feature));
                break;
            case SWITCH:
                components.add(new ComponentCheckbox(x + 1, y + 1, boxWidth, boxHeight, feature));
                break;
            case VERSION:
                components.add(new ComponentVersionCheckbox(x + 3 + mc.fontRendererObj.getStringWidth(feature.getTranslation()), y + 3, 27, boxHeight, feature));
                break;
            case MODE:
                components.add(new ComponentMode(x + 1, y + 1, boxWidth + 30, boxHeight, feature));
        }

        row++;
    }

    private double getRowHeight(double row, int buttonHeight) {
        row--;
        return height / 10 + height / 6 + (row * buttonHeight);
    }

    private static class Scroll extends GuiScrolling {
        private final java.util.List<Feature> list;
        private final int scrollbarX;

        public Scroll(List<Feature> list, int width, int height, int topIn, int bottomIn, int slotHeightIn, int scrollbarX) {
            super(Minecraft.getMinecraft(), width, height, topIn, bottomIn, slotHeightIn);
            this.list = list;
            this.scrollbarX = scrollbarX;
        }

        @Override
        public int getScrollBarX() {
            return scrollbarX;
        }

        @Override
        public void drawScroll(int i, int j) {
            int j1 = this.func_148135_f();

            if (j1 > 0)
            {
                int height = (this.bottom - this.top) * (this.bottom - this.top) / getContentHeight();
                height = MathHelper.clamp_int(height, 32, this.bottom - this.top - 8);
                height -= (int)Math.min((this.amountScrolled < 0.0) ? ((int)(-this.amountScrolled)) : ((this.amountScrolled > this.func_148135_f()) ? ((int)this.amountScrolled - this.func_148135_f()) : 0), height * 0.75);
                final int minY = Math.min(Math.max(this.getAmountScrolled() * (this.bottom - this.top - height) / this.func_148135_f() + this.top, this.top), this.bottom - height);
                Color c = new Color(255, 255, 255, 255);/**/
                // Draw bg
                GuiUtils.drawRect(j - 3, top, j - 5 + 1, bottom - 1, new Color(255, 255, 255, 150).getRGB());
                // Draw scroll
                GuiUtils.drawRoundedRect(j - 5, minY, j - 5 + 3, minY + height - 1, 1.5F, c.getRGB());
            }
        }

        @Override
        public void drawScreen(int mouseXIn, int mouseYIn, float p_148128_3_) {
            mouseX = mouseXIn;
            mouseY = mouseYIn;
        }

        @Override
        protected int getSize() {
            return list.size();
        }

        @Override
        protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
        }

        @Override
        protected boolean isSelected(int slotIndex) {
            return false;
        }

        @Override
        protected void drawBackground() {

        }

        @Override
        protected void drawSlot(int entryID, int p_180791_2_, int p_180791_3_, int p_180791_4_, int mouseXIn, int mouseYIn) {

        }
    }

}
