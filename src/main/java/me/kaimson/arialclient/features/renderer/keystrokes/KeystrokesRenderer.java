package me.kaimson.arialclient.features.renderer.keystrokes;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import me.kaimson.arialclient.features.Feature;
import me.kaimson.arialclient.features.renderer.IModuleRender;
import me.kaimson.arialclient.utils.BoxUtils;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class KeystrokesRenderer extends IModuleRender {

    @Override
    public void render(Feature feature, float x, float y) {
        float scale = feature.getScale();
        int coordX = BoxUtils.getBoxOffX(feature, (int) (x / scale), feature.getWidth());
        int coordY = BoxUtils.getBoxOffY(feature, (int) (y / scale), feature.getHeight());
        render(feature, coordX, coordY);
    }

    private final Minecraft mc = Minecraft.getMinecraft();
    private final KeyUtils keyUtils = new KeyUtils(this);

    private Builder builder;

    private Builder createLayout() {
        Key none = new Key.FillerKey();
        Key keyW = Key.create(mc.gameSettings.keyBindForward, this);
        Key keyA = Key.create(mc.gameSettings.keyBindLeft, this);
        Key keyS = Key.create(mc.gameSettings.keyBindBack, this);
        Key keyD = Key.create(mc.gameSettings.keyBindRight, this);

        Key keyLM = Key.create(mc.gameSettings.keyBindAttack, this).setLeftMouse();
        Key keyRM = Key.create(mc.gameSettings.keyBindUseItem, this).setRightMouse();
        Key keySpace = Key.create(mc.gameSettings.keyBindJump, this).setSpacebar();

        return new Builder()
                .setWidth(60)
                .setGapSize(2)
                .addRow(none, keyW, none)
                .addRow(keyA, keyS, keyD)
                .addRow(keyLM, keyRM)
                .addRow(keySpace)
                .build();
    }

    private float keyOffset;

    public void render(Feature display, int x, int y) {
        if (builder == null) builder = createLayout();

        display.setWidth(builder.width);
        display.setHeight(builder.height);

        float scale = display.getScale();

        GL11.glPushMatrix();
        GL11.glScaled(scale, scale, 0.0);
        GL11.glTranslated(x, y, 0.0);

        for (Rows row : builder.rows) {
            GL11.glPushMatrix();
            for (Key key : row.getKeys()) {
                key.render(row.keyWidth, row.getHeight());
                double offset = row.keyWidth + builder.gapSize;
                GL11.glTranslated(offset, 0, 0.0);
                keyOffset += offset / builder.width;
            }
            GL11.glPopMatrix();
            GL11.glTranslated(0.0, row.getHeight() + builder.gapSize, 0.0);
            keyOffset = 0.0F;
        }
        GL11.glScaled(Math.pow(scale, -1), Math.pow(scale, -1), 0.0);
        GL11.glPopMatrix();
    }

    public int getColor(double offset, boolean pressed) {
        int color;
        if (Feature.KEYSTROKES_CHROMA.isEnabled()) {
            if (pressed) {
                color = 0xFF000000;
            } else {
                long systemTime = 2000L;
                float speed = 2000F;

                float hue = System.currentTimeMillis() % systemTime / speed;
                hue -= (keyOffset + offset / builder.width) * 0.3;
                color = Color.HSBtoRGB(hue, 1.0F, 1.0F);
            }
        } else {
            color = new Color(255, 255, 255, 255).getRGB();
            if (pressed)
                color ^= 0x00FFFFFF;
        }
        return color;
    }

    public class Builder {

        private final List<Rows> rows = Lists.newArrayList();

        @Accessors(chain = true)
        @Setter
        private int width;
        @Getter
        private int height;
        @Accessors(chain = true)
        @Setter
        private int gapSize;

        public Builder addRow(Key... keys) {
            double keyWidth = (float) (width - gapSize * (keys.length - 1)) / keys.length;
            double height = Arrays.stream(keys).mapToDouble(Key::getHeight).max().orElse(0.0);
            rows.add(new Rows(keys, keyWidth, height));
            return this;
        }

        public Builder build() {
            height = (int) (rows.stream().mapToDouble(Rows::getHeight).sum() + gapSize * (rows.size() - 1));
            return this;
        }

    }

    @Getter
    @RequiredArgsConstructor
    public class Rows {

        private final Key[] keys;
        private final double keyWidth;
        private final double height;

    }

}
