package dev.axolotl.testmod.fluids;

import dev.axolotl.testmod.init.TestModIcons;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;

public class FluidChlorine extends Fluid
{
    public FluidChlorine()
    {
        super("chlorine");
        this.setUnlocalizedName("chlorine");

        // TODO: steal proper values from GT5
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
