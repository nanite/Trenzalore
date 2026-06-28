package com.unrealdinnerbone.trenzalore.api.registry;

import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

public class RegistryEntry<T> {

    private final ResourceKey<T> key;
    private final Supplier<? extends T> entry;

    public RegistryEntry(ResourceKey<T> key, Supplier<? extends T> entry) {
        this.key = key;
        this.entry = entry;
    }

    public ResourceKey<T> key() {
        return key;
    }

    public Supplier<? extends T> getEntry() {
        return entry;
    }

    public static class BlockEntry<T extends net.minecraft.world.level.block.Block> extends RegistryEntry<net.minecraft.world.level.block.Block> {
        public BlockEntry(ResourceKey<net.minecraft.world.level.block.Block> key, Supplier<T> entry) {
            super(key, entry);
        }
    }

    public static class ItemEntry<T extends net.minecraft.world.item.Item> extends RegistryEntry<net.minecraft.world.item.Item> {
        public ItemEntry(ResourceKey<net.minecraft.world.item.Item> key, Supplier<T> entry) {
            super(key, entry);
        }
    }



}
