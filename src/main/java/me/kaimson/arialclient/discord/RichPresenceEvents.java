package me.kaimson.arialclient.discord;

import me.kaimson.arialclient.event.TypeEvent;
import me.kaimson.arialclient.event.events.GuiScreenEvent;
import me.kaimson.arialclient.features.Feature;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;

public class RichPresenceEvents {

    @TypeEvent
    private void onGuiOpen(GuiScreenEvent.Open e) {
/*        if (ReplayModReplay.getInstance().getReplayHandler() != null) return;*/

        if (e.screen instanceof GuiMainMenu || e.screen instanceof GuiMultiplayer) {
            RichPresence.INSTANCE.setState("Idle");
        } else if (e.screen == null) {
/*            if (Feature.DISCORD_INTEGRATION_SHOW_SERVER.isEnabled())
                RichPresence.INSTANCE.setState(Minecraft.getMinecraft().isIntegratedServerRunning() ? "Playing Singleplayer" : "Playing on " + Minecraft.getMinecraft().getCurrentServerData().serverIP);
            else*/
                RichPresence.INSTANCE.setState("Playing Minecraft 1.8.9");
        }
    }

}