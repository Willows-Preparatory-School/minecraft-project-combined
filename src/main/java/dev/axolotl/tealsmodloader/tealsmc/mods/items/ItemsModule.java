package dev.axolotl.tealsmodloader.tealsmc.mods.items;

import dev.axolotl.tealsmodloader.tealsmodloader.module.Module;

public class ItemsModule extends Module {

    public static final RockSifter rockSifter = new RockSifter();
    public static final LightningStick lightningStick = new LightningStick();
    public static final CrystalGrowingItem crystalGrowing = new CrystalGrowingItem();
    public static final ItemMolotov molotov = new ItemMolotov();
    public static final VineWand vinewand = new VineWand();

    public static final DiscItem spinningDisc = new DiscItem();

    public void onLoad() {
        parentMod.itemRegistry.newInstance("rock_sifter", rockSifter, "Rock Sifter");
        parentMod.itemRegistry.newInstance("lightning_stick", lightningStick, "Lightning Stick");
        parentMod.itemRegistry.newInstance("crystal_growing_item", crystalGrowing, "Crystal Growing Item");
        parentMod.itemRegistry.newInstance("molotov", molotov, "Molotov");
        parentMod.itemRegistry.newInstance("vine_wand", vinewand, "Paradox Wand");

        parentMod.itemRegistry.newInstance("spinning_disc", spinningDisc, "Krishna's Disc");
    }
}
