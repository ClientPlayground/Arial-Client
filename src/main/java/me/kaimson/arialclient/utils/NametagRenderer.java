package me.kaimson.arialclient.utils;

import me.kaimson.arialclient.Client;
import me.kaimson.arialclient.gui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class NametagRenderer {

    public static void renderTag(String tag, double x, double y, double z)
    {
        Minecraft mc = Minecraft.getMinecraft();
        Entity entity = mc.getRenderManager().livingPlayer;

        if (entity != null && entity.getDisplayName() != null)
        {
//        String displayTag = (entity.getDisplayName().getFormattedText());
            String displayTag = " " + tag;
            FontRenderer fontrenderer = mc.fontRendererObj;
            float f = 1.6F;
            float f1 = 0.016666668F * f;
            RenderManager renderManager = mc.getRenderManager();
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)x + 0.0F, (float)y + entity.height + 0.5F, (float)z);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);

            float viewX = renderManager.playerViewX;
            float viewY = renderManager.playerViewY;
            if (Perspective.isEnabled()) {
                viewX = Perspective.cameraPitch;
                viewY = Perspective.cameraYaw;
            }

            GlStateManager.rotate(-viewY, 0.0F, 1.0F, 0.0F);

            if (mc.gameSettings.thirdPersonView == 2)
                GlStateManager.rotate(-viewX, 1.0F, 0.0F, 0.0F);
            else
                GlStateManager.rotate(viewX, 1.0F, 0.0F, 0.0F);

            GlStateManager.scale(-f1, -f1, f1);

            if (entity.isSneaking())
                GlStateManager.translate(0.0F, 9.374999F, 0.0F);

            GlStateManager.disableLighting();
            GlStateManager.depthMask(false);
            GlStateManager.disableDepth();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.disableTexture2D();
            int i = fontrenderer.getStringWidth(displayTag) / 2;
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            worldrenderer.pos((-i - 1), -1.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            worldrenderer.pos((-i - 1), 8.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            worldrenderer.pos((i + 1), 8.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            worldrenderer.pos((i + 1), -1.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            tessellator.draw();
            GlStateManager.enableTexture2D();

            if (!entity.isSneaking())
            {
                fontrenderer.drawString(displayTag, -fontrenderer.getStringWidth(displayTag) / 2, 0, 553648127);
                GlStateManager.enableDepth();
                GlStateManager.depthMask(true);
                fontrenderer.drawString(displayTag, -fontrenderer.getStringWidth(displayTag) / 2, 0, -1);
            }
            else
            {
                fontrenderer.drawString(displayTag, -fontrenderer.getStringWidth(displayTag) / 2, 0, 553648127);
                GlStateManager.enableDepth();
                GlStateManager.depthMask(true);
                fontrenderer.drawString(displayTag, -fontrenderer.getStringWidth(displayTag) / 2, 0, 553648127);
            }

            GlStateManager.enableBlend();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            mc.getTextureManager().bindTexture(new ResourceLocation(Client.resourceLocation + "/logo.png"));
            int b = 9;
            GuiUtils.drawModalRectWithCustomSizedTexture(-fontrenderer.getStringWidth(displayTag) / 2 - fontrenderer.getCharWidth(' ') * 2, -1, b, b, b, b, b, b);

            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.popMatrix();
        }
    }

    public static void render(double x, double y, double z)
    {
        Minecraft mc = Minecraft.getMinecraft();
        Scoreboard scoreboard = mc.thePlayer.getWorldScoreboard();
        ScoreObjective scoreobjective = scoreboard.getObjectiveInDisplaySlot(2);

        if (scoreobjective != null && !mc.thePlayer.isSneaking())
        {
            Score score = scoreboard.getValueFromObjective(mc.thePlayer.getCommandSenderName(), scoreobjective);
            renderTag(score.getScorePoints() + " " + scoreobjective.getDisplayName(), x, y, z);
            y += (float)mc.fontRendererObj.FONT_HEIGHT * 1.15F * 0.02666667;
        }
        renderTag((mc.thePlayer.getCustomNameTag() == null || mc.thePlayer.getCustomNameTag().isEmpty()) ? mc.thePlayer.getDisplayName().getFormattedText() : mc.thePlayer.getCustomNameTag(), x, y, z);
    }

}
