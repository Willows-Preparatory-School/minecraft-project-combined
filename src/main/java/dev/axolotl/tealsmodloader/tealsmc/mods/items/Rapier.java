package dev.axolotl.tealsmodloader.tealsmc.mods.items;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class Rapier extends ItemSword {

    public Rapier (){
        super(ToolMaterial.EMERALD);
        this.setCreativeTab(TestMod.tabTestMod);
    }
    //constructor of ItemSword class that sets the material to Emerald and defines it as a combat type
    // The rapier is an iron sword with a custom rapier texture
}
