package me.kaimson.arialclient.gui.components.settings;

import me.kaimson.arialclient.features.Feature;
import me.kaimson.arialclient.gui.GuiUtils;
import me.kaimson.arialclient.gui.components.Component;

public abstract class ComponentSetting extends Component {

    protected final Feature setting;

    public ComponentSetting(int x, int y, int width, int height, Feature setting) {
        super(x, y, width, height, true);
        this.setting = setting;
    }

    public void render(float xOffset, float yOffset) {
        drawText(xOffset, yOffset);
    }

    private void drawText(float xOffset, float yOffset) {
        GuiUtils.drawString(setting.hasTranslation() ? setting.getTranslation() : setting.name(), x + xOffset, y + yOffset);
    }

}
