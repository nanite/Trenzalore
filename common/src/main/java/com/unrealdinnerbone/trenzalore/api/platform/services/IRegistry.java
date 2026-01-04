package com.unrealdinnerbone.trenzalore.api.platform.services;

import com.unrealdinnerbone.trenzalore.api.registry.AbstractRegistryObjects;

import java.util.List;

public interface IRegistry {

    List<AbstractRegistryObjects<?>> getRegistryObjects();

    String getModID();

    default void afterRegistered(ICreativeTabRegister creativeTabRegister) {}

}
