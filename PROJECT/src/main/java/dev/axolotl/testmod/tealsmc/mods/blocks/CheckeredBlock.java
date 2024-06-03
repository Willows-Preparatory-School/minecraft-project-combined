package dev.axolotl.testmod.tealsmc.mods.blocks;

import dev.axolotl.testmod.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class CheckeredBlock extends Block
{
    CheckeredBlock()
    {
        super(Material.rock);
        this.setCreativeTab(TestMod.tabTestMod);
    }

    private static IIcon[] icons = new IIcon[2];

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        icons[0] = iconRegister.registerIcon(TestMod.MODID + ":black" );
        icons[1] = iconRegister.registerIcon(TestMod.MODID + ":white");
    }

    public IIcon getIcon (int side, int meta)
    {
        return icons[0];
    }

    public IIcon getIcon (IBlockAccess worldAccess, int x, int y, int z, int meta)
    {
        if((x + y + z) % 2 == 0)
        {
            return icons[0];
        }
        else
        {
            return icons[1];
        }
    }
}
