package com.unrealdinnerbone.trenzalore;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.PrimitiveCodec;
import com.unrealdinnerbone.trenzalore.api.platform.services.IRegistry;
import com.unrealdinnerbone.trenzalore.api.registry.Regeneration;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import com.unrealdinnerbone.trenzalore.events.AddItemModifier;
import com.unrealdinnerbone.trenzalore.events.ReplaceItemModifier;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.function.UnaryOperator;

public class TrenzaloreNeoRegistry implements IRegistry {
    private static final RegistryObjects<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIERS = Regeneration.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS);
    public static final RegistryEntry<MapCodec<? extends IGlobalLootModifier>> SIMPLE_LOOT_MODIFIER  = GLOBAL_LOOT_MODIFIERS.register("replace", () -> ReplaceItemModifier.CODEC);
    public static final RegistryEntry<MapCodec<? extends IGlobalLootModifier>> ADD_ITEM_MODIFIER  = GLOBAL_LOOT_MODIFIERS.register("add", () -> AddItemModifier.CODEC);


    @Override
    public List<RegistryObjects<?>> getRegistryObjects() {
        return List.of(GLOBAL_LOOT_MODIFIERS);
    }

    @Override
    public String getModID() {
        return Trenzalore.MOD_ID;
    }

}
