package com.unrealdinnerbone.trenzalore.api.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class RegistryEntry<T, V extends T> implements Supplier<T>, ReferencedHolder<T> {

    private final Identifier key;
    private final Supplier<V> entry;

    @Nullable
    private Holder<T> holder;

    public RegistryEntry(Identifier key, Supplier<V> entry) {
        this.key = key;
        this.entry = entry;
    }

    @ApiStatus.Internal
    public void setHolder(Holder<T> register) {
        if(holder != null) {
            throw new RuntimeException("Holder already set");
        }
        this.holder = register;
    }

    @Override
    public Holder<T> getHolder() {
        if (holder == null) {
            throw new IllegalStateException("Holder for " + key + " has not been set yet!");
        }
        return holder;
    }

    @Override
    public T get() {
        return getHolder().value();
    }

    public V entryValue() {
        return entry.get();
    }

    public Identifier getIDKey() {
        return key;
    }

    @Override
    public boolean areComponentsBound() {
        return getHolder().areComponentsBound();
    }

    @Override
    public DataComponentMap components() {
        return getHolder().components();
    }


    public static class BlockEntry<T extends net.minecraft.world.level.block.Block> extends RegistryEntry<net.minecraft.world.level.block.Block, T> {
        public BlockEntry(Identifier key, Supplier<T> entry) {
            super(key, entry);
        }
    }

    public static class ItemEntry<T extends net.minecraft.world.item.Item> extends RegistryEntry<net.minecraft.world.item.Item, T> {
        public ItemEntry(Identifier key, Supplier<T> entry) {
            super(key, entry);
        }
    }



}
