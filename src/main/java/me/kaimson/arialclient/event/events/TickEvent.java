package me.kaimson.arialclient.event.events;

import lombok.RequiredArgsConstructor;
import me.kaimson.arialclient.event.Event;

@RequiredArgsConstructor
public class TickEvent extends Event {

    public final Phase phase;
    public final float renderTickTime;

    public static class RenderTick extends TickEvent {
        public RenderTick(Phase phase, float renderTickTime) {
            super(phase, renderTickTime);
        }

        public static class Overlay extends RenderTick {
            public Overlay(Phase phase, float renderTickTime) {
                super(phase, renderTickTime);
            }
        }

    }

    public static class ClientTick extends TickEvent {
        public ClientTick(Phase phase, float renderTickTime) {
            super(phase, renderTickTime);
        }
    }

    public enum Phase {
        START,
        END
    }

}
