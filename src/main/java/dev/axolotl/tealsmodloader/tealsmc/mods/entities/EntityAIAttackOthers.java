package dev.axolotl.tealsmodloader.tealsmc.mods.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import dev.axolotl.tealsmodloader.tealsmodloader.builtin.entity.Robot;

import static dev.axolotl.tealsmodloader.tealsmodloader.builtin.entity.Robot.SPEED_FAST;

public class EntityAIAttackOthers extends EntityAIBase {
    private Robot bot;

    public EntityAIAttackOthers(Robot entity){
        bot = entity;
    }
    private EntityLivingBase target;

    public boolean shouldExecute(){

        System.out.println("Should " + bot.getAttackTarget() != null);
        return bot.getAttackTarget() != null;
    }

    public void startExecuting(){
        target = bot.getAttackTarget();
    }

    public boolean continueExecuting(){

        System.out.println("Continue " + (!target.isDead && (bot.getAttackTarget() != null)) + " " + target.toString());
        return (!target.isDead && (bot.getAttackTarget() != null));
    }

    public void updateTask(){
        if (!target.isDead){
            System.out.println(bot.getNavigator().tryMoveToEntityLiving(target, SPEED_FAST));
            if (bot.getDistanceToEntity(target) <= 2.0f){
                target.setDead();
            }
        }
    }
}
