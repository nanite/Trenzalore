package com.unrealdinnerbone.trenzalore.events;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.unrealdinnerbone.trenzalore.content.TrenzaloreConfig;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;

public class SimpleItemModifier extends LootModifier {

    public static final Codec<SimpleItemModifier> CODEC = RecordCodecBuilder.create((builder) ->
            codecStart(builder).apply(builder, SimpleItemModifier::new));


    public SimpleItemModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if(TrenzaloreConfig.CONFIG.get().poisonArrowsInJungleTemples()) {
            generatedLoot.replaceAll(itemStack -> {
                if (itemStack.getItem() == Items.ARROW) {
                    ItemStack itemStack1 = new ItemStack(Items.TIPPED_ARROW, itemStack.getCount());
                    return PotionUtils.setPotion(itemStack1, Potions.POISON);
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
