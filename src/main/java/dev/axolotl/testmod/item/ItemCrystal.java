package dev.axolotl.testmod.item;

import dev.axolotl.testmod.TestMod;
import net.minecraft.item.Item;

public class ItemCrystal extends Item
{
    public ItemCrystal()
    {
        super();
        this.setFull3D();
        this.setUnlocalizedName("crystal");
        this.setTextureName(TestMod.MODID+":crystal");
        this.setCreativeTab(TestMod.tabTestMod);
    }
}
