package dev.axolotl.testmod.tealsmc.mods.entities;

import dev.axolotl.testmod.tealsmodloader.builtin.entity.Robot;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class VirusRobot extends Robot
{
    public static final long lifetime = 20000;
    public long expirationTime;

    public VirusRobot(World world)
    {
        super(world);
        EntityAIWander entityAIWander = new EntityAIWander(this, SPEED_NORMAL);
        EntityAIInfect entityAIInfect = new EntityAIInfect(this);
        tasks.addTask(1, entityAIWander);
        tasks.addTask(2, entityAIInfect);
        expirationTime = System.currentTimeMillis() + lifetime;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        if(System.currentTimeMillis() > expirationTime)
        {
            damageEntity(new DamageSource("Died from old age"), getHealth());
        }
    }
}
