package me.kaimson.arialclient.event;

import lombok.Getter;
import lombok.Setter;

public class Cancellable extends Event
{
    @Getter @Setter
    private boolean cancelled;

}
