package dev.axolotl.tealsmodloader.tealsmc.mods.entities;

import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import dev.axolotl.tealsmodloader.tealsmodloader.builtin.entity.Robot;

public class DiscEntity extends Robot {
    public DiscEntity(World world) {
        super(world);
    }

    @Override
    public void onEntityDamage(DamageSource source, float amount) {
        // Makes the entity unkillable
        setHealth(10);
    }
}
