package dev.axolotl.tealsmodloader.item;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemTestItem extends Item
{
    public ItemTestItem()
    {
        super();
        this.setFull3D();
        this.setUnlocalizedName("test_item");
        this.setTextureName(TestMod.MODID+":test_item");
        this.setCreativeTab(TestMod.tabTestMod);
    }
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        player.addChatMessage(new ChatComponentText("I've been right clicked!!! ^-^").setChatStyle(new ChatStyle().setBold(true).setColor(EnumChatFormatting.LIGHT_PURPLE)));
        return stack;
    }

    /*
    public void addDetails(ItemStack itemstack, EntityPlayer player, List infos)
    {
        infos.add(ChatFormatting.LIGHT_PURPLE + "Test! :3");
        this.addInformation(itemstack, player, infos, true);
    }
     */
}
