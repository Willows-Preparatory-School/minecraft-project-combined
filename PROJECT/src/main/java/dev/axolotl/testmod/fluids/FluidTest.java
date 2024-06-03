package dev.axolotl.testmod.fluids;

import dev.axolotl.testmod.init.TestModIcons;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;

public class FluidTest extends Fluid
{
    public FluidTest()
    {
        super("test");
        this.setUnlocalizedName("test");

        //! The funny. :3
        this.setGaseous(false);
        this.setLuminosity(15);
        this.setTemperature(9823);
    }

    @Override
    public IIcon getStillIcon() {
        return TestModIcons.test_still;
    }

    @Override
    public IIcon getFlowingIcon() {
        return TestModIcons.test_flowing;
    }
}
