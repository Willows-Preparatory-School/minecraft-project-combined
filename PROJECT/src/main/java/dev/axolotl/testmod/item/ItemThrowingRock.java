package dev.axolotl.testmod.item;

import dev.axolotl.testmod.TestMod;
import dev.axolotl.testmod.entity.EntityThrowingRock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemThrowingRock extends BaseTestModItem
{
    // no more ID parameter
    public ItemThrowingRock()
    {
        super();
        // we'll set the max stack size and creative tab from here:
        setMaxStackSize(64);
        setCreativeTab(TestMod.tabTestMod);
        setUnlocalizedName("throwing_rock");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
        if (!player.capabilities.isCreativeMode)
        {
            --itemstack.stackSize;
        }

        world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        // IMPORTANT! Only spawn new entities on the server. If the world is not remote,
        // that means you are on the server:
        if (!world.isRemote)
        {
            world.spawnEntityInWorld(new EntityThrowingRock(world, player));
        }

        return itemstack;
    }
}
