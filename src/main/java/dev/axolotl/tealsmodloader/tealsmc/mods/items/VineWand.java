package dev.axolotl.tealsmodloader.tealsmc.mods.items;

import dev.axolotl.tealsmodloader.tealsmc.mods.blocks.BlocksModule;
import dev.axolotl.tealsmodloader.tealsmc.mods.blocks.ParadoxVine;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class VineWand extends Item {

    public VineWand() {
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabTools);
    }
    public boolean onItemUse (
            ItemStack item,               // Items in the player's inventory
            EntityPlayer player,
            World world,
            int x, int y, int z,          // Location of block (can be air) the player clicked
            int side,                     // Cube face the player clicked
            float px, float py, float pz  // Player's location in the world
    ){
        if(world.getBlock(x,y,z).equals(BlocksModule.vines[0]) && ((ParadoxVine) world.getBlock(x,y,z)).getId() == 0){
            int height = (int) (Math.random() * 41) + 20; //random height between 20 and 60 inclusive
            for(int i=0;i<height;i++){
                int y1 = y + i + 1;
                if(!world.getBlock(x,y1,z).equals(Blocks.air)){ //if blocked then stop growth
                    world.setBlock(x,y1-1,z,BlocksModule.vines[12]);
                    return true;
                }
                world.setBlock(x,y1,z,BlocksModule.vines[1]); //grow this block
                if(Math.random() < 0.08){ // 8% chance it spawns a branch
                    int dir = (int) (Math.random() * 4); //0=+x, 1=-x,2=+z,3=-z
                    if(dir==0){
                        // if vacant
                        if(world.getBlock(x+1,y1,z).equals(Blocks.air) && world.getBlock(x+2,y1,z).equals(Blocks.air)){
                            int height2 = (int) (Math.random() * (height - i)); //height of branch
                            if(height2 > 5){ //must be tall enough to look nice
                                for(int j=0;j<height2;j++){
                                    if(!world.getBlock(x+2,y1+j+1,z).equals(Blocks.air)){
                                        break;
                                    }
                                    world.setBlock(x+2,y1+j+1,z,BlocksModule.vines[1]);
                                }
                                world.setBlock(x+2,y1,z,BlocksModule.vines[4]);
                                world.setBlock(x+1,y1,z,BlocksModule.vines[2]);
                                world.setBlock(x,y1,z,BlocksModule.vines[8]);
                                world.setBlock(x+2,y1+height2,z,BlocksModule.vines[12]);
                            }
                        }
                    }
                    else if(dir==1){ //all the rest is the same as the above but different direction
                        if(world.getBlock(x-1,y1,z).equals(Blocks.air) && world.getBlock(x-2,y1,z).equals(Blocks.air)){
                            int height2 = (int) (Math.random() * (height - i));
                            if(height2 > 5){
                                for(int j=0;j<height2;j++){
                                    if(!world.getBlock(x-2,y1+j+1,z).equals(Blocks.air)){
                                        break;
                                    }
                                    world.setBlock(x-2,y1+j+1,z,BlocksModule.vines[1]);
                                }
                                world.setBlock(x-2,y1,z,BlocksModule.vines[5]);
                                world.setBlock(x-1,y1,z,BlocksModule.vines[2]);
                                world.setBlock(x,y1,z,BlocksModule.vines[9]);
                                world.setBlock(x-2,y1+height2,z,BlocksModule.vines[12]);
                            }
                        }
                    }
                    else if(dir==2){
                        if(world.getBlock(x,y1,z+1).equals(Blocks.air) && world.getBlock(x,y1,z+2).equals(Blocks.air)){
                            int height2 = (int) (Math.random() * (height - i));
                            if(height2 > 5){
                                for(int j=0;j<height2;j++){
                                    if(!world.getBlock(x,y1+j+1,z+2).equals(Blocks.air)){
                                        break;
                                    }
                                    world.setBlock(x,y1+j+1,z+2,BlocksModule.vines[1]);
                                }
                                world.setBlock(x,y1,z+2,BlocksModule.vines[6]);
                                world.setBlock(x,y1,z+1,BlocksModule.vines[3]);
                                world.setBlock(x,y1,z,BlocksModule.vines[10]);
                                world.setBlock(x,y1+height2,z+2,BlocksModule.vines[12]);
                            }
                        }
                    }
                    else{
                        if(world.getBlock(x,y1,z-1).equals(Blocks.air) && world.getBlock(x,y1,z-2).equals(Blocks.air)){
                            int height2 = (int) (Math.random() * (height - i));
                            if(height2 > 5){
                                for(int j=0;j<height2;j++){
                                    if(!world.getBlock(x,y1+j+1,z-2).equals(Blocks.air)){
                                        break;
                                    }
                                    world.setBlock(x,y1+j+1,z-2,BlocksModule.vines[1]);
                                }
                                world.setBlock(x,y1,z-2,BlocksModule.vines[7]);
                                world.setBlock(x,y1,z-1,BlocksModule.vines[3]);
                                world.setBlock(x,y1,z,BlocksModule.vines[11]);
                                world.setBlock(x,y1+height2,z-2,BlocksModule.vines[12]);
                            }
                        }
                    }
                }
            }
            world.setBlock(x,y+height,z,BlocksModule.vines[12]);
        }
        return true;
    }
}
