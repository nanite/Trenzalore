package com.unrealdinnerbone.trenzalore.platform;

import com.unrealdinnerbone.trenzalore.api.platform.services.IInternalUtils;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class FabricInternalUtils implements IInternalUtils {
    @Override
    public <T> void registryRegistryObjects(String modId, RegistryObjects<T> registryObjects) {
        ResourceKey<Registry<T>> registryKey = registryObjects.registryKey();
        Registry<T> registry = (Registry<T>) BuiltInRegistries.REGISTRY.get(registryKey.location());
        for (RegistryEntry<T> object : registryObjects.objects()) {
            ResourceLocation id = new ResourceLocation(modId, object.name());
            Registry.register(registry, id, object.get());
        }
    }
}
