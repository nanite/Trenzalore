package com.unrealdinnerbone.trenzalore.events;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.Optional;

public class ReplaceItemModifier extends LootModifier {

    private final Ingredient ingredient;
    private final ItemStackTemplate stack;

    public static final MapCodec<ReplaceItemModifier> CODEC = RecordCodecBuilder.mapCodec(instance -> codecStart(instance)
            .and(Ingredient.CODEC.fieldOf("item").forGetter(modifier -> modifier.ingredient))
            .and(ItemStackTemplate.CODEC.fieldOf("stack").forGetter(modifier -> modifier.stack))
            .apply(instance, ReplaceItemModifier::new));

    public ReplaceItemModifier(Optional<Holder<LootItemCondition>> condition, int priority, Ingredient ingredient, ItemStackTemplate itemStack) {
        super(condition, priority);
        this.ingredient = ingredient;
        this.stack = itemStack;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.replaceAll(itemStack -> ingredient.test(itemStack) ? stack.create().copyWithCount(itemStack.getCount()) : itemStack);
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
