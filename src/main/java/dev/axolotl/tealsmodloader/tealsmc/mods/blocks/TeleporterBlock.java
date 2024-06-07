package dev.axolotl.tealsmodloader.tealsmc.mods.blocks;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import dev.axolotl.tealsmodloader.tealsmodloader.Common;

public class TeleporterBlock extends Block {
    private IIcon[] icons = new IIcon[2];  // Responsible for storing textures for the 'TeleporterBlock'

    public TeleporterBlock() {
        super(Material.rock);
        setCreativeTab(TestMod.tabTestMod);
        TeleportVariables.activated = false;
    }

    public void registerBlockIcons(IIconRegister iconRegister) {
        // Stores the textures as icons in 'icon' list
        icons[0] = iconRegister.registerIcon(Common.MOD_ID + ":teleporter_block_sides");
        icons[1] = iconRegister.registerIcon(Common.MOD_ID + ":teleporter_block_top");
    }

    public IIcon getIcon(int side, int meta) {
        if (side == 1) {  // Checks if the side is the top of the block
            return icons[side];  // Sets the top texture to 'teleporter_block_top.png'
        }

        // Otherwise, the textures for all the other sides of the block are set to 'teleporter_block_sides.png'
        return icons[0];
    }

    @Override
    public void onPostBlockPlaced(World world, int x, int y, int z, int meta) {
        // This runs after the Teleporter Block is placed in the world

        if (!world.getBlock(x, y + 1, z).equals(Blocks.air)) {
            // If there is a block which isn't air above the block, then the block is set to air and drops as an item
            // This makes sure that the player places the block such that there is only air above it
            world.setBlock(x, y, z, Blocks.air);
            world.getBlock(x, y, z).dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float sideX, float sideY, float sideZ) {
        // This runs when the block is activated, or right-clicked

        if (!world.isRemote) {
            if (player.inventory.getCurrentItem() == null && !TeleportVariables.activated) {
                // This checks if the current item the player is holding is nothing and if the 'activated' variable is false
                player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(BlocksModule.endpointBlock));  // Sets the current slot in the inventory to an End Point Block
                TeleportVariables.activated = true;
            }
        }

        return true;
    }

    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
        // This runs when an entity walks on top of the block

        if (entity.equals(Minecraft.getMinecraft().thePlayer)) {  // Checks if the entity that walks on the block is the Player
            // Sets the players location to the coordinates of the End Point Block
            entity.setLocationAndAngles(TeleportVariables.endPointX, TeleportVariables.endPointY, TeleportVariables.endPointZ, entity.rotationYaw, entity.rotationPitch);
        }
    }
}
