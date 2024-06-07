package dev.axolotl.tealsmodloader.tealsmc.mods.entities;

import dev.axolotl.tealsmodloader.tealsmodloader.module.Module;

public class EntitiesModule extends Module
{
    public void onLoad()
    {
        // Register custom TealsMC Mod Entities.
        parentMod.entityRegistry.newInstance("leafbot", LeafBot.class, "rainbow_robot");
        parentMod.entityRegistry.newInstance("virusbot", VirusRobot.class, "red_robot");

        parentMod.entityRegistry.newInstance("spinningbot", DiscEntity.class, "spinning_disc_entity");

        parentMod.entityRegistry.newInstance ("leafbot", LeafBot.class, "green_robot");
        parentMod.entityRegistry.newInstance("virusbot", VirusRobot.class, "red_robot");
        parentMod.entityRegistry.newInstance("dodbot", DODRobot.class, "gray_robot");
    }
}
