package com.unrealdinnerbone.trenzalore.api.platform.services;

import com.unrealdinnerbone.trenzalore.api.registry.Regeneration;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalInfo;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public interface IPlatformHelper {

    String getPlatform();

    Path getConfigPath();

    boolean isModLoaded(String modId);

    @Nullable
    @ApiStatus.Internal
    <T extends Entity> Entity teleport(T entity, ServerLevel level, PortalInfo portalInfo);
    @ApiStatus.Internal
    void addItemToCreativeTab(ResourceKey<CreativeModeTab> tabResourceKey, List<Supplier<? extends Item>> item);
    @ApiStatus.Internal
    <T> void registryRegistryObjects(String modId, RegistryObjects<T> registryObjects);

    @Deprecated(forRemoval = true)
    @ApiStatus.ScheduledForRemoval(inVersion = "4.0.0")
    default <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(BiFunction<BlockPos, BlockState, T> creator, Block... validBlocks) {
        return Regeneration.createBlockEntityType(creator, validBlocks);
    }

}