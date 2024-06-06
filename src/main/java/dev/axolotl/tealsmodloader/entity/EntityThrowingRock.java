package dev.axolotl.tealsmodloader.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityThrowingRock extends EntityThrowable
{
    public EntityThrowingRock(World par1World)
    {
        super(par1World);
    }
    public EntityThrowingRock(World par1World, EntityLivingBase par2EntityLivingBase)
    {
        super(par1World, par2EntityLivingBase);
        // TODO Auto-generated constructor stub
    }
    public EntityThrowingRock(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
        // TODO Auto-generated constructor stub
    }
    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    @Override
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        if (par1MovingObjectPosition.entityHit != null)
        {
            float rockDamage = 2;
            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), rockDamage);
        }
        for (int l = 0; l < 4; ++l)
        {
            this.worldObj.spawnParticle("crit", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
}
