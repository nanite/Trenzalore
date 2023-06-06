package com.unrealdinnerbone.trenzalore.content;

import com.google.common.base.Suppliers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import com.unrealdinnerbone.trenzalore.Trenzalore;
import com.unrealdinnerbone.trenzalore.api.config.ConfigManger;
import com.unrealdinnerbone.trenzalore.api.platform.Services;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

public record TrenzaloreConfig(boolean boatFix, boolean fullBruteGoldArmor, boolean poisonArrowsInJungleTemples) {
    public static Supplier<TrenzaloreConfig> CONFIG = Suppliers.memoize(() ->
            ConfigManger.getOrCreateConfig(Trenzalore.MOD_ID, TrenzaloreConfig.class, () -> new TrenzaloreConfig(true, true, true)));
}
