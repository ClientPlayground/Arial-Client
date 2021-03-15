package me.kaimson.arialclient.event.events;

import lombok.RequiredArgsConstructor;
import me.kaimson.arialclient.event.Event;

@RequiredArgsConstructor
public class KeyTypeEvent extends Event {

    public final int keycode;

}
