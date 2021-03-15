package me.kaimson.arialclient.config;

import com.google.common.collect.Maps;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Cleanup;
import lombok.Getter;
import me.kaimson.arialclient.Client;
import me.kaimson.arialclient.config.utils.Position;
import me.kaimson.arialclient.features.Feature;
import me.kaimson.arialclient.features.annotations.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class Config {

    public final static Config INSTANCE = new Config();

    private final File configFile = new File(Client.dir, "settings.json");

    private final Set<Feature> enabled = EnumSet.noneOf(Feature.class);
    private final Map<Feature, Position> positions = Maps.newEnumMap(Feature.class);
    private final Map<Feature, Position.AnchorPoint> anchorPoints = Maps.newEnumMap(Feature.class);
    private final Map<Feature, Float> scales = Maps.newEnumMap(Feature.class);
    @Getter
    private final Map<Feature, Object> customs = Maps.newEnumMap(Feature.class);

    private final Minecraft mc = Minecraft.getMinecraft();

    public void saveConfig() {
        if (!configFile.exists() || !Client.dir.exists()) {
            Client.dir.mkdirs();
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        JsonObject configFileJson = new JsonObject();
        try {
            @Cleanup BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
            for (Feature feature : Feature.values()) {
                if (feature.isModule() || (feature.hasTranslation() && !feature.isModuleSetting() && !feature.hasGlobal())) {
                    JsonObject moduleObject = new JsonObject();
                    moduleObject.addProperty("enabled", feature.isEnabled());
                    if (feature.isModule()) {
                        moduleObject.addProperty("posX", getRelativePosition(feature).getX());
                        moduleObject.addProperty("posY", getRelativePosition(feature).getY());
                        moduleObject.addProperty("scale", getScale(feature));
                        moduleObject.addProperty("anchorPoint", getAnchorPoint(feature).getId());
                    }
                    if (feature.hasSettings()) {
                        JsonObject settingObject = new JsonObject();
                        for (Feature setting : feature.getSettings()) {
                            if (setting.isSettingSlider())
                                settingObject.addProperty(setting.name().substring(feature.name().length() + 1), (float) getCustoms().getOrDefault(setting, setting.getSettingSlider().current()));
                            else if (setting.hasGlobal()) {
                                if (setting.isGlobalSettingSlider())
                                    settingObject.addProperty(setting.name().substring("GLOBAL_".length()), (float) getCustoms().getOrDefault(setting, setting.getGlobalSettingSlider().current()));
                                else
                                    settingObject.addProperty(setting.name().substring("GLOBAL_".length()), setting.getAsInt());
                            } else {
                                if (setting.getSettingType() == Setting.Type.SWITCH || setting.getSettingType() == Setting.Type.VERSION)
                                    settingObject.addProperty(setting.name().substring(feature.name().length() + 1), setting.isEnabled());
                                else
                                    settingObject.addProperty(setting.name().substring(feature.name().length() + 1), setting.getAsInt());
                            }
                        }
                        moduleObject.add("settings", settingObject);
                    }
                    configFileJson.add(feature.name(), moduleObject);
                }
            }
            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(configFileJson.toString())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        JsonObject configFileJson = ConfigJson.INSTANCE.loadJsonFile(configFile);
        for (Feature feature : Feature.values()) {
            if (feature.isModule() || !feature.hasGlobal() && feature.hasTranslation() && !feature.isModuleSetting() && configFileJson.get(feature.name()) != null) {
                JsonObject moduleObject = configFileJson.get(feature.name()).getAsJsonObject();
                if (moduleObject.get("enabled") != null)
                    setEnabled(feature, moduleObject.get("enabled").getAsBoolean());
                if (feature.isModule()) {
                    if (moduleObject.get("posX") != null && moduleObject.get("posY") != null)
                        setPosition(feature, moduleObject.get("posX").getAsFloat(), moduleObject.get("posY").getAsFloat());
                    if (moduleObject.get("scale") != null)
                        setScale(feature, moduleObject.get("scale").getAsFloat());
                    if (moduleObject.get("anchorPoint") != null)
                        setAnchorPoint(feature, Position.AnchorPoint.fromId(moduleObject.get("anchorPoint").getAsInt()));
                }
                if (feature.hasSettings()) {
                    JsonObject settingObject = moduleObject.getAsJsonObject("settings");
                    for (Feature setting : feature.getSettings()) {
                        if (setting.isGlobalSetting() || setting.isGlobalSettingSlider() || setting.isDummy()) continue;
                        if (setting.isSettingSlider())
                            customs.put(setting, settingObject.get(setting.name().substring(feature.name().length() + 1)).getAsFloat());
                        else if (setting.getSettingType() == Setting.Type.SWITCH || setting.getSettingType() == Setting.Type.VERSION)
                            setEnabled(setting, settingObject.get(setting.name().substring(feature.name().length() + 1)).getAsBoolean());
                        else
                            getCustoms().put(setting, settingObject.get(setting.name().substring(feature.name().length() + 1)).getAsInt());
                    }
/*                            if (setting.isSettingSlider())
                                settingObject.addProperty(setting.name().substring(feature.name().length() + 1), setting.getSettingSlider().current());
                            else if (setting.isGlobal())
                                settingObject.addProperty(setting.name().substring("GLOBAL_".length()), setting.getAsInt());
                            else {
                                if (setting.getSetting().type() == Setting.Type.SWITCH)
                                    settingObject.addProperty(setting.name().substring(feature.name().length() + 1), setting.isEnabled());
                                else
                                    settingObject.addProperty(setting.name().substring(feature.name().length() + 1), setting.getAsInt());
                            }*/
                }
            }
        }
    }

    public void setEnabled(Feature feature, boolean enabled) {
        if (enabled) this.enabled.add(feature);
        else this.enabled.remove(feature);
    }

    public boolean isEnabled(Feature feature) {
        return enabled.contains(feature);
    }

    public void setPosition(Feature feature, float x, float y) {
        positions.put(feature, new Position(x, y));
    }

    public Position getRelativePosition(Feature feature) {
        if (positions.containsKey(feature)) {
            return positions.get(feature);
        } else {
            positions.put(feature, new Position(0, 0));
            anchorPoints.put(feature, Position.AnchorPoint.TOP_CENTER);
            return getRelativePosition(feature);
        }
    }

    public float getActualX(Feature feature) {
        return getAnchorPoint(feature).getX(new ScaledResolution(mc).getScaledWidth()) + getRelativePosition(feature).getX();
    }

    public float getActualY(Feature feature) {
        return getAnchorPoint(feature).getY(new ScaledResolution(mc).getScaledHeight()) + getRelativePosition(feature).getY();
    }

    public void setClosestAnchorPoint(Feature feature) {
        float actualX = getActualX(feature);
        float actualY = getActualY(feature);
        ScaledResolution sr = new ScaledResolution(mc);
        int maxX = sr.getScaledWidth();
        int maxY = sr.getScaledHeight();
        double shortestDistance = -1;
        Position.AnchorPoint closestAnchorPoint = Position.AnchorPoint.CENTER;
        for (Position.AnchorPoint anchorPoint : Position.AnchorPoint.values()) {
            double distance = Point2D.distance(actualX, actualY, anchorPoint.getX(maxX), anchorPoint.getY(maxY));
            if (shortestDistance == -1 || distance < shortestDistance) {
                closestAnchorPoint = anchorPoint;
                shortestDistance = distance;
            }
        }
        float x = actualX - closestAnchorPoint.getX(maxX);
        float y = actualY - closestAnchorPoint.getY(maxY);
        setAnchorPoint(feature, closestAnchorPoint);
        setPosition(feature, x, y);
    }

    public void setAnchorPoint(Feature feature, Position.AnchorPoint anchorPoint) {
        anchorPoints.put(feature, anchorPoint);
    }

    public Position.AnchorPoint getAnchorPoint(Feature feature) {
        return anchorPoints.getOrDefault(feature, Position.AnchorPoint.TOP_CENTER);
    }

    public void setScale(Feature feature, float scale) {
        scales.put(feature, scale);
    }

    public float getScale(Feature feature) {
        return scales.getOrDefault(feature, 1.0F);
    }

}
