package dev.axolotl.tealsmodloader.tealsmc.mods.items;

import dev.axolotl.tealsmodloader.tealsmodloader.module.Module;

public class ItemsModule extends Module {

    public static final RockSifter rockSifter = new RockSifter();
    public static final LightningStick lightningStick = new LightningStick();
    public static final CrystalGrowingItem crystalGrowing = new CrystalGrowingItem();
    public static final ItemMolotov molotov = new ItemMolotov();
    public static final VineWand vinewand = new VineWand();

    public static final DiscItem spinningDisc = new DiscItem();

    public static final TheDecimator decimator = new TheDecimator();
    public static final TheLevitator levitator = new TheLevitator();
    public static final TheConstructor constructor = new TheConstructor();

    public static final BatteryItem batteryItem = new BatteryItem();

    public static final Detonator detonator = new Detonator();

    public static final Rapier rapier = new Rapier();
    public static final Filament filament = new Filament();
    public static final UnrefinedPlastic unrefinedPlastic = new UnrefinedPlastic();
    public static final PlasticRefiner plasticRefiner = new PlasticRefiner();

    public static final GiftBox giftBox = new GiftBox();

    public void onLoad() {
        parentMod.itemRegistry.newInstance("rock_sifter", rockSifter, "Rock Sifter");
        parentMod.itemRegistry.newInstance("lightning_stick", lightningStick, "Lightning Stick");
        parentMod.itemRegistry.newInstance("crystal_growing_item", crystalGrowing, "Crystal Growing Item");
        parentMod.itemRegistry.newInstance("molotov", molotov, "Molotov");
        parentMod.itemRegistry.newInstance("vine_wand", vinewand, "Paradox Wand");

        parentMod.itemRegistry.newInstance("spinning_disc", spinningDisc, "Krishna's Disc");

        parentMod.itemRegistry.newInstance("the_decimator", decimator, "The Decimator");
        parentMod.itemRegistry.newInstance("the_levitator", levitator, "The Levitator");
        parentMod.itemRegistry.newInstance("the_constructor", constructor, "The Constructor");

        parentMod.itemRegistry.newInstance("battery_item", batteryItem, "Battery Item");

        parentMod.itemRegistry.newInstance("Detonator", detonator, "Deto");

        parentMod.itemRegistry.newInstance("img", rapier, "Rapier");
        parentMod.itemRegistry.newInstance("filament", filament, "Filament");
        parentMod.itemRegistry.newInstance("unrefined_plastic", unrefinedPlastic, "Unrefined Plastic");
        parentMod.itemRegistry.newInstance("plastic_refiner", plasticRefiner, "Plastic Refiner");

        parentMod.itemRegistry.newInstance("realgiftbox", giftBox, "Gift Box");
    }
}
