package dev.axolotl.tealsmodloader.fluids;

import dev.axolotl.tealsmodloader.init.TestModIcons;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;

public class FluidMoltenSpacetime extends Fluid
{
    public FluidMoltenSpacetime()
    {
        super("molten_spacetime");
        this.setUnlocalizedName("molten_spacetime");

        // TODO: get values from GT5
        this.setGaseous(false);
        this.setLuminosity(15);
    }

    @Override
    public IIcon getStillIcon() {
        return TestModIcons.chlorine_still;
    }

    @Override
    public IIcon getFlowingIcon() {
        return TestModIcons.chlorine_flowing;
    }
}
