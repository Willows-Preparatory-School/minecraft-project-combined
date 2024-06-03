package dev.axolotl.testmod.mixins.minecraft;

import dev.axolotl.testmod.TestMod;
import dev.axolotl.testmod.TestModEvents;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EntityPlayer.class)
public class ElytraGetEyeHeightMixin {
    @Inject(method = "getEyeHeight",
        at = @At("HEAD"),
        require = 1,
        locals = LocalCapture.CAPTURE_FAILEXCEPTION,
        cancellable = true)
    public void getEyeHeight(CallbackInfoReturnable<Float> cir) {
        /*
        if (TestModEvents.shouldOverrideEyeHeight((EntityPlayer) (Object) this)) {
            Float overriddenEyeHeight = TestModEvents.getEyeHeight((EntityPlayer) (Object) this);
            TestMod.LOG.info("getEyeHeight result: " + overriddenEyeHeight);
            cir.setReturnValue(overriddenEyeHeight);
            cir.cancel();
        }
         */
        boolean shouldOverwrite = TestModEvents.shouldOverrideEyeHeight((EntityPlayer) (Object) this);

        if (shouldOverwrite) {
            // Set the new return value
            float newValue = TestModEvents.getEyeHeight((EntityPlayer) (Object) this);

            // Log the new value if needed
            //TestMod.LOG.info("New Eye Height: " + newValue);

            // Set the return value and cancel the original method execution
            cir.setReturnValue(newValue);
            cir.cancel();
        }
    }
}
