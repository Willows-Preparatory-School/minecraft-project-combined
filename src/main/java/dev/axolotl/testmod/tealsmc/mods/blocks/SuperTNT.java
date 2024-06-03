package dev.axolotl.testmod.tealsmc.mods.blocks;

import dev.axolotl.testmod.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class SuperTNT extends BlockTNT
{
    SuperTNT()
    {
        super();
        this.setCreativeTab(TestMod.tabTestMod);
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ)
    {
        if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.flint_and_steel)
        {
            for (int i = 0; i < 10; i++)
            {
                this.func_150114_a(worldIn, x, y, z, 1, player);
            }
            worldIn.setBlockToAir(x, y, z);
            player.getCurrentEquippedItem().damageItem(1, player);
            return true;
        }
        else
        {
            return super.onBlockActivated(worldIn, x, y, z, player, side, subX, subY, subZ);
        }
    }
}
