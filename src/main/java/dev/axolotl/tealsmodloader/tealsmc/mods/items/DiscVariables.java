package dev.axolotl.tealsmodloader.tealsmc.mods.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import dev.axolotl.tealsmodloader.tealsmc.mods.entities.DiscEntity;

public class DiscVariables {
    public static DiscEntity e;
    public static EntityPlayer p;
    public static boolean moveBackward = false;
    public static boolean shouldEntityDie = false;
    public static boolean justMovedBackward = false;

    public static void moveDiscEntity(double speed) {
        /* This function is responsible for moving the 'DiscEntity' based on the direction it is looking in (rotationYaw) */

        // Stores the value of the direction the disc is looking in
        float yaw = e.rotationYaw;  // 'rotationYaw' is like a measure of what direction the entity is facing

        // Convert yaw to radians
        float radians = (float)Math.toRadians(yaw);

        // Calculate forward motion
        double motionX = -MathHelper.sin(radians) * speed;
        double motionZ = MathHelper.cos(radians) * speed;

        // Set the entity's motion
        e.motionX = motionX;
        e.motionZ = motionZ;
    }
}
