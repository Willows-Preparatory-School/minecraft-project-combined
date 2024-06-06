package dev.axolotl.tealsmodloader.item.elytra;

import dev.axolotl.tealsmodloader.TestMod;
import dev.axolotl.tealsmodloader.misc.TestModDamageSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class Elytra
{
    public Elytra()
    {

    }

    public static void updateElytra(EntityLivingBase e) {
        boolean flag = MethodImitations.isElytraFlying(e);

        if (flag && !e.onGround && !e.isRiding() && !e.isInWater()) {
            ItemStack itemstack = e.getEquipmentInSlot(3);

            if (itemstack != null && itemstack.getItem() == TestMod.itemElytra && ItemElytra.isBroken(itemstack)) {
                flag = true;

                if (!e.worldObj.isRemote && (MethodImitations.getTicksElytraFlying(e) + 1) % 20 == 0) {
                    itemstack.damageItem(1, e);
                }
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }

        if (!e.worldObj.isRemote) {
            MethodImitations.setElytraFlying(e, flag);
        }

        if (MethodImitations.isElytraFlying(e)) {
            FieldImitations.set(e, "ticksElytraFlying", FieldImitations.get(e, "ticksElytraFlying", 0)+1);
        } else {
            FieldImitations.set(e, "ticksElytraFlying", 0);
        }
    }

    public static boolean moveEntityWithHeading(EntityLivingBase e, float strafe, float forward) {
        if (e.field_70135_K && !e.isInWater() && !e.handleLavaMovement()) { // e.isServerWorld = e.field_70135_K (???)
            if (MethodImitations.isElytraFlying(e)) {
                if (e.motionY > -0.5D) {
                    e.fallDistance = 1.0F;
                }

                Vec3 vec3d = e.getLookVec();
                float f = e.rotationPitch * 0.017453292F;
                double d6 = Math.sqrt(vec3d.xCoord * vec3d.xCoord + vec3d.zCoord * vec3d.zCoord);
                double d8 = Math.sqrt(e.motionX * e.motionX + e.motionZ * e.motionZ);
                double d1 = vec3d.lengthVector();
                float f4 = MathHelper.cos(f);
                f4 = (float) ((double) f4 * (double) f4 * Math.min(1.0D, d1 / 0.4D));
                e.motionY += -0.08D + f4 * 0.06D;

                if (e.motionY < 0.0D && d6 > 0.0D) {
                    double d2 = e.motionY * -0.1D * f4;
                    e.motionY += d2;
                    e.motionX += vec3d.xCoord * d2 / d6;
                    e.motionZ += vec3d.zCoord * d2 / d6;
                }

                if (f < 0.0F) {
                    double d9 = d8 * (-MathHelper.sin(f)) * 0.04D;
                    e.motionY += d9 * 3.2D;
                    e.motionX -= vec3d.xCoord * d9 / d6;
                    e.motionZ -= vec3d.zCoord * d9 / d6;
                }

                if (d6 > 0.0D) {
                    e.motionX += (vec3d.xCoord / d6 * d8 - e.motionX) * 0.1D;
                    e.motionZ += (vec3d.zCoord / d6 * d8 - e.motionZ) * 0.1D;
                }

                e.motionX *= 0.9900000095367432D;
                e.motionY *= 0.9800000190734863D;
                e.motionZ *= 0.9900000095367432D;
                e.moveEntity(e.motionX, e.motionY, e.motionZ);

                if (e.isCollidedHorizontally && !e.worldObj.isRemote) {
                    double d10 = Math.sqrt(e.motionX * e.motionX + e.motionZ * e.motionZ);
                    double d3 = d8 - d10;
                    float f5 = (float) (d3 * 10.0D - 3.0D);

                    if (f5 > 0.0F) {
                        e.playSound((int) f5 > 4 ? "game.player.hurt.fall.big" : "game.player.hurt.fall.small", 1.0F, 1.0F);
                        e.attackEntityFrom(TestModDamageSource.getFlyIntoWall(), f5);
                    }
                }

                if (e.onGround && !e.worldObj.isRemote) {
                    MethodImitations.setElytraFlying(e, false);
                }
                return true;
            }
        }
        return false;
    }


}
