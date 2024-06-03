package dev.axolotl.testmod.tealsmc.mods.entities;

import dev.axolotl.testmod.tealsmodloader.builtin.entity.Robot;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class LeafBot extends Robot {

    public LeafBot (World world) {
        super(world);
        EntityAIWander entityAIWander = new EntityAIWander(this, SPEED_NORMAL);
        EntityAIConvertLeaves entityAIConvertLeaves = new EntityAIConvertLeaves(this);
        tasks.addTask(1, entityAIWander);
        tasks.addTask(2, entityAIConvertLeaves);
        //GL11.glTranslatef(0.0F, this.height + 0.1F, 0.0F);
        //GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    }
}
