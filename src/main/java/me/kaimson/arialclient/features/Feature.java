package me.kaimson.arialclient.features;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import me.kaimson.arialclient.config.Config;
import me.kaimson.arialclient.config.utils.Position;
import me.kaimson.arialclient.features.annotations.*;
import me.kaimson.arialclient.features.renderer.*;
import me.kaimson.arialclient.features.renderer.keystrokes.KeystrokesRenderer;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public enum Feature {

    /** ###################
     *  # Global Settings #
     *  ################### */
    @Translation(key = "global.text_color", description = "Text Color")
    @GlobalSetting(type = Setting.Type.COLOR, target = GlobalSetting.Target.DEFAULT_RENDER)
    GLOBAL_TEXT_COLOR,

    @Translation(key = "global.scale", description = "Scale")
    @GlobalSettingSlider(min = 0.1F, max = 2.0F, step = 0.1F, current = 1.0F, target = GlobalSetting.Target.RENDER)
    GLOBAL_SCALE,

    /** ###################
     *  # Rendering items #
     *  ################### */
    @Translation(key = "fps", description = "FPS")
    @Render(renderer = FPSRenderer.class) FPS,

    @Translation(key = "cps", description = "CPS")
    @Render(renderer = CPSRenderer.class) CPS,

    @Translation(key = "keystrokes", description = "Keystrokes")
    @Render(renderer = KeystrokesRenderer.class) KEYSTROKES,

    @Translation(key = "togglesprint", description = "Toggle Sprint")
    @Render(renderer = ToggleSprintRenderer.class) TOGGLE_SPRINT,

    @Translation(key = "coords", description = "Coordinates")
    @Render(renderer = CoordinatesRenderer.class) COORDINATES,

    @Translation(key = "ping", description = "Ping")
    @Render(renderer = PingRenderer.class) PING,

    @Translation(key = "serverAddress", description = "Server Address", overwrite = "")
    @Render(renderer = ServerAddressRenderer.class) SERVER_ADDRESS,

    @Translation(key = "time", description = "Time Display")
    @Render(renderer = TimeRenderer.class) TIME_DISPLAY,

    @Translation(key = "potioneffects", description = "Potion Effects")
    @Render(renderer = PotionRenderer.class) POTION_EFFECTS,

    @Translation(key = "armorstatus", description = "Armor Status")
    @Render(renderer = ArmorStatus.class) ARMOR_STATUS,

    /** ###############
     *  # Event items #
     *  ############### */
    @Translation(key = "autogg", description = "AutoGG")
    AUTOGG,

    @Translation(key = "perspective", description = "Freelook")
    PERSPECTIVE,

    @Translation(key = "scoreboard", description = "Scoreboard")
    SCOREBOARD,

    @Translation(key = "nametag", description = "Show own nametag")
    SHOW_OWN_NAMETAG,

    @Translation(key = "animations", description = "Animations")
    ANIMATIONS,

    @Translation(key = "performance.loading", description = "Faster World Loading")
    FASTER_WORLD_LOADING,

    @Translation(key = "crosshair", description = "Custom Crosshair")
    CROSSHAIR,

    /** #################
     *  # Setting items #
     *  ################# */
    @Translation(key = "togglesprint.show_text", description = "Show Text")
    @Setting TOGGLE_SPRINT_SHOW_TEXT,

    @Translation(key = "keystrokes.background_color", description = "Background Color")
    @Setting(type = Setting.Type.COLOR) KEYSTROKES_BACKGROUND_COLOR,

    @Translation(key = "keystrokes.pressed.background_color", description = "Pressed Background Color")
    @Setting(type = Setting.Type.COLOR) KEYSTROKES_PRESSED_BACKGROUND_COLOR,

    @Translation(key = "keystrokes.fade_time", description = "Fade Time")
    @SettingSlider(min = 0.0F, max = 500.0F, step = 1.0F, current = 250.0F) KEYSTROKES_FADE_TIME,

    @Translation(key = "keystrokes.outline", description = "Outline")
    @Setting KEYSTROKES_OUTLINE,

    @Translation(key = "chroma", description = "Chroma")
    @Setting KEYSTROKES_CHROMA,

    @Translation(key = "crosshair.mode.circle", description = "Circle")
    @Dummy CROSSHAIR_MODE_CIRCLE,
    @Translation(key = "crosshair.mode.square", description = "Square")
    @Dummy CROSSHAIR_MODE_SQUARE,
    @Translation(key = "crosshair.mode.cross", description = "Cross")
    @Dummy CROSSHAIR_MODE_CROSS,

    @Translation(key = "crosshair.mode", description = "Mode")
    @SettingMode(modes = {
        CROSSHAIR_MODE_CIRCLE,
        CROSSHAIR_MODE_SQUARE,
        CROSSHAIR_MODE_CROSS
    }) CROSSHAIR_MODE,

    @Translation(key = "crosshair.size", description = "Size")
    @SettingSlider(min = 0.1F, max = 5.0F, step = 0.1F, current = 1.0F) CROSSHAIR_SIZE,

    @Translation(key = "crosshair.rendergap", description = "Gap")
    @SettingSlider(min = 0.1F, max = 5.0F, step = 0.1F, current = 1.0F) CROSSHAIR_RENDERGAP,

    @Translation(key = "crosshair.thickness", description = "Thickness")
    @SettingSlider(min = 0.5F, max = 2.0F, step = 0.5F, current = 1.0F) CROSSHAIR_THICKNESS,

    @Translation(key = "crosshair.color", description = "Color")
    @Setting(type = Setting.Type.COLOR) CROSSHAIR_COLOR,

    @Translation(key = "animations.build", description = "Build")
    @Setting(type = Setting.Type.VERSION) ANIMATIONS_BUILD,
    @Translation(key = "animations.eat", description = "Eat")
    @Setting(type = Setting.Type.VERSION) ANIMATIONS_EAT,
    @Translation(key = "animations.blockhit", description = "Blockhit")
    @Setting(type = Setting.Type.VERSION) ANIMATIONS_BLOCKHIT,
    @Translation(key = "animations.rod", description = "Rod")
    @Setting(type = Setting.Type.VERSION) ANIMATIONS_ROD,
    @Translation(key = "animations.sword", description = "Sword")
    @Setting(type = Setting.Type.VERSION) ANIMATIONS_SWORD,
    @Translation(key = "animations.damage", description = "Damage")
    @Setting(type = Setting.Type.VERSION) ANIMATIONS_DAMAGE,
    @Translation(key = "animations.bow", description = "Bow")
    @Setting(type = Setting.Type.VERSION) ANIMATIONS_BOW,
    @Translation(key = "animations.swing", description = "Swing")
    @Setting(type = Setting.Type.VERSION) ANIMATIONS_SWING;

    @Getter @Setter
    private int width;
    @Getter @Setter
    private int height;

    Feature() {

    }

    public boolean isEnabled() {
        return Config.INSTANCE.isEnabled(this);
    }

    public float getX() {
        return Config.INSTANCE.getActualX(this);
    }

    public float getY() {
        return Config.INSTANCE.getActualY(this);
    }

    public Position.AnchorPoint getAnchorPoint() {
        return Config.INSTANCE.getAnchorPoint(this);
    }

    public float getScale() {
        return Config.INSTANCE.getScale(this);
    }

    public Class<? extends IModuleRender> getModuleRenderer() {
        return getAnnotation(Render.class).renderer();
    }

    public boolean isModule() {
        return hasAnnotation(Render.class);
    }

    public List<Feature> getSettings() {
        List<Feature> settings = Lists.newArrayList();
        for (Feature feature : Feature.values()) {
            if (feature == this) continue;
            if (feature.name().startsWith(this.name()) && feature.isModuleSetting() || feature.hasGlobal() && this.isTarget())
                settings.add(feature);
        }
        return settings;
    }

    public boolean hasSettings() {
        for (Feature feature : Feature.values()) {
            if (feature == this) continue;
            if (feature.name().startsWith(this.name()) && feature.isModuleSetting() || this.isTarget())
                return true;
        }
        return false;
    }

    public Setting getSetting() {
        return getAnnotation(Setting.class);
    }

    public SettingSlider getSettingSlider() {
        return getAnnotation(SettingSlider.class);
    }

    public SettingMode getSettingMode() {
        return getAnnotation(SettingMode.class);
    }

    public GlobalSetting getGlobalSetting() {
        return getAnnotation(GlobalSetting.class);
    }

    public GlobalSettingSlider getGlobalSettingSlider() {
        return getAnnotation(GlobalSettingSlider.class);
    }

    public boolean isGlobalSetting() {
        return hasAnnotation(GlobalSetting.class);
    }

    public boolean isGlobalSettingSlider() {
        return hasAnnotation(GlobalSettingSlider.class);
    }

    public boolean isModuleSetting() {
        return hasAnnotation(Setting.class) || hasAnnotation(SettingSlider.class) || hasAnnotation(SettingMode.class);
    }

    public boolean isSetting() {
        return hasAnnotation(Setting.class);
    }

    public boolean isSettingSlider() {
        return hasAnnotation(SettingSlider.class);
    }

    public boolean hasGlobal() {
        return hasAnnotation(GlobalSetting.class) || hasAnnotation(GlobalSettingSlider.class);
    }

    public boolean isDummy() {
        return hasAnnotation(Dummy.class);
    }

    public GlobalSetting.Target getGlobalTarget() {
        if (isGlobalSettingSlider())
            return getGlobalSettingSlider().target();
        else
            return getGlobalSetting().target();
    }

    public String getTranslation() {
        return getAnnotation(Translation.class).description();
    }

    public boolean hasTranslation() {
        return hasAnnotation(Translation.class);
    }

    public boolean isTarget() {
        for (Feature globals : Feature.values()) {
            if (!globals.hasGlobal()) continue;
            if ((globals.getGlobalTarget() == GlobalSetting.Target.DEFAULT_RENDER && this.isModule() && DefaultModuleRender.class.isAssignableFrom(getModuleRenderer())) ||
            globals.getGlobalTarget() == GlobalSetting.Target.RENDER && this.isModule())
                return true;
        }
        return false;
    }

    public Setting.Type getSettingType() {
        if (isSetting())
            return getSetting().type();
        else if (isSettingSlider())
            return Setting.Type.SLIDER;
        else if (hasAnnotation(GlobalSettingSlider.class))
            return Setting.Type.SLIDER;
        else if (hasAnnotation(SettingMode.class))
            return Setting.Type.MODE;
        else
            return getGlobalSetting().type();
    }

    public float[] getSliderValues() {
        if (isSettingSlider())
            return new float[] { getSettingSlider().min(), getSettingSlider().max(), getSettingSlider().step(), getSettingSlider().current() };
        else
            return new float[] { getGlobalSettingSlider().min(), getGlobalSettingSlider().max(), getGlobalSettingSlider().step(), getGlobalSettingSlider().current() };
    }

    public String getFormat(Object value) {
        return getFormat(value, true);
    }

    public String getFormat(Object value, boolean prefix) {
        return "[" + (prefix ? (this.getAnnotation(Translation.class).overwrite().equals("-") ? this.getTranslation() : this.getAnnotation(Translation.class).overwrite()) + ": " : "") + value + "]";
    }

    @SneakyThrows
    private <T extends Annotation> T getAnnotation(Class<T> annotation) {
        return this.getClass().getField(this.name()).getAnnotation(annotation);
    }

    @SneakyThrows
    private boolean hasAnnotation(Class<? extends Annotation> annotation) {
        return Arrays.stream(this.getClass().getField(this.name()).getAnnotations()).anyMatch(anno -> anno.annotationType() == annotation);
    }

    public Object get() {
        Object value;
        if (getSettingSlider() != null)
            value = getSettingSlider().current();
        else if (getSetting() != null && getSetting().type() == Setting.Type.SWITCH)
            value = isEnabled();
        else {
            if (isModuleSetting()) {
                switch (getSettingType()) {
                    case COLOR:
                        value = -1;
                        break;
                    case SWITCH:
                        value = false;
                        break;
                    case MODE:
                        value = 0;
                        break;
                    default:
                        value = null;
                }
            } else
                value = -1;
        }
        return Config.INSTANCE.getCustoms().getOrDefault(this, value);
    }

    public int getAsInt() {
        return (int) get();
    }

    public boolean getAsBoolean() {
        return (boolean) get();
    }

    public Object getOrDefault(Object value) {
        return Config.INSTANCE.getCustoms().getOrDefault(this, value);
    }

}
