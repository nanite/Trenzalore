package com.unrealdinnerbone.trenzalore.api.platform;

import com.unrealdinnerbone.trenzalore.api.platform.services.IPlatformHelper;
import org.jspecify.annotations.NullMarked;

import java.util.ServiceLoader;

@NullMarked
public class Services {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
    }

    public static <T> Iterable<T> loadAll(Class<T> clazz) {
        return ServiceLoader.load(clazz);
    }
}