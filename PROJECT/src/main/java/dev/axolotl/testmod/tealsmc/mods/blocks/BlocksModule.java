package dev.axolotl.testmod.tealsmc.mods.blocks;

import dev.axolotl.testmod.tealsmodloader.module.Module;

/**
 * All TEALS mod blocks are registered here in order to be displayed in game.
 * All of the custom blocks you will create in the future should be registered
 * here.
 */
public class BlocksModule extends Module {

    public static final AmethystOre amethystOre = new AmethystOre();
    public static final SuperTNT super_tnt = new SuperTNT();
    public static final BatteryBlock battery_block = new BatteryBlock();
    public static final CheckeredBlock checkered_block = new CheckeredBlock();
    public static final CrystalGrowingBlock crystalGrowingBlock = new CrystalGrowingBlock();
    public static final InfectedBlock infectedBlock = new InfectedBlock();

    public void onLoad() {
        parentMod.blockRegistry.newInstance("amethyst_ore", amethystOre, "Amethyst Ore");
        parentMod.blockRegistry.newInstance("super_tnt", super_tnt, "Super TNT");
        parentMod.blockRegistry.newInstance("battery_block", battery_block, "Battery Block");
        parentMod.blockRegistry.newInstance("checkered_block", checkered_block, "Checkered Block");
        parentMod.blockRegistry.newInstance("crystal_growing_block", crystalGrowingBlock, "Crystal Growing Block");
        parentMod.blockRegistry.newInstance("infected_block", infectedBlock, "Infected Block");
    }
}
