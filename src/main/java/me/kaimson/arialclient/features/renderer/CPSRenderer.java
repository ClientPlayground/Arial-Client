package me.kaimson.arialclient.features.renderer;

import com.google.common.collect.Lists;
import me.kaimson.arialclient.features.Feature;
import org.lwjgl.input.Mouse;

import java.util.Queue;

public class CPSRenderer extends DefaultModuleRender {

    @Override
    public void render(Feature feature, float x, float y) {
        render(feature, feature.getFormat(getCPS()), x, y);
    }

    private final Counter leftCounter = new Counter();
    private final Counter rightCounter = new Counter();

    private boolean lWasDown;
    private boolean rWasDown;

    public int getCPS() {
        Mouse.poll();
        boolean downNow = Mouse.isButtonDown(0);
        if (downNow != lWasDown && downNow)
            leftCounter.onClick();
        lWasDown = downNow;
        downNow = Mouse.isButtonDown(1);
        if (downNow != rWasDown && downNow)
            rightCounter.onClick();
        rWasDown = downNow;
        return Math.max(leftCounter.getCPS(), rightCounter.getCPS());
    }

    private static class Counter {
        private final Queue<Long> clicks = Lists.newLinkedList();

        public Counter onClick() {
            clicks.add(System.currentTimeMillis() + 1000L);
            return this;
        }

        public int getCPS() {
            long time = System.currentTimeMillis();
            while (!clicks.isEmpty() && clicks.peek() < time)
                clicks.remove();
            return clicks.size();
        }
    }

}
