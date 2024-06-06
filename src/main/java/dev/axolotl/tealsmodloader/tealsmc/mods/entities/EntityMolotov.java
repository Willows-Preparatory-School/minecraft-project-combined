package dev.axolotl.tealsmodloader.tealsmc.mods.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityMolotov extends EntityThrowable {
    private final int RADIUS = 12;

    public EntityMolotov(World p_i1773_1_) {
        super(p_i1773_1_);
    }

    public EntityMolotov(World p_i1774_1_, EntityLivingBase p_i1774_2_) {
        super(p_i1774_1_, p_i1774_2_);
    }

    public EntityMolotov(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_) {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }

    public void onImpact(MovingObjectPosition p_70184_1_) {
        int b0;
        if (p_70184_1_.entityHit != null) { //damages any hit entities
            p_70184_1_.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 20.0F);
        }

        for(b0 = 0; b0 < 8; ++b0) {
            this.worldObj.spawnParticle("basic_flame_particle", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
        }

        if (!this.worldObj.isRemote) {
            this.setDead();
        }
        explode(); //spawns the explosion
        for(int x = (int) (this.posX-RADIUS); x<=this.posX+RADIUS; x++){ //sets everything in the explosion radius on fire
            for(int y = (int) (this.posY-RADIUS); y<=this.posY+RADIUS; y++){
                for(int z = (int) (this.posZ-RADIUS); z<=this.posZ+RADIUS; z++){
                    double dist = Math.sqrt(Math.abs(this.posX - x) + Math.abs(this.posY - y) + Math.abs(this.posZ - z));
                    if(dist <= RADIUS && worldObj.getBlock(x,y,z).isAir(worldObj,x,y,z) && !worldObj.getBlock(x,y-1,z).isAir(worldObj,x,y-1,z)){
                        worldObj.setBlock(x,y,z, Blocks.fire);
                    }
                }
            }
        }
    }
    private void explode() { //creates the explosion within the world
        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float) RADIUS, true);
    }
}
