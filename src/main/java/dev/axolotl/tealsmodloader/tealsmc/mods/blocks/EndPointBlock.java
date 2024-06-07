package dev.axolotl.tealsmodloader.tealsmc.mods.blocks;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import dev.axolotl.tealsmodloader.tealsmodloader.Common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

import java.util.ArrayList;

public class EndPointBlock extends Block {
    private IIcon[] icons = new IIcon[2];  // Responsible for storing textures for the 'EndPointBlock'

    public EndPointBlock() {
        super(Material.rock);
        setCreativeTab(TestMod.tabTestMod);
    }

    public void registerBlockIcons(IIconRegister iconRegister) {
        // Stores the textures as icons in 'icon' list
        icons[0] = iconRegister.registerIcon(Common.MOD_ID + ":endpoint_block_sides");
        icons[1] = iconRegister.registerIcon(Common.MOD_ID + ":endpoint_block_top");
    }

    public IIcon getIcon(int side, int meta) {
        if (side == 1) {  // Checks if the side is the top of the block
            return icons[side];  // Sets the top texture to 'endpoint_block_top.png'
        }

        // Otherwise, the textures for all the other sides of the block are set to 'endpoint_block_sides.png'
        return icons[0];
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        // This makes it so that if the player breaks an End Point Block, it will drop nothing
        return new ArrayList<ItemStack>();
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
        // This runs when the End Point Block is destroyed by the player

        TeleportVariables.activated = false;
    }

    @Override
    public void onPostBlockPlaced(World world, int x, int y, int z, int meta) {
        // This runs after the End Point Block is placed in the world

        if (!world.getBlock(x, y + 1, z).equals(Blocks.air)) {
            // If there is a block which isn't air above the block, then the block is set to air and drops as an item
            // This makes sure that the player places the block such that there is only air above it
            world.setBlock(x, y, z, Blocks.air);
            world.getBlock(x, y, z).dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        }

        // Sets the positions along the x-axis, y-axis, and z-axis of the End Point Block to the static variables in the 'TeleportVariables' class
        // These coordinates are used in the 'TeleporterBlock' class to set the player's location to above this End Point Block so that the player 'teleports' in a sense
        TeleportVariables.endPointX = x;
        TeleportVariables.endPointY = y + 1 ;
        TeleportVariables.endPointZ = z;
    }
}
