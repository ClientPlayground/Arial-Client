package me.kaimson.arialclient.features.renderer.keystrokes;

import lombok.Getter;
import me.kaimson.arialclient.config.Config;
import me.kaimson.arialclient.features.Feature;
import me.kaimson.arialclient.gui.GuiUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class Key {
    private final KeyBinding keyBinding;
    private final KeystrokesRenderer keystrokes;

    private long pressTime;
    private float faded = 0;
    private float fadeTime = 100.0F;
    private boolean wasPressed;

    @Getter
    private double height = 18;
    private Type type = Type.NORMAL;

    public Key(KeyBinding keyBinding, KeystrokesRenderer keystrokes) {
        this.keyBinding = keyBinding;
        this.keystrokes = keystrokes;
    }

    public static Key create(KeyBinding keyBinding, KeystrokesRenderer keystrokes) {
        return new Key(keyBinding, keystrokes);
    }

    public void render(double width, double height) {
        boolean pressed = isPressed();
        float pressModifier = Math.min(1.0F, (System.currentTimeMillis() - pressTime) / (float) Feature.KEYSTROKES_FADE_TIME.getOrDefault(100.0F));
        float brightness = (pressed ? pressModifier : (1.0F - pressModifier)) * 0.8F;

        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        setBackgroundColor(brightness);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2d(0, height);
        GL11.glVertex2d(width, height);
        GL11.glVertex2d(width, 0);
        GL11.glVertex2d(0, 0);
        GL11.glEnd();
        GlStateManager.enableTexture2D();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();

        if (Feature.KEYSTROKES_OUTLINE.isEnabled()) {
            drawColoredRect(0.0, 0.0, width, 1.0, pressed);
            drawColoredRect(width - 1.0, 0.0, width, height, pressed);
            drawColoredRect(width, height, 0.0, height - 1.0, pressed);
            drawColoredRect(1.0, height, 0.0, 0.0, pressed);
        }

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        switch (type) {
            case NORMAL:
                KeyUtils.instance.drawKeyText(Keyboard.getKeyName(keyBinding.getKeyCode()), width, height, pressed);
                return;

            case SPACE_BAR:
                KeyUtils.instance.drawSpacebar(width, height, pressed);
                return;

            case RIGHT_MOUSE:
            case LEFT_MOUSE:
                KeyUtils.instance.drawKeyText(type == Type.LEFT_MOUSE ? "LMB" : "RMB", width, height, pressed);
        }
    }

    private void setBackgroundColor(float brightness) {
        boolean pressed = isPressed();
        int bgColor = 0;
        int bgPressed = 0;
        int converted = GuiUtils.convertPercentToValue(brightness);

        if ((int) Config.INSTANCE.getCustoms().getOrDefault(Feature.KEYSTROKES_BACKGROUND_COLOR, 0) != 0)
            bgColor = (int) Config.INSTANCE.getCustoms().get(Feature.KEYSTROKES_BACKGROUND_COLOR);

        if ((int) Config.INSTANCE.getCustoms().getOrDefault(Feature.KEYSTROKES_PRESSED_BACKGROUND_COLOR, 0) != 0)
            bgPressed = (int) Config.INSTANCE.getCustoms().get(Feature.KEYSTROKES_PRESSED_BACKGROUND_COLOR);

        if (bgColor == 0) bgColor = new Color(converted, converted, converted, (int) (0.4 * 255.0F)).getRGB();
        if (bgPressed == 0) bgPressed = new Color(converted, converted, converted, (int) (0.4 * 255.0F)).getRGB();

        int bg = (pressed ? bgPressed : bgColor);
        if (faded < 1.0F) {
            int lastColor = (!pressed ? bgPressed : bgColor);
            GuiUtils.setGlColor(GuiUtils.getIntermediateColor(bg, lastColor, faded));
            return;
        } else {
            GuiUtils.setGlColor(bg);
            return;
        }
        /*        GL11.glColor4f(brightness, brightness, brightness, 0.4F);*/
    }

    private void drawColoredRect(double x1, double y1, double x2, double y2, boolean invertColor) {
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glBegin(GL11.GL_QUADS);
        GuiUtils.setGlColor(keystrokes.getColor(x1, invertColor));
        GL11.glVertex3d(x1, y2, 0.0);
        GuiUtils.setGlColor(keystrokes.getColor(x2, invertColor));
        GL11.glVertex3d(x2, y2, 0.0);
        GL11.glVertex3d(x2, y1, 0.0);
        GuiUtils.setGlColor(keystrokes.getColor(x1, invertColor));
        GL11.glVertex3d(x1, y1, 0.0);
        GL11.glEnd();
        GlStateManager.enableTexture2D();
        GuiUtils.setGlColor(new Color(255, 255, 255, 255).getRGB());
        GlStateManager.popMatrix();
    }

    public Key setSpacebar() {
        height = 14.0;
        type = Type.SPACE_BAR;
        return this;
    }

    public Key setLeftMouse() {
        height = 20.0;
        type = Type.LEFT_MOUSE;
        return this;
    }

    public Key setRightMouse() {
        height = 20.0;
        type = Type.RIGHT_MOUSE;
        return this;
    }

    private boolean isPressed() {
        int keycode = keyBinding.getKeyCode();
        boolean pressed = keycode < 0 ? Mouse.isButtonDown(keycode + 100) : Keyboard.isKeyDown(keycode);
        if (wasPressed != pressed)
            pressTime = System.currentTimeMillis();
        fadeTime = (float) Feature.KEYSTROKES_FADE_TIME.getOrDefault(100.0F);
        faded = (System.currentTimeMillis() - pressTime) / fadeTime;
        wasPressed = pressed;
        return pressed;
    }

    public static class FillerKey extends Key {

        public FillerKey() {
            super(null, null);
        }

        @Override
        public void render(double width, double height) {
        }
    }

    private enum Type {
        NORMAL,
        LEFT_MOUSE,
        RIGHT_MOUSE,
        SPACE_BAR
    }
}
