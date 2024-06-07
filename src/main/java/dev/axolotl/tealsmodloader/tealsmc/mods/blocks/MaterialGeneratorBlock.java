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
import java.util.Random;
//random imports I need to make the code work, I honestly have no clue what most of them do. Ask the documentation I guess,,,,

public class MaterialGeneratorBlock extends Block {

    //the Material Generator Block has a 60% chance of randomly generating one resource (30% chance of iron, 20% chance of gold, 10%vchance of diamond)
    //The block requires "battery" to function. You can put a battery in the block by using the Battery Item

    public int battery;
    public IIcon[] texture = new IIcon[3];

    //the field battery is used to give the block a battery value, ranging from 0-90.
    //all sides of the cube are the same. The reason for three textures is because the texture of the block should display the battery value!


    //registers the textures for low battery, medium battery, and full batery to the texture[] array.
    public void registerBlockIcons(IIconRegister iconRegister) {
        texture[0] = iconRegister.registerIcon(Common.MOD_ID + ":low_battery");
        texture[1] = iconRegister.registerIcon(Common.MOD_ID + ":medium_battery");
        texture[2] = iconRegister.registerIcon(Common.MOD_ID + ":full_battery");
    }


    //this method determines which texture is being utilized. It's structured slightly differnetly, where it returns
    // the texture during a time period, instead of depending on a side. this is because I call the texture every
    // tick, so the texture is constantly being updated and the method is constantly called in the game loop!

    public IIcon getIcon(int side, int meta) {
        if (this.battery < 30) {
            return texture[0];
        }
        else if (this.battery < 60) {
            return texture[1];
        } else {
            return texture[2];
        }
    }

    /*This method is called every game tick, and is part of the game loop. The method does the following things:
    * refresh the sides of the block by calling getIcon (not displayed yet)
    * generates an ore based of a random ore generation percentage
    * updates the texture in the game by deleting and placing the object over and over again.
    * */
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        // world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(Items.gold_ingot)));
        // ^ this is the code i had to find to spawn the materials

        this.getIcon(1, 0);
        this.getIcon(2, 0);
        this.getIcon(3, 0);
        this.getIcon(4, 0);
        this.getIcon(5, 0);
        this.getIcon(6, 0);


        //as long as the block has battery, spawn a random object based of the spawn percentages.
        //these sequences of if loops generate an item based of the oreGenerationPercentage value.

        if (this.battery > 0) {
            int oreGenerationPercentage = (int)(Math.random()*10)+1;
            if (oreGenerationPercentage > 9) {
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(Items.diamond)));
            } else if (oreGenerationPercentage > 6) {
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(Items.gold_ingot)));
            } else if (oreGenerationPercentage > 4) {
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(Items.iron_ingot)));
            }
            //each game tick the battery jumps down once (maxBattery is 90)
            this.battery--;
        }
        //these booleans only exist because I need the method to work. This method deletes the block, and places it again
        // because minecraft only updates textures when a block is placed. This way, the texture constantly updates.
        boolean b = world.setBlock(x, y, z, Blocks.air);
        boolean c = world.setBlock(x, y, z, BlocksModule.materialGeneratorBlock);
    }

    // a setter I programed which I never used. IDK why it is here...
    public void setBattery(){
        this.battery = 100;
    }


    //Constructor sets the material to rock (so its hard), sets it to the block tab in creative menu,
    //and assigns the inital battery upon placement.
    public MaterialGeneratorBlock() {
        super (Material.rock);
        setCreativeTab(TestMod.tabTestMod);
        battery = 90;
    }
}
