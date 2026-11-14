package com.unrealdinnerbone.trenzalore.api.registry;

import net.minecraft.resources.ResourceKey;

import java.util.function.Supplier;

public interface IRegistryEntry<T, O extends T> {

    ResourceKey<T> key();

    Supplier<O> getEntry();

}
