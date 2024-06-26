package dev.axolotl.tealsmodloader.tealsmc.mods.blocks;

import dev.axolotl.tealsmodloader.tealsmodloader.module.Module;
import net.minecraft.block.Block;

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
    public static final ParadoxVine[] vines = {new ParadoxVine(0),new ParadoxVine(1),new ParadoxVine(2),new ParadoxVine(3),new ParadoxVine(4),new ParadoxVine(5),new ParadoxVine(6),new ParadoxVine(7),new ParadoxVine(8),new ParadoxVine(9),new ParadoxVine(10),new ParadoxVine(11),new ParadoxVine(12)};

    public static final TeleporterBlock teleporterBlock = new TeleporterBlock();
    public static final EndPointBlock endpointBlock = new EndPointBlock();

    public static final UselessBox uselessBox = new UselessBox();

    public static final MaterialGeneratorBlock materialGeneratorBlock = new MaterialGeneratorBlock();
    public static final BatteryGeneratorBlock batteryGeneratorBlock = new BatteryGeneratorBlock();

    public static final PrimerTimer redsonthusk = new PrimerTimer();
    public static final Explocharge explocharge = new Explocharge();
    public static final FieldGenerator feltgenerate = new FieldGenerator();

    public static final TexturedBlock texturedBlock = new TexturedBlock();
    public static final OppBlock oppBlock = new OppBlock();
    public static final SheepBlock sheepBlock = new SheepBlock();
    public static final PoliceBlock policeBlock = new PoliceBlock();
    public static final GoatBlock goatBlock = new GoatBlock();

    public static final Printer printer = new Printer();
    public static final Block plasticOre = new PlasticOre();

    public void onLoad() {
        parentMod.blockRegistry.newInstance("amethyst_ore", amethystOre, "Amethyst Ore");
        parentMod.blockRegistry.newInstance("super_tnt", super_tnt, "Super TNT");
        parentMod.blockRegistry.newInstance("battery_block", battery_block, "Battery Block");
        parentMod.blockRegistry.newInstance("checkered_block", checkered_block, "Checkered Block");
        parentMod.blockRegistry.newInstance("crystal_growing_block", crystalGrowingBlock, "Crystal Growing Block");
        parentMod.blockRegistry.newInstance("infected_block", infectedBlock, "Infected Block");
        parentMod.blockRegistry.newInstance("vine", vines[0], "Vine Block");
        for(int i=1;i<13;i++){
            parentMod.blockRegistry.newInstance("vine" + i, vines[i], "Vine Block" + i);
        }

        parentMod.blockRegistry.newInstance("teleporter_block", teleporterBlock, "Teleporter Block");
        parentMod.blockRegistry.newInstance("endpoint_block", endpointBlock, "End Point");

        parentMod.blockRegistry.newInstance("useless_box", uselessBox, "Useless Box");

        parentMod.blockRegistry.newInstance("material_generator_block", materialGeneratorBlock, "Material Generator Block");
        parentMod.blockRegistry.newInstance("10percent", batteryGeneratorBlock, "Battery Generator Block");

        parentMod.blockRegistry.newInstance("Redstone_Husk", redsonthusk, "Redstone Primer Block");
        parentMod.blockRegistry.newInstance("explocharge", explocharge, "Explocharge");
        parentMod.blockRegistry.newInstance("felt", feltgenerate, "Field Generator");

        parentMod.blockRegistry.newInstance("download (3)", oppBlock, "Opp Block");
        parentMod.blockRegistry.newInstance("download (2)", sheepBlock, "Sheep Block");
        parentMod.blockRegistry.newInstance("heyyfysfsd", policeBlock, "Police Block");
        parentMod.blockRegistry.newInstance("th (3)", texturedBlock, "Textured Block");
        parentMod.blockRegistry.newInstance("th (2)", goatBlock, "GoatBlock");

        parentMod.blockRegistry.newInstance("printer", printer, "Printer");
        parentMod.blockRegistry.newInstance("plastic_ore", plasticOre, "Plastic Ore");
    }
}
