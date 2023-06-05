package com.unrealdinnerbone.trenzalore.content;

import com.google.common.base.Suppliers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import com.unrealdinnerbone.trenzalore.api.platform.Services;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

public record TrenzaloreConfig(boolean boatFix, boolean fullBruteGoldArmor, boolean poisonArrowsInJungleTemples) {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final String CONFIG_NAME = "trenzalore.json";

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static Supplier<TrenzaloreConfig> CONFIG = Suppliers.memoize(() -> {
        try {
            return getConfigData();
        } catch (IOException e) {
            LOGGER.error("Error loading config defaulting to default config", e);
            return new TrenzaloreConfig(true, true, true);
        }
    });


    private static TrenzaloreConfig getConfigData() throws IOException {
        Path configPath = Services.PLATFORM.getConfigPath();
        Path resolve = configPath.resolve(CONFIG_NAME);
        if(!Files.exists(resolve)) {
            TrenzaloreConfig defaultConfig = new TrenzaloreConfig(true, true, true);
            String json = GSON.toJson(defaultConfig);
            Files.writeString(resolve, json);
            return defaultConfig;
        }else {
            String json = Files.readString(resolve);
            return GSON.fromJson(json, TrenzaloreConfig.class);
        }

    }
}
