package dev.axolotl.tealsmodloader.item.elytra;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class ItemElytra extends Item
{
    public ItemElytra()
    {
        super();
        this.setCreativeTab(TestMod.tabTestMod);
        this.setFull3D();
        this.setUnlocalizedName("elytra");
        this.setTextureName(TestMod.MODID+":elytra");
        this.maxStackSize = 1;
        //this.setMaxDurability(Backlytra.durability);
        this.setMaxDamage(32767);
        BlockDispenser.dispenseBehaviorRegistry.putObject(this, BlockDispenser.dispenseBehaviorRegistry.getObject(Items.iron_chestplate));
    }

    public static boolean isBroken(ItemStack stack) {
       //return stack.getMetadata() < stack.getMaxDurability() - 1;
       return stack.getItemDamage() < stack.getMaxDamage() -1 ;
       //return false;
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.leather;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        int entityequipmentslot = 3;
        ItemStack itemstack = playerIn.getEquipmentInSlot(entityequipmentslot);

        if (itemstack == null) {
            playerIn.setCurrentItemOrArmor(entityequipmentslot, itemStackIn.copy());
            itemStackIn.stackSize = 0;
            return itemStackIn;
        } else {
            return itemStackIn;
        }
    }

    @Override
    public boolean isValidArmor(ItemStack stack, int armorType, Entity entity) {
        return armorType == 1;
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int renderPass) {
        int color =
            stack.hasTagCompound() && stack.getTagCompound().hasKey("backlytra:elytraDye", Constants.NBT.TAG_ANY_NUMERIC)
                ? stack.getTagCompound().getInteger("backlytra:elytraDye")
                : -1;
        if(color == -1 || color == 15)
            return -1;

        //return ItemDye.dyeColors[color];
        return 1;
    }
}
