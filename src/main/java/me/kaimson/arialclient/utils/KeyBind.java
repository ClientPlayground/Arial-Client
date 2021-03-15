package me.kaimson.arialclient.utils;

import net.minecraft.client.settings.KeyBinding;

public class KeyBind {

    public final KeyBinding keyBinding;
    public final Runnable onPress;

    public KeyBind(String description, int keycode, String category, Runnable onPress) {
        this.keyBinding = new KeyBinding(description, keycode, category);
        this.onPress = onPress;
    }

    public int getKeycode() {
        return keyBinding.getKeyCode();
    }

}
