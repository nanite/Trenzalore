package com.unrealdinnerbone.trenzalore.platform;

import com.unrealdinnerbone.trenzalore.api.platform.services.IPlatformHelper;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class FabricPlatformHelper implements IPlatformHelper {
    @Override
    public String getPlatform() {
        return "Fabric";
    }

    @Override
    public Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public <T extends CriterionTrigger<?>> T registerCriterion(T criterion) {
        return CriteriaTriggers.register(criterion);
    }

    @Override
    @ApiStatus.Internal
    public void addItemToCreativeTab(ResourceKey<CreativeModeTab> tabResourceKey, List<Supplier<? extends Item>> items) {
        ItemGroupEvents.modifyEntriesEvent(tabResourceKey).register(entries -> items.forEach(itemSupplier -> entries.accept(itemSupplier.get())));
    }

    @Override
    public <T> void registryRegistryObjects(String modId, RegistryObjects<T> registryObjects) {
        ResourceKey<Registry<T>> registryKey = registryObjects.registryKey();
        Registry<T> registry = (Registry<T>) BuiltInRegistries.REGISTRY.get(registryKey.location());
        for (RegistryEntry<? extends T> object : registryObjects.objects()) {
            ResourceLocation id = new ResourceLocation(modId, object.name());
            Registry.register(registry, id, object.get());
        }
    }

    @Override
    public <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(BiFunction<BlockPos, BlockState, T> creator, Block... validBlocks) {
        return BlockEntityType.Builder.of(creator::apply, validBlocks).build(null);
    }


    @Override
    public @Nullable <T extends Entity> Entity teleport(T entity, ServerLevel level, PortalInfo portalInfo) {
        return FabricDimensions.teleport(entity, level, portalInfo);
    }

}
