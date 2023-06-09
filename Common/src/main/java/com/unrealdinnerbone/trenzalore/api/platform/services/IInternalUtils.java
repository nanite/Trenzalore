package com.unrealdinnerbone.trenzalore.api.platform.services;

import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

@Deprecated(forRemoval = true)
@ApiStatus.Internal
@ApiStatus.ScheduledForRemoval(inVersion = "3.0.0")
public interface IInternalUtils {
    <T> void registryRegistryObjects(String modId, RegistryObjects<T> registryObjects);

}
