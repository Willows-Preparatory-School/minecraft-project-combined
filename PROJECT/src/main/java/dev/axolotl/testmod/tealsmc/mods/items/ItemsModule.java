package dev.axolotl.testmod.tealsmc.mods.items;

import dev.axolotl.testmod.tealsmodloader.module.Module;

public class ItemsModule extends Module {

    public static final RockSifter rockSifter = new RockSifter();
    public static final LightningStick lightningStick = new LightningStick();
    public static final CrystalGrowingItem crystalGrowing = new CrystalGrowingItem();

    public void onLoad() {
        parentMod.itemRegistry.newInstance("rock_sifter", rockSifter, "Rock Sifter");
        parentMod.itemRegistry.newInstance("lightning_stick", lightningStick, "Lightning Stick");
        parentMod.itemRegistry.newInstance("crystal_growing_item", crystalGrowing, "Crystal Growing Item");
    }
}
