package com.unrealdinnerbone.trenzalore;

import com.mojang.serialization.Codec;
import com.unrealdinnerbone.trenzalore.api.platform.services.IRegistry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryFactory;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import com.unrealdinnerbone.trenzalore.events.SimpleItemModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class TrenzaloreForgeRegistry implements IRegistry {

    private static final RegistryObjects<Codec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIERS = RegistryFactory.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS);

    public static final RegistryEntry<Codec<? extends IGlobalLootModifier>> SIMPLE_LOOT_MODIFIER  = GLOBAL_LOOT_MODIFIERS.register("simple", () -> SimpleItemModifier.CODEC);

    @Override
    public List<RegistryObjects<?>> getRegistryObjects() {
        return List.of(GLOBAL_LOOT_MODIFIERS);
    }

    @Override
    public String getModID() {
        return Trenzalore.MOD_ID;
    }
}
