package me.kaimson.arialclient.features.renderer;

import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import me.kaimson.arialclient.features.Feature;

import java.util.Map;

public class RenderManager {

    public final static RenderManager INSTANCE = new RenderManager();

    private final Map<Feature, IModuleRender> renderer = Maps.newEnumMap(Feature.class);

    @SneakyThrows
    public void init() {
        for (Feature feature : Feature.values()) {
            if (feature.isModule()) {
                renderer.put(feature, feature.getModuleRenderer().newInstance());
            }
        }
    }

    public void render() {
        renderer.keySet().stream().filter(Feature::isEnabled).forEach(this::render);
    }

    public void render(Feature feature) {
        renderer.get(feature).render(feature, feature.getX(), feature.getY());
    }

}
