package com.unrealdinnerbone.trenzalore;

import com.mojang.serialization.MapCodec;
import com.unrealdinnerbone.trenzalore.api.attachment.AttachmentReference;
import com.unrealdinnerbone.trenzalore.api.platform.services.IRegistry;
import com.unrealdinnerbone.trenzalore.api.registry.AbstractRegistryObjects;
import com.unrealdinnerbone.trenzalore.api.registry.Regeneration;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import com.unrealdinnerbone.trenzalore.events.AddItemModifier;
import com.unrealdinnerbone.trenzalore.events.ReplaceItemModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

public class TrenzaloreNeoRegistry implements IRegistry {

    private static final RegistryObjects<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIERS = Regeneration.create(Trenzalore.MOD_ID, NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS);

    public static final RegistryEntry<MapCodec<? extends IGlobalLootModifier>, MapCodec<? extends IGlobalLootModifier>> SIMPLE_LOOT_MODIFIER  = GLOBAL_LOOT_MODIFIERS.register("replace", () -> ReplaceItemModifier.CODEC);
    public static final RegistryEntry<MapCodec<? extends IGlobalLootModifier>, MapCodec<? extends IGlobalLootModifier>> ADD_ITEM_MODIFIER  = GLOBAL_LOOT_MODIFIERS.register("add", () -> AddItemModifier.CODEC);


    @Override
    public List<AbstractRegistryObjects<?>> getRegistryObjects() {
        return List.of(GLOBAL_LOOT_MODIFIERS);
    }

    @Override
    public String getModID() {
        return Trenzalore.MOD_ID;
    }

}
