package me.kaimson.arialclient.features.renderer;

import me.kaimson.arialclient.features.Feature;
import me.kaimson.arialclient.utils.BoxUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;

public class ArmorStatus extends IModuleRender {

    @Override
    public void render(Feature feature, float x, float y) {
        float scale = feature.getScale();
        int coordX = BoxUtils.getBoxOffX(feature, (int) (x / scale), feature.getWidth());
        int coordY = BoxUtils.getBoxOffY(feature, (int) (y / scale), feature.getHeight());
        render(feature, coordX, coordY, scale);
    }

    private void render(Feature feature, int x, int y, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.scale(scale, scale, 1);
        int j = 0;
        for (int i = 3; i > -1; i--) {
            if (mc.thePlayer.getCurrentArmor(i) == null) continue;
            ItemStack currentPeace = mc.thePlayer.getCurrentArmor(i);
            mc.getRenderItem().renderItemAndEffectIntoGUI(currentPeace, x - 2, y - 2);
            j++;
            y += 16;
        }
        feature.setWidth(12);
        feature.setHeight(j * 15);
        GlStateManager.scale(Math.pow(scale, -1), Math.pow(scale, -1), 1);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
    }

}
