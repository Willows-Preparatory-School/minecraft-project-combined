//Lines 2-8 are just importing things
package dev.axolotl.tealsmodloader.tealsmc.mods.items;
import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
//Class header
public class TheConstructor extends Item {
    //Constructor (no parameters)
    public TheConstructor() {
        //The Constructor cannot stack
        setMaxStackSize(1);
        //The Constructor is a tool, so it goes into the tools tab
        setCreativeTab(TestMod.tabTestMod);
    }
    //When the player uses The Constructor:
    public boolean onItemUse (ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz) {
        //Make the block grow by one on all sides
        Block b = world.getBlock(x, y, z);
        world.setBlock(x+1, y, z, b);
        world.setBlock(x-1, y, z, b);
        world.setBlock(x, y+1, z, b);
        world.setBlock(x, y-1, z, b);
        world.setBlock(x, y, z+1, b);
        world.setBlock(x, y, z-1, b);
        //Confirming that the action has been done
        return true;
    }
}
