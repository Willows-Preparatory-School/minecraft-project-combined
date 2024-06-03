package dev.axolotl.testmod.asm;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

@IFMLLoadingPlugin.Name("TestMod")
@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions("dev.axolotl.testmod.asm")
@IFMLLoadingPlugin.SortingIndex(1001)
public class TestModLoadingPlugin implements IFMLLoadingPlugin
{

    @Override
    public String[] getASMTransformerClass() {
        return new String[] {
            "dev.axolotl.testmod.asm.TestModTransformer"
        };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

}
