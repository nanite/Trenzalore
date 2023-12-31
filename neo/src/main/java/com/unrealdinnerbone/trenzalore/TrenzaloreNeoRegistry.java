package com.unrealdinnerbone.trenzalore;

import com.mojang.serialization.Codec;
import com.unrealdinnerbone.trenzalore.api.platform.services.IRegistry;
import com.unrealdinnerbone.trenzalore.api.registry.Regeneration;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import com.unrealdinnerbone.trenzalore.events.AddItemModifier;
import com.unrealdinnerbone.trenzalore.events.ReplaceItemModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

public class TrenzaloreNeoRegistry implements IRegistry {
    private static final RegistryObjects<Codec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIERS = Regeneration.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS);
    public static final RegistryEntry<Codec<? extends IGlobalLootModifier>> SIMPLE_LOOT_MODIFIER  = GLOBAL_LOOT_MODIFIERS.register("replace", () -> ReplaceItemModifier.CODEC);
    public static final RegistryEntry<Codec<? extends IGlobalLootModifier>> ADD_ITEM_MODIFIER  = GLOBAL_LOOT_MODIFIERS.register("add", () -> AddItemModifier.CODEC);

    @Override
    public List<RegistryObjects<?>> getRegistryObjects() {
        return List.of();
    }

    @Override
    public String getModID() {
        return Trenzalore.MOD_ID;
    }
}
