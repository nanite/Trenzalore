package com.unrealdinnerbone.trenzalore.api.platform.services;

import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface IInternalUtils {

    <T> void registryRegistryObjects(String modId, RegistryObjects<T> registryObjects);
}
