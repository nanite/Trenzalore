package com.unrealdinnerbone.trenzalore.events;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class ReplaceItemModifier extends LootModifier {

    private final Ingredient ingredient;
    private final ItemStack stack;
    public static final MapCodec<ReplaceItemModifier> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    IGlobalLootModifier.LOOT_CONDITIONS_CODEC.fieldOf("conditions").forGetter(modifier -> modifier.conditions),
                    Ingredient.CODEC.fieldOf("item").forGetter(modifier -> modifier.ingredient),
                    ItemStack.CODEC.fieldOf("stack").forGetter(modifier -> modifier.stack),
                    Codec.INT.optionalFieldOf("priority", 1).forGetter(modifier -> modifier.priority))
            .apply(instance, ReplaceItemModifier::new));


    public ReplaceItemModifier(LootItemCondition[] conditions, Ingredient ingredient, ItemStack itemStack, int priority) {
        super(conditions, priority);
        this.ingredient = ingredient;
        this.stack = itemStack;
    }

    @Deprecated(forRemoval = true)
    public ReplaceItemModifier(LootItemCondition[] conditions, Ingredient ingredient, ItemStack itemStack) {
        this(conditions, ingredient, itemStack, 1);
    }

    public ReplaceItemModifier of(Ingredient ingredient, ItemStack itemStack, LootItemCondition... conditions) {
        return new ReplaceItemModifier(conditions, ingredient, itemStack);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.replaceAll(itemStack -> ingredient.test(itemStack) ? stack.copyWithCount(itemStack.getCount()) : itemStack);
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
