package dev.axolotl.tealsmodloader.tealsmc.mods.entities;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityTNTPrimed;

import static dev.axolotl.tealsmodloader.tealsmodloader.builtin.entity.Robot.SPEED_FAST;

public class EntityAILoseBattery extends EntityAIBase {
    private DODRobot dodbot;
    public EntityAILoseBattery(DODRobot bot){
        dodbot = bot;
    }

    public boolean shouldExecute() {
        return dodbot.getCharge() > 0;
    }

    public boolean continueExecuting() {
        return dodbot.getCharge() > 0;
    }

    public void startExecuting() {
        return;
    }

    public void updateTask(){
        dodbot.setCharge(dodbot.getCharge()-1.0);
        //System.out.println("Charge: " + dodbot.getCharge());
        if (dodbot.getCharge() <= 0){
            dodbot.worldObj.spawnEntityInWorld(new EntityTNTPrimed(dodbot.worldObj));
        }
    }
}
