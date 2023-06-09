package com.unrealdinnerbone.trenzalore.api.platform.services;

import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.portal.PortalInfo;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.List;

public interface IPlatformHelper {

    String getPlatform();

    Path getConfigPath();

    @Nullable <T extends Entity> Entity teleport(T entity, ServerLevel level, PortalInfo portalInfo);

    @ApiStatus.Internal
    <T extends CriterionTrigger<?>> T registerCriterion(T criterion);

    @ApiStatus.Internal
    void addItemToCreativeTab(ResourceKey<CreativeModeTab> tabResourceKey, List<Item> item);

    @ApiStatus.Internal
    <T> void registryRegistryObjects(String modId, RegistryObjects<T> registryObjects);

}