package dev.axolotl.testmod.mixinplugin;

import dev.axolotl.testmod.TestMod;
import net.minecraft.launchwrapper.Launch;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestModMixinPlugin implements IMixinConfigPlugin
{

    public static final MixinEnvironment.Side side = MixinEnvironment.getCurrentEnvironment().getSide();

    @Override
    public void onLoad(String mixinPackage)
    {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        List<String> mixins = new ArrayList<>();

        if (false) {
            mixins.add("backlytra.MixinEntityPlayer");
            mixins.add("backlytra.MixinEntityLivingBase");
            mixins.add("backlytra.MixinNetHandlerPlayServer");
            mixins.add("backlytra.MixinEntityTrackerEntry");
            if (doesClassExist("thaumcraft/common/lib/events/EventHandlerEntity")) {
                mixins.add("backlytra.thaumcraft.MixinEventHandlerEntity");
            }
            if (side == MixinEnvironment.Side.CLIENT) {
                mixins.add("backlytra.client.MixinAbstractClientPlayer");
                mixins.add("backlytra.client.MixinEntityPlayerSP");
                mixins.add("backlytra.client.MixinRenderPlayer");
                mixins.add("backlytra.client.MixinModelBiped");
                mixins.add("backlytra.client.MixinEntityRenderer");
            }
        }
        mixins.add("ExampleMixin");
        mixins.add("minecraft.ElytraMoveEntityWithHeadingMixin");

        //if (ConfigMixins.betterPistons) {
            mixins.add("minecraft.MixinBlockPistonBase");
        //}

        if (side == MixinEnvironment.Side.CLIENT)
        {
        }

        return mixins;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        TestMod.LOG.info("Applied Mixin: " + mixinClassName + " to " + targetClassName);
    }

    private boolean doesClassExist(String path) {
        return TestMod.class.getResource("/" + path + ".class") != null;
    }
}
