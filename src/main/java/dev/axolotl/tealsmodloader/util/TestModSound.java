package dev.axolotl.tealsmodloader.util;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;

import java.util.ArrayList;
import java.util.List;

public class TestModSound {
    private static final List<TestModCustomSound> SOUNDS_REGISTRY = new ArrayList<>();

    public static List<TestModCustomSound> getSounds() {
        return SOUNDS_REGISTRY;
    }

    public static final SoundType soundSlime = new TestModCustomSound("slime_block") {
        @Override
        public SoundType getDisabledSound() {
            return this;
        }
    };

    public static class TestModCustomSound extends SoundType {
        private final boolean placeSound;
        private SoundType disabledSound;

        public TestModCustomSound(String name, boolean placeSound) {
            this(name, 1, 1, placeSound);
        }

        public TestModCustomSound(String name, float volume, float pitch, boolean placeSound) {
            super(name, volume, pitch);
            this.placeSound = placeSound;
            SOUNDS_REGISTRY.add(this);
            disabledSound = Block.soundTypeStone;
        }

        public TestModCustomSound(String name, float volume, float pitch) {
            this(name, volume, pitch, false);
        }

        public TestModCustomSound(String name) {
            this(name, 1f, 1f);
        }


        @Override
        public String getBreakSound() {
            return TestMod.MODID + ":block." + soundName + ".break";
        }

        @Override
        public String getStepResourcePath() {
            return TestMod.MODID + ":block." + soundName + ".step";
        }

        @Override
        public String func_150496_b() {
            return placeSound ? TestMod.MODID + ":block." + soundName + ".place" : this.getBreakSound();
        }

        public SoundType getDisabledSound() {
            return disabledSound;
        }

        protected SoundType setDisabledSound(SoundType type) {
            disabledSound = type;
            return this;
        }
    }
}
