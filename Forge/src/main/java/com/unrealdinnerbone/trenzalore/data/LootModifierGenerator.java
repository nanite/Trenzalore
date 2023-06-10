package com.unrealdinnerbone.trenzalore.data;

import com.unrealdinnerbone.trenzalore.Trenzalore;
import com.unrealdinnerbone.trenzalore.events.ReplaceItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class LootModifierGenerator extends GlobalLootModifierProvider {

    public LootModifierGenerator(PackOutput packOutput) {
        super(packOutput, Trenzalore.MOD_ID);
    }

    @Override
    protected void start() {
        ItemStack itemStack = PotionUtils.setPotion( new ItemStack(Items.TIPPED_ARROW), Potions.POISON);
        add("poison_arrows", new ReplaceItemModifier(
                createChanceCondition( new ResourceLocation("minecraft", "chests/jungle_temple_dispenser")), new ResourceLocation("minecraft", "arrow"), itemStack));
    }

    public static LootItemCondition[] createChanceCondition(ResourceLocation table) {
        return new LootItemCondition[] { LootTableIdCondition.builder(table).build() };
    }
}
