package me.kaimson.arialclient.utils;

import lombok.Getter;
import me.kaimson.arialclient.event.TypeEvent;
import me.kaimson.arialclient.event.events.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class Perspective {

    public static KeyBinding key;

    public static float cameraYaw;
    public static float cameraPitch;

    @Getter
    public static boolean enabled = false;

    @TypeEvent
    public void onClientTick(TickEvent.ClientTick e)
    {
        if (!Minecraft.getMinecraft().inGameHasFocus) return;

        if (isEnabled())
        {
            if (!(Minecraft.getMinecraft().gameSettings.thirdPersonView > 0))
            {
                Minecraft.getMinecraft().gameSettings.thirdPersonView = 0;
                enabled = !enabled;
            }
        }
    }

    public static void pressed() {
        if (!Minecraft.getMinecraft().inGameHasFocus) return;
        Minecraft mc = Minecraft.getMinecraft();
        cameraYaw = mc.thePlayer.rotationYaw;
        cameraPitch = mc.thePlayer.rotationPitch;
        enabled = !enabled;

        mc.gameSettings.thirdPersonView = enabled ? 1 : 0;
    }

}
