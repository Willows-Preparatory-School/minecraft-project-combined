//The package it is in
package dev.axolotl.tealsmodloader.tealsmc.mods.blocks;

//The packages necessary to work with Minecraft
import net.minecraft.block.BlockTorch;

public class FieldGenerator extends BlockTorch {

    //Constructor
    //The light level is an Easter Egg for the value of the explosion size

    public FieldGenerator(){setLightLevel(Explocharge.explosionSize);}
}
