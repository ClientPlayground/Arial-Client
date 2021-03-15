package me.kaimson.arialclient.blur;

import lombok.RequiredArgsConstructor;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class BlurResourceManager implements IResourceManager {

    private final float BLUR_RADIUS;

    @Override
    public Set<String> getResourceDomains()
    {
        return null;
    }

    @Override
    public IResource getResource(ResourceLocation location) throws IOException
    {
        return new BlurResource(BLUR_RADIUS);
    }

    @Override
    public List<IResource> getAllResources(ResourceLocation location) throws IOException
    {
        return null;
    }
}
