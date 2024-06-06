package dev.axolotl.tealsmodloader.fluids;

import dev.axolotl.tealsmodloader.init.TestModIcons;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;

public class FluidNitrogendioxide extends Fluid
{
    public FluidNitrogendioxide()
    {
        super("nitrogendioxide");
        this.setUnlocalizedName("nitrogendioxide");

        //TODO: steal values from GT5
        this.setGaseous(false);
        this.setLuminosity(15);
    }

    @Override
    public IIcon getStillIcon() {
        return TestModIcons.nitrogendioxide_still;
    }

    @Override
    public IIcon getFlowingIcon() {
        return TestModIcons.nitrogendioxide_flowing;
    }
}
