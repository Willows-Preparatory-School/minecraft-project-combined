package dev.axolotl.testmod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dev.axolotl.testmod.TestMod;
import dev.axolotl.testmod.particles.TestModParticles;
import dev.axolotl.testmod.util.ShaderHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneLight;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.JsonException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class BlockParticleTest extends BlockRedstoneLight
{
    public static boolean shouldRenderBeacon = false;
    public static float angle = 0.0f;
    ShaderHandler shaderHandler = new ShaderHandler(Minecraft.getMinecraft());

    public BlockParticleTest()
    {
        super(true);
        this.setCreativeTab(TestMod.tabTestMod);
        this.setBlockTextureName(TestMod.MODID+":particle_test");
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
    public void onBlockAdded(World worldIn, int x, int y, int z) {
        if (!worldIn.isRemote)
        {
            if (!worldIn.isBlockIndirectlyGettingPowered(x, y, z))
            {
                // ???!! WHY
                //worldIn.scheduleBlockUpdate(x, y, z, this, 4);
                shouldRenderBeacon = false;
            }
            else if (worldIn.isBlockIndirectlyGettingPowered(x, y, z))
            {
                //worldIn.setBlock(x, y, z, Blocks.lit_redstone_lamp, 0, 2);
                shouldRenderBeacon = true;
            }
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    @Override
    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor)
    {
        if (!worldIn.isRemote)
        {
            if (!worldIn.isBlockIndirectlyGettingPowered(x, y, z))
            {
                //??? AGAIN WHY
                //worldIn.scheduleBlockUpdate(x, y, z, this, 4);
                shouldRenderBeacon = false;
            }
            else if (worldIn.isBlockIndirectlyGettingPowered(x, y, z))
            {
                //worldIn.setBlock(x, y, z, Blocks.lit_redstone_lamp, 0, 2);
                shouldRenderBeacon = true;
            }
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World worldIn, int x, int y, int z, Random random)
    {
        if (!worldIn.isRemote && !worldIn.isBlockIndirectlyGettingPowered(x, y, z))
        {
            //worldIn.setBlock(x, y, z, Blocks.redstone_lamp, 0, 2);
            shouldRenderBeacon = false;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World worldIn, int x, int y, int z, Random random)
    {
        /*
        double d0 = (float) x + 0.5F;
        double d1 = (float) y + 0.7F;
        double d2 = (float) z + 0.5F;
        double d3 = 0.2199999988079071D;
        double d4 = 0.27000001072883606D;
         */
        if(shouldRenderBeacon)
        {
            shaderHandler.loadShader();

            double sX = (float) x + 0.5F - 0.27000001072883606D;
            double sY = (float) y + 0.7F + 0.2199999988079071D + 1;
            double sZ = (float) z + 0.5F;

            // Circle thingy
            if(true) {
                //TestMod.LOG.info("shouldRenderBeacon == true!!!");

                double radius = 1.0;

                // Calculate the angle based on the current time

                // Convert angle to radians for sine/cosine calculations
                double angleRadians = Math.toRadians(angle);

                // Calculate the new X and Z coordinates
                double newX = sX + radius * Math.cos(angleRadians);
                double newZ = sZ + radius * Math.sin(angleRadians);

                TestModParticles.spawnCherryLeaf(worldIn, newX, sY, newZ);

                //TestMod.LOG.info("ANGLE: " + angle);

                if (angle >= 360)
                    angle = 0;
                else
                    angle += 20;
            }
            // static wireframe cube
            if(false)
            {
                // Define the vertices of a unit cube centered at (x, y, z)
                double[] vertices = {
                    sX - 0.5, sY - 0.5, sZ - 0.5,
                    sX + 0.5, sY - 0.5, sZ - 0.5,
                    sX + 0.5, sY - 0.5, sZ + 0.5,
                    sX - 0.5, sY - 0.5, sZ + 0.5,
                    sX - 0.5, sY + 0.5, sZ - 0.5,
                    sX + 0.5, sY + 0.5, sZ - 0.5,
                    sX + 0.5, sY + 0.5, sZ + 0.5,
                    sX - 0.5, sY + 0.5, sZ + 0.5
                };

                // Iterate over each vertex and spawn a particle
                for (int i = 0; i < vertices.length; i += 3) { // Process every third element (each vertex has 3 coordinates)
                    double vx = vertices[i];
                    double vy = vertices[i+1];
                    double vz = vertices[i+2];

                    // Spawn the particle
                    TestModParticles.spawnCherryLeaf(worldIn, vx, vy, vz);
                }
            }
        }
        else
        {
            shaderHandler.killShaders();
        }
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune)
    {
        return Item.getItemFromBlock(TestMod.blockParticleTest);
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, int x, int y, int z)
    {
        return Item.getItemFromBlock(TestMod.blockParticleTest);
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    @Override
    protected ItemStack createStackedBlock(int meta)
    {
        return new ItemStack(TestMod.blockParticleTest);
    }
}
