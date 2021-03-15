package me.kaimson.arialclient.utils;

import me.kaimson.arialclient.features.Feature;

public class BoxUtils {

    public static int getBoxOffX(Feature feature, int x, int width) {
        switch (feature.getAnchorPoint()) {
            case TOP_CENTER:
            case CENTER:
            case BOTTOM_CENTER:
                return x - width / 2;
            case CENTER_RIGHT:
            case BOTTOM_RIGHT:
            case TOP_RIGHT:
                return x - width;
        }
        return x;
    }

    public static int getBoxOffY(Feature feature, int y, int height) {
        switch (feature.getAnchorPoint()) {
            case CENTER_LEFT:
            case CENTER:
            case CENTER_RIGHT:
                return y - height / 2;
            case BOTTOM_RIGHT:
            case BOTTOM_CENTER:
            case BOTTOM_LEFT:
                return y - height;
        }
        return y;
    }

}
