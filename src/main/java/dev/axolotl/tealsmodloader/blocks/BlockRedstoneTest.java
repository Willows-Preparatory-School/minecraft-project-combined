package dev.axolotl.tealsmodloader.blocks;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockRedstoneTest extends BlockDirectional
{
    /*
    Ref:
    BlockRedstoneRepeater.java
    BlockRedstoneDiode.java
     */

    protected final boolean isPowered;

    public BlockRedstoneTest()
    {
        super(Material.circuits);
        this.setCreativeTab(TestMod.tabTestMod);
        this.isPowered = false;
    }

    @Override
    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor)
    {
        //TestMod.LOG.info("NAME: " + neighbor.getUnlocalizedName());
        super.onNeighborBlockChange(worldIn, x, y, z, neighbor);
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    @Override
    public boolean canProvidePower()
    {
        return true;
    }

    @Override
    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ)
    {
        return true;
    }

    @Override
    public int isProvidingStrongPower(IBlockAccess worldIn, int x, int y, int z, int side)
    {
        return this.isProvidingWeakPower(worldIn, x, y, z, side);
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess worldIn, int x, int y, int z, int side)
    {
        return 15;
    }

    protected boolean isGettingInput(World p_149900_1_, int p_149900_2_, int p_149900_3_, int p_149900_4_, int p_149900_5_)
    {
        return this.getInputStrength(p_149900_1_, p_149900_2_, p_149900_3_, p_149900_4_, p_149900_5_) > 0;
    }

    /**
     * Returns the signal strength at one input of the block. Args: world, X, Y, Z, side
     */
    protected int getInputStrength(World p_149903_1_, int x, int y, int z, int side)
    {
        TestMod.LOG.info("X: {} Y: {} Z: {} Side: {}", x, y, z, side);
        int i1 = getDirection(side);
        int j1 = x + Direction.offsetX[i1];
        int k1 = z + Direction.offsetZ[i1];
        int l1 = p_149903_1_.getIndirectPowerLevelTo(j1, y, k1, Direction.directionToFacing[i1]);
        return l1 >= 15 ? l1 : Math.max(l1, p_149903_1_.getBlock(j1, y, k1) == Blocks.redstone_wire ? p_149903_1_.getBlockMetadata(j1, y, k1) : 0);
    }

    @Override
    public void updateTick(World worldIn, int x, int y, int z, Random random)
    {
        int l = worldIn.getBlockMetadata(x, y, z);
        boolean flag = this.isGettingInput(worldIn, x, y, z, l);
        if (this.isPowered && !flag)
        {
            // not powered
        }
        else if (!this.isPowered)
        {
            //worldIn.setBlock(x, y, z, this.getBlockPowered(), l, 2);
            // powered
        }
    }
}
