package dev.axolotl.tealsmodloader.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dev.axolotl.tealsmodloader.TestMod;
import dev.axolotl.tealsmodloader.util.TestModSound;
import dev.axolotl.tealsmodloader.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Map;
import java.util.WeakHashMap;

public class BlockSlime extends Block
{
    private static final Map<Entity, Double> SLIME_BOUNCE_CACHE = new WeakHashMap<>();
    private long lastBounceTick;

    public BlockSlime() {
        super(Material.clay);
        this.setCreativeTab(TestMod.tabTestMod);
        this.setHardness(0.0F);
        this.setBlockTextureName(TestMod.MODID+":slime_block");
        Utils.setBlockSound(this, TestModSound.soundSlime);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1 - f, z + 1);
    }

    @Override
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance) {
        if (!entity.isSneaking()) {
            entity.fallDistance = 0;
            if (entity.motionY < 0.1) {
                SLIME_BOUNCE_CACHE.put(entity, -entity.motionY);
                lastBounceTick = world.getTotalWorldTime();
            }
        }
        super.onFallenUpon(world, x, y, z, entity, fallDistance);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (lastBounceTick == world.getTotalWorldTime()) {
            Double bounce = SLIME_BOUNCE_CACHE.remove(entity);
            if (bounce != null) {
                entity.motionY = bounce;
            }
        } else {
            SLIME_BOUNCE_CACHE.clear();
        }
        double d = 0.4 + Math.abs(entity.motionY) * 0.2;
        entity.motionX *= d;
        entity.motionZ *= d;
        super.onEntityCollidedWithBlock(world, x, y, z, entity);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 1;
    }

    //@Override
    //public int getRenderType() {
        //return RenderIDs.SLIME_BLOCK;
    //}
}
