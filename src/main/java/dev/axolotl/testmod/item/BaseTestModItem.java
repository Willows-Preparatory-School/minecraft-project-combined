package dev.axolotl.testmod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class BaseTestModItem extends Item
{
    // no more IDs
    public BaseTestModItem() {
        super();
    }

    // IconRegister renamed to IIconRegister
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("testmod:" + getUnlocalizedName().substring(5).toLowerCase());
    }
}
