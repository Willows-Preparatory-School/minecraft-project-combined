package dev.axolotl.tealsmodloader.tealsmc.mods.blocks;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.BlockOre;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import dev.axolotl.tealsmodloader.tealsmc.mods.items.ItemsModule;

import java.util.Random;


public class PlasticOre extends BlockOre {

    public PlasticOre() { // default constructor
        super(); // call to super class to set material type to rock
        this.setCreativeTab(TestMod.tabTestMod); // In creative menu it is found under block types
        blockHardness = 15.0F; // sets block hardness
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) { //  overridden method from BlockOre class that drops specified drop
        return this == BlocksModule.plasticOre ? ItemsModule.filament : Item.getItemFromBlock(this); // drops the unrefined plastic on block break
    }
}
