package me.kaimson.arialclient.features.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobalSettingSlider {

    float min();
    float max();
    float step();
    float current();
    GlobalSetting.Target target();

}
