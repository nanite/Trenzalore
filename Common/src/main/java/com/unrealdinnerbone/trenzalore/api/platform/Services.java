package com.unrealdinnerbone.trenzalore.api.platform;

import com.unrealdinnerbone.trenzalore.api.platform.services.IInternalUtils;
import com.unrealdinnerbone.trenzalore.api.platform.services.IPlatformHelper;
import com.unrealdinnerbone.trenzalore.api.platform.services.IPlatformUtils;
import org.jetbrains.annotations.ApiStatus;

import java.util.ServiceLoader;

public class Services {
    @ApiStatus.Internal
    public static final IInternalUtils INTERNAL = load(IInternalUtils.class);

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static final IPlatformUtils UTILS = load(IPlatformUtils.class);


    public static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
    }

}