package com.unrealdinnerbone.trenzalore.api.registry;

import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.List;
import java.util.function.Supplier;

public record RegistryObjects<T>(ResourceKey<Registry<T>> registryKey, List<RegistryEntry<T>> objects) {

    public RegistryEntry<T> register(String name, Supplier<T> object) {
        RegistryEntry<T> entry = new RegistryEntry<>(name, Suppliers.memoize(object::get));
        objects.add(entry);
        return entry;

    }
}
