package dev.axolotl.tealsmodloader.tealsmc.mods.blocks;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BatteryBlock extends Block
{
    public BatteryBlock()
    {
        super(Material.rock);
        this.setCreativeTab(TestMod.tabTestMod);
    }

    private static IIcon[] icons = new IIcon[3];

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        icons[0] = iconRegister.registerIcon(TestMod.MODID + ":battery_bottom");
        icons[1] = iconRegister.registerIcon(TestMod.MODID + ":battery_top");
        icons[2] = iconRegister.registerIcon(TestMod.MODID + ":battery_side");
    }

    public IIcon getIcon(int side, int meta)
    {
        if(side == 0)
        {
            return icons[0];
        }
        else if (side == 1)
        {
            return icons[1];
        }
        else
        {
            return icons[2];
        }
    }
}
