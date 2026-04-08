package by.banci.doze.mixin;

import by.banci.doze.Config;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.level.block.BedBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BedBlock.class)
public abstract class BedBlockMixin {

    @Redirect(
            method = "useWithoutItem",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/attribute/BedRule;explodes()Z")
    )
    private boolean dimensionOverride(BedRule instance) {
        return instance.explodes() && Config.BEDS_EXPLODE.get();
    }
}


