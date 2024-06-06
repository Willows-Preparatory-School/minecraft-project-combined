package dev.axolotl.tealsmodloader.item.elytra;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemElytraRocket extends Item
{
    public ItemElytraRocket()
    {
        super();
        this.setUnlocalizedName("elytra_rocket");
        this.setTextureName(TestMod.MODID+"elytra_rocket");
        this.setCreativeTab(TestMod.tabTestMod);
    }

    @Override
    //public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        boolean fireworkLock = false; // to prevent the player from spamming and crashing the game.
        if(!fireworkLock) {
            if (MethodImitations.isElytraFlying(player)) {
                EntityFireworkRocket entityFireworkRocket = new EntityFireworkRocket(world, player.posX, player.posY, player.posZ, stack);
                world.spawnEntityInWorld(entityFireworkRocket);
                if (!player.capabilities.isCreativeMode) {
                    --stack.stackSize;
                }
                fireworkLock = true;
                if (MethodImitations.isElytraFlying(player)) {
                    Vec3 vec3 = player.getLookVec();
                    double d = 1.5D;
                    double e = 0.1D;
                    for (int i = 0; i < 10; i++) {
                        player.addVelocity(vec3.xCoord * e + (vec3.xCoord * d - player.motionX), vec3.yCoord * e + (vec3.yCoord * d * 1.8 - player.motionY), vec3.zCoord * e + (vec3.zCoord * d - player.motionZ));
                    }
                }
                fireworkLock = false;
            }
        }
        return stack;
    }
}
