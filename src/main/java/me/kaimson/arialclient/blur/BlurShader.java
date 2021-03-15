package me.kaimson.arialclient.blur;

import me.kaimson.arialclient.Client;
import me.kaimson.arialclient.event.EventHandler;
import me.kaimson.arialclient.event.TypeEvent;
import me.kaimson.arialclient.event.events.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Map;

public class BlurShader {

    public final static BlurShader INSTANCE = new BlurShader();

    private final ResourceLocation SHADER = new ResourceLocation(Client.resourceLocation, "shaders/post/fade_in_blur.json");

    private List<Shader> listShaders;
    private Map resourceManager;

    private long start;

    private final Minecraft mc = Minecraft.getMinecraft();

    public BlurShader() {
        EventHandler.register(this);
    }

    public void onGuiOpen() {
        onGuiOpen(5.0F);
    }

    public void onGuiOpen(float BLUR_RADIUS) {
        if (resourceManager == null)
            resourceManager = ((SimpleReloadableResourceManager)mc.getResourceManager()).domainResourceManagers;
        if (!resourceManager.containsKey(Client.resourceLocation))
            resourceManager.put(Client.resourceLocation, new BlurResourceManager(BLUR_RADIUS));
        if (listShaders == null)
            listShaders = ShaderGroup.INSTANCE.listShaders;

        if (mc.theWorld != null)
            if (!mc.entityRenderer.isShaderActive()) {
                mc.entityRenderer.loadShader(SHADER);
                start = System.currentTimeMillis();
            }
    }

    public void onGuiClose() {
        if (mc.theWorld != null)
            if (mc.entityRenderer.isShaderActive()) {
                mc.entityRenderer.stopUseShader();
            }
    }

    @TypeEvent
    private void onRenderTick(TickEvent.RenderTick e) {
        if (mc.currentScreen != null && mc.entityRenderer.isShaderActive()) {
            for (Shader s : mc.entityRenderer.getShaderGroup().listShaders) {
                ShaderUniform su = s.getShaderManager().getShaderUniform("Progress");
                if (su != null)
                    su.set(getProgress());
            }
        }
    }

    private float getProgress() {
        return Math.min((float)(System.currentTimeMillis() - start) / 10, 1.0F);
    }

}
