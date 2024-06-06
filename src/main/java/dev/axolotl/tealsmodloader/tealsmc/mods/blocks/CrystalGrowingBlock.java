package dev.axolotl.tealsmodloader.tealsmc.mods.blocks;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CrystalGrowingBlock extends Block
{
    public CrystalGrowingBlock()
    {
        super(Material.glass);
        this.setCreativeTab(TestMod.tabTestMod);

    }
}
