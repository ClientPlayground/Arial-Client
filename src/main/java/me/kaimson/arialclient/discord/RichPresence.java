package me.kaimson.arialclient.discord;

/**
 * Fix yourself can't be fucked using a shit library.
 */

//import club.minnced.discord.rpc.DiscordEventHandlers;
//import club.minnced.discord.rpc.DiscordRPC;
//import club.minnced.discord.rpc.DiscordRichPresence;
import lombok.Setter;
import me.kaimson.arialclient.Client;
import me.kaimson.arialclient.event.EventHandler;

public class RichPresence {

    public Thread worker;
    private long currentTimestamp;
    private long endTimestamp;

    @Setter
    private String state;
    @Setter
    private String details;

//    private final DiscordRPC rpc = DiscordRPC.INSTANCE;
//    private final DiscordRichPresence presence = new DiscordRichPresence();

    private final RichPresenceEvents eventhandler = new RichPresenceEvents();

    public static final RichPresence INSTANCE = new RichPresence();

    public void init() {
//        EventHandler.register(eventhandler);
//        DiscordEventHandlers handlers = new DiscordEventHandlers();
//        handlers.ready = (user) -> Client.info("Discord Rich Presence is ready!");
//        rpc.Discord_Initialize("807516069379178567", handlers, true, "Test");
//        presence.startTimestamp = currentTimestamp == 0 ? currentTimestamp = System.currentTimeMillis() : currentTimestamp;
//        presence.state = state;
//        presence.details = details;
//        presence.largeImageKey = "ac2512";
//        presence.largeImageText = Client.name;
//        rpc.Discord_UpdatePresence(presence);
//        worker = new Thread(this::run, "Rich Presence");
//        worker.start();
    }

    private void run() {
//        while (worker != null && !worker.isInterrupted()) {
//            rpc.Discord_RunCallbacks();
//            presence.state = state;
//            presence.details = details;
//            updateTimestamp();
//            rpc.Discord_UpdatePresence(presence);
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException ignored) {}
//        }
    }

    private void updateTimestamp() {
//        if (presence.startTimestamp != currentTimestamp / 1000)
//            presence.startTimestamp = currentTimestamp / 1000;
//        if (presence.endTimestamp != endTimestamp / 1000)
//            presence.endTimestamp = endTimestamp / 1000;
    }

    public void shutdown() {
//        EventHandler.unregister(eventhandler);
//        rpc.Discord_Shutdown();
//        worker.interrupt();
//        worker = null;
    }

}
