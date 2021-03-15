package me.kaimson.arialclient.features.renderer;

import me.kaimson.arialclient.features.Feature;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeRenderer extends DefaultModuleRender {

    @Override
    public void render(Feature feature, float x, float y) {
        render(feature, feature.getFormat(new SimpleDateFormat("hh:mm a").format(new Date()), false), x, y);
    }
}
