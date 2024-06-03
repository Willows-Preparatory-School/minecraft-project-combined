package dev.axolotl.testmod.fluids;

import dev.axolotl.testmod.TestMod;
import dev.axolotl.testmod.init.TestModIcons;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;

public class FluidSulfuricAcid extends Fluid
{
    public FluidSulfuricAcid()
    {
        super("sulfuric_acid");

        this.setUnlocalizedName("sulfuric_acid");
        // TODO: Set these to proper values, aka steal from GT5
        //this.setViscosity(6000);
        this.setLuminosity(15);
        this.setTemperature(2000);
        this.setGaseous(false);
    }

    @Override
    public IIcon getStillIcon() {
        return TestModIcons.sulfuric_acid_still;
    }

    @Override
    public IIcon getFlowingIcon() {
        return TestModIcons.sulfuric_acid_flowing;
    }
}
