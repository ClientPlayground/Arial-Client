package me.kaimson.arialclient.features.renderer;

import me.kaimson.arialclient.features.Feature;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class ToggleSprintRenderer extends DefaultModuleRender {

    @Override
    public void render(Feature feature, float x, float y) {
        if (Feature.TOGGLE_SPRINT_SHOW_TEXT.isEnabled() && sprint)
            render(feature, "[Sprinting (Toggled)]", x, y);
        onTick();
    }

    private boolean sprint;
    private final Minecraft mc = Minecraft.getMinecraft();

    public void onTick() {
        KeyBinding keyBinding = mc.gameSettings.keyBindSprint;;

        if (keyBinding.isPressed()) {
            sprint = !sprint;
            if (!sprint)
                if (keyBinding.getKeyCode() > 0)
                    KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), Keyboard.isKeyDown(keyBinding.getKeyCode()));
        }
        if (sprint) {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
        }
    }

}
