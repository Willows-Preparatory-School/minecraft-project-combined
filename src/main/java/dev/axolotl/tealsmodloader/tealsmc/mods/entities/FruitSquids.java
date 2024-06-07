package dev.axolotl.tealsmodloader.tealsmc.mods.entities;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.world.World;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSquid;
import net.minecraft.util.ResourceLocation;


import java.util.Random;

public class FruitSquids extends EntitySquid {
    private static final ResourceLocation[] SQUID_TEXTURES = {
            new ResourceLocation("tealsmc:textures/entity/squid/strawberrysquid.png"),
            new ResourceLocation("tealsmc:textures/entity/squid/grapesquid.png"),
            new ResourceLocation("tealsmc:textures/entity/squid/orangesquid.png")
    };

    private final ResourceLocation texture;

    public FruitSquids(World world) {
        super(world);
        this.texture = getRandomTexture();
    }

    private ResourceLocation getRandomTexture() {
        Random random = new Random();
        return SQUID_TEXTURES[random.nextInt(SQUID_TEXTURES.length)];
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }
}

//    public static void registerEntity() {
//        EntityRegistry.registerModEntity(new ResourceLocation("tealsmc:fruit_squid"), FruitSquids.class, "FruitSquid", 1, "tealsmc", 80, 3, true);
//    }
//
//    public static void registerRenderer() {
//        RenderingRegistry.registerEntityRenderingHandler(FruitSquids.class, new RenderSquid(new RenderManager(null, null), new ModelSquid() {
//
//            private ResourceLocation getEntityTexture(Entity entity) {
//                return ((FruitSquids) entity).getTexture();
//            }
//        }, 0.7F));
//    }
//}
