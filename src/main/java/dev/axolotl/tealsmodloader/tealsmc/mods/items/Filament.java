package dev.axolotl.tealsmodloader.tealsmc.mods.items;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Filament extends Item {
    public Filament() {
        this.setMaxStackSize(64); // sets max stack size to 64
        this.setCreativeTab(TestMod.tabTestMod); // is a type tool
    }
}
