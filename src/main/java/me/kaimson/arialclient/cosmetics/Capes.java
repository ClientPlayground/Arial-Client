package me.kaimson.arialclient.cosmetics;

import lombok.Getter;

import java.util.Arrays;

public enum Capes {

    EUAN_CRAFT("cloak_1", 704, 546, "c2e27a9c-80f0-46e2-a67d-971b704243ad"),
    MINECON("2016", 2048, 1024, "667f3c53-6f3e-4ac0-93d5-2065aefa1de6", "2f795e19-0b92-47ef-881c-e699d1f59e5a", "76730d7a-f612-4ee1-94ad-61f7b0697007", "ebd9c81b-4ac2-4651-9615-705ccc3caaae"),
    STAR("MOSHED-2021-2-16-20-46-47", 555, 410, "70c73509-f1a9-4f87-a346-1e6eebb660b4"),
    GUY("pfpixel3", 800, 800, "f8927223-6fa3-4a31-a470-e53af063983b"),
    SUN("solarcape", 704, 546, "b370750f-52fc-420c-a366-21048aba2e46");

    @Getter
    private final String fileName;
    @Getter
    private final int textureWidth;
    @Getter
    private final int textureHeight;
    @Getter
    private final String[] uuid;

    Capes(String fileName, int textureWidth, int textureHeight, String... uuid) {
        this.fileName = fileName;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.uuid = uuid;
    }

    public static Capes has(String uuid) {
        for (Capes cape : Capes.values())
            if (cape.getUuid().length > 0 && Arrays.asList(cape.getUuid()).contains(uuid))
                return cape;
        return null;
    }

}
