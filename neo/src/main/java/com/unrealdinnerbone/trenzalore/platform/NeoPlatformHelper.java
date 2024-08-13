package com.unrealdinnerbone.trenzalore.platform;

import com.unrealdinnerbone.trenzalore.api.platform.services.IPlatformHelper;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.ApiStatus;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@ApiStatus.Internal
public class NeoPlatformHelper implements IPlatformHelper {

    private static final Map<ResourceKey<CreativeModeTab>, List<Supplier<? extends Item>>> CREATIVE_TAB_MAP = new HashMap<>();

    @Override
    public String getPlatform() {
        return "Neo";
    }

    @Override
    public Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public <T> void registryRegistryObjects(String modId, RegistryObjects<T> registryObjects) {
        DeferredRegister<T> deferredRegister = DeferredRegister.create(registryObjects.registryKey(), modId);
        registryObjects.objects().forEach(registryEntry -> {
            Holder<T> register = deferredRegister.register(registryEntry.name(), registryEntry.entry());
            registryEntry.setHolder(register);
        });
        IEventBus modEventBus = ModList.get().getModContainerById(modId).orElseThrow(() -> new IllegalArgumentException("Mod Not Found: " + modId)).getEventBus();
        if(modEventBus == null) {
            throw new IllegalArgumentException("Mod Does not have and event bus: " + modId);
        }
        deferredRegister.register(modEventBus);
    }


    @Override
    public void addItemToCreativeTab(ResourceKey<CreativeModeTab> tabResourceKey, List<Supplier<? extends Item>> item) {
        if(CREATIVE_TAB_MAP.containsKey(tabResourceKey)) {
            CREATIVE_TAB_MAP.get(tabResourceKey).addAll(item);
        }else {
            CREATIVE_TAB_MAP.put(tabResourceKey, new ArrayList<>(item));
        }
    }

    @ApiStatus.Internal
    public static Map<ResourceKey<CreativeModeTab>, List<Supplier<? extends Item>>> getCreativeTabMap() {
        return CREATIVE_TAB_MAP;
    }


}