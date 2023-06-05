package com.unrealdinnerbone.trenzalore;

import com.unrealdinnerbone.trenzalore.api.platform.Services;
import com.unrealdinnerbone.trenzalore.api.platform.services.IRegistry;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.util.ServiceLoader;

public class Trenzalore {

    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "trenzalore";

    public static void init() {
        ServiceLoader.load(IRegistry.class)
                        .forEach(iRegistry -> iRegistry.getRegistryObjects()
                                .forEach(registryObject -> Services.INTERNAL.registryRegistryObjects(iRegistry.getModID(), registryObject)));
    }
}