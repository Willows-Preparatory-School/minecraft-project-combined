package dev.axolotl.tealsmodloader.tealsmc.mods.entities;

import net.minecraft.entity.ai.EntityAIBase;
import dev.axolotl.tealsmodloader.tealsmc.mods.blocks.BlocksModule;

public class EntityAIGoToBattery extends EntityAIBase {
    private DODRobot bot;
    public EntityAIGoToBattery(DODRobot dodRobot){
        this.bot = dodRobot;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
    }

    public boolean shouldExecute() {
        return true;
    }

    @Override
    public boolean continueExecuting(){
        return bot.getCharge() <= 70.0;
    }

    public void updateTask(){
        int[] offsets = new int[21];
        for (int i = -10; i<11; i++){
            offsets[i+10]=i;
        }
        for (int i : offsets){
            for (int j : offsets){
                for (int k : offsets){
                    if(bot.worldObj.getBlock((int) bot.posX + i, (int) bot.posY+j, (int) bot.posZ+k).equals(BlocksModule.battery_block)){
                        bot.getNavigator().tryMoveToXYZ(bot.posX + i,  bot.posY+j, bot.posZ+k, 1.0);
                        return;
                    }
                }
            }
        }
    }
}
