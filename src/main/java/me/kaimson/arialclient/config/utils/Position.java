package me.kaimson.arialclient.config.utils;

import lombok.Getter;
import org.apache.commons.lang3.mutable.MutableFloat;

public class Position {

    private final MutableFloat x;
    private final MutableFloat y;

    public Position(float x, float y) {
        this.x = new MutableFloat(x);
        this.y = new MutableFloat(y);
    }

    public void setX(float x) {
        this.x.setValue(x);
    }

    public float getX() {
        return this.x.getValue();
    }

    public void setY(float y) {
        this.y.setValue(y);
    }

    public float getY() {
        return this.y.getValue();
    }

    public enum AnchorPoint {
        TOP_LEFT(0),
        TOP_CENTER(1),
        TOP_RIGHT(2),
        BOTTOM_LEFT(3),
        BOTTOM_CENTER(4),
        BOTTOM_RIGHT(5),
        CENTER_LEFT(6),
        CENTER(7),
        CENTER_RIGHT(8);

        @Getter
        private final int id;

        AnchorPoint(int id) {
            this.id = id;
        }

        public static AnchorPoint fromId(int id) {
            for (AnchorPoint ap : AnchorPoint.values())
                if (ap.getId() == id)
                    return ap;
            return null;
        }

        public int getX(int maxX) {
            int x;
            switch (this) {
                case TOP_RIGHT:
                case BOTTOM_RIGHT:
                case CENTER_RIGHT:
                    x = maxX;
                    break;

                case BOTTOM_CENTER:
                case CENTER:
                case TOP_CENTER:
                    x = maxX / 2;
                    break;

                default:
                    x = 0;
            }
            return x;
        }

        public int getY(int maxY) {
            int y;
            switch (this) {
                case BOTTOM_LEFT:
                case BOTTOM_CENTER:
                case BOTTOM_RIGHT:
                    y = maxY;
                    break;

                case CENTER:
                case CENTER_LEFT:
                case CENTER_RIGHT:
                    y = maxY / 2;
                    break;

                default:
                    y = 0;
            }
            return y;
        }
    }

}
