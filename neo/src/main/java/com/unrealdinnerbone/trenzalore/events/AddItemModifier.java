package com.unrealdinnerbone.trenzalore.events;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class AddItemModifier extends LootModifier {

    public static final MapCodec<AddItemModifier> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    IGlobalLootModifier.LOOT_CONDITIONS_CODEC.fieldOf("conditions").forGetter(glm -> glm.conditions),
                    ItemStack.CODEC.fieldOf("table").forGetter(addItemModifier -> addItemModifier.stack),
                    Codec.INT.optionalFieldOf("priority", 1).forGetter(addItemModifier -> addItemModifier.priority))
            .apply(instance, AddItemModifier::new));

    private final ItemStack stack;

    public AddItemModifier(LootItemCondition[] conditionsIn, ItemStack itemStack, int priority) {
        super(conditionsIn, priority);
        this.stack = itemStack;
    }

    @Deprecated(forRemoval = true)
    public AddItemModifier(LootItemCondition[] conditionsIn, ItemStack itemStack) {
        this(conditionsIn, itemStack, 1);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(stack.copy());
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
