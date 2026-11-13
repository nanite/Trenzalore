package com.unrealdinnerbone.trenzalore.api.registry;

import net.minecraft.resources.ResourceKey;

import java.util.function.Supplier;

public class TypedRegistryEntry<T, O extends T> extends RegistryEntry<T> {

    public TypedRegistryEntry(ResourceKey<T> key, Supplier<O> entry) {
        super(key, entry);
    }

    @Override
    public Supplier<O> getEntry() {
        return (Supplier<O>) super.getEntry();
    }
}
