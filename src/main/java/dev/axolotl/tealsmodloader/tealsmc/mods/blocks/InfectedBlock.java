package dev.axolotl.tealsmodloader.tealsmc.mods.blocks;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import java.util.Random;

public class InfectedBlock extends Block
{
    public InfectedBlock()
    {
        super(Material.glass);
        this.setCreativeTab(TestMod.tabTestMod);
        this.setStepSound(Block.soundTypeCloth);
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        // Define possible offsets
        int[] offsets = {-1, 0, 1};

        // loop through x, y, z
        for (int dx : offsets) {
            for (int dy : offsets) {
                for (int dz : offsets) {

                    if(random.nextInt(2) != 0)
                        break;

                    // the block's own pos
                    if (dx == 0 && dy == 0 && dz == 0) continue;

                    // get new position
                    int newX = x + dx;
                    int newY = y + dy;
                    int newZ = z + dz;

                    // Check if the new position is within the world bounds
                    /*
                    if (newX >= 0 && newX < world.getWorldInfo().getSpawnLimit() &&
                        newY >= 0 && newY < world.getWorldInfo().getSpawnLimit() &&
                        newZ >= 0 && newZ < world.getWorldInfo().getSpawnLimit()) {
                     */
                    if(true)
                    {

                        // get new block pos
                        Block blockAtNewPos = world.getBlock(newX, newY, newZ);

                        //System.out.println("Selected block: " + blockAtNewPos.getLocalizedName());
                        if(blockAtNewPos.getBlockHardness(world, newX, newY, newZ) < 10)
                        {
                            if(blockAtNewPos.getMaterial() != Material.air)
                            {
                                world.setBlock(newX, newY, newZ, BlocksModule.infectedBlock);
                            }
                        }
                    }
                }
            }
        }
    }
}
