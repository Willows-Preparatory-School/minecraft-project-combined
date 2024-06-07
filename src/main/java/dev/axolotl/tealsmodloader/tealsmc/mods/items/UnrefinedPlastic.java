package dev.axolotl.tealsmodloader.tealsmc.mods.items;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


import java.util.Random;

public class UnrefinedPlastic extends Item {

    public UnrefinedPlastic() { // default constructor
        this.setMaxStackSize(64); //sets max stack size
        this.setCreativeTab(TestMod.tabTestMod); // sets the type to tools
    }
}
