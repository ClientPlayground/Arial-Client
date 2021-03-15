package me.kaimson.arialclient.features.annotations;

import me.kaimson.arialclient.features.renderer.DefaultModuleRender;
import me.kaimson.arialclient.features.renderer.IModuleRender;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Render {

    Class<? extends IModuleRender> renderer() default DefaultModuleRender.class;

}
