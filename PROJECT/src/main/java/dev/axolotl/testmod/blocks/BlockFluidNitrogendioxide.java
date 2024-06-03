package dev.axolotl.testmod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dev.axolotl.testmod.TestMod;
import dev.axolotl.testmod.misc.TestModDamageSource;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidNitrogendioxide extends BlockFluidClassic
{
    public BlockFluidNitrogendioxide(Fluid fluid, Material material)
    {
        super(fluid, material);
        this.setCreativeTab(TestMod.tabTestMod);
    }

    public static IIcon flowingIcon;
    public static IIcon stillIcon;

    @Override
    public IIcon getIcon(int side, int meta) {
        return (side == 0 || side == 1) ? stillIcon : flowingIcon;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        flowingIcon = register.registerIcon(TestMod.MODID + ":fluids/nitrogendioxide");
        stillIcon = register.registerIcon(TestMod.MODID + ":fluids/nitrogendioxide");
    }

    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
        if (world.getBlock(x, y, z).getMaterial().isLiquid()) return false;
        return super.canDisplace(world, x, y, z);
    }

    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
        if (world.getBlock(x, y, z).getMaterial().isLiquid()) return false;
        return super.displaceIfPossible(world, x, y, z);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, int x, int y, int z, Entity entityIn)
    {
        if(entityIn != null)
            entityIn.attackEntityFrom(TestModDamageSource.getBurnDamage(), 2.5f);
    }
}
