//Lines 2-9 are just importing things
package dev.axolotl.tealsmodloader.tealsmc.mods.items;
import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
//Class header
public class TheDecimator extends Item {
    //Constructor (no parameters)
    public TheDecimator() {
        //The Decimator cannot stack
        setMaxStackSize(1);
        //The Decimator is a tool, so it goes into the tools tab
        setCreativeTab(TestMod.tabTestMod);
    }
    //What the two functions below do is when you right-click a block with The Decimator, it destroys that block and all blocks that are the same as it that are connected to it
    //When the player uses The Decimator:
    public boolean onItemUse (ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz) {
        //Call to the main usage function
        usageThing(item, player, world, x, y, z, side, px, py, pz);
        //Confirming that the action has been done
        return true;
    }
    //I am using a separate function for this so I don't have to return true each time. This is the main usage function.
    public void usageThing(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz) {
        //The block being targeted
        Block b = world.getBlock(x, y, z);
        //Destroy the targeted block
        world.setBlock(x, y, z, Blocks.air);
        //For each side of the targeted block, if the block on that side is the same block as the targeted block, then recur the function with it
        if(world.getBlock(x+1, y, z).equals(b)) usageThing(item, player, world, x+1, y, z, side, px, py, pz);
        if(world.getBlock(x-1, y, z).equals(b)) usageThing(item, player, world, x-1, y, z, side, px, py, pz);
        if(world.getBlock(x, y+1, z).equals(b)) usageThing(item, player, world, x, y+1, z, side, px, py, pz);
        if(world.getBlock(x, y-1, z).equals(b)) usageThing(item, player, world, x, y-1, z, side, px, py, pz);
        if(world.getBlock(x, y, z+1).equals(b)) usageThing(item, player, world, x, y, z+1, side, px, py, pz);
        if(world.getBlock(x, y, z-1).equals(b)) usageThing(item, player, world, x, y, z-1, side, px, py, pz);
    }
}
