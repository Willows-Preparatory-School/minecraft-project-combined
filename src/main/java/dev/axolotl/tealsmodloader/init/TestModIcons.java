package dev.axolotl.tealsmodloader.init;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;

public class TestModIcons
{
    public TestModIcons()
    {
        TestMod.LOG.info("Registering IIcons...");
    }

    public static IIcon sulfuric_acid_still;
    public static IIcon sulfuric_acid_flowing;
    public static IIcon chlorine_still;
    public static IIcon chlorine_flowing;
    public static IIcon molten_spacetime_still;
    public static IIcon molten_spacetime_flowing;
    public static IIcon nitrogendioxide_still;
    public static IIcon nitrogendioxide_flowing;
    public static IIcon tritium_still;
    public static IIcon tritium_flowing;
    public static IIcon test_still;
    public static IIcon test_flowing;

    @SubscribeEvent
    public void registerTextures(TextureStitchEvent event) {
        //System.out.println("Registering IIcons...");
        switch (event.map.getTextureType()) {
            case 0:
                initBlockIcons(event.map);
                break;
            case 4:
                //ClientProxy.holoIcons.registerIcons(event.map);
                break;
        }
    }

    private void initBlockIcons(IIconRegister r)
    {
        sulfuric_acid_still = register(r, "fluids/sulfuric_acid");
        sulfuric_acid_flowing = register(r, "fluids/sulfuric_acid");
        chlorine_still = register(r, "fluids/chlorine");
        chlorine_flowing = register(r, "fluids/chlorine");
        molten_spacetime_still = register(r, "fluids/molten_spacetime");
        molten_spacetime_flowing = register(r, "fluids/molten_spacetime");
        nitrogendioxide_still = register(r, "fluids/nitrogendioxide");
        nitrogendioxide_flowing = register(r, "fluids/nitrogendioxide");
        tritium_still = register(r, "fluids/tritium");
        tritium_flowing = register(r, "fluids/tritium");
        test_still = register(r, "fluids/test");
        test_flowing = register(r, "fluids/test");
    }

    public static IIcon register(IIconRegister register, String name) {
        return register.registerIcon(TestMod.MODID + ":" + name);
    }
}
