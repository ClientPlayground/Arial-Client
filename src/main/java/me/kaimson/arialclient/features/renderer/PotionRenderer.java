package me.kaimson.arialclient.features.renderer;

import me.kaimson.arialclient.features.Feature;
import me.kaimson.arialclient.gui.GuiUtils;
import me.kaimson.arialclient.utils.BoxUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionRenderer extends IModuleRender {

    private final ResourceLocation inventoryBackground = new ResourceLocation("textures/gui/container/inventory.png");

    @Override
    public void render(Feature feature, float x, float y) {
        float scale = feature.getScale();
        int coordX = BoxUtils.getBoxOffX(feature, (int) (x / scale), feature.getWidth());
        int coordY = BoxUtils.getBoxOffY(feature, (int) (y / scale), feature.getHeight());
        render(feature, coordX, coordY, scale);
    }

    private void render(Feature feature, float x, float y, float scale) {
        if (mc.thePlayer.getActivePotionEffects() == null) return;

        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, 1);
        GlStateManager.enableTexture2D();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        feature.setWidth(getWidth());
        feature.setHeight(getHeight());

        int j = (int)y;
        int l = 33;

        for (PotionEffect potionEffect : mc.thePlayer.getActivePotionEffects()) {
            Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            mc.getTextureManager().bindTexture(inventoryBackground);

            if (potion.hasStatusIcon()) {
                int i1 = potion.getStatusIconIndex();
                GuiUtils.INSTANCE.drawTexturedModalRect(x + 6, j + 7, i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);
            }

            String s1 = I18n.format(potion.getName());
            switch (potionEffect.getAmplifier()) {
                case 1:
                    s1 = s1 + " " + I18n.format("enchantment.level.2");
                    break;
                case 2:
                    s1 = s1 + "  " + I18n.format("enchantment.level.3");
                    break;
                case 3:
                    s1 = s1 + " " + I18n.format("enchantment.level.4");
            }
            String s = Potion.getDurationString(potionEffect);

            GuiUtils.drawString(s1, x + 10 + 18, j + 6, 16777215, true);
            GuiUtils.drawString(s, x + 10 + 18, j + 6 + 10, 8355711, true);
            j += l;
        }
        GlStateManager.scale(Math.pow(scale, -1), Math.pow(scale, -1), 1);
        GlStateManager.popMatrix();
    }

    private int getWidth() {
        if (mc.thePlayer.getActivePotionEffects().size() < 1) return 94;

        int length = 0;
        for (PotionEffect potionEffect : mc.thePlayer.getActivePotionEffects())
        {
            Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
            String s1 = I18n.format(potion.getName());

            if (potionEffect.getAmplifier() == 1)
            {
                s1 = s1 + " " + I18n.format("enchantment.level.2");
            }
            else if (potionEffect.getAmplifier() == 2)
            {
                s1 = s1 + " " + I18n.format("enchantment.level.3");
            }
            else if (potionEffect.getAmplifier() == 3)
            {
                s1 = s1 + " " + I18n.format("enchantment.level.4");
            }

            if (mc.fontRendererObj.getStringWidth(s1) > length)
            {
                length = mc.fontRendererObj.getStringWidth(s1);
            }
        }

        return length + 10 + 18;
    }

    private int getHeight() {
        if (mc.thePlayer.getActivePotionEffects().size() < 1) return 34 * 3;

        int j = 0;
        int l = 33;

        j += l * mc.thePlayer.getActivePotionEffects().size();

        return j;
    }
}
