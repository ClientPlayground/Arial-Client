package me.kaimson.arialclient.event.events;

import lombok.RequiredArgsConstructor;
import me.kaimson.arialclient.event.Event;
import net.minecraft.client.gui.GuiScreen;

@RequiredArgsConstructor
public class GuiScreenEvent extends Event {

    public final GuiScreen screen;

    public static class Open extends GuiScreenEvent {
        public Open(GuiScreen screen) {
            super(screen);
        }
    }

}
