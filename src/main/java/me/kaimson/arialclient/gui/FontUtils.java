package me.kaimson.arialclient.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class FontUtils extends Gui {

    protected final static Minecraft mc = Minecraft.getMinecraft();

    public static int drawString(String text, int x, int y) {
        return mc.fontRendererObj.drawString(text, x, y, 0xFFFFFF, false);
    }

    public static int drawString(String text, float x, float y) {
        return mc.fontRendererObj.drawString(text, x, y, 0xFFFFFF, false);
    }

    public static int drawString(String text, float x, float y, int color) {
        return mc.fontRendererObj.drawString(text, x, y, color, false);
    }

    public static int drawString(String text, float x, float y, int color, boolean shadow) {
        return mc.fontRendererObj.drawString(text, x, y, color, shadow);
    }

    public static int drawString(String text, int x, int y, int color) {
        return drawString(text, x, y, color, false);
    }

    public static int drawString(String text, int x, int y, boolean shadow) {
        return mc.fontRendererObj.drawString(text, x, y, 0xFFFFFF, shadow);
    }

    public static int drawString(String text, int x, int y, int color, boolean shadow) {
        return mc.fontRendererObj.drawString(text, x, y, color, shadow);
    }

    public static int drawCenteredString(String text, int x, int y) {
        return drawString(text, x - mc.fontRendererObj.getStringWidth(text) / 2, y);
    }

    public static int drawCenteredString(String text, float x, float y, int color) {
        return drawString(text, x - (float)mc.fontRendererObj.getStringWidth(text) / 2, y, color);
    }

    public static int drawCenteredString(String text, int x, int y, boolean shadow) {
        return drawString(text, x - mc.fontRendererObj.getStringWidth(text) / 2, y, shadow);
    }

    public static int drawCenteredString(String text, float x, float y, int color, boolean shadow) {
        return drawString(text, x - (float)mc.fontRendererObj.getStringWidth(text) / 2, y, color, shadow);
    }

    public static int drawCenteredString(String text, int x, int y, int color, boolean shadow) {
        return drawString(text, x - mc.fontRendererObj.getStringWidth(text) / 2, y, color, shadow);
    }

}
