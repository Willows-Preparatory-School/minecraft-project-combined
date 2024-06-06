package dev.axolotl.tealsmodloader.blocks;

import dev.axolotl.tealsmodloader.TestMod;
import dev.axolotl.tealsmodloader.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BaseBlock extends Block
{
    private Block mapColorBase;

    public BaseBlock(Material p_i45394_1_) {
        super(p_i45394_1_);
        setCreativeTab(TestMod.tabTestMod);
    }

    public BaseBlock setUnlocalizedNameWithPrefix(String name) {
        setBlockName((getNameDomain().isEmpty() ? "" : getNameDomain() + ".") + name);
        return this;
    }

    @Override
    public Block setBlockTextureName(String name) {
        return super.setBlockTextureName((getTextureDomain().isEmpty() ? "" : getTextureDomain() + ":")
            + (getTextureSubfolder().isEmpty() ? "" : getTextureSubfolder() + "/") + name);
    }

    public BaseBlock setNames(String name) {
        setUnlocalizedNameWithPrefix(name);
        setBlockTextureName(name);
        return this;
    }

    public BaseBlock setToolClass(String toolClass, int level) {
        for (int m = 0; m < 16; m++) {
            setHarvestLevel(toolClass, level, m);
        }
        return this;
    }

    public BaseBlock setToolClass(String toolClass, int level, int meta) {
        setHarvestLevel(toolClass, level, meta);
        return this;
    }

    public BaseBlock setBlockSound(SoundType type) {
        Utils.setBlockSound(this, type);
        return this;
    }

    public BaseBlock setMapColorBaseBlock(Block block) {
        mapColorBase = block;
        return this;
    }

    @Override
    public MapColor getMapColor(int p_149728_1_) {
        return mapColorBase == null ? super.getMapColor(p_149728_1_) : mapColorBase.getMapColor(p_149728_1_);
    }

    public String getTextureDomain() {
        return TestMod.MODID;
    }

    public String getTextureSubfolder() {
        return "";
    }

    public String getNameDomain() {
        return TestMod.MODID;
    }
}
