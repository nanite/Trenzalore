package com.unrealdinnerbone.trenzalore.events;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.Optional;

public class AddItemModifier extends LootModifier {

    public static final MapCodec<AddItemModifier> CODEC = RecordCodecBuilder.mapCodec(instance -> codecStart(instance)
            .and(
                   ItemStackTemplate.CODEC.fieldOf("stack").forGetter(o -> o.stackTemplate)
            ).apply(instance, AddItemModifier::new));

    private final ItemStackTemplate stackTemplate;

    public AddItemModifier(Optional<Holder<LootItemCondition>> condition, int priority, ItemStackTemplate stackTemplate) {
        super(condition, priority);
        this.stackTemplate = stackTemplate;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(stackTemplate.create());
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
