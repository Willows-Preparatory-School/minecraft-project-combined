package dev.axolotl.testmod.tealsmc.mods.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

import static dev.axolotl.testmod.tealsmodloader.builtin.entity.Robot.*;

public class EntityAIInfect extends EntityAIBase
{
    public EntityAIInfect(VirusRobot virusRobot)
    {
        super();
        this.entity = virusRobot;
    }

    private EntityLivingBase targetLiving;
    EntityLiving entity;

    @Override
    public boolean shouldExecute() {
        this.targetLiving = entity.getAttackTarget();
        return this.targetLiving != null;
    }

    @Override
    public void startExecuting() {
        if(shouldExecute())
        {
            this.targetLiving = entity.getAttackTarget();
        }
    }

    @Override
    public boolean continueExecuting() {
        return entity.getAttackTarget() != null && !entity.getAttackTarget().isDead;
    }

    @Override
    public void updateTask()
    {
        //System.out.println("AI INFECT CALLED!!!");
        if(!this.targetLiving.isDead)
        {
            //System.out.println("Found target: " + this.targetLiving.getEntityId());
            float distanceInBlocks = this.entity.getDistanceToEntity(this.entity.getAttackTarget());
            //System.out.println("Moving to target at: X: " + this.targetLiving.posX + " Y: " + this.targetLiving.posY + " Z: " + this.targetLiving.posZ);
            this.entity.getNavigator().tryMoveToEntityLiving(targetLiving, SPEED_FAST);
            if(distanceInBlocks <= 2)
            {
                VirusRobot newVirusRobot = new VirusRobot(this.entity.worldObj);
                newVirusRobot.setLocationAndAngles(targetLiving.posX, targetLiving.posY + 1, targetLiving.posZ, 0 ,0);
                this.entity.worldObj.spawnEntityInWorld(newVirusRobot);

                this.targetLiving.setDead();
            }
        }
        else
        {
            // do nothing :3
        }
    }
}
