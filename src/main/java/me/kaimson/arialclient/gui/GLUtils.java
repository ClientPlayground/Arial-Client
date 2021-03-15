package me.kaimson.arialclient.gui;

import me.kaimson.arialclient.Client;
import me.kaimson.arialclient.utils.MathUtil;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class GLUtils extends FontUtils {

    public static void drawRect(float left, float top, float right, float bottom, int color, boolean smooth)
    {
        GL11.glPushMatrix();
        setGlColor(color);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex3f(left, bottom, 0.0F);
        GL11.glVertex3f(right, bottom, 0.0F);
        GL11.glVertex3f(right, top, 0.0F);
        GL11.glVertex3f(left, top, 0.0F);
        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        //Reset color
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    public static void drawRectOutline(int left, int top, int right, int bottom, int color) {
        drawRect(left - 1, top - 1, right + 1, top, color);
        drawRect(right, top, right + 1, bottom, color);
        drawRect(left - 1, bottom, right + 1, bottom + 1, color);
        drawRect(left - 1, top, left, bottom, color);
    }

    public static void drawRoundedRect(float paramInt1, float paramInt2, float paramInt3, float paramInt4, float radius, int color)
    {
        float f1 = (color >> 24 & 0xFF) / 255.0F;
        float f2 = (color >> 16 & 0xFF) / 255.0F;
        float f3 = (color >> 8 & 0xFF) / 255.0F;
        float f4 = (color & 0xFF) / 255.0F;
        GlStateManager.color(f2, f3, f4, f1);
        drawRoundedRect(paramInt1, paramInt2, paramInt3, paramInt4, radius);
    }

    private static void drawRoundedRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5)
    {
        int i = 18;
        float f1 = 90.0F / i;
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat2);
        GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat4);
        GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat2);
        GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat4);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        GL11.glVertex2f(paramFloat1, paramFloat2 + paramFloat5);
        GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat2 + paramFloat5);
        GL11.glVertex2f(paramFloat1, paramFloat4 - paramFloat5);
        GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat4 - paramFloat5);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        GL11.glVertex2f(paramFloat3, paramFloat2 + paramFloat5);
        GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat2 + paramFloat5);
        GL11.glVertex2f(paramFloat3, paramFloat4 - paramFloat5);
        GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat4 - paramFloat5);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        float f2 = paramFloat3 - paramFloat5;
        float f3 = paramFloat2 + paramFloat5;
        GL11.glVertex2f(f2, f3);
        float f4;

        for (int j = 0; j <= i; j++)
        {
            f4 = j * f1;
            GL11.glVertex2f((float)(f2 + paramFloat5 * Math.cos(Math.toRadians(f4))), (float)(f3 - paramFloat5 * Math.sin(Math.toRadians(f4))));
        }

        GL11.glEnd();
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        f2 = paramFloat1 + paramFloat5;
        f3 = paramFloat2 + paramFloat5;
        GL11.glVertex2f(f2, f3);

        for (int j = 0; j <= i; j++)
        {
            f4 = j * f1;
            GL11.glVertex2f((float)(f2 - paramFloat5 * Math.cos(Math.toRadians(f4))), (float)(f3 - paramFloat5 * Math.sin(Math.toRadians(f4))));
        }

        GL11.glEnd();
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        f2 = paramFloat1 + paramFloat5;
        f3 = paramFloat4 - paramFloat5;
        GL11.glVertex2f(f2, f3);

        for (int j = 0; j <= i; j++)
        {
            f4 = j * f1;
            GL11.glVertex2f((float)(f2 - paramFloat5 * Math.cos(Math.toRadians(f4))), (float)(f3 + paramFloat5 * Math.sin(Math.toRadians(f4))));
        }

        GL11.glEnd();
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        f2 = paramFloat3 - paramFloat5;
        f3 = paramFloat4 - paramFloat5;
        GL11.glVertex2f(f2, f3);

        for (int j = 0; j <= i; j++)
        {
            f4 = j * f1;
            GL11.glVertex2f((float)(f2 + paramFloat5 * Math.cos(Math.toRadians(f4))), (float)(f3 + paramFloat5 * Math.sin(Math.toRadians(f4))));
        }

        GL11.glEnd();
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    public static void drawRoundedOutline(int x, int y, int x2, int y2, float radius, float width, int color) {
        float f1 = (color >> 24 & 0xFF) / 255.0F;
        float f2 = (color >> 16 & 0xFF) / 255.0F;
        float f3 = (color >> 8 & 0xFF) / 255.0F;
        float f4 = (color & 0xFF) / 255.0F;
        GlStateManager.color(f2, f3, f4, f1);
        drawRoundedOutline(x, y, x2, y2, radius, width);
    }

    private static void drawRoundedOutline(float x, float y, float x2, float y2, float radius, float width)
    {
        int i = 18;
        int j = 90 / i;

        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

        if (width != 1.0F)
        {
            GL11.glLineWidth(width);
        }

        GL11.glBegin(3);

        GL11.glVertex2f(x + radius, y);
        GL11.glVertex2f(x2 - radius, y);

        GL11.glEnd();

        GL11.glBegin(3);

        GL11.glVertex2f(x2, y + radius);
        GL11.glVertex2f(x2, y2 - radius);

        GL11.glEnd();

        GL11.glBegin(3);

        GL11.glVertex2f(x2 - radius, y2 - 0.1F);
        GL11.glVertex2f(x + radius, y2 - 0.1F);

        GL11.glEnd();

        GL11.glBegin(3);

        GL11.glVertex2f(x + 0.1F, y2 - radius);
        GL11.glVertex2f(x + 0.1F, y + radius);

        GL11.glEnd();

        float f1 = x2 - radius;
        float f2 = y + radius;

        GL11.glBegin(3);
        for (int k = 0; k <= i; k++)
        {
            int m = 90 - k * j;
            GL11.glVertex2f((float)(f1 + radius * MathUtil.getRightAngle(m)), (float)(f2 - radius * MathUtil.getAngle(m)));
        }
        GL11.glEnd();

        f1 = x2 - radius;
        f2 = y2 - radius;

        GL11.glBegin(3);
        for (int k = 0; k <= i; k++)
        {
            int m = k * j + 270;
            GL11.glVertex2f((float)(f1 + radius * MathUtil.getRightAngle(m)), (float)(f2 - radius * MathUtil.getAngle(m)));
        }
        GL11.glEnd();

        GL11.glBegin(3);

        f1 = x + radius;
        f2 = y2 - radius;
        for (int k = 0; k <= i; k++)
        {
            int m = k * j + 90;
            GL11.glVertex2f((float)(f1 + radius * MathUtil.getRightAngle(m)), (float)(f2 + radius * MathUtil.getAngle(m)));
        }
        GL11.glEnd();

        GL11.glBegin(3);

        f1 = x + radius;
        f2 = y + radius;
        for (int k = 0; k <= i; k++)
        {
            int m = 270 - k * j;
            GL11.glVertex2f((float)(f1 + radius * MathUtil.getRightAngle(m)), (float)(f2 + radius * MathUtil.getAngle(m)));
        }
        GL11.glEnd();

        if (width != 1.0F)
        {
            GL11.glLineWidth(1.0F);
        }

        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
    }

    public static void drawRoundedOutlineGradient(float x, float y, float x2, float y2, float radius, float width, int color, int color2)
    {
        int i = 18;
        int j = 90 / i;

        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

        if (width != 1.0F)
        {
            GL11.glLineWidth(width);
        }

        GuiUtils.setGlColor(color);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glBegin(3);

        GL11.glVertex2f(x + radius, y);
        GL11.glVertex2f(x2 - radius, y);

        GL11.glEnd();

        GL11.glBegin(3);

        GL11.glVertex2f(x2, y + radius);
        GuiUtils.setGlColor(color2);
        GL11.glVertex2f(x2, y2 - radius);

        GL11.glEnd();

        GL11.glBegin(3);

        GL11.glVertex2f(x2 - radius, y2 - 0.1F);
        GL11.glVertex2f(x + radius, y2 - 0.1F);

        GL11.glEnd();

        GL11.glBegin(3);

        GuiUtils.setGlColor(color2);
        GL11.glVertex2f(x + 0.1F, y2 - radius);
        GuiUtils.setGlColor(color);
        GL11.glVertex2f(x + 0.1F, y + radius);

        GL11.glEnd();

        float f1 = x2 - radius;
        float f2 = y + radius;

        GuiUtils.setGlColor(color);
        GL11.glBegin(3);
        for (int k = 0; k <= i; k++)
        {
            int m = 90 - k * j;
            GL11.glVertex2f((float)(f1 + radius * MathUtil.getRightAngle(m)), (float)(f2 - radius * MathUtil.getAngle(m)));
        }
        GL11.glEnd();

        f1 = x2 - radius;
        f2 = y2 - radius;

        GuiUtils.setGlColor(color2);
        GL11.glBegin(3);
        for (int k = 0; k <= i; k++)
        {
            int m = k * j + 270;
            GL11.glVertex2f((float)(f1 + radius * MathUtil.getRightAngle(m)), (float)(f2 - radius * MathUtil.getAngle(m)));
        }
        GL11.glEnd();

        GuiUtils.setGlColor(color2);
        GL11.glBegin(3);

        f1 = x + radius;
        f2 = y2 - radius;
        for (int k = 0; k <= i; k++)
        {
            int m = k * j + 90;
            GL11.glVertex2f((float)(f1 + radius * MathUtil.getRightAngle(m)), (float)(f2 + radius * MathUtil.getAngle(m)));
        }
        GL11.glEnd();

        GuiUtils.setGlColor(color);
        GL11.glBegin(3);

        f1 = x + radius;
        f2 = y + radius;
        for (int k = 0; k <= i; k++)
        {
            int m = 270 - k * j;
            GL11.glVertex2f((float)(f1 + radius * MathUtil.getRightAngle(m)), (float)(f2 + radius * MathUtil.getAngle(m)));
        }
        GL11.glEnd();

        if (width != 1.0F)
        {
            GL11.glLineWidth(1.0F);
        }

        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
    }

    public static void drawRoundedCorner(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float radius, int color, int[] corners)
    {
        GlStateManager.pushMatrix();
        setGlColor(color);
        int i = 18;
        float f1 = 90.0F / i;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        GL11.glVertex2f(paramFloat1 + radius, paramFloat2);
        GL11.glVertex2f(paramFloat1 + radius, paramFloat4);
        GL11.glVertex2f(paramFloat3 - radius, paramFloat2);
        GL11.glVertex2f(paramFloat3 - radius, paramFloat4);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        GL11.glVertex2f(paramFloat1, paramFloat2 + radius);
        GL11.glVertex2f(paramFloat1 + radius, paramFloat2 + radius);
        GL11.glVertex2f(paramFloat1, paramFloat4 - radius);
        GL11.glVertex2f(paramFloat1 + radius, paramFloat4 - radius);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        GL11.glVertex2f(paramFloat3, paramFloat2 + radius);
        GL11.glVertex2f(paramFloat3 - radius, paramFloat2 + radius);
        GL11.glVertex2f(paramFloat3, paramFloat4 - radius);
        GL11.glVertex2f(paramFloat3 - radius, paramFloat4 - radius);
        GL11.glEnd();
        float f2 = paramFloat3 - radius;
        float f3 = paramFloat2 + radius;
        float f4 = 0;

        if (corners[0] == 0)
        {
            GL11.glBegin(GL11.GL_TRIANGLE_FAN);
            GL11.glVertex2f(f2, f3);

            for (int j = 0; j <= i; j++)
            {
                f4 = j * f1;
                GL11.glVertex2f((float)(f2 + radius * Math.cos(Math.toRadians(f4))), (float)(f3 - radius * Math.sin(Math.toRadians(f4))));
            }

            GL11.glEnd();
        }

        if (corners[1] == 0)
        {
            GL11.glBegin(GL11.GL_TRIANGLE_FAN);
            f2 = paramFloat1 + radius;
            f3 = paramFloat2 + radius;
            GL11.glVertex2f(f2, f3);

            for (int j = 0; j <= i; j++)
            {
                f4 = j * f1;
                GL11.glVertex2f((float)(f2 - radius * Math.cos(Math.toRadians(f4))), (float)(f3 - radius * Math.sin(Math.toRadians(f4))));
            }

            GL11.glEnd();
        }

        if (corners[2] == 0)
        {
            GL11.glBegin(GL11.GL_TRIANGLE_FAN);
            f2 = paramFloat1 + radius;
            f3 = paramFloat4 - radius;
            GL11.glVertex2f(f2, f3);

            for (int j = 0; j <= i; j++)
            {
                f4 = j * f1;
                GL11.glVertex2f((float)(f2 - radius * Math.cos(Math.toRadians(f4))), (float)(f3 + radius * Math.sin(Math.toRadians(f4))));
            }

            GL11.glEnd();
        }

        if (corners[3] == 0)
        {
            GL11.glBegin(GL11.GL_TRIANGLE_FAN);
            f2 = paramFloat3 - radius;
            f3 = paramFloat4 - radius;
            GL11.glVertex2f(f2, f3);

            for (int j = 0; j <= i; j++)
            {
                f4 = j * f1;
                GL11.glVertex2f((float)(f2 + radius * Math.cos(Math.toRadians(f4))), (float)(f3 + radius * Math.sin(Math.toRadians(f4))));
            }

            GL11.glEnd();
        }

        if (corners[0] != 0)
        {
            drawRect(paramFloat3, paramFloat2, paramFloat3 - radius, paramFloat2 + radius, color, false);
        }

        if (corners[1] != 0)
        {
            drawRect(paramFloat1, paramFloat2, paramFloat1 + radius, paramFloat2 + radius, color, false);
        }

        if (corners[2] != 0)
        {
            drawRect(paramFloat1, paramFloat4, paramFloat1 + radius, paramFloat4 - radius, color, false);
        }

        if (corners[3] != 0)
        {
            drawRect(paramFloat3, paramFloat4, paramFloat3 - radius, paramFloat4 - radius, color, false);
        }

        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    public static void setGlColor(int color)
    {
        float alpha = (color >> 24 & 0xFF) / 255.0f;
        float red = (color >> 16 & 0xFF) / 255.0f;
        float green = (color >> 8 & 0xFF) / 255.0f;
        float blue = (color & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }

    public static void drawCircle(float x, float y, float radius, float thickness, Color color, boolean smooth) {
        drawPartialCircle(x, y, radius, 0, 360, thickness, color, smooth);
    }

    public static void drawFilledRect(float x1, float y1, float x2, float y2, int colour, boolean smooth)
    {
        drawFilledShape(new float[] { x1, y1, x1, y2, x2, y2, x2, y1 }, new Color(colour, true), smooth);
    }

    public static void drawFilledShape(float[] points, Color colour, boolean smooth)
    {
        //Pre-render
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        if (smooth)
        {
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
        }
        else
        {
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
        }

        GL11.glLineWidth(1.0F);
        /*        GL11.glEnable(GL11.GL_BLEND);*/
        GL11.glColor4f(colour.getRed() / 255.0F, colour.getGreen() / 255.0F, colour.getBlue() / 255.0F, colour.getAlpha() / 255.0F);
        GL11.glBegin(GL11.GL_POLYGON);

        for (int i = 0; i < points.length; i += 2)
        {
            GL11.glVertex2f(points[i], points[i + 1]);
        }

        GL11.glEnd();
        //Post-render
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    public static void drawLine(float x, float x1, float y, float thickness, int colour, boolean smooth)
    {
        drawLines(new float[] { x, y, x1, y}, thickness, new Color(colour, true), smooth);
    }

    public static void drawVerticalLine(float x, float y, float y1, float thickness, int colour, boolean smooth)
    {
        drawLines(new float[] { x, y, x, y1}, thickness, new Color(colour, true), smooth);
    }

    public static void drawLines(float[] points, float thickness, Color colour, boolean smooth)
    {
        //Pre-render
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        if (smooth)
        {
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
        }
        else
        {
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
        }

        GL11.glLineWidth(thickness);
        GL11.glColor4f(colour.getRed() / 255.0F, colour.getGreen() / 255.0F, colour.getBlue() / 255.0F, colour.getAlpha() / 255.0F);
        GL11.glBegin(GL11.GL_LINES);

        for (int i = 0; i < points.length; i += 2) {
            GL11.glVertex2f(points[i], points[i + 1]);
        }

        GL11.glEnd();
        //Post-render
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }

    public static void drawPartialCircle(float x, float y, float radius, int startAngle, int endAngle, float thickness, Color colour, boolean smooth)
    {
        //Pre-render
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        if (startAngle > endAngle)
        {
            int temp = startAngle;
            startAngle = endAngle;
            endAngle = temp;
        }

        if (startAngle < 0)
        {
            startAngle = 0;
        }

        if (endAngle > 360)
        {
            endAngle = 360;
        }

        if (smooth)
        {
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
        }
        else
        {
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
        }

        GL11.glLineWidth(thickness);
        GL11.glColor4f(colour.getRed() / 255.0F, colour.getGreen() / 255.0F, colour.getBlue() / 255.0F, colour.getAlpha() / 255.0F);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        float ratio = 0.017453292F;

        for (int i = startAngle; i <= endAngle; i++)
        {
            float radians = (i - 90) * ratio;
            GL11.glVertex2f(x + (float)Math.cos(radians) * radius, y + (float)Math.sin(radians) * radius);
        }

        GL11.glEnd();
        //Post-render
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static int convertPercentToValue(float percent) {
        return (int) (percent * 255.0F);
    }

    public static int getIntermediateColor(int a, int b, float percent)
    {
        float avgRed = (a >> 16 & 0xFF) * percent + (b >> 16 & 0xFF) * (1.0F - percent);
        float avgGreen = (a >> 8 & 0xFF) * percent + (b >> 8 & 0xFF) * (1.0F - percent);
        float avgBlue = (a >> 0 & 0xFF) * percent + (b >> 0 & 0xFF) * (1.0F - percent);
        float avgAlpha = (a >> 24 & 0xFF) * percent + (b >> 24 & 0xFF) * (1.0F - percent);

        try
        {
            return (new Color(avgRed / 255.0F, avgGreen / 255.0F, avgBlue / 255.0F, avgAlpha / 255.0F)).getRGB();
        }
        catch (IllegalArgumentException e)
        {
            Client.error("Color parameter outside of expected range!");
            return Integer.MIN_VALUE;
        }
    }

}
