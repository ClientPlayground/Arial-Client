package me.kaimson.arialclient.features.annotations;

import me.kaimson.arialclient.features.Feature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SettingMode {

    Setting.Type type = Setting.Type.SWITCH;
    Feature[] modes();

}