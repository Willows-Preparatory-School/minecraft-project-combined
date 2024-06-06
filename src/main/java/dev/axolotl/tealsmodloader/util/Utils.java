package dev.axolotl.tealsmodloader.util;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class Utils
{
    public static void setBlockSound(Block block, Block.SoundType type)
    {
        /*
        if (type instanceof ModSounds.CustomSound) {
            block.setStepSound(ConfigSounds.newBlockSounds ? type : ((ModSounds.CustomSound) type).getDisabledSound());
        }
         */
        block.setStepSound(type);
    }

    public static EntityFX spawnParticle(World world, EntityFX entityFX) {
        if (world.isRemote) {
            Minecraft.getMinecraft().effectRenderer.addEffect(entityFX);
            return entityFX;
        }
        return null;
    }
}
