package com.unrealdinnerbone.trenzalore.events;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.unrealdinnerbone.trenzalore.lib.CustomCodecs;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class ReplaceItemModifier extends LootModifier {

    private final Ingredient ingredient;
    private final ItemStack stack;
    public static final Codec<ReplaceItemModifier> CODEC = RecordCodecBuilder.create(builder ->
            codecStart(builder)
                    .and(CustomCodecs.INGREDIENT.fieldOf("item")
                            .forGetter((modifier) -> modifier.ingredient))
                    .and(ItemStack.CODEC.fieldOf("stack")
                            .forGetter((modifier) -> modifier.stack))
                    .apply(builder, ReplaceItemModifier::new));


    public ReplaceItemModifier(LootItemCondition[] conditions, Ingredient ingredient, ItemStack itemStack) {
        super(conditions);
        this.ingredient = ingredient;
        this.stack = itemStack;
    }

    public ReplaceItemModifier of(Ingredient ingredient, ItemStack itemStack, LootItemCondition... conditions) {
        return new ReplaceItemModifier(conditions, ingredient, itemStack);
    }

    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.replaceAll(itemStack -> ingredient.test(itemStack) ? stack.copyWithCount(itemStack.getCount()) : itemStack);
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
