package dev.axolotl.tealsmodloader.tealsmc.mods.entities;

import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import dev.axolotl.tealsmodloader.tealsmodloader.builtin.entity.Robot;
// my design looks like a squid but extends robot
public class OrangeSquid extends Robot {

    public OrangeSquid(World world) {
        super(world);
        // creates a task for the orange squid to jump at you
        EntityAIWander wander = new EntityAIWander(this, SPEED_NORMAL);
        // makes the task priority 1
        tasks.addTask (1, wander);

    }
}
