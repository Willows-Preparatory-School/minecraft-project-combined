package dev.axolotl.testmod.tealsmc.mods.blocks;

import dev.axolotl.testmod.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class AmethystOre extends BlockFalling {

    public AmethystOre() {
        super (Material.rock);   // Creates an air block for now. You'll need to replace this.
        this.setCreativeTab(TestMod.tabTestMod);
    }
}
