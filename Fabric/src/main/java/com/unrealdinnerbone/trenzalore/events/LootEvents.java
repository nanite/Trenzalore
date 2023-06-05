package com.unrealdinnerbone.trenzalore.events;

import com.unrealdinnerbone.trenzalore.content.TrenzaloreConfig;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;

public class LootEvents {
    public static void init() {
        if(TrenzaloreConfig.CONFIG.get().poisonArrowsInJungleTemples()) {
            LootTableEvents.MODIFY.register((resourceManager, lootManager, id, builder, setter) -> {
                if(id.toString().equals("minecraft:chests/jungle_temple_dispenser")) {
                    builder.modifyPools(poolBuilder -> {
                        poolBuilder.apply(new LootItemFunction() {
                            @Override
                            public LootItemFunctionType getType() {
                                return LootItemFunctions.SET_POTION;
                            }

                            @Override
                            public ItemStack apply(ItemStack itemStack, LootContext lootContext) {
                                return PotionUtils.setPotion(new ItemStack(Items.TIPPED_ARROW, itemStack.getCount()), Potions.POISON);
                            }
                        });
                    });

                }
            });
        }
    }
}
