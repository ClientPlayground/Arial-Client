package me.kaimson.arialclient;

import com.google.common.collect.Lists;
import lombok.Getter;
import me.kaimson.arialclient.config.Config;
import me.kaimson.arialclient.discord.RichPresence;
import me.kaimson.arialclient.event.EventHandler;
import me.kaimson.arialclient.event.TypeEvent;
import me.kaimson.arialclient.event.events.InitializationEvent;
import me.kaimson.arialclient.event.events.KeyTypeEvent;
import me.kaimson.arialclient.event.events.SettingsRegisteredEvent;
import me.kaimson.arialclient.event.events.TickEvent;
import me.kaimson.arialclient.features.renderer.RenderManager;
import me.kaimson.arialclient.gui.ui.GuiHudEditor;
import me.kaimson.arialclient.utils.KeyBind;
import me.kaimson.arialclient.utils.Perspective;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.util.List;

public class Client {

    @Getter
    private static Client INSTANCE;
    public final static String name = "Arial Client";
    public final static String resourceLocation = "arialclient";
    public final static File dir = new File(Minecraft.getMinecraft().mcDataDir, "Arial Client");

    private final Minecraft mc = Minecraft.getMinecraft();
    private final List<KeyBind> keyBinds = Lists.newArrayList();

    private final static Logger logger = (Logger) LogManager.getLogger();

    public Client() {
        INSTANCE = this;
        EventHandler.register(this);
    }

    @TypeEvent
    private void onPreInit(InitializationEvent.Pre e) {
        Config.INSTANCE.loadConfig();
        RichPresence.INSTANCE.init();
        EventHandler.register(new Perspective());
    }

    @TypeEvent
    private void onInit(InitializationEvent e) {
        RenderManager.INSTANCE.init();
    }

    @TypeEvent
    private void onPostInit(InitializationEvent.Post e) {
    }

    @TypeEvent
    private void onSettingsRegistered(SettingsRegisteredEvent e) {
        addKeyBind(new KeyBind("Opens the settings menu", Keyboard.KEY_RSHIFT, Client.name, () -> mc.displayGuiScreen(new GuiHudEditor())));
        addKeyBind(new KeyBind("Freelook", Keyboard.KEY_LMENU, Client.name, Perspective::pressed));
    }

    @TypeEvent
    private void onKeyTyped(KeyTypeEvent e) {
        if (Minecraft.getMinecraft().currentScreen == null)
            for (KeyBind keyBind : keyBinds)
                if (keyBind.getKeycode() == e.keycode)
                    keyBind.onPress.run();
    }

    @TypeEvent
    private void onClientTick(TickEvent.ClientTick e) {
        if (e.phase != TickEvent.Phase.END) return;
    }

    @TypeEvent
    private void onRenderTick(TickEvent.RenderTick.Overlay e) {
        if (e.phase != TickEvent.Phase.START || (mc.gameSettings.showDebugInfo)) return;
        RenderManager.INSTANCE.render();
    }

    private void addKeyBind(KeyBind keyBind) {
        keyBinds.add(keyBind);
        mc.gameSettings.keyBindings = ArrayUtils.add(mc.gameSettings.keyBindings, keyBind.keyBinding);
    }

    public static void info(Object obj, Object... objs) {
        logger.info(String.valueOf(obj), objs);
    }

    public static void error(Object obj, Object... objs) {
        logger.error(String.valueOf(obj), objs);
    }

}
