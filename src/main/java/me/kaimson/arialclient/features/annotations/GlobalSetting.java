package me.kaimson.arialclient.features.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobalSetting {

    Setting.Type type();
    Target target();

    enum Target {
        RENDER,
        DEFAULT_RENDER,
        SETTING,
        SETTING_SLIDER,
    }

}
