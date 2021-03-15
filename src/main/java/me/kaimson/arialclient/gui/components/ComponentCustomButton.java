package me.kaimson.arialclient.gui.components;

public class ComponentCustomButton extends ComponentButton {

    private final Consumer<ComponentButton, Integer, Integer, Float> drawBackground;

    public ComponentCustomButton(int x, int y, int width, int height, Consumer<ComponentButton, Integer, Integer, Float> drawBackground, java.util.function.Consumer<ComponentButton> consumer) {
        super(x, y, width, height, "", consumer);
        this.drawBackground = drawBackground;
    }

    public ComponentCustomButton(int x, int y, int width, int height, String text, Consumer<ComponentButton, Integer, Integer, Float> drawBackground, java.util.function.Consumer<ComponentButton> consumer) {
        super(x, y, width, height, text, consumer);
        this.drawBackground = drawBackground;
    }

    @Override
    protected void renderBackground(int xOffset, int yOffset, int mouseX, int mouseY, float partialTicks) {
        drawBackground.apply(this, xOffset, yOffset, partialTicks);
    }

    @FunctionalInterface
    public interface Consumer<A, B, C, D> {
        void apply(A a, B b, C c, D d);
    }

}
