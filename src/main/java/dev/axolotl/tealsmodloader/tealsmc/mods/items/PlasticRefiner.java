package dev.axolotl.tealsmodloader.tealsmc.mods.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dev.axolotl.tealsmodloader.tealsmc.mods.blocks.BlocksModule;


public class PlasticRefiner extends Item {

    public PlasticRefiner() {
        this.setMaxStackSize(1); // max stack size is 1
        this.setCreativeTab(CreativeTabs.tabTools); // sets to type tools
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        ItemStack [] inventory = player.inventory.mainInventory; // creates array with players inventory
        for(int i = 0; i < inventory.length; i++){ // iterates through players inventory
            if (inventory[i] == null){ // if the inventory slot is empty it continues to search
                continue;
            }
            String itemName = inventory[i].getItem().getUnlocalizedName(); // if not empty, get the name of the item
            if (itemName.equals(ItemsModule.unrefinedPlastic.getUnlocalizedName())) { // checks if the item in inventory index i is unrefined plastic
                int num = inventory[i].stackSize; // sets num to the amount of items at index i
                double randomChance = Math.random(); // sets a random double
                int count = 0; // initializes count to 0

                if(randomChance > 0.999){ // if r.c is greater than 0.999 then it spawns a rapier
                    inventory[i] = new ItemStack(ItemsModule.rapier);
                } else if(randomChance >= 0.975){ // if 0.999 >= r.c. > 0.975 it yields a 10% chance drop diamond for each item
                    for(int z = 0; z < num; z++){
                        if (Math.random() > 0.9)
                            count++;
                    }
                    inventory[i] = new ItemStack(Items.diamond,count);
                } else if(randomChance > 0.95){ // if 0.975 > r.c. > 0.95 it yields a 10% chance drop emerald for each item
                    for(int z = 0; z < num; z++){
                        if (Math.random() > 0.9)
                            count++;
                    }
                    inventory[i] = new ItemStack(Items.emerald,count);
                } else if(randomChance > 0.9){ // if 0.95 >= r.c. > 0.9 it yields a 10% chance drop for gold ingots for each item
                    for(int z = 0; z < num; z++){
                        if (Math.random() > 0.9)
                            count++;
                    }
                    inventory[i] = new ItemStack(Items.gold_ingot,count);
                } else if(randomChance > 0.85){ // if 0.9 >= r.c. > 0.85 it yields a 20% chance drop iron ingots for each item
                    for(int z = 0; z < num; z++){
                        if (Math.random() > 0.8)
                            count++;
                    }
                    inventory[i] = new ItemStack(Items.iron_ingot,count);
                } else if (randomChance > 0.8) { // if 0.85 >= r.c. > 0.8 yield a printer
                    inventory[i] = new ItemStack(BlocksModule.printer);
                } else if(randomChance < 0.8){ // if r.c. < 0.8 it yields a 30% chance to drop filament for each item.
                    for(int z = 0; z < num; z++){
                        if (Math.random() > 0.7)
                            count++;
                    }
                    inventory[i] = new ItemStack(ItemsModule.filament,count);
                }
            }
        }
        return stack;
    }
}
