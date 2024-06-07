package dev.axolotl.tealsmodloader.tealsmc.mods.entities;

import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import dev.axolotl.tealsmodloader.tealsmodloader.builtin.entity.Robot;
// my design looks like a squid but extends robot
public class GrapeSquid extends Robot {
    public GrapeSquid (World world) {
        super(world);
        // creates a task for the grape squid to run around
        EntityAIPanic panic = new EntityAIPanic(this, Robot.SPEED_FAST);
        // makes the task priority 1
        tasks.addTask (1, panic);

    }
}


