package dev.axolotl.tealsmodloader.tealsmc.mods.entities;

import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import dev.axolotl.tealsmodloader.tealsmodloader.builtin.entity.Robot;
// my design looks like a squid but extends robot
public class StrawberrySquid extends Robot {
    public StrawberrySquid (World world) {
        super(world);
        // creates a task for the strawberry squid to look at the player
        EntityAIWatchClosest look = new EntityAIWatchClosest(this, EntityPlayer.class, 50F);
        // makes the task priority 1
        tasks.addTask (1, look);



    }
}

