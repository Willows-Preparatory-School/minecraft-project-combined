package dev.axolotl.testmod.mixins.minecraft;

import dev.axolotl.testmod.TestMod;
import dev.axolotl.testmod.item.elytra.Elytra;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = EntityLivingBase.class)
public class ElytraMoveEntityWithHeadingMixin {

    @Inject(method = "moveEntityWithHeading",
        at = @At("HEAD"),
        require = 1,
        locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    public void moveEntityWithHeading(float strafe, float forward, CallbackInfo cir) {
        boolean result = Elytra.moveEntityWithHeading((EntityLivingBase) (Object) this, strafe, forward);
        if(result) {
            cir.cancel();
        }
    }
}
