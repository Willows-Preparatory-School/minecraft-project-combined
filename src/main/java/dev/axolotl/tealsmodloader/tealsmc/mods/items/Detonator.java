//The package it is in
package dev.axolotl.tealsmodloader.tealsmc.mods.items;

//The packages necessary to work with Minecraft
import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

//Our packages
import dev.axolotl.tealsmodloader.tealsmc.mods.blocks.BlocksModule;
import dev.axolotl.tealsmodloader.tealsmc.mods.blocks.Explocharge;

public class Detonator extends Item {

    //These are the coordinates of the Primer Block where the explosion will be in XYZ form.
    private int[] coordinates = new int[3];
    //The boolean tracks if there has been coordinates chosen, if so then the explosion will set off there.
    private boolean primed = false;

    //The constructor
    public Detonator(){
        setCreativeTab(TestMod.tabTestMod);
    }

    public boolean onItemUse (ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz) {

        //Just looks at the block the player has right-clicked on
        Block chosen = world.getBlock(x,y,z);

        //Makes it so tht it doesn't do anything when clicking the primer block
        if (chosen.equals(BlocksModule.redsonthusk)){

            //Finishes Method Runtime
            return true;

        } else if(chosen.equals(BlocksModule.explocharge)){
            //I\The above test says that if it is an Explocharge, it is now a Primer block
            world.setBlock(x,y,z,BlocksModule.redsonthusk);
            //gets the coordinates
            coordinates[0]=x;
            coordinates[1]=y;
            coordinates[2]=z;

            //says the coordinates were chosen
            primed = true;

            //Finishes Method Runtime
            return true;

        } else if (!(chosen.equals(BlocksModule.explocharge)) && !(chosen.equals(BlocksModule.redsonthusk)) && (primed)){
            //The above test makes it so that if you click another block other than an Explocharge or Primer block,
            //then it starts the detonation process, granted that there are chosen coordinates as said by the primed
            //boolean.

            //makes it more readable for us, these are just the coordinates
            int x2 = coordinates[0];
            int y2 = coordinates[1];
            int z2 = coordinates[2];

            //transforms the blocks into invisible barriers
            world.createExplosion(null,x2,y2,z2,Explocharge.explosionSize,true);
                //null would usually be the entity to explode, there is none so it is null
                //The next three values are the world coordinates to set off the explosion at
                //The Explocharge.explosion is the explosion size, same as Explocharge's
                //The true tells the method that blocks should be destroyed, creating the barriers, and if it was false then nothing would be destroyed

            //Puts the re-textured FieldGenerator Torch to delineate the center of the barrier explosion
            world.setBlock(x,y,z,BlocksModule.feltgenerate);

            //Says the coordinates are now not chosen
            primed = false;

            //Finishes Method Runtime
            return true;
        }

        //Finishes Method Runtime
        return true;
    }
}
