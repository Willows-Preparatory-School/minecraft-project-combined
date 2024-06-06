package dev.axolotl.tealsmodloader.tealsmc.mods.blocks;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;

public class AmethystOre extends BlockFalling {

    public AmethystOre() {
        super (Material.rock);   // Creates an air block for now. You'll need to replace this.
        this.setCreativeTab(TestMod.tabTestMod);
    }
}
