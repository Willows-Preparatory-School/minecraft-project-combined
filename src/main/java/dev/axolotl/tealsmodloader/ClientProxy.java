package dev.axolotl.tealsmodloader;

import cpw.mods.fml.client.registry.RenderingRegistry;
import dev.axolotl.tealsmodloader.blocks.BlockEndRodRenderer;
import dev.axolotl.tealsmodloader.entity.*;
import dev.axolotl.tealsmodloader.item.elytra.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import  org.apache.logging.log4j.Level;

public class ClientProxy extends CommonProxy {
    // Override CommonProxy methods here, if you want a different behaviour on the client (e.g. registering renders).
    // Don't forget to call the super methods as well.

    /** A Map allows for easy handling of many armor models */
    public static final Map<Item, ModelBiped> armorModels = new HashMap<Item, ModelBiped>();

    @Override
    public void registerRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityThrowingRock.class, new RenderSnowball(TestMod.itemThrowingRock));
        RenderingRegistry.registerEntityRenderingHandler(EntityEndermite.class, new EndermiteRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityHusk.class, new HuskRenderer());
        RenderingRegistry.registerEntityRenderingHandler(EntityStray.class, new StrayRenderer());
        RenderingRegistry.registerBlockHandler(new BlockEndRodRenderer());
    }

    @Override
    public int addArmor(String armor) {
        return RenderingRegistry.addNewArmourRendererPrefix(armor);
    }

    /**
     * Adds a mapping for an ItemArmor to a ModelBase for rendering
     * @param armor The armor Item
     * @param model The model should not be null and must extend ModelBiped
     */
    private void addArmorModel(Item armor, ModelBiped model) {
        if (model == null) {
            // technically, you CAN add a null model, but the default is already to return null, so it would be redundant
            TestMod.LOG.log(Level.WARN, String.format("Error adding model for %s: Cannot add a NULL armor model!", armor.getUnlocalizedName()));
            return;
        }
        // better let yourself / users know when overwriting an entry, as it is likely to be a mistake!
        if (armorModels.containsKey(armor)) {
            TestMod.LOG.log(Level.WARN, String.format("A model for %s has already been registered! It will now be overwritten.", armor.getUnlocalizedName()));
        }
        armorModels.put(armor, model);
    }

    @Override
    public void update(EntityLivingBase elb) {
        if (elb instanceof EntityPlayerSP) {
            EntityPlayerSP e = (EntityPlayerSP) elb;
            boolean lastJumping = FieldImitations.get(e, "lastIsJumping", false);
            if (e.movementInput.jump && !lastJumping && !e.onGround && e.motionY < 0.0D && !MethodImitations.isElytraFlying(e) && !e.capabilities.isFlying) {
                ItemStack itemstack = e.getEquipmentInSlot(3);

                if (itemstack != null && itemstack.getItem() == TestMod.itemElytra ) {//&& ItemElytra.isBroken(itemstack)) {
                    TestMod.network.sendToServer(new StartFallFlying());
                    Minecraft.getMinecraft().getSoundHandler().playSound(new ElytraSound(e));
                }
            }
            FieldImitations.set(e, "lastIsJumping", e.movementInput.jump);
        }
    }
}
