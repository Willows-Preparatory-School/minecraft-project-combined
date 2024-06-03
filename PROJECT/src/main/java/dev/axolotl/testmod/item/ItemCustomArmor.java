package dev.axolotl.testmod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dev.axolotl.testmod.ClientProxy;
import dev.axolotl.testmod.TestMod;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCustomArmor extends ItemArmor
{
    ItemCustomArmor(ArmorMaterial material, int renderIndex, int type)
    {
        super(material, renderIndex, type);

        this.setCreativeTab(TestMod.tabTestMod);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        // this can be used to add a variety of effects while the armor is worn
        // but since this is supposed to be a generic armor class for all kinds
        // of armor, we will not be using its
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, int armorSlot) {
        // adding all the armor models to a Map allows this method to be handled cleanly
        // if the Item is not in the map, get(this) returns null, which is perfect, since
        // returning null defaults to using the vanilla armor model
        return ClientProxy.armorModels.get(this);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        // Assuming all armors are named as "armor_name_chest", "armor_name_legs", etc.
        // then using the following format, the armor texture files should be named
        // "armor_name_layer_1" and "armor_name_layer_2"; layer 2 is for the legs
        String name = getUnlocalizedName().substring(5, getUnlocalizedName().lastIndexOf("_"));
        return String.format("%s:textures/armor/%s_layer_%d.png", TestMod.MODID, name, (slot == 2 ? 2 : 1));
        // If you are not familiar with String.format, the above statement is equivalent to:
		/*
		if (slot == 2) {
			return ArmorTutorial.MODID + ":textures/armor/" + name + "_layer_2.png";
		} else {
			return ArmorTutorial.MODID + ":textures/armor/" + name + "_layer_1.png";
		}

		// which could also be written as:

		return ArmorTutorial.MODID + ":textures/armor/" + name + (slot == 2 ? "_layer_2.png" : "_layer_1.png");
		 */
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        itemIcon = register.registerIcon(TestMod.MODID + ":" + getUnlocalizedName().substring(5));
    }
}
