package com.unrealdinnerbone.trenzalore.platform;

import com.unrealdinnerbone.trenzalore.api.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class FabricPlatformHelper implements IPlatformHelper {
    @Override
    public String getPlatform() {
        return "Fabric";
    }

    @Override
    public Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir();
    }

}
