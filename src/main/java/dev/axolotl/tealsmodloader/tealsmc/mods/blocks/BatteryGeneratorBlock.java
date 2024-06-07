package dev.axolotl.tealsmodloader.tealsmc.mods.blocks;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import dev.axolotl.tealsmodloader.tealsmodloader.Common;
import dev.axolotl.tealsmodloader.tealsmc.mods.items.ItemsModule;

import java.util.Random;

public class BatteryGeneratorBlock extends Block {

    //the battery generator block creates a new BatteryItem every 100 ticks, so that you can actually recharge your
    //Material Generator Block!

    public int percent;
    public int counter = 0;
    public IIcon[] texture = new IIcon[11];

    //Percent is the variable I use to identify what percent of completion the block is at. The percent block ranges from
    //a value from 0-10, representing 0% to 100%! The counter is used so that the percent only moves once every 10 ticks.
    // I could have just made percent go to 100, but I did this late at night.
    //texture[] just stores all 11 texture states in the array.


    //registerBlockIcons() just puts textures into the texture[] array. this is called onLoad.

    public void registerBlockIcons(IIconRegister iconRegister) {
        texture[0] = iconRegister.registerIcon(Common.MOD_ID + ":10percent");
        texture[1] = iconRegister.registerIcon(Common.MOD_ID + ":20percent");
        texture[2] = iconRegister.registerIcon(Common.MOD_ID + ":30percent");
        texture[3] = iconRegister.registerIcon(Common.MOD_ID + ":40percent");
        texture[4] = iconRegister.registerIcon(Common.MOD_ID + ":50percent");
        texture[5] = iconRegister.registerIcon(Common.MOD_ID + ":60percent");
        texture[6] = iconRegister.registerIcon(Common.MOD_ID + ":70percent");
        texture[7] = iconRegister.registerIcon(Common.MOD_ID + ":80percent");
        texture[8] = iconRegister.registerIcon(Common.MOD_ID + ":90percent");
        texture[9] = iconRegister.registerIcon(Common.MOD_ID + ":10percent");
        texture[10] = iconRegister.registerIcon(Common.MOD_ID + ":0percent");
    }


    //this returns the texture based of the percent value the block is at. I updated the code to also call getIcon
    //in the randomDisplayTick() method so that it updates the icon every tick, rather than just onLoad.

    public IIcon getIcon(int side, int meta) {
        if (this.percent == 0) {
            return texture[10];
        } else if (this.percent == 1) {
            return texture[0];
        } else if (this.percent == 2) {
            return texture[1];
        } else if (this.percent == 3) {
            return texture[2];
        } else if (this.percent == 4) {
            return texture[3];
        } else if (this.percent == 5) {
            return texture[4];
        } else if (this.percent == 6) {
            return texture[5];
        } else if (this.percent == 7) {
            return texture[6];
        } else if (this.percent == 8) {
            return texture[7];
        } else if (this.percent == 9) {
            return texture[8];
        } else if (this.percent == 10) {
            return texture[9];
        }
        return texture[0];
    }


    /*The randomDisplayTick() method does a series of things:
    *   Updates textures, counter, and percent every tick (textures are not yet displayed)
    *   Generates a battery if the percent value is 10, and then resets it to 0.
    *   Displays the texture by deleting and replacing the block, so that the texture actually gets updated. */

    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        counter++;

        this.getIcon(1, 0);
        this.getIcon(2, 0);
        this.getIcon(3, 0);
        this.getIcon(4, 0);
        this.getIcon(5, 0);
        this.getIcon(6, 0);
        //calling get icon for each side of the block to update textures (inefficient but this is how MC is programmed)

        if (this.percent == 10) {
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(ItemsModule.batteryItem)));
                //^ Line of code used to spawn a new batteryItem
                percent = 0;
                //updates percent so it goes back to 0 after creating a battery.
        }

        if(counter > 10) {
            percent++;
            counter = 0;
            //updates counter and percent, so that every time counter reaches 10, percent goes up one and counter goes
            //down one.
        }

        boolean b = world.setBlock(x, y, z, Blocks.air);
        boolean c = world.setBlock(x, y, z, BlocksModule.batteryGeneratorBlock);
        //The booleans do nothing, I just need them so that the method to delete and rebuild the block works (so you can
        // actually see your updated textures)

        System.out.println("percent:" + percent);
        System.out.println("counter" + counter);
        //this is here because it takes a really long time for the battery to be created, so you can make sure the code
        //is working in the console if you cant see the textures.
    }

    public void setPercent(){
        this.percent = 0;
    }
    //a useless setter for percent.


//constructor!!!
    public BatteryGeneratorBlock() {
        super (Material.rock);
        setCreativeTab(TestMod.tabTestMod);
        this.percent = 0;
    }
}
