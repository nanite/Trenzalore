package com.unrealdinnerbone.trenzalore.platform;

import com.unrealdinnerbone.trenzalore.api.platform.services.IInternalUtils;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class ForgeInternalUtils implements IInternalUtils {

    @Override
    public <T> void registryRegistryObjects(String modId, RegistryObjects<T> registryObjects) {
        DeferredRegister<T> deferredRegister = DeferredRegister.create(registryObjects.registryKey(), modId);
        registryObjects.objects().forEach(registryEntry -> deferredRegister.register(registryEntry.name(), registryEntry.entry()));
        deferredRegister.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
