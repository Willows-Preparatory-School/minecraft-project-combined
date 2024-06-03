package dev.axolotl.testmod.tealsmc.mods.items;

import dev.axolotl.testmod.TestMod;
import dev.axolotl.testmod.tealsmc.mods.blocks.BlocksModule;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class CrystalGrowingItem extends Item
{
    public CrystalGrowingItem()
    {
        super();
        this.setCreativeTab(TestMod.tabTestMod);
    }

    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz)
    {
        Block blockAtLoc = world.getBlock(x, y, z);
        if(blockAtLoc == BlocksModule.crystalGrowingBlock && world.getBlock(x, y+1, z) == Blocks.air)
        {
            Random random = new Random();
            int ranHeight = random.nextInt(11) + 10;
            // Fill gold blocks
            for (int i = 0; i < ranHeight; i++)
            {
                world.setBlock(x, y+i, z, Blocks.gold_block);
            }
            // Fill coal block
            world.setBlock(x, y, z, Blocks.coal_block);
        }
        return true;
    }
}
