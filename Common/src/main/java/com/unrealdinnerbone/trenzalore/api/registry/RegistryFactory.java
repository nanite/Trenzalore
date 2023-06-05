package com.unrealdinnerbone.trenzalore.api.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistryFactory {

    @ApiStatus.Internal
    private static final Map<String, List<RegistryObjects<?>>> registryMap = new HashMap<>();

    public static <T> RegistryObjects<T> create(String modid, ResourceKey<Registry<T>> registry) {
        if(!registryMap.containsKey(modid)) {
            registryMap.put(modid, new ArrayList<>());
        }
        if(registryMap.get(modid).stream().anyMatch(registryObjects -> registryObjects.registryKey().equals(registry))) {
            throw new RuntimeException("Registry already exists");
        }
        RegistryObjects<T> registryObjects = new RegistryObjects<>(registry, new ArrayList<>());
        registryMap.get(modid).add(registryObjects);
        return registryObjects;

    }

    @ApiStatus.Internal
    public static Map<String, List<RegistryObjects<?>>> getRegistryMap() {
        return registryMap;
    }
}
