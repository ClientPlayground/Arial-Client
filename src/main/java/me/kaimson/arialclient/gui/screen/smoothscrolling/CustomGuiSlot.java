package me.kaimson.arialclient.gui.screen.smoothscrolling;

import lombok.SneakyThrows;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;

import java.lang.reflect.Method;

public class CustomGuiSlot {
    public static void setScroller(final GuiSlot list) {
        list.scroller = new GuiSlotScroller(list);
    }

    public static void setScrollAmount(final GuiSlot list) {
        setScroller(list);
        list.scrollVelocity = 0.0;
    }

    @SneakyThrows
    public static void renderScrollbar(final GuiSlot list, final int mouseX, final int mouseY) {
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer buffer = tessellator.getWorldRenderer();
        /*        final int scrollbarPositionMinX = list.getScrollbarX();*/
        /*        final int scrollbarPositionMinX = (int) list.getClass().getDeclaredMethod("getScrollBarX").invoke(list);*/
        final int scrollbarPositionMinX = (int) callReflectionsReturn(list, "getScrollBarX", "d");
        final int scrollbarPositionMaxX = scrollbarPositionMinX + 6;
        final int maxScroll = list.func_148135_f();
        /*        int contentHeight = list.contentHeight*/
        int contentHeight = (int) callReflectionsReturn(list, "getContentHeight", "k");

        if (maxScroll > 0) {
            int height = (list.bottom - list.top) * (list.bottom - list.top) / contentHeight;
            height = MathHelper.clamp_int(height, 32, list.bottom - list.top - 8);
            height -= (int) Math.min((list.amountScrolled < 0.0) ? ((int) (-list.amountScrolled)) : ((list.amountScrolled > list.func_148135_f()) ? ((int) list.amountScrolled - list.func_148135_f()) : 0), height * 0.75);
            final int minY = Math.min(Math.max(list.getAmountScrolled() * (list.bottom - list.top - height) / maxScroll + list.top, list.top), list.bottom - height);
            buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            buffer.pos((double) scrollbarPositionMinX, (double) list.bottom, 0.0).tex(0.0, 1.0).color(0, 0, 0, 255).endVertex();
            buffer.pos((double) scrollbarPositionMaxX, (double) list.bottom, 0.0).tex(1.0, 1.0).color(0, 0, 0, 255).endVertex();
            buffer.pos((double) scrollbarPositionMaxX, (double) list.top, 0.0).tex(1.0, 0.0).color(0, 0, 0, 255).endVertex();
            buffer.pos((double) scrollbarPositionMinX, (double) list.top, 0.0).tex(0.0, 0.0).color(0, 0, 0, 255).endVertex();
            tessellator.draw();
            buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            buffer.pos((double) scrollbarPositionMinX, (double) (minY + height), 0.0).tex(0.0, 1.0).color(128, 128, 128, 255).endVertex();
            buffer.pos((double) scrollbarPositionMaxX, (double) (minY + height), 0.0).tex(1.0, 1.0).color(128, 128, 128, 255).endVertex();
            buffer.pos((double) scrollbarPositionMaxX, (double) minY, 0.0).tex(1.0, 0.0).color(128, 128, 128, 255).endVertex();
            buffer.pos((double) scrollbarPositionMinX, (double) minY, 0.0).tex(0.0, 0.0).color(128, 128, 128, 255).endVertex();
            tessellator.draw();
            buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            buffer.pos((double) scrollbarPositionMinX, (double) (minY + height - 1), 0.0).tex(0.0, 1.0).color(192, 192, 192, 255).endVertex();
            buffer.pos((double) (scrollbarPositionMaxX - 1), (double) (minY + height - 1), 0.0).tex(1.0, 1.0).color(192, 192, 192, 255).endVertex();
            buffer.pos((double) (scrollbarPositionMaxX - 1), (double) minY, 0.0).tex(1.0, 0.0).color(192, 192, 192, 255).endVertex();
            buffer.pos((double) scrollbarPositionMinX, (double) minY, 0.0).tex(0.0, 0.0).color(192, 192, 192, 255).endVertex();
            tessellator.draw();
        }

        /*        list.func_148142_b_c(mouseX, mouseY);*/
        /*        list.func_148142_b(mouseX, mouseY);*/
        Method method;
        try {
            method = GuiSlot.class.getDeclaredMethod("func_148142_b", int.class, int.class);
        } catch (Exception e) {
            method = GuiSlot.class.getDeclaredMethod("b", int.class, int.class);
        }
        method.setAccessible(true);
        method.invoke(list, mouseX, mouseY);
        GlStateManager.enableTexture2D();
        GlStateManager.shadeModel(7424);
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
    }

    @SneakyThrows
    private static Object callReflectionsReturn(final GuiSlot slot, String methodName, String fallback) {
        Method method;
        try {
            method = GuiSlot.class.getDeclaredMethod(methodName);
        } catch (Exception e) {
            method = GuiSlot.class.getDeclaredMethod(fallback);
        }
        method.setAccessible(true);
        return method.invoke(slot);
    }

/*    public static void mouseScrolled(final GuiSlot list, final double amount)
    {
        setScroller(list);
        final double scrollVelocity = list.scrollVelocity;
        final GuiSlotScroller scroller = list.scroller;
        double velo = list.scrollVelocity;
        final RunSixtyTimesEverySec sec = list.scroller;

        if (list.amountScrolled <= list.func_148135_f() && amount < 0.0)
        {
            velo += 16.0;
        }

        if (list.amountScrolled >= 0.0 && amount > 0.0)
        {
            velo -= 16.0;
        }

        list.scrollVelocity = velo;

        if (!sec.isRegistered())
        {
            sec.registerTick();
        }
    }*/
}
