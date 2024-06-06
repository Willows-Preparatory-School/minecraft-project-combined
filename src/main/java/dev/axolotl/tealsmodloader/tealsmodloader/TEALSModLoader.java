package dev.axolotl.tealsmodloader.tealsmodloader;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import dev.axolotl.tealsmodloader.TestMod;
import dev.axolotl.tealsmodloader.tealsmc.mods.entities.EntityMolotov;
import dev.axolotl.tealsmodloader.tealsmodloader.builtin.entity.RobotRender;
import dev.axolotl.tealsmodloader.tealsmodloader.commands.SpawnEntityCommand;
import dev.axolotl.tealsmodloader.tealsmodloader.entity.BaseEntityModifier;
import dev.axolotl.tealsmodloader.tealsmodloader.entity.EntityListener;
import dev.axolotl.tealsmodloader.tealsmodloader.util.EntityInfo;
import dev.axolotl.tealsmodloader.tealsmodloader.util.registry.Registry;
import dev.axolotl.tealsmodloader.tealsmodloader.entity.registry.EntitySpawnInfo;
import dev.axolotl.tealsmodloader.tealsmodloader.module.Module;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import dev.axolotl.tealsmodloader.tealsmc.mods.blocks.BlocksModule;
import dev.axolotl.tealsmodloader.tealsmc.mods.entities.EntitiesModule;
import dev.axolotl.tealsmodloader.tealsmc.mods.items.ItemsModule;

import java.util.*;

public class TEALSModLoader
{
    private HashMap<String, EntityInfo> entityLookup = new HashMap<String, EntityInfo>();
    public int numMods = 0;

    /**
     * Block registry, automatically registers new blocks to the GameRegistry.
     * Used by calling with a block identifier name, block instance, and an optional display name.
     */
    public Registry<Block> blockRegistry = new Registry<Block>() {
        @Override
        protected void register(String key, Block type, Object... args) {
            type.setBlockName(key);
            type.setBlockTextureName(TestMod.MODID + ":" + key);

            GameRegistry.registerBlock(type, key);

            if (args != null && args.length > 0) {
                LanguageRegistry.addName(type, args[0].toString());
            }
        }
    };

    /**
     * Item registry, automatically registers new items to the GameRegistry.
     * Used by calling with an identifier name, item instance, and an optional display name.
     */
    public Registry<Item> itemRegistry = new Registry<Item>() {
        @Override
        protected void register(String key, Item type, Object... args) {
            type.setUnlocalizedName(key);
            type.setTextureName(TestMod.MODID + ":" + key);

            GameRegistry.registerItem(type, key);

            if (args != null && args.length > 0) {
                LanguageRegistry.addName(type, args[0].toString());
            }
        }
    };

    /**
     * Entity registry, automatically registers new entities to the GameRegistry.
     * Used by calling with an identifier name, entity class, and an optional (in any order)... texture name or spawn info.
     */
    public Registry<Class<? extends EntityLiving>> entityRegistry = new Registry<Class<? extends EntityLiving>>() {
        @Override
        protected void register(String key, Class<? extends EntityLiving> type, Object... args) {
            int uniqueId = EntityRegistry.findGlobalUniqueEntityId();
            EntityRegistry.registerGlobalEntityID(type, key, uniqueId);

            if (args != null && args.length > 0) {
                for (Object argument : args) {
                    if (argument instanceof EntitySpawnInfo) {
                        EntitySpawnInfo info = (EntitySpawnInfo) argument;

                        EntityRegistry.addSpawn(type,
                            info.getWeightedSpawnChance(),
                            info.getMinPerPack(),
                            info.getMaxPerPack(),
                            info.getCreatureType(),
                            info.getBiomes().toArray(new BiomeGenBase[0]));
                    } else if (argument instanceof String) {
                        RenderingRegistry.registerEntityRenderingHandler(
                            type,
                            new RobotRender(argument.toString() + ".png")
                        );
                    }
                }
            }

            entityLookup.put(key, new EntityInfo(type, uniqueId));
        }
    };

    /**
     * Event registry, will register listener objects to the Minecraft Forge event bus.
     */
    public Registry<Object> listenerRegistry = new Registry<Object>() {
        protected void register(String key, Object type, Object... args) {
            MinecraftForge.EVENT_BUS.register(type);
        }
    };

    private Collection<BaseEntityModifier> entityModifiers = new ArrayList<BaseEntityModifier>();

    public void onServerStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new SpawnEntityCommand());
    }

    public void preInit(FMLPreInitializationEvent event, TestMod instance) throws Exception
    {
        //Initalize listeners
        listenerRegistry.newInstance("entityListener", new EntityListener());

        System.out.println("Looking for modules... ");

        //Find all modules
        //List<Class<Module>> classes = ReflectionUtil.getClassNamesFromPackage("tealsmc.mods", Module.class);
        List<Class> classes = new ArrayList<>();
        // Add extra modules here.
        // Example classes.add(MyEpicModule.class);
        classes.add(ItemsModule.class);
        classes.add(BlocksModule.class);
        classes.add(EntitiesModule.class);
        System.out.println(classes);
        for (Class c : classes)
        {
            Module create = (Module) c.newInstance();
            System.out.println("Loading module: " + c.getSimpleName());

            create.parentMod = this;
            create.onLoad();
        }

        System.out.println("Found " + classes.size());
        numMods = classes.size();

        System.out.println("Init entities...");
        int modEntityID = 10; // Starting at 10, just in case :>
        // Example of registering an entity.
        /*
        EntityRegistry.registerModEntity(EntityMolotov.class, "Molotov", ++modEntityID, instance, 64, 10, true);
         */


        //Keep the world time forced to mid day.
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (Minecraft.getMinecraft().thePlayer != null) {
                    if (Minecraft.getMinecraft().getIntegratedServer().worldServers.length > 0) {
                        WorldServer worldServer = Minecraft.getMinecraft().getIntegratedServer().worldServers[0];
                        if (worldServer != null) {
                            //worldServer.setWorldTime(6000);
                        }
                    }
                }
            }
        }, 0, 200);

        // Example of registering a new entity renderer.
        /*
        RenderingRegistry.registerEntityRenderingHandler(EntityMolotov.class, new RenderSnowball(ItemsModule.molotov);
        */
    }

    public void registerEntityModifier(BaseEntityModifier modifier) {
        entityModifiers.add(modifier);
    }

    public Collection<BaseEntityModifier> getEntityModifiers() {
        return entityModifiers;
    }

    public Collection<String> getEntityNames() {
        return entityLookup.keySet();
    }

    public HashMap<String, EntityInfo> getEntityLookup() {
        return entityLookup;
    }

    /**
     * Can be used to display debug messages in game chat.
     * Useful for debugging code without monitoring both the game and the IDE.
     *
     * @param message log message to display.
     */
    public static void log(String message) {
        if (Minecraft.getMinecraft().thePlayer != null) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
                EnumChatFormatting.GOLD + "(INFO) " + EnumChatFormatting.GRAY + message));
        }
    }

}
