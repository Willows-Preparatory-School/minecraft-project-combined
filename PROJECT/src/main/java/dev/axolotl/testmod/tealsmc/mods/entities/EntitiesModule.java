package dev.axolotl.testmod.tealsmc.mods.entities;

import dev.axolotl.testmod.tealsmodloader.module.Module;

public class EntitiesModule extends Module
{
    public void onLoad()
    {
        // Register custom TealsMC Mod Entities.
        parentMod.entityRegistry.newInstance("leafbot", LeafBot.class, "rainbow_robot");
        parentMod.entityRegistry.newInstance("virusbot", VirusRobot.class, "red_robot");
    }
}
