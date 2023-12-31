package com.unrealdinnerbone.trenzalore.api.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import com.unrealdinnerbone.trenzalore.api.platform.Services;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

public class ConfigManger {

    private static final Logger LOGGER = LogUtils.getLogger();


    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "5.0.0")
    public static <T> T getOrCreateConfig(String name, Class<T> tClass, Supplier<T> defaultValue) {
        Path configPath = Services.PLATFORM.getConfigPath();
        Path config = configPath.resolve(name + ".json");
        if(!Files.exists(config)) {
            T defaultConfig = defaultValue.get();
            try {
                Files.write(config, GSON.toJson(defaultConfig).getBytes());
            } catch (Exception e) {
                LOGGER.error("Error saving config. Defaulting to default config", e);
            }
            return defaultConfig;
        }
        try {
            return GSON.fromJson(Files.readString(config), tClass);
        } catch (Exception e) {
            LOGGER.error("Error loading config. Defaulting to default config", e);
            return defaultValue.get();
        }
    }

}
