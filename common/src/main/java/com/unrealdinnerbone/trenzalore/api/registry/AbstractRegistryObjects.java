package com.unrealdinnerbone.trenzalore.api.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRegistryObjects<T> {

    protected final String modID;
    protected final ResourceKey<Registry<T>> registryKey;
    protected final List<RegistryEntry<T>> objects;

    public AbstractRegistryObjects(String modID, ResourceKey<Registry<T>> registryKey) {
        this.modID = modID;
        this.registryKey = registryKey;
        this.objects = new ArrayList<>();
    }

    public ResourceKey<Registry<T>> registryKey() {
        return registryKey;
    }

    public List<RegistryEntry<T>> objects() {
        return objects;
    }
}
