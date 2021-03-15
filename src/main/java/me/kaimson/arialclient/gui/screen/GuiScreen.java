package me.kaimson.arialclient.gui.screen;

import com.google.common.collect.Lists;
import me.kaimson.arialclient.gui.components.Component;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.List;

public class GuiScreen extends net.minecraft.client.gui.GuiScreen {

    protected int xPosition;
    protected int yPosition;

    protected GuiScrolling scroll;

    protected final List<Component> components = Lists.newArrayList();

    @Override
    public void initGui() {
        super.initGui();
        this.components.clear();
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        if (scroll != null)
            scroll.handleMouseInput();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (scroll != null) {
            scroll.drawScroll(scroll.getScrollBarX(), scroll.getScrollBarX() + 3);
            scroll.handleDragging(mouseX, mouseY);
            scroll.drawScreen(mouseX, mouseY, partialTicks);
            yPosition = scroll.getAmountScrolled();
        }
        for (Component component : components) {
            if (component.shouldScissor) {
                GL11.glEnable(GL11.GL_SCISSOR_TEST);
                GlStateManager.translate(-xPosition, -yPosition, 0);
            }
            component.render(-xPosition, -yPosition, mouseX, mouseY, partialTicks);
            if (component.shouldScissor) {
                GL11.glDisable(GL11.GL_SCISSOR_TEST);
                GlStateManager.translate(xPosition, yPosition, 0);
            }
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        components.forEach(component -> component.mouseReleased(mouseX, mouseY, state));
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for (Component component : components)
            component.mouseClicked(mouseX, mouseY, mouseButton);
    }

    protected void registerScroll(GuiScrolling scroll) {
        this.scroll = scroll;
        this.scroll.registerScrollButtons(7, 8);
    }

}
