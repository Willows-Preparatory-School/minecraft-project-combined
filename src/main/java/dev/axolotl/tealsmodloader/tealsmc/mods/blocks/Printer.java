package dev.axolotl.tealsmodloader.tealsmc.mods.blocks;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import dev.axolotl.tealsmodloader.tealsmodloader.Common;

public class Printer extends Block {
    private IIcon[] textures; // an array of IIcon[] types to store textures of dif sides


    public Printer(){
        super (Material.glass); // sets material to glass
        this.setCreativeTab(TestMod.tabTestMod); // sets to type block in creative menu
        textures = new IIcon[3]; // initializes array of IIcon type of length of 3
    }

    public void registerBlockIcons(IIconRegister iconRegister){ // sets all indexes of array to the 3 dif textures
        textures[0] = iconRegister.registerIcon(Common.MOD_ID + ":printer_top");
        textures[1] = iconRegister.registerIcon(Common.MOD_ID + ":printer_bottom");
        textures[2] = iconRegister.registerIcon(Common.MOD_ID + ":printer_side");
    }

    public IIcon getIcon(int side, int meta){ // logic behind which texture the game will render for the player to see

        IIcon sides;

        if (side == 0){ // displays the bottom texture when this side is shown
            sides = textures[1];
        } else if (side == 1){ // displays the top texture when this side is shown
            sides = textures[0];
        } else {
            sides = textures[2]; // // displays the side texture when this side is shown
        }
        return sides;
    }
}
