package dev.axolotl.tealsmodloader.tealsmc.mods.entities;

import dev.axolotl.tealsmodloader.tealsmodloader.builtin.entity.Robot;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;

import java.util.Random;

public class EntityAIConvertLeaves extends EntityAIBase
{
    Robot robot;
    Random random = new Random();

    public EntityAIConvertLeaves(Robot robot)
    {
        super();
        this.robot = robot;
    }

    public boolean shouldExecute()
    {
        return random.nextDouble() > 0.5; // Returns true or false.
        //return true;
    }

    public void startExecuting()
    {
        //System.out.println("EntityAIConvertLeaves called!!!");
        // range to find random block
        int range = 2; // 2 blocks in each direction

        // get random x, y, z coords
        int x = (int) (robot.posX + (int)(Math.random() * (range * 2)) - range);
        int y = (int) robot.posY;
        int z = (int) (robot.posZ + (int)(Math.random() * (range * 2)) - range);

        //System.out.println("Random leaves: X: " + x + " Y: " + y + " Z: " + z);
        if(!robot.worldObj.getBlock(x, y, z).isAir(robot.worldObj, x, y, z) && !robot.worldObj.getBlock(x, y, z).isLeaves(robot.worldObj, x, y, z))
        {
            //System.out.println("Setting leaves at: X: "+x+" Y: "+y+" Z: "+z);
            robot.worldObj.setBlock(x, y, z, Blocks.leaves);
        }
    }

    public boolean continueExecuting()
    {
        return false;
    }
}
