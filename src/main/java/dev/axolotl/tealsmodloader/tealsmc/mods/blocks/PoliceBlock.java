package dev.axolotl.tealsmodloader.tealsmc.mods.blocks;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class PoliceBlock extends Block {
    private IIcon[] icon5;

    public PoliceBlock() {
        super(Material.rock);
        setCreativeTab(TestMod.tabTestMod);

    }
}
