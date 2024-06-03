package dev.axolotl.testmod.particles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dev.axolotl.testmod.util.Utils;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;
import java.util.Random;
import dev.axolotl.testmod.util.RandomXoshiro256StarStar;

@SideOnly(Side.CLIENT)
public class TestModParticles
{
    protected static Random rand = new RandomXoshiro256StarStar();

    public static EntityFX spawnSoulFlame(World world, double x, double y, double z, double mX, double mY, double mZ) {
        EntityFX particle = new SoulFlameFX(world, x, y, z, mX, mY, mZ);
        return Utils.spawnParticle(world, particle);
    }

    public static EntityFX spawnSoulFlame(World world, double x, double y, double z) {
        EntityFX particle = new SoulFlameFX(world, x, y, z);
        return Utils.spawnParticle(world, particle);
    }

    public static EntityFX spawnEndRodParticle(World world, double x, double y, double z) {
        EntityFX particle = new EndRodFX(world, x, y, z);
        return Utils.spawnParticle(world, particle);
    }

    public static EntityFX spawnCherryLeaf(World world, double x, double y, double z) {
        EntityFX particle = new CherryLeafFX(world, x, y, z);
        return Utils.spawnParticle(world, particle);
    }
}
