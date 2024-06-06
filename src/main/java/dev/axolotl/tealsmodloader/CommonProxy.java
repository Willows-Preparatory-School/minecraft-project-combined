package dev.axolotl.tealsmodloader;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import dev.axolotl.tealsmodloader.entity.*;
import dev.axolotl.tealsmodloader.item.elytra.StartFallFlying;
import net.minecraft.entity.EntityLivingBase;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        TestMod.LOG.info(Config.greeting);
        TestMod.LOG.info("I am TEALS Mod Loader at version " + Tags.VERSION);

        TestMod.LOG.info("Registering networkChannel...");
        TestMod.network = NetworkRegistry.INSTANCE.newSimpleChannel("TestMod");
        TestMod.network.registerMessage(StartFallFlying.class, StartFallFlying.class, 0, Side.SERVER);
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {}

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {}

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {}

    // register rendering, for client side only
    public void registerRenderers() {}

    // this just lets us override the method in the ClientProxy,
    // since the RenderingRegistry only needs to be done client-side
    public int addArmor(String string) {
        return 0;
    }

    public void update(EntityLivingBase elb) {}

    public void registerEntities(TestMod testMod)
    {
        TestMod.LOG.info("Registering entities...");
        TestModEntityList.registerEntity(EntityThrowingRock.class, "throwing_rock", 0, testMod, 64, 10, true);
        TestModEntityList.registerEntity(EntityEndermite.class, "endermite", 1, testMod, 64, 1, true, 0x161616, 0x6E6E6E);
        TestModEntityList.registerEntity(EntityHusk.class, "husk", 2, testMod, 80, 3, true, 0x777561, 0xE0D991);
        TestModEntityList.registerEntity(EntityStray.class, "stray", 3, testMod, 80, 3, true, 0x617778, 0xE6EAEA);
    }
}
