package com.unrealdinnerbone.trenzalore.api.platform.services;

import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Supplier;

public interface IPlatformHelper extends ICreativeTabRegister {

    String getPlatform();

    Path getConfigPath();

    boolean isModLoaded(String modId);

    @ApiStatus.Internal
    <T> void registryRegistryObjects(String modId, RegistryObjects<T> registryObjects);

}