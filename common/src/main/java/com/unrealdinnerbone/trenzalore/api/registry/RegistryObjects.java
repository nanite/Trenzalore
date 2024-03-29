package com.unrealdinnerbone.trenzalore.api.registry;

import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record RegistryObjects<T>(ResourceKey<Registry<T>> registryKey, List<RegistryEntry<? extends T>> objects) {

    public static <T> RegistryObjects<T> of(ResourceKey<Registry<T>> registryKey) {
        return new RegistryObjects<>(registryKey, new ArrayList<>());
    }
    public <A extends T> RegistryEntry<A> register(String name, Supplier<A> object) {
        RegistryEntry<A> entry = new RegistryEntry<>(name, Suppliers.memoize(object::get));
        objects.add(entry);
        return entry;
    }

}
