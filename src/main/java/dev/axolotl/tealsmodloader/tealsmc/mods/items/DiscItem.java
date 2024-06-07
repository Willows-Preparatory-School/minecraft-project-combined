package dev.axolotl.tealsmodloader.tealsmc.mods.items;

import dev.axolotl.tealsmodloader.TestMod;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import dev.axolotl.tealsmodloader.tealsmc.mods.entities.DiscEntity;
import net.minecraft.creativetab.CreativeTabs;

public class DiscItem extends Item {
    public DiscItem() {
        setMaxStackSize(1);
        setCreativeTab(TestMod.tabTestMod);
    }


    /* Responsible for the core function of the 'DiscItem' item and 'DiscEntity" entity. The core function is that when
    'DiscItem' item is rightclicked, a 'DiscEntity' entity is spawned. The entity moves forward seemingly towards the
     direction of the player's crosshair, and then moves back towards the player while simultaneously creating explosions along the way.
     Lastly, this entity is force killed once it reaches a certain proximity from the player. */

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        /* When the player right-clicks the 'DiscItem' item, the player's inventory is searched to find the disc and
        enchant it so that it has a glow. Then, a 'DiscEntity" entity is created and spawned in the world with the
        same location and angles of the player. Lastly, the entity is moved forward in the direction it's facing which is also
        the direction of the player is facing. The effect of this is that it seems as if the entity moves towards the direction
        of the crosshair far away.*/

        // Create Spinning Disc Entity
        DiscVariables.e = new DiscEntity(world);
        // Set new Entity with the same coordinates and angles as the player currently
        DiscVariables.e.copyLocationAndAnglesFrom(player);

        if (!world.isRemote) {  // Checks if the world is not on the server that the client is connected to
            // Without this check, the entity would be spawned twice
            world.spawnEntityInWorld(DiscVariables.e);  // Spawns the entity in world
        }

        // Moves entity forward in the directions it's facing
        DiscVariables.moveDiscEntity(15);

        DiscVariables.p = player;
        // The entity moved forward and now has to move backward towards the player. So, 'moveBackward' is set to true.
        DiscVariables.moveBackward = true;

        return itemStack;
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        if (DiscVariables.shouldEntityDie) {
        /* Responsible for killing the 'DiscEntity' entity when in close enough proximity with the player or create an
        explosion in the entity's current position if it's a certain distance away from the player. */

            // 'justMovedBackwards' allows for more frames to pass so that the Spinning Disc entity can actually move backward and not be instantly set to dead
            if (DiscVariables.e.posX == DiscVariables.e.prevPosX && DiscVariables.e.posZ == DiscVariables.e.prevPosZ && !DiscVariables.justMovedBackward) {  // Checks if the 'Disc Entity' entity is standing still
                DiscVariables.e.setDead();
                DiscVariables.shouldEntityDie = false;
            } else if (DiscVariables.e.getDistanceToEntity(DiscVariables.p) > 10) {  // Checks if the distance between 'DiscEntity' entity and the player is greater than 10
                // If the spinning disc entity is far away enough from the player so that an explosion would not affect the player,
                // then an explosion is created in the spinning disc entity's current position
                DiscVariables.e.worldObj.createExplosion(null, DiscVariables.e.posX, DiscVariables.e.posY, DiscVariables.e.posZ, 4.0F, true);
                DiscVariables.justMovedBackward = false;
            }
        }

        if (DiscVariables.moveBackward) {
            /* Responsible for moving the entity in the opposite direction it went, towards the player. */

            if (DiscVariables.e.motionX == 0 && DiscVariables.e.motionZ == 0) {  // Checks if 'DiscEntity' entity is standing still
                DiscVariables.moveDiscEntity(-25);

                DiscVariables.moveBackward = false;
                DiscVariables.justMovedBackward = true;
                DiscVariables.shouldEntityDie = true;
            }
        }
    }


    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        /* When player left clicks on an entity while holding the 'DiscItem' item, that entity is immidietly killed.
        However, nothing is dropped by the entity as it would make this weapon too overpowered. */

        entity.setDead();
        return true;
    }
}
