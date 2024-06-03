package dev.axolotl.testmod.mixins;

import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(GuiMainMenu.class)
public class ExampleMixin
{
    @Inject(method="initGui()V",
        at = @At("HEAD"),
        require = 1,
        locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void init(CallbackInfo info)
    {
        System.out.println("This line is printed by an example mixin!");
    }
}
