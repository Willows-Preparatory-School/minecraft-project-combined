package dev.axolotl.testmod;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dev.axolotl.testmod.blocks.*;
import dev.axolotl.testmod.entity.TestModEntityList;
import dev.axolotl.testmod.fluids.*;
import dev.axolotl.testmod.init.TestModIcons;
import dev.axolotl.testmod.item.*;
import dev.axolotl.testmod.item.elytra.ItemElytra;
import dev.axolotl.testmod.item.elytra.ItemElytraRocket;
import dev.axolotl.testmod.tealsmc.mods.blocks.BlocksModule;
import dev.axolotl.testmod.tealsmc.mods.items.ItemsModule;
import dev.axolotl.testmod.tealsmodloader.TEALSModLoader;
import dev.axolotl.testmod.util.PistonBehaviorRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

import java.util.ArrayList;
import java.util.List;

@Mod(
    modid = TestMod.MODID,
    version = Tags.VERSION,
    name = "TestMod",
    acceptedMinecraftVersions = "[1.7.10]"
)
public class TestMod
{
    @Mod.Instance("TestMod")
    public static TestMod instance;
    public static final String MODID = "testmod";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(clientSide = "dev.axolotl.testmod.ClientProxy", serverSide = "dev.axolotl.testmod.CommonProxy")
    public static CommonProxy proxy;
    public static TEALSModLoader tealsModLoader;
    public static SimpleNetworkWrapper network;

    public static ArrayList<Item> itemIconList = new ArrayList<>();
    public static int tabIndex = 0;

    public static boolean isObfEnv = false;

    public TestMod() {
        isObfEnv = !(boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }

    //! Listeners
    TestModIcons testModIcons = new TestModIcons();
    TestModEvents testModEvents = new TestModEvents();

    public static TestMod getInstance() {return instance;}
    public static TEALSModLoader getTEALSInstance() {
        return tealsModLoader;
    }

    //! Items
    public static Item itemThrowingRock;
    public static Item itemTestItem;
    public static Item itemCrystal;
    public static Item itemElytra;
    public static Item itemElytraRocket;

    //! Blocks
    public static BlockFluidSulfuricAcid blockFluidSulfuric;
    public static BlockFluidChlorine blockFluidChlorine;
    public static BlockFluidMoltenSpacetime blockFluidMoltenSpacetime;
    public static BlockFluidNitrogendioxide blockFluidNitrogendioxide;
    public static BlockFluidTritium blockFluidTritium;
    public static BlockFluidTest blockFluidTest;
    public static BlockSlime blockSlime;
    public static BlockSoulTorch blockSoulTorch;
    public static BlockEndRod blockEndRod;
    public static BlockParticleTest blockParticleTest;
    public static BlockRedstoneTest blockRedstoneTest;
    //public static DoorTest doorTest;

    //! Fluids
    public static FluidSulfuricAcid fluidSulfuricAcid;
    public static FluidChlorine fluidChlorine;
    public static FluidMoltenSpacetime fluidMoltenSpacetime;
    public static FluidNitrogendioxide fluidNitrogendioxide;
    public static FluidTritium fluidTritium;
    public static FluidTest fluidTest;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) throws Exception
    {
        LOG.info("Reached testmod preInit func...");
        tealsModLoader = new TEALSModLoader();
        LOG.info("Calling proxy.preInit(event);");
        proxy.preInit(event);
        LOG.info("Registering listeners...");
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(testModIcons);
        MinecraftForge.EVENT_BUS.register(testModEvents);
        LOG.info("TEALS mod loader preInit...");
        tealsModLoader.preInit(event);

        //Fluids
        fluidSulfuricAcid = new FluidSulfuricAcid();
        fluidChlorine = new FluidChlorine();
        fluidMoltenSpacetime = new FluidMoltenSpacetime();
        fluidNitrogendioxide = new FluidNitrogendioxide();
        fluidTritium = new FluidTritium();
        fluidTest = new FluidTest();

        LOG.info("Registering fluids...");
        FluidRegistry.registerFluid(fluidSulfuricAcid.setUnlocalizedName("sulfuric_acid"));
        FluidRegistry.registerFluid(fluidChlorine.setUnlocalizedName("chlorine"));
        FluidRegistry.registerFluid(fluidMoltenSpacetime.setUnlocalizedName("molten_spacetime"));
        FluidRegistry.registerFluid(fluidNitrogendioxide.setUnlocalizedName("nitrogendioxide"));
        FluidRegistry.registerFluid(fluidTritium.setUnlocalizedName("tritium"));
        FluidRegistry.registerFluid(fluidTest.setUnlocalizedName("test"));

        // Items
        itemThrowingRock = new ItemThrowingRock().setUnlocalizedName("throwing_rock");
        itemTestItem = new ItemTestItem();
        itemCrystal = new ItemCrystal();
        itemElytra = new ItemElytra();
        itemElytraRocket = new ItemElytraRocket();

        // Blocks
        blockFluidSulfuric = new BlockFluidSulfuricAcid(fluidSulfuricAcid, Material.water);
        blockFluidChlorine = new BlockFluidChlorine(fluidChlorine, Material.water);
        blockFluidMoltenSpacetime = new BlockFluidMoltenSpacetime(fluidMoltenSpacetime, Material.water);
        blockFluidNitrogendioxide = new BlockFluidNitrogendioxide(fluidNitrogendioxide, Material.water);
        blockFluidTritium = new BlockFluidTritium(fluidTritium, Material.water);
        blockFluidTest = new BlockFluidTest(fluidTest, Material.water);
        blockSlime = new BlockSlime();
        blockSoulTorch = new BlockSoulTorch();
        blockEndRod = new BlockEndRod();
        blockParticleTest = new BlockParticleTest();
        blockRedstoneTest = new BlockRedstoneTest();
        //doorTest = new DoorTest();

        LOG.info("Registering items...");
        //! Items
        GameRegistry.registerItem(itemTestItem, itemTestItem.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(itemThrowingRock, itemThrowingRock.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(itemCrystal, itemCrystal.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(itemElytra, itemElytra.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(itemElytraRocket, itemElytraRocket.getUnlocalizedName().substring(5));

        LOG.info("Registering blocks...");
        //! Blocks
        GameRegistry.registerBlock(blockFluidSulfuric.setBlockName("sulfuric_acid"), "sulfuric_acid");
        GameRegistry.registerBlock(blockFluidChlorine.setBlockName("chlorine"), "chlorine");
        GameRegistry.registerBlock(blockFluidMoltenSpacetime.setBlockName("molten_spacetime"), "molten_spacetime");
        GameRegistry.registerBlock(blockFluidNitrogendioxide.setBlockName("nitrogendioxide"), "nitrogendioxide");
        //GameRegistry.registerBlock(doorTest.setBlockName("door_cleanroom"), "door_cleanroom");
        GameRegistry.registerBlock(blockFluidTritium.setBlockName("tritium"), "tritium");
        GameRegistry.registerBlock(blockFluidTest.setBlockName("test"), "test");
        GameRegistry.registerBlock(blockSlime.setBlockName("slime_block"), "slime_block");
        GameRegistry.registerBlock(blockSoulTorch.setBlockName("soul_torch"), "soul_torch");
        GameRegistry.registerBlock(blockEndRod.setBlockName("end_rod"), "end_rod");
        GameRegistry.registerBlock(blockParticleTest.setBlockName("particle_test"), "particle_test");
        GameRegistry.registerBlock(blockRedstoneTest.setBlockName("redstone_test"), "redstone_test");

        //LOG.info("Registering entities...");
        //! Entities
        // I like using an incremental index to set my IDs rather than writing 1, 2, 3, etc., so I never have
        // to worry about order or if I missed a number (doesn't really matter though)
        //int modEntityID = 0;

        // If you have a lot of Entities to register, consider creating a class with a static 'initEntity' method
        // so your main class stays tidy and readable
        //EntityRegistry.registerModEntity(EntityThrowingRock.class, "Throwing Rock", ++modEntityID, this, 64, 10, true);
        //EntityRegistry.registerModEntity(EntityEndermite.class, "Endermite", ++modEntityID, this, 64, 10, true);
        //TestMod.LOG.info("ENTITY ID: " + modEntityID);

        LOG.info("Registering renderer...");
        //! Renderer
        // Now that we've registered the entity, tell the proxy to register the renderer
        proxy.registerRenderers();
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
        proxy.registerEntities(this);
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
        PistonBehaviorRegistry.init();

        itemIconList.add(new ItemStack(BlocksModule.amethystOre).getItem());
        itemIconList.add(new ItemStack(BlocksModule.battery_block).getItem());
        itemIconList.add(new ItemStack(BlocksModule.checkered_block).getItem());
        itemIconList.add(new ItemStack(BlocksModule.super_tnt).getItem());
        itemIconList.add(new ItemStack(BlocksModule.crystalGrowingBlock).getItem());
        itemIconList.add(new ItemStack(BlocksModule.infectedBlock).getItem());
        itemIconList.add(new ItemStack(TestMod.blockFluidChlorine).getItem());
        itemIconList.add(new ItemStack(TestMod.blockSlime).getItem());
        itemIconList.add(new ItemStack(TestMod.blockFluidSulfuric).getItem());
        itemIconList.add(new ItemStack(TestMod.blockFluidNitrogendioxide).getItem());
        itemIconList.add(new ItemStack(TestMod.blockFluidMoltenSpacetime).getItem());
        itemIconList.add(new ItemStack(TestMod.blockFluidTritium).getItem());
        itemIconList.add(new ItemStack(TestMod.blockSoulTorch).getItem());

        itemIconList.add(new ItemStack(ItemsModule.crystalGrowing).getItem());
        itemIconList.add(new ItemStack(ItemsModule.lightningStick).getItem());
        itemIconList.add(new ItemStack(ItemsModule.rockSifter).getItem());
        itemIconList.add(new ItemStack(TestMod.itemTestItem).getItem());
        itemIconList.add(new ItemStack(TestMod.itemElytra).getItem());
        itemIconList.add(new ItemStack(TestMod.itemThrowingRock).getItem());
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event)
    {
        proxy.serverStarting(event);
        LOG.info("TEALS command handler...");
        tealsModLoader.onServerStart(event);
    }

    public static CreativeTabs tabTestMod = new CreativeTabs("tabTestMod")
    {
        @Override
        public Item getTabIconItem()
        {
            if(tabIndex >= itemIconList.size())
            {
                tabIndex = 0;
            }
            Item tabIcon = itemIconList.get(tabIndex);
            tabIndex++;


            return tabIcon;
        }
        @SideOnly(Side.CLIENT)
        @Override
        public void displayAllReleventItems(List p_78018_1_) {
            for (int i : TestModEntityList.eggIDs) {
                p_78018_1_.add(new ItemStack(Items.spawn_egg, 1, i));
            }
            super.displayAllReleventItems(p_78018_1_);
        }
    };
}
