package com.unrealdinnerbone.trenzalore.platform;

import com.unrealdinnerbone.trenzalore.api.platform.services.IPlatformHelper;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import com.unrealdinnerbone.trenzalore.lib.RLUtils;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class FabricPlatformHelper implements IPlatformHelper {

    private static final Map<ResourceKey<CreativeModeTab>, Event<ItemGroupEvents.ModifyEntries>> creativeTabEvents = new HashMap<>();

    @Override
    public String getPlatform() {
        return "Fabric";
    }

    @Override
    public Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    @ApiStatus.Internal
    public void addItemToCreativeTab(ResourceKey<CreativeModeTab> tabResourceKey, List<Supplier<? extends Item>> items) {
        if(!creativeTabEvents.containsKey(tabResourceKey)) {
            creativeTabEvents.put(tabResourceKey, ItemGroupEvents.modifyEntriesEvent(tabResourceKey));
        }
        creativeTabEvents.get(tabResourceKey).register(entries -> items.forEach(itemSupplier -> entries.accept(itemSupplier.get())));
    }

    @Override
    public <T> void registryRegistryObjects(String modId, RegistryObjects<T> registryObjects) {
        ResourceKey<Registry<T>> registryKey = registryObjects.registryKey();
        Registry<T> registry = (Registry<T>) BuiltInRegistries.REGISTRY.get(registryKey.location());
        for (RegistryEntry<? extends T> object : registryObjects.objects()) {
            ResourceLocation id = RLUtils.rl(modId, object.name());
            Registry.register(registry, id, object.get());
        }
    }

}
