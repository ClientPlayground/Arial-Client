package me.kaimson.arialclient.gui.ui;

import lombok.RequiredArgsConstructor;
import me.kaimson.arialclient.gui.screen.GuiScreen;

@RequiredArgsConstructor
public class SettingBase extends GuiScreen {

    protected final int relativeHeight = 90;
    protected final int gap = 5;
    protected final int columns = 4;

    protected final net.minecraft.client.gui.GuiScreen parentScreen;

    protected int getWidth(int boxWidth, int gap) {
        return (boxWidth + gap) * 5;
    }

    protected int getMaxWidth(int boxWidth, int gap) {
        return (boxWidth + gap) * columns;
    }

}
