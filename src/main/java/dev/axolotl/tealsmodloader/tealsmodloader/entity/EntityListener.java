package dev.axolotl.tealsmodloader.tealsmodloader.entity;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dev.axolotl.tealsmodloader.TestMod;
import dev.axolotl.tealsmodloader.tealsmodloader.builtin.entity.Robot;
import net.minecraft.entity.EntityLiving;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

/**
 * @author Connor Hollasch
 * https://github.com/CHollasch
 */
public class EntityListener {

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) throws Exception {
        if (!(event.entity instanceof EntityLiving))
            return;

        EntityLiving entity = (EntityLiving) event.entity;

        //Set to custom path navigation system
        EntityUtil.getEntityNavigation(entity);

        for (BaseEntityModifier modifier : TestMod.getTEALSInstance().getEntityModifiers()) {
            if (modifier.willInject(entity))
                modifier.inject(entity);
        }
    }

    @SubscribeEvent
    public void onRobotDamage(LivingAttackEvent event) {
        if (event.entity instanceof Robot) {
            Robot robot = (Robot) event.entity;
            robot.onEntityDamage(event.source, event.ammount);
        }
    }
}
