package dev.axolotl.testmod.fluids;

import dev.axolotl.testmod.init.TestModIcons;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;

public class FluidTritium extends Fluid
{
    public FluidTritium()
    {
        super("tritium");
        this.setUnlocalizedName("tritium");

        // GT5
        this.setGaseous(false);
        this.setLuminosity(15);
    }

    @Override
    public IIcon getStillIcon()
    {
        return TestModIcons.tritium_still;
    }

    @Override
    public IIcon getFlowingIcon()
    {
        return TestModIcons.tritium_flowing;
    }
}
