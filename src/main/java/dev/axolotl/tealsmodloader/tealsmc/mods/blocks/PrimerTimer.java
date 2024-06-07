//The package it is in
package dev.axolotl.tealsmodloader.tealsmc.mods.blocks;

//The packages necessary to work with Minecraft
import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import dev.axolotl.tealsmodloader.tealsmodloader.Common;

public class PrimerTimer extends Block {

    //The textures of the block
    private IIcon[] texture = new IIcon[2];

    //Constructor
    public PrimerTimer(){

        //Makes it easy for the player to harvest
        super(Material.circuits);

        //Puts it in the Redstone tab
        setCreativeTab(TestMod.tabTestMod);
    }

    //Gets the textures of the sides and bases
    public void registerBlockIcons(IIconRegister iconRegister) {
        //Texture of the bases
        texture[0] = iconRegister.registerIcon(Common.MOD_ID + ":timer_casing");

        //Texture of the sides
        texture[1] = iconRegister.registerIcon(Common.MOD_ID + ":timer_side");
    }

    //Says what the textures of the bases and sides are
    public IIcon getIcon(int side, int meta){
        if(side <= 1){
            //textures of the bases
            return texture[0];

        } else {
            //Textures of the sides
            return texture[1];

        }
    }
}
