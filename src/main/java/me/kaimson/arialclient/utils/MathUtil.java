package me.kaimson.arialclient.utils;

import net.minecraft.util.MathHelper;

public class MathUtil {
    private static final double[] a = new double[65536];
    private static final double[] b = new double[360];

    static {
        for (int i = 0; i < 65536; i++) {
            a[i] = Math.sin(i * 3.141592653589793D * 2.0D / 65536.0D);
        }
        for (int i = 0; i < 360; i++) {
            b[i] = Math.sin(Math.toRadians(i));
        }
    }

    public static double getAngle(int paramInt) {
        paramInt %= 360;
        return b[paramInt];
    }

    public static double getRightAngle(int paramInt) {
        paramInt += 90;
        paramInt %= 360;
        return b[paramInt];
    }

    private static float snapToStep(float value, float valueStep)
    {
        if (valueStep > 0.0F)
        {
            value = valueStep * (float) Math.round(value / valueStep);
        }

        return value;
    }
    public static float normalizeValue(float value, float valueMin, float valueMax, float valueStep)
    {
        return MathHelper.clamp_float((snapToStepClamp(value, valueMin, valueMax, valueStep) - valueMin) / (valueMax - valueMin), 0.0F, 1.0F);
    }

    private static float snapToStepClamp(float value, float valueMin, float valueMax, float valueStep)
    {
        value = snapToStep(value, valueStep);
        return MathHelper.clamp_float(value, valueMin, valueMax);
    }

    public static float denormalizeValue(float value, float valueMin, float valueMax, float valueStep)
    {
        return snapToStepClamp(valueMin + (valueMax - valueMin) * MathHelper.clamp_float(value, 0.0F, 1.0F), valueMin, valueMax, valueStep);
    }
}
