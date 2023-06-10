package com.unrealdinnerbone.trenzalore.events;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.unrealdinnerbone.trenzalore.content.TrenzaloreConfig;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;

public class ReplaceItemModifier extends LootModifier {

    private final ResourceLocation item;
    private final ItemStack stack;
    public static final Codec<ReplaceItemModifier> CODEC = RecordCodecBuilder.create((builder) ->
            codecStart(builder)
                    .and(ResourceLocation.CODEC.fieldOf("item")
                            .forGetter((modifier) -> modifier.item))
                    .and(ItemStack.CODEC.fieldOf("stack")
                            .forGetter((modifier) -> modifier.stack))
                    .apply(builder, ReplaceItemModifier::new));


    public ReplaceItemModifier(LootItemCondition[] conditionsIn, ResourceLocation item, ItemStack itemStack) {
        super(conditionsIn);
        this.item = item;
        this.stack = itemStack;
    }

    @SuppressWarnings("deprecation") // Forge stop being annoying please
    @Nonnull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if(TrenzaloreConfig.CONFIG.get().poisonArrowsInJungleTemples()) {
            generatedLoot.replaceAll(itemStack -> {
                if (BuiltInRegistries.ITEM.get(item) == itemStack.getItem()) {
                    ItemStack copy = stack.copy();
                    copy.setCount(itemStack.getCount());
                    return copy;
                }
                return itemStack;
            });
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
