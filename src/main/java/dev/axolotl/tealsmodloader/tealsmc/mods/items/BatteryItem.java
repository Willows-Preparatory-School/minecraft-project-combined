package dev.axolotl.tealsmodloader.tealsmc.mods.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dev.axolotl.tealsmodloader.tealsmodloader.Common;
import dev.axolotl.tealsmodloader.tealsmc.mods.blocks.BlocksModule;
import dev.axolotl.tealsmodloader.tealsmc.mods.blocks.MaterialGeneratorBlock;


public class BatteryItem extends Item {
    //BatteryItem is an item used to set the battery on the Material Generator Block back to 90!
    // The BatteryItem is generated by the Battery Generator Block.

    public BatteryItem() {
        this.setCreativeTab(CreativeTabs.tabTools);
    }
    //constructor!!!


    //this is the method we use to reset the battery of the Material Generator Block!

    public boolean onItemUse (
            ItemStack item,               // Items in the player's inventory
            EntityPlayer player,
            World world,
            int x, int y, int z,          // Location of block (can be air) the player clicked
            int side,                     // Cube face the player clicked
            float px, float py, float pz// Player's location in the world
    ) {
            Block block = world.getBlock(x, y, z);
            if (block.equals(BlocksModule.materialGeneratorBlock)) {
                MaterialGeneratorBlock genBlock = (MaterialGeneratorBlock) block;

                //tests to see if the selected block is a Material Generator Block, if it is it casts from Block type to
                // Material Generator Block type.

                genBlock.setBattery();

                //sets the battery to 90! I dont need to use the setter because the field is public, but I did anyway.

                player.clearItemInUse();
                item.stackSize--;

                //one of these two gets rid of the battery after use. IDK which, Im just not changing code now that it
                // works...
        }
        return true;
            //return statement so method works.
    }

}
