package dev.axolotl.tealsmodloader.tealsmc.mods.items;

import dev.axolotl.tealsmodloader.TestMod;
import ibxm.Player;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GiftBox extends Item {
    public GiftBox(){

        setMaxStackSize(64);// maximum stack is 64
        setCreativeTab(TestMod.tabTestMod);

    }
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        // this method makes it so that when you right-click, the gift box drops a golden apple
        final Item apple = Items.golden_apple; // gets a golden apple item
        final ItemStack yourItem = new ItemStack(apple);
        player.dropItem(yourItem.getItem(), 1); // this ensures that our item, the golden apple, is dropped
        return yourItem; // returns our golden apples
    }
}
