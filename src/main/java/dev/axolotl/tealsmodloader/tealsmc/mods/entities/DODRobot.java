package dev.axolotl.tealsmodloader.tealsmc.mods.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import dev.axolotl.tealsmodloader.tealsmodloader.builtin.entity.Robot;


public class DODRobot extends Robot {
    // Starting charge (slowly lowers as time goes on)
    private double charge = 100.0;

    public DODRobot(World w){
        super(w);
        tasks.addTask(1, new EntityAIAttackOthers(this));
        tasks.addTask(2, new EntityAILoseBattery(this));
        tasks.addTask(3, new EntityAIChargeNearBattery(this));
        tasks.addTask(4, new EntityAIWander(this, SPEED_NORMAL));
        tasks.addTask(5, new EntityAIGoToBattery(this));
    }

    // This method spawns TNT whenever DODRobot takes damage
    public void onEntityDamage(DamageSource source, float amount)
    {
        for(int i =0; i < amount; i++)
        {
            EntityTNTPrimed tntPrimed = new EntityTNTPrimed(worldObj, posX, posY, posZ, this);
            worldObj.spawnEntityInWorld(tntPrimed);
        }
    }

    public double getCharge(){
        return charge;
    }

    public void setCharge(double amt){
        this.charge = amt;
    }

}
