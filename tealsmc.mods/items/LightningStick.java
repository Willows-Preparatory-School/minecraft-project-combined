package dev.axolotl.testmod.tealsmc.mods.items;

import dev.axolotl.testmod.TestMod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LightningStick extends Item
{
    public LightningStick()
    {
        this.maxStackSize = 1;
        this.setCreativeTab(TestMod.tabTestMod);
        this.bFull3D = true;
        this.setFull3D();
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        // Send a ray out to get the right-clicked block.
        MovingObjectPosition mop = Minecraft.getMinecraft().renderViewEntity.rayTrace(200, 1.0F);
        // Is the result null?
        if(mop != null) {
            int blockHitSide = mop.sideHit;
        }
        /*
        double lookX = player.getLookVec().xCoord;
        double lookY = player.getLookVec().yCoord;
        double lookZ = player.getLookVec().zCoord;
         */
        double lookX = mop.blockX;
        double lookY = mop.blockY;
        double lookZ = mop.blockZ;

        //System.out.println("RIGHT CLICK: X: " + lookX + " Y: " + lookY + " Z: " + lookZ);

        // Create a new EntityLightningBolt object.
        EntityLightningBolt entitybolt = new EntityLightningBolt(world, lookX, lookY, lookZ);
        entitybolt.boltVertex = 100L;

        // Spawn the lightning bolt.
        world.spawnEntityInWorld(entitybolt);

        return stack;
    }
}
