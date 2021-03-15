package me.kaimson.arialclient.features.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Setting {

    Type type() default Type.SWITCH;

    enum Type {
        SWITCH,
        COLOR,
        SLIDER,
        VERSION,
        MODE,
    }

}
