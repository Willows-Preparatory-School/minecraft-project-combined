package dev.axolotl.testmod;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dev.axolotl.testmod.item.elytra.Elytra;
import dev.axolotl.testmod.item.elytra.FieldImitations;
import dev.axolotl.testmod.item.elytra.LayerBetterElytra;
import dev.axolotl.testmod.item.elytra.MethodImitations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.WorldEvent;
import org.lwjgl.opengl.GL11;

import java.util.Set;

public class TestModEvents
{

    public TestModEvents()
    {
        TestMod.LOG.info("Registering TestModEvents...");
    }

    @SubscribeEvent(priority= EventPriority.LOWEST)
    public void onLivingTick(LivingEvent.LivingUpdateEvent e)
    {
        if (e.isCanceled()) return;
        TestMod.proxy.update(e.entityLiving);
        Elytra.updateElytra(e.entityLiving);
    }

    @SubscribeEvent
    public void onPostPlayerTick(TickEvent.PlayerTickEvent e) {
        if (e.phase == TickEvent.Phase.END) {
            boolean isElytraFlying = MethodImitations.isElytraFlying(e.player);
            if (e.player instanceof EntityPlayerMP && isElytraFlying) {
                ((EntityPlayerMP)e.player).playerNetServerHandler.floatingTickCount = 0; // floatingTickCount = field_147365_f
            }
            if (isElytraFlying != FieldImitations.get(e.player, "lastIsElytraFlying", false)) {
                float f = 0.6f;
                float f1 = isElytraFlying ? 0.6f : 1.8f;

                if (f != e.player.width || f1 != e.player.height) {
                    AxisAlignedBB axisalignedbb = e.player.boundingBox;
                    axisalignedbb = AxisAlignedBB.getBoundingBox(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + f, axisalignedbb.minY + f1, axisalignedbb.minZ + f);

                    if (e.player.worldObj.func_147461_a(axisalignedbb).isEmpty()) {
                        float f2 = e.player.width;
                        e.player.width = f;
                        e.player.height = f1;
                        e.player.boundingBox.setBounds(e.player.boundingBox.minX, e.player.boundingBox.minY, e.player.boundingBox.minZ, e.player.boundingBox.minX + e.player.width, e.player.boundingBox.minY + e.player.height, e.player.boundingBox.minZ + e.player.width);

                        if (e.player.width > f2 && !e.player.worldObj.isRemote) {
                            e.player.moveEntity(f2 - e.player.width, 0.0D, f2 - e.player.width);
                        }
                    }
                }
                FieldImitations.set(e.player, "lastIsElytraFlying", isElytraFlying);
            }
        }
    }

    @SubscribeEvent
    public void onPostWorldTick(TickEvent.WorldTickEvent e) {
        if (e.phase == TickEvent.Phase.END && e.world instanceof WorldServer) {
            WorldServer ws = (WorldServer)e.world;
            for (EntityTrackerEntry ete : (Set<EntityTrackerEntry>)ws.getEntityTracker().trackedEntities) {
                if (ete.field_85178_v instanceof EntityLivingBase) {
                    EntityLivingBase elb = (EntityLivingBase) ete.field_85178_v;
                    boolean flying = MethodImitations.isElytraFlying(elb);
                    if (!flying && FieldImitations.get(ete, "wasForcingVelocityUpdates", false)) {
                        ete.sendVelocityUpdates = false;
                    } else if (flying) {
                        if (!ete.sendVelocityUpdates) {
                            FieldImitations.get(ete, "wasForcingVelocityUpdates", true);
                        }
                        ete.sendVelocityUpdates = true;
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load e) {
        e.world.getGameRules().addGameRule("disableElytraMovementCheck", "false");
    }

    @SideOnly(Side.CLIENT)
    public static void rotateCorpse(AbstractClientPlayer entityLiving, float partialTicks) {
        if (MethodImitations.isElytraFlying(entityLiving)) {
            float f = MethodImitations.getTicksElytraFlying(entityLiving) + partialTicks;
            float f1 = MathHelper.clamp_float(f * f / 100.0F, 0.0F, 1.0F);
            GL11.glRotatef(f1 * (-90.0F - entityLiving.rotationPitch), 1.0F, 0.0F, 0.0F);
            Vec3 vec3d = entityLiving.getLook(partialTicks);
            double d0 = entityLiving.motionX * entityLiving.motionX + entityLiving.motionZ * entityLiving.motionZ;
            double d1 = vec3d.xCoord * vec3d.xCoord + vec3d.zCoord * vec3d.zCoord;

            if (d0 > 0.0D && d1 > 0.0D) {
                double d2 = (entityLiving.motionX * vec3d.xCoord + entityLiving.motionZ * vec3d.zCoord) / (Math.sqrt(d0) * Math.sqrt(d1));
                double d3 = entityLiving.motionX * vec3d.zCoord - entityLiving.motionZ * vec3d.xCoord;
                GL11.glRotatef((float) (Math.signum(d3) * Math.acos(d2)) * 180.0F / (float) Math.PI, 0.0F, 1.0F, 0.0F);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void postSetRotationAngles(ModelBiped modelBiped, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        if (entityIn instanceof EntityLivingBase && (Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 ? entityIn != Minecraft.getMinecraft().renderViewEntity : true))
        {
            LayerBetterElytra.doRenderLayer((EntityLivingBase)entityIn, limbSwing, limbSwingAmount, Minecraft.getMinecraft().timer.renderPartialTicks, ageInTicks, netHeadYaw, headPitch, 0.0625F);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void setRotationAngles(ModelBiped modelBiped, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        boolean flag = entityIn instanceof EntityLivingBase && MethodImitations.getTicksElytraFlying((EntityLivingBase) entityIn) > 4;

        if (flag) {
            limbSwing = ageInTicks;

            modelBiped.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;

            modelBiped.bipedHead.rotateAngleX = -((float) Math.PI / 4F);

            modelBiped.bipedBody.rotateAngleY = 0.0F;
            modelBiped.bipedRightArm.rotationPointZ = 0.0F;
            modelBiped.bipedRightArm.rotationPointX = -5.0F;
            modelBiped.bipedLeftArm.rotationPointZ = 0.0F;
            modelBiped.bipedLeftArm.rotationPointX = 5.0F;
            float f = 1.0F;

            f = (float) (entityIn.motionX * entityIn.motionX + entityIn.motionY * entityIn.motionY + entityIn.motionZ * entityIn.motionZ);
            f = f / 0.2F;
            f = f * f * f;

            if (f < 1.0F) {
                f = 1.0F;
            }

            modelBiped.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
            modelBiped.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
            modelBiped.bipedRightArm.rotateAngleZ = 0.0F;
            modelBiped.bipedLeftArm.rotateAngleZ = 0.0F;
            modelBiped.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
            modelBiped.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
            modelBiped.bipedRightLeg.rotateAngleY = 0.0F;
            modelBiped.bipedLeftLeg.rotateAngleY = 0.0F;
            modelBiped.bipedRightLeg.rotateAngleZ = 0.0F;
            modelBiped.bipedLeftLeg.rotateAngleZ = 0.0F;
        }
    }

    public static boolean shouldOverrideEyeHeight(EntityPlayer entityPlayer) {
        return MethodImitations.isElytraFlying(entityPlayer) && !entityPlayer.isPlayerSleeping();
    }

    public static float getEyeHeight(EntityPlayer player) {
        return 0.4f;
    }

    public static double modifyMovementDelta(EntityPlayerMP playerEntity, double d10) {
        if (MethodImitations.isElytraFlying(playerEntity)) {
            if (playerEntity.worldObj.getGameRules().getGameRuleBooleanValue("disableElytraMovementCheck")) {
                return 0;
            }
            return d10/3;
        }
        return d10;
    }
}
