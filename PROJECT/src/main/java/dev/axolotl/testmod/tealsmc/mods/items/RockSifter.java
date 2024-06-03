package dev.axolotl.testmod.tealsmc.mods.items;

import dev.axolotl.testmod.TestMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Objects;

public class RockSifter extends Item {

    public RockSifter()
    {
        this.maxStackSize = 64;
        this.bFull3D = true;
        this.setCreativeTab(TestMod.tabTestMod);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        //System.out.println("Right clicked!!! PlayerName: " + player.getDisplayName() + " World: " + world.getProviderName());
        ItemStack[] inventory = player.inventory.mainInventory;

        for(int i = 0; i < inventory.length; i++)
        {
            //String itemName = Objects.requireNonNull(stack.getItem()).getUnlocalizedName();
            if(inventory[i] != null) {
                String itemName = inventory[i].getItem().getUnlocalizedName();
                if (itemName.equals(Blocks.sand.getUnlocalizedName()))
                {
                    int goldNum = 0;
                    for(int j = 0; j < inventory[i].stackSize; j++) {
                        if (Math.random() < 0.10) {
                            goldNum++;
                        }
                    }
                    inventory[i] = new ItemStack(Items.gold_nugget);
                    inventory[i].stackSize = goldNum;
                }
            }
        }

        return stack;
    }
}
