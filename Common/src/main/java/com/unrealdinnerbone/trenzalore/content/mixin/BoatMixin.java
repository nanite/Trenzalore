package com.unrealdinnerbone.trenzalore.content.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Boat.class)
public abstract class BoatMixin extends Entity {

    public BoatMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Inject(method = "checkFallDamage", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/vehicle/Boat;causeFallDamage(FFLnet/minecraft/world/damagesource/DamageSource;)Z"), cancellable = true)
    public void stopFallDamage(double $$0, boolean $$1, BlockState $$2, BlockPos $$3, CallbackInfo ci) {
        ci.cancel();
        resetFallDistance();
    }
}
