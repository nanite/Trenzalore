package com.unrealdinnerbone.trenzalore;

import com.unrealdinnerbone.trenzalore.api.platform.Services;
import com.unrealdinnerbone.trenzalore.api.platform.services.IRegistry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import com.unrealdinnerbone.trenzalore.lib.RLUtils;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.ServiceLoader;

public class Trenzalore {
    public static final String MOD_ID = "trenzalore";

    public static void init() {
        ServiceLoader.load(IRegistry.class).forEach(iRegistry -> {
            List<RegistryObjects<?>> registryObjects = iRegistry.getRegistryObjects();
            registryObjects.forEach(registryObject -> Services.PLATFORM.registryRegistryObjects(iRegistry.getModID(), registryObject));
            iRegistry.afterRegistered();
        });
    }

    public static ResourceLocation rl(String value) {
        return RLUtils.rl(MOD_ID, value);
    }

}