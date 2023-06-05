package com.unrealdinnerbone.trenzalore.content.mixin;

import com.unrealdinnerbone.trenzalore.content.TrenzaloreTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.PiglinBruteAi;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PiglinBruteAi.class)
public class PiglinBruteAIMixin
{
    @Inject(method = "getTargetIfWithinRange(Lnet/minecraft/world/entity/monster/piglin/AbstractPiglin;Lnet/minecraft/world/entity/ai/memory/MemoryModuleType;)Ljava/util/Optional;", at = @At(
            value = "TAIL"), cancellable = true)
    private static void onFindTarget(AbstractPiglin piglin, MemoryModuleType<? extends LivingEntity> moduleType, CallbackInfoReturnable<Optional<? extends LivingEntity>> cir) {
        piglin.getBrain().getMemory(moduleType).ifPresent((livingEntity) -> {
            if(isWearingFullGoldArmor(livingEntity)) {
                cir.setReturnValue(Optional.empty());
            }
        });
    }

    private static boolean isWearingFullGoldArmor(LivingEntity livingEntity) {
        if(livingEntity instanceof Player player) {
            for (int i = 0; i < 4; i++) {
                if (!player.getInventory().getArmor(i).is(TrenzaloreTags.GOLD_ARMOR)) {
                    return false;
                }
            }
        }
        return true;
    }
}
