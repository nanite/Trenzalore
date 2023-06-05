package com.unrealdinnerbone.trenzalore.api.platform.services;

import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;

import java.util.List;

public interface IRegistry {
    List<RegistryObjects<?>> getRegistryObjects();

    String getModID();
}
