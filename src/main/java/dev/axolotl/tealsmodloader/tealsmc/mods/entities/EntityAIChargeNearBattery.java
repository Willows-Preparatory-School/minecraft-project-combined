package dev.axolotl.tealsmodloader.tealsmc.mods.entities;

import net.minecraft.entity.ai.EntityAIBase;
import dev.axolotl.tealsmodloader.tealsmc.mods.blocks.BatteryBlock;
import dev.axolotl.tealsmodloader.tealsmc.mods.blocks.BlocksModule;

public class EntityAIChargeNearBattery extends EntityAIBase {
    private DODRobot dodbot;

    public EntityAIChargeNearBattery(DODRobot bot) {
         dodbot = bot;
    }

    @Override
    public boolean continueExecuting() {
        return super.continueExecuting();
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
    }

    public boolean shouldExecute(){
        int[] offsets = {-2, -1, 0, 1, 2};

        for (int i : offsets){
            for (int j : offsets){
                for (int k : offsets){
                    if (
                            dodbot.worldObj.getBlock((int) dodbot.posX + i,
                    (int) dodbot.posY + j,
                            (int) dodbot.posZ + k).equals(BlocksModule.battery_block)
                    ){
                        dodbot.setCharge(dodbot.getCharge() + 1.5);
                    }
                }
            }
        }
        return true;
    }
}
