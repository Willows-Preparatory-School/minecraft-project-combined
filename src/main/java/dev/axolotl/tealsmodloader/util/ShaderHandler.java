package dev.axolotl.tealsmodloader.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.util.JsonException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ShaderHandler
{
    private Minecraft mc;

    public ShaderHandler(Minecraft mc) {
        this.mc = mc;
    }

    public void loadShader() {
        if(OpenGlHelper.shadersSupported)
        {
            if(this.mc.renderViewEntity instanceof EntityPlayer)
            {
                try {
                    EntityRenderer entityRenderer = this.mc.entityRenderer;
                    if (entityRenderer.theShaderGroup != null) {
                        entityRenderer.theShaderGroup.deleteShaderGroup();
                    }

                    entityRenderer.theShaderGroup = new ShaderGroup(
                        this.mc.getTextureManager(),
                        this.mc.getResourceManager(),
                        this.mc.getFramebuffer(),
                        new ResourceLocation("shaders/post/bits.json") // ntsc - scan_pincushion - bits
                    );
                    entityRenderer.theShaderGroup.createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
                }
                catch (JsonException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void killShaders()
    {
        EntityRenderer entityRenderer = this.mc.entityRenderer;
        if (entityRenderer.theShaderGroup != null) {
            entityRenderer.theShaderGroup.deleteShaderGroup();
        }
    }
}
