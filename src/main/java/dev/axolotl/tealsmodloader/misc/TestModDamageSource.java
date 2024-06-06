package dev.axolotl.tealsmodloader.misc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;

import javax.annotation.Nullable;

public class TestModDamageSource
{
    public static DamageSource getElectricDamage() {
        //return ic2.api.info.Info.DMG_ELECTRIC;
        return new DamageSourceElectric();
    }

    public static DamageSource getRadioactiveDamage() {
        //return ic2.api.info.Info.DMG_RADIATION;
        return new DamageSourceRadioactive();
    }

    public static DamageSource getNukeExplosionDamage() {
        //return ic2.api.info.Info.DMG_NUKE_EXPLOSION;
        return new DamageSourceNukeExplosion();
    }

    public static DamageSource getExplodingDamage() {
        return new DamageSourceExploding();
    }

    public static DamageSource getCombatDamage(String aType, EntityLivingBase aPlayer, IChatComponent aDeathMessage) {
        return new DamageSourceCombat(aType, aPlayer, aDeathMessage);
    }

    public static DamageSource getHeatDamage() {
        return new DamageSourceHeat();
    }

    public static DamageSource getFrostDamage() {
        return new DamageSourceFrost();
    }

    public static DamageSource getAcidDamage(){
        return new DamageSourceAcid();
    }

    public static DamageSource getSpaghettifiedDamage() {
        return new DamageSourceSpaghettification();
    }

    public static DamageSource getBurnDamage(){
        return new DamageSourceBurn();
    }

    public static DamageSource getFlyIntoWall()
    {
        return new DamageSourceFlyIntoWall();
    }

    private static class DamageSourceFlyIntoWall extends DamageSource
    {
        public DamageSourceFlyIntoWall() {
            super("flyIntoWall");
            setDamageBypassesArmor();
        }

        @Override
        public IChatComponent func_151519_b(EntityLivingBase aTarget) {
            return new ChatComponentText(
                EnumChatFormatting.RED + aTarget.getCommandSenderName() + EnumChatFormatting.WHITE + " kinetic energy smth, smth...");
        }
    }

    private static class DamageSourceBurn extends DamageSource
    {
        public DamageSourceBurn() {
            super("burn");
            setDifficultyScaled();
            setDamageBypassesArmor();
            setDamageIsAbsolute();
        }

        @Override
        public IChatComponent func_151519_b(EntityLivingBase aTarget) {
            return new ChatComponentText(
                EnumChatFormatting.RED + aTarget.getCommandSenderName() + EnumChatFormatting.WHITE + " got burned");
        }
    }

    private static class DamageSourceSpaghettification extends DamageSource
    {
        public DamageSourceSpaghettification() {
            super("spaghettification");
            setDifficultyScaled();
            setDamageBypassesArmor();
            setDamageIsAbsolute();
        }

        @Override
        public IChatComponent func_151519_b(EntityLivingBase aTarget) {
            return new ChatComponentText(
                EnumChatFormatting.RED + aTarget.getCommandSenderName() + EnumChatFormatting.WHITE + " got spaghettified");
        }
    }

    private static class DamageSourceAcid extends DamageSource
    {
        public DamageSourceAcid() {
            super("acid");
            setDifficultyScaled();
            setDamageBypassesArmor();
        }

        @Override
        public IChatComponent func_151519_b(EntityLivingBase aTarget) {
            return new ChatComponentText(
                EnumChatFormatting.RED + aTarget.getCommandSenderName() + EnumChatFormatting.WHITE + " was dissolved");
        }
    }

    private static class DamageSourceElectric extends DamageSource
    {
        public DamageSourceElectric() {
            super("electric");
            setDifficultyScaled();
        }

        @Override
        public IChatComponent func_151519_b(EntityLivingBase aTarget) {
            return new ChatComponentText(
                EnumChatFormatting.RED + aTarget.getCommandSenderName() + EnumChatFormatting.WHITE + " was electrocuted");
        }
    }

    private static class DamageSourceRadioactive extends DamageSource
    {
        public DamageSourceRadioactive() {
            super("radioactive");
            setDifficultyScaled();
        }

        @Override
        public IChatComponent func_151519_b(EntityLivingBase aTarget) {
            return new ChatComponentText(
                EnumChatFormatting.RED + aTarget.getCommandSenderName() + EnumChatFormatting.WHITE + " got irritated");
        }
    }

    private static class DamageSourceNukeExplosion extends DamageSource
    {
        public DamageSourceNukeExplosion() {
            super("nuke");
            setDifficultyScaled();
        }

        @Override
        public IChatComponent func_151519_b(EntityLivingBase aTarget) {
            return new ChatComponentText(
                EnumChatFormatting.RED + aTarget.getCommandSenderName() + EnumChatFormatting.WHITE + " got nuked");
        }
    }

    private static class DamageSourceCombat extends EntityDamageSource {
        private final IChatComponent mDeathMessage;

        public DamageSourceCombat(String aType, EntityLivingBase aPlayer, IChatComponent aDeathMessage) {
            super(aType, aPlayer);
            mDeathMessage = aDeathMessage;
        }

        @Override
        public IChatComponent func_151519_b(EntityLivingBase aTarget) {
            return mDeathMessage == null ? super.func_151519_b(aTarget) : mDeathMessage;
        }
    }

    private static class DamageSourceFrost extends DamageSource {

        public DamageSourceFrost() {
            super("frost");
            setDifficultyScaled();
        }

        @Override
        public IChatComponent func_151519_b(EntityLivingBase aTarget) {
            return new ChatComponentText(
                EnumChatFormatting.RED + aTarget.getCommandSenderName() + EnumChatFormatting.WHITE + " got frozen");
        }
    }

    private static class DamageSourceHeat extends DamageSource {

        public DamageSourceHeat() {
            super("steam");
            setFireDamage();
            setDifficultyScaled();
        }

        @Override
        public IChatComponent func_151519_b(EntityLivingBase aTarget) {
            return new ChatComponentText(
                EnumChatFormatting.RED + aTarget.getCommandSenderName()
                    + EnumChatFormatting.WHITE
                    + " was boiled alive");
        }
    }

    public static class DamageSourceHotItem extends DamageSourceHeat {

        @Nullable
        private final ItemStack stack;

        public DamageSourceHotItem(@Nullable ItemStack cause) {
            this.stack = cause;
        }

        @Nullable
        public ItemStack getDamagingStack() {
            return stack;
        }
    }

    public static class DamageSourceExploding extends DamageSource {
        public DamageSourceExploding() {
            super("exploded");
            //setDamageAllowedInCreativeMode();
            setDamageBypassesArmor();
            setDamageIsAbsolute();
        }

        @Override
        public IChatComponent func_151519_b(EntityLivingBase aTarget) {
            return new ChatComponentText(
                EnumChatFormatting.RED + aTarget.getCommandSenderName() + EnumChatFormatting.WHITE + " exploded");
        }
    }
}
