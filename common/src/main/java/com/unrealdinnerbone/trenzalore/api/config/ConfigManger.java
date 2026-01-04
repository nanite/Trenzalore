package com.unrealdinnerbone.trenzalore.api.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
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
    @ApiStatus.ScheduledForRemoval(inVersion = "26.1.0")
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

    public static <T> T getOrCreateCodecConfig(String name, Codec<T> codec, Supplier<T> defaultValue) {
        Path configPath = Services.PLATFORM.getConfigPath();
        Path config = configPath.resolve(name + ".json");
        if (!Files.exists(config)) {
            T defaultConfig = defaultValue.get();
            save(codec, defaultConfig, config);
            return defaultConfig;
        } else {
            try {
                String jsonString = Files.readString(config);
                JsonElement parse = new JsonParser().parse(jsonString);
                DataResult<T> data = codec.parse(JsonOps.INSTANCE, parse);
                T orThrow = data.getOrThrow();
                save(codec, orThrow, config);
                return orThrow;
            } catch (Exception e) {
                LOGGER.error("Error loading config. Defaulting to default config", e);
                return defaultValue.get();
            }
        }
    }

    private static <T> void save(Codec<T> codec, T config, Path path) {
        try {
            DataResult<JsonElement> result = codec.encodeStart(JsonOps.INSTANCE, config);
            if (result.result().isPresent()) {
                if (!Files.exists(path.getParent())) {
                    Files.createDirectories(path.getParent());
                }
                Files.writeString(path, GSON.toJson(result.result().get()));
            } else {
                LOGGER.error("Failed to load config: {}", result.error().get());
            }
        } catch (Exception e) {
            LOGGER.error("Error saving config. Defaulting to default config", e);
        }
    }

}
