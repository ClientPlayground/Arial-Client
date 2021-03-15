package me.kaimson.arialclient.blur;

import lombok.RequiredArgsConstructor;
import me.kaimson.arialclient.Client;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.util.ResourceLocation;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

@RequiredArgsConstructor
public class BlurResource implements IResource {

    private final float BLUR_RADIUS;

    @Override
    public InputStream getInputStream()
    {
        StringBuilder data = new StringBuilder();
        Scanner scan = new Scanner(BlurResource.class.getResourceAsStream("/assets/minecraft/" + Client.resourceLocation + "/shaders/post/fade_in_blur.json"));

        try
        {
            while (scan.hasNextLine())
            {
                data.append(scan.nextLine().replaceAll("@radius@", Integer.toString((int) BLUR_RADIUS))).append("\n");
            }
        }
        finally
        {
            scan.close();
        }

        return new ByteArrayInputStream(data.toString().getBytes());
    }

    @Override
    public boolean hasMetadata()
    {
        return false;
    }

    @Override
    public <T extends IMetadataSection> T getMetadata(String p_110526_1_)
    {
        return null;
    }

    @Override
    public String getResourcePackName()
    {
        return null;
    }

    @Override
    public ResourceLocation getResourceLocation()
    {
        return null;
    }
}
