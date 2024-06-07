//Lines 2-10 are just importing things
package dev.axolotl.tealsmodloader.tealsmc.mods.items;
import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
//Class header
public class TheLevitator extends Item {
    //Instance variable for delay when levitating the block
    private long lastTime;
    //Boolean of whether a block is currently being levitated
    private boolean moving;
    //Instance variable for what block is being levitated
    private Block b;
    //Instance variables for storing the position of the block
    private int tx;
    private int ty;
    private int tz;
    //Constructor (no parameters)
    public TheLevitator() {
        //The Levitator cannot stack
        setMaxStackSize(1);
        //The Levitator is a tool, so it goes into the tools tab
        setCreativeTab(TestMod.tabTestMod);
        //Initializing all the instance variables
        lastTime = 0;
        moving = false;
        tx = 0;
        ty = 0;
        tz = 0;
    }
    //When the player uses The Levitator: (Intent: Make the targeted block float up until the build limit, where it is destroyed. Most of the code for this is in the onUpdate function, but onItemUse starts it.)
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz) {
        //If a block is not already being levitated
        if(!moving) {
            //Storing what block it is in an instance variable for future use
            b = world.getBlock(x, y, z);
            //Storing the position of the targeted block
            tx = x;
            ty = y;
            tz = z;
            //Making sure that the onUpdate function will levitate the block
            moving = true;
        }
        //Confirming that the action has been done without failure
        return true;
    }
    public void onUpdate(ItemStack stackThing, World world, Entity e, int smth, boolean smthElse) {
        //If ready for next block movement
        if (System.currentTimeMillis() >= lastTime && moving) {
            //Set delay for next execution
            lastTime = System.currentTimeMillis() + 50;
            //Increment the y value of the block
            ty++;
            //Put the block at the new y value
            world.setBlock(tx, ty, tz, b);
            //If at build limit, stop
            if(ty >= 255) {
                moving = false;
                //Delete the block
                world.setBlock(tx, ty, tz, Blocks.air);
            }
        }
        //Making sure that the block below the block being levitated is air
        world.setBlock(tx, ty-1, tz, Blocks.air);
    }
}
